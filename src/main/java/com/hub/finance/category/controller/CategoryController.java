package com.hub.finance.category.controller;

import com.hub.finance.category.dto.CategoryRequestDTO;
import com.hub.finance.category.dto.CategoryResponseDTO;
import com.hub.finance.category.dto.UpdateCategoryRequest;
import com.hub.finance.category.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> findById(@PathVariable UUID id) {

        CategoryResponseDTO categoryResponseDTO = categoryService.findById(id);

        return ResponseEntity.ok(categoryResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable UUID id, @RequestBody UpdateCategoryRequest request) {

        categoryService.update(id, request);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {

        categoryService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
