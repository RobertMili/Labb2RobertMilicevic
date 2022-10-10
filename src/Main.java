import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //TODO change in addProduct to unComment
        // put try/catch in add
        //Variable camel name
        //searching lösa med steam
        //ArrayList<Product> together = new ArrayList<>() put together - väntar att se vad Martin säger


        ArrayList<Fruit> fruitList = new ArrayList<>();
        ArrayList<Meat> meatList = new ArrayList<>();
        ArrayList<Product> productsTogether = new ArrayList<>();


        boolean switching = true;
        while (switching) {
            mainMeny();
            String choice = sc.nextLine();

            switch (choice) {
                case "e", "E" -> {
                    System.exit(0);

                }
                case "1" -> {
                    products();
                    String choice2 = sc.nextLine();
                    if (choice2.equals("1")) {

                        addFruits(sc, fruitList);

                    } else if (choice2.equals("2")) {

                        addMeats(sc, meatList);
                    }
                }
                case "2" -> {
                    System.out.println("Sökning genom namn");
                    products();
                    String choice3 = sc.nextLine();
                    if (choice3.equals("1")) {

                        searchingFruitsNamn(sc, fruitList);

                    } else if (choice3.equals("2")) {

                        searchingMeatsNamn(sc, meatList);

                    }
                }
                case "3" -> {
                    System.out.println("Sökning genom pris");
                    products();
                    String choice4 = sc.nextLine();
                    if (choice4.equals("1")) {

                        searchingFruitPrices(sc, fruitList);
                        sc.nextLine();

                    } else if (choice4.equals("2")) {

                        searchingMeatPrices(sc, meatList);
                        sc.nextLine();

                    }
                }
                case "4" -> {
                    System.out.println("Sökning genom prisintervall");
                    products();
                    String choice4 = sc.nextLine();
                    if (choice4.equals("1")) {

                        searchingFruitsPriceRange(fruitList, sc);

                    } else if (choice4.equals("2")) {

                        searchingMeatsPriceRange(meatList, sc);

                    }
                }
                case "5" -> {
                    System.out.println("Sökning genom EAN");
                    products();
                    String choice5 = sc.nextLine();
                    if (choice5.equals("1")) {

                        searchingEAN_Fruits(sc, fruitList);
                        sc.nextLine();

                    } else if (choice5.equals("2")) {

                        searchingEAN_Meats(sc, meatList);
                        sc.nextLine();

                    }
                }
                case "6" -> {
                    System.out.println("Ta bort");
                    products();
                    String choice6 = sc.nextLine();
                    if (choice6.equals("1")) {

                        removeFruits(fruitList, sc);

                    } else if (choice6.equals("2")) {

                        removeMeats(meatList, sc);
                    }
                }
                case "7" -> {
                    System.out.println("Lagersaldo");
                    products();
                    String choice7 = sc.nextLine();
                    if (choice7.equals("1")) {

                        lagerSaldoFruits(fruitList);
                        getLengthOfObjectsText_Dynamisk_Fruit(fruitList);

                    } else if (choice7.equals("2")) {

                        lagerSaldoMeats(meatList);
                        getLengthOfObjectsText_Dynamisk_Meat(meatList);
                    }
                }
                case "8" -> {
                    productsAddingToReceipt();
                    String choice7 = sc.nextLine();

                    if (choice7.equals("1")) {

                        addToReceiptFruit(sc, fruitList, productsTogether);

                    } else if (choice7.equals("2")) {

                        addToReceiptMeat(sc, fruitList, meatList, productsTogether);

                    } else if (choice7.equals("3")) {

                        receiptPrintOut(fruitList, productsTogether);


                    } else if (choice7.equals("4")) {
                        receiptPrintOutDiscount(fruitList, productsTogether);
                    }
                }
            }
        }
    }

    private static void mainMeny() {
        final String menyText = """
                   
                   Main meny
                ================
                1. Add
                2. Sökning genom namn
                3. Sökning genom pris
                4. Sökning genom prisintervall
                5. Sökning genom EAN
                6. Ta bort
                7. Lagersaldo
                8. Lägg på kvitto
                e. avsluta""";

        System.out.println(menyText);
    }

    private static void products() {
        final String secondMeny = """
                Product
                ========
                Välja Category:
                1. Välja Frukt
                2. Välja Kött 
                e. avsluta """;
        System.out.println(secondMeny);
    }

    private static void productsAddingToReceipt() {
        final String menyProducts = """
                Lagersaldo
                Product
                ========
                Välja Category:
                1. Välja Frukt
                2. Välja Kött 
                3. Vissa på kvitto
                4. Vissa kvitto med rabat
                e. avsluta
                """;
        System.out.println(menyProducts);
    }

    private static void addFruits(Scanner sc, ArrayList<Fruit> fruitList) {


        //System.out.print("Please enter name of fruit: ");
        String name = "BANANA";
        //String name = sc.nextLine().toUpperCase();

        //System.out.print("Please enter price of " + name + ": ");
        int price = 10;
        //int price = sc.nextInt();
        //System.out.print("Please enter EAN (code) for " + name + ": ");
        int EAN = 123;

        //int EAN = sc.nextInt();

        boolean equals = fruitList.stream().anyMatch(i -> i.getIdkod() == EAN);

        //immutableEAN_Fruit(fruitList, EAN, name, price, equals);
        addFruitArrays(fruitList, name, price, EAN);
        getLengthOfObjectsText_Dynamisk_Fruit(fruitList);

    }

    private static void immutableEAN_Fruit(ArrayList<Fruit> fruitList, int EAN, String name, int price, boolean equals) {
        if (!equals) {
            addFruitArrays(fruitList, name, price, EAN);
            getLengthOfObjectsText_Dynamisk_Fruit(fruitList);

        } else {
            System.out.println("Den här product med EAN number finns redan, försök med annat EAN number. Tack så mycket!");
        }
    }

    private static void addFruitArrays(ArrayList<Fruit> fruitArrayList, String name, int price, int EAN) {
        fruitArrayList.add(new Fruit(name, price, EAN));
        lagerSaldoFruits(fruitArrayList);
    }

    private static void addMeats(Scanner sc, ArrayList<Meat> meatList) {

        //System.out.print("Please enter name of fruit:");
        String name = "KYCKLING";
        //String name = sc.nextLine();

        //System.out.print("Please enter price of " + name + ": ");
        int price = 10;
        //int price = sc.nextInt();
        //System.out.print("Please enter EAN (code) for " + name + ": ");
        int EAN = 123;
        //int EAN = sc.nextInt();

        boolean equals = meatList.stream().anyMatch(i -> i.getIdkod() == EAN);

        //immutableEAN_Meat(meatList, name, price, EAN, equals);
        //You need to delete this:
        addMeatLists(meatList, name, price, EAN);
        getLengthOfObjectsText_Dynamisk_Meat(meatList);
    }

    private static void immutableEAN_Meat(ArrayList<Meat> meatList, String name, int price, int EAN, boolean equals) {
        if (!equals) {
            addMeatLists(meatList, name, price, EAN);
            getLengthOfObjectsText_Dynamisk_Meat(meatList);

        } else {
            System.out.println("Den här product med EAN number finns redan, försök med annat EAN number. Tack så mycket!");
        }
    }

    private static void addMeatLists(ArrayList<Meat> meatList, String name, int price, int EAN) {
        meatList.add(new Meat(name, price, EAN));
        lagerSaldoMeats(meatList);
    }

    public static void searchingFruitsNamn(Scanner sc, ArrayList<Fruit> fruitList) {
        System.out.print("Sökning fruit: ");
        int counting = 1;
        String search = sc.nextLine().toUpperCase();

        for (Fruit fruit : fruitList) {
            if (fruit.getName() != null && fruit.getName().contains(search)) {
                System.out.println("Produkt: " + counting++ + " -> " + fruit);

            } else {
                System.out.println("The product you are looking for do not exist");
                break;
            }
        }
    }

    public static void searchingMeatsNamn(Scanner sc, ArrayList<Meat> meatList) {
        System.out.print("Sökning kött: ");
        int counting = 1;
        String search = sc.nextLine().toUpperCase();


        for (Meat meats : meatList) {
            if (meats.getName() != null && meats.getName().contains(search)) {
                System.out.println("Produkt: " + counting++ + " -> " + meats);

            } else {
                System.out.println("The product you are looking for do not exist");
                break;
            }
        }
    }

    public static void searchingFruitPrices(Scanner sc, ArrayList<Fruit> fruitList) {
        System.out.print("Write price for at search product: ");
        int counting = 1;


        try {
            int search = sc.nextInt();
            getLengthOfObjectsText_Dynamisk_Fruit(fruitList);

            for (Fruit fruit : fruitList) {
                if (fruit.getPris() != 0 &&fruit.getPris() == search) {
                    System.out.println("Produkt: " + counting++ + " -> " + fruit);

                } else {
                    System.out.println("The product you are looking  do not exist");
                    break;
                }
            }
            getLengthOfObjectsText_Dynamisk_Fruit(fruitList);
        } catch (Exception e) {
            System.out.println("The price you are looking for do not exist");
        }
    }

    public static void searchingMeatPrices(Scanner sc, ArrayList<Meat> meatList) {
        System.out.print("Write price for at search product: ");
        int counting = 1;

        try {
            int search = sc.nextInt();
            getLengthOfObjectsText_Dynamisk_Meat(meatList);

            for (Meat meats : meatList) {
                if (meats.getPris() != 0 && meats.getPris() == search) {
                    System.out.println("Produkt: " + counting++ + " -> " + meats);

                } else {
                    System.out.println("The product you are looking  do not exist");
                    break;

                }
            }
            getLengthOfObjectsText_Dynamisk_Meat(meatList);
        } catch (Exception e) {
            System.out.println("The price you are looking for do not exist");
        }
    }

    private static void searchingFruitsPriceRange(ArrayList<Fruit> fruitList, Scanner sc) {
        System.out.print("Skriv till vilken pris vill du att varor ska skriva ut: ");

        try {
            int search = sc.nextInt();

            getLengthOfObjectsText_Dynamisk_Fruit(fruitList);

            fruitList.stream().filter(x -> x.getPris() <= search)

                    .forEach(System.out::println);
            getLengthOfObjectsText_Dynamisk_Fruit(fruitList);
        } catch (Exception e) {
            System.out.println("The price you are looking for do not exist");
        }
    }

    private static void searchingMeatsPriceRange(ArrayList<Meat> meatList, Scanner sc) {
        System.out.print("Skriv till vilken pris vill du att varor ska skriva ut : ");

        try {
            int search = sc.nextInt();

            getLengthOfObjectsText_Dynamisk_Meat(meatList);

            meatList.stream().filter(x -> x.getPris() < search).forEach(System.out::println);

            getLengthOfObjectsText_Dynamisk_Meat(meatList);
        } catch (Exception e) {

            System.out.println("The price you are looking for do not exist");
        }
    }

    public static void searchingEAN_Fruits(Scanner sc, ArrayList<Fruit> fruitList) {
        System.out.print("Write EAN number for at search product: ");
        int counting = 1;

        try {
            int search = sc.nextInt();
            getLengthOfObjectsText_Dynamisk_Fruit(fruitList);

            for (Fruit fruit : fruitList) {
                if (fruit.getIdkod() == search) {
                    System.out.println("Produkt: " + counting++ + " -> " + fruit);

                } else {
                    System.out.println("The product you are looking  do not exist");
                    break;
                }
            }
            getLengthOfObjectsText_Dynamisk_Fruit(fruitList);
        } catch (Exception e) {
            System.out.println("The price you are looking for do not exist");
        }
    }

    public static void searchingEAN_Meats(Scanner sc, ArrayList<Meat> meatList) {
        System.out.print("Write EAN number for at search product: ");
        int counting = 1;

        try {
            int search = sc.nextInt();
            getLengthOfObjectsText_Dynamisk_Meat(meatList);

            for (Meat meats : meatList) {
                if (meats.getIdkod() == search) {
                    System.out.println("Produkt: " + counting++ + " -> " + meats);

                } else {
                    System.out.println("The product you are looking  do not exist");
                    break;
                }
            }
            getLengthOfObjectsText_Dynamisk_Meat(meatList);
        } catch (Exception e) {
            System.out.println("The price you are looking for do not exist");
        }
    }

    private static void removeFruits(ArrayList<Fruit> fruitArrayList, Scanner sc) {
        lagerSaldoFruits(fruitArrayList);
        getLengthOfObjectsText_Dynamisk_Fruit(fruitArrayList);

        System.out.print("Skriv vad vill du ta bort: ");

        try {
            String removeObject = sc.nextLine().toUpperCase();

            Fruit fruitsObject = getFruitsObject(fruitArrayList, removeObject);

            fruitArrayList.remove(fruitsObject);

        } catch (Exception e) {
            System.out.println("\nDen här produkt finns inte i kategori.");
        }
        lagerSaldoFruits(fruitArrayList);
        getLengthOfObjectsText_Dynamisk_Fruit(fruitArrayList);

    }

    private static Fruit getFruitsObject(ArrayList<Fruit> fruitArrayList, String removeObject) {
        Fruit fruitsObject = fruitArrayList.stream().filter(i -> i.getName().equals(removeObject)).reduce((first, second) -> second).get();
        return fruitsObject;
    }

    private static void removeMeats(ArrayList<Meat> meatArrayList, Scanner sc) {
        lagerSaldoMeats(meatArrayList);
        getLengthOfObjectsText_Dynamisk_Meat(meatArrayList);

        System.out.print("Skriv vad vill du ta bort: ");

        try {
            String removeObject = sc.nextLine().toUpperCase();

            Meat removeFruits = meatArrayList.stream().filter(i -> i.getName().equals(removeObject)).reduce((first, second) -> second).get();

            meatArrayList.remove(removeFruits);
        } catch (Exception e) {
            System.out.println("\nDen här produkt finns inte i kategori.");
        }

        lagerSaldoMeats(meatArrayList);
        getLengthOfObjectsText_Dynamisk_Meat(meatArrayList);
    }

    public static void lagerSaldoFruits(ArrayList<Fruit> fruitArrayList) {
        int counting = 1;

        getLengthOfObjectsText_Dynamisk_Fruit(fruitArrayList);

        for (Fruit fruit : fruitArrayList) {
            System.out.println("Produkt: " + counting++ + " -> " + fruit);

        }

    }

    public static void lagerSaldoMeats(ArrayList<Meat> meatArrayList) {
        int counting = 1;

        getLengthOfObjectsText_Dynamisk_Meat(meatArrayList);

        for (Meat meat : meatArrayList) {
            System.out.println("Produkt: " + counting + " -> " + meat);
            counting++;
        }
    }

    private static void getLengthOfObjectsText_Dynamisk_Fruit(ArrayList<Fruit> fruitArrayList) {

        try {
            int nameLength = fruitArrayList.stream().sorted(Comparator.comparing(Fruit::getName)).mapToInt(i -> i.getName().length()).reduce((first, second) -> second).getAsInt();

            String prisLenght = String.valueOf(fruitArrayList.stream().sorted(Comparator.comparing(Fruit::getPris)).map(i -> String.valueOf(i.getPris())).reduce((first, second) -> second).stream().mapToInt(i -> i.length()).sum());

            String eanLength = String.valueOf(fruitArrayList.stream().sorted(Comparator.comparing(Fruit::getIdkod)).map(i -> String.valueOf(i.getIdkod())).reduce((first, second) -> second).stream().mapToInt(i -> i.length()).sum());

            var nameLengthOfObject = nameLength;
            int prisLengthOfObject = Integer.parseInt(prisLenght);
            int eanLengthObject = Integer.parseInt(eanLength);
            var sumOfObjectLength = nameLengthOfObject + prisLengthOfObject + eanLengthObject;

            int lengthWithOutObjectLength = 37;

            xPlacer("=".repeat(sumOfObjectLength + lengthWithOutObjectLength));
        } catch (Exception e) {
            // catch if fruitArrays empty because this is main for length av objects.
            System.out.println(" ");
        }
    }

    private static void getLengthOfObjectsText_Dynamisk_Meat(ArrayList<Meat> meatArrayList) {
        int nameLength = meatArrayList.stream().sorted(Comparator.comparing(Meat::getName)).mapToInt(i -> i.getName().length()).reduce((first, second) -> second).getAsInt();

        String prisLenght = String.valueOf(meatArrayList.stream().sorted(Comparator.comparing(Meat::getPris)).map(i -> String.valueOf(i.getPris())).reduce((first, second) -> second).stream().mapToInt(i -> i.length()).sum());

        String eanLenght = String.valueOf(meatArrayList.stream().sorted(Comparator.comparing(Meat::getIdkod)).map(i -> String.valueOf(i.getIdkod())).reduce((first, second) -> second).stream().mapToInt(i -> i.length()).sum());

        var nameLengthOfObject = nameLength;
        int prisLengthOfObject = Integer.parseInt(prisLenght);
        int eanLengthObject = Integer.parseInt(eanLenght);
        var sumOfObjectLenth = nameLengthOfObject + prisLengthOfObject + eanLengthObject;

        int lengthWithOutObjectLength = 37;
        xPlacer("=".repeat(sumOfObjectLenth + lengthWithOutObjectLength));
    }

    private static void xPlacer(String repeat) {

        System.out.println(repeat);
    }

    private static void addToReceiptMeat(Scanner sc, ArrayList<Fruit> fruitList, ArrayList<Meat> meatList, ArrayList<Product> productsTogether) {
        lagerSaldoMeats(meatList);
        getLengthOfObjectsText_Dynamisk_Meat(meatList);

        System.out.print("Skriv vad vill du lägga på kvitto: ");


        String removeObject = sc.nextLine().toUpperCase();

        Meat removeMeat = meatList.stream().filter(i -> i.getName().equals(removeObject)).reduce((first, second) -> second).get();

        meatList.remove(removeMeat);


        lagerSaldoMeats(meatList);
        getLengthOfObjectsText_Dynamisk_Meat(meatList);

        productsTogether.add(removeMeat);

        getLengthOfObjectsText_Dynamisk_Fruit(fruitList);
        System.out.println("Den här har du lagt på kvitto: ");
        getLengthOfObjectsText_Dynamisk_Fruit(fruitList);
        productsTogether.forEach(System.out::println);
        getLengthOfObjectsText_Dynamisk_Fruit(fruitList);
    }

    private static void addToReceiptFruit(Scanner sc, ArrayList<Fruit> fruitList, ArrayList<Product> productsTogether) {
        lagerSaldoFruits(fruitList);
        getLengthOfObjectsText_Dynamisk_Fruit(fruitList);

        System.out.print("Skriv vad vill du lägga på kvitto: ");
        try {
            String removeObject = sc.nextLine().toUpperCase();

            Fruit removeFruits = getFruitsObject(fruitList, removeObject);

            fruitList.remove(removeFruits);

            lagerSaldoFruits(fruitList);

            productsTogether.add(removeFruits);

        } catch (Exception e) {
            System.out.println("Den här produkt finns inte i kategori.");
        }

        getLengthOfObjectsText_Dynamisk_Fruit(fruitList);
        System.out.println("Den här har du lagt på kvitto: \n");
        getLengthOfObjectsText_Dynamisk_Fruit(fruitList);

        productsTogether.forEach(System.out::println);
        getLengthOfObjectsText_Dynamisk_Fruit(fruitList);

    }


    private static void receiptPrintOutDiscount(ArrayList<Fruit> fruitList, ArrayList<Product> productsTogether) {
        getLengthOfObjectsText_Dynamisk_Fruit(fruitList);
        System.out.println("Den här är kvitto: ");
        productsTogether.forEach(System.out::println);
        receiptDiscount(productsTogether);
        getLengthOfObjectsText_Dynamisk_Fruit(fruitList);
    }

    private static void receiptPrintOut(ArrayList<Fruit> fruitList, ArrayList<Product> productsTogether) {
        getLengthOfObjectsText_Dynamisk_Fruit(fruitList);
        System.out.println("Den här är kvitto: ");
        productsTogether.forEach(System.out::println);
        receipt(productsTogether);
        getLengthOfObjectsText_Dynamisk_Fruit(fruitList);
    }

    public static BigDecimal receipt(ArrayList<Product> products) {

        BigDecimal sumOfPris = BigDecimal.valueOf(products.stream().mapToInt(Product::getPris).sum());

        System.out.println("\nTotal: " + sumOfPris);

        return sumOfPris;
    }

    public static void receiptDiscount(ArrayList<Product> products) {
        BigDecimal sumOfPris = receipt(products);


        Discounter discounter = amount -> amount.multiply(BigDecimal.valueOf(0.1));

        BigDecimal showDiscount = discounter.applyDiscount(sumOfPris);

        System.out.println("\nRabat : " + showDiscount);
        BigDecimal discounterSum = sumOfPris.subtract(discounter.applyDiscount(sumOfPris));

        System.out.println("\nTotal med rabat: " + discounterSum);
    }
}

//    private static ArrayList<Product> getAllProduct (ArrayList<Fruit> fruitArrayList,ArrayList<Meat> meatArrayList){
//
//
//        ArrayList<Product> productsTogether = new ArrayList<>();
//       productsTogether.addAll(addToReceiptFruit(fruitArrayList));
//       productsTogether.addAll(meatArrayList);
//
//
//        productsTogether.forEach(System.out::println);
//
//        return productsTogether;
//    }
//    private static Fruit addToReceiptFruit(ArrayList<Fruit> fruitArrayList, Scanner sc,ArrayList<Meat> meatArrayList) {
//        String removeObject = sc.nextLine().toUpperCase();
//
//        Fruit fruitsObject = fruitArrayList.stream()
//                .filter(i -> i.getName().equals(removeObject))
//                .reduce((first, second) -> second)
//                .get();
//
//        fruitArrayList.remove(fruitsObject);
//
//        getLengthOfObjectsText_Dynamisk_Fruit(fruitArrayList);
//        System.out.println("Den här har du lagt på kvitto: ");
//        getLengthOfObjectsText_Dynamisk_Fruit(fruitArrayList);
//
//
//        return fruitsObject;
//    }

