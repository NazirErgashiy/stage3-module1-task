package com.mjc.school;

import com.mjc.school.controller.Controller;
import com.mjc.school.repository.impl.dao.DataSource;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        Controller mainController = new Controller();
        DataSource repository = new DataSource();
        repository.setDefaultNewsAndAuthors();
        boolean exit = false;

        Scanner reader = new Scanner(System.in);
        while (!exit) {

            System.out.println("Enter the number of operation:");
            System.out.println("1 - Get all news.");
            System.out.println("2 - Get news by id.");
            System.out.println("3 - Create news.");
            System.out.println("4 - Update news.");
            System.out.println("5 - Remove news by id.");
            System.out.println("0 - Exit.");

            try {
                int input = reader.nextInt();

                switch (input) {
                    case 1:
                        try {
                            System.out.println(mainController.actionGetAll().toString());
                        } catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 2:
                        try {
                            System.out.println(mainController.actionGetById(getUserInputAsInt("Input id:", reader)).toString());
                        } catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 3:
                        try {
                            System.out.println(mainController.actionCreate(getUserInputAsString("Input title:", reader),
                                    getUserInputAsString("Input content", reader),
                                    getUserInputAsInt("Input author id:", reader)).toString());
                        } catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 4:
                        try {
                            System.out.println(mainController.actionUpdate(getUserInputAsString("Input title:", reader),
                                    getUserInputAsString("Input content", reader),
                                    getUserInputAsInt("Input author id:", reader)).toString());
                        }catch (RuntimeException e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 5:
                        try {
                            System.out.println(mainController.actionDelete(getUserInputAsInt("Input id:", reader)));
                        }catch (RuntimeException e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 0:
                        exit = true;
                        break;
                    default:
                        System.out.println("Unsupported operation");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static int getUserInputAsInt(String message, Scanner reader) {
        System.out.println(message);
        try {
            return reader.nextInt();
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    private static String getUserInputAsString(String message, Scanner reader) {
        System.out.println(message);
        try {
            return reader.next();
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }
}
