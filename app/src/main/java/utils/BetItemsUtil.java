package utils;

import android.widget.ArrayAdapter;

import com.siemanejro.siemanejroproject.adapters.BetDataBinder;
import com.siemanejro.siemanejroproject.adapters.DataBinder;
import com.siemanejro.siemanejroproject.adapters.LeagueDataBinder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import model.Bet;
import model.RVItems.League;

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

    public static List<DataBinder> convertToDataBinders(ArrayList<Bet> bets) {
        List<DataBinder> dataBinders = new ArrayList<>();

        Map<League, ArrayList<Bet>> rvItems = createMapItems(bets);
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
}
