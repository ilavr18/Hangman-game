package game;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    static Scanner scanner = new Scanner(System.in);
    static Random rand = new Random();

    static String[] gallows = {


            "       _____\n"
                    +  "      |\n"
                    +  "      |\n"
                    +  "      |\n"
                    +  "      |\n"
                    + "     _|_\n"

            ,

            "       _____\n"
                    +  "      |     |\n"
                    +  "      |\n"
                    +  "      |\n"
                    +  "      |\n"
                    +  "      |\n"
                    + "     _|_\n"

            ,

            "       _____\n"
                    +  "      |     |\n"
                    +  "      |     O\n"
                    +  "      |\n"
                    +  "      |\n"
                    +  "      |\n"
                    + "     _|_\n"

            ,

            "       _____\n"
                    +  "      |     |\n"
                    +  "      |     O\n"
                    +  "      |     |\n"
                    +  "      |\n"
                    +  "      |\n"
                    + "     _|_\n"

            ,

            "       _____\n"
                    +  "      |     |\n"
                    +  "      |     O\n"
                    +  "      |    /|\n"
                    +  "      |\n"
                    +  "      |\n"
                    + "     _|_\n"

            ,

            "        _____\n"
                    +  "      |     |\n"
                    +  "      |     O\n"
                    +  "      |    /|\\\n"
                    +  "      |\n"
                    +  "      |\n"
                    + "     _|_\n"

            ,

            "       _____\n"
                    +  "      |     |\n"
                    +  "      |     O\n"
                    +  "      |    /|\\\n"
                    +  "      |    /\n"
                    +  "      |\n"
                    + "     _|_\n"

            ,

            "       _____\n"
                    +  "      |     |\n"
                    +  "      |     O\n"
                    +  "      |    /|\\\n"
                    +  "      |    / \\\n"
                    +  "      |\n"
                    + "     _|_\n"

    };

    static String[] words = {"paper", "oxygen", "strength", "zombie", "sky", "sphinx", "keyboard",
            "zigzagging", "jizz"};

    static char[] word;     // Hidden word
    static char[] guessedLetters;     // Player's guessed letters
    static ArrayList<Character> usedLetters;     // Letters, which player used

    public static void startGame() {
        System.out.println("The hangman game. Guess the word by choosing one letter before you're hanged.");

        System.out.print("\nTo start a game press 1 > ");
        int game = scanner.nextInt();

        while(game == 1) {
            boolean win = false;
            int quantity = 0;     // Quantity of guessed letters

            word = getWordArr();
            guessedLetters = getGuessedArr(word);
            usedLetters = new ArrayList<Character>();

            for (int i = 0; i < gallows.length; ) {

                System.out.println("\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n");
                if(quantity == word.length)
                {
                    win = true;
                    System.out.print("  "); printGuessedArr(guessedLetters);
                    System.out.println("\n\n  Congratulations! You win!");
                    break;
                }

                char temp_c = 0;     // For checking if letter using not first time

                if(i == 0) {
                    System.out.print("  "); printGuessedArr(guessedLetters);
                    System.out.print("\n\n  Enter the letter > ");
                    temp_c = scanner.next().charAt(0);
                    if(usedLetters.contains(temp_c)) {
                        System.out.println("  Letter is already used!");
                        continue;
                    }
                    usedLetters.add(temp_c);
                    int n;
                    if((n = quantityOfRightChoices(usedLetters.get(usedLetters.size() - 1))) > 0) {
                        quantity += n;
                    } else {
                        i++;
                    }
                } else {
                    if(i == gallows.length - 1) {
                        System.out.println(gallows[i]);
                        System.out.println(); break;
                    }
                    System.out.println(gallows[i]);
                    System.out.print("  "); printGuessedArr(guessedLetters);
                    System.out.println();
                    System.out.print("\n  Used letters: "); printArrayList(usedLetters);
                    System.out.print("\n\n  Enter the letter > ");
                    temp_c = scanner.next().charAt(0);
                    if(usedLetters.contains(temp_c)) {
                        System.out.println("  Letter is already used!");
                        continue;
                    }
                    usedLetters.add(temp_c);
                    int n = 0;
                    if((n = quantityOfRightChoices(usedLetters.get(usedLetters.size() - 1))) > 0) {
                        quantity += n;
                    } else {
                        i++;
                    }
                }

            }

            if(win == false) {
                System.out.print("  Sadly you lost! You will be lucky next time! The word is \"");
                printHiddenWord(word); System.out.println("\".");
            }

            System.out.print("\nIf you want to play one more time press 1 > ");
            game = scanner.nextInt();
        }

        System.out.println("\nGoodbye!");
    }


    private static char[] getWordArr() {
        int max = words.length;
        int NOfWord = rand.nextInt(max);
        char[] arrWord = words[NOfWord].toCharArray();
        return arrWord;
    }

    private static char[] getGuessedArr(char[] word) {
        char[] array = new char[word.length];
        for(int i = 0; i < word.length; i++) {
            array[i] = '_';
        }
        return array;
    }

    private static void printGuessedArr(char[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    private static void printArrayList(ArrayList<Character> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
        }
    }

    private static int quantityOfRightChoices(Character letter) {
        int hasLetterInWord = 0;
        for (int i = 0; i < word.length; i++) {
            if(letter == word[i]) {
                guessedLetters[i] = letter;
                hasLetterInWord++;
            }
        }
        return hasLetterInWord;
    }

    private static void printHiddenWord(char[] word) {
        for (int i = 0; i < word.length; i++) {
            System.out.print(word[i]);
        }
    }
}