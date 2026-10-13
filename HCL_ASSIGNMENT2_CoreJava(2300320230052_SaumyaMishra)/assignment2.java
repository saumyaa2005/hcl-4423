import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Year;
import java.util.*;
import java.util.function.Function;

// ==========================================
// 1. MODEL LAYER (Interfaces & Classes)
// ==========================================

// Item issue aur return karne ka interface
interface Borrowable {
    boolean isAvailable();
    void borrow();
    void giveBack();
}

// Base abstract class
abstract class LibraryItem implements Borrowable, Comparable<LibraryItem> {
    private static int idCounter = 1001; // ID 1001 se start hogi
    private final int id;
    private String title;
    private int year;
    private boolean available;

    public LibraryItem(String title, int year) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title blank nahi ho sakta.");
        }
        int currentYear = Year.now().getValue();
        if (year < 1900 || year > currentYear) {
            throw new IllegalArgumentException("Year 1900 se " + currentYear + " ke beech hona chahiye.");
        }
        this.id = idCounter++;
        this.title = title.trim();
        this.year = year;
        this.available = true;
    }

    public abstract double lateFee(int daysLate);

    @Override
    public boolean isAvailable() { return available; }

    @Override
    public void borrow() { this.available = false; }

    @Override
    public void giveBack() { this.available = true; }

    @Override
    public int compareTo(LibraryItem other) {
        return this.title.compareToIgnoreCase(other.title);
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public int getYear() { return year; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LibraryItem that = (LibraryItem) o;
        return id == that.id;
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() {
        return id + ", " + title + ", " + year;
    }
}

// Book subclass (Late fee: 2.0 per day)
class Book extends LibraryItem {
    private String author;
    private String isbn;

    public Book(String title, int year, String author, String isbn) {
        super(title, year);
        this.author = author;
        this.isbn = isbn;
    }

    @Override
    public double lateFee(int daysLate) {
        return daysLate <= 0 ? 0.0 : 2.0 * daysLate;
    }

    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }

    @Override
    public String toString() {
        return "Book[" + super.toString() + ", " + author + "]";
    }
}

// Magazine subclass (Late fee: 1.0 per day)
class Magazine extends LibraryItem {
    private int issueNumber;

    public Magazine(String title, int year, int issueNumber) {
        super(title, year);
        this.issueNumber = issueNumber;
    }

    @Override
    public double lateFee(int daysLate) {
        return daysLate <= 0 ? 0.0 : 1.0 * daysLate;
    }

    @Override
    public String toString() {
        return "Magazine[" + super.toString() + ", Issue #" + issueNumber + "]";
    }
}

// DVD subclass (Late fee: 5.0 per day)
class DVD extends LibraryItem {
    private int durationMinutes;

    public DVD(String title, int year, int durationMinutes) {
        super(title, year);
        this.durationMinutes = durationMinutes;
    }

    @Override
    public double lateFee(int daysLate) {
        return daysLate <= 0 ? 0.0 : 5.0 * daysLate;
    }

    @Override
    public String toString() {
        return "DVD[" + super.toString() + ", " + durationMinutes + " mins]";
    }
}

// Member class
class Member {
    private static int memberIdCounter = 1;
    private final int id;
    private String name;
    private String email;
    private final List<LibraryItem> borrowedItems = new ArrayList<>();

    public Member(String name, String email) {
        this(memberIdCounter++, name, email);
    }

    public Member(int id, String name, String email) {
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Email me '@' hona zaroori hai.");
        }
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public List<LibraryItem> getBorrowedItems() {
        return Collections.unmodifiableList(borrowedItems);
    }

    public void addBorrowedItem(LibraryItem item) { borrowedItems.add(item); }
    public void removeBorrowedItem(LibraryItem item) { borrowedItems.remove(item); }
    public int getBorrowedCount() { return borrowedItems.size(); }

    @Override
    public String toString() {
        return "Member[" + id + ", " + name + ", " + email + "]";
    }
}

// ==========================================
// 2. EXCEPTION LAYER (Custom Exceptions)
// ==========================================

class LibraryException extends RuntimeException {
    public LibraryException(String message) { super(message); }
    public LibraryException(String message, Throwable cause) { super(message, cause); }
}

class ItemNotFoundException extends LibraryException {
    public ItemNotFoundException(String message) { super(message); }
}

class ItemNotAvailableException extends LibraryException {
    public ItemNotAvailableException(String message) { super(message); }
}

class BorrowLimitExceededException extends LibraryException {
    public BorrowLimitExceededException(String message) { super(message); }
}

// ==========================================
// 3. REPOSITORY LAYER (Generics)
// ==========================================

interface Repository<T, ID> {
    void save(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();
}

class InMemoryRepository<T, ID> implements Repository<T, ID> {
    private final Map<ID, T> storage = new HashMap<>();
    private final Function<T, ID> idExtractor;

