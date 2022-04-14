package com.test.task.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserSectorDto {
    private Long id;
    private String username;
    private Boolean agreedToTerms;
    @JsonProperty("sectors")
    private Set<Long> sectorIds;
}
