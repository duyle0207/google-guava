package com.example.cach.service.impl;

import com.example.cach.dto.FilterProductDTO;
import com.example.cach.dto.ProductDTO;
import com.example.cach.service.ProductService;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ProductServiceImpl implements ProductService {

    private List<ProductDTO> products;

    private Cache<String, FilterProductDTO> cache;

    @PostConstruct
    public void cacheConfig() {
        CacheLoader<String, FilterProductDTO> loader;
        loader = new CacheLoader<>() {
            @Override
            public FilterProductDTO load(String key) {
                return null;
            }
        };

        cache = CacheBuilder
                .newBuilder()
                .expireAfterWrite(60 * 100, TimeUnit.MILLISECONDS)
                .build(loader);
    }

    @PostConstruct
    public void initProducts() {
        FilterProductDTO filterProductDTO = new FilterProductDTO();
        filterProductDTO.setLastFetch(LocalDateTime.now());

        List<ProductDTO> lstData = new ArrayList<>();
        products.add(ProductDTO.builder().category("A").name("A1").build());
        products.add(ProductDTO.builder().category("B").name("B1").build());
        products.add(ProductDTO.builder().category("A").name("A2").build());
        products.add(ProductDTO.builder().category("A").name("A3").build());
        filterProductDTO.setData(lstData);
    }

    @Override
    public FilterProductDTO findByCategory(String category) {
        return null;
    }
}
