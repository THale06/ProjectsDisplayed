package final_project.data_handlers;

import final_project.MyException;
import final_project.UserInput;
import final_project.data_access.MyDAO;
import java1review.Book;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class GetBook implements MyDataHandler{

    @Override
    public void handleTask(MyDAO data_source, Scanner scanner, ResourceBundle messages) throws MyException {
        String userIn = UserInput.getString("Search for book by their id, " +
                "title, author name, 0r date published(YYYY-MM-DD)", scanner);
        try {
            // Check for id
            int id = Integer.parseInt(userIn);
            Book book =(Book)data_source.get(id);
            if(book == null){
                System.out.println("No book found with id '"+ id + "'.");
            }else {
                System.out.println("\nRetrieved:");
                System.out.println(book);
            }
        }catch (NumberFormatException e1){
            try {
                DateTimeFormatter formatterInput = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate datePublished = LocalDate.parse(userIn, formatterInput);
                List<Book> books = data_source.get(datePublished);
                if (books.size() == 0){
                    System.out.println("\nNo book found with this published date '" + datePublished + "'.");
                } else {
                    System.out.println("\nRetrieved:");
                    for (Book book: books) {
                        System.out.println(book);
                    }
                }
            } catch (DateTimeParseException e2){
                // The input must be a string
                List<Book> books = data_source.get(userIn);
                if (books.size() == 0){
                    System.out.println("\nNo book found with this title or author name containing '" + userIn + "'.");
                } else {
                    System.out.println("\nRetrieved:");
                    for (Book book: books) {
                        System.out.println(book);
                    }
                }
            }

        }
    }
}
