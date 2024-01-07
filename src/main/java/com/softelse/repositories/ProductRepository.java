package com.softelse.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softelse.entities.Product;

public interface ProductRepository extends JpaRepository<Product, UUID>{

}
