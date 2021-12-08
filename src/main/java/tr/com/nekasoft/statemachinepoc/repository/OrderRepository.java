package tr.com.nekasoft.statemachinepoc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tr.com.nekasoft.statemachinepoc.entity.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, String> {
}
