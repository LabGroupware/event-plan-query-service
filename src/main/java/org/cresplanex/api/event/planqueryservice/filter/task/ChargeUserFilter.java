package org.cresplanex.api.event.planqueryservice.filter.task;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ChargeUserFilter {

    private boolean isValid;
    private List<String> chargeUserIds;
}
