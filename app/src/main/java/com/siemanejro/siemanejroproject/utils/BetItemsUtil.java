package com.siemanejro.siemanejroproject.utils;

import com.siemanejro.siemanejroproject.dataBinders.BetDataBinder;
import com.siemanejro.siemanejroproject.dataBinders.DataBinder;
import com.siemanejro.siemanejroproject.dataBinders.LeagueDataBinder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.siemanejro.siemanejroproject.model.Bet;
import com.siemanejro.siemanejroproject.model.FullTimeResult;
import com.siemanejro.siemanejroproject.model.League;
import com.siemanejro.siemanejroproject.model.Match;
import com.siemanejro.siemanejroproject.model.Score;

public class BetItemsUtil {

    private static Map<League, ArrayList<Bet>> createMapItems(ArrayList<Bet> bets) {
        Map<League, ArrayList<Bet>> rvItems = new HashMap<>();
        for (Bet bet :
                bets) {
            League league = League.getLeagueById(bet.getMatch().getCompetition().getId());
            rvItems.putIfAbsent(league, new ArrayList<>());
            rvItems.get(league).add(bet);
        }
        return rvItems;
    }

    public static List<DataBinder> convertToDataBinders(List<Match> matches) {
        List<Bet> bets = expandMatchesToBets(matches);
        List<DataBinder> dataBinders = new ArrayList<>();

        Map<League, ArrayList<Bet>> rvItems = createMapItems((ArrayList<Bet>) bets);
        rvItems.keySet()
                .forEach(l -> {
                    dataBinders.add(new LeagueDataBinder(l));
                    List<Bet> betList = rvItems.get(l);
                    if(betList != null) {
                        betList.forEach(b -> dataBinders.add(new BetDataBinder(b)));
                    }
                });
        return dataBinders;
    }

    private static ArrayList<Bet> expandMatchesToBets(List<Match> matches) {
        //TODO: download userScores from sqlLite
        return (ArrayList<Bet>) matches.stream()
                .map(match-> new Bet(null, match, null, new Score(null, null, new FullTimeResult(null, null, null)), null))
                .collect(Collectors.toList());
    }
}