    public InMemoryRepository(Function<T, ID> idExtractor) {
        this.idExtractor = idExtractor;
    }

    @Override
    public void save(T entity) {
        storage.put(idExtractor.apply(entity), entity);
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(storage.values());
    }
}

// ==========================================
// 4. UTILITY & CACHE LAYER
// ==========================================

class CollectionUtils {
    // Bounded Generic: Max element dhundna
    public static <T extends Comparable<T>> T maxOf(List<T> list) {
        if (list == null || list.isEmpty()) return null;
        return Collections.max(list);
    }

    // Bounded Wildcard: Total late fee calculate karna
    public static double totalFees(List<? extends LibraryItem> items, int daysLate) {
        double total = 0.0;
        for (LibraryItem item : items) {
            total += item.lateFee(daysLate);
        }
        return total;
    }
}

// Last 5 searches ka LRU cache
class SearchCache extends LinkedHashMap<String, LibraryItem> {
    public SearchCache() {
        super(5, 0.75f, true);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<String, LibraryItem> eldest) {
        return size() > 5;
    }
}

// ==========================================
// 5. SERVICE LAYER (Business Logic)
// ==========================================

class LibraryService {
    private final InMemoryRepository<LibraryItem, Integer> items = new InMemoryRepository<>(LibraryItem::getId);
    private final InMemoryRepository<Member, Integer> members = new InMemoryRepository<>(Member::getId);
    private final Set<String> isbns = new HashSet<>();
    private final Map<Integer, Queue<Member>> waitlist = new HashMap<>();
    private final SearchCache cache = new SearchCache();

    public void addItem(LibraryItem item) {
        if (item instanceof Book) {
            Book b = (Book) item;
            if (isbns.contains(b.getIsbn())) {
                throw new LibraryException("Duplicate ISBN: " + b.getIsbn());
            }
            isbns.add(b.getIsbn());
        }
        items.save(item);
    }

    public void registerMember(Member m) {
        members.save(m);
    }

    public LibraryItem borrowItem(int memberId, int itemId) {
        Member member = members.findById(memberId)
                .orElseThrow(() -> new LibraryException("Member ID nahi mila: " + memberId));
        LibraryItem item = items.findById(itemId)
                .orElseThrow(() -> new ItemNotFoundException("Item " + itemId + " not found."));

        if (!item.isAvailable()) {
            throw new ItemNotAvailableException("Item " + itemId + " is not available.");
        }
        if (member.getBorrowedCount() >= 3) {
            throw new BorrowLimitExceededException("Limit exceeded (Maximum 3 items).");
        }

        item.borrow();
        member.addBorrowedItem(item);
        return item;
    }

    public void returnItem(int itemId, int daysLate) {
        LibraryItem item = items.findById(itemId)
                .orElseThrow(() -> new ItemNotFoundException("Item " + itemId + " not found."));

        if (item.isAvailable()) {
            throw new LibraryException("Item borrow nahi hua hai.");
        }

        for (Member m : members.findAll()) {
            if (m.getBorrowedItems().contains(item)) {
                m.removeBorrowedItem(item);
                break;
            }
        }
        item.giveBack();

        double fee = item.lateFee(daysLate);
        System.out.println("Late fee: " + fee);

        // Waitlist check karna
        Queue<Member> q = waitlist.get(itemId);
        if (q != null && !q.isEmpty()) {
            Member next = q.poll();
            System.out.println("Notification: Member " + next.getId() + " (" + next.getName() + ") is next in the waitlist for " + itemId + ".");
        }
    }

    public void addToWaitlist(int itemId, int memberId) {
        Member m = members.findById(memberId)
                .orElseThrow(() -> new LibraryException("Member nahi mila."));
        waitlist.computeIfAbsent(itemId, k -> new ArrayDeque<>()).offer(m);
    }

    public void search(String title) {
        String key = title.toLowerCase().trim();
        if (cache.containsKey(key)) {
            System.out.println("[Cache] Found: " + cache.get(key));
            return;
        }
        for (LibraryItem item : items.findAll()) {
            if (item.getTitle().equalsIgnoreCase(title.trim())) {
                cache.put(key, item);
                System.out.println("Found: " + item);
                return;
            }
        }
        System.out.println("Item nahi mila: " + title);
    }

    public void listItems(int sortType) {
        List<LibraryItem> list = items.findAll();
        if (sortType == 2) {
            list.sort(Comparator.comparing(LibraryItem::getYear, Comparator.reverseOrder())
                    .thenComparing(LibraryItem::getTitle, String.CASE_INSENSITIVE_ORDER));
        } else {
            Collections.sort(list); // Natural sort (Title)
        }
        for (LibraryItem item : list) {
            System.out.println(" - " + item + " [" + (item.isAvailable() ? "Available" : "Borrowed") + "]");
        }

        // Statistics using Map.merge
        Map<String, Integer> stats = new HashMap<>();
        for (LibraryItem item : list) {
            stats.merge(item.getClass().getSimpleName(), 1, Integer::sum);
        }
        System.out.println("Stats: " + stats);
    }

