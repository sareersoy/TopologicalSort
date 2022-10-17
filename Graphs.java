
import java.util.ArrayList;
class Graphs {
    int V;
    ArrayList<ArrayList<Integer>> adjListArray;
    Graphs(int V) {
        this.V = V;
        adjListArray = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adjListArray.add(i, new ArrayList<>());}}

    void addEdge(int src, int dest) {
        adjListArray.get(src).add(dest);
        adjListArray.get(dest).add(src);}

    ArrayList<Integer> DFSUtil(int v, boolean[] visited, ArrayList<Integer> neigh) {
        visited[v] = true;
       neigh.add(v);
        for (int x : adjListArray.get(v)) {
            if (!visited[x])
                DFSUtil(x, visited, neigh);}
        return neigh;}

    ArrayList<ArrayList<Integer>> connectedComponents() {
        ArrayList<ArrayList<Integer>> galaxy = new ArrayList<>();
        boolean[] visited = new boolean[V];
        for (int v = 0; v < V; ++v) {
            if (!visited[v]) {
                ArrayList<Integer> neigh = new ArrayList<>();
                neigh=DFSUtil(v, visited, neigh);
                galaxy.add(neigh);}}
        return galaxy;}}