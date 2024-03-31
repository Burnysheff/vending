package edu.paper.vending.repo;

import edu.paper.vending.model.Operation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OperationRepository extends CrudRepository<Operation, Long> {
    Optional<Operation> findById(Long id);
}
