package com.example.ScheduleMate.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicePackageDto {

    @JsonProperty("packageId")
    private Long packageId;

    @JsonProperty("packageName")
    private String PackageName;

}