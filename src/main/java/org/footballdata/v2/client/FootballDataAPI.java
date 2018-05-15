package org.footballdata.v2.client;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.footballdata.v2.model.*;

import java.time.LocalDate;

@Headers({"X-Response-Control: minified"})
public interface FootballDataAPI {
    @RequestLine("GET /v2/areas")
    AreaList getAreas();

    @RequestLine("GET /v2/areas?areas={filters}")
    AreaList getAreas(@Param("filters") String filters); // TODO make this support an array of integers

    @RequestLine("GET /v2/areas/{area}")
    Area getArea(@Param("area") int area);

    @RequestLine("GET /v2/competitions")
    CompetitionList getCompetitions();

    @RequestLine("GET /v2/competitions?areas={filters}")
    CompetitionList getCompetitions(@Param("filters") String filters); // TODO make this support an array of integers

    @RequestLine("GET /v2/competitions/{competition}")
    Competition getCompetition(@Param("competition") int competition);

    @RequestLine("GET /v2/competitions/{competition}/matches")
    MatchList getCompetitionMatches(@Param("competition") int competition);

    @RequestLine("GET /v2/competitions/{competition}/matches?stage={stages}&status={status}&matchday={matchday}")
    MatchList getCompetitionMatchesWithParams(@Param("competition") int competition,
                                              @Param("stages") String stages,
                                              @Param("status") String status,
                                              @Param("matchday") int matchday);

    @RequestLine("GET /v2/competitions/{competition}/teams")
    TeamList getCompetitionTeams(@Param("competition") int competition);

    @RequestLine("GET /v2/teams/{team}")
    Team getTeam(@Param("team") int team);

    @RequestLine("GET /v2/matches")
    MatchList getMatches();

    @RequestLine("GET /v2/matches?dateFrom={dateFrom}&dateTo={dateTo}")
    MatchList getMatchesBetween(@Param("dateFrom") LocalDate dateFrom, @Param("dateTo") LocalDate dateTo);

    @RequestLine("GET /v2/matches/{matchId}")
    Match getMatch(@Param("matchId") int matchId);
}
