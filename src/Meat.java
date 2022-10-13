import java.util.Objects;

public class Meat extends Product{


    public Meat(String name, int pris, int EAN) {
        super(name, pris, EAN);
    }


    @Override
    public String toString() {
        return super.getName()+ "       " + super.getPris() + "     "+ super.getEAN();
    }
}
