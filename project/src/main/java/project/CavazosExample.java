package project;

import java.util.Random;
import java.util.Scanner;
import org.json.simple.*;

public class CavazosExample {

  public static void main(String[] args) {
    String fileName = "/Users/youjia/Documents/GitHub/cis055-assignment-general-cavazos-commander/project/target/classes/project/commands.json";

    // read coammands
    JSONArray commandJSONArray = JSONFile.readArray(fileName);
    String[] commandArray = getCommandArray(commandJSONArray);

    Scanner scan = new Scanner(System.in);
    char command;

    while (true) {
      printMenu();

      System.out.print("Enter a command: ");
      command = scan.nextLine().toLowerCase().charAt(0);

      if (command == 'q') {
        System.out.println("Quitting application...");
        break;
      }

      switch (command) {
        case 'i':
          System.out.println("Issue command not implemented yet.");
          break;
        case 'l':
          System.out.println("List commands not implemented yet.");
          break;
        case 'u':
          System.out.println("Undo not implemented yet.");
          break;
        case 'r':
          System.out.println("Redo not implemented yet.");
          break;
        default:
          System.out.println("Invalid command.");
      }

      System.out.println();
    }

    scan.close();
  }

  // randomly issue commands from General Cavazos
  public static void randomCommand(String[] commandArray, int numCommand) {
    Random rand = new Random();
    System.out.printf("Number\tCommand\n");
    System.out.printf("------\t---------------\n");
    for (int i = 0; i < numCommand; i++) {
      int randIndex = rand.nextInt(commandArray.length);
      System.out.printf("%04d\t%s\n", i, commandArray[randIndex]);
    }
  }

  // print command array
  public static void print(String[] commandArray) {
    System.out.printf("Number\tCommand\n");
    System.out.printf("------\t---------------\n");
    for (int i = 0; i < commandArray.length; i++) {
      System.out.printf("%04d\t%s\n", i, commandArray[i]);
    }
  }

  // get array of commands
  public static String[] getCommandArray(JSONArray commandArray) {
    String[] arr = new String[commandArray.size()];

    // get names from json object
    for (int i = 0; i < commandArray.size(); i++) {
      String command = commandArray.get(i).toString();
      arr[i] = command;
    }
    return arr;
  }

  public static void printMenu() {
    System.out.println("------------------------------------------------------------");
    System.out.println("General Cavazos Commander App");
    System.out.println("------------------------------------------------------------");
    System.out.println("i      Issue a command");
    System.out.println("l      List all of the commands");
    System.out.println("u      Undo the last command that was issued");
    System.out.println("r      Redo the last command that was issued");
    System.out.println("q      Quit");
    System.out.println("------------------------------------------------------------");
  }
}
