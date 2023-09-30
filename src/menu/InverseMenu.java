package menu;
import operators.*;
import java.util.*;
import IO.InputOutput;

public class InverseMenu {
    private static Scanner scanner = new Scanner(System.in);
    
    public static void menu(){
        Matrix inverseMatrix = new Matrix(1, 1); // will be re-initialized
        boolean inputValid = true;
        int method = 0;
        int inputMode = 0;

        System.out.println();
        System.out.println("                          ANDA BERADA DI SUBMENU MATRIKS BALIKAN");
        System.out.println("1. Ekspansi Kofaktor");
        System.out.println("2. Gauss-Jordan"); // Matriks Augmented bersama Matriks Identitas
        System.out.print("Pilih Metode penyelesaian: ");

        try {
            method = scanner.nextInt();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("                          PILIH MODE MASUKAN");
        System.out.println("""
          1. Keyboard input
          2. File input
          Masukkan pilihan mode input: """);
        try {
          inputMode = scanner.nextInt();
        } catch (Exception e) {
          e.printStackTrace();
        }
    
        if (inputMode == 1) {
          System.out.println(input);
          for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
              fMat.setELMT(4*i+j, 0, scanner.nextDouble());
            }
          }
          a = scanner.nextDouble();
          b = scanner.nextDouble();
        } else if (inputMode == 2) {
          InputOutput.readBicubicInputText(bicubicOutput, fMat, a, b);
        } else {
          inputValid = false;
          System.out.println("Input tidak valid, hanya bisa memilih mode 1 atau 2.");
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
            String inverseOutput = "";
            try {
                // masukkan keterangan input dulu, yaitu berupa matrix
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
                // output .txt juga
                inverseOutput = InputOutput.matrixToString(inverseMatrix) + "Matriks Balikan dari matriks persegi di atas adalah: " + inverseOutput;
            } catch (NullPointerException e) {
                inverseOutput = "Matriks persegi di atas tidak memiliki invers";
                System.out.println(inverseOutput);
            }
            InputOutput.writeOutputText(inverseOutput);
        }
    }  
}
