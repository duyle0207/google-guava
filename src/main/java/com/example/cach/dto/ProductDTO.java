package com.example.cach.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ProductDTO {

    private String name;

    private String category;

    private LocalDateTime lastFetch;

}
