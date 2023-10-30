import java.util.Scanner;

public class BinarySearchTreeDriver {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter a command: ");
            String command = scanner.nextLine();
            switch (command) {
                case "p":
                    System.out.print("In-order: ");
                    bst.inOrder();
                    System.out.println();
                    break;
                case "i":
                    System.out.print("Enter a number to insert: ");
                    int insertValue = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    if (bst.retrieve(insertValue)) {
                        System.out.println("The item already exists in the tree.");
                    } else {
                        bst.insert(insertValue);
                    }
                    break;
                case "r":
                    System.out.print("Enter a number to search: ");
                    int searchValue = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    if (bst.retrieve(searchValue)) {
                        System.out.println("Item is present in the tree");
                    } else {
                        System.out.println("Item is not present in the tree");
                    }
                    break;
                case "d":
                    System.out.print("Enter a number to delete: ");
                    int deleteValue = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    if (bst.retrieve(deleteValue)) {
                        bst.delete(deleteValue);
                    } else {
                        System.out.println("The number is not present in the tree");
                    }
                    break;
                case "l":
                    int leafCount = bst.getNumLeafNodes();
                    System.out.println("The number of leaf nodes are " + leafCount);
                    break;
                case "s":
                    System.out.println("Single Parents: " + findSingleParents(bst.getRoot()));
                    break;
                case "c":
                    System.out.print("Enter a number: ");
                    int cousinValue = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    System.out.print(cousinValue + " cousins: ");
                    printCousins(bst.getRoot(), cousinValue);
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