import java.util.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class InvalidProductCodeException extends Exception {
    public InvalidProductCodeException(String message) {
        super(message);
    }
}

abstract class Product {
    protected String code;
    protected String name;
    protected double price;

    public Product(String code, String name, double price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    
    public abstract String getDetails();
}

class WesternWear extends Product {
    public WesternWear(String code, String name, double price) {
        super(code, name, price);
    }
    @Override
    public String getDetails() { return "[Western]   " + name; }
}

class EthnicWear extends Product {
    public EthnicWear(String code, String name, double price) {
        super(code, name, price);
    }
    @Override
    public String getDetails() { return "[Ethnic]    " + name; }
}

class Accessory extends Product {
    public Accessory(String code, String name, double price) {
        super(code, name, price);
    }
    @Override
    public String getDetails() { return "[Accessory] " + name; }
}

class CartItem {
    Product product;
    int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    public double getTotal() { return product.getPrice() * quantity; }
}

class ReceiptGeneratorThread extends Thread {
    private List<CartItem> cart;
    private double subTotal;
    private double taxAmount;
    private double grandTotal;
    private String timestamp;
    private String customerName;
    private String mobileNumber;

    public ReceiptGeneratorThread(List<CartItem> cart, double subTotal, double taxAmount, double grandTotal, String customerName, String mobileNumber) {
        this.cart = new ArrayList<>(cart); 
        this.subTotal = subTotal;
        this.taxAmount = taxAmount;
        this.grandTotal = grandTotal;
        this.customerName = customerName;
        this.mobileNumber = mobileNumber;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public void run() {
        try (FileWriter fileWriter = new FileWriter("receipts_log.txt", true);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            
            printWriter.println("\n========================================");
            printWriter.println("      Smart Clothes For Smarter You");
            printWriter.println("========================================");
            printWriter.println(" Date    : " + timestamp);
            printWriter.println(" Customer: " + customerName);
            printWriter.println(" Mobile  : " + mobileNumber);
            printWriter.println("----------------------------------------");
            
            for (CartItem item : cart) {
                printWriter.printf("%-18s x%2d   $%.2f\n", item.product.getName(), item.quantity, item.getTotal());
            }
            
            printWriter.println("----------------------------------------");
            printWriter.printf(" SUBTOTAL:                $%.2f\n", subTotal);
            printWriter.printf(" GST (18%%):               $%.2f\n", taxAmount);
            printWriter.println("----------------------------------------");
            printWriter.printf(" GRAND TOTAL (Incl. Tax): $%.2f\n", grandTotal);
            printWriter.println("========================================\n");
            
            Thread.sleep(1000); 
            System.out.println("\n[SYSTEM] Invoice saved for " + customerName);
            
        } catch (IOException | InterruptedException e) {
            System.out.println("Receipt Generation Error: " + e.getMessage());
        }
    }
}

class StoreManager {
    private Map<String, Product> inventory = new HashMap<>();
    private List<CartItem> currentCart = new ArrayList<>();

    public void loadInventory() {
        inventory.put("WST01", new WesternWear("WST01", "Denim Jacket", 45.99));
        inventory.put("WST02", new WesternWear("WST02", "Graphic T-Shirt", 15.50));
        inventory.put("WST03", new WesternWear("WST03", "Cargo Pants", 29.99));
        inventory.put("WST04", new WesternWear("WST04", "Polo T-Shirt", 22.50));
        inventory.put("WST05", new WesternWear("WST05", "Trench Coat", 89.99));
        inventory.put("WST06", new WesternWear("WST06", "Cotton Shorts", 19.99));
        
        inventory.put("ETH01", new EthnicWear("ETH01", "Silk Kurta", 35.00));
        inventory.put("ETH02", new EthnicWear("ETH02", "Cotton Saree", 55.00));
        inventory.put("ETH03", new EthnicWear("ETH03", "Designer Sherwani", 120.00));
        inventory.put("ETH04", new EthnicWear("ETH04", "Nehru Jacket", 45.00));
        inventory.put("ETH05", new EthnicWear("ETH05", "Lehenga Choli", 150.00));
        inventory.put("ETH06", new EthnicWear("ETH06", "Dhoti Kurta", 40.00));
        
        inventory.put("ACC01", new Accessory("ACC01", "Leather Belt", 12.50));
        inventory.put("ACC02", new Accessory("ACC02", "Aviator Glasses", 22.00));
        inventory.put("ACC03", new Accessory("ACC03", "Silver Watch", 85.00));
        inventory.put("ACC04", new Accessory("ACC04", "Silk Tie", 18.00));
        inventory.put("ACC05", new Accessory("ACC05", "Leather Wallet", 25.00));
        inventory.put("ACC06", new Accessory("ACC06", "Gold Cufflinks", 45.00));
    }

    public void showInventory() {
        System.out.println("\n--- PRODUCT INVENTORY ---");
        List<String> sortedKeys = new ArrayList<>(inventory.keySet());
        Collections.sort(sortedKeys);
        
        for (String key : sortedKeys) {
            Product p = inventory.get(key);
            System.out.printf("Code: %s | %-25s | Price: $%.2f\n", p.getCode(), p.getDetails(), p.getPrice());
        }
        System.out.println("-------------------------");
    }

    public void addToCart(String code, int quantity) throws InvalidProductCodeException {
        if (!inventory.containsKey(code)) {
            throw new InvalidProductCodeException("Invalid Code: " + code + " not found in inventory.");
        }
        Product p = inventory.get(code);
        currentCart.add(new CartItem(p, quantity));
        System.out.println("Added " + quantity + "x " + p.getName() + " to cart.");
    }

    public void processCheckout(Scanner scanner) {
        if (currentCart.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }

        System.out.println("\n--- CHECKOUT ---");
        System.out.print("Customer Name: ");
        String customerName = scanner.nextLine().trim();
        System.out.print("Mobile Number: ");
        String mobileNumber = scanner.nextLine().trim();

        double subTotal = 0;
        for (CartItem item : currentCart) {
            subTotal += item.getTotal();
        }

        double taxAmount = subTotal * 0.18;
        double grandTotal = subTotal + taxAmount;

        ReceiptGeneratorThread printer = new ReceiptGeneratorThread(currentCart, subTotal, taxAmount, grandTotal, customerName, mobileNumber);
        printer.start();

        System.out.printf("\nPayment due for %s: $%.2f\n", customerName, grandTotal);
        currentCart.clear(); 
    }
}

public class BillingSystemMain {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        StoreManager store = new StoreManager();
        store.loadInventory();

        System.out.println("=========================================");
        System.out.println("     Smart Clothes For Smarter You       ");
        System.out.println("=========================================");
        
        while (true) {
            store.showInventory();
            System.out.print("Scan Product Code (type 'BILL' to print, 'EXIT' to close): ");
            String userInput = inputScanner.nextLine().trim().toUpperCase();

            if (userInput.equals("EXIT")) {
                System.out.println("Shutting down POS...");
                break;
            }

            if (userInput.equals("BILL")) {
                store.processCheckout(inputScanner);
                continue;
            }

            try {
                System.out.print("Quantity: ");
                int itemQty = Integer.parseInt(inputScanner.nextLine().trim());
                
                if (itemQty <= 0) {
                    System.out.println("Quantity must be at least 1.");
                    continue;
                }

                store.addToCart(userInput, itemQty);
                
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a number for quantity.");
            } catch (InvalidProductCodeException e) {
                System.out.println(e.getMessage());
            }
        }
        inputScanner.close();
    }
}