package zjt.learn.infrastructure.statemachine.depositorder.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachineRuntimePersister;
import org.springframework.statemachine.service.DefaultStateMachineService;
import org.springframework.statemachine.service.StateMachineService;
import zjt.learn.statemashine.BaseEvent;
import zjt.learn.statemashine.BaseState;

/**
 * 功能：
 *
 * @Author: zhaojiatao
 * @Date: 2021/11/8 17:03
 * @ClassName: DepositStateMachineConfig
 */
@Configuration
public class DepositStateMachineConfig {
    @Autowired
    @Qualifier("depositOrderFactory")
    StateMachineFactory<BaseState, BaseEvent> depositOrderFactory;

    @Bean(name = "depositOrderStateMachineService")
    public StateMachineService<BaseState, BaseEvent> stateMachineService(
            StateMachineRuntimePersister<BaseState, BaseEvent, String> commonStateMachineRuntimePersister) {
        return new DefaultStateMachineService<>(depositOrderFactory, commonStateMachineRuntimePersister);
    }
}
