import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class Assembly {

    public static void main(String[] args) {
        String genome = "GATCAC";
        int k = 4;

        Map<String, Set<String>> deBruijnGraph = createDeBruijnGraph(genome, k);

        for (Map.Entry<String, Set<String>> entry : deBruijnGraph.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

    }

    public static Map<String, Set<String>> createDeBruijnGraph(String genome, int k) {
        Map<String, Set<String>> graph = new LinkedHashMap<>();
        int prefixLength = k - 1;

        for (int i = 0; i <= genome.length() - k; i++) {
            String kmer = genome.substring(i, i + k);
            String prefix = kmer.substring(0, prefixLength);
            String suffix = kmer.substring(1, k);

            graph.computeIfAbsent(prefix, p -> new LinkedHashSet<>()).add(suffix);
        }

        return graph;
    }
}
