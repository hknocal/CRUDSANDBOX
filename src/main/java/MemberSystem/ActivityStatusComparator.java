package MemberSystem;

import java.util.Comparator;

public class ActivityStatusComparator implements Comparator <Member> {
    @Override
    public int compare(Member o1, Member o2) {
        return Boolean.compare(o1.getActivityStatus(),o2.getActivityStatus());
    }
}
