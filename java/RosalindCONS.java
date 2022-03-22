import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;

public class RosalindCONS {
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

    private static void writeData(String filePath, TreeMap<Character, List<Integer>> profile, String consensus) throws IOException {
        Path outputFile = Paths.get(filePath);

        Files.deleteIfExists(outputFile);
        Files.createDirectories(outputFile.getParent());
        Files.createFile(outputFile);

        Files.writeString(outputFile, consensus + "\n", StandardOpenOption.APPEND);

        for (Character key : profile.keySet()) {
            String joinedValues = profile.get(key).stream().map(String::valueOf).collect(Collectors.joining(" "));
            Files.writeString(outputFile, key + ": " + joinedValues + "\n", StandardOpenOption.APPEND);
        }
    }

    private static TreeMap<Character, List<Integer>> getProfile(HashMap<String,String> fastaMap) {
        TreeMap<Character, List<Integer>> profile = new TreeMap<>();

        for (String label : fastaMap.keySet()) {
            String dna = fastaMap.get(label);

            for (int i = 0; i < dna.length(); i++) {
                if (!profile.containsKey(dna.charAt(i))) {
                    List<Integer> values = new ArrayList<>(Collections.nCopies(dna.length(), 0));
                    profile.put(dna.charAt(i), values);
                }

                List<Integer> values = profile.get(dna.charAt(i));
                values.set(i, profile.get(dna.charAt(i)).get(i) + 1);
                profile.put(dna.charAt(i), values);
            }
        }

        return profile;
    }

    private static String getConsensus(TreeMap<Character, List<Integer>> profile) {
        List<Character> keys = new ArrayList<>(profile.keySet());

        String consensus = "";
        int consensusLength = profile.get(keys.get(0)).size();

        for (int i = 0; i < consensusLength; i++) {

            char mostCommonKey = 0;
            int mostKeyOccurrences = 0;

            for (Character key : keys) {
                int currentKeyOccurrences = profile.get(key).get(i);

                if (currentKeyOccurrences > mostKeyOccurrences) {
                    mostCommonKey = key;
                    mostKeyOccurrences = currentKeyOccurrences;
                }
            }

            consensus += mostCommonKey;
        }

        return consensus;
    }

    public static void main(String[] args) throws IOException {
        HashMap<String, String> data = readData("input/rosalind_cons.txt");
        TreeMap<Character, List<Integer>> profile = getProfile(data);
        String consensus = getConsensus(profile);
        writeData("output/rosalind_cons.txt", profile, consensus);
    }
}
