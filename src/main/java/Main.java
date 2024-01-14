import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PhoneBook phoneBook = new PhoneBook();
        boolean firstLaunch = true;

        while (true) {
            if (firstLaunch){
                System.out.println("\n" +
                        " _______  __                                \n" +
                        "|_   __ \\[  |                               \n" +
                        "  | |__) || |--.   .--.   _ .--.    _   __  \n" +
                        "  |  ___/ | .-. |/ .'`\\ \\[ `.-. |  [ \\ [  ] \n" +
                        " _| |_    | | | || \\__. | | | | |   \\ '/ /  \n" +
                        "|_____|  [___]|__]'.__.' [___||__][\\_:  /   \n" +
                        "                                   \\__.'    \n" +

                        "---------------------------------------------------------\n" +
                        "                  Main Menu                              \n" +
                        "---------------------------------------------------------\n" +

                        "1. Add new contact \n" +
                        "2. Find contact \n" +
                        "3. View all contacts \n" +
                        "4. Exit \n");
                firstLaunch = false;
            }else {
                System.out.println("\n" +

                        "---------------------------------------------------------\n" +
                        "                  Main Menu                              \n" +
                        "---------------------------------------------------------\n" +

                        "1. Add new contact \n" +
                        "2. Find contact \n" +
                        "3. View all contacts \n" +
                        "4. Exit \n");

            }

            System.out.println("\nChoose action:");

            if (scanner.hasNextInt()) {
                int userInput = scanner.nextInt();
                scanner.nextLine();

                switch (userInput) {
                    case 1:
                        System.out.println("Enter a phone number (international format +):");
                        String phoneNumber = scanner.nextLine();
                        System.out.println("\nEnter contact name:");
                        String contactName = scanner.nextLine();
                        phoneBook.addContact(phoneNumber, contactName);
                        break;
                    case 2:
                        System.out.println("\n 1. By name" +
                                "\n 2. By Phone Number");
                        int searchType = scanner.nextInt();
                        scanner.nextLine();

                        switch (searchType) {
                            case 1:
                                System.out.println("\nEnter contact name:");
                                String searchName = scanner.nextLine();
                                phoneBook.getContactByName(searchName);
                                break;
                            case 2:
                                System.out.println("Enter a phone number (international format +):");
                                String searchPhone = scanner.nextLine();
                                phoneBook.getContactByPhone(searchPhone);
                                break;
                        }
                        break;
                    case 3:
                        phoneBook.getAllContacts();
                        break;
                    case 4:
                        System.exit(0);
                }

            } else {
                System.out.println("Invalid input. Please enter action number.");
                scanner.nextLine();
            }
        }
    }
}
