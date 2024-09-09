import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class PeptidesBinaryTest {
    PeptidesBinary peptide;

    @Before
    public void init() {
        peptide = new PeptidesBinary();
        peptide.setProtein("ABCDEFGHBCDEFGHABCDEFGHBCDEFGHABCDEFGHBCDEFGH");
        peptide.setLibrary(Arrays.asList("ABCDEFGH", "BCDEFGHB", "EFGHABCD"));
    }

    @Test
    public void initializationParameters() {
        assertNotNull(peptide.protein);
        assertEquals(0, peptide.positionsMap.size());
    }

    @Test
    public void testIfArrayInitializedAndSorted() {
        peptide.initializePeptideArray();
        long[] expectedArray = {
                peptide.convertPeptideToLong("ABCDEFGH"),
                peptide.convertPeptideToLong("BCDEFGHB"),
                peptide.convertPeptideToLong("EFGHABCD")
        };
        Arrays.sort(expectedArray);
        assertArrayEquals(expectedArray, peptide.peptideArray);
    }

    @Test
    public void testBinarySearch() {
        peptide.initializePeptideArray();
        assertTrue(peptide.binarySearchPeptide(peptide.convertPeptideToLong("ABCDEFGH")));
        assertTrue(peptide.binarySearchPeptide(peptide.convertPeptideToLong("BCDEFGHB")));
        assertFalse(peptide.binarySearchPeptide(peptide.convertPeptideToLong("ZZZZZZZZ")));  // Not in library
    }

    @Test
    public void testIfFoundCorrectPositions() {
        peptide.initializePeptideArray();
        peptide.findPositionsInProtein(peptide.protein, 8);

        HashMap<Long, List<Integer>> expectedMap = new HashMap<>();
        expectedMap.put(peptide.convertPeptideToLong("ABCDEFGH"), List.of(0, 15, 30));
        expectedMap.put(peptide.convertPeptideToLong("BCDEFGHB"), List.of(1, 16, 31));
        expectedMap.put(peptide.convertPeptideToLong("EFGHABCD"), List.of(11, 26));

        assertEquals(expectedMap, peptide.positionsMap);
    }

    @Test
    public void testEmptyProtein() {
        peptide.setProtein("");
        peptide.initializePeptideArray();
        peptide.findPositionsInProtein(peptide.protein, 8);

        assertTrue(peptide.positionsMap.isEmpty());
    }

    @Test
    public void testEmptyLibrary() {
        peptide.setLibrary(Arrays.asList());
        peptide.initializePeptideArray();
        peptide.findPositionsInProtein(peptide.protein, 8);

        assertTrue(peptide.positionsMap.isEmpty());
    }

    @Test
    public void testProteinSmallerThanPeptideLength() {
        peptide.setProtein("ABC");
        peptide.initializePeptideArray();
        peptide.findPositionsInProtein(peptide.protein, 8);

        assertTrue(peptide.positionsMap.isEmpty());
    }

    @Test
    public void testPeptidesNotFound() {
        peptide.setLibrary(Arrays.asList("ZZZZZZZZ", "YYYYYYYY", "XXXXXXXX"));
        peptide.initializePeptideArray();
        peptide.findPositionsInProtein(peptide.protein, 8);

        assertTrue(peptide.positionsMap.isEmpty());
    }

    @Test
    public void testDuplicatePeptidesInLibrary() {
        peptide.setLibrary(Arrays.asList("ABCDEFGH", "ABCDEFGH", "BCDEFGHB"));
        peptide.initializePeptideArray();
        peptide.findPositionsInProtein(peptide.protein, 8);

        HashMap<Long, List<Integer>> expectedMap = new HashMap<>();
        expectedMap.put(peptide.convertPeptideToLong("ABCDEFGH"), List.of(0, 15, 30));
        expectedMap.put(peptide.convertPeptideToLong("BCDEFGHB"), List.of(1, 16, 31));

        assertEquals(expectedMap, peptide.positionsMap);
    }

    @Test
    public void testMultipleOccurrences() {
        peptide.setProtein("ABCDEFGHABCDEFGHABCDEFGH");
        peptide.setLibrary(Arrays.asList("ABCDEFGH"));
        peptide.initializePeptideArray();
        peptide.findPositionsInProtein(peptide.protein, 8);

        HashMap<Long, List<Integer>> expectedMap = new HashMap<>();
        expectedMap.put(peptide.convertPeptideToLong("ABCDEFGH"), List.of(0, 8, 16));

        assertEquals(expectedMap, peptide.positionsMap);
    }

    @Test
    public void testOverlappingPeptides() {
        peptide.setProtein("ABCDEFGHABCDEFGHABCDEFGH");
        peptide.setLibrary(Arrays.asList("ABCDEFGH", "BCDEFGHA"));
        peptide.initializePeptideArray();
        peptide.findPositionsInProtein(peptide.protein, 8);

        HashMap<Long, List<Integer>> expectedMap = new HashMap<>();
        expectedMap.put(peptide.convertPeptideToLong("ABCDEFGH"), List.of(0, 8, 16));
        expectedMap.put(peptide.convertPeptideToLong("BCDEFGHA"), List.of(1, 9));

        assertEquals(expectedMap, peptide.positionsMap);
    }
}
