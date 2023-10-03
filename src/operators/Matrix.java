package operators;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Matrix {
  private static Scanner scan = new Scanner(System.in);

  // Atribut
  // atribut default ukuran baris dan kolom
  private int rowSize;
  private int colSize;
  private double[][] Mat;

  // atribut isSquare untuk Matrix persegi
  boolean isSquare = (rowSize == colSize) ? true : false;

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

  public void setMat(int row, int col){
    this.Mat = new double[row][col];
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
      }
    }

    copyMatrix(tempMat); // copy tempMat ke this
  }

  public void copyMatrix(Matrix Mat) { // Prosedur salin matrix Mat ke matrix this
    this.setRow(Mat.getRow());
    this.setCol(Mat.getCol());
    int i, j;
    for (i = 0; i < this.getRow(); i++) {
      for (j = 0; j < this.getCol(); j++) {
        setELMT(i, j, Mat.getELMT(i, j));
      }
    }
  }

  public static Matrix copyMatrix2(Matrix Mat) { // Fungsi mengembalikan matriks Mat
    Matrix copyMat = new Matrix(Mat.getRow(), Mat.getCol());
    int i, j;
    for (i = 0; i < Mat.getRow(); i++) {
      for (j = 0; j < Mat.getCol(); j++) {
        copyMat.setELMT(i, j, Mat.getELMT(i, j));
      }
    }
    return copyMat;
  }

  // 2 Operators OBE
  public void swapRow(int row1, int row2) {
    // swap baris
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

  public static Matrix getMinorMat(Matrix Mat, int a, int b) { // Mengembalikan Matrix Minor
    // Prekondisi : not isAugmented, isSquare
    Matrix MinorMat = new Matrix(Mat.getRow() - 1, Mat.getCol() - 1);
    int i, j;
    for (i = 0; i < MinorMat.getRow(); i++) {
      for (j = 0; j < MinorMat.getCol(); j++) {
        if (i >= a && j >= b) {
          MinorMat.setELMT(i, j, Mat.getELMT(i + 1, j + 1));
        } else if (i >= a) {
          MinorMat.setELMT(i, j, Mat.getELMT(i + 1, j));
        } else if (j >= b) {
          MinorMat.setELMT(i, j, Mat.getELMT(i, j + 1));
        } else { // i < a && j < b
          MinorMat.setELMT(i, j, Mat.getELMT(i, j));
        }
      }
    }
    return MinorMat;
  }

  public static double getKofaktor(Matrix Mat, int a, int b) { // Mengembalikan Kofaktor
    // Prekondisi not isAugmented
    return detKofaktor((Matrix.getMinorMat(Mat, a, b)));
  }

  // ------------------------------ MENCARI DETERMINAN
  // -------------------------------- //
  public static double detKofaktor(Matrix Mat) {
    // Prekondisi not isAugmented
    // check isSquare
    if (!(Mat.isSquare())) {
      System.out.println("Determinan tidak ada karena bukan matrix persegi");
      return Double.NaN ;
    }

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
    // prekondisi not isAugmented
    // check isSquare 
      if (!(Mat.isSquare())) {
      System.out.println("Determinan tidak ada karena bukan matrix persegi");
      return Double.NaN ;
    }
    
    double det, subtractorMagnitude;
    int i = 0, j = 0, cntSwap = 0, k;

    // Membentuk matrix segitiga bawah
    while (i < Mat.getRow() && j < Mat.getCol()) {
      int pivotRow = i;
      // mencari leading 1
      while (pivotRow < Mat.getRow() - 1 && Mat.getELMT(pivotRow, j) == 0) {
        pivotRow++;
      }

      if (Mat.getELMT(pivotRow, j) != 0) { // Ada elemen bukan 0 pada row pivotRow
        if (pivotRow != i) {
          Mat.swapRow(pivotRow, i);
          cntSwap++;
        }
        for (k = i + 1; k < Mat.getRow(); k++) {
          subtractorMagnitude = Mat.getELMT(k, j) / Mat.getELMT(i, j);
          Mat.subtractRow(k, pivotRow, subtractorMagnitude);
        }
        i++;
      }
      j++;
    }

    // Terbentuk matrix segitiga bawah
    int l;
    det = (cntSwap % 2 == 0) ? 1 : -1;
    for (l = 0; l < Mat.getRow(); l++) {
      det *= Mat.getELMT(l, l);
    }

    // special case
    if (det == -0.0) {
      det += 0.0;
    }
    return det;
  }

  // ------------------------------ MENCARI INVERS/BALIKAN
  // ------------------------------ //
  public Matrix getAdj() {
    Matrix Madj = new Matrix(getRow(), getCol() - 1); // getCol()
    int i, j;
    for (i = 0; i < Madj.getRow(); i++) {
      for (j = 0; j < Madj.getCol(); j++) {
        Madj.setELMT(i, j, getKofaktor(this, i, j));
      }
    }
    Madj.transpose();
    return Madj;
  }

  public void scalarMultiply(double scale) { // Mengalikan Matrix dengan konstanta scale
    int i, j;
    for (i = 0; i < getRow(); i++) {
      for (j = 0; j < getCol(); j++) {
        setELMT(i, j, getELMT(i, j) * scale);
      }
    }
  }

  public Matrix inverseEkspansiCofactor() {
    // KAMUS LOKAL
    Matrix invMat = this.getAdj();

    // ALGORITMA
    invMat.scalarMultiply(1 / detMatrixSegitiga(this));
    return invMat;
  }

  // Prosedur eliminasi Gauss untuk operasi augmented matrix [I A] ke tujuan
  // invers [A^-1 I]
  public void pMultRow(int row, double scale) {
    for (int i = 0; i < this.getCol(); i++) {
      // by default, ini udah ada this.
      setELMT(row, i, getELMT(row, i) * scale);
    }
  }

  // strictGauss: leading 1 must be perfectly diagonalized
  void strictGauss() {
    /**
     * Prekondisi: tidak ada col yang 0 semua atau ada baris yg saling kelipatan
     * return null;
     * ga bakal ada kasus 0 semuanya, karena pasti setiap variabel berguna
     * mari kita buat tiap baris menjadi leading 1
     * blum bikin kasus untuk yg variabelnya habis kan bakal i != j
     * track col sampe 0 nya habis
     */
    // bikin tiap leading 1 dari baris atas ke bawah
    for (int i = 0; i < getRow(); i++) {
      // biar jadi Matrix eselon, harus bikin calon leading 1 yg mungkin blm 1
      // mencari leading 1 yg mungkin
      if (getELMT(i, i) == 0) {
        for (int j = i + 1; j < getRow(); j++) {
          if (getELMT(j, i) != 0) {
            swapRow(i, j);
            break;
          }
        }
      }

      // harusnya ini row cari dulu yg leading one agar bisa dioperasikan
      if (getELMT(i, i) != 0) { // handle kasus tidak sesuai prekondisi
        this.pMultRow(i, 1 / getELMT(i, i));

        // terbentuk leading 1 pada baris tsb.
        for (int j = i + 1; j < getRow(); j++) {
          this.subtractRow(j, i, getELMT(j, i));
        }
      }
    }
  }

  // strictGaussJordan: leading 1 must be perfectly diagonalized
  void strictGaussJordan() {
    this.strictGauss();
    for (int i = 1; i < getRow(); i++) {
      // leading 1 pada diagonal untuk mengurangi baris2 di atas agar lebih tereduksi
      for (int j = i - 1; j >= 0; j--) {
        this.subtractRow(j, i, getELMT(j, i));
      }
    }
  }

  public Matrix inverseGaussJordan() {
    int i, j; // iterator
    Matrix AugmentedMatrix = new Matrix(getRow(), 2 * getCol());
    Matrix InverseMatrix = new Matrix(getRow(), getCol());

    // copy matrix persegi ke bagian kiri AugmentedMatrix
    for (i = 0; i < AugmentedMatrix.getRow(); i++) {
      for (j = 0; j < getCol(); j++) {
        AugmentedMatrix.setELMT(i, j, getELMT(i, j));
      }
    }

    // copy matriks identitas bagian kanan
    for (i = 0; i < AugmentedMatrix.getRow(); i++) {
      for (j = getCol(); j < AugmentedMatrix.getCol(); j++) {
        if (i == j % getCol()) {
          AugmentedMatrix.setELMT(i, j, 1);
        } else {
          AugmentedMatrix.setELMT(i, j, 0);
        }
      }
    }

    // lakukan operasi Gauss Jordan agar diperoleh submatriks identitas kiri
    AugmentedMatrix.strictGaussJordan();
    boolean hasInverse = true;
    for (i = 0; i < AugmentedMatrix.getRow(); i++) {
      if (AugmentedMatrix.getELMT(i, i) == 0) {
        // Matrix tidak memiliki invers
        hasInverse = false;
        break;
      }
    }

    // jika matriks memiliki invers, return inversnya: matriks persegi kanan
    // AugmentedMatrix
    if (hasInverse) { // or !this.isSingular()
      for (i = 0; i < AugmentedMatrix.getRow(); i++) {
        for (j = getCol(); j < AugmentedMatrix.getCol(); j++) {
          InverseMatrix.setELMT(i, j % InverseMatrix.getCol(), AugmentedMatrix.getELMT(i, j));
          // this.Mat[i][j % getCol()] = AugmentedMatrix.Mat[i][j];
        }
      }
      return InverseMatrix;
    } else { // jika tidak, return null buat validasi kalau tidak punya invers
      return null;
    }
  }

  // ------------------------------ Mencari SPL ----------------------------//
  // class SPL dibikinin Dabbir
  
  
  // ------------------------------ Mencari Regresi Linier Berganda ----------------------------//
  
  
  // ------------------------------ IO ------------------------------//
  public void readMatrix(int n, int m) {
    int i, j;
    for (i = 0; i < n; i++) {
      for (j = 0; j < m; j++) {
        setELMT(i, j, scan.nextDouble());
      }
    }
  }

  public static Matrix readMatSquare() {
    int n = 0;
    System.out.print("Anda akan menginput matriks segi empat dengan ukuran n x n. \nMasukkan n: ");
    try {
      n = scan.nextInt();
    } catch (InputMismatchException e) {
      e.printStackTrace();
    }
    Matrix MatSquare = new Matrix();
    MatSquare.setRow(n);
    MatSquare.setCol(n);
    MatSquare.Mat = new double[MatSquare.getRow()][MatSquare.getCol()];
    System.out.printf("Masukkan matriks dengan ukuran %d x %d:\n", n, n);
    MatSquare.readMatrix(n, n);

    return MatSquare;
  }

  public static void printMatrix(Matrix Mat) {
    int i, j, n, m;
    n = Mat.getCol();
    m = Mat.getRow();
    for (i = 0; i < m; i++) {
      for (j = 0; j < n; j++) {
        System.out.print(Mat.getELMT(i, j) + " ");
      }
      System.out.println();
    }
  }

  // NOTES : code di bawah ini belum terpakai// !!!!!!!!!!!!!!!!!!!!!!!
  // ------------------------------ OPERATORS LAIN
  // ------------------------------//

  // Operasi- operasi Matrix lain
  // + 2 OBE Procedures, buat bandingkan
  public void addRow(int row, int row2, double scale) {
    for (int i = 0; i < this.getCol(); i++) {
      setELMT(row, i, getELMT(row, i) + getELMT(row2, i) * scale);
    } // Langsung pakai multipliedRowELMT
  }

  // Ambil suatu baris dikali konstanta
  // public double[] multipliedRowELMT(int row, double scale) {
  // double[] multipliedRow = new double[this.getCol()];
  // // aku pakai declare iterator dalam blok for
  // for (int i = 0; i < this.getCol(); i++) {
  // multipliedRow[i] = scale*getELMT(row, i);
  // }
  // return multipliedRow;
  // }

  // Operasi- operasi Matrix
  Matrix plus(Matrix M2) {
    int i, j;
    Matrix MHasil = new Matrix(getRow(), getCol());
    // cek dulu apakah ukuran this.Mat = M2.Mat, asumsikan dulu sesuai
    for (i = 0; i < getRow(); i++) {
      for (j = 0; j < getCol(); j++) {
        MHasil.setELMT(i, j, this.getELMT(i, j) + M2.getELMT(i, j));
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
        MHasil.setELMT(i, j, this.getELMT(i, j) - M2.getELMT(i, j));
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
          MHasil.setELMT(i, j, MHasil.getELMT(i, j) + this.getELMT(i, j) - M2.getELMT(i, j));
        }
      }
    }
    return MHasil;
  }

  void divideRow(int row, double divisor) {
    for (int j = 0; j < getCol(); j++) {
      double temp = this.getELMT(row, j);
      setELMT(row, j, getELMT(row, j) / 2);
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

  // jadi, tidak perlu memakai atribut isSquare
  public boolean isSquare() {
    return getRow() == getCol();
  }

  // calculate determinant, here in class Java
  public static boolean isSingular(Matrix Mat) {
    return detMatrixSegitiga(Mat) == 0;
  }
}
