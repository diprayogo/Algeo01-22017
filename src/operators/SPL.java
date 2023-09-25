package operators;

public class SPL {
  // 1. Metode Eleminasi Gauss
  public static void metodeGauss(Matrix matrix) {
    // String result = new String();

    matrix.strictGauss();

    // Membuat list untuk augmented atau b
    double[] augMat = new double[matrix.rowSize];
    for (int i = 0; i < augMat.length; i++) {
      augMat[i] = matrix.getELMT(i, matrix.colSize - 1);
    }

    // Membuat list untuk akar-akar persamaan dengan x_i
    double[] rootSolution = new double[matrix.rowSize];
    for (int i = 0; i < rootSolution.length; i++) {
      rootSolution[i] = 1;
    }

    // Membuat list untuk parameter jika SPL memiliki solusi non-unik
    // double[] params = new double[matrix.rowSize];
    // for (int i = 0; i < params.length; i++) {
    // params[i] = i + 1;
    // }

    boolean isNoSolution = false;
    boolean isNonUnique = false;
    for (int i = matrix.rowSize - 1; i >= 0; i--) {
      int countZero = 0;
      for (int j = 0; j < matrix.colSize - 1; j++) {
        if (matrix.getELMT(i, j) == 0) {
          countZero++;
        }
      }
      if (countZero == matrix.colSize - 1) { // Tidak memiliki solusi
        isNoSolution = matrix.getELMT(i, matrix.colSize - 1) != 0;
      } else {
        if (matrix.getELMT(i, matrix.colSize - 1) != 0 || matrix.isSquare) { // Memiliki solusi banyak
          // Subtitusi mundur
          for (int p = rootSolution.length - 1; p >= 0; p--) {
            double sum = 0;
            for (int q = p + 1; q < rootSolution.length; q++) {
              sum += matrix.getELMT(p, q) * rootSolution[q];
            }
            rootSolution[p] = (augMat[p] - sum);
          }
        } else { // Memiliki solusi unik / tunggal
          isNonUnique = true;
        }
      }
    }

    if (isNoSolution) {
      System.out.println("SPL tidak memiliki solusi");
    } else {
      if (isNonUnique) {
        System.out.println("SPL memiliki solusi non-unik");
      } else {
        System.out.println("Solusi dari persamaan linear tersebut adalah:");
        for (int k = 0; k < rootSolution.length; k++) {
          System.out.println("x" + (k + 1) + ": " + rootSolution[k]);
        }
      }
    }
  }

  // 2. Metode Gauss-Jordan
  public static void metodeGaussJordan(Matrix matrix) {
    // String result = new String();

    matrix.strictGaussJordan();

    // Membuat list untuk augmented atau b
    double[] augMat = new double[matrix.rowSize];
    for (int i = 0; i < augMat.length; i++) {
      augMat[i] = matrix.getELMT(i, matrix.colSize - 1);
    }

    // Membuat list untuk akar-akar persamaan dengan x_i
    double[] rootSolution = new double[matrix.rowSize];
    for (int i = 0; i < rootSolution.length; i++) {
      rootSolution[i] = 1;
    }

    // Membuat list untuk parameter jika SPL memiliki solusi non-unik
    // double[] params = new double[matrix.rowSize];
    // for (int i = 0; i < params.length; i++) {
    // params[i] = i + 1;
    // }

    boolean isNoSolution = false;
    boolean isNonUnique = false;
    for (int i = matrix.rowSize - 1; i >= 0; i--) {
      int countZero = 0;
      for (int j = 0; j < matrix.colSize - 1; j++) {
        if (matrix.getELMT(i, j) == 0) {
          countZero++;
        }
      }
      if (countZero == matrix.colSize - 1) { // Tidak memiliki solusi
        isNoSolution = matrix.getELMT(i, matrix.colSize - 1) != 0;
      } else {
        if (matrix.getELMT(i, matrix.colSize - 1) == 0 || !matrix.isSquare) { // Memiliki solusi banyak
          isNonUnique = true;
        } else { // Memiliki solusi unik / tunggal
          // Subtitusi mundur
          for (int p = rootSolution.length - 1; p >= 0; p--) {
            double sum = 0;
            for (int q = p + 1; q < rootSolution.length; q++) {
              sum += matrix.getELMT(p, q) * rootSolution[q];
            }
            rootSolution[p] = (augMat[p] - sum);
          }
        }
      }
    }

    if (isNoSolution) {
      System.out.println("SPL tidak memiliki solusi");
    } else {
      if (isNonUnique) {
        System.out.println("SPL memiliki solusi non-unik");
      } else {
        System.out.println("Solusi dari persamaan linear tersebut adalah:");
        for (int k = 0; k < rootSolution.length; k++) {
          System.out.println("x" + (k + 1) + ": " + rootSolution[k]);
        }
      }
    }
  }

  // return rootSolution;
}
