package dev.rayh.jwt.repository;

import dev.rayh.jwt.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}