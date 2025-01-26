package org.cresplanex.api.event.planqueryservice.filter.task;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TeamFilter {

    private boolean isValid;
    private List<String> teamIds;
}
