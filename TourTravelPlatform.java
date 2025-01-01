import java.util.*;

// Admin.java
class Admin {
    private final String adminId = "zaman07";
    private final String password = "119340F";

    public boolean login(String id, String pwd) {
        return adminId.equals(id) && password.equals(pwd);
    }

    public void resetUserPassword(User user, String newPassword) {
        user.setPassword(newPassword);
    }
}

// User.java
class User {
    private String name;
    private String mobileNumber;
    private String username;
    private String password;

    public User(String name, String mobileNumber, String username, String password) {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public boolean validatePassword(String password) {
        return this.password.equals(password);
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Mobile: " + mobileNumber + ", Username: " + username;
    }
}

// Tour.java
class Tour {
    private String location;
    private String tourMethod;
    private int totalMembers;
    private String startDate;
    private String returnDate;
    private double travelCost;
    private double foodCost;
    private double ticketCost;
    private double stayCost;

    public Tour(String location, String tourMethod, int totalMembers, String startDate, String returnDate,
                double travelCost, double foodCost, double ticketCost, double stayCost) {
        this.location = location;
        this.tourMethod = tourMethod;
        this.totalMembers = totalMembers;
        this.startDate = startDate;
        this.returnDate = returnDate;
        this.travelCost = travelCost;
        this.foodCost = foodCost;
        this.ticketCost = ticketCost;
        this.stayCost = stayCost;
    }

    public double calculateTotalCost() {
        double memberMultiplier = totalMembers > 0 ? totalMembers : 1;
        return (travelCost + foodCost + ticketCost + stayCost) * memberMultiplier;
    }

    @Override
    public String toString() {
        return "Location: " + location + "\nMethod: " + tourMethod + "\nMembers: " + totalMembers +
                "\nDates: " + startDate + " to " + returnDate + "\nTotal Cost: " + calculateTotalCost();
    }
}

// Main.java
public class TourTravelPlatform {
    private static Map<String, User> userDatabase = new HashMap<>();
    private static List<Tour> tours = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Admin admin = new Admin();

