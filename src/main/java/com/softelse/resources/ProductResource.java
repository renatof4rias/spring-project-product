package com.softelse.resources;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.softelse.dtos.ProductRecordDto;
import com.softelse.entities.Product;
import com.softelse.repositories.ProductRepository;

import jakarta.validation.Valid;

@RestController
public class ProductResource {

	@Autowired
	private ProductRepository repository;
	
	@PostMapping("/products")
	public ResponseEntity<Product> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto){
		var product = new Product();
		BeanUtils.copyProperties(productRecordDto, product);
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(product));
	}
	
}
