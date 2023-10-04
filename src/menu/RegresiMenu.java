package menu;

import operators.RegresiLinearBerganda;

import java.util.Scanner;

import myUtils.*;

public class RegresiMenu {
    private static Scanner scanner = new Scanner(System.in);

    public static void menu(){
        System.out.println();
        System.out.println("                   ANDA BERADA DI SUBMENU Regresi Linear Berganda");
    
        boolean inputValid = false, fromFile = true;
        int inputSrc = 0;
        System.out.println("1. Masukan dari file");
        System.out.println("2. Masukan dari keyboard ");
        while (!inputValid) {
            System.out.print("Pilih Sumber input : ");
            try {
                inputSrc = scanner.nextInt();
                switch (inputSrc) {
                case 1: // fromFile = true;
                    inputValid = true;
                    break;
                case 2:
                    inputValid = true;
                    fromFile = false;
                    break;
                default:
                    System.out.println("Input tidak valid. Mohon hanya masukkan 1 atau 2.\n");
                }
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("Input tidak valid. Mohon hanya masukkan 1 atau 2.\n");
            }
        }

        String output ;
        output = RegresiLinearBerganda.isRegresiFromFile(fromFile);
        myUtils.strToFile(output);
    }
}
