import java.util.Objects;

public class Meat extends Product{

    private final String FruitProduct = "Kött product";

    public Meat(String name, int pris, int idkod) {
        super(name, pris, idkod);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Meat meat = (Meat) o;
        return Objects.equals(FruitProduct, meat.FruitProduct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), FruitProduct);
    }

}
