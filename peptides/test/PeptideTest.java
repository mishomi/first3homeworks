import jdk.management.jfr.FlightRecorderMXBean;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class PeptideTest {
    Peptide peptide;

    @Before
    public void init(){
        peptide = new Peptide();
        peptide.setProtein("ABCDEFGHBCDEFGHABCDEFGHBCDEFGHABCDEFGHBCDEFGH");
        peptide.setLibrary(Arrays.asList("ABCDEFGH","BCDEFGHB","EFGHABCD"));

    }
    @Test
    public void initializationParameters(){
        assertNotNull(peptide.protein);
        assertEquals(0,peptide.peptideMap.size());
    }

    @Test
    public void testIfHashMapInitialized(){
        peptide.initializePeptideMap(peptide.library);
        assertEquals(peptide.peptideMap.size(), peptide.library.size());
    }
    @Test
    public void testIfFoundCorrectPositions(){
        peptide.initializePeptideMap(peptide.library);
        peptide.findPositionsInProtein(peptide.protein,8);
        HashMap<String, List<Integer>> temp = new HashMap<>();
        temp.put("ABCDEFGH",List.of(0,15,30));
        temp.put("BCDEFGHB",List.of(1, 16, 31));
        temp.put("EFGHABCD",List.of(11,26));
        assertEquals(temp,peptide.peptideMap);
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
}