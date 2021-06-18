package br.com.stockquote.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.stockquote.model.StockDTO;

@Repository
public interface CacheRepository extends JpaRepository<StockDTO, Long> {
	Optional<StockDTO> findById(String id);
}
