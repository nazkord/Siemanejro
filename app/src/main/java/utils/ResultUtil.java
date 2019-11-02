package utils;

import model.Score;

public class ResultUtil {

    public static Integer getResult(Score score1, Score score2) {
        if(score1.getWinner().equals(score2.getWinner())) {
            if(score1.getFullTime().equals(score2.getFullTime()))
                return 4;
            if(score1.getFullTime().getDifference().equals(score2.getFullTime().getDifference())) {
                return 3;
            }
            return 2;
        } else {
            return 0;
        }
    }
}
