package operators;

public class SPL {
  // 1. Gauss Method
  public static String metodeGauss(Matrix matrix) {
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

    Matrix.printMatrix(matrix);

    if (isNoSolution(matrix)) {
      result = "SPL tidak memiliki solusi";
    } else {
      if (isNonUnique(matrix) && matrix.getRow() < matrix.getCol()) {
        result = parametric(matrix);
        // SPL2.SolveManySolution(matrix);
      } else {
        if (matrix.getRow() > matrix.getCol()) {
          // Subtitusi mundur
          for (int p = rootSolution.length - 1; p >= 0; p--) {
            double sum = 0;
            for (int q = p + 1; q < rootSolution.length; q++) {
              sum += matrix.getELMT(p, q) * rootSolution[q];
            }
            rootSolution[p] = (augMat[p] - sum);
          }
          result = "Solusi dari SPL tersebut adalah: \n";
          for (int i = 0; i < matrix.getRow(); i++) {
            boolean isRowZero = false; // Boolean jika satu baris bernilai 0 semua
            for (int j = 0; j < matrix.getCol(); j++) {
              if (matrix.getELMT(i, j) != 0 && j != i) {
                isRowZero = true;
                break;
              }
            }
            if (i < rootSolution.length && isRowZero) {
              result += "x" + (i + 1) + " = " + rootSolution[i] + "\n";
            }
          }
        } else {
          // Subtitusi mundur
          for (int p = rootSolution.length - 1; p >= 0; p--) {
            double sum = 0;
            for (int q = p + 1; q < rootSolution.length; q++) {
              sum += matrix.getELMT(p, q) * rootSolution[q];
            }
            rootSolution[p] = (augMat[p] - sum);
          }
          result = "Solusi dari SPL tersebut adalah: \n";
          for (int i = 0; i < rootSolution.length; i++) {
            result += "x" + (i + 1) + " = " + rootSolution[i] + "\n";
          }
        }
      }

    }
    return result;
  }

  // 2. Gauss Jordan Method
  public static String metodeGaussJordan(Matrix matrix) {
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

    Matrix.printMatrix(matrix);

    if (isNoSolution(matrix)) {
      result = "SPL tidak memiliki solusi";
    } else {
      if (isNonUnique(matrix) && matrix.getRow() < matrix.getCol()) {
        result = parametric(matrix);
        // SPL2.SolveManySolution(matrix);
      } else {
        if (matrix.getRow() > matrix.getCol()) {
          // Subtitusi mundur
          for (int p = rootSolution.length - 1; p >= 0; p--) {
            double sum = 0;
            for (int q = p + 1; q < rootSolution.length; q++) {
              sum += matrix.getELMT(p, q) * rootSolution[q];
            }
            rootSolution[p] = (augMat[p] - sum);
          }
          result = "Solusi dari SPL tersebut adalah: \n";
          for (int i = 0; i < matrix.getRow(); i++) {
            boolean isRowZero = false; // Boolean jika satu baris bernilai 0 semua
            for (int j = 0; j < matrix.getCol(); j++) {
              if (matrix.getELMT(i, j) != 0 && j != i) {
                isRowZero = true;
                break;
              }
            }
            if (i < rootSolution.length && isRowZero) {
              result += "x" + (i + 1) + " = " + rootSolution[i] + "\n";
            }
          }
        } else {
          // Subtitusi mundur
          for (int p = rootSolution.length - 1; p >= 0; p--) {
            double sum = 0;
            for (int q = p + 1; q < rootSolution.length; q++) {
              sum += matrix.getELMT(p, q) * rootSolution[q];
            }
            rootSolution[p] = (augMat[p] - sum);
          }
          result = "Solusi dari SPL tersebut adalah: \n";
          for (int i = 0; i < rootSolution.length; i++) {
            result += "x" + (i + 1) + " = " + rootSolution[i] + "\n";
          }
        }
      }

    }
    return result;
  }

  // 3. Inverse Method
  public static String metodeInverse(Matrix matrix) {
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

    if (matSquare.isSquare()) {// Mencari invers dari matriks dan mengalikan dengan b
      Matrix inversMat = matSquare.inverseGaussJordan();
      Matrix rootSolution = inversMat.mult(b);

      result = "Solusi dari persamaan linear tersebut adalah:";
      for (int k = 0; k < rootSolution.getRow(); k++) {
        result += "\nx" + (k + 1) + ": " + rootSolution.getELMT(k, 0);
      }
    } else {
      result = "Matriks yang dimasukkan tidak bisa menggunakan metode ini";
    }

    return result;
  }

  // 4. Kaidah Cramer
  public static String kaidahCramer(Matrix matrix) {
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

    // boolean isNoSolution = false;
    // boolean isNonUnique = false;
    // for (int i = matrix.getRow() - 1; i >= 0; i--) {
    // int countZero = 0;
    // for (int j = 0; j < matrix.getCol() - 1; j++) { // Menghitung kemunculan
    // angka 0
    // if (matrix.getELMT(i, j) == 0) {
    // countZero++;
    // }
    // }
    // if (countZero == matrix.getCol() - 1) {
    // if (matrix.getELMT(i, matrix.getCol() - 1) != 0) {
    // isNoSolution = true; // Tidak memiliki solusi
    // } else {
    // isNonUnique = true; // Solusi parametrik
    // }
    // }
    // }

    if (matSquare.isSquare() || matrix.getRow() != matrix.getCol() - 1) {
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
        if (tempDet == -0.0) {
          tempDet = 0.0;
        }
        rootSolution.setELMT(i, 0, tempDet);

        tempMatrix.copyMatrix(matSquare);
      }
      result = "Determinan: " + detA;
      result = "\nSolusi dari persamaan linear tersebut adalah:";
      for (int k = 0; k < rootSolution.getRow(); k++) {
        result += "\nx" + (k + 1) + ": " + rootSolution.getELMT(k, 0);
      }
    } else {
      result = "Matriks yang dimasukkan tidak bisa menggunakan metode ini";
    }

