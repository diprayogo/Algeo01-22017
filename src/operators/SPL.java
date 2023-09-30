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

    boolean isNoSolution = false; // Jika solusi tidak ada
    boolean isNonUnique = false; // Jika solusi berupa parametrik
    if (matrix.getCol() != matrix.getRow() + 1) {
      isNonUnique = true;
    }
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
        } else {
          isNonUnique = true; // Solusi parametrik
        }
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
          isNonUnique = true; // Masuk ke kasus solusi parametrik
        }
      }
    }

    matrix.printMatrix(matrix.getRow(), matrix.getCol());

    if (isNoSolution) {
      result = "SPL tidak memiliki solusi";
    } else {
      if (isNonUnique) {
        result = "SPL memiliki solusi non-unik\n";
        for (int i = 0; i < matrix.getRow(); i++) {
          boolean isRowZero = true; // Boolean jika satu baris bernilai 0 semua
          for (int j = 0; j < matrix.getCol(); j++) {
            if (matrix.getELMT(i, j) != 0 && j != i) {
              isRowZero = false;
              break;
            }
          }
          if (!isRowZero) {
            boolean zero = false;
            if (matrix.getELMT(i, matrix.getCol() - 1) != 0) {
              result += "x" + (i + 1) + " = " + matrix.getELMT(i, matrix.getCol() - 1);
            } else {
              result += "x" + (i + 1) + " = ";
              zero = true;
            }
            if (!zero && i > 1) {
              if (matrix.getELMT(i, i) < 0) {
                if (matrix.getELMT(i, i) == -1.0) {
                  result += " + " + "x" + (i + 1);
                } else {
                  result += " - " + (matrix.getELMT(i, i) * -1) + "x" + (i + 1);
                }
              } else {
                if (matrix.getELMT(i, i) == 1.0) {
                  result += " - " + "x" + (i + 1);
                } else {
                  result += " + " + matrix.getELMT(i, i) + "x" + (i + 1);
                }
              }
            }
            for (int j = 0; j < matrix.getCol() - 1; j++) {
              if (matrix.getELMT(i, j) != 0 && j != i) {
                if (matrix.getELMT(i, j) < 0) {
                  if (zero) {
                    result += (matrix.getELMT(i, j) * -1) + "x" + (j + 1);
                  } else if (matrix.getELMT(i, j) == -1.0) {
                    result += " + " + "x" + (j + 1);
                  } else {
                    result += " + " + (matrix.getELMT(i, j) * -1) + "x" + (j + 1);
                  }
                } else {
                  if (zero) {
                    result += matrix.getELMT(i, j) + "x" + (j + 1);
                  } else if (matrix.getELMT(i, j) == 1.0) {
                    result += " - " + "x" + (j + 1);
                  } else {
                    result += " - " + matrix.getELMT(i, j) + "x" + (j + 1);
                  }
                }
              }
            }
            result += "\n";
          } else {
            result += "x" + (i + 1) + " = t" + (i + 1) + " dengan " + "t" + (i + 1) + " adalah bilangan Real\n";
          }
        }
        if (matrix.getCol() != matrix.getRow() + 1) {
          for (int k = matrix.getRow(); k < matrix.getCol() - 1; k++) {
            result += "x" + (k + 1) + " = t" + (k + 1) + " dengan " + "t" + (k + 1) + " adalah bilangan Real\n";
          }
        }
      } else {
        result = "Solusi dari SPL tersebut adalah: \n";
        for (int i = 0; i < rootSolution.length; i++) {
          result += "x" + (i + 1) + " = " + rootSolution[i] + "\n";
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

    boolean isNoSolution = false; // Jika solusi tidak ada
    boolean isNonUnique = false; // Jika solusi berupa parametrik
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
        } else {
          isNonUnique = true; // Solusi parametrik
        }
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
          isNonUnique = true; // Masuk ke kasus solusi parametrik
        }
      }
    }

    matrix.printMatrix(matrix.getRow(), matrix.getCol());

    if (isNoSolution) {
      result = "SPL tidak memiliki solusi";
    } else {
      if (isNonUnique) {
        result = "SPL memiliki solusi non-unik\n";
        for (int i = 0; i < matrix.getRow(); i++) {
          boolean isRowZero = true; // Boolean jika satu baris bernilai 0 semua
          for (int j = 0; j < matrix.getCol(); j++) {
            if (matrix.getELMT(i, j) != 0 && j != i) {
              isRowZero = false;
              break;
            }
          }
          if (!isRowZero) {
            boolean zero = false;
            if (matrix.getELMT(i, matrix.getCol() - 1) != 0) {
              result += "x" + (i + 1) + " = " + matrix.getELMT(i, matrix.getCol() - 1);
            } else {
              result += "x" + (i + 1) + " = ";
              zero = true;
            }
            if (!zero && i > 1) {
              if (matrix.getELMT(i, i) < 0) {
                if (matrix.getELMT(i, i) == -1.0) {
                  result += " + " + "x" + (i + 1);
                } else {
                  result += " - " + (matrix.getELMT(i, i) * -1) + "x" + (i + 1);
                }
              } else {
                if (matrix.getELMT(i, i) == 1.0) {
                  result += " - " + "x" + (i + 1);
                } else {
                  result += " + " + matrix.getELMT(i, i) + "x" + (i + 1);
                }
              }
            }
            for (int j = 0; j < matrix.getCol() - 1; j++) {
              if (matrix.getELMT(i, j) != 0 && j != i) {
                if (matrix.getELMT(i, j) < 0) {
                  if (zero) {
                    result += (matrix.getELMT(i, j) * -1) + "x" + (j + 1);
                  } else if (matrix.getELMT(i, j) == -1.0) {
                    result += " + " + "x" + (j + 1);
                  } else {
                    result += " + " + (matrix.getELMT(i, j) * -1) + "x" + (j + 1);
                  }
                } else {
                  if (zero) {
                    result += matrix.getELMT(i, j) + "x" + (j + 1);
                  } else if (matrix.getELMT(i, j) == 1.0) {
                    result += " - " + "x" + (j + 1);
                  } else {
                    result += " - " + matrix.getELMT(i, j) + "x" + (j + 1);
                  }
                }
              }
            }
            result += "\n";
          } else {
            result += "x" + (i + 1) + " = t" + (i + 1) + " dengan " + "t" + (i + 1) + " adalah bilangan Real";
            result += "\n";
          }
        }
      } else {
        result = "Solusi dari SPL tersebut adalah: \n";
        for (int i = 0; i < rootSolution.length; i++) {
          result += "x" + (i + 1) + " = " + rootSolution[i] + "\n";
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
