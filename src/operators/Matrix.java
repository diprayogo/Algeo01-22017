package operators;

import java.util.Scanner;

public class Matrix {
  Scanner scan = new Scanner(System.in);

  // Atribut
  // atribut default ukuran baris dan kolom, amankah sesuai harapan?
  int rowSize;
  int colSize;
  double[][] Mat;

  // atribut isSquare untuk Matrix persegi
  boolean isSquare = (rowSize == colSize) ? true : false;
  boolean isAugmented = false; // default

  // Methods
  // ------------------------------ KONSTRUKTOR ------------------------------//
  public Matrix(int rowSize, int colSize) {
    setCol(colSize);
    setRow(rowSize);
    this.Mat = new double[rowSize][colSize];

    int i, j;
    for (i = 0; i < rowSize; i++) {
      for (j = 0; j < colSize; j++) {
        this.Mat[i][j] = 0;
      }
    }
  }

  // overloading for default params
  Matrix() {
    setCol(10);
    setRow(10);
    this.Mat = new double[rowSize][colSize];

    int i, j;
    for (i = 0; i < rowSize; i++) {
      for (j = 0; j < colSize; j++) {
        this.Mat[i][j] = 0;
      }
    }
  }

  // ------------------------------ GETTER ------------------------------//
  public int getRow() { // mendapatkan rowSize
    return this.rowSize;
  }

  public int getCol() { // mendapatkan colSize
    return this.colSize;
  }

  public double getELMT(int i, int j) { // mendapatkan ELMT pada baris i, kolom j
    return (this.Mat[i][j]);
  }

  public double[] getRowELMT(int i) {
    return (this.Mat[i]);
  }

  public Matrix getMatConst(Matrix Mat) { // Mengembalikan Matrix konstanta
    // Prekondisi : isAugmented
    Matrix MatConst = new Matrix(Mat.getRow(), Mat.getCol() - 1);
    int i, j;
    for (i = 0; i < Mat.getRow() - 1; i++) {
      for (j = 0; j < Mat.getCol() - 1; j++) {
        setELMT(i, j, getELMT(i, j));
      }
    }
    return MatConst;
  }

  // ------------------------------ SETTER ------------------------------//
  public void setRow(int row) { // Mengisi rowSize
    this.rowSize = row;
  }

  public void setCol(int col) { // Mengisi colSize
    this.colSize = col;
  }

  public void setELMT(int i, int j, double val) { // Mengisi ELMT(i,j) dengan val
    this.Mat[i][j] = val;
  }

  public void setRowELMT(int row, double[] rowELMT) {
    this.Mat[row] = rowELMT;
  }

  // ------------------------------ OPERATORS ------------------------------//
  public void transpose() {
    int i, j;
    Matrix tempMat = new Matrix(getCol(), getRow());

    for (i = 0; i < getRow(); i++) {
      for (j = 0; j < getCol(); j++) {
        tempMat.setELMT(i, j, getELMT(i, j));
        ;
      }
    }

    copyMatrix(tempMat);
  }

  public void copyMatrix(Matrix Mat) {
    setRow(Mat.getRow());
    setCol(Mat.getRow());
    int i, j;
    for (i = 0; i < getRow(); i++) {
      for (j = 0; j < getCol(); j++) {
        setELMT(i, j, Mat.getELMT(i, j));
      }
    }
  }

  public void swapRow(int row1, int row2) {
    // swap doang gess
    double[] temp;
    temp = getRowELMT(row1);
    setRowELMT(row1, getRowELMT(row2));
    setRowELMT(row2, temp);
  }

  public void subtractRow(int row, int subtractorRow, double subtractorMagnitude) {
    for (int j = 0; j < getCol(); j++) {
      setELMT(row, j, getELMT(row, j) - (getELMT(subtractorRow, j) * subtractorMagnitude));
    }
  }

