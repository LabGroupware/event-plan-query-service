package org.cresplanex.api.event.planqueryservice.event.handler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.cresplanex.api.event.planqueryservice.entity.TaskEntity;
import org.cresplanex.api.event.planqueryservice.mapper.dto.DtoMapper;
import org.cresplanex.api.event.planqueryservice.service.TaskService;
import org.cresplanex.api.state.common.event.EventAggregateType;
import org.cresplanex.api.state.common.event.model.plan.TaskCreated;
import org.cresplanex.api.state.common.event.model.plan.TaskUpdatedStatus;
import org.cresplanex.api.state.common.saga.data.plan.CreateTaskResultData;
import org.cresplanex.api.state.common.saga.data.plan.UpdateStatusTaskResultData;
import org.cresplanex.core.events.common.DomainEventEnvelope;
import org.cresplanex.core.events.subscriber.DomainEventHandlers;
import org.cresplanex.core.events.subscriber.DomainEventHandlersBuilder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Slf4j
@AllArgsConstructor
@Component
public class TaskEventHandler {

    private final TaskService taskService;

    public DomainEventHandlers domainEventHandlers() {
        var handlerBuilder = DomainEventHandlersBuilder
                .forAggregateType(EventAggregateType.PLAN_TASK);

        handlerBuilder
            .onEvent(TaskCreated.SuccessJobDomainEvent.class, this::handleSuccessfullyTaskCreated, TaskCreated.SuccessJobDomainEvent.TYPE)
            .onEvent(TaskUpdatedStatus.SuccessJobDomainEvent.class, this::handleSuccessfullyTaskUpdatedStatus, TaskUpdatedStatus.SuccessJobDomainEvent.TYPE);

        return handlerBuilder.build();
    }

    private void handleSuccessfullyTaskCreated(DomainEventEnvelope<TaskCreated.SuccessJobDomainEvent> dee) {
        ObjectMapper objectMapper = new ObjectMapper();
        CreateTaskResultData resultData = objectMapper.convertValue(dee.getEvent().getEndedData(), CreateTaskResultData.class);
        
        TaskEntity taskEntity = DtoMapper.convertFrom(resultData.getTask());

        taskService.createAndAttacheFiles(
            "",
            taskEntity,
            taskEntity.getTaskAttachments()
        );
    }

    private void handleSuccessfullyTaskUpdatedStatus(DomainEventEnvelope<TaskUpdatedStatus.SuccessJobDomainEvent> dee) {
        ObjectMapper objectMapper = new ObjectMapper();
        UpdateStatusTaskResultData resultData = objectMapper.convertValue(dee.getEvent().getEndedData(), UpdateStatusTaskResultData.class);
        taskService.update(
            "", 
            resultData.getTask().getTaskId(),
            resultData.getTask().getStatus()
        );
    }
}
