package tr.com.nekasoft.statemachinepoc.action;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class PaymentSuccessAction implements Action<String, String> {
    @Override
    public void execute(StateContext<String, String> context) {
        log.info("The payment for {} product was successful.", context.getExtendedState()
                .get("product-name", String.class));
    }
}
