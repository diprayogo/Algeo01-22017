package operators;

public class SPL {
  // 1. Metode Eleminasi Gauss
  public String metodeGauss(Matrix matrix) {
    String result = new String();

    matrix.strictGauss();

    // Membuat list untuk augmented atau b
    double[] augMat = new double[matrix.getRow()];
    for (int i = 0; i < augMat.length; i++) {
      augMat[i] = matrix.getELMT(i, matrix.getCol() - 1);
    }

    // Membuat list untuk akar-akar persamaan dengan x_i
    double[] rootSolution = new double[matrix.getRow()];
    for (int i = 0; i < rootSolution.length; i++) {
      rootSolution[i] = 1;
    }

    // Membuat list untuk parameter jika SPL memiliki solusi non-unik
    // double[] params = new double[matrix.getRow()];
    // for (int i = 0; i < params.length; i++) {
    // params[i] = i + 1;
    // }

    boolean isNoSolution = false;
    boolean isNonUnique = false;
    for (int i = matrix.getRow() - 1; i >= 0; i--) {
      int countZero = 0;
      for (int j = 0; j < matrix.getCol() - 1; j++) {
        if (matrix.getELMT(i, j) == 0) {
          countZero++;
        }
      }
      if (countZero == matrix.getCol() - 1) { // Tidak memiliki solusi
        isNoSolution = matrix.getELMT(i, matrix.getCol() - 1) != 0;
      } else {
        if (matrix.getELMT(i, matrix.getCol() - 1) != 0 || matrix.isSquare) { // Memiliki solusi unik / tunggal
          // Subtitusi mundur
          for (int p = rootSolution.length - 1; p >= 0; p--) {
            double sum = 0;
            for (int q = p + 1; q < rootSolution.length; q++) {
              sum += matrix.getELMT(p, q) * rootSolution[q];
            }
            rootSolution[p] = (augMat[p] - sum);
          }
        } else { // Memiliki solusi banyak
          isNonUnique = true;
        }
      }
    }

    if (isNoSolution) {
      result = "SPL tidak memiliki solusi";
    } else {
      if (isNonUnique) {
        result = "SPL memiliki solusi non-unik";
      } else {
        result = "Solusi dari persamaan linear tersebut adalah:";
        for (int k = 0; k < rootSolution.length; k++) {
          result += "\nx" + (k + 1) + ": " + rootSolution[k];
        }
      }
    }
    return result;
  }

  // 2. Metode Gauss-Jordan
  public String metodeGaussJordan(Matrix matrix) {
    String result = new String();

    matrix.strictGaussJordan();

    // Membuat list untuk augmented atau b
    double[] augMat = new double[matrix.getRow()];
    for (int i = 0; i < augMat.length; i++) {
      augMat[i] = matrix.getELMT(i, matrix.getCol() - 1);
    }

    // Membuat list untuk akar-akar persamaan dengan x_i
    double[] rootSolution = new double[matrix.getRow()];
    for (int i = 0; i < rootSolution.length; i++) {
      rootSolution[i] = 1;
    }

    // Membuat list untuk parameter jika SPL memiliki solusi non-unik
    // double[] params = new double[matrix.getRow()];
    // for (int i = 0; i < params.length; i++) {
    // params[i] = i + 1;
    // }

    boolean isNoSolution = false;
    boolean isNonUnique = false;
    for (int i = matrix.getRow() - 1; i >= 0; i--) {
      int countZero = 0;
      for (int j = 0; j < matrix.getCol() - 1; j++) {
        if (matrix.getELMT(i, j) == 0) {
          countZero++;
        }
      }
      if (countZero == matrix.getCol() - 1) { // Tidak memiliki solusi
        isNoSolution = matrix.getELMT(i, matrix.getCol() - 1) != 0;
      } else {
        if (matrix.getELMT(i, matrix.getCol() - 1) != 0 || matrix.isSquare) { // Memiliki solusi unik / tunggal
          // Subtitusi mundur
          for (int p = rootSolution.length - 1; p >= 0; p--) {
            double sum = 0;
            for (int q = p + 1; q < rootSolution.length; q++) {
              sum += matrix.getELMT(p, q) * rootSolution[q];
            }
            rootSolution[p] = (augMat[p] - sum);
          }
        } else { // Memiliki solusi banyak
          isNonUnique = true;
        }
      }
    }

    if (isNoSolution) {
      result = "SPL tidak memiliki solusi";
    } else {
      if (isNonUnique) {
        result = "SPL memiliki solusi non-unik";
      } else {
        result = "Solusi dari persamaan linear tersebut adalah:";
        for (int k = 0; k < rootSolution.length; k++) {
          result += "\nx" + (k + 1) + ": " + rootSolution[k];
        }
      }
    }
    return result;
  }

