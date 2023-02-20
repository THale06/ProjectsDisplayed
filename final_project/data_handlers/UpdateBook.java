package final_project.data_handlers;

import final_project.MyException;
import final_project.UIUtility;
import final_project.UserInput;
import final_project.data_access.MyDAO;
import java1review.Book;
import java1review.Person;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class UpdateBook implements MyDataHandler{
    @Override
    public void handleTask(MyDAO data_source, Scanner scanner, ResourceBundle messages) throws MyException {
        List<Book> list = data_source.getAll();
        if (list != null && list.size() > 0) {
            int choice = 0;
            while (true) {
                String menuTitle = "Update Book";
                String prompt = "Select a book to update";
                String[] menuOptions = new String[list.size()];
                for (int i = 0; i < list.size(); i++){
                    menuOptions[i] = list.get(i).toString();
                }
                choice = UIUtility.showMenuOptions(menuTitle, prompt, menuOptions, scanner, messages);
                if (choice == menuOptions.length + 1) {
                    break;
                }
                if (choice <= 0 || choice > menuOptions.length + 1) {
                    UIUtility.pressEnterToContinue(scanner, messages);
                    continue;
                }
                UIUtility.showSectionTitle("Updating " + menuOptions[choice - 1]);
                Book book = list.get(choice - 1);
                updateBook(book, data_source, choice - 1, scanner, messages);
            }
        } else {
            System.out.println("\nThere are no records found");
        }
    }

    private void updateBook(Book book, MyDAO data_source, int choice, Scanner scanner, ResourceBundle messages) throws MyException {
        String keepCurrent = " (Press enter to keep the current value)";
        System.out.println("\nBook-ID: " + book.getId());
        for (; ; ) {
            try {
                String input = UserInput.getString(messages.getString("new-id") + " " +keepCurrent, scanner);
                if (input == "") {
                    break;
                }
                book.setId(Integer.parseInt(input));
                break;
            } catch (IllegalArgumentException e) {
                UIUtility.showErrorMessage(e.getMessage(), scanner, messages);
            }
        }

        System.out.println("First name: " + book.getTitle());
        for (; ; ) {
            try {
                String userIn = UserInput.getString("New title" + keepCurrent, scanner);
                if (userIn.equals("")) {
                    break;
                }
                book.setTitle(userIn);
                break;
            } catch (IllegalArgumentException e) {
                UIUtility.showErrorMessage(e.getMessage(), scanner, messages);
            }
        }

        System.out.println("\n" + messages.getString("author") + " " + book.getAuthor());
        for (; ; ) {
            try {
                String userIn = UserInput.getString(messages.getString("new-author") + " " +keepCurrent, scanner);
                if (userIn.equals("")) {
                    break;
                }
                book.setAuthor(new Person(userIn));
                break;
            } catch (IllegalArgumentException e) {
                UIUtility.showErrorMessage(e.getMessage(), scanner, messages);
            }
        }

        System.out.println("Number of Pages: " + book.getNumPages());
        for (; ; ) {
            try {
                String userIn = UserInput.getString("New Number of pages" + keepCurrent, scanner);
                if (userIn.equals("")) {
                    break;
                }
                book.setNumPages(Integer.parseInt(userIn));
                break;
            } catch (NumberFormatException e) {
                UIUtility.showErrorMessage("Invalid Number", scanner, messages);
            } catch (IllegalArgumentException e) {
                UIUtility.showErrorMessage(e.getMessage(), scanner, messages);
            }
        }


        System.out.println("Date Published: " + book.getDatePublished());
        for(;;) {
            try {
                String userIn = UserInput.getString("New date published (YYYY-MM-DD" + keepCurrent, scanner);
                if (userIn.equals("")) {
                    break;
                }
                DateTimeFormatter formatterInput = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                book.setDatePublished(LocalDate.parse(userIn, formatterInput));
                break;
            } catch (DateTimeParseException e) {
                UIUtility.showErrorMessage("Invalid Number", scanner, messages);
            } catch (IllegalArgumentException e) {
                UIUtility.showErrorMessage(e.getMessage(), scanner, messages);
            }
        }

        System.out.println("Unit Price: " + book.isRead());
        for (; ; ) {
            try {
                String userIn = UserInput.getString("Was Read" + keepCurrent, scanner);
                if (userIn.equals("")) {
                    break;
                }
                book.setRead(Boolean.parseBoolean(userIn));
                break;
            } catch (NumberFormatException e) {
                UIUtility.showErrorMessage("Invalid Number", scanner, messages);
            } catch (IllegalArgumentException e) {
                UIUtility.showErrorMessage(e.getMessage(), scanner, messages);
            }
        }


        System.out.println("Unit Price: " + book.getUnitPrice());
        for (; ; ) {
            try {
                String userIn = UserInput.getString("New Price" + keepCurrent, scanner);
                if (userIn.equals("")) {
                    break;
                }
                book.setUnitPrice(Double.parseDouble(userIn));
                break;
            } catch (NumberFormatException e) {
                UIUtility.showErrorMessage("Invalid Number", scanner, messages);
            } catch (IllegalArgumentException e) {
                UIUtility.showErrorMessage(e.getMessage(), scanner, messages);
            }
        }

        data_source.set(choice, book);
        System.out.println("\nBook updated");

        UIUtility.pressEnterToContinue(scanner, messages);
    }
}
