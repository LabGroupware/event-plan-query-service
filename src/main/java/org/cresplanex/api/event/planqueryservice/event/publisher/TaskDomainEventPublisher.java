package org.cresplanex.api.event.planqueryservice.event.publisher;

import org.cresplanex.api.event.planqueryservice.entity.TaskEntity;
import org.cresplanex.api.state.common.event.EventAggregateType;
import org.cresplanex.api.state.common.event.model.plan.TaskDomainEvent;
import org.cresplanex.api.state.common.event.publisher.AggregateDomainEventPublisher;
import org.cresplanex.core.events.publisher.DomainEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class TaskDomainEventPublisher extends AggregateDomainEventPublisher<TaskEntity, TaskDomainEvent> {

    public TaskDomainEventPublisher(DomainEventPublisher eventPublisher) {
        super(eventPublisher, TaskEntity.class, EventAggregateType.PLAN_TASK);
    }
}
