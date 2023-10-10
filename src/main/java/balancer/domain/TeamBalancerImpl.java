package balancer.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TeamBalancerImpl implements TeamBalancer {

    private final List<Member> members;
    private final int numOfTeams;

    public TeamBalancerImpl(List<Member> members, int numOfTeams) {
        this.members = members;
        this.numOfTeams = numOfTeams;
    }

    @Override
    public List<Team> divideIntoTeams() throws IllegalArgumentException {
        validateTeamBalance();
        Collections.sort(members, (p1, p2) -> Double.compare(p2.getRate(), p1.getRate()));

        final List<Team> teamResults = initializeTeamResults();


        for (final Member member : members) {
            final Team teamWithLowestSumRate =
                    teamResults
                            .stream()
                            .min(Comparator.comparingDouble(TeamBalancerImpl::calculateSumTeamRate))
                            .orElse(null);

            if (teamWithLowestSumRate != null) {
                teamWithLowestSumRate.getMembers().add(member);
            }
        }
        return teamResults;
    }

    private List<Team> initializeTeamResults() {
        final List<Team> teamResults = new ArrayList<>();
        for (int i = 0; i < numOfTeams; i++){
            teamResults.add(new Team(new ArrayList<>()));
        }
        return teamResults;
    }

    private void validateTeamBalance() {
        if (members.isEmpty() || members.size() % numOfTeams != 0) {
            throw new IllegalArgumentException("Teams cannot be formed. You have provided an incorrect value.");
        }
    }

    private static double calculateSumTeamRate(final Team team) {
        if (team.getMembers().size() == 0) {
            return 0.0;
        }
        final double sumTeamRate = team.getMembers().stream().mapToDouble(Member::getRate).sum();
        return sumTeamRate;
    }
}
