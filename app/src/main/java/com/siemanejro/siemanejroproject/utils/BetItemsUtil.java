package com.siemanejro.siemanejroproject.utils;

import android.content.Context;

import com.siemanejro.siemanejroproject.dataBinders.BetDataBinder;
import com.siemanejro.siemanejroproject.dataBinders.DataBinder;
import com.siemanejro.siemanejroproject.dataBinders.LeagueDataBinder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.siemanejro.siemanejroproject.model.Bet;
import com.siemanejro.siemanejroproject.model.League;
import com.siemanejro.siemanejroproject.model.Match;
import com.siemanejro.siemanejroproject.model.RoomBet;
import com.siemanejro.siemanejroproject.utils.roomUtil.RoomService;

public class BetItemsUtil {

    private static Map<League, List<Bet>> createMapItems(List<Bet> bets) {
        Map<League, List<Bet>> rvItems = new HashMap<>();
        for (Bet bet :
                bets) {
            League league = League.getLeagueById(bet.getMatch().getCompetition().getId());
            rvItems.putIfAbsent(league, new ArrayList<>());
            rvItems.get(league).add(bet);
        }
        return rvItems;
    }

    public static List<DataBinder> convertToDataBinders(List<Match> matches, Context context) {
        List<Bet> bets = expandMatchesToBets(matches, context);
        List<DataBinder> dataBinders = new ArrayList<>();

        Map<League, List<Bet>> rvItems = createMapItems(bets);
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

    private static ArrayList<Bet> expandMatchesToBets(List<Match> matches, Context context) {
        return (ArrayList<Bet>) matches.stream()
                .map(match-> {
                    RoomBet bet = RoomService.getBetByMatchId(match.getId(), context);
                    return new Bet(false, null, match, null, bet.getUserScore(), bet.getResult());
                })
                .collect(Collectors.toList());
    }
}
