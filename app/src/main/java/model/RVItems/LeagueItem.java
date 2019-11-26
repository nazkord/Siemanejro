package model.RVItems;

public class LeagueItem extends BetPageItem {

    private League league;

    public League getLeagues() {
        return league;
    }

    @Override
    public int getType() {
        return TYPE_LEAGUE;
    }
}
