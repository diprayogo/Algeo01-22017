package menu;
import java.util.*;
import java.io.IOException;
import operators.*;
import myUtils.myUtils;

public class InverseMenu {
    private static Scanner scanner = new Scanner(System.in);

    public static void menu(){
        System.out.println();
        System.out.println("                          ANDA BERADA DI SUBMENU MATRIKS BALIKAN");
        
        // KAMUS LOKAL
        Matrix inverseMatrix = new Matrix(0, 0);
        boolean inputValid = false, fromfile = true;
        int inputSrc = 0 , method = 0  ;

        // ALGORITMA
        // Valdasi input
        System.out.println("1. Masukan dari file");
        System.out.println("2. Masukan dari keyboard ");
        while (!inputValid){
            System.out.print("Pilih sumber input : ");
            try {
                inputSrc = scanner.nextInt();
                switch (inputSrc) {
                    case 1:
                        inputValid = true;
                        break;            
                    case 2:
                        inputValid = true;
                        fromfile = false;
                        break;
                    default:
                        System.out.println("Input tidak valid. Mohon hanya masukkan 1 atau 2.\n");
                }
            } catch (Exception e) {
                scanner.nextLine(); 
                System.out.println("Input tidak valid. Mohon hanya masukkan 1 atau 2.\n");
            }
        }
        inputValid = false ; 

        System.out.println("\n1. Ekspansi Kofaktor");
        System.out.println("2. Gauss-Jordan");
        while (!inputValid){
            System.out.print("Pilih Metode penyelesaian: ");
            try {
                method = scanner.nextInt();
                switch (method) {
                    case 1:
                        if (fromfile) inverseMatrix = myUtils.readMatrixFromFile().inverseEkspansiCofactor();
                        else inverseMatrix = Matrix.readMatSquare().inverseEkspansiCofactor();
                        inputValid = true;
                        break;
                    case 2:
                        if (fromfile) inverseMatrix = myUtils.readMatrixFromFile().inverseGaussJordan();
                        else inverseMatrix = Matrix.readMatSquare().inverseGaussJordan();
                        inputValid = true;
                        break;
                    default:
                        System.out.println("Input tidak valid. Mohon hanya masukkan 1 atau 2.\n");
                }
            }
            catch (java.util.InputMismatchException e) {
                scanner.nextLine(); 
                System.out.println("Input tidak valid. Mohon hanya masukkan 1 atau 2.\n");
            }
        }

        if (inputValid){
            try {
                if (inverseMatrix == null) {
                    System.out.println("Matriks persegi di atas tidak memiliki invers");
                } else if (inverseMatrix.getRow() == 0) {
                    System.out.println("Matriks tersebut bukan matriks persegi ataupun matriks augmented yang berbentuk persegi");
                } else {
                    System.out.println("Matriks balikan: ");
                    Matrix.printMatrix(inverseMatrix);
                    myUtils.matrixToFile(inverseMatrix);
                    System.out.println("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
