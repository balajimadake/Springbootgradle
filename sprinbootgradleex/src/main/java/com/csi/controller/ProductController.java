package com.csi.controller;

import com.csi.exception.RecordNotFounfException;
import com.csi.model.Product;
import com.csi.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService productService;

    @PostMapping("/save")
    public ResponseEntity<Product> save(@RequestBody Product product) {
        return ResponseEntity.ok(productService.save(product));
    }

    @GetMapping("/findbyid/{productId}")
    public ResponseEntity<Optional<Product>> findById(@PathVariable long productId) {
        return ResponseEntity.ok(productService.findBYId(productId));
    }

    @GetMapping("/findbyname/{productName}")
    public ResponseEntity<List<Product>> findByName(@PathVariable String productName) {
        return ResponseEntity.ok(productService.findByName(productName));
    }

    @GetMapping("/findall")
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/sortbyprice")
    public ResponseEntity<List<Product>> sortByPrice(@RequestParam(required = false,defaultValue = "") String price){
        return ResponseEntity.ok(productService.sortByPrice(price));
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<Product> update(@PathVariable long productId, @RequestBody Product product) {
        Product product1 = productService.findBYId(productId).orElseThrow(() -> new RecordNotFounfException("###Product ID not Exist"));
        product1.setProductName(product.getProductName());
        product1.setProductPrice(product.getProductPrice());
        product1.setProductId(product.getProductId());

        return ResponseEntity.ok(productService.update(product1));

    }

    @DeleteMapping("/deletebyid/{productId}")
    public ResponseEntity<String> deleteById(@PathVariable long productId) {
        productService.deleteById(productId);
        return ResponseEntity.ok("Data deleted successfully For ProductID +" + productId);
    }
}
