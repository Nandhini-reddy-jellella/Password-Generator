import java.util.*;

public class Password {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String special = "!@#$%^&*()-_=+<>?";

        StringBuilder allowed = new StringBuilder();

        System.out.println("=== Strong Password Generator ===");

        System.out.print("Enter password length (minimum 6): ");
        int length = scanner.nextInt();
        scanner.nextLine();

        if (length < 6) {
            System.out.println("Password too short. Use at least 6 characters.");
            return;
        }

        System.out.print("Include uppercase letters? (y/n): ");
        if (scanner.nextLine().equalsIgnoreCase("y")) {
            allowed.append(upper);
        }

        System.out.print("Include lowercase letters? (y/n): ");
        if (scanner.nextLine().equalsIgnoreCase("y")) {
            allowed.append(lower);
        }

        System.out.print("Include numbers? (y/n): ");
        if (scanner.nextLine().equalsIgnoreCase("y")) {
            allowed.append(numbers);
        }

        System.out.print("Include special characters? (y/n): ");
        if (scanner.nextLine().equalsIgnoreCase("y")) {
            allowed.append(special);
        }

        if (allowed.length() == 0) {
            System.out.println("Error: Select at least one character type.");
            return;
        }

        String password = generatePassword(length, allowed.toString());
        System.out.println("Generated Password: " + password);
        System.out.println("Password Strength: " + ratePassword(password));
        scanner.close();
    }

    // Generate password logic
    public static String generatePassword(int length, String allowedChars) {
        Random rand = new Random();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = rand.nextInt(allowedChars.length());
            password.append(allowedChars.charAt(index));
        }

        return password.toString();
    }

    // Password strength estimator
    public static String ratePassword(String password) {
        int score = 0;

        if (password.length() >= 8) score++;
        if (password.matches(".[A-Z].")) score++;
        if (password.matches(".[a-z].")) score++;
        if (password.matches(".\\d.")) score++;
        if (password.matches(".[!@#$%^&()\\-_=+<>?].*")) score++;

        switch (score) {
            case 5: return "Very Strong";
            case 4: return "Strong";
            case 3: return "Moderate";
            case 2: return "Weak";
            default: return "Very Weak";
        }
    }
}
