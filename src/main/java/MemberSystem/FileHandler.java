package MemberSystem;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    File file = new File("Data/memberList.csv");
    FileWriter writer;

    public void saveToDB(ArrayList<Member> memberDB) {
        try {
            writer = new FileWriter(file);
            for (Member m : memberDB) {
                writer.write(m.getName() + "," + m.getSurname() + "," + m.getAge() + "," + m.getActivityStatus() + "\n");
            }
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList <Member> loadDB() {
        ArrayList <Member> memberList = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()) {
                String[] Strings = sc.nextLine().split(",");
                Member member = new Member(
                        Strings [0],
                        Strings [1],
                        Integer.parseInt(Strings[2]),
                        Boolean.parseBoolean(Strings[3])
                );
                memberList.add(member);
            }
            sc.close();
            return memberList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