  // 3. Metode Matriks Balikan
  public String metodeMatriksBalikan(Matrix matrix) {
    String result = new String();

    Matrix matSquare = new Matrix(matrix.getRow(), matrix.getCol() - 1);
    Matrix b = new Matrix(matrix.getRow(), 1);
    int col = matrix.getCol() - 1;

    // Mengambil matriks square
    for (int i = 0; i < matSquare.getRow(); i++) {
      for (int j = 0; j < matSquare.getCol(); j++) {
        matSquare.setELMT(i, j, matrix.getELMT(i, j));
      }
    }

    // Mengambil b dari matriks augmented
    for (int i = 0; i < b.getRow(); i++) {
      b.setELMT(i, 0, matrix.getELMT(i, col));
    }

    // Mencari invers dari matriks dan mengalikan dengan b
    Matrix inversMat = matSquare.inverseGaussJordan();
    Matrix rootSolution = inversMat.mult(b);

    result = "Solusi dari persamaan linear tersebut adalah:";
    for (int k = 0; k < rootSolution.getRow(); k++) {
      result += "\nx" + (k + 1) + ": " + rootSolution.getELMT(k, 0);
    }

    return result;
  }

  // 4. Kaidah Cramer
  public String kaidahCramer(Matrix matrix) {
    String result = new String();

    Matrix matSquare = new Matrix(matrix.getRow(), matrix.getCol() - 1);
    Matrix b = new Matrix(matrix.getRow(), 1);
    int col = matrix.getCol() - 1;

    // Mengambil matriks square
    for (int i = 0; i < matSquare.getRow(); i++) {
      for (int j = 0; j < matSquare.getCol(); j++) {
        matSquare.setELMT(i, j, matrix.getELMT(i, j));
      }
    }
    // Menyimpan matriks square ke temporary
    Matrix tempMatrix = Matrix.copyMatrix2(matSquare);
    Matrix tempMatrix2 = Matrix.copyMatrix2(matSquare);

    // Mengambil b dari matriks augmented
    for (int i = 0; i < b.getRow(); i++) {
      b.setELMT(i, 0, matrix.getELMT(i, col));
    }

    // Mencari determinan matriks square A
    double detA = Matrix.detMatrixSegitiga(tempMatrix2);
    double tempDet;
    Matrix rootSolution = new Matrix(matSquare.getRow(), 1);

    for (int i = 0; i < matSquare.getCol(); i++) {
      for (int j = 0; j < matSquare.getRow(); j++) {
        tempMatrix.setELMT(j, i, b.getELMT(j, 0));
      }

      tempDet = Matrix.detMatrixSegitiga(tempMatrix);
      tempDet /= detA;
      rootSolution.setELMT(i, 0, tempDet);

      tempMatrix.copyMatrix(matSquare);
    }

    result = "Determinan: " + detA;
    result = "\nSolusi dari persamaan linear tersebut adalah:";
    for (int k = 0; k < rootSolution.getRow(); k++) {
      result += "\nx" + (k + 1) + ": " + rootSolution.getELMT(k, 0);
    }

    return result;
  }

}
