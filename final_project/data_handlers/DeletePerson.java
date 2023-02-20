package final_project.data_handlers;

import final_project.MyException;
import final_project.UIUtility;
import final_project.data_access.MyDAO;
import java1review.Person;

import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class DeletePerson implements MyDataHandler{

    @Override
    public void handleTask(MyDAO data_source, Scanner scanner, ResourceBundle messages) throws MyException {
        List<Person> list = data_source.getAll();
        if(list != null && list.size() > 0) {
            int choice = 0;
            while(true) {
                String menuTitle = "Delete Person";
                String prompt = "Select a person to delete";
                String[] menuOptions = new String[list.size()];
                for(int i = 0; i < list.size(); i++) {
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
                Person person = list.get(choice - 1);
                if (data_source.remove(person)){
                    System.out.println("Person removed");
                }else {
                    System.out.println("could not delete the person");
                }
                data_source.remove(person);
                break;
            }
        } else {
            System.out.println("\nThere are no records found.");
        }
    }
}
