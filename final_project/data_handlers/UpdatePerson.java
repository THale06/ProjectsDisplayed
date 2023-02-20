package final_project.data_handlers;

import final_project.MyException;
import final_project.UIUtility;
import final_project.UserInput;
import final_project.data_access.MyDAO;
import java1review.Person;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class UpdatePerson implements MyDataHandler {
    @Override
    public void handleTask(MyDAO data_source, Scanner scanner, ResourceBundle messages) throws MyException {
        List<Person> list = data_source.getAll();
        if (list != null && list.size() > 0) {
            int choice = 0;
            while (true) {
                String menuTitle = "Update Person";
                String prompt = "Select a person to update";
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
                Person person = list.get(choice - 1);
                updatePerson(person, data_source, choice - 1, scanner, messages);
            }
        } else {
            System.out.println("\nThere are no records found");
        }
    }

    private void updatePerson(Person person, MyDAO data_source, int choice, Scanner scanner, ResourceBundle messages) throws MyException {
        String keepCurrent = " (Press enter to keep the current value)";
        System.out.println("First name: " + person.getFirstName());
        for (; ; ) {
            try {
                String userIn = UserInput.getString("New first name" + keepCurrent, scanner);
                if (userIn.equals("")) {
                    break;
                }
                person.setFirstName(userIn);
                break;
            } catch (IllegalArgumentException e) {
                UIUtility.showErrorMessage(e.getMessage(), scanner, messages);
            }
        }

        System.out.println("Last name: " + person.getLastName());
        for (; ; ) {
            try {
                String userIn = UserInput.getString("New last name" + keepCurrent, scanner);
                if (userIn.equals("")) {
                    break;
                }
                person.setFirstName(userIn);
                break;
            } catch (IllegalArgumentException e) {
                UIUtility.showErrorMessage(e.getMessage(), scanner, messages);
            }
        }

        System.out.println("Height (in inches): " + person.getHeightInInches());
        for (; ; ) {
            try {
                String userIn = UserInput.getString("New Height" + keepCurrent, scanner);
                if (userIn.equals("")) {
                    break;
                }
                person.setHeightInInches(Integer.parseInt(userIn));
                break;
            } catch (NumberFormatException e) {
                UIUtility.showErrorMessage("Invalid Number", scanner, messages);
            } catch (IllegalArgumentException e) {
                UIUtility.showErrorMessage(e.getMessage(), scanner, messages);
            }
        }

        System.out.println("Weight (in pounds): " + person.getHeightInInches());
        for (; ; ) {
            try {
                String userIn = UserInput.getString("New Weight" + keepCurrent, scanner);
                if (userIn.equals("")) {
                    break;
                }
                person.setWeightInPounds(Double.parseDouble(userIn));
                break;
            } catch (NumberFormatException e) {
                UIUtility.showErrorMessage("Invalid Number", scanner, messages);
            } catch (IllegalArgumentException e) {
                UIUtility.showErrorMessage(e.getMessage(), scanner, messages);
            }
        }

        System.out.println("Date of Birth: " + person.getDateOfBirth().toLocalDate());
            for(;;) {
                try {
                    String userIn = UserInput.getString("New date of birth (YYYY-MM-DD" + keepCurrent, scanner);
                    if (userIn.equals("")) {
                        break;
                    }
                    DateTimeFormatter formatterInput = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    person.setDateOfBirth(LocalDate.parse(userIn, formatterInput).atStartOfDay());
                    break;
                } catch (DateTimeParseException e) {
                    UIUtility.showErrorMessage("Invalid Number", scanner, messages);
                } catch (IllegalArgumentException e) {
                    UIUtility.showErrorMessage(e.getMessage(), scanner, messages);
                }
            }

        data_source.set(choice, person);
        System.out.println("\nPerson updated");

        UIUtility.pressEnterToContinue(scanner, messages);
    }
}
