import java.util.*;

public class Benchmark {

    private static final int PROTEIN_SIZE = 10_000;
    private static final int LIBRARY_SIZE = 100_000;
    static final byte[] ALPHABET = new byte[26];

    static {
        for (byte c = 'A'; c <= 'Z'; c++) {
            ALPHABET[c - 'A'] = c;
        }
    }

    public static void main(String[] args) {

        System.out.println("Generating data...");

        String protein = generateProtein(PROTEIN_SIZE);
        List<String> library = generateLibrary(LIBRARY_SIZE);
        PeptidesBinary peptidesBinary = new PeptidesBinary();
        peptidesBinary.setProtein(protein);
        peptidesBinary.setLibrary(library);

        System.out.println("Searching peptides...");
        long start = System.currentTimeMillis();
        Map<Long, List<Integer>> positionsMap = peptidesBinary.findPeptidePositions(protein);
        long stop = System.currentTimeMillis();

        peptidesBinary.displayPeptidePositions(positionsMap);
        System.out.println("Elapsed: " + (stop - start));
    }

    static String generateProtein(int proteinSize) {
        Random r = new Random();
        var data = new byte[proteinSize];
        for (int i = 0; i < proteinSize; i++) {
            data[i] = ALPHABET[r.nextInt(ALPHABET.length)];
        }
        return new String(data);
    }

    private static List<String> generateLibrary(int librarySize) {
        var library = new ArrayList<String>(librarySize);
        for (int i = 0; i < librarySize; i++) {
            var peptide = generateProtein(PROTEIN_SIZE);
            library.add(peptide);
        }
        return library;
    }
}
