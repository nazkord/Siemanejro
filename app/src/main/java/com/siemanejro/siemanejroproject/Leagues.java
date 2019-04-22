package com.siemanejro.siemanejroproject;

import java.io.Serializable;

public enum Leagues {
    GERMANY("Bundesliga", "2002"),
    ENGLAND("Premier League", "2021"),
    SPAIN("La Liga", "2014"),
    ITALY("Seria A", "2019"),
    FRANCE("La Ligue", "2015");
    // NETHERLAND("Eredivise", );

    private final String uiLeagueName;
    private final String leagueId;

    private Leagues(String uiLeagueName, String leagueId) {
        this.uiLeagueName = uiLeagueName;
        this.leagueId = leagueId;
    }

    public String getLeagueId() {
        return leagueId;
    }

    public String getUiLeagueName() {
        return uiLeagueName;
    }
}
