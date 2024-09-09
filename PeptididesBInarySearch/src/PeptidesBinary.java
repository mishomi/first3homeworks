import java.util.*;

public class PeptidesBinary {
    String protein;
    List<String> library;
    long[] peptideArray;
    Map<Long, List<Integer>> positionsMap = new HashMap<>();

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public void setLibrary(List<String> library) {
        this.library = library;
    }

    public long convertPeptideToLong(String peptide) {
        long result = 0;
        for (int i = 0; i < peptide.length(); i++) {
            result = result * 26 + (peptide.charAt(i) - 'A');
        }
        return result;
    }

    public void initializePeptideArray() {
        peptideArray = library.stream().mapToLong(this::convertPeptideToLong).toArray();
        Arrays.sort(peptideArray);
    }

    public boolean binarySearchPeptide(long kmerAsLong) {
        int left = 0, right = peptideArray.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (peptideArray[mid] == kmerAsLong) {
                return true;
            } else if (peptideArray[mid] < kmerAsLong) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }

    public void findPositionsInProtein(String longProtein, int peptideLength) {
        int longProteinLength = longProtein.length();

        for (int i = 0; i <= longProteinLength - peptideLength; i++) {
            String kmer = longProtein.substring(i, i + peptideLength);
            long kmerAsLong = convertPeptideToLong(kmer);

            if (binarySearchPeptide(kmerAsLong)) {
                positionsMap.computeIfAbsent(kmerAsLong, k -> new ArrayList<>()).add(i);
            }
        }
    }

    public Map<Long, List<Integer>> findPeptidePositions(String longProtein) {
        int peptideLength = 8;

        initializePeptideArray();

        findPositionsInProtein(longProtein, peptideLength);

        return positionsMap;
    }

    public void displayPeptidePositions(Map<Long, List<Integer>> positionsMap) {
        for (Map.Entry<Long, List<Integer>> entry : positionsMap.entrySet()) {
            long peptideAsLong = entry.getKey();
            List<Integer> positions = entry.getValue();
            System.out.println("Peptide (long): " + peptideAsLong + ", Positions: " + positions);
        }
    }
}
