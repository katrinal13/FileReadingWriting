import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileReader;

public class PersonSaver
{
    public PersonSaver() { }

    public void load()
    {
        // try loading data from the file "person.data";
        // if the file isn't found, that means this is the first
        // time running the program and it hasn't been created yet
        // (in which case, an exception is thrown and the "catch"
        // block is executed)
        Scanner scan = new Scanner(System.in);
        try {
            File f = new File("src/person.data");
            Scanner input = new Scanner(f);

            while (input.hasNextLine())
            {
                System.out.println(input.nextLine());
            }

            Scanner s = new Scanner(f); // a Scanner can be initialized with a File object (rather than System.in)
            String name = "";
            String hobby = "";
            String age = "";

            System.out.println(f);
            System.out.print("Select the Person whose data to edit (indicate number): ");
            int person = scan.nextInt();
            // reading from the file line by line

            int lines = 0;
            while (s.hasNextLine())
            {
                s.nextLine();
                lines++;
            }
            int lineP = lines/person * (person - 1);

            Scanner s2 = new Scanner(f);
            int line = 0;
            while (s2.hasNextLine()) {
                String data = s2.nextLine();
                if (line == lineP) {
                    name = data;
                }
                if (line == lineP + 1) {
                    hobby = data;
                }
                if (line == lineP + 2)
                {
                    age = data;
                }
                line++;
            }
            s.close(); // close scanner

            Person p =  new Person(name, hobby, age);
            update(p);

            System.out.println("Good bye!");
        }
        // if the file doesn't exist, an Exception gets generated
        // and "thrown" and "caught" below; this calls the helper method
        // which creates a new Person object and asks them for
        // a name and hobby; when the person gets saved,
        // the file gets created (so the next time the program runs,
        // the file exists and can be loaded!)
        catch (FileNotFoundException e)
        {
            System.out.println("file doesn't exist yet! exception message: " + e.getMessage());
            String exit = "";
            System.out.print("Would you like to add a person: (Y)es or (N)o: ");
            exit = scan.nextLine().toLowerCase();
            // let's create a person and save to file so that it does exist
            while (!exit.equals("n"))
            {
                createPerson();
                System.out.print("Would you like to add a person: (Y)es or (N)o: ");
                exit = scan.nextLine().toLowerCase();
            }
        }
    }

    // private helper method
    private void update(Person person) {
        System.out.println(person.greet());
        System.out.println("Would you like to update any information? Select an option");
        System.out.println("1: Change my name");
        System.out.println("2: Change my hobby");
        System.out.println("3: Change my age");
        System.out.println("4: Change both hobby and name");
        System.out.println("5: Change both name and age");
        System.out.println("6: Change both hobby and age");
        System.out.println("7: Change hobby, name, and age");
        System.out.println("8: Exit");
        Scanner s = new Scanner(System.in);
        System.out.print("Enter your option: ");
        String option = s.nextLine();

        if (option.equals("1") || option.equals("4") || option.equals("5") || option.equals("7")) {
            String n = "";
            System.out.print("Enter your new name: ");
            n = s.nextLine();
            person.setName(n);
        }
        if (option.equals("2") || option.equals("4") || option.equals("6") || option.equals("7")) {
            String h = "";
            System.out.print("Enter your new hobby: ");
            h = s.nextLine();
            person.setHobby(h);
        }
        if (option.equals("3") || option.equals("5") || option.equals("6") || option.equals("7"))
        {
            String a = "";
            System.out.print("Enter your new age: ");
            a = s.nextLine();
            person.setAge(a);
        }
        if (!option.equals("8"))
        {
            person.save();  // calls the save() method in the Person class which saves to file
        }
        s.close();
    }

    // private helper method; only called the first time
    // the program is run and the file doesn't yet exist
    private void createPerson()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("What is your name? ");
        String name = in.nextLine();
        System.out.print("What is your hobby? ");
        String hobby = in.nextLine();
        System.out.print("What is your age? ");
        String age = in.nextLine();
        Person p = new Person(name, hobby, age);
        p.save();
    }
}
