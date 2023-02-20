package final_project.data_access;

import final_project.MyException;
import java1review.Person;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PersonDAO_CSV implements MyDAO<Person> {
    private static final String FILE_NAME = "src/main/resources/csv_files/person.csv";
    private List<Person> list;
    private int next_id = 0;

    @Override
    public void readInData() throws MyException {
        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            list = new ArrayList<Person>();
            String line = "";
            int lineCount = 0;
            String[] fields;
            int id = 0;
            String firstName = "";
            String lastName = "";
            int height = 0;
            double weight = 0;
            LocalDate dateOfBirth;
            line = reader.readLine();  // reads in header row

            while(true) {
                lineCount++;
                line = reader.readLine();
                fields = line.split(",");
                id = Integer.parseInt(fields[0]);
                firstName = fields[1];
                lastName = fields[2];
                height = Integer.parseInt(fields[3]);
                weight = Double.parseDouble(fields[4]);
                dateOfBirth = LocalDate.parse(fields[5]);

                Person person = new Person();
                person.setId(id);
                person.setFirstName(firstName);
                person.setLastName(lastName);
                person.setHeightInInches(height);
                person.setWeightInPounds(weight);
                person.setDateOfBirth(dateOfBirth.atStartOfDay());
                if(id > next_id) {
                    next_id = id;
                }
                if(reader.readLine()== null){
                    break;
                }
                list.add(person);

            }


        } catch(IOException e) {
            throw new MyException("File '" + FILE_NAME + "' not found");
        }
        /*try(Scanner scanner = new Scanner(new File(FILE_NAME))) {
            list = new ArrayList<Person>();
            String line = "";
            int lineCount = 0;
            String[] fields;
            int id = 0;
            String firstName = "";
            String lastName = "";
            int height = 0;
            double weight = 0;
            LocalDate dateOfBirth;
            line = scanner.nextLine();  // reads in header row

            while(scanner.hasNextLine()) {
                lineCount++;
                line = scanner.nextLine();
                fields = line.split(",");
                id = Integer.parseInt(fields[0]);
                firstName = fields[1];
                lastName = fields[2];
                height = Integer.parseInt(fields[3]);
                weight = Double.parseDouble(fields[4]);
                dateOfBirth = LocalDate.parse(fields[5]);

                Person person = new Person();
                person.setId(id);
                person.setFirstName(firstName);
                person.setLastName(lastName);
                person.setHeightInInches(height);
                person.setWeightInPounds(weight);
                person.setDateOfBirth(dateOfBirth.atStartOfDay());
                if(id > next_id) {
                    next_id = id;
                }
                list.add(person);

            }


        } catch(FileNotFoundException e) {
            throw new MyException("File '" + FILE_NAME + "' not found");
        }*/
    }

    @Override
    public void add(Person obj) throws MyException {
        obj.setId(++next_id);
        list.add(obj);
        saveToFile();
    }

    private void saveToFile() throws MyException {
        try (PrintWriter writer = new PrintWriter(new File(FILE_NAME))){
            String firstLine = "id,firstName,lastName,heightInInches,weightInPounds,dateOfBirth";
            writer.println(firstLine);
            list.forEach((person) -> {
                String line = person.getId() + ","
                        + person.getFirstName() + ","
                        + person.getLastName() + ","
                        + person.getHeightInInches() + ","
                        + person.getWeightInPounds() + ","
                        + person .getDateOfBirth().toLocalDate();
                writer.println(line);
            });
        }catch (FileNotFoundException e){
            throw new MyException(e.getMessage());
        }
    }

    @Override
    public Person get(int id) throws MyException {
        try(Scanner scanner = new Scanner(new File(FILE_NAME))) {
            String line = scanner.nextLine();
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                String[] fields = line.split(",");
                if (id == Integer.parseInt(fields[0])) {
                    Person person = new Person(id, fields[1], fields[2], Integer.parseInt(fields[3]),
                            Double.parseDouble(fields[4]), LocalDate.parse(fields[5]).atStartOfDay());
                }
            }
        }catch(FileNotFoundException e) {
            throw new MyException("File cannot be found at '" + FILE_NAME + "'.");
        }
        return null;
    }

    @Override
    public List<Person> get(String name) throws MyException {
        List<Person> list = new ArrayList<>();
        try(Scanner scanner = new Scanner(new File(FILE_NAME))) {
            String line = scanner.nextLine();
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                String[] fields = line.split(",");
                String firstName = fields[1].toUpperCase();
                String lastName = fields[2].toUpperCase();
                if (firstName.contains(name.toUpperCase()) || lastName.contains(name.toUpperCase()) || (firstName + " " + lastName).contains(name.toUpperCase())) {
                    Person person = new Person(Integer.parseInt(fields[0]), fields[1], fields[2], Integer.parseInt(fields[3]), Double.parseDouble(fields[4]), LocalDate.parse(fields[5], DateTimeFormatter.ofPattern("yyyy-MM-dd")).atStartOfDay());
                    list.add(person);
                }
            }
        } catch(FileNotFoundException e) {
            throw new MyException("File cannot be found at '" + FILE_NAME + "'.");
        }
        Collections.sort(list);
        return list;
    }

    @Override
    public List<Person> get(LocalDate date) throws MyException {
        List<Person> list = new ArrayList<>();
        try(Scanner scanner = new Scanner(new File(FILE_NAME))) {
            String line = scanner.nextLine();
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                String[] fields = line.split(",");
                if (date.equals(LocalDate.parse(fields[5]))) {
                    Person person = new Person(Integer.parseInt(fields[0]), fields[1], fields[2], Integer.parseInt(fields[3]), Double.parseDouble(fields[4]), date.atStartOfDay());
                    list.add(person);
                }
            }
        } catch(FileNotFoundException e) {
            throw new MyException("File cannot be found at '" + FILE_NAME + "'.");
        }
        return list;
    }

    @Override
    public List<Person> getAll() throws MyException {
        return list;
    }

    @Override
    public void set(int id, Person obj) throws MyException {
        list.set(id, obj);
        saveToFile();
    }

    @Override
    public boolean remove(Person obj) throws MyException {
        boolean removed = list.remove(obj);
        saveToFile();
        return removed;
    }
}