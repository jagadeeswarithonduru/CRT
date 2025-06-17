import java.io.*;
import java.util.*;

public class collection {
    static Scanner sc = new Scanner(System.in);
    static int queueRotations = 0;

    public static void main(String[] args) {
        while (true) {
            try {
                System.out.print("\nEnter an expression (e.g. 2 + 3 * 4) or type 'exit': ");
                String input = sc.nextLine();
                if (input.equalsIgnoreCase("exit")) break;

                List<String> tokens = tokenize(input);
                if (tokens.isEmpty()) {
                    System.out.println("Invalid expression.");
                    continue;
                }

                displayResult(tokens);
                collectionMenu(tokens);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    static List<String> tokenize(String expr) {
        List<String> list = new ArrayList<>();
        String[] parts = expr.trim().split("\\s+");
        for (String part : parts) {
            if (isNumeric(part) || isOperator(part)) {
                list.add(part);
            }
        }
        return list;
    }

    static void collectionMenu(List<String> tokens) {
        while (true) {
            try {
                System.out.println("\n--- Choose Collection ---");
                System.out.println("1. ArrayList");
                System.out.println("2. LinkedList");
                System.out.println("3. Queue");
                System.out.println("4. Back to Expression Input");
                System.out.print("Enter choice: ");
                int choice = getIntInput();

                switch (choice) {
                    case 1 -> handleCollection("ArrayList", new ArrayList<>(tokens));
                    case 2 -> handleCollection("LinkedList", new LinkedList<>(tokens));
                    case 3 -> handleQueue(new LinkedList<>(tokens));
                    case 4 -> { return; }
                    default -> System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("Collection menu error: " + e.getMessage());
            }
        }
    }

    static void handleCollection(String type, List<String> collection) {
        while (true) {
            try {
                System.out.println("\n--- " + type + " Menu ---");
                System.out.println("1. Display Tokens");
                System.out.println("2. Add Token");
                System.out.println("3. Remove Token");
                System.out.println("4. Clear Tokens");
                System.out.println("5. Sort Numbers");
                System.out.println("6. Search Token");
                System.out.println("7. Back to Collections Menu");
                System.out.print("Enter choice: ");

                int choice = getIntInput();
                switch (choice) {
                    case 1 -> displayResult(collection);
                    case 2 -> {
                        addToken(collection);
                        displayResult(collection);
                    }
                    case 3 -> {
                        removeToken(collection);
                        displayResult(collection);
                    }
                    case 4 -> {
                        collection.clear();
                        System.out.println("Tokens cleared.");
                        displayResult(collection);
                    }
                    case 5 -> {
                        sortAndDisplay(collection);
                        displayResult(collection);
                    }
                    case 6 -> searchToken(collection);
                    case 7 -> { return; }
                    default -> System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("Collection error: " + e.getMessage());
            }
        }
    }

    static void handleQueue(Queue<String> queue) {
        queueRotations = 0;
        while (true) {
            try {
                System.out.println("\n--- Queue Menu ---");
                System.out.println("1. Display Queue");
                System.out.println("2. Add Token");
                System.out.println("3. Remove Token");
                System.out.println("4. Clear Queue");
                System.out.println("5. Sort Numbers");
                System.out.println("6. Search Token");
                System.out.println("7. Rotate Queue");
                System.out.println("8. Back to Collections Menu");
                System.out.println("9. Circular Queue Input (custom)");
                System.out.print("Enter choice: ");

                int choice = getIntInput();
                switch (choice) {
                    case 1 -> displayResult(new ArrayList<>(queue));
                    case 2 -> {
                        addToken((List<String>) queue);
                        displayResult(new ArrayList<>(queue));
                    }
                    case 3 -> {
                        removeToken((List<String>) queue);
                        displayResult(new ArrayList<>(queue));
                    }
                    case 4 -> {
                        queue.clear();
                        System.out.println("Queue cleared.");
                        displayResult(new ArrayList<>(queue));
                    }
                    case 5 -> {
                        sortAndDisplay(new ArrayList<>(queue));
                        displayResult(new ArrayList<>(queue));
                    }
                    case 6 -> searchToken((List<String>) queue);
                    case 7 -> {
                        rotateQueue(queue);
                        System.out.println("Queue after rotation: " + queue);
                        System.out.println("Total Rotations: " + queueRotations);
                        displayResult(new ArrayList<>(queue));
                    }
                    case 8 -> { return; }
                    case 9 -> handleCircularIterationQueue();
                    default -> System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("Queue error: " + e.getMessage());
            }
        }
    }

    static void rotateQueue(Queue<String> queue) {
        if (!queue.isEmpty()) {
            String front = queue.poll();
            queue.offer(front);
            queueRotations++;
        }
    }

    static void addToken(List<String> collection) {
        try {
            System.out.print("Enter token to add (number/operator): ");
            String token = sc.nextLine();
            if (isNumeric(token) || isOperator(token)) {
                collection.add(token);
                System.out.println("Token added.");
            } else {
                System.out.println("Invalid token.");
            }
        } catch (Exception e) {
            System.out.println("Add token failed: " + e.getMessage());
        }
    }

    static void removeToken(List<String> collection) {
        try {
            System.out.print("Enter token to remove: ");
            String token = sc.nextLine();
            if (collection.remove(token)) {
                System.out.println("Token removed.");
            } else {
                System.out.println("Token not found.");
            }
        } catch (Exception e) {
            System.out.println("Remove failed: " + e.getMessage());
        }
    }

    static void searchToken(List<String> collection) {
        try {
            System.out.print("Enter token to search: ");
            String token = sc.nextLine();
            if (collection.contains(token)) {
                System.out.println("Token found.");
            } else {
                System.out.println("Token not found.");
            }
        } catch (Exception e) {
            System.out.println("Search failed: " + e.getMessage());
        }
    }

    static void sortAndDisplay(List<String> collection) {
        try {
            List<Double> numbers = new ArrayList<>();
            for (String token : collection) {
                if (isNumeric(token)) {
                    numbers.add(Double.parseDouble(token));
                }
            }
            Collections.sort(numbers);
            System.out.println("Sorted Numbers: " + numbers);
        } catch (Exception e) {
            System.out.println("Sort failed: " + e.getMessage());
        }
    }

    static void displayResult(List<String> tokens) {
        System.out.println("Tokens: " + tokens);
        try {
            double result = evaluateExpression(tokens);
            System.out.println("Result: " + result);
        } catch (Exception e) {
            System.out.println("Evaluation failed: " + e.getMessage());
        }
    }

    static double evaluateExpression(List<String> tokens) {
        try {
            Iterator<String> it = tokens.iterator();
            double result = Double.parseDouble(it.next());

            while (it.hasNext()) {
                String op = it.next();
                double nextNum = Double.parseDouble(it.next());

                switch (op) {
                    case "+" -> result += nextNum;
                    case "-" -> result -= nextNum;
                    case "*" -> result *= nextNum;
                    case "/" -> {
                        if (nextNum == 0) throw new ArithmeticException("Division by zero");
                        result /= nextNum;
                    }
                    default -> throw new IllegalArgumentException("Unknown operator: " + op);
                }
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Invalid expression: " + e.getMessage());
        }
    }

    static void handleCircularIterationQueue() {
        try {
            System.out.print("Enter the values separated by spaces: ");
            String line = sc.nextLine();

            System.out.print("Enter the capacity: ");
            int capacity = Integer.parseInt(sc.nextLine());

            int[] queue = new int[capacity];
            int rear = -1, size = 0, circularCount = 0;
            List<Integer> excluded = new ArrayList<>();

            String[] values = line.trim().split("\\s+");

            for (String val : values) {
                int num = Integer.parseInt(val);

                int prevRear = rear;
                rear = (rear + 1) % capacity;

                if (size == capacity) {
                    excluded.add(num);
                    rear = prevRear; // maintain rear
                } else {
                    queue[rear] = num;
                    size++;
                    if (prevRear == capacity - 1 && rear == 0) {
                        circularCount++;
                    }
                }
            }

            System.out.println("\n--- Final Circular Queue State ---");
            for (int i = 0; i < size; i++) {
                int index = (rear - size + 1 + i + capacity) % capacity;
                System.out.println(queue[index]);
            }

            System.out.println("Circular Count: " + circularCount);
            System.out.println("Excluded Elements (Overflow): " + excluded);
        } catch (Exception e) {
            System.out.println("Circular queue error: " + e.getMessage());
        }
    }

    static boolean isNumeric(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    static boolean isOperator(String s) {
        return s.matches("[+\\-*/]");
    }

    static int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.print("Invalid input. Enter a number: ");
            }
        }
    }
}
j