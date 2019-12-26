package com.siemanejro.siemanejroproject.utils;

import com.siemanejro.siemanejroproject.model.Match;

import java.util.List;
import java.util.stream.Collectors;

public class MatchItemsUtil {

    public static List<Match> filterByDate(List<Match> matches, String date) {
        return matches.stream()
                .filter(Match -> Match.getUtcDate().substring(0,10).equals(date))
                .collect(Collectors.toList());
    }
}
