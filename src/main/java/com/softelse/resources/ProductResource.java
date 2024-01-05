package com.softelse.resources;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts(){
		return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
	}
	
	@GetMapping("/products/{id}")
	public ResponseEntity<Object> getOneProduct(@PathVariable(value="id")UUID id){
		Optional<Product> product = repository.findById(id);
		if(product.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
		}
		return ResponseEntity.status(HttpStatus.OK).body(product.get());
	}
	
	
}
