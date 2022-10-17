
import java.util.ArrayList;
import java.util.List;
public class Project {
    private final String name;
    private final List<Task> tasks;
    public Project(String name, List<Task> tasks) {
        this.name = name;
        this.tasks = tasks;
    }
    static int[] reverse(int[] a, int n)
    {
        int[] b = new int[n];
        int j = n;
        for (int i = 0; i < n; i++) {
            b[j - 1] = a[i];
            j = j - 1;}
        return b;}

    public int[] getEarliestSchedule() {
        Graph g = new Graph(tasks.size());
        for(int i=0; i<tasks.size(); i++){
            for(int j=0; j<tasks.get(i).getDependencies().size();j++){
                g.addEdge(i,tasks.get(i).getDependencies().get(j));}}
        ArrayList<Integer> aList = g.topologicalSort();
        int[] intArr = new int[aList.size()];
        int start=0;
        int end;
        boolean ctrl;
        for (Integer integer : aList) {
            for (Task task : tasks) {
                end = 0;
                ctrl = true;
                if (task.getTaskID() == integer) {
                    if (task.getDependencies().size() == 1) {
                        end = intArr[task.getDependencies().get(0)] + tasks.get(task.getDependencies().get(0)).getDuration();
                        ctrl = false;}
                    if (ctrl) {
                        for (int a = 0; a < task.getDependencies().size(); a++) {
                            if (a == 0) {
                                end = intArr[task.getDependencies().get(0)] + tasks.get(task.getDependencies().get(0)).getDuration();
                                continue;}
                            if (end < intArr[task.getDependencies().get(a)] + tasks.get(task.getDependencies().get(a)).getDuration()) {
                                end = intArr[task.getDependencies().get(a)] + tasks.get(task.getDependencies().get(a)).getDuration();}}}
                    start = end;
                    break;}}

            intArr[integer] = start;}
        return intArr;}

    public int getProjectDuration() {
        int projectDuration = 0;
        int[] arr = getEarliestSchedule();
        projectDuration = arr[arr.length-1]+tasks.get(tasks.size()-1).getDuration();

        return projectDuration;}

    public static void printlnDash(int limit, char symbol) {
        for (int i = 0; i < limit; i++) System.out.print(symbol);
        System.out.println();}

    public void printSchedule(int[] schedule) {

        int limit = 65;
        char symbol = '-';
        printlnDash(limit, symbol);

        System.out.println(String.format("Project name: %s", name));
        printlnDash(limit, symbol);

        System.out.println(String.format("%-10s%-45s%-7s%-5s", "Task ID", "Description", "Start", "End"));
        printlnDash(limit, symbol);
        for (int i = 0; i < schedule.length; i++) {
            Task t = tasks.get(i);
            System.out.println(String.format("%-10d%-45s%-7d%-5d", i, t.getDescription(), schedule[i], schedule[i] + t.getDuration()));}
        printlnDash(limit, symbol);
        System.out.println(String.format("Project will be completed in %d days.", tasks.get(schedule.length - 1).getDuration() + schedule[schedule.length - 1]));
        printlnDash(limit, symbol);}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        int equal = 0;
        for (Task otherTask : ((Project) o).tasks) {
            if (tasks.stream().anyMatch(t -> t.equals(otherTask))) {
                equal++;}}
        return name.equals(project.name) && equal == tasks.size();}}
