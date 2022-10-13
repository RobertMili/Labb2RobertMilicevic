import java.util.Objects;

public class Product {
    public final String name;
    private final int pris ;
    private final int EAN ;

    public Product(String name, int pris, int EAN) {
        this.name = name;
        this.pris = pris;
        this.EAN = EAN;
    }

    public int getPris() {
        return pris;
    }


    public String getName() {
        return name;
    }


    public int getEAN() {
        return EAN;
    }

    public int size(int name) {
        name++;
        return name;
    }

    @Override
    public String toString() {
        return " namn = " + name + " pris = " + pris + " EAN = " + EAN;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return pris == product.pris && EAN == product.EAN && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, pris, EAN);
    }

}
