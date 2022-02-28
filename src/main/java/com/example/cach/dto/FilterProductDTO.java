package com.example.cach.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class FilterProductDTO {

    private List<ProductDTO> data;

    private LocalDateTime lastFetch;

}
