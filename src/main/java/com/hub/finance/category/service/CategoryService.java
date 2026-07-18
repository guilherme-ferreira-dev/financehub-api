package com.hub.finance.category.service;

import com.hub.finance.category.dto.CategoryRequestDTO;
import com.hub.finance.category.dto.CategoryResponseDTO;
import com.hub.finance.category.entity.CategoryEntity;
import com.hub.finance.category.exception.CategoryNotFoundException;
import com.hub.finance.category.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {
    private final CategoryRepository repository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.repository = categoryRepository;
    }

    public void create(CategoryRequestDTO request) {

        if (repository.existsByName(request.name())) {
            throw new RuntimeException("Category already exists");
        }

        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setId(UUID.randomUUID());
        categoryEntity.setName(request.name());
        categoryEntity.setDescription(request.description());

        repository.save(categoryEntity);

    }

    public List<CategoryResponseDTO> findAll() {

        List<CategoryEntity> categories = repository.findAll();

        return categories.stream()
                .map(categoryEntity -> new CategoryResponseDTO(
                        categoryEntity.getId(),
                        categoryEntity.getName(),
                        categoryEntity.getDescription()
                ))
                .toList();
    }

    public CategoryResponseDTO findById(UUID id) {

        CategoryEntity categoryEntity = repository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);

        return new CategoryResponseDTO(
                categoryEntity.getId(),
                categoryEntity.getName(),
                categoryEntity.getDescription()
        );
    }
}
