import java.util.Scanner;

import ./model/APIClient.java;

class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String genre;
        String main_character;

        System.out.println("What genre of story would you like?");
        genre = scan.nextLine();

        System.out.println("Give a brief descrption for the main character" +
                            "(for example, \"A pirate wearing a fedora\"): ");
        main_character = scan.nextLine();

        //generate_story(genre, main_character);
    }
}