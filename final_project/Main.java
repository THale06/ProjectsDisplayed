package final_project;

import final_project.data_access.MyDAO;
import final_project.data_access.MyDAOFactory;
import final_project.data_handlers.*;
import java1review.Person;

import java.util.ResourceBundle;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws MyException {
        Language language = new Language();
        ResourceBundle messages = language.getMessages();
        Scanner scanner = new Scanner(System.in);
        String data_source = "csv";
        MyDAO personDAO = MyDAOFactory.getMyDAO(data_source).get(0);
        MyDAO bookDAO = MyDAOFactory.getMyDAO(data_source).get(1);
        if (personDAO == null) {
            UIUtility.showErrorMessage("Data source '" + data_source + "' invalid", scanner, messages);
            return;
        }
        if (bookDAO == null) {
            UIUtility.showErrorMessage("Data source '" + data_source + "' invalid", scanner, messages);
            return;
        }
        try {
            personDAO.readInData();
            bookDAO.readInData();
        } catch (MyException e) {
            UIUtility.showErrorMessage(e.getMessage(), scanner, messages);
            return;
        }
        if (personDAO == null) {
            System.out.println("Person data object not found");
            return;
        }
        try {
            personDAO.readInData();
        } catch (MyException e) {
            System.out.println(e.getMessage());
            return;
        }
        int choice = 0;
        while (true) {

            if (personDAO == null) {
                System.out.println("Person data object not found");
                return;
            }

            try {
                personDAO.readInData();
            } catch (MyException ex) {
                System.out.println(ex.getMessage());
            }

            String menuTitle = messages.getString("main-menu");
            String prompt = messages.getString("prompt");
            String[] menuOptions = {
                    messages.getString("add-person")
                    , messages.getString("get-person")
                    , messages.getString("update-person")
                    , messages.getString("delete-person")
                    , messages.getString("add-book")
                    , messages.getString("get-book")
                    , messages.getString("update-book")
                    , messages.getString("delete-book")
                    , messages.getString("change-language")
            };
            choice = UIUtility.showMenuOptions(menuTitle, prompt, menuOptions, scanner, messages);
            if (choice <= 0 || choice > menuOptions.length + 1) {
                UIUtility.pressEnterToContinue(scanner, messages);
                continue;
            }
            if (choice == menuOptions.length + 1) {
                break;
            }
            UIUtility.showSectionTitle(menuOptions[choice - 1]);
            try {
                switch (choice) {
                    case 1:
                        new AddPerson().handleTask(personDAO, scanner, messages);
                        break;
                    case 2:
                        new GetPerson().handleTask(personDAO, scanner, messages);
                        break;
                    case 3:
                        new UpdatePerson().handleTask(personDAO, scanner, messages);
                        break;
                    case 4:
                        new DeletePerson().handleTask(personDAO, scanner, messages);
                        break;
                    case 5:
                        //new AddBook().handleTask(bookDAO, scanner, messages);
                        break;
                    case 6:
                        new GetBook().handleTask(bookDAO, scanner, messages);
                        break;
                    case 7:
                        new UpdateBook().handleTask(bookDAO, scanner, messages);
                        break;
                    case 8:
                        new DeleteBook().handleTask(bookDAO, scanner, messages);
                        break;
                    case 9:
                        language.setMessages(scanner);
                        messages = language.getMessages();
                        break;
                }
            } catch (MyException ex) {
                UIUtility.showErrorMessage(ex.getMessage(), scanner, messages);
            }
            UIUtility.pressEnterToContinue(scanner, messages);
        }
        System.out.println("\n" + messages.getString("end"));
        scanner.close();
    }
}