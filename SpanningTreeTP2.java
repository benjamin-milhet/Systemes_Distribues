import visidia.simulation.SimulationConstants;
import visidia.simulation.process.algorithm.LC0_Algorithm;
import visidia.simulation.process.edgestate.MarkedState;

public class SpanningTreeTP2 extends LC0_Algorithm{
    private String[] voisins;
    private int etat = -1;

    @Override
    public Object clone() {
        return new SpanningTreeTP2();
    }

    @Override
    public String getDescription() {
        return "Spanning tree algorithm using LC0.\n" + "Rule 1 : N---A ---> A---M\n" + "Rule 2 : A---M ---> F---A\n" + "Forbidden rule : A---N";
    }

    @Override
    protected void beforeStart() {
        setLocalProperty("label", vertex.getLabel());
        setLocalProperty("father", this.etat);
        setLocalProperty("ordre", 1);

        this.voisins = new String[getArity()];

        for (int i = 0 ; i < getArity() ; i++) {
            this.voisins[i] = "N";
        }

        putProperty("Affichage", 1, SimulationConstants.PropertyStatus.DISPLAYED);

    }

    @Override
    protected void onStarCenter() {
        this.voisins[neighborDoor] = getNeighborProperty("label").toString();

        if (getLocalProperty("label").equals("N") && getNeighborProperty("label").equals("A")) {
            setNeighborProperty("label", "M");
            setLocalProperty("label", "A");
            setLocalProperty("father", neighborDoor);
            setDoorState(new MarkedState(true), neighborDoor);
            this.voisins[neighborDoor] = "M";
        } else if (getLocalProperty("label").equals("A") && getNeighborProperty("label").equals("M") && neighborDoor == (int) getLocalProperty("father") && this.getNbVoisin("N") == 0) {
            setNeighborProperty("label", "A");
            setLocalProperty("label", "F");
            this.voisins[neighborDoor] = "A";
            setNeighborProperty("ordre", (int) getLocalProperty("ordre") + (int) getNeighborProperty("ordre"));
            putProperty("Affichage", (int) getNeighborProperty("ordre"), SimulationConstants.PropertyStatus.DISPLAYED);
        }


    }

    private int getNbVoisin(String lettre) {
        int res = 0;

        for (int i = 0 ; i < getArity() ; i++){
            if (this.voisins[i].equals(lettre)) res ++;
        }

        return res;
    }
}
