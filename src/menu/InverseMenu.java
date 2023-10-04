package menu;
import operators.*;
import java.util.*;
import myUtils.*;

public class InverseMenu {
    private static Scanner scanner = new Scanner(System.in);

    public static void menu(){
        System.out.println();
        System.out.println("                          ANDA BERADA DI SUBMENU MATRIKS BALIKAN");
        
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

        Matrix inverseMatrix = new Matrix(1, 1); // will be re-initialized
        int method = 0;
        inputValid = false ; //  re-assign untuk validasi metode
        System.out.println("\n1. Ekspansi Kofaktor");
        System.out.println("2. Gauss-Jordan"); // Matriks Augmented bersama Matriks Identitas
        while (!inputValid){
            System.out.print("Pilih Metode penyelesaian :");
            try {
                method = scanner.nextInt();
                switch (method) {
                    case 1:
                        if (fromFile) inverseMatrix = myUtils.readMatrixFromFile().inverseEkspansiCofactor();
                        else inverseMatrix = Matrix.readMatSquare().inverseEkspansiCofactor();
                        inputValid = true ; 
                        break;
                    case 2:
                        if (fromFile) inverseMatrix = myUtils.readMatrixFromFile().inverseGaussJordan();
                        else inverseMatrix = Matrix.readMatSquare().inverseGaussJordan();
                        inputValid  = true ;
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

        // masukkan keterangan input dulu
        String inverseOutput = "Matriks Balikan dari matriks persegi di atas adalah: ";
        int i, j, n, m;
        n = inverseMatrix.getCol();
        m = inverseMatrix.getRow();
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                inverseOutput += (inverseMatrix.getELMT(i, j) + " ");
            }
            inverseOutput += "\n";
        }
        System.out.println(inverseOutput);
        myUtils.strToFile(inverseOutput);
    }  
}
