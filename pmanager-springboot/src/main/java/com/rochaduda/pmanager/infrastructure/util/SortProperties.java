package com.rochaduda.pmanager.infrastructure.util;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

@Getter
public class SortProperties {

    private final List<String> sortPropertieList;
    private final List<String> ALLOWED_PROPERTIES = List.of(
        "title",
        "status",
        "numberOfDays"
    );

    public SortProperties(String commaSeparatedString){
        sortPropertieList = Arrays
            .stream(commaSeparatedString.split(","))
            .filter(ALLOWED_PROPERTIES::contains)
            .toList();
    }

}
