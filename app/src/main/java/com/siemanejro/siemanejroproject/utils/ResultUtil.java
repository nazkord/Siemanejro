package com.siemanejro.siemanejroproject.utils;

import com.siemanejro.siemanejroproject.model.FullTimeResult;
import com.siemanejro.siemanejroproject.model.Score;

public class ResultUtil {

    public static Integer calculateResult(Score score1, Score score2) {
        if(score1.getWinner().equals(score2.getWinner())) {
            if(score1.getFullTime().equals(score2.getFullTime()))
                return 4;
            if(getDifference(score1.getFullTime()).equals(getDifference(score2.getFullTime()))) {
                return 3;
            }
            return 2;
        } else {
            return 0;
        }
    }

    private static Integer getDifference(FullTimeResult fullTimeResult) {
        return fullTimeResult.getHomeTeam() - fullTimeResult.getAwayTeam();
    }
}
