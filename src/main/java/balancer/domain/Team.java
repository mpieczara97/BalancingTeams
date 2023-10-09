package balancer.domain;

import java.util.List;
import java.util.Objects;

public class Team {

    private List<Member> members;

    public Team(List<Member> members) {
        this.members = members;
    }

    public List<Member> getMembers() {
        return members;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(members, team.members);
    }

    @Override
    public int hashCode() {
        return Objects.hash(members);
    }

    public double calculateAverageRate() {
        return members.stream().mapToDouble(Member::getRate).sum() / members.size();
    }
}