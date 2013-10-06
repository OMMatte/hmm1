import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @author mathiaslindblom
 */
public class Main {
    private static float[][] transisionMatrix;
    private static float[][] emissionMatrix;
    private static float[][] initialStatePDMatrix;


    public static void main(String[] args) throws IOException {


        ArrayList<String[]> lines = new ArrayList<String[]>();

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String line;
        while ((line = in.readLine()) != null) {
            if (line.contains(";")) {
                break;
            }

            lines.add(line.split(" "));
        }

        for (int n = 0; n < 3; n++) {
            String[] stringArray = lines.get(n);
            int row = Integer.parseInt(stringArray[0]);
            int col = Integer.parseInt(stringArray[1]);
            float[][] matrix = new float[row][col];

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    matrix[i][j] = Float.parseFloat(stringArray[col * i + j + 2]);
                }
            }
            if(n == 0)transisionMatrix = matrix;
            if(n == 1)emissionMatrix = matrix;
            if(n == 2)initialStatePDMatrix = matrix;
        }
    }

    public Main() {
    }
}
