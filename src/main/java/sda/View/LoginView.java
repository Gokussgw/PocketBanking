package sda.View;

import java.util.Scanner;

public class LoginView {
    private Scanner scanner;

    public LoginView() {
        this.scanner = new Scanner(System.in);
    }

    public int getCustomerId() {
        System.out.print("Enter Customer ID: ");
        return scanner.nextInt();
    }

    public String getPassword() {
        System.out.print("Enter Password: ");
        return scanner.next();
    }

    public boolean showLoginResult(boolean success) {
        if (success) {
            System.out.println("Login successful.");
            return true;
        } else {
            System.out.println("Login failed.");
            return false;
        }
    }

    public void close() {
        scanner.close();
    }
}