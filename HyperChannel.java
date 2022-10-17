public class HyperChannel {
    private Planet to;
    private Planet from;
    private Double cost;

    public HyperChannel(Planet to, Planet from, Double cost) {
        this.to = to;
        this.from = from;
        this.cost = cost;
    }
    public Planet getTo() {
        return to;
    }

    public Planet getFrom() {
        return from;
    }

    public Double getWeight() {
        return cost;
    }
}
