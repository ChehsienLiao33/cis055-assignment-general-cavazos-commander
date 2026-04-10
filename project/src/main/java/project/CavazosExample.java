package project;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import org.json.simple.*;

public class CavazosExample {

  public static ArrayList<String> history = new ArrayList<String>();
  public static ArrayList<String> undoneHistory = new ArrayList<String>();

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
          System.out.print("How many commands do you want to generate? ");
          int num = Integer.parseInt(scan.nextLine());

          String[] issuedCommands = randomCommand(commandArray, num);

          undoneHistory.clear();

          System.out.println("Issued Commands:");
          for (int i = 0; i < issuedCommands.length; i++) {
            System.out.println(issuedCommands[i]);
            history.add(issuedCommands[i]);
          }

          break;
        case 'l':
          System.out.println("----- List of all commands -----");
          print(commandArray);
          break;
        case 'u':
          if (history.size() > 0) {
            String undoneCommand = history.remove(history.size() - 1);
            undoneHistory.add(undoneCommand);
            System.out.println("Undid Command: " + undoneCommand);
          } else {
            System.out.println("No command to undo.");
          }
          break;
        case 'r':
          if (undoneHistory.size() > 0) {
            String redoneCommand = undoneHistory.remove(undoneHistory.size() - 1);
            history.add(redoneCommand);
            System.out.println("Redid Command: " + redoneCommand);
          } else {
            System.out.println("No command to redo.");
          }
          break;
        default:
          System.out.println("Invalid command.");
      }

      System.out.println();
    }

    scan.close();
  }

  // randomly issue commands from General Cavazos
  public static String[] randomCommand(String[] commandArray, int numCommand) {
    Random rand = new Random();
    String[] result = new String[numCommand];

    for (int i = 0; i < numCommand; i++) {
      int randIndex = rand.nextInt(commandArray.length);
      result[i] = commandArray[randIndex];
    }

    return result;
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
