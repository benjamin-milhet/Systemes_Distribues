import visidia.simulation.process.algorithm.LC2_Algorithm;
import visidia.simulation.process.edgestate.MarkedState;

public class EtoileFermees extends LC2_Algorithm {

    @Override
    public Object clone() {
        return new EtoileFermees();
    }

    @Override
    public String getDescription() {
        return "Etoile fermées algorithm using LC2 avec detection de la terminaison locale.\n"
                + "Rule : Si le centre de l'étoile est à l'état 'A' et qu'il possède des voisins 'N', il demande à ces derniers de passe à l'état 'A' et marque les liens qui le connectent à ces voisins.\n";
    }

    @Override
    protected void beforeStart() {
        setLocalProperty("label", vertex.getLabel());
        setLocalProperty("isDone", false);
    }

    @Override
    protected void onStarCenter() {
        if ((boolean) getLocalProperty("isDone")) localTermination();

        if (getLocalProperty("label").equals("A")) {

            for (int i = 0 ; i < getActiveDoors().size() ; i++) {
                int tmp = getActiveDoors().get(i);

                if (getNeighborProperty(tmp, "label").equals("N")) {
                    setNeighborProperty(tmp,"label", "A");
                    setDoorState(new MarkedState(true), tmp);
                }

                setLocalProperty("isDone", true);
            }

        }
    }
}
