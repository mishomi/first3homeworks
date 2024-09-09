import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class Peptides3Test {
    Peptides3 peptide;

    @Before
    public void init() {
        peptide = new Peptides3();
        peptide.setProtein("ABCDEFGHBCDEFGHABCDEFGHBCDEFGHABCDEFGHBCDEFGH");
        peptide.setLibrary(Arrays.asList("ABCDEFGH", "BCDEFGHB", "EFGHABCD"));
    }

    @Test
    public void initializationParameters() {
        assertNotNull(peptide.protein);
        assertEquals(0, peptide.peptideMap.size());
    }

    @Test
    public void testIfHashMapInitialized() {
        peptide.initializePeptideMap(peptide.library);
        assertEquals(peptide.peptideMap.size(), peptide.library.size());
    }

    @Test
    public void testIfFoundCorrectPositions() {
        peptide.initializePeptideMap(peptide.library);
        peptide.findPositionsInProtein(peptide.protein, 8);

        HashMap<Long, List<Integer>> expectedMap = new HashMap<>();
        expectedMap.put(peptide.convertPeptideToLong("ABCDEFGH"), List.of(0, 15, 30));
        expectedMap.put(peptide.convertPeptideToLong("BCDEFGHB"), List.of(1, 16, 31));
        expectedMap.put(peptide.convertPeptideToLong("EFGHABCD"), List.of(11, 26));

        assertEquals(expectedMap, peptide.peptideMap);
    }
    @Test
    public void testEmptyProtein() {
        peptide.setProtein("");
        peptide.initializePeptideMap(peptide.library);
        peptide.findPositionsInProtein(peptide.protein, 8);

        for (List<Integer> positions : peptide.peptideMap.values()) {
            assertTrue(positions.isEmpty());
        }
    }

    @Test
    public void testEmptyLibrary() {
        peptide.setLibrary(Arrays.asList());
        peptide.initializePeptideMap(peptide.library);

        assertTrue(peptide.peptideMap.isEmpty());
    }

    @Test
    public void testPeptidesNotFound() {
        peptide.setLibrary(Arrays.asList("ZZZZZZZZ", "YYYYYYYY", "XXXXXXXX"));
        peptide.initializePeptideMap(peptide.library);
        peptide.findPositionsInProtein(peptide.protein, 8);

        for (List<Integer> positions : peptide.peptideMap.values()) {
            assertTrue(positions.isEmpty());
        }
    }

    @Test
    public void testProteinSmallerThanPeptide() {
        peptide.setProtein("ABC");
        peptide.initializePeptideMap(peptide.library);
        peptide.findPositionsInProtein(peptide.protein, 8);

        for (List<Integer> positions : peptide.peptideMap.values()) {
            assertTrue(positions.isEmpty());
        }
    }

    @Test
    public void testOverlappingPeptidesInProtein() {
        peptide.setProtein("ABCDEFGHABCDEFGHABCDEFGH");
        peptide.setLibrary(Arrays.asList("ABCDEFGH", "BCDEFGHA"));
        peptide.initializePeptideMap(peptide.library);
        peptide.findPositionsInProtein(peptide.protein, 8);

        HashMap<Long, List<Integer>> expectedMap = new HashMap<>();
        expectedMap.put(peptide.convertPeptideToLong("ABCDEFGH"), List.of(0, 8, 16));
        expectedMap.put(peptide.convertPeptideToLong("BCDEFGHA"), List.of(1, 9));

        assertEquals(expectedMap, peptide.peptideMap);
    }

    @Test
    public void testDuplicatePeptides() {
        peptide.setLibrary(Arrays.asList("ABCDEFGH", "ABCDEFGH"));
        peptide.initializePeptideMap(peptide.library);
        peptide.findPositionsInProtein(peptide.protein, 8);

        HashMap<Long, List<Integer>> expectedMap = new HashMap<>();
        expectedMap.put(peptide.convertPeptideToLong("ABCDEFGH"), List.of(0, 15, 30));
        assertEquals(expectedMap, peptide.peptideMap);
    }
}
