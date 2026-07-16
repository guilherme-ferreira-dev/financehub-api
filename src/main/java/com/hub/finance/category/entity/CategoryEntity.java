package com.hub.finance.category.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "category")
public class CategoryEntity {

    @Id
    private UUID id;

    @Column(nullable = false, length = 100)
    private String name;

}
