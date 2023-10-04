package operators;
<<<<<<< HEAD
// import operators.Matrix;
import java.lang.Math;

public class Bicubic {
=======

import java.io.*;
import java.lang.Math;
import java.util.*;
import myUtils.myUtils;

public class Bicubic {
  private static Scanner scanner = new Scanner(System.in);

>>>>>>> d39ac29d29d73cb72986734671a20996e4e241e5
  public static void main(String[] args){
    Matrix.printMatrix(getBicubicPolynomialMatrix());
    Matrix.printMatrix(getInverseBicubicPolynomialMatrix());
    Matrix.printMatrix(getImageMatrix());
    Matrix.printMatrix(getInverseBicubicPolynomialMatrix().mult(getImageMatrix()));
    Matrix m = new Matrix(2, 2);
    m.readMatrix(2, 2);
    Matrix.printMatrix(m);
    m.inverseGaussJordan();
    Matrix.printMatrix(m.inverseGaussJordan());
  }
  
  public static Matrix getBicubicPolynomialMatrix() {
    // Generate X in y = Xa, X is BicubicPolynomialMatrix
    // KAMUS LOKAL
    Matrix X = new Matrix(16, 16);
    /**
     * deriv for function and its derivatives iterator
     * x, y for each f(x,y)
     * i, j for a(i, j) constant
     */
    int deriv, x, y, i, j, row = 0, col;

    // ALGORTIMA
    // perlu cek Math.pow(0, 0) = 0 apakah benar? Possible bugs.
    // Decomposing from derives form, y, x (3 row decomposition): 4*2*2
    // (inner) Decomposing (per row) from j, i form (2 col decomposition): 4*4
    for (deriv = 0; deriv < 4; deriv++){
      for (y = 0; y <= 1; y++) {
        for (x = 0; x <= 1; x++) {
          col = 0;
          // Efficiently, code derivative (switch) cases here, but too much linear for-loops
          for (j = 0; j < 4; j++) {
            for (i = 0; i < 4; i++) {
              double val = 0;
              switch (deriv) {
                case 0:
                  val = 1; // no i, j to be derived and affecting coef aij
                  // case to make 0*aij = 0
                  if ((x == 0 && i != 0) || (y == 0 && j != 0)) {
                    val = 0;
                  } 
                  break;
                case 1:
                  val = i;
                  if (i == 0) val = 0; // turunannya 0
                  else {
                    val *= Math.pow(x, i-1) * Math.pow(y, j);
                  }
                  break;
                case 2:
                  val = j;
                  if (j == 0) val = 0; // turunannya 0
                  else {
                    val *= Math.pow(x, i) * Math.pow(y, j-1);
                  }
                  break;
                case 3:
                  val = i*j;
                  if (i == 0 || j == 0) val = 0; // turunannya 0
                  else {
                    val *= Math.pow(x, i-1) * Math.pow(y, j-1);
                  }
                  break;
              }
              X.setELMT(row, col, val);
              col++;
            }
          }
          row++;
        }
      }
    }
    return X;
  }

  public static Matrix getInverseBicubicPolynomialMatrix() {
    // generate X^-1 for a = X^-1 y
    return getBicubicPolynomialMatrix().inverseGaussJordan();
  }

  public static Matrix getImageMatrix() {
    // generate D for y = DI, I not identity matrix, but image value representation
    // KAMUS LOKAL
    Matrix D = new Matrix(16, 16);
    /**
     * deriv for function and its derivatives iterator
     * x, y for each f(x,y)
     * i, j for a(i, j) constant
     */
    int deriv, x, y, i, j, row = 0, col;

    // ALGORTIMA
    // perlu cek Math.pow(0, 0) = 0 apakah benar? Possible bugs.
    // Decomposing from derives form, y, x (3 row decomposition): 4*2*2
    // (inner) Decomposing (per row) from j, i form (2 col decomposition): 4*4
    for (deriv = 0; deriv < 4; deriv++){
      for (y = 0; y <= 1; y++) {
        for (x = 0; x <= 1; x++) {
          col = 0;
          // Efficiently, code derivative (switch) cases here, but too much linear for-loops
          for (j = -1; j <= 2; j++) {
            for (i = -1; i <= 2; i++) {
              double val = 0;
              switch (deriv) {
                case 0: // normal value
                  if (x == i && y == j) val = 1;
                  break;
                case 1: // derived by x
                  if (x-1 == i && y == j) val = -0.5;
                  else if (x+1 == i && y == j) val = 0.5;
                  break;
                case 2: // derived by y
                  if (x == i && y-1 == j) val = -0.5;
                  else if (x == i && y+1 == j) val = 0.5;
                  break;
                case 3: // derived by xy
                  if (x+1 == i && y+1 == j) val = 0.25;
                  else if (x-1 == i && y == j) val = -0.25;
                  else if (x == i && y-1 == j) val = -0.25;
                  else if (x == i && y == j) val = 0.25; // gambar dan persamaan tidak sesuai, lalu pakai yang mana? coba cek referensi mssc
                  break;
              }
              D.setELMT(row, col, val);
              col++;
            }
          }
          row++;
        }
      }
    }
    return D;
  }

