package com.example.diffbookdepthstreams.enums;

import com.example.diffbookdepthstreams.exception.BusinessException;

import java.util.Arrays;

public enum MillisecondsTypes {
    ONE_HUNDRED("100"),
    TWO_HUNDRED_FIFTY("250"),
    FIVE_HUNDRED("500");

    private final String millisecond;

    MillisecondsTypes(String millisecond) {
        this.millisecond = millisecond;
    }

    public String getMillisecond() {
        return millisecond;
    }

    public static void searchMilliseconds(String millisecond) {
        Arrays.stream(values())
                .filter(data -> data.millisecond.equalsIgnoreCase(millisecond))
                .findFirst()
                .orElseThrow(() -> new BusinessException("Millisecond is incorrect!", BusinessStatus.INCORRECT_MILLISECOND));
    }
}
