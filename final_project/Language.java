package final_project;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Language {
    private ResourceBundle messages;

    public Language() {
        messages = ResourceBundle.getBundle("messages", Locale.ENGLISH);
    }

    public ResourceBundle getMessages() {
        return messages;
    }

    public void setMessages(Scanner scanner) {
        int choice = 0;
        while (true) {
        String menuTitle = messages.getString("languages-available");
        String prompt = messages.getString("select-language");
        String[] menuOptions = {
                this.messages.getString("english"), this.messages.getString("french"), this.messages.getString("swedish")
            };

        choice = UIUtility.showMenuOptions(menuTitle, prompt, menuOptions, scanner, messages);
            if(choice <= 0 || choice > menuOptions.length + 1) {
                UIUtility.pressEnterToContinue(scanner, messages);
                continue;
            }
            else if(choice == menuOptions.length + 1) {
                System.out.println("\n" + this.messages.getString("language-canceled") + ".");
                break;
            }
            else if (choice == 1){
                this.messages = ResourceBundle.getBundle("messages", Locale.getDefault());
                System.out.println("\n" + this.messages.getString("language-changed") + ".");
                break;
            }
            else if (choice == 2){
                this.messages = ResourceBundle.getBundle("messages", Locale.FRENCH);
                System.out.println("\n" + this.messages.getString("language-changed") + ".");
                break;
            }
            else if (choice == 3){
                this.messages = ResourceBundle.getBundle("messages", new Locale("sv","SE"));
                System.out.println("\n" + this.messages.getString("language-changed") + ".");
                break;
            }
        }
    }
}