    public void exportReport(String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            bw.write("=== LIBRARY REPORT ===\n");
            for (LibraryItem item : items.findAll()) {
                bw.write(item.getId() + " | " + item.getTitle() + " | " + (item.isAvailable() ? "AVAILABLE" : "BORROWED") + "\n");
            }
            System.out.println("Report export ho gaya: " + fileName);
        } catch (IOException e) {
            throw new LibraryException("Export failed", e);
        }
    }
}

// ==========================================
// 6. MAIN CLASS (Console UI Menu)
// ==========================================

public class assignment2 {
    public static void main(String[] args) {
        LibraryService service = new LibraryService();
        Scanner sc = new Scanner(System.in);

        // Sample Data Seed
        service.addItem(new Book("Clean Code", 2008, "Robert Martin", "ISBN-101"));
        service.addItem(new Book("Effective Java", 2018, "Joshua Bloch", "ISBN-102"));
        service.addItem(new Magazine("Java Magazine", 2023, 42));
        service.addItem(new DVD("Inception", 2010, 148));

        service.registerMember(new Member(1, "Aman", "aman@gmail.com"));
        service.registerMember(new Member(2, "Ravi", "ravi@gmail.com"));

        while (true) {
            System.out.println("\n===== LIBRARY MENU =====");
            System.out.println("1. Add item     2. Register member     3. Borrow     4. Return");
            System.out.println("5. Waitlist     6. List items          7. Search     8. Export report     0. Exit");
            System.out.print("Choice: ");

            String input = sc.nextLine().trim();
            if (input.isEmpty()) continue;

            try {
                int choice = Integer.parseInt(input);
                if (choice == 0) {
                    System.out.println("Exiting... Bye!");
                    break;
                }

                switch (choice) {
                    case 1:
                        System.out.print("Title: ");
                        String title = sc.nextLine();
                        System.out.print("Year: ");
                        int year = Integer.parseInt(sc.nextLine());
                        System.out.print("Author: ");
                        String author = sc.nextLine();
                        System.out.print("ISBN: ");
                        String isbn = sc.nextLine();
                        service.addItem(new Book(title, year, author, isbn));
                        System.out.println("Book add ho gayi!");
                        break;

                    case 2:
                        System.out.print("Name: ");
                        String name = sc.nextLine();
                        System.out.print("Email: ");
                        String email = sc.nextLine();
                        service.registerMember(new Member(name, email));
                        System.out.println("Member register ho gaya!");
                        break;

                    case 3:
                        System.out.print("Member id: ");
                        int mId = Integer.parseInt(sc.nextLine());
                        System.out.print("Item id: ");
                        int iId = Integer.parseInt(sc.nextLine());
                        LibraryItem item = service.borrowItem(mId, iId);
                        System.out.println("Issued: " + item);
                        break;

                    case 4:
                        System.out.print("Item id: ");
                        int retItemId = Integer.parseInt(sc.nextLine());
                        System.out.print("Late by (days): ");
                        int days = Integer.parseInt(sc.nextLine());
                        service.returnItem(retItemId, days);
                        break;

                    case 6:
                        System.out.print("Sort by: 1. Title (A-Z)  2. Year (Newest first): ");
                        int sort = Integer.parseInt(sc.nextLine());
                        service.listItems(sort);
                        break;

                    case 7:
                        System.out.print("Title to search: ");
                        service.search(sc.nextLine());
                        break;

                    case 8:
                        service.exportReport("report.txt");
                        break;

                    default:
                        System.out.println("Invalid choice!");
                }
            } catch (ItemNotAvailableException e) {
                System.out.print("ERROR: " + e.getMessage() + " Added to waitlist? (y/n): ");
                String ans = sc.nextLine().trim();
                if (ans.equalsIgnoreCase("y")) {
                    System.out.print("Enter Item ID: ");
                    int itemId = Integer.parseInt(sc.nextLine());
                    System.out.print("Enter Member ID: ");
                    int memberId = Integer.parseInt(sc.nextLine());
                    service.addToWaitlist(itemId, memberId);
                    System.out.println("Waitlist me add ho gaye!");
                }
            } catch (ItemNotFoundException e) {
                System.out.println("ERROR: " + e.getMessage());
            } catch (BorrowLimitExceededException e) {
                System.out.println("ERROR: " + e.getMessage());
            } catch (LibraryException e) {
                System.out.println("Library Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
