package utils;

import org.junit.Test;

import model.FullTimeResult;
import model.Score;

import static org.junit.Assert.*;

public class ResultUtilTest {

    @Test
    public void testResult3() {
        Score score1 = new Score(null, "HOME_TEAM", new FullTimeResult(null, 2, 1));
        Score score2 = new Score(null, "HOME_TEAM", new FullTimeResult(null, 3, 2));
        assertEquals(Integer.valueOf(3), ResultUtil.calculateResult(score1, score2));
    }

    @Test
    public void testResult2() {
        Score score1 = new Score(null, "HOME_TEAM", new FullTimeResult(null, 2, 1));
        Score score2 = new Score(null, "HOME_TEAM", new FullTimeResult(null, 3, 0));
        assertEquals(Integer.valueOf(2), ResultUtil.calculateResult(score1, score2));
    }

    @Test
    public void testResult0_1() {
        Score score1 = new Score(null, "HOME_TEAM", new FullTimeResult(null, 2, 1));
        Score score2 = new Score(null, "AWAY_TEAM", new FullTimeResult(null, 0, 1));
        assertEquals(Integer.valueOf(0), ResultUtil.calculateResult(score1, score2));
    }

    @Test
    public void testResult0_2() {
        Score score1 = new Score(null, "AWAY_TEAM", new FullTimeResult(null, 0, 1));
        Score score2 = new Score(null, "DRAW", new FullTimeResult(null, 0, 0));
        assertEquals(Integer.valueOf(0), ResultUtil.calculateResult(score1, score2));
    }

    @Test
    public void testResult3Draw() {
        Score score1 = new Score(null, "DRAW", new FullTimeResult(null, 1, 1));
        Score score2 = new Score(null, "DRAW", new FullTimeResult(null, 0, 0));
        assertEquals(Integer.valueOf(3), ResultUtil.calculateResult(score1, score2));
    }

    @Test
    public void testResult4Draw() {
        Score score1 = new Score(null, "DRAW", new FullTimeResult(null, 0, 0));
        Score score2 = new Score(null, "DRAW", new FullTimeResult(null, 0, 0));
        assertEquals(Integer.valueOf(4), ResultUtil.calculateResult(score1, score2));
    }

    @Test
    public void testResult4() {
        Score score1 = new Score(null, "AWAY_TEAM", new FullTimeResult(null, 0, 4));
        Score score2 = new Score(null, "AWAY_TEAM", new FullTimeResult(null, 0, 4));
        assertEquals(Integer.valueOf(4), ResultUtil.calculateResult(score1, score2));
    }

//    @Test
//    public void testResultWithoutScore() {
//        Score score1 = new Score(null, null, null);
//        Score score2 = new Score(null, "AWAY_TEAM", new FullTimeResult(null, 0, 4));
//        assertEquals(Integer.valueOf(0), ResultUtil.calculateResult(score1, score2));
//    }
}