package ir.znu.znuproject.service;

import ir.znu.znuproject.command.ProductCommand;

import ir.znu.znuproject.dto.ProductDTO;
import ir.znu.znuproject.dto.ProductDtoMapper;
import ir.znu.znuproject.model.Product;
import ir.znu.znuproject.repository.ProductRepository;
import ir.znu.znuproject.shared.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductDtoMapper productDtoMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductDtoMapper productDtoMapper
    ) {
        this.productRepository = productRepository;
        this.productDtoMapper = productDtoMapper;
    }

    public ResponseEntity<Response> save(ProductCommand command) {
        Product savingProduct = new Product();
        Response response = new Response<>();
        try {
            savingProduct.setOff(command.getOff());
            savingProduct.setName(command.getName());
            savingProduct.setPrice(command.getPrice());
            savingProduct.setAmount(command.getAmount());
            savingProduct.setTotalSold(command.getTotalSold());
            savingProduct.setDescription(command.getDescription());
            System.out.println(savingProduct.toString());
            productRepository.save(savingProduct);
            response.setSuccess(true);
            response.setMessage("New record saved!");
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(e.toString());
            return ResponseEntity.internalServerError().body(response);
        }
    }

    public ResponseEntity<Response> getAllProducts() {
        Response response = new Response();
        Map map = new HashMap<String, List<ProductDTO>>();
        List<ProductDTO> products = productRepository.findAll().stream().map(productDtoMapper).collect(Collectors.toList());
        try {
            map.put("products", products);
            map.put("length", products.size());
            response.setData(map);
            response.setSuccess(true);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error occurred!");
            return ResponseEntity.internalServerError().body(response);
        }
    }

    public ResponseEntity<Response> findById(Long id) {
        Response response = new Response();
        Map map = new HashMap<String, List<ProductDTO>>();
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            map.put("Product", product);
            response.setData(map);
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
