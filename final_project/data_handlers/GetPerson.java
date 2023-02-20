package final_project.data_handlers;

import final_project.MyException;
import final_project.UserInput;
import final_project.data_access.MyDAO;
import java1review.Person;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class GetPerson  implements  MyDataHandler{

    @Override
    public void handleTask(MyDAO data_source, Scanner scanner, ResourceBundle messages) throws MyException {
        String userIn = UserInput.getString("Search for person by their id, " +
                "first name, last name, 0r date of birth(YYYY-MM-DD)", scanner);
        try {
            // Check for id
            int id = Integer.parseInt(userIn);
            Person person =(Person)data_source.get(id);
            if(person == null){
                System.out.println("No person found with id '"+ id + "'.");
            }else {
                System.out.println("\nRetrieved:");
                System.out.println(person);
            }
        }catch (NumberFormatException e1){
            try {
                DateTimeFormatter formatterInput = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate dateOfBirth = LocalDate.parse(userIn, formatterInput);
                List<Person> people = data_source.get(dateOfBirth);
                if (people.size() == 0){
                    System.out.println("\nNo person found with this birth date '" + dateOfBirth + "'.");
                } else {
                    System.out.println("\nRetrieved:");
                    for (Person person: people) {
                        System.out.println(person);
                    }
                }
            } catch (DateTimeParseException e2){
                // The input must be a string
                List<Person> people = data_source.get(userIn);
                if (people.size() == 0){
                    System.out.println("\nNo person found with first or last name containing '" + userIn + "'.");
                } else {
                    System.out.println("\nRetrieved:");
                    for (Person person: people) {
                        System.out.println(person);
                    }
                }
            }

        }
    }
}
