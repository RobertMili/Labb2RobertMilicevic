import java.util.Objects;

public class Fruit extends Product{
    private final String FruitProduct = "Fruit product";

    public Fruit(String name, int pris, int idkod) {
        super(name, pris, idkod);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Fruit fruit = (Fruit) o;
        return Objects.equals(FruitProduct, fruit.FruitProduct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), FruitProduct);
    }

}
