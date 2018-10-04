import ru.zinnurov.Library;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Nail Zinnurov
 * cobratms@gmail.com
 * on 20.09.2018
 */

public class Main {
    private static final String LIBRARY_DIR_PATH = "Library";

    public static void main(String[] args) throws IOException {

        File lib = new File(LIBRARY_DIR_PATH);
        checkAvailableDir(lib);

        welcomeMenu();


    }

    private static void checkAvailableDir(File lib) {
        if(!lib.isDirectory()) {
            lib.mkdir();
        }
    }

    private static void welcomeMenu() throws IOException {


        System.out.println("Welcome to the Library!");
        System.out.println("What do you want to do?");
        System.out.println("1. View the list of available books.");
        System.out.println("2. View a list of genres.");
        System.out.println("3. Add a book.");
        System.out.println("4. Delete the book.");
        System.out.println("5. Exit");

        while(chooseAct()) {
            System.out.println("\n" + "Enter the number corresponding to the desired act.");
        }
    }

    private static boolean chooseAct() throws IOException {
        Scanner choice = new Scanner(System.in);
        int numberAct;
        boolean checkInput = true;
        boolean exitFlag = true;

        Library libActivities = new Library();
        while (checkInput) {
            if(choice.hasNextInt()) {
                numberAct = choice.nextInt();

                switch (numberAct) {
                    case 1:
                        libActivities.viewListBooks();
                        checkInput = false;
                        break;

                    case 2:
                        libActivities.viewListGenres();
                        checkInput = false;
                        break;

                    case 3:
                        libActivities.addBook();
                        checkInput = false;
                        break;

                    case 4:
                        libActivities.deleteBook();
                        checkInput = false;
                        break;

                    case 5:
                        System.out.println("Good Luck!");
                        checkInput = false;
                        exitFlag = false;
                        break;

                    default:
                        System.out.println("Enter numbers from 1 to 4!");
                }
            }
            else {
                System.out.println("Enter numbers from 1 to 4!");
                choice.next();
            }
        }

        return  exitFlag;
    }

}

