import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Matrix A: ");
        String[] dimensionsA = scanner.next().split(",");
        int rowA = Integer.parseInt(dimensionsA[0]);
        int columnA = Integer.parseInt(dimensionsA[1]);

        Multiply matrixA = new Multiply(rowA, columnA);
        matrixA.inputMatrix();

        System.out.print("Matrix B: ");
        String[] dimensionsB = scanner.next().split(",");
        int rowB = Integer.parseInt(dimensionsB[0]);
        int columnB = Integer.parseInt(dimensionsB[1]);

        if (columnA != rowB) {
            System.out.println("Matrix multiplication is not possible. Columns of A must equal rows of B.");
            return;
        }

        Multiply matrixB = new Multiply(rowB, columnB);
        matrixB.inputMatrix();
        

        Multiply resultMatrix = matrixA.multiply(matrixB);

        System.out.println("Matrix C:");
        resultMatrix.printMatrix();
    }
}

class Multiply {
    private int rows;
    private int columns;
    private int[][] matrix;

    public Multiply(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.matrix = new int[rows][columns];
    }

    public void inputMatrix() {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
    }

    public void printMatrix() {
        for (int i = 0; i < rows; i++) {
            System.out.print("| ");
            for (int j = 0; j < columns; j++) {
                System.out.printf("%-4d", matrix[i][j]);
            }
            System.out.println("|");
        }
    }

    public Multiply multiply(Multiply matrixB) {
        if (this.columns != matrixB.rows) {
            throw new IllegalArgumentException("Matrix multiplication is not possible.");
        }

        Multiply resultMatrix = new Multiply(this.rows, matrixB.columns);

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < matrixB.columns; j++) {
                resultMatrix.matrix[i][j] = 0;
                for (int k = 0; k < this.columns; k++) {
                    resultMatrix.matrix[i][j] += this.matrix[i][k] * matrixB.matrix[k][j];
                }
            }
        }

        return resultMatrix;
    }
}
