package balancer.util;

import balancer.domain.Team;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

public class StandardDeviationCalculator {

    private StandardDeviationCalculator() {
    }

    public static double calculateStandardDeviation(List<Team> teams, double totalTeamsRate) {
        double averageRate = totalTeamsRate / teams.size();

        double squaredDifferencesSum = 0;
        for (Team team : teams) {
            double teamRate = team.calculateAverageRate();
            double difference = teamRate - averageRate;
            squaredDifferencesSum += Math.pow(difference, 2);
        }

        double standardDeviation = Math.sqrt(squaredDifferencesSum / teams.size());

        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat df = new DecimalFormat("#.##", symbols);
        return Double.parseDouble(df.format(standardDeviation));
    }
}
