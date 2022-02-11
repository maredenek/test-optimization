package org.template.configuration;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum Browser {

    CHROME("chrome"),
    FIREFOX("firefox"),
    IE("ie"),
    SAFARI("safari");

    private final String name;

    public static Browser fromText(String lookUpName) {
        return Arrays.stream(values())
                .filter(entry -> entry.name.equals(lookUpName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
