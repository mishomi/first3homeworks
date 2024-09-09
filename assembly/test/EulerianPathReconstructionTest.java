import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class EulerianPathReconstructionTest {

    private Map<String, Set<String>> graph;
    private int k;

    @BeforeEach
    public void setUp() {
        graph = new HashMap<>();
        graph.put("GAT", new HashSet<>(Set.of("ATC")));
        graph.put("ATC", new HashSet<>(Set.of("TCA")));
        graph.put("TCA", new HashSet<>(Set.of("CAC", "ACC")));
        graph.put("CAC", new HashSet<>(Set.of("ACG")));
        graph.put("ACC", new HashSet<>(Set.of("CGG")));

        k = 4;
    }

    @Test
    public void testFindEulerianPath() {
        List<String> expectedPath = Arrays.asList("GAT", "ATC", "TCA", "CAC", "ACC", "CGG");
        List<String> actualPath = EulerianPathReconstruction.findEulerianPath(graph);

        assertNotNull(actualPath, "Eulerian path should not be null.");
        assertEquals(expectedPath, actualPath, "Eulerian path was not found or is incorrect.");
    }

    @Test
    public void testReconstructGenome() {
        List<String> eulerianPath = Arrays.asList("GAT", "ATC", "TCA", "CAC", "ACC", "CGG");
        String expectedGenome = "GATCAC";

        String actualGenome = EulerianPathReconstruction.reconstructGenome(eulerianPath, k);

        assertEquals(expectedGenome, actualGenome, "Reconstructed genome is incorrect.");
    }
}