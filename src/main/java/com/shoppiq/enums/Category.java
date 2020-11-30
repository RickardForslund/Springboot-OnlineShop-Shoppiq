package com.shoppiq.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.NoArgsConstructor;

@JsonDeserialize(as = Category.class)
@NoArgsConstructor
public enum Category {

    CLOTHES,TOYS,ELECTRONICS;

    Category(String string) {
    }
}
