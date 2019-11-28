package model;

import com.siemanejro.siemanejroproject.R;

import java.util.stream.Stream;

public enum League {

    GERMANY(R.drawable.ic_bundesliga, "Bundesliga", 2002L),
    ENGLAND(R.drawable.ic_pl1, "Premier League", 2021L),
    SPAIN(R.drawable.ic_laliga1, "La Liga", 2014L),
    ITALY(R.drawable.ic_serie_a, "Seria A", 2019L),
    FRANCE(R.drawable.ic_ligue_1, "La Ligue", 2015L);
    // NETHERLAND("Eredivise", );

    private final Integer leagueIcon;
    private final String leagueName;
    private final Long leagueId;

    League(Integer leagueIcon, String leagueName, Long leagueId) {
        this.leagueIcon = leagueIcon;
        this.leagueName = leagueName;
        this.leagueId = leagueId;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public Long getLeagueId() {
        return leagueId;
    }

    public Integer getLeagueIcon() {
        return leagueIcon;
    }

    //class's method, not instance's, so declare as static
    public static League getLeagueById(Long id) {
        return Stream.of(League.values())
                .filter(l -> l.getLeagueId().equals(id))
                .findFirst().get();
        //TODO:
//                .orElseThrow(() -> new RuntimeException("There is no such league"));
    }
}