package json.placeholder.demo;

import json.placeholder.demo.cli.UsersCLICommand;

import java.util.Arrays;
import java.util.Scanner;

public class JsonServiceCLIMain {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        UsersCLICommand usersCLICommand = new UsersCLICommand();

        System.out.println("Welcome in JSON PlaceHolder DEMO");
        System.out.println("--------------------------------");
        System.out.println("Supported commands are:");
        System.out.println("users {id} -> return a user and his related posts");
        System.out.println("exit -> terminate application");
        System.out.println("--------------------------------");


        while(true) {
            String commandLine = scanner.nextLine();
            String[] commandSplit = commandLine.split(" ");
            String command = commandSplit[0];


            switch (command) {
                case "users":
                    String[] commandArgs = Arrays.copyOfRange(commandSplit,1,commandSplit.length);
                    usersCLICommand.execute(commandArgs);
                    break;
                case "exit":
                    System.out.println("Application is exiting! Hasta Pronto!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Command '" + command + "' is not supported by this application.");

            }
        }
    }
}
