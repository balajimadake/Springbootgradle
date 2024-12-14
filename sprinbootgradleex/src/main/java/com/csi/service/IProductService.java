package com.csi.service;

import com.csi.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    Product save (Product product);

    Optional<Product> findBYId(long productId);

    List<Product> findByName(String productName);

   // List<Product> findByProductPrice(Double productPrice);

   // List<Product> findByProductLaunchDate(String ProductLaunchDate);

    List<Product> findAll();

    Product update (Product product);

    void deleteById(long productId);


    List<Product> sortByProductPrice(String price);
}
