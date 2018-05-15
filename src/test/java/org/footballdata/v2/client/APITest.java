package org.footballdata.v2.client;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.slf4j.Slf4jLogger;
import org.footballdata.v2.model.MatchStage;
import org.footballdata.v2.model.MatchStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDate;
import java.time.Month;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class APITest {
    @Test
    public void testApi() {
        FootballDataAPI api = Feign.builder()
                .decoder(new JacksonDecoder())
                .logger(new Slf4jLogger(APITest.class))
                .logLevel(feign.Logger.Level.BASIC)
                .target(FootballDataAPI.class, "http://localhost:8080");

        System.out.println(api.getAreas().toString());
        System.out.println(api.getAreas("164,2").toString());
        System.out.println(api.getArea(164).toString());
        System.out.println(api.getCompetitions().toString());
        // System.out.println(api.getCompetitions("1,2,3").toString()) --> broken
        System.out.println(api.getCompetition(1).toString());
        System.out.println(api.getCompetitionMatches(1).toString());
        System.out.println(api.getCompetitionMatchesWithParams(1,
                MatchStage.REGULAR_SEASON.toString(),
                MatchStatus.FINISHED.toString(),
                1).toString());
        System.out.println(api.getCompetitionTeams(1).toString());
        System.out.println(api.getTeam(1));

        System.out.println(api.getMatches().toString());
        System.out.println(api.getMatchesBetween(LocalDate.of(2018, Month.APRIL, 15), LocalDate.of(2018, Month.APRIL, 15)).toString());
        System.out.println(api.getMatch(313).toString());
    }
}
