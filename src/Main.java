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
    private static float[]   initialStatePDVector; //Says matrix in assignment but should/could be vector;


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
            if (n == 0) { transisionMatrix = matrix; }
            if (n == 1) { emissionMatrix = matrix; }
            if (n == 2) { initialStatePDVector = matrix[0]; }
        }
        System.out.print(test());
        float[] nextStateVector = computeNextStateProb(initialStatePDVector, transisionMatrix, emissionMatrix);
        for(float test: nextStateVector){
            System.out.print(test + " ");
        }
        System.out.println();

        float[] emissionPVector = computeEmissionProb(emissionMatrix, nextStateVector);

        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("1 " + emissionPVector.length + " ");
        for (float emissionProp : emissionPVector) {
            resultBuilder.append(emissionProp + " ");
        }
        System.out.println(resultBuilder.toString().trim());
    }

    private static float[] computeNextStateProb(float[] initialStatePDVector, float[][] transisionMatrix, float[][] emissionMatrix) {
        float[] nextStateVector = new float[initialStatePDVector.length];
        for (int transitionRow = 0; transitionRow < transisionMatrix.length; transitionRow++) {
            for (int transitionCol = 0; transitionCol < transisionMatrix.length; transitionCol++) {
                nextStateVector[transitionCol] += initialStatePDVector[transitionRow] * transisionMatrix[transitionRow][transitionCol];
            }
        }
        return nextStateVector;
    }

    private static float[] computeEmissionProb(float[][] emissionMatrix, float[] stateVector) {
        float[] emissionPVector = new float[emissionMatrix[0].length];

        for (int emissionIndex = 0; emissionIndex < emissionPVector.length; emissionIndex++) {
            for (int stateProbIndex = 0; stateProbIndex < stateVector.length; stateProbIndex++) {
                emissionPVector[emissionIndex] += stateVector[stateProbIndex] * emissionMatrix[stateProbIndex][emissionIndex];
            }
        }
        return emissionPVector;
    }

    public static String test(){
        StringBuilder sb = new StringBuilder();
        sb.append("Transission Matrix");
        for(int row = 0; row < transisionMatrix.length; row++){
            sb.append('\n');
            for(int col = 0; col < transisionMatrix.length; col++){
                sb.append(transisionMatrix[row][col]);
                sb.append('\t');
            }
        }
        sb.append('\n');
        sb.append('\n');
        sb.append("Emission Matrix");
        for(int row = 0; row < emissionMatrix.length; row++){
            sb.append('\n');
            for(int col = 0; col < emissionMatrix[0].length; col++){
                sb.append(emissionMatrix[row][col]);
                sb.append('\t');
            }
        }
        sb.append('\n');
        sb.append('\n');
        return sb.toString();
    }
}
