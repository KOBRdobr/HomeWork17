package ru.zinnurov;

import java.io.*;
import java.util.Scanner;


/**
 * @author Nail Zinnurov
 * cobratms@gmail.com
 * on 20.09.2018
 */

public class Library {

    File libraryDir = new File("library");

    public void newBook() throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("Какого жанра книга?");
        String genre = in.nextLine();

        String[] files = libraryDir.list();
        boolean check = false;
        for(String search : files) {
            if(search.equalsIgnoreCase(genre)) {
                check = true;
                break;
            }
        }

        if (!check) {
            File newFile = new File("library\\" + genre + ".txt");
            newFile.createNewFile();
        }
        String[] newBook = new String[3];
        Scanner in2 = new Scanner(System.in);

        System.out.println("Введите назваение книги: ");
        newBook[0] = in2.nextLine();

        System.out.println("Введите имя автора: ");
        newBook[1] = in2.nextLine();

        System.out.println("Введите год издания: ");
        newBook[2] = in2.nextLine();
        String book = newBook[0] + "_" + newBook[1] + "_" + newBook[2];
        byte[] bookInBytes = book.getBytes();


        if(!checkAvailability(genre, book)) {
            try (FileWriter stream = new FileWriter("library\\" + genre + ".txt")) {
                BufferedWriter bw = new BufferedWriter(stream);
                for (Byte eachByte : bookInBytes) {
                    bw.write(eachByte);
                }
                bw.write((byte) '\n');
                bw.close();
            }
        }
        else System.out.println("Такая книга уже есть!");

    }

    private boolean checkAvailability(String genre, String book) {
        boolean check = false;
        try(FileReader stream = new FileReader("library\\" + genre + ".txt")) {
            int symbol, k = 0;
            char[] file = new char[book.length()];
            while ((symbol = stream.read()) != -1 && k != file.length) {
                file[k] = (char) symbol;
                k++;
            }

            String genreFile =new String(file);
            String[] books = genreFile.split("\n");

            for(int i = 0; i < books.length; i++) {
                if(book.equalsIgnoreCase(books[i])) {
                    System.out.println("Такая книга уже есть!");
                    check = true;
                    break;
                }
            }


        } catch (IOException e) {
            System.out.println("Пролетел");
        }
        return check;
    }

    public void listBook() {
    }

    public void chooseBook() {
    }
}
