import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RosalindGC {
    private static double getGCContent(String dna) {
        int count = 0;

        for (int i = 0; i < dna.length(); i++) {
            if (dna.charAt(i) == 'G' || dna.charAt(i) == 'C')
                count++;
        }

        return 100 * ((double) count / (double) dna.length());
    }

    private static HashMap<String, String> readData(String filePath) throws IOException {
        Path inputFile = Paths.get(filePath);
        List<String> inputLines = Files.readAllLines(inputFile);

        HashMap<String, String> fastaMap = new HashMap<>();
        String label = "";

        for (String line : inputLines) {
            if (line.charAt(0) == '>') {
                label = line.substring(1);
                fastaMap.put(label, "");
                continue;
            }

            fastaMap.put(label, fastaMap.get(label) + line);
        }

        return fastaMap;
    }

    private static void writeData(String filePath, Map.Entry<String, Double> data) throws IOException {
        Path outputFile = Paths.get(filePath);

        Files.deleteIfExists(outputFile);
        Files.createDirectories(outputFile.getParent());
        Files.createFile(outputFile);

        Files.writeString(outputFile, data.getKey() + "\n" + data.getValue());
    }

    private static Map.Entry<String, Double> findMaxGCContent(HashMap<String,String> fastaMap) {
        String maxGCLabel = "";
        double maxGCContent = 0.0;

        for (String label : fastaMap.keySet()) {
            double gcContent = getGCContent(fastaMap.get(label));

            if (gcContent > maxGCContent) {
                maxGCLabel = label;
                maxGCContent = gcContent;
            }
        }

        return Map.entry(maxGCLabel, maxGCContent);
    }

    public static void main(String[] args) throws IOException {
        HashMap<String, String> data = readData("input/rosalind_gc.txt");
        Map.Entry<String, Double> result = findMaxGCContent(data);
        writeData("output/rosalind_gc.txt", result);
    }
}
