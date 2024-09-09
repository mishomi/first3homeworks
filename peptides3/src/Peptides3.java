import java.util.*;

public class Peptides3 {

    String protein;
    List<String> library;
    Map<Long, List<Integer>> peptideMap = new HashMap<>();
    Map<Character, Integer> aminoAcidMap = new HashMap<>();

    public Peptides3() {
        initializeAminoAcidMap();
    }

    private void initializeAminoAcidMap() {
        for (int i = 0; i < 26; i++) {
            aminoAcidMap.put((char) ('A' + i), i);
        }
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public void setLibrary(List<String> library) {
        this.library = library;
    }

    long convertPeptideToLong(String peptide) {
        long value = 0;
        for (int i = 0; i < peptide.length(); i++) {
            value = value * 26 + aminoAcidMap.get(peptide.charAt(i));
        }
        return value;
    }

    public void initializePeptideMap(List<String> peptides) {
        for (String peptide : peptides) {
            long peptideLong = convertPeptideToLong(peptide);
            peptideMap.put(peptideLong, new ArrayList<>());
        }
    }

    public void findPositionsInProtein(String longProtein, int peptideLength) {
        int longProteinLength = longProtein.length();

        for (int i = 0; i <= longProteinLength - peptideLength; i++) {
            String kmer = longProtein.substring(i, i + peptideLength);
            long kmerLong = convertPeptideToLong(kmer);

            if (peptideMap.containsKey(kmerLong)) {
                peptideMap.get(kmerLong).add(i);
            }
        }
    }

    public Map<Long, List<Integer>> findPeptidePositions(String longProtein, List<String> peptides) {
        int peptideLength = 8;

        initializePeptideMap(peptides);
        findPositionsInProtein(longProtein, peptideLength);

        return peptideMap;
    }

    public void displayPeptidePositions(Map<Long, List<Integer>> peptideMap) {
        for (Map.Entry<Long, List<Integer>> entry : peptideMap.entrySet()) {
            System.out.println("Peptide (Long): " + entry.getKey() + ", Positions: " + entry.getValue());
        }
    }
}
