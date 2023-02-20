package final_project.data_handlers;

import final_project.MyException;
import final_project.UIUtility;
import final_project.UserInput;
import final_project.data_access.MyDAO;
import java1review.Book;
import java1review.Person;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.Scanner;

/*public class AddBook implements MyDataHandler {
    @Override
    public void handleTask(MyDAO data_source, Scanner scanner, ResourceBundle messages) throws MyException {
        Book book = new Book();
        UIUtility.showMenuTitle(messages.getString("add-book"));
        for(;;) {
            try {
                int input = UserInput.getInt(messages.getString("book-id"), scanner, messages);
                book.setId(input);
                break;
            } catch(IllegalArgumentException e) {
                UIUtility.showErrorMessage(e.getMessage(), scanner, messages);
            }
        }
        for(;;) {
            try {
                String userIn = UserInput.getString(messages.getString("enter-title"), scanner);
                book.setTitle(userIn);
                break;
            } catch(IllegalArgumentException e) {
                UIUtility.showErrorMessage(e.getMessage(), scanner, messages);
            }
        }
        for(;;) {
            try {
                String inputFirstName = UserInput.getString(messages.getString("book-author-first-name"), scanner);
                String inputLastName = UserInput.getString(messages.getString("book-author-last-name"), scanner);
                Person author = new Person();
                author.setFirstName(inputFirstName);
                author.setLastName(inputLastName);
                book.setAuthor(author);
                break;
            } catch(IllegalArgumentException e) {
                UIUtility.showErrorMessage(e.getMessage(), scanner, messages);
            }
        }

        for(;;) {
            try {
                int userIn = UserInput.getInt(messages.getString("enter-number-of-pages"), scanner, messages);
                book.setNumPages(userIn);
                break;
            } catch(IllegalArgumentException e) {
                UIUtility.showErrorMessage(e.getMessage(), scanner, messages);
            }
        }


        for(;;) {
            try {
                LocalDateTime userIn = UserInput.getDate(messages.getString("enter-date-published-YYYY-MM-DD"), scanner, messages);
                book.setDatePublished(LocalDate.from(userIn));
                break;
            } catch(IllegalArgumentException e) {
                UIUtility.showErrorMessage(e.getMessage(), scanner, messages);
            }
        }

        for(;;) {
            try {
                Double userIn = UserInput.getDouble(messages.getString("unit-cost"), scanner, messages);
                book.setUnitPrice(userIn);
                break;
            } catch(IllegalArgumentException e) {
                UIUtility.showErrorMessage(e.getMessage(), scanner, messages);
            }
        }

        data_source.add(book);
        UIUtility.showSectionTitle(messages.getString("book-added"));
    }
}*/