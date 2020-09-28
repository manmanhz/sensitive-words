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

    public static void init() {
        if (sensitiveFilter == null) {
            sensitiveFilter = new DefaultSensitiveFilter(new BufferedReader(
                new InputStreamReader(SensitiveFilterManager.class.getResourceAsStream("/sensi_words.txt"),
                    StandardCharsets.UTF_8)));
        }
    }

    public static void init(List<String> words) {
        if (sensitiveFilter == null) {
            sensitiveFilter = new DefaultSensitiveFilter(words);
        }
    }

    public static void init(String url) {
        if (sensitiveFilter == null) {
            sensitiveFilter = new DefaultSensitiveFilter(new BufferedReader(
                new InputStreamReader(SensitiveFilterManager.class.getResourceAsStream(url),
                    StandardCharsets.UTF_8)));
        }
    }

    public static SensitiveFilter filter() {
        if (sensitiveFilter == null) {
            sensitiveFilter = new DefaultSensitiveFilter(new BufferedReader(
                new InputStreamReader(SensitiveFilterManager.class.getResourceAsStream("/sensi_words.txt"),
                    StandardCharsets.UTF_8)));
        }
        return sensitiveFilter;
    }
}
