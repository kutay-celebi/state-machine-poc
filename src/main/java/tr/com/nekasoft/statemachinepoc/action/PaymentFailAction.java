package tr.com.nekasoft.statemachinepoc.action;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;
import tr.com.nekasoft.statemachinepoc.service.OrderService;

@RequiredArgsConstructor
@Component
@Slf4j
public class PaymentFailAction implements Action<String, String> {

    private final OrderService orderService;

    @Override
    public void execute(StateContext<String, String> context) {
        Boolean isFail = orderService.failOrder(context.getStateMachine().getId());
        if (Boolean.FALSE.equals(isFail)) {
            context.getStateMachine().setStateMachineError(new Exception("fail action is aborted"));
        }
    }
}
