package com.resourse;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ActivityDTO {
    private String activityType;
    private String description;
    private String timestamp;
}
