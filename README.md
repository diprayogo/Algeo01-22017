# Algeo01-22017
# Tugas Besar 1 IF2123 Aljabar Linier dan Geometri Sistem Persamaan Linier, Determinan, dan Aplikasinya Semester I Tahun 2023/2024

# Kelompok 50
##  Table of contents

- <a href="#description">Deskripsi</a>
- <a href="#how-to-run">Cara Menggunakan Program</a>

# Tubes Algeo01-21044
<h2 id="description">Deskripsi</h2>
Tugas besar ini berisi program dalam bahasa Java yang berisi fungsi-fungsi eliminasi Gauss, eliminasi Gauss-Jordan, menentukan balikan matriks, menghitung determinan, kaidah Cramer (kaidah Cramer khusus untuk SPL dengan n peubah dan n persamaan), interpolasi polinomial, interpolasi bicubic spline, dan regresi linier berganda. Detail terkait program ini bisa dilihat di <a href="doc/Algeo01-22017.pdf">file laporan</a>.

## File structure

Algeo01-21017/ <br>
┣ bin/ <br>
┃ ┣ Aplikasi/ <br>
┃ ┃ ┣ BicubicInterpolationApp.class <br>
┃ ┃ ┣ DeterminantApp.class <br>
┃ ┃ ┣ InterpolasiApp.class <br>
┃ ┃ ┣ InverseApp.class <br>
┃ ┃ ┣ RLBApp.class <br>
┃ ┃ ┗ SPLApp.class <br>
┃ ┣ Matrix/ <br>
┃ ┃ ┣ Determinant.class <br>
┃ ┃ ┣ Inverse.class <br>
┃ ┃ ┣ Matrix.class <br>
┃ ┃ ┗ SPL.class <br>
┃ ┣ Utils/ <br>
┃ ┃ ┗ Utils.class <br>
┃ ┗ Main.class <br> 
┣ lib/ <br>
┣ src/ <br>
┃ ┣ Aplikasi/ <br>
┃ ┃ ┣ BicubicInterpolationApp.java <br> 
┃ ┃ ┣ DeterminantApp.java <br> 
┃ ┃ ┣ InterpolasiApp.java <br> 
┃ ┃ ┣ InverseApp.java <br>
┃ ┃ ┣ RLBApp.java <br>
┃ ┃ ┗ SPLApp.java <br>
┃ ┣ Matrix/ <br>
┃ ┃ ┣ Determinant.java <br>
┃ ┃ ┣ Inverse.java <br>
┃ ┃ ┣ Matrix.java <br>
┃ ┃ ┗ SPL.java <br>
┃ ┣ Utils/ <br>
┃ ┃ ┗ Utils.java <br>
┃ ┗ Main.java <br>
┣ test/ <br>
┃ ┣ input/ <br>
┃ ┗ output/ <br>
┣ .gitignore <br>
┗ README.md <br>

<h2 id="how-to-run">Cara Menggunakan Program</h2>
## How to run

Clone this repo https://github.com/chaerla/Algeo01-22017.git

### *Compile*
*Compile* program Java bisa dilakukan dengan cara:
```bash
sh compile.sh
```
Atau, untuk *compile* program ke bytecode saja di folder bin bisa dengan:
```bash
cd src
javac -d ../bin ./*.java
```
Lalu, untuk *compile* hasil bytecode ke file .jar di folder bin bisa dengan:
```bash
cd bin
jar -cvfm matrix.jar ../src/META-INF/MANIFEST.MF *
```

### *Run*
Untuk menjalankan program yang telah di-*compile*, bisa dengan menjalankan file .jar ataupun bytecode.

#### Bytecode
Untuk menjalankan program dari bytecode bisa dengan:

```
cd bin
java Main
```
#### File .jar
Sedangkan untuk menjalankan program dari file .jar bisa dengan:

```bash
cd bin
java -jar <jar-file-name>
```
