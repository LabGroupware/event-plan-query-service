package org.cresplanex.api.event.planqueryservice.filter.task;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StartDatetimeFilter {

    private boolean earlierInfinity;
    private boolean laterInfinity;
    private String earlierThan;
    private String laterThan;
}
