package edu.paper.vending.repo;

import edu.paper.vending.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Vending extends CrudRepository<Vending, Long> {
    List<Vending> findAllByProduct(Product product);
}
