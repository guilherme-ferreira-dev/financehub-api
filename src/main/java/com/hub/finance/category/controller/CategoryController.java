package com.hub.finance.category.controller;

import com.hub.finance.category.dto.CategoryRequestDTO;
import com.hub.finance.category.dto.CategoryResponseDTO;
import com.hub.finance.category.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CategoryRequestDTO requestDTO) {
        categoryService.create(requestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> findAll() {

        List<CategoryResponseDTO> categories = categoryService.findAll();

        return ResponseEntity.ok(categories);
    }

}
