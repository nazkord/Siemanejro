package model.RVItems;

import model.Bet;

public class RVBetItem extends RVBetPageItem {
    private Bet bet;

    public RVBetItem(Bet bet) {
        this.bet = bet;
    }

    public Bet getBet() {
        return bet;
    }

    @Override
    public int getType() {
        return TYPE_BET;
    }
}
