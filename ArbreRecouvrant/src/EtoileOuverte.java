import visidia.simulation.process.algorithm.LC1_Algorithm;
import visidia.simulation.process.edgestate.MarkedState;

public class EtoileOuverte extends LC1_Algorithm {
    @Override
    public Object clone() {
        return new EtoileOuverte();
    }

    @Override
    public String getDescription() {
        return "Spanning tree algorithm using LC0.\n"
                + "Rule : Si le centre de l'étoile est à l'état 'N' et qu'il possède au moins un voisin 'A', il en choisit un (et un seul) et il marque l'arête qui le lie à ce voisin.\n";
    }

    @Override
    protected void beforeStart() {
        setLocalProperty("label", vertex.getLabel());
    }

    @Override
    protected void onStarCenter() {
        if (getLocalProperty("label").equals("N")) {
            int port = getVoisinA();

            if (port != -1) {
                setLocalProperty("label", "A");
                setDoorState(new MarkedState(true), port);
            }
        }
    }

    private int getVoisinA(){
        int res = -1;

        for (int i = 0 ; i < getActiveDoors().size() ; i++) {
            int tmp = getActiveDoors().get(i);

            if (getNeighborProperty(tmp, "label").equals("A")) {
                res = tmp;
            }
        }

        return res;
    }
}
