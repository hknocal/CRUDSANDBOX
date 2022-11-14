package MemberSystem;

import java.util.Comparator;

public class AgeComparator implements Comparator<Member> {
    @Override
    public int compare(Member o1, Member o2){
        return Integer.compare(o1.getAge(),o2.getAge());
    }
}
