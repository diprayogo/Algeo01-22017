package operators;

import java.lang.Math;
import java.util.*;
import myUtils.myUtils;

public class Bicubic {
  private static Scanner scanner = new Scanner(System.in);
  
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
    Matrix imageValue = new Matrix(16, 1); // return in (also) size of 16x1
    imageValue = getInverseBicubicPolynomialMatrix().mult(getImageMatrix().mult(I));
    return imageValue;
  }

  public static double getBicubicFunctionValue(Matrix fMat, double x, double y) {
    int i, j;
    Matrix bicubicCoef = getInverseBicubicPolynomialMatrix().mult(fMat);
    double fResult = 0;
    for (j = 0; j < 4; j++) {
      for (i = 0; i < 4; i++) {
        fResult += bicubicCoef.getELMT(4*i + j, 0) * Math.pow(x, i) * Math.pow(y, j);
      }
    }
    return fResult;
  }
}