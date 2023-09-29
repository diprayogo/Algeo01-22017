package menu;
import operators.*;
import java.util.*;


public class DeterminantMenu {
    private static Scanner scanner = new Scanner(System.in);
    public static void menu(){
        System.out.println();
        System.out.println("                          ANDA BERADA DI SUBMENU DETERMINAN");
        System.out.println("1. Matrix Segitiga");
        System.out.println("2. Determinan Kofaktor");
        
        boolean inputValid = false;
        double det = 0 ;
        int method = 0  ;
        while (!inputValid){
            System.out.print("Pilih Metode penyelesaian :");
            try {
                method = scanner.nextInt();
            } catch (Exception e) {
                e.printStackTrace();
            }
            switch (method) {
                case 1:
                    inputValid = true;
                    det = operators.Matrix.detMatrixSegitiga(Matrix.readMatSquare());
                    break;
                    
                case 2:
                    inputValid = true;
                    det = operators.Matrix.detKofaktor(Matrix.readMatSquare());
                    break;
                default:
                    System.out.println("Input tidak valid. Mohon hanya masukkan 1 atau 2.\n");
            }
        }
        if (inputValid){
            System.out.print("Determinan: ");
            System.out.printf("%.4f", det);
            System.out.println("\n");
        }
    }  
}