  public static Matrix getImageValue(Matrix I) {
    // generate a = X^-1 DI, input from I the value of 16 points (squared) images in size of 16x1
    // get image value of y
    Matrix imageValue = new Matrix(16, 1); // return in (also) size of 16x1
    imageValue = getInverseBicubicPolynomialMatrix().mult(getImageMatrix().mult(I));
    return imageValue;
  }

  public static double getBicubicFunctionValue(Matrix fMat, double x, double y) {
    int i, j;
    // read nilai matrix f fx fy fxy nya dulu, this must be 16 x 1
    // 4 x 4 dibaca 16 x 1 biar bisa langsung dikali
    // Matrix fAsSquareValue = new Matrix(16, 1);
    Matrix fMatARow = new Matrix(16, 1);
    for (i = 0; i < 4; i++) {
      for (j = 0; j < 4; j++) {
        fMatARow.setELMT(4*i + j, 0, fMat.getELMT(i, j));
      }
    }
    Matrix bicubicCoef = getInverseBicubicPolynomialMatrix().mult(fMatARow);
    double fResult = 0;
    for (j = 0; j < 4; j++) {
      for (i = 0; i < 4; i++) {
        System.out.printf("DISINIIIIIII %d \n", 4*i+j);
        fResult += bicubicCoef.getELMT(4*i + j, 0) * Math.pow(x, i) + Math.pow(j, y);
      }
    }
    return fResult;
  }
<<<<<<< HEAD
=======

  public void menuBicubic() {
    System.out.println();
    System.out.println("                          ANDA BERADA DI SUBMENU INTERPOLASI BICUBIC SPLINE");

    Matrix fMat = new Matrix(4, 4);
    boolean inputValid = false, fromfile = true;
    int inputSrc = 0;
    double a = 1, b = 1;
    System.out.println("1. Masukan dari file");
    System.out.println("2. Masukan dari keyboard ");
    while (!inputValid){
        System.out.print("Pilih Sumber input : ");
        try {
            inputSrc = scanner.nextInt();
            switch (inputSrc) {
                case 1: // fromfile = true;
                    inputValid = true;
                    break; 
                case 2:
                    inputValid = true;
                    fromfile = false;
                    break;
                default:
                    System.out.println("Input tidak valid. Mohon hanya masukkan 1 atau 2.\n");
            }
        }
        catch (Exception e) {
            scanner.nextLine(); 
            System.out.println("Input tidak valid. Mohon hanya masukkan 1 atau 2.\n");
        }
    }

    if (fromfile) {
      fMat = myUtils.readMatrixFromFile();
      a = fMat.getELMT(4, 0);
      b = fMat.getELMT(4, 1);
      fMat.setRow(4);
      fMat.inverseGaussJordan();
      Matrix.printMatrix(fMat);
      // System.out.println("HALLO");
    } else {
      fMat.readMatrix(4, 4);
      a = scanner.nextInt();
      b = scanner.nextInt();
      fMat = fMat.inverseGaussJordan();
    }

    if (inputValid){
        try {
          System.out.println("APAKAH ADA SI DINI");
          double result = getBicubicFunctionValue(fMat, a, b);
          String resultString = String.format("f(%f, %f) = %f", a, b, result);
          System.out.println(resultString);
          myUtils.strToFile(resultString);
          System.out.println("\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  }
>>>>>>> d39ac29d29d73cb72986734671a20996e4e241e5
}