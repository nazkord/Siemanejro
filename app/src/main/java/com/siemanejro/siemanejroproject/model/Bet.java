package com.siemanejro.siemanejroproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bet {

    @JsonIgnore
    private Boolean isChanged = false;

    private Long id;
    private Match match;
    private User user;
    private Score userScore;
    private Integer result;

    @JsonIgnore
    public Boolean getChanged() {
        return isChanged;
    }

    @JsonIgnore
    public void setChanged(Boolean changed) {
        isChanged = changed;
    }
}
