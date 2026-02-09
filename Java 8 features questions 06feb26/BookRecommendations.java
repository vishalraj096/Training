import java.util.*;
import java.util.stream.Collectors;

class Book {
    private String title;
    private String author;
    private String genre;
    private double rating;

    public Book(String title, String author, String genre, double rating) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public double getRating() {
        return rating;
    }
}

class BookRecommendation {
    private String title;
    private double rating;

    public BookRecommendation(String title, double rating) {
        this.title = title;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public double getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "BookRecommendation{" +
                "title='" + title + '\'' +
                ", rating=" + rating +
                '}';
    }
}

public class BookRecommendations {

    public static List<List<BookRecommendation>> getRecommendations(List<Book> books) {
        List<BookRecommendation> top = books.stream()
                .filter(b -> "Science Fiction".equalsIgnoreCase(b.getGenre()) && b.getRating() > 4.0)
                .sorted(Comparator.comparingDouble(Book::getRating).reversed())
                .limit(10)
                .map(b -> new BookRecommendation(b.getTitle(), b.getRating()))
                .collect(Collectors.toList());

        List<List<BookRecommendation>> pages = new ArrayList<>();
        int pageSize = 5;
        for (int i = 0; i < top.size(); i += pageSize) {
            int end = Math.min(i + pageSize, top.size());
            pages.add(top.subList(i, end));
        }
        return pages;
    }

    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Dune", "Frank Herbert", "Science Fiction", 4.8));
        books.add(new Book("Foundation", "Isaac Asimov", "Science Fiction", 4.5));
        books.add(new Book("Random", "Author", "Drama", 4.9));
        books.add(new Book("Neuromancer", "William Gibson", "Science Fiction", 4.2));
        books.add(new Book("Hyperion", "Dan Simmons", "Science Fiction", 4.7));
        books.add(new Book("Book6", "A", "Science Fiction", 4.1));
        books.add(new Book("Book7", "A", "Science Fiction", 4.3));
        books.add(new Book("Book8", "A", "Science Fiction", 4.6));
        books.add(new Book("Book9", "A", "Science Fiction", 4.4));
        books.add(new Book("Book10", "A", "Science Fiction", 4.9));
        books.add(new Book("Book11", "A", "Science Fiction", 4.0));

        List<List<BookRecommendation>> pages = getRecommendations(books);

        int pageNumber = 1;
        for (List<BookRecommendation> page : pages) {
            System.out.println("Page " + pageNumber++ + ":");
            page.forEach(System.out::println);
            System.out.println();
        }
    }
}
