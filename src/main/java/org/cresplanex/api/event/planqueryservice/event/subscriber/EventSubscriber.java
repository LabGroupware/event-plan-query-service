package org.cresplanex.api.event.planqueryservice.event.subscriber;

import lombok.AllArgsConstructor;

import org.cresplanex.api.event.planqueryservice.event.handler.TaskEventHandler;
import org.cresplanex.api.state.common.constants.ServiceType;
import org.cresplanex.api.state.common.event.EventAggregateType;
import org.cresplanex.api.state.common.event.subscriber.BaseSubscriber;
import org.cresplanex.core.events.subscriber.DomainEventDispatcher;
import org.cresplanex.core.events.subscriber.DomainEventDispatcherFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class EventSubscriber extends BaseSubscriber {

    @Bean
    public DomainEventDispatcher taskDomainEventDispatcher(
            TaskEventHandler taskEventHandler,
            DomainEventDispatcherFactory domainEventDispatcherFactory
    ) {
        return domainEventDispatcherFactory.make(
                this.getDispatcherId(ServiceType.NOVA_PLAN + "Query", EventAggregateType.PLAN_TASK),
                taskEventHandler.domainEventHandlers()
        );
    }
}
