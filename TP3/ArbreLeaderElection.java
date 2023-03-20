import visidia.simulation.SimulationConstants;
import visidia.simulation.process.algorithm.LC0_Algorithm;
import visidia.simulation.process.edgestate.MarkedState;
public class ArbreLeaderElection extends LC0_Algorithm{
    @Override
    public Object clone() {
        return new ArbreLeaderElection();
    }

    @Override
    public String getDescription() {
        return "Déterminer le leader dans un arbre\n"
                + "Tous les sommets de départ sont à l'état neutre N\n"
                + "Rule 1 : N(1)---N(x) ---> F(0)---N(x-1)\n"
                + "Rule 2 : N(1)---N(1) ---> E(0)---F(0)\n"
                + "avec x > 1";
    }

    @Override
    protected void beforeStart() {
        setLocalProperty("label", vertex.getLabel());
        setLocalProperty("x", getArity());

        putProperty("Affichage", getArity(), SimulationConstants.PropertyStatus.DISPLAYED);
    }

    @Override
    protected void onStarCenter() {
        if (getLocalProperty("label").equals("N") && getNeighborProperty("label").equals("N")) {
            int nbVoisinsLocal = (int) getLocalProperty("x");
            int nbVoisinsNeighbor = (int) getNeighborProperty("x");

            if (nbVoisinsLocal == 1 && nbVoisinsNeighbor > 1) {
                setLocalProperty("label", "F");
                setLocalProperty("x", 0);

                setNeighborProperty("label", "N");
                setNeighborProperty("x", nbVoisinsNeighbor - 1);

                nbVoisinsLocal = (int) getLocalProperty("x");
                putProperty("Affichage", nbVoisinsLocal, SimulationConstants.PropertyStatus.DISPLAYED);
                
            } else if (nbVoisinsLocal == 1 && nbVoisinsNeighbor == 1) {
                setLocalProperty("label", "E");
                setLocalProperty("x", 0);

                setNeighborProperty("label", "F");
                setNeighborProperty("x", 0);

                nbVoisinsLocal = (int) getLocalProperty("x");
                putProperty("Affichage", nbVoisinsLocal, SimulationConstants.PropertyStatus.DISPLAYED);

            }
        }
    }
}
