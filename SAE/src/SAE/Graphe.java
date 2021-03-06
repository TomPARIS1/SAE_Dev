package SAE;

import java.util.*;
import java.util.Arrays;

public class Graphe<E> {
    public List<Villes> StockVilles = null;
    public List<List<Aretes>> StockAretes = null;
    public int zone;

    public Graphe(int zone) {
        this.zone = zone;
        this.StockVilles = new ArrayList<>();
        this.StockAretes = new ArrayList<>();
    }

    public void setZone (int zone) {
        this.zone = zone;
    }

    public int getZone () {
        return zone;
    }

    public void creationVilles (int nb_ville) {

        for (int i=0; i < nb_ville; i++) {
            double x = Math.random() * (this.zone);
            double y = Math.random() * (this.zone);

            Villes v = new Villes(x,y,i);

            this.StockVilles.add(v);
        }
    }

    public void creationAretes () {
        for (int i=0; i < StockVilles.size(); i++) {
            this.StockAretes.add(new ArrayList<>());
            for (int j=0; j < StockVilles.size(); j++) {
                if (i != j) {
                    Aretes a = new Aretes(this.StockVilles.get(i), this.StockVilles.get(j));
                    a.setDistance();
                    a.setVisibilite(this.zone);
                    this.StockAretes.get(i).add(a);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Graphe{" +
                "StockVilles=" + StockVilles +
                '}';
    }

    public static void main(String[] args) {
        Graphe<Villes> Graphe2 = new Graphe<Villes>(1000);

        Graphe2.creationVilles(15);
        Graphe2.creationAretes();

        Fourmis f = new Fourmis(Graphe2.StockVilles.get(0), Graphe2.StockVilles);

        f.choixChemin(Graphe2.StockAretes);

        for (Aretes arete:f.areteVisite) {
            System.out.println(arete);
        }


    }
}
