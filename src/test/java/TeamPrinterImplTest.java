import balancer.domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import  org.junit.Rule;
import org.junit.contrib.java.lang.system.SystemOutRule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TeamPrinterImplTest {
    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Test
    void shouldPrintTeamsAndStandardDeviation() {
        //given
        final Member member1 = new Member("Johny", 7);
        final Member member2 = new Member("Robbie", 5);
        final Member member3 = new Member("Juliet", 3);
        final Member member4 = new Member("Scarlet", 4);

        final Team team1 = new Team(Arrays.asList(member1, member3));
        final Team team2 = new Team(Arrays.asList(member2, member4));

        List<Team> teams = Arrays.asList(team1, team2);

        //when
        final TeamPrinterImpl teamPrinter = new TeamPrinterImpl(teams);
        teamPrinter.printTeams();

        //then
        String expectedOutput = "Team no 1 has 2 players (Johny, Juliet). Average rate: 5.0  \n" +
                "Team no 2 has 2 players (Robbie, Scarlet). Average rate: 4.5  \n" +
                "Teams rate standard deviation: 0.25\n";
        Assertions.assertEquals(expectedOutput, systemOutRule.getLog());
    }
}
