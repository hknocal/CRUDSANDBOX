package MemberSystem;

import java.util.ArrayList;
import java.util.Collections;

public class Database {
    ArrayList<Member> memberDB = new ArrayList<>();

    public Database () {
    }

    public ArrayList<Member> getMemberDB() {
        return memberDB;
    }

    public void setMemberDB(ArrayList <Member> memberDB) {
        this.memberDB = memberDB;
    }

    public void addMember(String name, String surname, int age, boolean activityStatus) {
        memberDB.add(new Member(name, surname, age, activityStatus));
    }

    public void sortByName () {
        Collections.sort(getMemberDB(), new NameComparator());
    }

    public void sortBySurname () {
        Collections.sort(getMemberDB(), new SurnameComparator());
    }

    public void sortByAge () {
        Collections.sort(getMemberDB(), new AgeComparator());
    }

    public void sortByActivityStatus () {
        Collections.sort(getMemberDB(), new ActivityStatusComparator());
    }
}
