import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class RosalindHAMM {
    private static List<String> readData(String filePath) throws IOException {
        Path inputFile = Paths.get(filePath);
        return Files.readAllLines(inputFile);
    }

    private static void writeData(String filePath, Integer data) throws IOException {
        Path outputFile = Paths.get(filePath);

        Files.deleteIfExists(outputFile);
        Files.createDirectories(outputFile.getParent());
        Files.createFile(outputFile);

        Files.writeString(outputFile, Integer.toString(data));
    }

    private static Integer countMutations(String firstDNA, String secondDNA) {
        Integer mutations = 0;
        for (int i = 0; i < firstDNA.length(); i++) {
            if (firstDNA.charAt(i) != secondDNA.charAt(i))
            mutations++;
        }
        return mutations;
    }

    public static void main(String[] args) throws IOException {
        List<String> data = readData("input/rosalind_hamm.txt");
        Integer result = countMutations(data.get(0), data.get(1));
        writeData("output/rosalind_hamm.txt", result);
    }
}
