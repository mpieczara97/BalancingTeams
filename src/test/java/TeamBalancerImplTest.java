import balancer.domain.Member;
import balancer.domain.Team;
import balancer.domain.TeamBalancer;
import balancer.domain.TeamBalancerImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TeamBalancerImplTest {

    @Test
    void shouldDivideIntoThreeTeams() {
        //given
        final Member member1 = new Member("Johny", 8);
        final Member member2 = new Member("Robbie", 5);
        final Member member3 =  new Member("Juliet", 3);
        final Member member4 = new Member("Scarlet", 5.5);
        final Member member5 = new Member("Jude", 9);
        final Member member6 = new Member("Deborah", 6);
        final List<Member> members = Arrays.asList(member1, member2, member3, member4, member5, member6);

        final Team team1 = new Team(Arrays.asList(member5, member3));
        final Team team2 = new Team(Arrays.asList(member1, member2));
        final Team team3 = new Team(Arrays.asList(member6, member4));

        //when
        final TeamBalancer teamBalancer = new TeamBalancerImpl(members, 3);
        final List<Team> results = teamBalancer.divideIntoTeams();

        //then
        Assertions.assertEquals(results.size(), 3);
        Assertions.assertEquals(team1.getMembers(), results.get(0).getMembers());
        Assertions.assertEquals(team2.getMembers(), results.get(1).getMembers());
        Assertions.assertEquals(team3.getMembers(), results.get(2).getMembers());
    }

    @Test
    void shouldDivideIntoTwoTeams() {
        //given
        final Member member1 = new Member("Johny", 7);
        final Member member2 = new Member("Robbie", 5);
        final Member member3 =  new Member("Juliet", 3);
        final Member member4 = new Member("Scarlet", 4);
        final Member member5 = new Member("Jude", 9);
        final Member member6 = new Member("Deborah", 6);
        final List<Member> members = Arrays.asList(member1, member2, member3, member4, member5, member6);

        final Team team1 = new Team(Arrays.asList(member5, member2, member3));
        final Team team2 = new Team(Arrays.asList(member1, member6, member4));

        //when
        final TeamBalancer teamBalancer = new TeamBalancerImpl(members, 2);
        final List<Team> results = teamBalancer.divideIntoTeams();

        //then
        Assertions.assertEquals(results.size(), 2);
        Assertions.assertEquals(team1.getMembers(), results.get(0).getMembers());
        Assertions.assertEquals(team2.getMembers(), results.get(1).getMembers());
    }

    @Test
    void shouldThrowExceptionWhenNotPossibleToCreateTeams() {
        //given
        final Member member1 = new Member("Johny", 7);
        final Member member2 = new Member("Robbie", 5);
        final Member member3 =  new Member("Juliet", 3);
        final Member member4 = new Member("Scarlet", 4);
        final Member member5 = new Member("Jude", 9);
        final Member member6 = new Member("Deborah", 6);
        final List<Member> members = Arrays.asList(member1, member2, member3, member4, member5, member6);

        //when
        final TeamBalancer teamBalancer = new TeamBalancerImpl(members, 4);

        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> teamBalancer.divideIntoTeams());
    }

    @Test
    void shouldThrowExceptionWhenMembersNotProvided() {
        //given
        final List<Member> members = new ArrayList<>();

        //when
        final TeamBalancer teamBalancer = new TeamBalancerImpl(members, 4);

        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> teamBalancer.divideIntoTeams());
    }
}