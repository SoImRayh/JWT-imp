package dev.rayh.jwt.controller;

import dev.rayh.jwt.model.Produto;
import dev.rayh.jwt.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/produto")
@CrossOrigin("*")
public class ProdutoController {
    private final ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @PostMapping("")
    public ResponseEntity<Produto> add(@RequestBody Produto produto){
        produto = produtoRepository.save(produto);
        return new ResponseEntity<>(produto, HttpStatusCode.valueOf(201));
    }

    @GetMapping("/teste")
    public String sayhello(){
        throw new BadCredentialsException("bad credentials");
    }

    @GetMapping("/all")
    public ResponseEntity<List<Produto>> all(){
        System.out.println("indo buscar");
        var produtos = produtoRepository.findAll();
        return new ResponseEntity<>(produtos, HttpStatusCode.valueOf(200));
    }
}
