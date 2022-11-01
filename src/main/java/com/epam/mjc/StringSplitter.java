package com.epam.mjc;

import java.util.*;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source     source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        List<String> splitSource = List.of(source);
        List<String> updatedSource = new ArrayList<>();

        for (String d : delimiters) {
            for (String s : splitSource) {
                String[] split = s.split(d);
                Arrays.stream(split)
                        .filter(item -> !item.equals(""))
                        .forEach(updatedSource::add);
            }
            splitSource = updatedSource;
            updatedSource = new ArrayList<>();
        }
        return splitSource;
    }
}
