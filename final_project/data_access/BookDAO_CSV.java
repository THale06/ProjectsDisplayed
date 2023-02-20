package final_project.data_access;

import final_project.MyException;
import java1review.Book;
import java1review.Person;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.*;

public class BookDAO_CSV implements MyDAO<Book> {
    private static final String FILE_NAME = "src/main/resources/csv_files/Book.csv";
    private List<Book> list;
    private int next_id = 0;

    @Override
    public void readInData() throws MyException {
        try(Scanner scanner = new Scanner(new File(FILE_NAME))) {
            list = new ArrayList<Book>();
            String line = "";
            int lineCount = 0;
            String[] fields;
            int id = 0;
            String title = "";
            Person author = new Person();
            int numPages = 1;
            LocalDate datePublished = LocalDate.now();
            boolean read = false;
            double unitPrice = 0.00;

            line = scanner.nextLine();  // reads in header row

            while(scanner.hasNextLine()) {
                lineCount++;
                line = scanner.nextLine();
                fields = line.split(",");
                id = Integer.parseInt(fields[0]);
                title = fields[1];
                author = new Person(fields[2]);
                numPages = Integer.parseInt(fields[3]);
                datePublished = LocalDate.parse(fields[4]);
                read = Boolean.parseBoolean(fields[5]);
                unitPrice = Double.parseDouble(fields[6]);


                Book Book = new Book(id, fields[1], new Person(fields[2]), Integer.parseInt(fields[3]), LocalDate.parse((fields[4])), Boolean.parseBoolean(fields[5]), Double.parseDouble(fields[6]));
                Book.setId(id);
                Book.setAuthor(author);
                Book.setNumPages(numPages);
                Book.setDatePublished(datePublished);
                Book.setRead(read);
                Book.setUnitPrice(unitPrice);
                if(id > next_id) {
                    next_id = id;
                }
                list.add(Book);

            }


        } catch(FileNotFoundException e) {
            throw new MyException("File '" + FILE_NAME + "' not found");
        }
    }

    @Override
    public void add(Book obj) throws MyException {
        obj.setId(++next_id);
        list.add(obj);
        saveToFile();
    }

    private void saveToFile() throws MyException {
        try (PrintWriter writer = new PrintWriter(new File(FILE_NAME))){
            String firstLine = "id,title,author,numPages,datePublished,read,unitPrice";
            writer.println(firstLine);
            list.forEach((Book) -> {
                String line = Book.getId() + ","
                        + Book.getTitle() + ","
                        + Book.getAuthor() + ","
                        + Book.getNumPages() + ","
                        + Book.getDatePublished() + ","
                        + Book.isRead() + ","
                        + Book.getUnitPrice();

                writer.println(line);
            });
        }catch (FileNotFoundException e){
            throw new MyException(e.getMessage());
        }
    }

    @Override
    public Book get(int id) throws MyException {
        try(Scanner scanner = new Scanner(new File(FILE_NAME))) {
            String line = scanner.nextLine();
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                String[] fields = line.split(",");
                if (id == Integer.parseInt(fields[0])) {
                    Book Book = new Book(id, fields[1], new Person(fields[2]), Integer.parseInt(fields[3]), LocalDate.parse((fields[4])), Boolean.parseBoolean(fields[5]),Double.parseDouble(fields[6]));
                }
            }
        }catch(FileNotFoundException e) {
            throw new MyException("File cannot be found at '" + FILE_NAME + "'.");
        }
        return null;
    }

    @Override
    public List<Book> get(String name) throws MyException {
        return null;
    }


    public List<Book> get(Person author) throws MyException {
        List<Book> list = new ArrayList<>();
        try(Scanner scanner = new Scanner(new File(FILE_NAME))) {
            String line = scanner.nextLine();
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                String[] fields = line.split(",");
                String firstName = fields[1].toUpperCase();
                String lastName = fields[2].toUpperCase();
                if (firstName.contains(author.toString().toUpperCase()) || lastName.contains(author.toString().toUpperCase()) || (firstName + " " + lastName).contains(author.toString().toUpperCase())) {
                    Book Book = new Book(Integer.parseInt(fields[0]), fields[1], new Person(fields[2]), Integer.parseInt(fields[3]), LocalDate.parse(fields[4]),Boolean.parseBoolean(fields[5]), Double.parseDouble(fields[6]));
                    list.add(Book);
                }
            }
        } catch(FileNotFoundException e) {
            throw new MyException("File cannot be found at '" + FILE_NAME + "'.");
        }
        Collections.sort(list);
        return list;
    }

    @Override
    public List<Book> get(LocalDate date) throws MyException {
        List<Book> list = new ArrayList<>();
        try(Scanner scanner = new Scanner(new File(FILE_NAME))) {
            String line = scanner.nextLine();
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                String[] fields = line.split(",");
                if (date.equals(LocalDate.parse(fields[5]))) {
                    Book Book = new Book(Integer.parseInt(fields[0]), fields[1], new Person (fields[2]), Integer.parseInt(fields[3]),LocalDate.parse(fields[4]), Boolean.parseBoolean(fields[5]), Double.parseDouble(fields[6]));
                    list.add(Book);
                }
            }
        } catch(FileNotFoundException e) {
            throw new MyException("File cannot be found at '" + FILE_NAME + "'.");
        }
        return list;
    }

    @Override
    public List<Book> getAll() throws MyException {
        return list;
    }

    @Override
    public void set(int id, Book obj) throws MyException {
        list.set(id, obj);
        saveToFile();
    }

    @Override
    public boolean remove(Book obj) throws MyException {
        boolean removed = list.remove(obj);
        saveToFile();
        return removed;
    }
}