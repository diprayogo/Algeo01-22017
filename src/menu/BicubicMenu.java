package menu;
import operators.*;
import java.util.Scanner;

import IO.InputOutput;
import myUtils.myUtils;
import operators.Matrix;

public class BicubicMenu {
    private static Scanner scanner = new Scanner(System.in);

    public static void menu() {
        System.out.println("                          ANDA BERADA DI SUBMENU INTERPOLASI BICUBIC SPLINE");
        String bicubicOutput = "";
        Matrix fMat = new Matrix(16, 1);
        double a = 0, b = 0;
        boolean fromFile ;
        fromFile = myUtils.inputSrcValidation();
        
        
        if (fromFile) {
            System.out.print("Masukkan jumlah titik konfigurasi nilai fungsi dan turunan berarah di sekitarnya,\n diikuti dengan nilai a dan b untuk mencari taksiran f(a, b): \n");
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
        } else if (!fromFile ) {
            InputOutput.readBicubicInputText(bicubicOutput, fMat, a, b);
        }
        

        double taksir = Bicubic.getBicubicFunctionValue(fMat, a, b);
        // bicubicOutput += "Nilai taksirannya adalah ";
        bicubicOutput += "f(" + a + ", " + b + ") = " + taksir;
        System.out.println(bicubicOutput);
        // auto output ke txt juga
        InputOutput.writeOutputText(bicubicOutput);
    }
}
