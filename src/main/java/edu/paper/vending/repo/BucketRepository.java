package edu.paper.vending.repo;

import edu.paper.vending.model.Bucket;
import edu.paper.vending.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BucketRepository extends CrudRepository<Bucket, Long> {
    List<Bucket> findBucketsByProducts(Product product);
    List<Bucket> findBucketsByUser(User user);
    Bucket findBucketById(Long id);
}
