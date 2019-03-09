package atm;

import atm.model.UserModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ATMProgram {

    static UserModel current_user;

    private static String ADMIN_USERNAME = "admin";
    private static String ADMIN_PASSWORD = "000";

    public static void main(String[] args) {
        // Loads the CSV files as java objects.
        BankDatabase centralDatabase = new BankDatabase();

        Scanner scanInput = new Scanner(System.in);
        System.out.println("Please enter your user name and press enter");
        String enteredUsername = scanInput.nextLine();
        System.out.println("Please enter your password and press enter");
        String enteredPassword = scanInput.nextLine();
        if (enteredUsername.equals(ADMIN_USERNAME) && enteredPassword.equals(ADMIN_PASSWORD)) {
            //do BankManager stuff
            System.out.println("LOGGED IN AS BANK MANAGER");

            ATMSim simulation = new ATMSim();
            simulation.runATM(centralDatabase);


        } else if (centralDatabase.tryLogin(enteredUsername, enteredPassword) != null) {
            System.out.println("LOGGED IN!");

            current_user = centralDatabase.usersByUsername.get(enteredUsername);

            ATMSim simulation = new ATMSim();
            simulation.runATM(centralDatabase, current_user);

        }
        scanInput.close();
    }

}
