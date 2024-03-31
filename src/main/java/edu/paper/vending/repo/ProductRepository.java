package edu.paper.vending.repo;

import edu.paper.vending.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
