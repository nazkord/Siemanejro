package model.RVItems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.RVItems.RVBetPageItem;

public class LeagueItem extends RVBetPageItem {

    private Leagues leagues;

    public Leagues getLeagues() {
        return leagues;
    }

    @Override
    public int getType() {
        return TYPE_LEAGUE;
    }
}
