import java.util.*;

public class Benchmark {

    private static final int PROTEIN_SIZE = 10_000;
    private static final int LIBRARY_SIZE = 100_000;
    static final byte[] ALPHABET = new byte[26];

    static {
        for (byte c = 'A'; c <= 'Z'; c++) {
            ALPHABET[c-'A'] = c;
        }
    }

    public static void main(String[] args) {

        System.out.println("generating data...");

        String protein = generateProtein(PROTEIN_SIZE);
        List<String> library = generateLibrary(LIBRARY_SIZE);
        Peptides3 peptides3 = new Peptides3();
        peptides3.setProtein(protein);
        peptides3.setLibrary(library);

        System.out.println("searching peptides...");
        long start = System.currentTimeMillis();
        Map<Long, List<Integer>> peptideMap = peptides3.findPeptidePositions(protein, library);
        long stop = System.currentTimeMillis();

        peptides3.displayPeptidePositions(peptideMap);

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
