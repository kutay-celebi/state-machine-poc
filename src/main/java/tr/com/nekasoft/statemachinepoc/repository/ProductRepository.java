package tr.com.nekasoft.statemachinepoc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tr.com.nekasoft.statemachinepoc.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, String> {

    Product findByNameContains(String name);
}
