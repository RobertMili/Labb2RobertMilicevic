import java.io.*;
import java.math.BigDecimal;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //TODO change in addProduct to unComment
        // ta bort fixa med namn ta bort


        final ArrayList<Fruit> fruitList = new ArrayList<>();
        final ArrayList<Meat> meatList = new ArrayList<>();
        final ArrayList<Product> productsTogether = new ArrayList<>();

        readingFile(fruitList, meatList); // creating file or reading file

        while (true) {
            mainMeny();
            String choice = sc.nextLine();

            switch (choice) {
                case "e", "E" -> {
                    // saving file:
                    savingFile(fruitList, meatList);
                    System.exit(0);

                }
                case "1" -> {
                    products();
                    String choice2 = sc.nextLine();

                    if (choice2.equals("1")) {

                        addFruits(sc, fruitList);
                        sc.nextLine();

                    } else if (choice2.equals("2")) {

                        addMeats(sc, meatList);
                        sc.nextLine();
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

                        searchingEANFruits(sc, fruitList);
                        sc.nextLine();

                    } else if (choice5.equals("2")) {

                        searchingEANMeats(sc, meatList);
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

                    } else if (choice7.equals("2")) {

                        lagerSaldoMeats(meatList);

                    }
                }
                case "8" -> {
                    productsAddingToReceipt();
                    String choice7 = sc.nextLine();

                    switch (choice7) {
                        case "1" -> addToReceiptFruit(sc, fruitList, productsTogether);
                        case "2" -> addToReceiptMeat(sc, fruitList, meatList, productsTogether);
                        case "3" -> receiptPrintOut(fruitList, productsTogether);
                        case "4" -> receiptPrintOutDiscount(fruitList, productsTogether);
                    }
                }
                case "9" -> {
                    System.out.println("Den här hade program lagt in:");
                    readingFile(fruitList, meatList);
                    lagerSaldoFruits(fruitList);
                    lagerSaldoMeats(meatList);

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
                9. Spara till file
                e. Avsluta och spara""";

        System.out.println(menyText);
    }

    private static void products() {
        final String secondMeny = """
                Produkt
                ========
                Välja Category:
                1. Välja Frukt
                2. Välja Kött\040
                e. avsluta\040""";
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


        System.out.print("Skriv namn på frukt: ");
        //String name = "BANANA";
        String name = getStringInput(sc);

        printQuestionPrice(name);

        //int price = 10;
        int price = sc.nextInt();

        System.out.print("Skriv  EAN (Qr code) för " + name + ": ");
        //int EAN = 123;
        int EAN = sc.nextInt();

        boolean equals = fruitList.stream().anyMatch(i -> i.getEAN() == EAN);


        immutableEANFruit(fruitList, EAN, name, price, equals);
        //addFruitArrays(fruitList, name, price, EAN);

    }

    private static void immutableEANFruit(ArrayList<Fruit> fruitList, int EAN, String name, int price, boolean equals) {
        if (!equals) {
            addFruitArrays(fruitList, name, price, EAN);
            getLengthOfObjectsTextDynamiskFruit(fruitList);

        } else {
            System.out.println("Den här product med EAN number finns redan, försök med annat EAN number. Tack så mycket!");
        }
    }

    private static void addFruitArrays(ArrayList<Fruit> fruitArrayList, String name, int price, int EAN) {
        fruitArrayList.add(new Fruit(name, price, EAN));
        lagerSaldoFruits(fruitArrayList);
    }

    private static void addMeats(Scanner sc, ArrayList<Meat> meatList) {
        String name;
        int price;
        int EAN;

        System.out.print("Skriv namn på meat:");
        //String name = "KYCKLING";
        name = getStringInput(sc);

        printQuestionPrice(name);
        //int price = 10;
        price = sc.nextInt();

        System.out.print("Skriv  EAN (Qr code) för " + name + ": ");
        //int EAN = 123;
        EAN = sc.nextInt();

        boolean equals = meatList.stream().anyMatch(i -> i.getEAN() == EAN);

        immutableEANMeat(meatList, name, price, EAN, equals);
        //TODO You need to delete this:
        //addMeatLists(meatList, name, price, EAN);

    }
    private static String getStringInput(Scanner sc) {
        return sc.nextLine().toUpperCase();
    }

    private static void printQuestionPrice(String name) {
        System.out.print("Skriv pris på " + name + " : ");
    }

    private static void immutableEANMeat(ArrayList<Meat> meatList, String name, int price, int EAN, boolean equals) {
        if (!equals) {
            addMeatLists(meatList, name, price, EAN);
            getLengthOfObjectsTextDynamisk_Meat(meatList);

        } else {
            System.out.println("Den här product med EAN number finns redan, försök med annat EAN number. Tack så mycket!");
        }
    }

    private static void addMeatLists(ArrayList<Meat> meatList, String name, int price, int EAN) {
        meatList.add(new Meat(name, price, EAN));
        lagerSaldoMeats(meatList);
    }

    public static void searchingFruitsNamn(Scanner sc, ArrayList<Fruit> fruitList) {
        lagerSaldoFruits(fruitList);

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
        lagerSaldoMeats(meatList);

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
        lagerSaldoFruits(fruitList);

        System.out.print("Write price for at search product: ");
        int counting = 1;


        try {
            int search = sc.nextInt();
            getLengthOfObjectsTextDynamiskFruit(fruitList);

            for (Fruit fruit : fruitList) {
                if (fruit.getPris() != 0 && fruit.getPris() == search) {
                    System.out.println("Produkt: " + counting++ + " -> " + fruit);

                } else {
                    System.out.println("The product you are looking  do not exist");
                    break;
                }
            }
            getLengthOfObjectsTextDynamiskFruit(fruitList);
        } catch (Exception e) {
            System.out.println("The price you are looking for do not exist");
        }
    }

    public static void searchingMeatPrices(Scanner sc, ArrayList<Meat> meatList) {
        lagerSaldoMeats(meatList);

        System.out.print("Write price for at search product: ");
        int counting = 1;

        try {
            int search = sc.nextInt();
            getLengthOfObjectsTextDynamisk_Meat(meatList);

            for (Meat meats : meatList) {
                if (meats.getPris() != 0 && meats.getPris() == search) {
                    System.out.println("Produkt: " + counting++ + " -> " + meats);

                } else {
                    System.out.println("The product you are looking  do not exist");
                    break;

                }
            }
            getLengthOfObjectsTextDynamisk_Meat(meatList);
        } catch (Exception e) {
            System.out.println("The price you are looking for do not exist");
        }
    }

    private static void searchingFruitsPriceRange(ArrayList<Fruit> fruitList, Scanner sc) {
        lagerSaldoFruits(fruitList);

        System.out.print("Skriv till vilken pris vill du att varor ska skriva ut: ");

        try {
            int search = sc.nextInt();

            getLengthOfObjectsTextDynamiskFruit(fruitList);

            fruitList.stream()
                    .filter(x -> x.getPris() <= search) // Man kan sätta == searching to get samma number som user söker.
                    .forEach(System.out::println);

            getLengthOfObjectsTextDynamiskFruit(fruitList);
        } catch (Exception e) {
            System.out.println("The price you are looking for do not exist");
        }
    }

    private static void searchingMeatsPriceRange(ArrayList<Meat> meatList, Scanner sc) {
        lagerSaldoMeats(meatList);

        System.out.print("Skriv till vilken pris vill du att varor ska skrivas ut : ");

        try {
            int search = sc.nextInt();

            getLengthOfObjectsTextDynamisk_Meat(meatList);

            meatList.stream()
                    .filter(x -> x.getPris() <= search) //Man kan sätta == searching to get samma number som user söker.
                    .forEach(System.out::println);

            getLengthOfObjectsTextDynamisk_Meat(meatList);
        } catch (Exception e) {

            System.out.println("The price you are looking for do not exist");
        }
    }

    public static void searchingEANFruits(Scanner sc, ArrayList<Fruit> fruitList) {
        lagerSaldoFruits(fruitList);

        System.out.print("Skriv till vilken EAN number vill du att varor ska skrivas ut : ");


        try {
            int search = sc.nextInt();
            getLengthOfObjectsTextDynamiskFruit(fruitList);

            fruitList.stream()
                    .filter(x -> x.getEAN() <= search)
                    .forEach(System.out::println);

            getLengthOfObjectsTextDynamiskFruit(fruitList);
        } catch (Exception e) {
            System.out.println("The price you are looking for do not exist");
        }
    }

    public static void searchingEANMeats(Scanner sc, ArrayList<Meat> meatList) {
        lagerSaldoMeats(meatList);

        System.out.print("Skriv till vilken EAN number vill du att varor ska skrivas ut: ");

        try {
            int search = sc.nextInt();
            getLengthOfObjectsTextDynamisk_Meat(meatList);

            meatList.stream()
                    .filter(x -> x.getEAN() <= search)
                    .forEach(System.out::println);

            getLengthOfObjectsTextDynamisk_Meat(meatList);
        } catch (Exception e) {
            System.out.println("The price you are looking for do not exist");
        }
    }

    private static void removeFruits(ArrayList<Fruit> fruitArrayList, Scanner sc) {
        lagerSaldoFruits(fruitArrayList);

        System.out.print("Skriv vad vill du ta bort: ");

        try {
            String removeObject = sc.nextLine().toUpperCase();

            Fruit fruitsObject = getFruitsObject(fruitArrayList, removeObject);

            fruitArrayList.remove(fruitsObject);

        } catch (Exception e) {
            System.out.println("\nDen här produkt finns inte i kategori.");
        }
        lagerSaldoFruits(fruitArrayList);

    }

    private static Fruit getFruitsObject(ArrayList<Fruit> fruitArrayList, String removeObject) {
        return fruitArrayList.stream()
                .filter(i -> i.getName()
                        .equals(removeObject))
                .reduce((first, second) -> second)
                .orElse(null);
    }

    private static void removeMeats(ArrayList<Meat> meatArrayList, Scanner sc) {
        lagerSaldoMeats(meatArrayList);


        System.out.print("Skriv vad vill du ta bort: ");

        try {
            String removeObject = sc.nextLine().toUpperCase();

            Meat removeFruits = meatArrayList.stream()
                    .filter(i -> i.getName().equals(removeObject))
                    .reduce((first, second) -> second)
                    .orElse(null);

            meatArrayList.remove(removeFruits);
        } catch (Exception e) {
            System.out.println("\nDen här produkt finns inte i kategori.");
        }

        lagerSaldoMeats(meatArrayList);

    }

    public static void lagerSaldoFruits(ArrayList<Fruit> fruitArrayList) {
        int counting = 1;

        getLengthOfObjectsTextDynamiskFruit(fruitArrayList);
        System.out.println("              Namn      Pris    Ean");

        for (Fruit fruit : fruitArrayList) {
            System.out.println("Produkt: " + counting++ + " -> " + fruit);

        }
        getLengthOfObjectsTextDynamiskFruit(fruitArrayList);

    }

    public static void lagerSaldoMeats(ArrayList<Meat> meatArrayList) {
        int counting = 1;

        getLengthOfObjectsTextDynamisk_Meat(meatArrayList);
        System.out.println("              Namn        Pris    Ean");

        for (Meat meat : meatArrayList) {
            System.out.println("Produkt: " + counting + " -> " + meat);
            counting++;
        }
        getLengthOfObjectsTextDynamisk_Meat(meatArrayList);
    }

    private static void getLengthOfObjectsTextDynamiskFruit(ArrayList<Fruit> fruitArrayList) {

        try {
            int nameLengthObject = fruitArrayList.stream()
                    .sorted(Comparator.comparing(Fruit::getName))
                    .mapToInt(i -> i.getName().length())
                    .reduce((first, second) -> second)
                    .orElse(0);

            String priceLength = String.valueOf(fruitArrayList.stream()
                    .sorted(Comparator.comparing(Fruit::getPris))
                    .map(i -> String.valueOf(i.getPris()))
                    .reduce((first, second) -> second)
                    .stream().mapToInt(String::length)
                    .sum());

            String eanLength = String.valueOf(fruitArrayList.stream()
                    .sorted(Comparator.comparing(Fruit::getEAN))
                    .map(i -> String.valueOf(i.getEAN()))
                    .reduce((first, second) -> second)
                    .stream().mapToInt(String::length)
                    .sum());

            int prisLengthOfObject = Integer.parseInt(priceLength);
            int eanLengthObject = Integer.parseInt(eanLength);
            var sumOfObjectLength = nameLengthObject + prisLengthOfObject + eanLengthObject;

            int lengthWithOutObjectLength = 37;

            xPlacer("=".repeat(sumOfObjectLength + lengthWithOutObjectLength));
        } catch (Exception e) {
            // catch if fruitArrays empty because this is main for length av objects.
            System.out.println(" ");
        }
    }

    private static void getLengthOfObjectsTextDynamisk_Meat(ArrayList<Meat> meatArrayList) {
        try {
            int nameLengthObject = meatArrayList.stream().sorted(Comparator.comparing(Meat::getName)).mapToInt(i -> i.getName().length()).reduce((first, second) -> second).orElse(0);

            String prisLength = String.valueOf(meatArrayList.stream().sorted(Comparator.comparing(Meat::getPris)).map(i -> String.valueOf(i.getPris())).reduce((first, second) -> second).stream().mapToInt(String::length).sum());

            String eanLength = String.valueOf(meatArrayList.stream().sorted(Comparator.comparing(Meat::getEAN)).map(i -> String.valueOf(i.getEAN())).reduce((first, second) -> second).stream().mapToInt(String::length).sum());

            int prisLengthOfObject = Integer.parseInt(prisLength);
            int eanLengthObject = Integer.parseInt(eanLength);
            var sumOfObjectLength = nameLengthObject + prisLengthOfObject + eanLengthObject;

            int lengthWithOutObjectLength = 37;
            xPlacer("=".repeat(sumOfObjectLength + lengthWithOutObjectLength));
        } catch (Exception e) {
            // catch if fruitArrays empty because this is main for length av objects.
            System.out.println(" ");
        }
    }

    private static void xPlacer(String repeat) {

        System.out.println(repeat);
    }

    private static void addToReceiptFruit(Scanner sc, ArrayList<Fruit> fruitList, ArrayList<Product> productsTogether) {
        lagerSaldoFruits(fruitList);

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

        System.out.println("\nDen här har du lagt på kvitto: ");
        getLengthOfObjectsTextDynamiskFruit(fruitList);

        productsTogether.forEach(System.out::println);
        getLengthOfObjectsTextDynamiskFruit(fruitList);

    }
    private static void addToReceiptMeat(Scanner sc, ArrayList<Fruit> fruitList, ArrayList<Meat> meatList, ArrayList<Product> productsTogether) {
        lagerSaldoMeats(meatList);

        System.out.print("Skriv vad vill du lägga på kvitto: ");


        String removeObject = sc.nextLine().toUpperCase();

        Meat removeMeat = meatList.stream().filter(i -> i.getName().equals(removeObject)).reduce((first, second) -> second).orElse(null);

        meatList.remove(removeMeat);

        lagerSaldoMeats(meatList);
        getLengthOfObjectsTextDynamisk_Meat(meatList);

        productsTogether.add(removeMeat);

        System.out.println("\nDen här har du lagt på kvitto: \n");
        getLengthOfObjectsTextDynamiskFruit(fruitList);

        productsTogether.forEach(System.out::println);
        getLengthOfObjectsTextDynamiskFruit(fruitList);
    }

    private static void receiptPrintOutDiscount(ArrayList<Fruit> fruitList, ArrayList<Product> productsTogether) {
        getLengthOfObjectsTextDynamiskFruit(fruitList);
        System.out.println("Den här är kvitto: \n");
        productsTogether.forEach(System.out::println);
        receiptDiscount(productsTogether);
        getLengthOfObjectsTextDynamiskFruit(fruitList);
    }

    private static void receiptPrintOut(ArrayList<Fruit> fruitList, ArrayList<Product> productsTogether) {
        getLengthOfObjectsTextDynamiskFruit(fruitList);
        System.out.println("Den här är kvitto: \n");
        productsTogether.forEach(System.out::println);
        receipt(productsTogether);
        getLengthOfObjectsTextDynamiskFruit(fruitList);
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

    private static void readingFile(ArrayList<Fruit> fruitArrayList, ArrayList<Meat> meatArrayList) {

        readingFileFruit(fruitArrayList);
        readingFileMeat(meatArrayList);

    }

    private static void savingFile(ArrayList<Fruit> fruitArrayList, ArrayList<Meat> meatArrayList) {
        savingFruitFolder(fruitArrayList);
        savingMeatFolder(meatArrayList);
    }

    private static void savingFruitFolder(ArrayList<Fruit> fruitList) {
        String homeFolder = getHomeFolder();

        Path filePath = Path.of(homeFolder, "FruitFolder.txt");

        for (Fruit fruit : fruitList) {
            creatingReadingFile(filePath, fruit + System.lineSeparator(), fruit);
        }

        System.out.println("File har sparat på :" + filePath);
    }

    private static void savingMeatFolder(ArrayList<Meat> meatList) {
        String homeFolder = getHomeFolder();


        Path filePath = Path.of(homeFolder, "MeatFolder.txt");

        for (Meat meat : meatList) {
            creatingReadingFile(filePath, meat + System.lineSeparator(), null);
        }
        System.out.println("File har sparat på :" + filePath);
    }

    private static void creatingReadingFile(Path filePath, String s, Fruit ignoredFruit) {
        try {
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
                System.out.println("Creating new Folder");
            }
            Files.writeString(filePath, s, StandardOpenOption.APPEND);

        } catch (FileAlreadyExistsException e) {
            System.out.println("File already exists: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e.getClass().getName() + " " + e.getMessage());
        }
    }

    private static void readingFileFruit(ArrayList<Fruit> fruitArrayList) {
        String homeFolder = getHomeFolder();

        Path filePath = Path.of(homeFolder, "FruitFolder.txt");

        Scanner s;
        try {
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
                System.out.println("Creating new Folder: " + filePath);

            } else {

                s = new Scanner(new File(String.valueOf(filePath)));

                ArrayList<String> list = new ArrayList<>();
                while (s.hasNext()) {

                    list.add(s.next());
                }
                s.close();

                if (!list.isEmpty()) {

                    String listProduct;
                    int listPris = Integer.parseInt(list.get(1));
                    int listEAN = Integer.parseInt(list.get(2));


                    for (int i = 0; i < list.size(); i = i + 3) {
                        listProduct = list.get(i);

                        fruitArrayList.add(new Fruit(listProduct, listPris, listEAN));
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void readingFileMeat(ArrayList<Meat> meatArrayList) {
        String homeFolder = getHomeFolder();

        Path filePath = Path.of(homeFolder, "MeatFolder.txt");

        Scanner s;
        try {
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
                System.out.println("Creating new Folder: " + filePath);

            } else {
                s = new Scanner(new File(String.valueOf(filePath)));

                ArrayList<String> list = new ArrayList<>();
                while (s.hasNext()) {

                    list.add(s.next());

                }
                s.close();

                if (!list.isEmpty()) {

                    String listProduct;
                    int listPris = Integer.parseInt(list.get(1));
                    int listEAN = Integer.parseInt(list.get(2));

                    for (int i = 0; i < list.size(); i = i + 3) {
                        listProduct = list.get(i);

                        meatArrayList.add(new Meat(listProduct, listPris, listEAN));
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static String getHomeFolder() {
        //System.out.println(homeFolder);
        return System.getProperty("user.home");
    }
}


