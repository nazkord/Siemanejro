package model.RVItems;

public enum Leagues {

    GERMANY("Bundesliga", 2002L),
    ENGLAND("Premier League", 2021L),
    SPAIN("La Liga", 2014L),
    ITALY("Seria A", 2019L),
    FRANCE("La Ligue", 2015L);
    // NETHERLAND("Eredivise", );

    private final String leagueName;
    private final Long leagueId;

    Leagues(String leagueName, Long leagueId) {
        this.leagueName = leagueName;
        this.leagueId = leagueId;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public Long getLeagueId() {
        return leagueId;
    }
}
