package balancer;

import balancer.domain.*;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        final Member member1 = new Member("Johny", 8);
        final Member member2 = new Member("Robbie", 5);
        final Member member3 = new Member("Juliet", 3);
        final Member member4 = new Member("Scarlet", 5);
        final Member member5 = new Member("Jude", 9);
        final Member member6 = new Member("Deborah", 6);
        final List<Member> members = Arrays.asList(member1, member2, member3, member4, member5, member6);

        final TeamBalancer teamBalancer = new TeamBalancerImpl(members, 3);
        final List<Team> teamResults = teamBalancer.divideIntoTeams();
        final TeamPrinter teamPrinter = new TeamPrinterImpl(teamResults);
        teamPrinter.printTeams();
    }
}