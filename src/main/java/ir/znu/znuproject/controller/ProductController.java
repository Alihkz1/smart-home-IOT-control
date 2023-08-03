package ir.znu.znuproject.controller;

import ir.znu.znuproject.command.LogCommand;
import ir.znu.znuproject.command.ProductCommand;
import ir.znu.znuproject.dto.LogDTO;
import ir.znu.znuproject.model.Product;
import ir.znu.znuproject.service.ProductService;
import ir.znu.znuproject.shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    ResponseEntity<Response> getProductList() {
        return productService.getAllProducts();
    }

    @PostMapping(path = "add", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Response> save(@RequestBody(required = true) ProductCommand command) {
        return productService.save(command);
    }

    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> getById(@PathVariable Long id) {
        return productService.findById(id);
    }
}
