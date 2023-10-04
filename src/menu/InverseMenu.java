package menu;
import operators.*;
import java.util.*;
import myUtils.*;

public class InverseMenu {
    private static Scanner scanner = new Scanner(System.in);

    public static void menu(){
        System.out.println();
        System.out.println("                          ANDA BERADA DI SUBMENU MATRIKS BALIKAN");
        
        boolean inputValid = false, fromFile;
        fromFile = myUtils.inputSrcValidation();

        Matrix inverseMatrix = new Matrix(1, 1); // will be re-initialized
        int method = 0;

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
        System.out.println("\n"); // output .txt juga
        
    }  
}
