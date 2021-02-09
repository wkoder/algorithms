import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class FileGenerator {
  public static void main(String[] args) throws IOException {
    printNumbers(1,1_000_000);
    printNumbers(1_000_000,1);
    printNumbers(100,10_000);
    printNumbers(10_000,100);
    printNumbers(100,1_000);
    printNumbers(1_000,100);
    printNumbers(1_000,1);
    printNumbers(1,1_000);
    printNumbers(1_000,1_000);
  }

  private static void printNumbers(int rows, int cols) throws IOException {
    try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(
        String.format("io/files/numbers-%sx%s.txt", rows, cols))))) {
      out.println(rows * cols);
      for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
          if (j > 0) {
            out.print(' ');
          }
          out.print(i*cols + j);
        }
        out.println();
      }
      out.flush();
    }
  }
}
