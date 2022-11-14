package MemberSystem;

public class Member {
    private String name;
    private String surname;
    private int age;
    private boolean activityStatus;

    public Member(String name, String surname, int age, boolean activityStatus) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.activityStatus = activityStatus;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public boolean getActivityStatus() {
        return activityStatus;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setActivityStatus(boolean activityStatus) {
        this.activityStatus = activityStatus;
    }

    public String toString() {
        return "Name: " + name + "," + " Surname: " + surname  + "," + " Age: " + age + "," + " Activity Status: " + activityStatus;
    }
}
