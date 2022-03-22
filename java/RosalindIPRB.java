import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class RosalindIPRB {
    private static int[] readData(String filePath) throws IOException {
        Path inputFile = Paths.get(filePath);
        String inputLine = Files.readAllLines(inputFile).get(0);
        return Arrays.stream(inputLine.split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    private static void writeData(String filePath, double data) throws IOException {
        Path outputFile = Paths.get(filePath);

        Files.deleteIfExists(outputFile);
        Files.createDirectories(outputFile.getParent());
        Files.createFile(outputFile);

        Files.writeString(outputFile,String.valueOf(data));
    }

    // k - homozygous dominant
    // m - heterozygous recessive
    // n - homozygous recessive
    private static double dominantAlleleProbability(int k, int m, int n) {
        int t = k + m + n;

        double pk = (double)k / t;
        double pm = (double)m / t;
        double pn = (double)n / t;

        // homozygous recessive & homozygous recessive
        double c1 = pn * ((double)(n - 1) / (double)(t - 1));

        // homozygous recessive & heterozygous recessive
        double c2 = 2 * pn * ((double)m / (double)(t - 1)) * 0.5;

        // heterozygous recessive & heterozygous recessive
        double c3 = pm * ((double)(m - 1) / (double)(t - 1)) * 0.25;

        return 1 - (c1 + c2 + c3);
    }

    public static void main(String[] args) throws IOException {
        int[] data = readData("input/rosalind_iprb.txt");
        double result = dominantAlleleProbability(data[0], data[1], data[2]);
        writeData("output/rosalind_iprb.txt", result);
    }
}
