package tr.com.nekasoft.statemachinepoc.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import tr.com.nekasoft.statemachinepoc.action.OrderEvent;
import tr.com.nekasoft.statemachinepoc.action.OrderStates;

import java.util.HashMap;

@Slf4j
public class StateMachineMapPersist implements StateMachinePersist<OrderStates, OrderEvent, String> {

    private HashMap<String, StateMachineContext<OrderStates, OrderEvent>> storage = new HashMap<>();

    //@Override
    //public void persist(StateMachine<ApplicationStates, ApplicationEvent> stateMachine, String contextObj)
    //        throws Exception {
    //    log.info("{} context obj has been persisted", contextObj);
    //    storage.put(contextObj, stateMachine);
    //}
    //
    //@Override
    //public StateMachine<ApplicationStates, ApplicationEvent> restore(
    //        StateMachine<ApplicationStates, ApplicationEvent> stateMachine, String contextObj) throws Exception {
    //    log.info("{} context obj has been restored", contextObj);
    //    return storage.get(contextObj);
    //}

    @Override
    public void write(StateMachineContext<OrderStates, OrderEvent> context, String contextObj) throws Exception {
        log.info("{} context obj has been persisted", contextObj);
        storage.put(contextObj, context);
    }

    @Override
    public StateMachineContext<OrderStates, OrderEvent> read(String contextObj) throws Exception {
        log.info("{} context obj has been restored", contextObj);
        return storage.get(contextObj);
    }
}
