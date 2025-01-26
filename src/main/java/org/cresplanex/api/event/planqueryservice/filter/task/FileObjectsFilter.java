package org.cresplanex.api.event.planqueryservice.filter.task;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FileObjectsFilter {

    private boolean isValid;
    private boolean any;
    private List<String> fileObjectIds;
}
