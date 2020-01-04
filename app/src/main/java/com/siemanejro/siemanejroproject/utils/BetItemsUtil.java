package com.siemanejro.siemanejroproject.utils;

import android.content.Context;

import com.siemanejro.siemanejroproject.dataBinders.BetDataBinder;
import com.siemanejro.siemanejroproject.dataBinders.DataBinder;
import com.siemanejro.siemanejroproject.dataBinders.LeagueDataBinder;
import com.siemanejro.siemanejroproject.model.Bet;
import com.siemanejro.siemanejroproject.model.FullTimeResult;
import com.siemanejro.siemanejroproject.model.League;
import com.siemanejro.siemanejroproject.model.Match;
import com.siemanejro.siemanejroproject.model.RoomBet;
import com.siemanejro.siemanejroproject.model.Score;
import com.siemanejro.siemanejroproject.utils.roomUtil.RoomService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    public static List<DataBinder> convertToDataBindersByDate(List<Match> matches, String date, Context context) {
        List<Bet> bets = expandMatchesToBetsByDate(matches, date, context);
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

    private static ArrayList<Bet> expandMatchesToBetsByDate(List<Match> matches, String date, Context context) {
        Optional<List<RoomBet>> roomBets = Optional.ofNullable(RoomService.getBetsByDate(date, context));
        Map<Long, RoomBet> roomBetMap = createMapOfRoomBets(
                roomBets.orElse(Collections.emptyList())
        );
        return (ArrayList<Bet>) matches.stream()
                .map(match -> {
                    Optional<RoomBet> roomBet = Optional.ofNullable(roomBetMap.get(match.getId()));
                    return new Bet(false, null, match, null,
                            roomBet.map(RoomBet::getUserScore).orElse(new Score(null, null, new FullTimeResult(null, null, null))),
                            roomBet.map(RoomBet::getResult).orElse(null));
                })
                .collect(Collectors.toList());
    }

    private static Map<Long, RoomBet> createMapOfRoomBets(List<RoomBet> roomBets) {
        return roomBets.stream().collect(
                Collectors.toMap(RoomBet::getMatchId, Function.identity()));
    }
}
