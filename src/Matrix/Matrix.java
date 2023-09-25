package Matrix;

import java.util.Scanner;

public class Matrix {
  Scanner scan = new Scanner(System.in);

  // Atribut
  // atribut default ukuran baris dan kolom, amankah sesuai harapan?
  int rowSize;
  int colSize;
  float[][] Mat;
  // atribut isSquare untuk Matrix persegi
  boolean isSquare = (rowSize == colSize) ? true : false;
  boolean isAugmented = false; // default

  // Methods
  // coba bikin konstruktor
  public Matrix(int rowSize, int colSize) {
    this.rowSize = rowSize;
    this.colSize = colSize;
    this.Mat = new float[rowSize][colSize];

    int i, j;
    for (i = 0; i < rowSize; i++) {
      for (j = 0; j < colSize; j++) {
        this.Mat[i][j] = 0;
      }
    }
  }

  // overloading for default params
  public Matrix() {
    this.rowSize = 10;
    this.colSize = 10;
    this.Mat = new float[rowSize][colSize];

    int i, j;
    for (i = 0; i < rowSize; i++) {
      for (j = 0; j < colSize; j++) {
        this.Mat[i][j] = 0;
      }
    }
  }

  // read elemennya ntar dulu yaaa
  public void readMatrix(int n, int m) {
    int i, j;
    for (i = 0; i < n; i++) {
      for (j = 0; j < m; j++) {
        this.Mat[i][j] = scan.nextInt();
      }
    }
  }

  public void printMatrix(int n, int m) {
    int i, j;
    for (i = 0; i < n; i++) {
      for (j = 0; j < m; j++) {
        System.out.print(this.Mat[i][j] + " ");
      }
      System.out.println();
    }
  }

  // void transpose(int n, int m, int Matt[][]) {
  // int i, j;
  // for (i = 0; i < n; i++) {
  // for (j = 0; j < m; j++) {
  // Matt[j][i] = this.Mat[i][j];
  // }
  // }
  // }

  public float getElemen(int row, int col) {
    return Mat[row][col];
  }

  void transpose() {
    int i, j;
    float[][] tempMat = new float[this.rowSize][this.colSize];
    int temp;

    tempMat = this.Mat;
    for (i = 0; i < this.rowSize; i++) {
      for (j = 0; j < this.colSize; j++) {
        this.Mat[j][i] = tempMat[i][j];
      }
    }
    temp = this.rowSize;
    this.colSize = this.rowSize;
    this.rowSize = temp;
  }

  // Operasi- operasi Matrix
  Matrix plus(Matrix M2) {
    int i, j;
    Matrix MHasil = new Matrix(this.rowSize, this.colSize);
    // cek dulu apakah ukuran this.Mat = M2.Mat, asumsikan dulu sesuai
    for (i = 0; i < this.rowSize; i++) {
      for (j = 0; j < this.colSize; j++) {
        MHasil.Mat[i][j] = this.Mat[i][j] + M2.Mat[i][j];
      }
    }
    return MHasil;
  }

  Matrix minus(Matrix M2) {
    int i, j;
    Matrix MHasil = new Matrix(this.rowSize, this.colSize);
    // cek dulu apakah ukuran this.Mat = M2.Mat, asumsikan dulu sesuai
    for (i = 0; i < this.rowSize; i++) {
      for (j = 0; j < this.colSize; j++) {
        MHasil.Mat[i][j] = this.Mat[i][j] - M2.Mat[i][j];
      }
    }
    return MHasil;
  }

  Matrix mult(Matrix M2) {
    int i, j, k;
    Matrix MHasil = new Matrix(this.rowSize, this.colSize);
    // cek dulu apakah ukuran this.Mat = M2.Mat, asumsikan dulu sesuai
    for (i = 0; i < this.rowSize; i++) {
      for (j = 0; j < this.colSize; j++) {
        MHasil.Mat[i][j] = 0;
        for (k = 0; k < this.colSize; k++) {
          MHasil.Mat[i][j] += this.Mat[i][k] * M2.Mat[k][j];
        }
      }
    }
    return MHasil;
  }

  void scalarMultiply(float scale) {
    int i, j;
    for (i = 0; i < this.rowSize; i++) {
      for (j = 0; j < this.colSize; j++) {
        this.Mat[i][j] *= scale;
      }
    }
  }

  // 3 OBE Operations
  void swapRow(int row1, int row2) {
    // swap doang gess
    float[] temp;
    temp = this.Mat[row1];
    this.Mat[row1] = this.Mat[row2];
    this.Mat[row2] = temp;
  }

