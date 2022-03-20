import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class RosalindHAMM {
    public static class Dataset {
        public String firstDNA;
        public String secondDNA;

        public Dataset(String firstDNA, String secondDNA) {
            this.firstDNA = firstDNA;
            this.secondDNA = secondDNA;
        }
    }

    private static Dataset readData(String filePath) throws IOException {
        Path inputFile = Paths.get(filePath);
        List<String> lines = Files.readAllLines(inputFile);
        return new Dataset(lines.get(0), lines.get(1));
    }

    private static void writeData(String filePath, Integer data) throws IOException {
        Path outputFile = Paths.get(filePath);

        Files.deleteIfExists(outputFile);
        Files.createDirectories(outputFile.getParent());
        Files.createFile(outputFile);

        Files.writeString(outputFile, Integer.toString(data));
    }

    private static Integer countMutations(Dataset data) {
        Integer mutations = 0;
        for (int i = 0; i < data.firstDNA.length(); i++) {
            if (data.firstDNA.charAt(i) != data.secondDNA.charAt(i))
                mutations++;
        }
        return mutations;
    }

    public static void main(String[] args) throws IOException {
        Dataset data = readData("input/rosalind_hamm.txt");
        Integer result = countMutations(data);
        writeData("output/rosalind_hamm.txt", result);
    }
}