  public void scalarMultiply(double scale) { // Mengalikan Matrix dengan konstanta scale
    int i, j;
    for (i = 0; i < getRow(); i++) {
      for (j = 0; j < getCol(); j++) {
        setELMT(i, j, getELMT(i, j) * scale);
      }
    }
  }

  // Operasi- operasi Matrix
  Matrix plus(Matrix M2) {
    int i, j;
    Matrix MHasil = new Matrix(getRow(), getCol());
    // cek dulu apakah ukuran this.Mat = M2.Mat, asumsikan dulu sesuai
    for (i = 0; i < getRow(); i++) {
      for (j = 0; j < getCol(); j++) {
        MHasil.setELMT(i, j, this.getELMT(i, j) + M2.getELMT(i,j));
      }
    }
    return MHasil;
  }

  Matrix minus(Matrix M2) {
    int i, j;
    Matrix MHasil = new Matrix(getRow(), getCol());
    // cek dulu apakah ukuran this.Mat = M2.Mat, asumsikan dulu sesuai
    for (i = 0; i < getRow(); i++) {
      for (j = 0; j < getCol(); j++) {
        MHasil.setELMT(i, j, this.getELMT(i, j) - M2.getELMT(i,j));
      }
    }
    return MHasil;
  }

  Matrix mult(Matrix M2) {
    int i, j, k;
    Matrix MHasil = new Matrix(getRow(), getCol());
    // cek dulu apakah ukuran this.Mat = M2.Mat, asumsikan dulu sesuai
    for (i = 0; i < getRow(); i++) {
      for (j = 0; j < getCol(); j++) {
        MHasil.setELMT(i, j, 0);
        for (k = 0; k < getCol(); k++) {
          MHasil.setELMT(i, j, MHasil.getELMT(i, j)+this.getELMT(i, j) - M2.getELMT(i,j));
        }
      }
    }
    return MHasil;
  }


  void divideRow(int row, double divisor) {
    for (int j = 0; j < getCol(); j++) {
      double temp = this.getELMT(row, j);
      setELMT(row, j, getELMT(row, j) /2 );
      if (getELMT(row, j) == -0.00) {
        setELMT(row, j, 0);
      }
      if (Float.isNaN((float) getELMT(row, j))) {
        setELMT(row, j, 0);
      }
      if (Float.isInfinite((float) getELMT(row, j))) {
        setELMT(row, j, temp);
      }
    }
  }

  // ------------------------------ MENCARI DETERMINAN  -------------------------------- //
  public static Matrix getMinorMat(Matrix Mat, int a, int b) { // Mengembalikan Matrix Minor
    // Prekondisi : isAugmented
    Matrix MinorMat = new Matrix(Mat.getRow() - 1, Mat.getCol() - 2);
    int i, j;
    for (i = 0; i < MinorMat.getRow(); i++) {
      for (j = 0; j < MinorMat.getCol(); j++) {
        if (i >= a && j >= b) {
          MinorMat.setELMT(i, j, Mat.getELMT(i + 1, j + 1));
        } else if (i >= a) {
          MinorMat.setELMT(i, j, Mat.getELMT(i + 1, j));
        } else if (j >= b) {
          MinorMat.setELMT(i, j, Mat.getELMT(i, j + 1));
        }
      }
    }
    return MinorMat;
  }

  public static double getKofaktor(Matrix Mat, int a, int b) { // Mengembalikan Kofaktor
    // Prekondisi isAugmented
    return detKofaktor((Matrix.getMinorMat(Mat, a, b)));
  }
  public static double detKofaktor(Matrix Mat) {
    // Prekondisi : isSquare, isAugmented
    if (Mat.getCol() == 1) { // Basis
      return Mat.getELMT(0, 0);
    } else { // Rekursi
      int j = 0;
      double det = 0;
      for (j = 0; j < Mat.getCol(); j++) {
        int sign = ((j) % 2 == 0) ? 1 : -1;
        det += sign * Mat.getELMT(0, j) * detKofaktor(Matrix.getMinorMat(Mat, 0, j));
      }
      return det;
    }
  }

