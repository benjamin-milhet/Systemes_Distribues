import visidia.simulation.process.algorithm.LC1_Algorithm;
import visidia.simulation.process.edgestate.MarkedState;

public class EtoileOuverteTerminaisonLocale extends LC1_Algorithm {
    @Override
    public Object clone() {
        return new EtoileOuverte();
    }

    @Override
    public String getDescription() {
        return "Spanning tree algorithm using LC1.\n"
                + "Rule : Si le centre de l'étoile est à l'état 'N' et qu'il possède au moins un voisin 'A', il en choisit un (et un seul) et il marque l'arête qui le lie à ce voisin.\n";
    }

    @Override
    protected void beforeStart() {
        setLocalProperty("label", vertex.getLabel());
        setLocalProperty("p", false);
        setLocalProperty("a", -1);
    }

    @Override
    protected void onStarCenter() {
        int portA = getVoisinA();
        int portN = getVoisinN();

        if (portA != -1) {
            setLocalProperty("label", "A");
            setDoorState(new MarkedState(true), portA);
        } else if (portN != -1) {
            
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

    private int getVoisinN() {
        int res = -1;

        for (int i = 0 ; i < getActiveDoors().size() ; i++) {
            int tmp = getActiveDoors().get(i);

            if (getNeighborProperty(tmp, "label").equals("N")) {
                res = tmp;
            }
        }

        return res;
    }


}