    return result;
  }

  public static boolean isNoSolution(Matrix matrix) {
    // Matriks inputan berupa matriks yang sudah terlebih dahulu dilakukan OBE
    boolean isNoSolution = false; // Jika solusi tidak ada
    for (int i = matrix.getRow() - 1; i >= 0; i--) {
      int countZero = 0;
      for (int j = 0; j < matrix.getCol() - 1; j++) { // Menghitung kemunculan angka 0
        if (matrix.getELMT(i, j) == 0) {
          countZero++;
        }
      }
      if (countZero == matrix.getCol() - 1) {
        if (matrix.getELMT(i, matrix.getCol() - 1) != 0) {
          isNoSolution = true; // Tidak memiliki solusi
        }
      }
    }

    return isNoSolution;
  }

  public static boolean isNonUnique(Matrix matrix) {
    boolean isNonUnique = false;
    if (matrix.getCol() > matrix.getRow() + 1) {
      isNonUnique = true;
    } else {
      for (int i = matrix.getRow() - 1; i >= 0; i--) {
        int countZero = 0;
        for (int j = 0; j < matrix.getCol() - 1; j++) { // Menghitung kemunculan angka 0
          if (matrix.getELMT(i, j) == 0) {
            countZero++;
          }
        }
        if (countZero == matrix.getCol() - 1) {
          if (matrix.getELMT(i, matrix.getCol() - 1) == 0) {
            isNonUnique = true; // Solusi parametrik
          }
        }
      }
    }

    return isNonUnique;
  }

  public static String parametric(Matrix matrix) {
    String result = new String();
    int row = matrix.getRow();
    int col = matrix.getCol() - 1;
    int[] idxLeadingOne = new int[row];
    for (int i = 0; i < idxLeadingOne.length; i++) {
      idxLeadingOne[i] = -1;
    }

    int[] idxSolution = new int[col];
    for (int i = 0; i < idxSolution.length; i++) {
      idxSolution[i] = i + 1;
    }

    int r = 0;
    int c = 0;
    boolean isLeadOne = false;
    while (r < row) {
      while (c < col && !isLeadOne) {
        if (matrix.getELMT(r, c) != 0) {
          idxLeadingOne[r] = c;
          isLeadOne = true;
        } else {
          c++;
        }
      }
      isLeadOne = false;
      r++;
    }

    int[] idxParams = new int[col];
    // Menyimpan solusi yang mengandung parametrik
    for (int i = 0; i < col; i++) {
      if (i < row) {
        if (idxSolution[i] < 0) {
          idxParams[i] = i;
        } else {
          idxParams[i] = -1;
        }
      } else {
        idxParams[i] = i;
      }
    }

    // Mencetak solusi parametrik
    result = "SPL memiliki solusi banyak\n";
    for (int i = 0; i < row; i++) {
      if (idxSolution[i] >= 0 && matrix.getELMT(i, i) != 0) {
        boolean zero = false;
        if (matrix.getELMT(i, matrix.getCol() - 1) != 0) {
          result += "x" + idxSolution[i] + " = " + matrix.getELMT(i, matrix.getCol() - 1);
        } else {
          result += "x" + idxSolution[i] + " = ";
          zero = true;
        }
        for (int j = idxLeadingOne[i] + 1; j < col; j++) {
          if (idxParams[j] == -1) {
            if (matrix.getELMT(i, j) != 0 && i != j) {
              if (zero) {
                result += matrix.getELMT(i, j) + "x" + (j + 1);
              } else if (matrix.getELMT(i, j) < 0) {
                if (matrix.getELMT(i, j) == -1.0) {
                  result += " + " + "x" + (j + 1);
                } else {
                  result += " + " + (matrix.getELMT(i, j) * -1) + "x" + (j + 1);
                }
              } else if (matrix.getELMT(i, j) == 1.0) {
                result += " - " + "x" + (j + 1);
              } else {
                result += " - " + matrix.getELMT(i, j) + "x" + (j + 1);
              }
            }
          } else {
            if (matrix.getELMT(i, j) != 0 && i != j) {
              if (zero) {
                result += matrix.getELMT(i, j) + "t" + (j + 1);
              } else if (matrix.getELMT(i, j) < 0) {
                if (matrix.getELMT(i, j) == -1.0) {
                  result += " + " + "t" + (j + 1);
                } else {
                  result += " + " + (matrix.getELMT(i, j) * -1) + "t" + (j + 1);
                }
              } else if (matrix.getELMT(i, j) == 1.0) {
                result += " - " + "t" + (j + 1);
              } else {
                result += " - " + matrix.getELMT(i, j) + "t" + (j + 1);
              }
            }
          }
        }
        result += "\n";
      } else {
        result += "x" + idxSolution[i] + " = " + "t" + (i + 1) + " dengan t" + (i + 1) + " adalah bilangan Real\n";
      }
    }
    if (row != col) {
      for (int i = row; i < col; i++) {
        result += "x" + idxSolution[i] + " = " + "t" + (i + 1) + " dengan t" + (i + 1) + " adalah bilangan Real\n";
      }
    }

    return result;
  }

}
