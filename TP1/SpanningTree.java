import visidia.simulation.process.algorithm.LC0_Algorithm;
import visidia.simulation.process.edgestate.MarkedState;

public class SpanningTree extends LC0_Algorithm{
    @Override
    public Object clone() {
        return new SpanningTree();
    }

    @Override
    public String getDescription() {
        return "Spanning tree algorithm using LC0.\n" + "Rule: A---N ---> A---A";
    }

    @Override
    protected void beforeStart() {
        setLocalProperty("label", vertex.getLabel());
    }

    @Override
    protected void onStarCenter() {
        if (getLocalProperty("label").equals("A") && getNeighborProperty("label").equals("N")) {
            setNeighborProperty("label", "A");
            setDoorState(new MarkedState(true), neighborDoor);
        }
    }
}
