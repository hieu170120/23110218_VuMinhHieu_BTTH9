package com.example.demo.DTO;

import java.util.List;

public record CategoryInput(
    String name,
    String images,
    List<Long> productIds,
    List<Long> userIds
) {}
