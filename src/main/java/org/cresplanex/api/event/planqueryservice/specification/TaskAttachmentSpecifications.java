package org.cresplanex.api.event.planqueryservice.specification;

import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;

import org.cresplanex.api.event.planqueryservice.entity.TaskAttachmentEntity;
import org.hibernate.type.descriptor.java.StringJavaType;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TaskAttachmentSpecifications {

    public static Specification<org.cresplanex.api.event.planqueryservice.entity.TaskAttachmentEntity> fetchTask() {
        return (root, query, criteriaBuilder) -> {
            if (query == null) {
                return null;
            }
            if (Long.class != query.getResultType()) {
                root.fetch("task", JoinType.LEFT);
                query.distinct(true);
                return null;
            }

            return null;
        };
    }

    public static Specification<TaskAttachmentEntity> whereTaskId(String taskId) {
        String newTaskId = new StringJavaType().wrap(taskId, null);

        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (taskId != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("taskId"), newTaskId));
            }
            return predicate;
        };
    }

    public static Specification<TaskAttachmentEntity> whereTaskIds(Iterable<String> taskIds) {
        List<String> taskIdList = new ArrayList<>();
        taskIds.forEach(taskId -> {
            taskIdList.add(new StringJavaType().wrap(taskId, null));
        });

        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            predicate = criteriaBuilder.and(predicate, root.get("taskId").in(taskIdList));
            return predicate;
        };
    }

    public static Specification<TaskAttachmentEntity> whereFileObjectId(String fileObjectId) {
        String newFileObjectId = new StringJavaType().wrap(fileObjectId, null);

        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            if (fileObjectId != null) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("fileObjectId"), newFileObjectId));
            }
            return predicate;
        };
    }

    public static Specification<TaskAttachmentEntity> whereFileObjectIds(Iterable<String> fileObjectIds) {
        List<String> fileObjectIdList = new ArrayList<>();
        fileObjectIds.forEach(fileObjectId -> {
            fileObjectIdList.add(new StringJavaType().wrap(fileObjectId, null));
        });

        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            predicate = criteriaBuilder.and(predicate, root.get("fileObjectId").in(fileObjectIdList));
            return predicate;
        };
    }
}
