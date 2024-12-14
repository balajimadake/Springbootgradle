package com.csi.service;

import com.csi.model.Product;
import com.csi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> findBYId(long productId) {
        return productRepository.findById(productId);
    }

    @Override
    public List<Product> findByName(String productName) {
        return productRepository.findAll().stream().filter(pd->pd.getProductName().equals(productName)).toList();
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteById(long productId) {
        productRepository.deleteById(productId);

    }

    @Override
    public List<Product> sortByProductPrice(String price) {
        return switch (price){
            case "PriceASC"->productRepository.findAll().stream().sorted(Comparator.comparing(Product::getProductPrice)).toList();
            case "PriceDSC"->productRepository.findAll().stream().sorted(Comparator.comparing(Product::getProductPrice).reversed()).toList();
            default ->productRepository.findAll().stream().sorted(Comparator.comparing(Product::getProductPrice)).toList();
        };

    }
}
