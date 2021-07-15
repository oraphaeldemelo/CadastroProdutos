package com.raphael.CadastroProdutos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raphael.CadastroProdutos.model.Produto;
import com.raphael.CadastroProdutos.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {
	
	@Autowired
	private ProdutoRepository repository;
	
	@GetMapping
	public List<Produto> listar(){
		return repository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Produto> cadastrar(@RequestBody Produto novoProduto) {
		return ResponseEntity.ok(repository.save(novoProduto));
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Produto> atualizar(@RequestBody Produto produtoAtualizado, @PathVariable Long id) {
		Optional<Produto> buscaProduto = repository.findById(id);
		if(buscaProduto.isPresent()) {
			produtoAtualizado.setId(id);
			return ResponseEntity.ok(repository.save(produtoAtualizado));
		} else {
			return ResponseEntity.notFound().build(); 
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id) {
		repository.deleteById(id);
		return ResponseEntity.ok().build();
	}
	

}
