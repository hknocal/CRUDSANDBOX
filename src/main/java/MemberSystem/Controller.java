package MemberSystem;

import java.util.ArrayList;

public class Controller {
    private Database db = new Database ();
    private FileHandler fh = new FileHandler();

    public void addMember (String name, String surname, int age, boolean activityStatus) {
        db.addMember(name, surname, age, activityStatus);
    }

    public void deleteMember(int number) {
        db.memberDB.remove(number);
    }

    public ArrayList <Member> getMemberDB () {
        return db.getMemberDB();
    }

    public void saveToDB () {
        fh.saveToDB(db.getMemberDB());
    }

    public void loadDB () {
        db.setMemberDB(fh.loadDB());
    }

    public void sortByName () {
        db.sortByName();
    }

    public void sortBySurname () {
        db.sortBySurname();
    }

    public void sortByAge () {
        db.sortByAge();
    }

    public void sortByActivityStatus() {
        db.sortByActivityStatus();
    }

}
