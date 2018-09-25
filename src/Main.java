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
    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(System.in);
        boolean key = true;

        System.out.println("Добро пожаловать в библиотеку!");
        System.out.println("Что выхотите сделать? (Выбор осуществляется вводом номера нужного действия");
        System.out.println("1. Добавить книгу");
        System.out.println("2. Показать список книг");
        System.out.println("3. Выбрать книгу");

        Library lib = new Library();
        while (key){
            switch (in.next()) {
                case "1":
                    lib.newBook();
                    key = false;
                    break;
                case "2":
                    lib.listBook();
                    key = false;
                    break;
                case "3":
                    lib.chooseBook();
                    key = false;
                    break;
                default:
                    System.out.println("Введите число от 1 до 3!");
            }
        }
    }

}

