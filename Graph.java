
import java.util.*;
class Graph {
    private final int V;
    private final ArrayList<ArrayList<Integer> > adj;
    Graph(int v)
    {
        V = v;
        adj = new ArrayList<>(v);
        for (int i = 0; i < v; ++i)
            adj.add(new ArrayList<>());
    }
    void addEdge(int v, int w) { adj.get(v).add(w); }

    void topologicalSortUtil(int v, boolean[] visited,
                            ArrayList<Integer> arr)
    {
        visited[v] = true;
        Integer i;
        for (Integer integer : adj.get(v)) {
            i = integer;
            if (!visited[i])
                topologicalSortUtil(i, visited, arr);
        }
        arr.add(v);
    }
    ArrayList<Integer> topologicalSort()
    {
        ArrayList<Integer> arr = new ArrayList<>();
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++)
            visited[i] = false;
        for (int i = 0; i < V; i++)
            if (!visited[i])
                topologicalSortUtil(i, visited, arr);
return arr;
    }}

