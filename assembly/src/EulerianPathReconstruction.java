import java.util.*;

public class EulerianPathReconstruction {
    public static void reconstructGenomeFromGraph(Map<String, Set<String>> graph, int k) {
        List<String> eulerianPath = findEulerianPath(graph);

        if (eulerianPath != null) {
            String reconstructedGenome = reconstructGenome(eulerianPath, k);
            System.out.println("Reconstructed Genome: " + reconstructedGenome);
        } else {
            System.out.println("No Eulerian path found.");
        }
    }

    public static List<String> findEulerianPath(Map<String, Set<String>> graph) {
        Map<String, Integer> inDegree = new HashMap<>();
        Map<String, Integer> outDegree = new HashMap<>();
        Map<String, Stack<String>> adjacencyList = new HashMap<>();

        for (Map.Entry<String, Set<String>> entry : graph.entrySet()) {
            String node = entry.getKey();
            Set<String> edges = entry.getValue();
            outDegree.put(node, edges.size());
            adjacencyList.put(node, new Stack<>());
            adjacencyList.get(node).addAll(edges);
            for (String neighbor : edges) {
                inDegree.put(neighbor, inDegree.getOrDefault(neighbor, 0) + 1);
            }
        }

        String startNode = null;
        String endNode = null;
        for (String node : new HashSet<>(inDegree.keySet())) {
            int in = inDegree.getOrDefault(node, 0);
            int out = outDegree.getOrDefault(node, 0);
            if (in < out) {
                startNode = node;
            } else if (in > out) {
                endNode = node;
            }
        }
        if (startNode == null) startNode = graph.keySet().iterator().next();

        List<String> path = new ArrayList<>();
        findEulerianPathUtil(startNode, adjacencyList, path);
        Collections.reverse(path);
        if (path.size() == graph.values().stream().mapToInt(Set::size).sum() + 1) {
            return path;
        }
        return null;
    }

    private static void findEulerianPathUtil(String node, Map<String, Stack<String>> adjacencyList, List<String> path) {
        Stack<String> stack = new Stack<>();
        stack.push(node);

        while (!stack.isEmpty()) {
            String current = stack.peek();
            if (adjacencyList.containsKey(current) && !adjacencyList.get(current).isEmpty()) {
                String neighbor = adjacencyList.get(current).pop();
                stack.push(neighbor);
            } else {
                path.add(stack.pop());
            }
        }
    }

    public static String reconstructGenome(List<String> eulerianPath, int k) {
        StringBuilder genome = new StringBuilder(eulerianPath.get(0));

        for (int i = 1; i < eulerianPath.size(); i++) {
            String suffix = eulerianPath.get(i);
            genome.append(suffix.charAt(k - 2));
        }
        return genome.toString();
    }
}
