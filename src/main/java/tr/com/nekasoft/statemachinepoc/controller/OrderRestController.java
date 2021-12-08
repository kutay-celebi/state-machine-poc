package tr.com.nekasoft.statemachinepoc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.service.StateMachineService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tr.com.nekasoft.statemachinepoc.action.OrderEvent;
import tr.com.nekasoft.statemachinepoc.service.OrderService;

@RestController
@RequiredArgsConstructor
public class OrderRestController {

    private final OrderService orderService;
    private final StateMachineService<String, String> stateMachineService;

    @GetMapping(value = "/send-to-basket", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> order(@RequestParam("product") String name, @RequestParam("quantity") Long quantity) {
        String orderId = orderService.createProcess(name, quantity);
        return ResponseEntity.ok(orderId);
    }

    @GetMapping("/create-payment")
    public void order(@RequestParam("order-id") String id) {
        StateMachine<String, String> stateMachine = stateMachineService.acquireStateMachine(id);
    }

    @GetMapping("/fail")
    public void fail(@RequestParam("order-id") String id) {
        orderService.failOrder(id);
    }

    @GetMapping("/success")
    public void success(@RequestParam("order-id") String id) {
        StateMachine<String, String> stateMachine = stateMachineService.acquireStateMachine(id);
        stateMachine.sendEvent(OrderEvent.SUCCESS.name());
    }
}
