import visidia.simulation.process.algorithm.LC2_Algorithm;

public class ArbreLeaderV3 extends LC2_Algorithm {
    @Override
    public Object clone() {
        return new ArbreLeaderV3();
    }

    @Override
    public String getDescription() {
        return "Election d'un leader dans un arbre avec LC2.\n"
                + "Rule 1 : Si le centre est à l'état 'N', il demande à tous ces voisins 'N' et qui ont un seul voisin actif de passer à l'état F. Et le centre retire tous les voisins passés à l'état 'F' du nombre de ses voisins actif.\n"
                + "Rule 2 : Si le centre est à l'état 'N' et qu'il ne possède aucun voisin actif, alors il devient élu.";
    }

    @Override
    protected void beforeStart() {
        setLocalProperty("label", vertex.getLabel());
        setLocalProperty("x", getArity());
        setLocalProperty("isDone", false);
    }

    @Override
    protected void onStarCenter() {
        if ((boolean) getLocalProperty("isDone")) localTermination();

        if (getLocalProperty("label").equals("N")) {

            for (int i = 0 ; i < getActiveDoors().size() ; i++) {
                int tmp = getActiveDoors().get(i);

                if (getNeighborProperty(tmp, "label").equals("N")) {
                    if ((int) getNeighborProperty(tmp, "x") == 1){
                        setNeighborProperty(tmp, "label", "F");
                        setLocalProperty("x", (int) getLocalProperty("x")-1);
                        setNeighborProperty(tmp, "isDone", true);
                    }
                }
                if ((int) getLocalProperty("x") == 0) {
                    setLocalProperty("label", "E");
                    setLocalProperty("isDone", true);
                }
            }
        }
    }
}
