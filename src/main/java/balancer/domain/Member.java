package balancer.domain;

import java.util.Objects;

public class Member {

    private String name;
    private double rate;

    public Member(String name, double rate) {
        this.name = name;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Double.compare(member.rate, rate) == 0 && name.equals(member.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rate);
    }
}