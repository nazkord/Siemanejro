package model;

public enum Leagues {
    GERMANY("Bundesliga", 2002L),
    ENGLAND("Premier League", 2021L),
    SPAIN("La Liga", 2014L),
    ITALY("Seria A", 2019L),
    FRANCE("La Ligue", 2015L);
    // NETHERLAND("Eredivise", );

    private final String uiLeagueName;
    private final Long leagueId;

    private Leagues(String uiLeagueName, Long leagueId) {
        this.uiLeagueName = uiLeagueName;
        this.leagueId = leagueId;
    }

    public Long getLeagueId() {
        return leagueId;
    }

    public String getUiLeagueName() {
        return uiLeagueName;
    }
}
