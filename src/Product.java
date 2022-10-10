import java.util.Objects;

public class Product {
    public String name;
    private int pris = 5;
    private int idkod = 123;

    public Product(String name, int pris, int idkod) {
        this.name = name;
        this.pris = pris;
        this.idkod = idkod;
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

    public int getIdkod() {
        return idkod;
    }

    public void setIdkod(int idkod) {
        this.idkod = idkod;
    }
    public int size(int name){
        name++;
        return name;
    }

    @Override
    public String toString() {
        return " namn = " + name + " pris = " + pris + " EAN = " + idkod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return pris == product.pris && idkod == product.idkod && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name,pris, idkod);
    }
    public int idKodCounter(){
        idkod++;
        return idkod;
    }
    public static void lagersaldo(){

    }
}
