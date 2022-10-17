import java.util.*;

public class Galaxy {
    private final List<Planet> planets;
    private List<SolarSystem> solarSystems;

    public Galaxy(List<Planet> planets) {
        this.planets = planets;
        exploreSolarSystems();
    }

    public List<SolarSystem> exploreSolarSystems() {
        solarSystems = new ArrayList<>();
        Graphs ssgraph = new Graphs(planets.size());

        for(int i=0; i<planets.size(); i++){
            for(int j=0; j<planets.get(i).getNeighbors().size(); j++){
                for(int k=0; k<planets.size(); k++) {
                    if(Objects.equals(planets.get(i).getNeighbors().get(j), planets.get(k).getId())){
                    ssgraph.addEdge(i,k);}}}}

        ArrayList<ArrayList<Integer>> arrayLists;
        arrayLists = ssgraph.connectedComponents();

        for (ArrayList<Integer> arrayList : arrayLists) {
            SolarSystem ss = new SolarSystem();
            for (Integer integer : arrayList) {ss.addPlanet(planets.get(integer));}
            solarSystems.add(ss);}

        return new ArrayList<>(solarSystems);}

    public List<SolarSystem> getSolarSystems() {
        return solarSystems;}


    public List<Planet> getPlanets() {
        return planets;
    }

    public int getSolarSystemIndexByPlanetID(String planetId) {
        for (int i = 0; i < solarSystems.size(); i++) {
            SolarSystem solarSystem = solarSystems.get(i);
            if (solarSystem.hasPlanet(planetId)) {
                return i;}}
        return -1;}

    public void printSolarSystems(List<SolarSystem> solarSystems) {

        System.out.printf("%d solar systems have been discovered.%n", solarSystems.size());
        for (int i = 0; i < solarSystems.size(); i++) {
            SolarSystem solarSystem = solarSystems.get(i);
            List<Planet> planets = new ArrayList<>(solarSystem.getPlanets());
            Collections.sort(planets);
            System.out.printf("Planets in Solar System %d: %s", i + 1, planets);
            System.out.println();
        }}}
