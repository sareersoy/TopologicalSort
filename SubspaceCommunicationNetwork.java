
import java.util.*;

public class SubspaceCommunicationNetwork {

    private List<SolarSystem> solarSystems;

    public SubspaceCommunicationNetwork(List<SolarSystem> solarSystems) {

        this.solarSystems = solarSystems;
    }

    public static void doSelectionSort(List<HyperChannel> arr) {
        for (int i = 0; i < arr.size(); i++) {
            int pos = i;
            for (int j = i; j < arr.size(); j++) {
                if (arr.get(j).getWeight() < arr.get(pos).getWeight())
                    pos = j;}
            HyperChannel min = arr.get(pos);
            arr.set(pos, arr.get(i));
            arr.set(i, min);}}

    public List<HyperChannel> getMinimumCostCommunicationNetwork() {
        List<HyperChannel> minimumCostCommunicationNetwork = new ArrayList<>();
        List<Planet> maxTechLevel = new ArrayList<>();
        Planet pl;
        Planet pldefault=new Planet("",0,new ArrayList<>());
        for(SolarSystem ss: solarSystems){
            pl=pldefault;
            for (Planet planet:ss.getPlanets()){
                if(planet.getTechnologyLevel()>pl.getTechnologyLevel()){
                    pl=planet;}}
            maxTechLevel.add(pl);}

        List<HyperChannel> HCList = new ArrayList<>();
        for(int i=0; i<maxTechLevel.size(); i++){
            for(int j=i+1; j<maxTechLevel.size(); j++){
                HyperChannel hp = new HyperChannel(maxTechLevel.get(i), maxTechLevel.get(j),Constants.SUBSPACE_COMMUNICATION_CONSTANT/((maxTechLevel.get(i).getTechnologyLevel()+maxTechLevel.get(j).getTechnologyLevel())/2.0));
               HCList.add(hp);}}

        doSelectionSort(HCList);
        Map<Planet,Boolean> checkList = new HashMap<>(maxTechLevel.size());
        for (Planet planet : maxTechLevel) {
            checkList.put(planet, false);
        }

        for (HyperChannel hyperChannel : HCList) {
            if (!checkList.get(hyperChannel.getFrom()) || !checkList.get(hyperChannel.getTo())) {
                checkList.put(hyperChannel.getFrom(), true);
                checkList.put(hyperChannel.getTo(), true);
                minimumCostCommunicationNetwork.add(hyperChannel);
            }
        }

        System.out.println(maxTechLevel.size());
        return minimumCostCommunicationNetwork;}

    public void printMinimumCostCommunicationNetwork(List<HyperChannel> network) {
        double sum = 0;
        for (HyperChannel channel : network) {
            Planet[] planets = {channel.getFrom(), channel.getTo()};
            Arrays.sort(planets);
            System.out.printf("Hyperchannel between %s - %s with cost %f", planets[0], planets[1], channel.getWeight());
            System.out.println();
            sum += channel.getWeight();
        }
        System.out.printf("The total cost of the subspace communication network is %f.", sum);
        System.out.println();}}
