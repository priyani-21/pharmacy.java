import java.util.*;

class Medicine {
    int id, stock;
    String name;
    double price;

    Medicine(int id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String toString() {
        return id + " | " + name + " | ₹" + price + " | Stock: " + stock;
    }
}

class Node {
    Medicine data;
    Node left, right;

    Node(Medicine data) {
        this.data = data;
    }
}

class BST {
    Node root;

    void insert(Medicine m) {
        root = insertNode(root, m);
    }

    Node insertNode(Node r, Medicine m) {
        if (r == null) return new Node(m);

        if (m.name.compareToIgnoreCase(r.data.name) < 0)
            r.left = insertNode(r.left, m);
        else
            r.right = insertNode(r.right, m);

        return r;
    }

    void search(Node r, String name) {
        if (r == null) {
            System.out.println("Medicine not found");
            return;
        }

        if (r.data.name.equalsIgnoreCase(name))
            System.out.println(r.data);
        else if (name.compareToIgnoreCase(r.data.name) < 0)
            search(r.left, name);
        else
            search(r.right, name);
    }
}

public class PharmacyApp {

    static Scanner sc = new Scanner(System.in);

    static HashMap<Integer, Medicine> medicines = new HashMap<>();
    static BST bst = new BST();

    static void addMedicine() {
        System.out.print("ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Price: ");
        double price = sc.nextDouble();

        System.out.print("Stock: ");
        int stock = sc.nextInt();

        Medicine m = new Medicine(id, name, price, stock);
        medicines.put(id, m);
        bst.insert(m);

        System.out.println("Medicine added successfully.");
    }

    static void sellMedicine() {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();

        Medicine m = medicines.get(id);

        if (m == null)
            System.out.println("Medicine not found.");
        else {
            System.out.print("Quantity: ");
            int q = sc.nextInt();

            if (q <= m.stock) {
                m.stock -= q;
                System.out.println("Sale successful.");
            } else {
                System.out.println("Not enough stock.");
            }
        }
    }

    static void display() {
        for (Medicine m : medicines.values())
            System.out.println(m);
    }

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n--- PHARMACY INVENTORY ---");
            System.out.println("1. Add Medicine");
            System.out.println("2. Search Medicine");
            System.out.println("3. Sell Medicine");
            System.out.println("4. Display Medicines");
            System.out.println("5. Exit");

            System.out.print("Choice: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1:
                    addMedicine();
                    break;

                case 2:
                    System.out.print("Enter medicine name: ");
                    String name = sc.nextLine();
                    bst.search(bst.root, name);
                    break;

                case 3:
                    sellMedicine();
                    break;

                case 4:
                    display();
                    break;

                case 5:
                    System.out.println("Thank you!");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}