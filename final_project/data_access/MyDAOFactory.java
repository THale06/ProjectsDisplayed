package final_project.data_access;

import java1review.Book;
import java1review.Person;

import java.awt.*;
import java.util.ArrayList;

public class MyDAOFactory {

    public static ArrayList<MyDAO> getMyDAO(String data_source){
        MyDAO personDAO = null;
        MyDAO bookDAO = null;
        switch (data_source.toUpperCase()){
            case "CSV":
                personDAO = new PersonDAO_CSV();
                bookDAO = new BookDAO_CSV();
                break;
            case "XML":
                personDAO = new PersonDAO_XML();
               // bookDao = new BookDAO_XML();
                break;
            case "MYSQL":
                personDAO = new PersonDAO_MySQL();
                //bookDAO = new BookDAO_MySQL();
                break;
        }
        ArrayList<MyDAO> daos = new ArrayList<MyDAO>();
        daos.add(personDAO);
        daos.add(bookDAO);
        return daos;
    }

}
