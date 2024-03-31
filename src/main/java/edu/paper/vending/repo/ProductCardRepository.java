package edu.paper.vending.repo;

import edu.paper.vending.model.ProductCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCardRepository extends CrudRepository<ProductCard, Long> {
    List<ProductCard> findAllByTitle(String title);
}
