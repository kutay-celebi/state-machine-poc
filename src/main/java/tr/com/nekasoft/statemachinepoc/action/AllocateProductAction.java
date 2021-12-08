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
public class AllocateProductAction implements Action<String, String> {

    private final OrderService orderService;

    @Override
    public void execute(StateContext<String, String> context) {
        log.info("{} product for allocated", context.getExtendedState().getVariables().get("product-name"));
    }
}
