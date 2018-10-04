package ru.zinnurov;

import java.io.*;
import java.util.Scanner;

/**
 * @author Nail Zinnurov
 * cobratms@gmail.com
 * on 03.10.2018
 */

public class Library {

    private static final String LIBRARY_DIR_PATH = "Library";
    File lib = new File(LIBRARY_DIR_PATH);

    public void viewListBooks() throws IOException {


        if (lib.list().length != 0) {

            String[] filesPath = lib.list();
            for (String path : filesPath) {

                try (FileReader file = new FileReader(LIBRARY_DIR_PATH + "//" + path)) {
                    int symbol;
                    StringBuilder bookBilder = new StringBuilder();

                    while ((symbol = file.read()) != -1) {
                        bookBilder.append((char) symbol);
                    }

                    String books = bookBilder.toString();

                    System.out.println( "Genre: " + path.substring(0, (path.length() - 4)));
                    System.out.println(books.replace('_', ' '));

                } catch (IOException e) {
                    throw new IOException("Write Error");
                }
            }
        } else System.out.println("Library is empty!");

    }

    public void viewListGenres() {

        if (lib.list().length != 0) {
            System.out.println("Genres: ");
            String[] genres = getGenres();

            for (String genre : genres) {
                System.out.println(genre);
            }
        } else System.out.println("Library is empty!");
    }

    private String[] getGenres() {
        String[] filesPath = lib.list();
        String[] genre = new String[filesPath.length];

        for(int i = 0; i < filesPath.length; i++) {
            genre[i] = (filesPath[i].substring(0, (filesPath[i].length() - 4)));
        }

        return genre;
    }

    public void addBook() throws IOException {
        System.out.println("What genre do you want to add a book?");

        Scanner add = new Scanner(System.in);
        String genre = add.nextLine();
        checkAvailabilityGenre(genre);

        System.out.println("Name book: ");
        String nameBook = add.nextLine();

        System.out.println("Author name: ");
        String nameAuthor = add.nextLine();

        System.out.println("Writing year: ");
        String writingYear = add.nextLine();

        String book = "\"" + nameBook + "\"_" + nameAuthor + "_" + writingYear + "\n";
        char[] chars = book.toCharArray();

        try(FileWriter newBook = new FileWriter(LIBRARY_DIR_PATH + "//" + genre + ".txt", true)) {
            for(char eachChar : chars) {
                newBook.write(eachChar);
            }
            newBook.flush();
        } catch (IOException e) {
            throw new IOException("Output error");
        }



    }

    private void checkAvailabilityGenre(String genre) throws IOException {

        String[] genres = getGenres();
        boolean checkAvailability = false;

        for(String availability : genres ) {
            if(genre.equalsIgnoreCase(availability)) {
                checkAvailability = true;
            }
        }

        if(!checkAvailability) {
            createNewFile(genre);
        }
    }

    private void createNewFile(String newGenre) throws IOException {
        try {
            File newFile = new File(LIBRARY_DIR_PATH + "//" + newGenre + ".txt");
            newFile.createNewFile();
        } catch (IOException e) {
            throw new IOException("Error ctreate file");
        }

    }

    public void deleteBook() throws IOException {
        if (lib.list().length != 0) {
            System.out.println("What book do you want to remove?");
            Scanner deleteBook = new Scanner(System.in);
            String selectedBook = deleteBook.nextLine();
            boolean bookExist = false;
            String[] filesPath = lib.list();

            for (String path : filesPath) {
                StringBuilder bookBilder = new StringBuilder();
                try (FileReader file = new FileReader(LIBRARY_DIR_PATH + "//" + path)) {
                    int symbol;

                    while ((symbol = file.read()) != -1) {
                        bookBilder.append((char) symbol);
                    }

                } catch (IOException e) {
                    throw new IOException("Write Error");
                }

                String[] allFile = bookBilder.toString().split("\n");
                FileWriter writer = new FileWriter(LIBRARY_DIR_PATH + "//" + path);
                for(int i = 0; i < allFile.length; i++) {
                    if(!selectedBook.equalsIgnoreCase(allFile[i].substring(1, (allFile[i].indexOf('_') - 1)))) {
                        char[] chars = allFile[i].toCharArray();
                        try {
                            for(char eachChar : chars) {
                                writer.write(eachChar);
                            }
                            writer.write("\n");
                            writer.flush();
                        } catch (IOException e) {
                            throw new IOException("Output error");
                        }
                    } else bookExist = true;
                }
                writer.close();
            }
            if (bookExist) System.out.println("Successfully");
            else System.out.println("The book is not in the library");
        }
        else System.out.println("Library is empty");
    }
}
