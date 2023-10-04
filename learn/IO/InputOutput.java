package learn.IO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import operators.Matrix;

public class InputOutput {
  private static Scanner scanner = new Scanner(System.in);

  // MATRIX TO STRING
  public static String matrixToString(Matrix Mat) {
    String MatString = "";
    int i, j, n, m;
    n = Mat.getCol();
    m = Mat.getRow();
    for (i = 0; i < m; i++) {
      for (j = 0; j < n; j++) {
        MatString += Mat.getELMT(i, j) + " ";
      }
      MatString += "\n";
    }
    return MatString;
  }
  
  // BICUBIC TEXT READER
  public static void readBicubicInputText(String toOutput, Matrix fMat, double a, double b) {
    int i, j;
    System.out.print("Masukkan nama file: ");
    String fileName = scanner.nextLine();

    try {
      File file = new File("../test/input" + fileName);
      Scanner fileReader = new Scanner(file);
      for (i = 0; i < 4; i++) {
        for (j = 0; j < 4; j++) {
          fMat.setELMT(4*i, j, (double) fileReader.nextDouble());
          toOutput += fMat.getELMT(4*i+j, 0) + " ";
        }
      }
      a = (double) fileReader.nextDouble();
      b = (double) fileReader.nextDouble();
      toOutput += "a = " + a + ", b = " + b + "\n";
      fileReader.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  // WRITE STRING TO TEXT AND SAVE TO DIR "test/output/<filename>.txt"
  public static void writeOutputText(String output) {
    System.out.print("Untuk menyimpan hasil, masukkan nama output file (dalam format \".txt\"): ");
    String FileName = scanner.nextLine();
    try {
      File file = new File("../test/output/" + FileName); // jangan lupa bikin file dulu di path ini
      FileWriter writer = new FileWriter(file); // ini konstruktornya param file bukan path
      writer.write(output);
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}