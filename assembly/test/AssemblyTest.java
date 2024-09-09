import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssemblyTest {

    private String genome;
    private int k;
    private Map<String, Set<String>> expectedGraph;

    @BeforeEach
    public void setUp() {
        genome = "GATCAC";
        k = 4;

        expectedGraph = new LinkedHashMap<>();
        expectedGraph.put("GAT", new LinkedHashSet<>(Set.of("ATC")));
        expectedGraph.put("ATC", new LinkedHashSet<>(Set.of("TCA")));
        expectedGraph.put("TCA", new LinkedHashSet<>(Set.of("CAC")));
    }

    @Test
    public void testCreateDeBruijnGraph() {
        Map<String, Set<String>> actualGraph = Assembly.createDeBruijnGraph(genome, k);

        assertEquals(expectedGraph, actualGraph, "The De Bruijn graph was not constructed correctly.");
    }
}
