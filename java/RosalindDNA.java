import java.io.IOException;
import java.nio.file.*;
import java.util.TreeMap;

public class RosalindDNA {
    private static String readData(String filePath) throws IOException {
        Path inputFile = Paths.get(filePath);
        return Files.readString(inputFile);
    }

    private static void writeData(String filePath, TreeMap<Character, Integer> data) throws IOException {
        Path outputFile = Paths.get(filePath);

        Files.deleteIfExists(outputFile);
        Files.createDirectories(outputFile.getParent());
        Files.createFile(outputFile);

        for (int value : data.values()) {
            Files.writeString(outputFile, Integer.toString(value) + " ", StandardOpenOption.APPEND);
        }

    }

    private static TreeMap<Character, Integer> countNucleotides(String dna) {
        TreeMap<Character, Integer> counters = new TreeMap<>();

        for (int i = 0; i < dna.length(); i++) {
            int count = counters.getOrDefault(dna.charAt(i), 0);
            counters.put(dna.charAt(i), count + 1);
        }

        return counters;
    }

    public static void main(String[] args) throws IOException {
        String data = readData("input/rosalind_dna.txt");
        TreeMap<Character, Integer> result = countNucleotides(data);
        writeData("output/rosalind_dna.txt", result);
    }
}
