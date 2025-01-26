package org.cresplanex.api.event.planqueryservice.mapper.dto;

import org.cresplanex.api.event.planqueryservice.entity.TaskAttachmentEntity;
import org.cresplanex.api.event.planqueryservice.entity.TaskEntity;
import org.cresplanex.api.state.common.dto.plan.FileObjectOnTaskDto;
import org.cresplanex.api.state.common.dto.plan.TaskDto;
import org.cresplanex.api.state.common.dto.plan.TaskWithAttachmentsDto;

import java.time.LocalDateTime;
import java.util.List;

public class DtoMapper {

    public static TaskDto convert(TaskEntity taskEntity) {
        return TaskDto.builder()
                .taskId(taskEntity.getTaskId())
                .teamId(taskEntity.getTeamId())
                .chargeUserId(taskEntity.getChargeUserId())
                .title(taskEntity.getTitle())
                .description(taskEntity.getDescription())
                .status(taskEntity.getStatus())
                .startDateTime(taskEntity.getStartDatetime().toString())
                .dueDateTime(taskEntity.getDueDatetime().toString())
                .build();
    }

    public static FileObjectOnTaskDto convert(TaskAttachmentEntity taskAttachmentEntity) {
        return FileObjectOnTaskDto.builder()
                .taskAttachmentId(taskAttachmentEntity.getTaskAttachmentId())
                .fileObjectId(taskAttachmentEntity.getFileObjectId())
                .build();
    }

    public static List<FileObjectOnTaskDto> convert(List<TaskAttachmentEntity> organizationUserEntities) {
        return organizationUserEntities.stream()
                .map(DtoMapper::convert)
                .toList();
    }

    public static TaskWithAttachmentsDto convert(TaskEntity taskEntity, List<TaskAttachmentEntity> organizationUserEntities) {
        return TaskWithAttachmentsDto.builder()
                .task(DtoMapper.convert(taskEntity))
                .attachments(convert(organizationUserEntities))
                .build();
    }

    public static TaskEntity convertFrom(TaskDto taskDto) {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTaskId(taskDto.getTaskId());
        taskEntity.setTeamId(taskDto.getTeamId());
        taskEntity.setChargeUserId(taskDto.getChargeUserId());
        taskEntity.setTitle(taskDto.getTitle());
        taskEntity.setDescription(taskDto.getDescription());
        taskEntity.setStatus(taskDto.getStatus());
        taskEntity.setStartDatetime(LocalDateTime.parse(taskDto.getStartDateTime()));
        taskEntity.setDueDatetime(LocalDateTime.parse(taskDto.getDueDateTime()));
        return taskEntity;
    }

    public static TaskAttachmentEntity convertFrom(FileObjectOnTaskDto fileObjectOnTaskDto) {
        TaskAttachmentEntity taskAttachmentEntity = new TaskAttachmentEntity();
        taskAttachmentEntity.setTaskAttachmentId(fileObjectOnTaskDto.getTaskAttachmentId());
        taskAttachmentEntity.setFileObjectId(fileObjectOnTaskDto.getFileObjectId());
        return taskAttachmentEntity;
    }

    public static List<TaskAttachmentEntity> convertFrom(List<FileObjectOnTaskDto> fileObjectOnTaskDtos) {
        return fileObjectOnTaskDtos.stream()
                .map(DtoMapper::convertFrom)
                .toList();
    }

    public static TaskEntity convertFrom(TaskWithAttachmentsDto taskWithAttachmentsDto) {
        TaskEntity taskEntity = convertFrom(taskWithAttachmentsDto.getTask());
        taskEntity.setTaskAttachments(convertFrom(taskWithAttachmentsDto.getAttachments()));
        return taskEntity;
    }
}
