package balancer.domain;

import balancer.util.StandardDeviationCalculator;

import java.util.List;

public class TeamPrinterImpl implements TeamPrinter {
    private List<Team> teams;
    private static double totalTeamsRate;

    public TeamPrinterImpl(List<Team> teams) {
        this.teams = teams;
    }

    @Override
    public void printTeams() {
        int teamNumber = 1;
        for (Team team : teams) {
            double averageRate = team.calculateAverageRate();
            totalTeamsRate = totalTeamsRate + averageRate;
            System.out.print("Team no " + teamNumber + " has " + team.getMembers().size() + " players (");
            for (int i = 0; i < team.getMembers().size(); i++) {
                Member member = team.getMembers().get(i);
                System.out.print(member.getName());
                if (i < team.getMembers().size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.print("). Average rate: " + averageRate + "  ");
            teamNumber++;
            System.out.println();
        }

        double standardDeviation = StandardDeviationCalculator.calculateStandardDeviation(teams, totalTeamsRate);
        System.out.println("Teams rate standard deviation: " + standardDeviation);
    }
}