  void divideRow(int row, float divisor) {
    for (int i = 0; i < this.colSize; i++) {
      float temp = this.Mat[row][i];
      this.Mat[row][i] /= divisor;
      if (this.Mat[row][i] == -0.00) {
        this.Mat[row][i] = 0;
      }
      if (Float.isNaN(this.Mat[row][i])) {
        this.Mat[row][i] = 0;
      }
      if (Float.isInfinite(this.Mat[row][i])) {
        this.Mat[row][i] = temp;
      }
    }
  }

  void subtractRow(int row, int subtractorRow, float subtractorMagnitude) {
    for (int i = 0; i < this.colSize; i++) {
      this.Mat[row][i] -= this.Mat[subtractorRow][i] * subtractorMagnitude;
    }
  }

  // strictGauss: leading 1 must be perfectly diagonalized
  void strictGauss() {
    // n: row/col, gausa pake param ini, kan udah didefine sebagai atribut

    // bikin tiap generate leading 1 dari baris atas ke bawah
    for (int i = 0; i < this.rowSize; i++) {
      // biar jadi Matrix eselon, harus bikin calon leading 1 yg mungkin blm 1
      if (this.Mat[0][0] == 0) {
        for (int j = 1; j < this.rowSize; j++) {
          if (this.Mat[j][0] != 0) {
            this.swapRow(0, j);
            break;
          }
        }
      }
      // ga bakal ada kasus 0 semuanya, karena pasti setiap variabel berguna

      // mari kita buat tiap baris menjadi leading 1
      // blum bikin kasus untuk yg variabelnya habis kan bakal i != j
      // track col sampe 0 nya habis
      this.divideRow(i, this.Mat[i][i]); // harusnya ini row cari dulu yg leading one

      // kurangi semua nilai kolom dari baris2 di bawah supaya baris tsb punya leading
      // 1
      for (int j = i + 1; j < this.rowSize; j++) {
        this.subtractRow(j, i, this.Mat[j][i]);
      }
    }
  }

  // strictGaussJordan: leading 1 must be perfectly diagonalized
  void strictGaussJordan() {
    this.strictGauss();
    for (int i = 1; i < this.rowSize; i++) {
      // seharusnya cari dulu leading one
      // kurangi baris2 di atas agar lebih tereduksi
      for (int j = i - 1; j >= 0; j--) {
        this.subtractRow(j, i, this.Mat[j][i]);
      }
    }
  }

  // Beberapa prosedur untuk manipulasi Matrix
  // prosedur mengubah suatu Matrix persegi menjadi Matrix minor
  void minor() {
    // cari minor entri secara rekursif
  }

  void kofaktor() {
    this.minor();
  }

  void adjoin() {
    this.kofaktor();
    this.transpose();
  }

  float determinant() {
    float det = 1;
    return det;
  }

  void inverseEkspansiKofaktor() {
    float inverseDet = 1 / this.determinant();
    this.adjoin();
    this.scalarMultiply(inverseDet);
  }

  void inverseGJIdentity() {
    int i, j;
    Matrix augmentedMatrix = new Matrix(this.rowSize, 2 * this.colSize);
    // copy matrix persegi ke bagian kiri
    for (i = 0; i < augmentedMatrix.rowSize; i++) {
      for (j = 0; j < this.colSize; j++) {
        augmentedMatrix.Mat[i][j] = this.Mat[i][j];
      }
    }

    // copy matriks identitas bagian kanan
    for (i = 0; i < augmentedMatrix.rowSize; i++) {
      for (j = this.colSize; j < augmentedMatrix.colSize; j++) {
        if (i == j % this.colSize) {
          augmentedMatrix.Mat[i][j] = 1;
        } else {
          augmentedMatrix.Mat[i][j] = 0;
        }
      }
    }

    // lakukan operasi Gauss Jordan agar diperoleh submatriks identitas kiri
    augmentedMatrix.strictGaussJordan();
    boolean hasInverse = true;
    for (i = 0; i < augmentedMatrix.rowSize; i++) {
      if (augmentedMatrix.Mat[i][i] == 0) {
        // Matrix tidak memiliki invers
        hasInverse = false;
        break;
      }
    }

    // jika matriks memiliki invers, maka ubah this.Mat ke bentuk inversnya
    if (hasInverse) {
      for (i = 0; i < augmentedMatrix.rowSize; i++) {
        for (j = this.colSize; j < augmentedMatrix.colSize; j++) {
          this.Mat[i][j % this.colSize] = augmentedMatrix.Mat[i][j];
        }
      }
    } else { // jika tidak, do nothing
      return;
    }
  }
}
