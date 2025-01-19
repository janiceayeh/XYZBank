import java.util.InputMismatchException;
import java.util.Scanner;

// Defines a class to represent a loan record
class Record {
    private final String recordId;
    private final String customerId;
    private final String loanType;
    private final double interestRate;
    private final double amountLeft;
    private final int termLeft;

    // Constructor to initialize the properties
    public Record(String recordId, String customerId, String loanType, double interestRate, double amountLeft, int termLeft) {
        this.recordId = recordId;
        this.customerId = customerId;
        this.loanType = loanType;
        this.interestRate = interestRate;
        this.amountLeft = amountLeft;
        this.termLeft = termLeft;
    }

    // Method to display a loan record
    public void displayRecord() {
        System.out.printf("%-10s %-10s %-10s %-15.2f %-15.2f %-10d%n", recordId, customerId, loanType, interestRate, amountLeft, termLeft);
    }
}

public class XYZBank {
    // Create a Scanner object to read user input
    private static final Scanner scanner = new Scanner(System.in);
    // Define the types of loans
    private static final String[] LOAN_TYPES = {"Auto", "Builder", "Mortgage", "Personal", "Other"};

    // Main method to execute the program
    public static void main(String[] args) {
        try {
            // Ask the user to enter the maximum number of records
            System.out.print("Enter the maximum number of records: ");
            int numRecords = scanner.nextInt();
            scanner.nextLine();

            // Create an array to store the records
            Record[] records = new Record[numRecords];
            // Loop to input details for each record
            for (int i = 0; i < numRecords; i++) {
                System.out.println("\nEnter details for Record " + (i + 1));
                records[i] = createRecord(); // Create a record and add it to the array
            }
            // Print all records
            printRecords(records);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        } finally {
            scanner.close(); // Close the scanner
        }
    }

    // Method to create a record
    public static Record createRecord() {
        String recordId = "";
        String customerId = "";
        String loanType = "";
        double interestRate = 0.0;
        double amountLeft = 0.0;
        int termLeft = 0;

        try {
            // Get a valid Record ID
            recordId = getValidRecordId();
            // Get a valid Customer ID
            customerId = getValidCustomerId();
            // Get a valid loan type
            do {
                System.out.print("Enter loan type (Auto/Builder/Mortgage/Personal/Other): ");
                loanType = scanner.nextLine();
            } while (!isValidLoanType(loanType));

            // Get interest rate, amount left, and term left
            System.out.print("Enter Interest Rate: ");
            interestRate = scanner.nextDouble();

            System.out.print("Enter Amount left to pay: ");
            amountLeft = scanner.nextDouble();

            System.out.print("Enter loan term left (years): ");
            termLeft = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input format. Please enter valid data.");
            scanner.nextLine(); // Consume the invalid input
            return createRecord(); // Retry creating the record
        }

        return new Record(recordId, customerId, loanType, interestRate, amountLeft, termLeft);
    }

    // Method to get a valid Record ID
    public static String getValidRecordId() {
        String recordId = "";
        boolean isValid = false;
        while (!isValid) {
            System.out.print("Enter Record ID (XXXXXX): ");
            recordId = scanner.nextLine();
            isValid = recordId.matches("\\d{6}"); // Check if the Record ID matches the format XXXXXX (6 digits)
            if (!isValid) {
                System.out.println("Invalid Record ID format. Please enter in the format 'XXXXXX'.");
            }
        }
        return recordId;
    }

    // Method to get a valid Customer ID
    public static String getValidCustomerId() {
        String customerId = "";
        boolean isValid = false;
        while (!isValid) {
            System.out.print("Enter Customer ID (AAAXXX): ");
            customerId = scanner.nextLine();
            isValid = customerId.matches("[A-Z]{3}\\d{3}"); // Check if the Customer ID matches the format AAAXXX (3 letters followed by 3 digits)
            if (!isValid) {
                System.out.println("Invalid Customer ID format. Please enter in the format 'AAAXXX'.");
            }
        }
        return customerId;
    }

    // Method to check if a loan type is valid
    public static boolean isValidLoanType(String loanType) {
        for (String type : LOAN_TYPES) {
            if (type.equalsIgnoreCase(loanType)) {
                return true;
            }
        }
        System.out.println("Invalid loan type. Please enter a valid loan type.");
        return false;
    }

    // Method to print all records
    public static void printRecords(Record[] records) {
        System.out.println("\nRecords: ");
        System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s%n", "Record ID", "Customer ID", "Loan Type", "Interest Rate", "Amount Left", "Term Left");
        for (Record record : records) {
            record.displayRecord();
        }
    }
}
