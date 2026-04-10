package project;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import org.json.simple.*;

public class CavazosExample {

  // history stores all commands that have been issued
  public static ArrayList<String> history = new ArrayList<String>();

  // undoneHistory stores commands that were undone
  public static ArrayList<String> undoneHistory = new ArrayList<String>();

  public static void main(String[] args) {
    String fileName = "/Users/youjia/Documents/GitHub/cis055-assignment-general-cavazos-commander/project/target/classes/project/commands.json";

    // read commands
    JSONArray commandJsonArray = JSONFile.readArray(fileName);
    String[] commandArray = getCommandArray(commandJsonArray);

    Scanner scan = new Scanner(System.in);
    char command;
    boolean userQuit = false;

    while (!userQuit) {
      printMenu();

      System.out.print("Enter a command: ");
      command = scan.nextLine().toLowerCase().charAt(0);

      switch (command) {
        case 'i':
          System.out.print("How many commands do you want to generate? ");
          int numCommands = Integer.parseInt(scan.nextLine());

          // generate an array of random commands
          String[] issuedCommands = randomCommand(commandArray, numCommands);

          // once new commands are issued, previous redo history is no longer valid
          undoneHistory.clear();

          System.out.println("Issued Commands:");

          // print each generated command and add it to history
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
          // check if there is at least one command to undo
          if (history.size() > 0) {
            String undoneCommand = history.remove(history.size() - 1);
            undoneHistory.add(undoneCommand);
            System.out.println("Undid Command: " + undoneCommand);
          } else {
            System.out.println("No command to undo.");
          }
          break;

        case 'r':
          // check if there is a command available to redo
          if (undoneHistory.size() > 0) {
            String redoneCommand = undoneHistory.remove(undoneHistory.size() - 1);
            history.add(redoneCommand);
            System.out.println("Redid Command: " + redoneCommand);
          } else {
            System.out.println("No command to redo.");
          }
          break;

        case 'q':
          System.out.println("Quitting application...");
          userQuit = true;
          break;

        default:
          System.out.println("Invalid command.");
      }

      System.out.println();
    }

    scan.close();
  }

  // randomly issue commands from General Cavazos
  public static String[] randomCommand(String[] commandArray, int numCommands) {
    Random rand = new Random();

    // result array stores the generated commands
    String[] result = new String[numCommands];

    // randomly select commands from commandArray
    for (int i = 0; i < numCommands; i++) {
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

    // get names from json array
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