        while (true) {
            System.out.println("Welcome to the Tour & Travel Platform");
            System.out.println("1. Admin Login\n2. User Login\n3. Sign Up\n4. Forget Password\n5. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Admin ID: ");
                    String adminId = scanner.nextLine();
                    System.out.print("Enter Password: ");
                    String adminPwd = scanner.nextLine();
                    if (admin.login(adminId, adminPwd)) {
                        System.out.println("Admin logged in successfully.");
                        adminPanel(scanner, admin);
                    } else {
                        System.out.println("Invalid Admin credentials.");
                    }
                    break;
                case 2:
                    System.out.print("Enter Username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter Password: ");
                    String password = scanner.nextLine();
                    if (userDatabase.containsKey(username) && userDatabase.get(username).validatePassword(password)) {
                        System.out.println("Login successful.");
                        userPanel(scanner, userDatabase.get(username));
                    } else {
                        System.out.println("Invalid credentials.");
                    }
                    break;
                case 3:
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Mobile Number: ");
                    String mobile = scanner.nextLine();
                    System.out.print("Choose a Username: ");
                    String newUsername = scanner.nextLine();
                    if (userDatabase.containsKey(newUsername)) {
                        System.out.println("Username already exists. Please choose another.");
                    } else {
                        System.out.print("Create a Password: ");
                        String newPassword = scanner.nextLine();
                        userDatabase.put(newUsername, new User(name, mobile, newUsername, newPassword));
                        System.out.println("Sign up successful. You can now log in.");
                    }
                    break;
                case 4:
                    System.out.print("Enter Username: ");
                    String forgetUsername = scanner.nextLine();
                    if (userDatabase.containsKey(forgetUsername)) {
                        System.out.println("Request sent to Admin for password reset.");
                    } else {
                        System.out.println("Username not found.");
                    }
                    break;
                case 5:
                    System.out.println("Thank you for using the platform. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void adminPanel(Scanner scanner, Admin admin) {
        while (true) {
            System.out.println("\nAdmin Panel");
            System.out.println("1. Reset User Password\n2. Add Tour\n3. View Users\n4. Logout");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Username to reset password: ");
                    String username = scanner.nextLine();
                    if (userDatabase.containsKey(username)) {
                        System.out.print("Enter new password: ");
                        String newPassword = scanner.nextLine();
                        admin.resetUserPassword(userDatabase.get(username), newPassword);
                        System.out.println("Password reset successfully.");
                    } else {
                        System.out.println("User not found.");
                    }
                    break;
                case 2:
                    System.out.print("Enter Location: ");
                    String location = scanner.nextLine();
                    System.out.print("Enter Tour Method (Solo, Couple, Family, Group): ");
                    String method = scanner.nextLine();
                    System.out.print("Enter Total Members (0 for Solo/Couple): ");
                    int members = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Start Date: ");
                    String startDate = scanner.nextLine();
                    System.out.print("Enter Return Date: ");
                    String returnDate = scanner.nextLine();
                    System.out.print("Enter Travel Cost: ");
                    double travelCost = scanner.nextDouble();
                    System.out.print("Enter Food Cost: ");
                    double foodCost = scanner.nextDouble();
                    System.out.print("Enter Ticket Cost: ");
                    double ticketCost = scanner.nextDouble();
                    System.out.print("Enter Stay Cost: ");
                    double stayCost = scanner.nextDouble();
                    scanner.nextLine();
                    tours.add(new Tour(location, method, members, startDate, returnDate, travelCost, foodCost, ticketCost, stayCost));
                    System.out.println("Tour added successfully.");
                    break;
                case 3:
                    System.out.println("Registered Users:");
                    userDatabase.values().forEach(System.out::println);
                    break;
                case 4:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void userPanel(Scanner scanner, User user) {
        while (true) {
            System.out.println("\nUser Panel");
            System.out.println("1. Book a Tour\n2. Logout");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Available Tours:");
                    for (int i = 0; i < tours.size(); i++) {
                        System.out.println((i + 1) + ". " + tours.get(i));
                    }
                    System.out.print("Select a tour by number: ");
                    int tourChoice = scanner.nextInt();
                    scanner.nextLine();
                    if (tourChoice > 0 && tourChoice <= tours.size()) {
                        Tour selectedTour = tours.get(tourChoice - 1);
                        System.out.println("You selected: \n" + selectedTour);
                        System.out.println("Proceeding to payment...");
                        paymentProcess(scanner);
                    } else {
                        System.out.println("Invalid selection.");
                    }
                    break;
                case 2:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void paymentProcess(Scanner scanner) {
        System.out.println("Payment Methods:");
        System.out.println("1. Card\n2. Mobile Financial Services (MFS)");
        System.out.print("Select payment method: ");
        int method = scanner.nextInt();
        scanner.nextLine();

        switch (method) {
            case 1:
                System.out.print("Enter Card Type (Visa, Mastercard, etc.): ");
                String cardType = scanner.nextLine();
                System.out.print("Enter Card Number: ");
                String cardNumber = scanner.nextLine();
                System.out.print("Enter PIN: ");
                String pin = scanner.nextLine();
                System.out.println("Payment successful. Thank you!");
                break;
            case 2:
                System.out.println("Available MFS: Rocket, bKash, MYCash, etc.");
                System.out.print("Enter MFS Provider: ");
                String mfsProvider = scanner.nextLine();
                System.out.print("Enter Account Number: ");
                String accountNumber = scanner.nextLine();
                System.out.print("Enter PIN: ");
                String mfsPin = scanner.nextLine();
                System.out.println("Payment successful. Thank you!");
                break;
            default:
                System.out.println("Invalid payment method. Try again.");
        }
    }
}
