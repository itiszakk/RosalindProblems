import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class RosalindPROT {
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

    private static HashMap<String, String> getCodonsTranslationMap() {
        HashMap<String, String> codonsTranslationMap = new HashMap<>();
        codonsTranslationMap.put("UUU", "F");
        codonsTranslationMap.put("CUU", "L");
        codonsTranslationMap.put("AUU", "I");
        codonsTranslationMap.put("GUU", "V");
        codonsTranslationMap.put("UUC", "F");
        codonsTranslationMap.put("CUC", "L");
        codonsTranslationMap.put("AUC", "I");
        codonsTranslationMap.put("GUC", "V");
        codonsTranslationMap.put("UUA", "L");
        codonsTranslationMap.put("CUA", "L");
        codonsTranslationMap.put("AUA", "I");
        codonsTranslationMap.put("GUA", "V");
        codonsTranslationMap.put("UUG", "L");
        codonsTranslationMap.put("CUG", "L");
        codonsTranslationMap.put("AUG", "M");
        codonsTranslationMap.put("GUG", "V");
        codonsTranslationMap.put("UCU", "S");
        codonsTranslationMap.put("CCU", "P");
        codonsTranslationMap.put("ACU", "T");
        codonsTranslationMap.put("GCU", "A");
        codonsTranslationMap.put("UCC", "S");
        codonsTranslationMap.put("CCC", "P");
        codonsTranslationMap.put("ACC", "T");
        codonsTranslationMap.put("GCC", "A");
        codonsTranslationMap.put("UCA", "S");
        codonsTranslationMap.put("CCA", "P");
        codonsTranslationMap.put("ACA", "T");
        codonsTranslationMap.put("GCA", "A");
        codonsTranslationMap.put("UCG", "S");
        codonsTranslationMap.put("CCG", "P");
        codonsTranslationMap.put("ACG", "T");
        codonsTranslationMap.put("GCG", "A");
        codonsTranslationMap.put("UAU", "Y");
        codonsTranslationMap.put("CAU", "H");
        codonsTranslationMap.put("AAU", "N");
        codonsTranslationMap.put("GAU", "D");
        codonsTranslationMap.put("UAC", "Y");
        codonsTranslationMap.put("CAC", "H");
        codonsTranslationMap.put("AAC", "N");
        codonsTranslationMap.put("GAC", "D");
        codonsTranslationMap.put("CAA", "Q");
        codonsTranslationMap.put("AAA", "K");
        codonsTranslationMap.put("GAA", "E");
        codonsTranslationMap.put("CAG", "Q");
        codonsTranslationMap.put("AAG", "K");
        codonsTranslationMap.put("GAG", "E");
        codonsTranslationMap.put("UGU", "C");
        codonsTranslationMap.put("CGU", "R");
        codonsTranslationMap.put("AGU", "S");
        codonsTranslationMap.put("GGU", "G");
        codonsTranslationMap.put("UGC", "C");
        codonsTranslationMap.put("CGC", "R");
        codonsTranslationMap.put("AGC", "S");
        codonsTranslationMap.put("GGC", "G");
        codonsTranslationMap.put("CGA", "R");
        codonsTranslationMap.put("AGA", "R");
        codonsTranslationMap.put("GGA", "G");
        codonsTranslationMap.put("UGG", "W");
        codonsTranslationMap.put("CGG", "R");
        codonsTranslationMap.put("AGG", "R");
        codonsTranslationMap.put("GGG", "G");
        codonsTranslationMap.put("UAA", "STOP");
        codonsTranslationMap.put("UAG", "STOP");
        codonsTranslationMap.put("UGA", "STOP");
        return codonsTranslationMap;
    }

    private static String translateIntoProtein(String rna) {
        String protein = "";
        HashMap<String, String> codonsTranslationMap = getCodonsTranslationMap();

        for (int i = 0; i < rna.length() - 3; i += 3) {
            String codon = rna.substring(i, i + 3);
            String aminoAcid = codonsTranslationMap.get(codon);

            if (aminoAcid.equals("STOP"))
                break;

            protein += aminoAcid;
        }

        return protein;
    }

    public static void main(String[] args) throws IOException {
        String data = readData("input/rosalind_prot.txt");
        String result = translateIntoProtein(data);
        writeData("output/rosalind_prot.txt", result);
    }
}
