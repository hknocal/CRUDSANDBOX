package MemberSystem;

import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface {
    Scanner sc = new Scanner(System.in);
    Controller controller = new Controller();

    public void loadUI() {
        loadDB();
        boolean isRunning = true;
        System.out.print("""
                -----------------------------
                ** MEMBER HANDLING SYSTEM **
                -----------------------------
                """);
        showMenu();
        help();
        while (isRunning) {
            command();
            switch (readInt()) {
                case 0:
                    showMenu();
                    break;
                case 1:
                    addMember();
                    break;
                case 2:
                    editMember();
                    break;
                case 3:
                    deleteMember();
                    break;
                case 4:
                    showMembers();
                    break;
                case 5:
                    searchMembers();
                    break;
                case 9:
                    saveToDB();
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid input. Try again");
                    break;
            }
        }
    }

    public void showMenu() {
        System.out.println("""
                1. Add member
                2. Edit member
                3. Delete member
                4. Show members
                5. Search members
                9. Exit and save
                """);
    }

    public void command() {
        System.out.print("Enter command -> ");
    }

    public void help() {
        System.out.println("Enter 0 in the command line to see the menu options again." + "\n");
    }

    public int readInt() {
        boolean checkInput;
        int readInt = 0;

        do {
            try {
                checkInput = false;
                readInt = sc.nextInt();
            } catch (InputMismatchException e) {
                sc.next();
                checkInput = true;
                System.out.println("Invalid numeric input. Try again");
            }
        } while (checkInput == true);
        return readInt;
    }

    public void addMember() {
        System.out.println("Enter name: ");
        String name = sc.next();

        System.out.println("Enter surname: ");
        String surname = sc.next();

        System.out.println("Enter age: ");
        int age = readInt();

        System.out.println("Enter activity status - answer YES if active, else the member will be considered inactive: ");
        boolean activityStatus = false;
        String isActivityStatus = sc.next();
        if (isActivityStatus.contentEquals("yes")) {
            activityStatus = true;
        }

        controller.addMember(name, surname, age, activityStatus);

        System.out.println("Member added to DB.");
    }

    public void deleteMember() {
        try {
            for (int i = 0; i < controller.getMemberDB().size(); i++) {
                System.out.println("[ID: " + i + "] " + controller.getMemberDB().get(i));
            }
            System.out.println("Enter the ID you wish to delete: ");
            int number = readInt();
            controller.deleteMember(number);
            System.out.println("Member with ID: " + number + " was deleted.");
        } catch (InputMismatchException e) {
            System.out.println("Unable to delete the requested member. Try again");
        }
    }

    public void editMember() {
        try {
            for (int i = 0; i < controller.getMemberDB().size(); i++) {
                System.out.println("[" + i + "] " + controller.getMemberDB().get(i));
            }
            System.out.println("Enter the ID you wish to edit: ");
            int number = readInt();
            sc.nextLine();

            Member editMember = controller.db.getMemberDB().get(number);
            System.out.println("Editing: " + editMember.getName() + " " + editMember.getSurname());
            System.out.println("Enter your changes and press ENTER. If you wish to skip, leave the field blank and press ENTER");


            System.out.println("Name: " + editMember.getName());
            System.out.print("Enter new name -> ");
            String newName = sc.nextLine();
            if (!newName.isEmpty()) editMember.setName(newName);

            System.out.println("Surname: " + editMember.getSurname());
            System.out.print("Enter new surname -> ");
            String newSurname = sc.nextLine();
            if (!newSurname.isEmpty()) editMember.setSurname(newSurname);

            System.out.println("Age: " + editMember.getAge());
            System.out.print("Enter new age -> ");
            String newAge = sc.nextLine();
            if (!newAge.isEmpty()) editMember.setAge(Integer.parseInt(newAge));

            System.out.println("Activity status: " + editMember.getActivityStatus());
            System.out.print("Enter new activity status (true if active, false if inactive) -> ");
            String newActivityStatus = sc.nextLine();
            if (!newActivityStatus.isEmpty()) editMember.setActivityStatus(Boolean.parseBoolean(newActivityStatus));

            System.out.println("Your edit has been stored.");

        } catch (InputMismatchException e) {
            System.out.println("Unable to edit the requested member. Try again");
        }
    }

    public void showMembers() {
        System.out.println("""
                1. Show list of members
                2. Show sorted list of members
                """);
        command();
        switch (readInt()) {
            case 1:
                printMembers();
                break;
            case 2:
                System.out.println("""
                        1. Sort list by one criteria
                        2. Sort list by two criteria
                        """);
                command();
                switch (readInt()) {
                    case 1 -> sortByOneCriteria();
                    case 2 -> sortByTwoCriteria();
                    default -> System.out.println("Invalid input. Try again");
                }
        }
    }

    public void sortByOneCriteria() {
        System.out.println("""
                Select criteria to sort list with
                1. Name
                2. Surname
                3. Age
                4. Activity Stus
                """);
        command();

        switch (readInt()) {
            case 1:
                controller.sortByName();
                printMembers();
                break;
            case 2:
                controller.sortBySurname();
                printMembers();
                break;
            case 3:
                controller.sortByAge();
                printMembers();
                break;
            case 4:
                controller.sortByActivityStatus();
                printMembers();
                break;
            default:
                System.out.println("Invalid input. Try again");
                break;
        }
    }

    public void sortByTwoCriteria() {

        Comparator<Member> firstCriteria = null;
        Comparator<Member> secondCriteria = null;

        System.out.println("""
                Select first criteria to sort list with
                1. Name
                2. Surname
                3. Age
                4. Activity Status
                """);
        command();
        int firstChoice = readInt();
        switch (firstChoice) {
            case 1:
                firstCriteria = new NameComparator();
                break;
            case 2:
                firstCriteria = new SurnameComparator();
                break;
            case 3:
                firstCriteria = new AgeComparator();
                break;
            case 4:
                firstCriteria = new ActivityStatusComparator();
                break;
        }

        System.out.println("""
                Select second criteria to sort list with
                1. Name
                2. Surname
                3. Age
                4. Activity Status
                """);
        command();
        int secondChoice = readInt();
        switch (secondChoice) {
            case 1:
                secondCriteria = new NameComparator();
                break;
            case 2:
                secondCriteria = new SurnameComparator();
                break;
            case 3:
                secondCriteria = new AgeComparator();
                break;
            case 4:
                secondCriteria = new ActivityStatusComparator();
                break;
        }

        Collections.sort(controller.getMemberDB(), firstCriteria.thenComparing(secondCriteria));
        printMembers();
    }

    public void printMembers() {
        for (Member members : controller.getMemberDB()) {
            System.out.println(members);
        }
    }

    public void searchMembers() {
        System.out.print("Enter search criteria -> ");
        String searchCriteria = sc.next();
        boolean found = false;
        for (Member m : controller.getMemberDB()) {
            if (m.getName().equals(searchCriteria)) {
                System.out.println("Member found -> " + m);
                found = true;
            }
            if (m.getSurname().equals(searchCriteria)) {
                System.out.println("Member found -> " + m);
                found = true;
            }
        }
        if (!found) System.out.println("No member with the search criteria: " + searchCriteria + " was found.");
    }

    public void saveToDB() {
        controller.saveToDB();
        System.out.println(controller.db.getMemberDB().size() + " members have been saved to the database.");
        System.out.println("Member system has been terminated.");
    }

    public void loadDB() {
        controller.loadDB();
        System.out.println("Database with members has been loaded.");
    }
}
