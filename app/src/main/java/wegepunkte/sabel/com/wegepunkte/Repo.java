package wegepunkte.sabel.com.wegepunkte;

import java.util.List;
import java.util.Objects;

public class Repo {

    private List<Wegepunkt> wegepunkte;

    public Repo() {
    }

    public Wegepunkt getWegepunkte(int index) {
        return wegepunkte.get(index);
    }

    public void addWegepunkt(Wegepunkt wegepunkt){
        wegepunkte.add(wegepunkt);
    }

    public int getSize(){
        return wegepunkte.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repo repo = (Repo) o;
        return Objects.equals(wegepunkte, repo.wegepunkte);
    }

    @Override
    public int hashCode() {

        return Objects.hash(wegepunkte);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Wegepunkt wegepunkt : wegepunkte) {
            stringBuilder.append(wegepunkt.toString());
            stringBuilder.append(String.format("%n"));
        }
        return stringBuilder.toString();
    }
}
