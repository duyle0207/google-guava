package com.example.cach.service;

import com.example.cach.dto.FilterProductDTO;
import com.example.cach.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    FilterProductDTO findByCategory(String category);

}
