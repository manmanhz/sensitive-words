package com.odianyun.util.sensi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * defaultFilter, filter启动有以后就不会再变化了。
 */
public class SensitiveFilterManager {

    private static SensitiveFilter sensitiveFilter;

    public static SensitiveFilter defaultFilter() {
        if (sensitiveFilter == null) {
            sensitiveFilter = new DefaultSensitiveFilter(new BufferedReader(
                new InputStreamReader(SensitiveFilterManager.class.getResourceAsStream("/sensi_words.txt"),
                    StandardCharsets.UTF_8)));
        }
        return sensitiveFilter;
    }

    public static SensitiveFilter filter(List<String> words) {
        if (sensitiveFilter == null) {
            sensitiveFilter = new DefaultSensitiveFilter(words);
        }
        return sensitiveFilter;
    }
}
