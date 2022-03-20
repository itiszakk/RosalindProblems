import java.io.IOException;
import java.nio.file.*;

public class RosalindRNA {
    private static String readData(String filePath) throws IOException {
        Path inputFile = Paths.get(filePath);
        return Files.readAllLines(inputFile).get(0);
    }

    private static void writeData(String filePath, String data) throws IOException {
        Path outputFile = Paths.get(filePath);

        Files.deleteIfExists(outputFile);
        Files.createDirectories(outputFile.getParent());
        Files.createFile(outputFile);

        Files.writeString(outputFile, data);
    }

    private static String transcribeToRNA(String dna) {
        return dna.replace('T', 'U');
    }

    public static void main(String[] args) throws IOException {
        String data = readData("input/rosalind_rna.txt");
        String result = transcribeToRNA(data);
        writeData("output/rosalind_rna.txt", result);
    }
}
