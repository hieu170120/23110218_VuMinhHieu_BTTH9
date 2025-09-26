package com.example.demo.DTO;

import java.util.List;

public record ProductInput(
    String title,
    Integer quantity,
    String desc,
    Double price,
    Long ownerId,
    List<Long> categoryIds
) {}
