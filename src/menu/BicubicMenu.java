package menu;
import operators.*;
import java.util.Scanner;

import myUtils.*;

public class BicubicMenu {
    private static Scanner scanner = new Scanner(System.in);

    public static void menu() {
        System.out.println();
        System.out.println("                          ANDA BERADA DI SUBMENU INTERPOLASI BICUBIC SPLINE");
        String bicubicOutput = "";
        Matrix fMat = new Matrix(16, 1);
        double a = 0, b = 0;
        
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
        
        if (!fromFile) {
            System.out.println("Masukkan jumlah titik konfigurasi nilai fungsi dan turunan berarah di sekitarnya, ");
            System.out.print("diikuti dengan nilai a dan b untuk mencari taksiran f(a, b): \n");
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    fMat.setELMT(4*i+j, 0, scanner.nextDouble());
                    bicubicOutput += fMat.getELMT(4*i+j, 0) + " ";
                    }
                    bicubicOutput += "\n";
            }
            a = scanner.nextDouble();
            b = scanner.nextDouble();
            bicubicOutput += "a = " + a + ", b = " + b + "\n";
        } else if (fromFile) {
            Matrix matInput = new Matrix(0, 0);
            matInput = myUtils.readMatrixFromFile();
            a = matInput.getELMT(matInput.getRow() - 1, 0);
            b = matInput.getELMT(matInput.getRow() - 1, 1);
            matInput.setRow(matInput.getRow() - 1);

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    fMat.setELMT(4*i+j, 0, matInput.getELMT(i, j));
                    bicubicOutput += fMat.getELMT(4*i+j, 0) + " ";
                }
                bicubicOutput += "\n";
            }
        }

        double taksir = Bicubic.getBicubicFunctionValue(fMat, a, b);
        // bicubicOutput += "Nilai taksirannya adalah ";
        bicubicOutput += "f(" + a + ", " + b + ") = " + taksir;
        System.out.println(bicubicOutput);
        // auto output ke txt juga
        myUtils.strToFile(bicubicOutput);
    }
}
