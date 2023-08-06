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
import javax.transaction.Transactional;
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
            response.setSuccess(true);
            return ResponseEntity.ok().body(response);
        } else return ResponseEntity.notFound().build();
    }

    @Transactional
    public ResponseEntity<Response> editProduct(ProductCommand command) {
        Response response = new Response();
        Product product = productRepository.findById(command.getID()).orElseThrow(() -> new IllegalStateException("Not Found"));
        if (product != null) {
            product.setID(command.getID());
            product.setName(command.getName());
            product.setDescription(command.getDescription());
            product.setPrice(command.getPrice());
            product.setTotalSold(command.getTotalSold());
            product.setOff(command.getOff());
            product.setAmount(command.getAmount());
            try {
                productRepository.save(product);
                response.setMessage("Product updated");
                response.setSuccess(true);
                return ResponseEntity.ok(response);
            } catch (Exception e) {
                response.setMessage(e.getMessage());
                response.setSuccess(false);
                return ResponseEntity.internalServerError().body(response);
            }
        } else return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Response> deleteById(Long id) {
        Response response = new Response();
        try {
            productRepository.deleteById(id);
            response.setSuccess(true);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Error occurred!");
            return ResponseEntity.internalServerError().body(response);

        }
    }
}
