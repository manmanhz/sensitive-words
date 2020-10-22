package com.odianyun.util.sensi;

import java.util.List;
import java.util.Set;

public interface SensitiveFilter {

    Set<String> findWords(String sentence);

    String filter(String sentence, char replace);

    boolean put(String word);

    void rebuild(List<String> words);
}
