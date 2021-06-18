package br.com.stockquote.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.stockquote.model.QuotedStock;

@Repository
public interface QuotedStockRepository extends JpaRepository<QuotedStock, Long>{
	Optional<QuotedStock> findById(Long id);
	List<QuotedStock> findByIdStock(String id);
	
}
