import java.util.Objects;

public class Product {
    public String name;
    private int pris = 5;
    private int EAN = 123;

    public Product(String name, int pris, int EAN) {
        this.name = name;
        this.pris = pris;
        this.EAN = EAN;
    }

    public int getPris() {
        return pris;
    }

    public void setPris(int pris) {
        this.pris = pris;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEAN() {
        return EAN;
    }

    public void setEAN(int EAN) {
        this.EAN = EAN;
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
