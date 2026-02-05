import java.util.HashSet;
import java.util.Set;

class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}

class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }
}

public class UserService {

    private Set<String> registeredUsers = new HashSet<>();

    public void registerUser(String username) throws UserAlreadyExistsException {
        if (registeredUsers.contains(username)) {
            throw new UserAlreadyExistsException("User already exists: " + username);
        }
        registeredUsers.add(username);
        System.out.println("User registered successfully: " + username);
    }

    public void checkUserExistence(String username) throws UserNotFoundException {
        if (!registeredUsers.contains(username)) {
            throw new UserNotFoundException("User not found: " + username);
        }
        System.out.println("User exists: " + username);
    }

    public static void main(String[] args) {
        UserService service = new UserService();

        try {
            service.registerUser("alice");
            service.registerUser("bob");
            // throw UserAlreadyExistsException
            service.registerUser("alice");
        } catch (UserAlreadyExistsException e) {
            System.err.println("Registration error: " + e.getMessage());
        }

        try {
            // throw UserNotFoundException
            service.checkUserExistence("charlie");
        } catch (UserNotFoundException e) {
            System.err.println("Lookup error: " + e.getMessage());
        }

        try {
            service.checkUserExistence("bob");
        } catch (UserNotFoundException e) {
            System.err.println("Lookup error: " + e.getMessage());
        }
    }
}
