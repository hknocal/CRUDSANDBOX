package MemberSystem;

import java.util.Comparator;

public class SurnameComparator implements Comparator<Member> {
    @Override
    public int compare(Member o1, Member o2) {
        return o1.getSurname().compareTo(o2.getSurname());
    }
}
