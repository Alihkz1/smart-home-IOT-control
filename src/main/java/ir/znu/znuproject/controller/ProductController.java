package ir.znu.znuproject.controller;

import ir.znu.znuproject.command.product.ProductCreateCommand;
import ir.znu.znuproject.command.product.ProductEditCommand;
import ir.znu.znuproject.dto.product.ProductListDTO;
import ir.znu.znuproject.service.ProductService;
import ir.znu.znuproject.shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("api/v1/products")
@CrossOrigin(origins = "*")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<ProductListDTO>> list() {
        return productService.getList();
    }

    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> getById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @PostMapping(path = "create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> create(@RequestBody(required = true) ProductCreateCommand command) {
        return productService.create(command);
    }

    @PutMapping(path = "edit", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> edit(@RequestBody(required = true) ProductEditCommand command) {
        return productService.editProduct(command);
    }

    @DeleteMapping(path = "delete/{id}")
    public ResponseEntity<Response> deleteById(@PathVariable Long id) {
        return productService.deleteById(id);
    }

    @DeleteMapping(path = "deleteall",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> deleteAll(){
        return productService.deleteAll();
    }

}
