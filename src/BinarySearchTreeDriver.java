import java.util.Scanner;

public class BinarySearchTreeDriver {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter list type (i - int, d - double, s - string): ");
        String type = keyboard.next();
        switch (type) {
            case "i":
                BinarySearchTreeDriver.<Integer>genericRun();
                break;

            case "d":
                BinarySearchTreeDriver.<Double>genericRun();
                break;

            case "s":
                BinarySearchTreeDriver.<String>genericRun();
        
            default:
                break;
        }
        keyboard.close();  
    }
        
        private static <T extends Comparable<T>> void genericRun() {
        BinarySearchTree<T> bst = new BinarySearchTree<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter a command: ");
            String command = scanner.nextLine();
            switch (command) {
                case "p":
                    printList(bst);
                    break;
                case "i":
                    printList(bst);
                    System.out.print("Enter a value to insert: ");
                    T insertValue = (T)scanner.next();
                    scanner.nextLine(); // Consume the newline character
                    if (bst.retrieve(insertValue)) {
                        System.out.println("The item already exists in the tree.");
                    } else {
                        bst.insert(insertValue);
                    }
                    printList(bst);
                    break;
                case "r":
                    printList(bst);
                    System.out.print("Enter a value to search: ");
                    T searchValue = (T)scanner.next();
                    scanner.nextLine(); // Consume the newline character
                    if (bst.retrieve(searchValue)) {
                        System.out.println("Item is present in the tree");
                    } else {
                        System.out.println("Item is not present in the tree");
                    }
                    break;
                case "d":
                    printList(bst);
                    System.out.print("Enter a value to delete: ");
                    T deleteValue = (T)scanner.next();
                    scanner.nextLine(); // Consume the newline character
                    if (bst.retrieve(deleteValue)) {
                        bst.delete(deleteValue);
                    } else {
                        System.out.println("The number is not present in the tree");
                    }
                    printList(bst);
                    break;
                case "l":
                    int leafCount = bst.getNumLeafNodes();
                    System.out.println("The number of leaf nodes are " + leafCount);
                    break;
                case "s":
                    System.out.println("Single Parents: "); 
                    bst.getSingleParent();
                    break;
                case "c":
                    printList(bst);
                    System.out.print("Enter a number: ");
                    T cousinValue = (T)scanner.next();
                    scanner.nextLine(); // Consume the newline character
                    System.out.print(cousinValue + " cousins: ");
                    bst.getCousins(new BinarySearchTree.Node<T>(cousinValue));
                    System.out.println();
                    break;
                case "q":
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid command. Please try again.");
                    break;
            }
        }
    }
    private static <T extends Comparable<T>> void printList(BinarySearchTree<T> bst) {
        System.out.print("In-order: ");
        bst.inOrder();
        System.out.println();
    }
}