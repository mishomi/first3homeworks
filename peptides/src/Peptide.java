
import java.util.*;

public class Peptide {
    public static final int DEFAULT_PEPTIDE_SIZE = 8;
    String protein;
    List<String> library;
    Map<String, List<Integer>> peptideMap = new HashMap<>();


    public void setProtein(String protein) {
        this.protein = protein;
    }

    public void setLibrary(List<String> library) {
        this.library = library;
    }

    public void initializePeptideMap(List<String> peptides) {
        for (String peptide : peptides) {
            peptideMap.put(peptide, new ArrayList<>());
        }
    }

    public  void findPositionsInProtein(String longProtein, int peptideLength) {
        int longProteinLength = longProtein.length();

        for (int i = 0; i <= longProteinLength - peptideLength; i++) {
            String kmer = longProtein.substring(i, i + peptideLength);

            if (peptideMap.containsKey(kmer)) {
                peptideMap.get(kmer).add(i);
            }
        }
    }

    public  Map<String, List<Integer>> findPeptidePositions(String longProtein, List<String> peptides) {
        int peptideLength = 8;

        initializePeptideMap(peptides);

        findPositionsInProtein(longProtein, peptideLength);

        return peptideMap;
    }

    public  void dsisplayPeptidePositions(Map<String, List<Integer>> peptideMap) {
        for (Map.Entry<String, List<Integer>> entry : peptideMap.entrySet()) {
            System.out.println("Peptide: " + entry.getKey() + ", Positions: " + entry.getValue());
        }
    }



}