import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RosalindSUBS {
    public static class Dataset {
        String dna;
        String sub;

        public Dataset(String dna, String sub) {
            this.dna = dna;
            this.sub = sub;
        }
    }

    private static Dataset readData(String filePath) throws IOException {
        Path inputFile = Paths.get(filePath);
        List<String> lines = Files.readAllLines(inputFile);
        return new Dataset(lines.get(0), lines.get(1));
    }

    private static void writeData(String filePath, List<Integer> data) throws IOException {
        Path outputFile = Paths.get(filePath);

        Files.deleteIfExists(outputFile);
        Files.createDirectories(outputFile.getParent());
        Files.createFile(outputFile);

        String joinedList = data.stream().map(String::valueOf).collect(Collectors.joining(" "));

        Files.writeString(outputFile, joinedList);
    }

    private static List<Integer> getSubstringPositions(Dataset data) {
        List<Integer> positions = new ArrayList<>();

        for (int i = 0; i < data.dna.length(); i++) {
            if (data.dna.startsWith(data.sub, i)) {
                positions.add(i + 1);
            }
        }

        return positions;
    }

    public static void main(String[] args) throws IOException {
        Dataset data = readData("input/rosalind_subs.txt");
        List<Integer> result = getSubstringPositions(data);
        writeData("output/rosalind_subs.txt", result);
    }
}
