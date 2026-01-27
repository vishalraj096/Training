import java.util.*;

public class SocialMediaFriendConnections {
    static class User {
        int id;
        String name;
        int age;
        List<Integer> friends = new ArrayList<>();
        User next;

        User(int id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }
    }

    static class UserList {
        User head;

        void addUser(User u) {
            if (head == null) {
                head = u;
                return;
            }
            User c = head;
            while (c.next != null)
                c = c.next;
            c.next = u;
        }

        User findById(int id) {
            for (User c = head; c != null; c = c.next)
                if (c.id == id)
                    return c;
            return null;
        }

        List<User> searchByName(String name) {
            List<User> r = new ArrayList<>();
            for (User c = head; c != null; c = c.next)
                if (c.name.equalsIgnoreCase(name))
                    r.add(c);
            return r;
        }

        void displayFriends(int id) {
            User u = findById(id);
            if (u == null) {
                System.out.println("User not found.");
                return;
            }
            if (u.friends.isEmpty()) {
                System.out.println("No friends.");
                return;
            }
            System.out.println("Friends of " + u.name + ":");
            for (int fid : u.friends) {
                User f = findById(fid);
                System.out.printf("%d - %s%n", fid, f != null ? f.name : "(unknown)");
            }
        }

        void addConnection(int a, int b) {
            if (a == b)
                return;
            User ua = findById(a), ub = findById(b);
            if (ua == null || ub == null) {
                System.out.println("User not found.");
                return;
            }
            if (!ua.friends.contains(b))
                ua.friends.add(b);
            if (!ub.friends.contains(a))
                ub.friends.add(a);
            System.out.println("Connection added.");
        }

        void removeConnection(int a, int b) {
            User ua = findById(a), ub = findById(b);
            if (ua == null || ub == null) {
                System.out.println("User not found.");
                return;
            }
            ua.friends.remove(Integer.valueOf(b));
            ub.friends.remove(Integer.valueOf(a));
            System.out.println("Connection removed.");
        }

        List<Integer> mutualFriends(int a, int b) {
            User ua = findById(a), ub = findById(b);
            List<Integer> r = new ArrayList<>();
            if (ua == null || ub == null)
                return r;
            Set<Integer> set = new HashSet<>(ua.friends);
            for (int x : ub.friends)
                if (set.contains(x))
                    r.add(x);
            return r;
        }

        void countFriends() {
            for (User c = head; c != null; c = c.next)
                System.out.printf("%s(%d) -> %d friends%n", c.name, c.id, c.friends.size());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserList users = new UserList();
        while (true) {
            System.out.println("\nSocial Media Friend Connections (Singly Linked List)");
            System.out.println("1. Add user");
            System.out.println("2. Add friend connection");
            System.out.println("3. Remove friend connection");
            System.out.println("4. Find mutual friends");
            System.out.println("5. Display all friends of a user");
            System.out.println("6. Search user by Name");
            System.out.println("7. Search user by ID");
            System.out.println("8. Count number of friends per user");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            int ch = safeInt(sc);
            if (ch == 0)
                break;
            switch (ch) {
                case 1 -> users.addUser(readUser(sc));
                case 2 -> {
                    System.out.print("User ID A: ");
                    int a = safeInt(sc);
                    System.out.print("User ID B: ");
                    int b = safeInt(sc);
                    users.addConnection(a, b);
                }
                case 3 -> {
                    System.out.print("User ID A: ");
                    int a = safeInt(sc);
                    System.out.print("User ID B: ");
                    int b = safeInt(sc);
                    users.removeConnection(a, b);
                }
                case 4 -> {
                    System.out.print("User ID A: ");
                    int a = safeInt(sc);
                    System.out.print("User ID B: ");
                    int b = safeInt(sc);
                    List<Integer> mf = users.mutualFriends(a, b);
                    if (mf.isEmpty())
                        System.out.println("No mutual friends.");
                    else {
                        System.out.println("Mutual friend IDs: " + mf);
                    }
                }
                case 5 -> {
                    System.out.print("User ID: ");
                    int id = safeInt(sc);
                    users.displayFriends(id);
                }
                case 6 -> {
                    System.out.print("Name: ");
                    String n = sc.nextLine().trim();
                    List<User> r = users.searchByName(n);
                    if (r.isEmpty())
                        System.out.println("No results.");
                    else
                        for (User u : r)
                            System.out.printf("%d - %s (Age %d)%n", u.id, u.name, u.age);
                }
                case 7 -> {
                    System.out.print("User ID: ");
                    int id = safeInt(sc);
                    User u = users.findById(id);
                    if (u == null)
                        System.out.println("Not found.");
                    else
                        System.out.printf("%d - %s (Age %d)%n", u.id, u.name, u.age);
                }
                case 8 -> users.countFriends();
                default -> System.out.println("Invalid.");
            }
        }
        sc.close();
    }

    static User readUser(Scanner sc) {
        System.out.print("User ID: ");
        int id = safeInt(sc);
        System.out.print("Name: ");
        String name = sc.nextLine().trim();
        System.out.print("Age: ");
        int age = safeInt(sc);
        return new User(id, name, age);
    }

    static int safeInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            sc.nextLine();
            System.out.print("Enter integer: ");
        }
        int v = sc.nextInt();
        sc.nextLine();
        return v;
    }
}
