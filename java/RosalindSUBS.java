import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RosalindSUBS {
    private static List<String> readData(String filePath) throws IOException {
        Path inputFile = Paths.get(filePath);
        return Files.readAllLines(inputFile);
    }

    private static void writeData(String filePath, ArrayList<Integer> data) throws IOException {
        Path outputFile = Paths.get(filePath);

        Files.deleteIfExists(outputFile);
        Files.createDirectories(outputFile.getParent());
        Files.createFile(outputFile);

        String joinedValues = data.stream().map(String::valueOf).collect(Collectors.joining(" "));
        Files.writeString(outputFile, joinedValues);
    }

    private static ArrayList<Integer> getSubstringPositions(String dna, String sub) {
        ArrayList<Integer> positions = new ArrayList<>();

        for (int i = 0; i < dna.length(); i++) {
            if (dna.startsWith(sub, i))
                positions.add(i + 1);
        }

        return positions;
    }

    public static void main(String[] args) throws IOException {
        List<String> data = readData("input/rosalind_subs.txt");
        ArrayList<Integer> result = getSubstringPositions(data.get(0), data.get(1));
        writeData("output/rosalind_subs.txt", result);
    }
}
