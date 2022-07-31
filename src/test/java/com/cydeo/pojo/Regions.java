package com.cydeo.pojo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Setter
@Getter
@ToString

@JsonIgnoreProperties(ignoreUnknown = true)//this comes from Jackson
public class Regions {
    private List<Region> items;
    private int count;

}