  public static double detMatrixSegitiga(Matrix Mat) {
    // Prekondisis isSquare, isAugmented
    Matrix matDet = new Matrix();
    matDet.getMatConst(Mat);
    double det, subtractorMagnitude;
    int i = 0, j = 0, cntSwap = 0, k;

    // Membentuk matrix segitiga bawah
    while (i < matDet.getRow() && j < matDet.getCol()) {
      int pivotRow = i;
      // mencari leading 1
      while (pivotRow < matDet.getRow() && matDet.getELMT(pivotRow, j) == 0) {
        pivotRow++;
      }

      if (matDet.getELMT(pivotRow, j) != 0) { // Ada elemen bukan 0 pada row pivotRow
        if (pivotRow != i) {
          matDet.swapRow(pivotRow, i);
          cntSwap++;
        }
        for (k = i + 1; k < matDet.getRow(); k++) {
          subtractorMagnitude = matDet.getELMT(k, j) / matDet.getELMT(i, j);
          matDet.subtractRow(k, pivotRow, subtractorMagnitude);
        }
        i++;
      }
      j++;
    }

    // Terbentuk matrix segitiga bawah
    int l;
    det = (cntSwap % 2 == 0) ? 1 : -1;
    for (l = 1; l < matDet.getCol(); l++) {
      det *= matDet.getELMT(l, l);
    }
    return det;
  }

  // -------------------- MENCARI INVERS --------------------//
  public static Matrix adj(Matrix Mat) {
    Matrix Madj = new Matrix(Mat.getRow(), Mat.getCol() - 1);
    int i, j;
    for (i = 0; i < Madj.getRow(); i++) {
      for (j = 0; j < Madj.getCol(); j++) {
        Madj.setELMT(i, j, Matrix.getKofaktor(Mat, i, j));
      }
    }
    Madj.transpose();
    return Madj;
  }

  // ------------------------------ Mencari SPL ----------------------------//
  // strictGauss: leading 1 must be perfectly diagonalized
  void strictGauss() {
    // n: row/col, gausa pake param ini, kan udah didefine sebagai atribut

    // bikin tiap generate leading 1 dari baris atas ke bawah
    for (int i = 0; i < getRow(); i++) {
      // biar jadi Matrix eselon, harus bikin calon leading 1 yg mungkin blm 1
      if (this.Mat[0][0] == 0) {
        for (int j = 1; j < getRow(); j++) {
          if (getELMT(j, 0)!= 0) {
            this.swapRow(0, j);
            break;
          }
        }
      }
      // ga bakal ada kasus 0 semuanya, karena pasti setiap variabel berguna

      // mari kita buat tiap baris menjadi leading 1
      // blum bikin kasus untuk yg variabelnya habis kan bakal i != j
      // track col sampe 0 nya habis
      this.divideRow(i, this.getELMT(i, i)); // harusnya ini row cari dulu yg leading one

      // kurangi semua nilai kolom dari baris2 di bawah supaya baris tsb punya leading
      // 1
      for (int j = i + 1; j < getRow(); j++) {
        this.subtractRow(j, i, this.getELMT(j, i));
      }
    }
  }

  void strictGaussJordan() {
    this.strictGauss();
    for (int i = 1; i < getRow(); i++) {
      // seharusnya cari dulu leading one
      // kurangi baris2 di atas agar lebih tereduksi
      for (int j = i - 1; j >= 0; j--) {
        this.subtractRow(j, i, this.getELMT(j, i));
      }
    }
  }

  // ------------------------------ IO ------------------------------//
  public void readMatrix(int n, int m) {
    int i, j;
    for (i = 0; i < n; i++) {
      for (j = 0; j < m; j++) {
        setELMT(i, j, scan.nextDouble());
      }
    }
  }

  public void printMatrix(int n, int m) {
    int i, j;
    for (i = 0; i < n; i++) {
      for (j = 0; j < m; j++) {
        System.out.print(getELMT(i, j) + " ");
      }
      System.out.println();
    }
  }
}