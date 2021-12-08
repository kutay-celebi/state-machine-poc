package tr.com.nekasoft.statemachinepoc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.service.StateMachineService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.com.nekasoft.statemachinepoc.action.OrderEvent;
import tr.com.nekasoft.statemachinepoc.entity.Order;
import tr.com.nekasoft.statemachinepoc.entity.Product;
import tr.com.nekasoft.statemachinepoc.repository.OrderRepository;
import tr.com.nekasoft.statemachinepoc.repository.ProductRepository;

import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final StateMachineService<String, String> stateMachineService;


    public String createProcess(String productName, Long quantity) {
        Product product = productRepository.findByNameContains(productName);

        if (product == null) {
            throw new RuntimeException("produc does not exists");
        }

        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);

        Order order = Order.builder().product(product).quantity(quantity).build();
        Order savedOrder = orderRepository.save(order);

        StateMachine<String, String> stateMachine = stateMachineService.acquireStateMachine(savedOrder.getId(), true);
        stateMachine.getExtendedState().getVariables().put("product-name", product.getName());
        return order.getId();
    }

    public Boolean failOrder(String orderId) {
        StateMachine<String, String> stateMachine = stateMachineService.acquireStateMachine(orderId);
        Optional<Order> orderOptional = orderRepository.findById(orderId);

        if (orderOptional.isEmpty()) {
            return false;
        }


        String productName = stateMachine.getExtendedState().get("product-name", String.class);
        Product product = productRepository.findByNameContains(productName);
        product.setQuantity(product.getQuantity() + orderOptional.get().getQuantity());

        stateMachine.sendEvent(OrderEvent.FAIL.name());
        return Boolean.TRUE;
    }
}
