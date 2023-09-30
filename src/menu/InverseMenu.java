package menu;
import operators.*;
import java.util.*;


public class InverseMenu {
    private static Scanner scanner = new Scanner(System.in);
    public static void menu(){
        System.out.println();
        System.out.println("                          ANDA BERADA DI SUBMENU MATRIKS BALIKAN");
        System.out.println("1. Ekspansi Kofaktor");
        System.out.println("2. Gauss-Jordan Matriks Augmented bersama Matriks Identitas");
        System.out.print("Pilih Metode penyelesaian :");

        boolean inputValid = true;
        Matrix inverseMatrix = new Matrix(1, 1); // will be re-initialized
        int method = 0;
        try {
            method = scanner.nextInt();
        } catch (Exception e) {
            e.printStackTrace();
        }
        switch (method) {
            case 1:
                inverseMatrix = Matrix.readMatSquare().inverseEkspansiCofactor();
                break;
            case 2:
                inverseMatrix = Matrix.readMatSquare().inverseGaussJordan();
                break;
            default:
                inputValid = false;
                System.out.println("Input tidak valid. Mohon hanya masukkan 1 atau 2.\n");
        }
        if (inputValid){
            System.out.print("Matriks Balikan: ");
            Matrix.printMatrix(inverseMatrix);
            System.out.println("\n");
        }
    }  
}
