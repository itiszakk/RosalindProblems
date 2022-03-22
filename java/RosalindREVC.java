import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;

public class RosalindREVC {
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

    private static String getReverseComplement(String dna) {
        HashMap<Character, Character> complements = new HashMap<>();
        complements.put('A', 'T');
        complements.put('T', 'A');
        complements.put('C', 'G');
        complements.put('G', 'C');

        StringBuilder reverseComplement = new StringBuilder(dna.length());

        for (int i = dna.length() - 1; i >= 0; i--) {
            reverseComplement.append(complements.get(dna.charAt(i)));
        }

        return reverseComplement.toString();
    }

    public static void main(String[] args) throws IOException {
        String data = readData("input/rosalind_revc.txt");
        String result = getReverseComplement(data);
        writeData("output/rosalind_revc.txt", result);
    }
}
