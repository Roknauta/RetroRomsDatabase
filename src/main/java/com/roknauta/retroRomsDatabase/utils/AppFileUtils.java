package com.roknauta.retroRomsDatabase.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AppFileUtils {

    public static final String ACCEPTED_REGIONS =
        "USA,Brazil,Europe,World,Portugal,Canada,Australia,United Kingdom,New Zealand,Mexico,Argentina,Latin America,Spain,France,Italy,Germany,Greece,Sweden,Austria,Romania,Netherlands,Finland,Denmark,Hungary,Scandinavia,Japan,Hong Kong,Asia,China,Korea,Taiwan,Russia";

    public static List<String> getRegionsOrder() {
        return Arrays.asList(ACCEPTED_REGIONS.split(","));
    }

    public static boolean isValid(String fullName) {
        List<String> invalidWords = List.of("Beta", "Proto", "Sample");
        List<String> keywords = getKeywords(fullName);
        for (String invalidWord : invalidWords) {
            for (String keyword : keywords) {
                if (StringUtils.containsIgnoreCase(keyword, invalidWord)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static String getRegions(String fullName) {
        List<String> regions = Arrays.stream(ACCEPTED_REGIONS.split(",")).toList();
        List<String> subStrings = getKeywords(fullName);
        String region = null;
        for (String subString : subStrings) {
            if (Arrays.stream(subString.split(",")).anyMatch(regions::contains)) {
                region = subString;
                break;
            }
        }
        return region;
    }

    public static List<String> getRegionsList(String fullName) {
        String regions = getRegions(fullName);
        return regions==null?new ArrayList<>():Arrays.stream(regions.split(",")).map(String::trim).collect(Collectors.toList());
    }

    public static List<String> getKeywords(String fullName) {
        String[] subStrings = StringUtils.substringsBetween(fullName, "(", ")");
        return subStrings == null ? Collections.emptyList() : Arrays.stream(subStrings).toList();
    }

    public static String getBaseName(String fullName) {
        String regions = getRegions(fullName);
        return StringUtils.substringBefore(fullName, "(");
    }

}
