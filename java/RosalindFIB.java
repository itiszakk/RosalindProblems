import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class RosalindFIB {
    private static int[] readData(String filePath) throws IOException {
        Path inputFile = Paths.get(filePath);
        String inputLine = Files.readAllLines(inputFile).get(0);
        return Arrays.stream(inputLine.split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    private static void writeData(String filePath, long data) throws IOException {
        Path outputFile = Paths.get(filePath);

        Files.deleteIfExists(outputFile);
        Files.createDirectories(outputFile.getParent());
        Files.createFile(outputFile);

        Files.writeString(outputFile,String.valueOf(data));
    }

    private static long countRabbitsPairs(int months, int litter) {
        if (months <= 2) return 1;
        return countRabbitsPairs(months - 1, litter) + litter * countRabbitsPairs(months - 2, litter);
    }

    public static void main(String[] args) throws IOException {
        int[] data = readData("input/rosalind_fib.txt");
        long result = countRabbitsPairs(data[0], data[1]);
        writeData("output/rosalind_fib.txt", result);
    }
}
