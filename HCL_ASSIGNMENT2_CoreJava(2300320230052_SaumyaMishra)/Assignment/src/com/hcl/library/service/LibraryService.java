package com.hcl.library.service;

import com.hcl.library.exception.*;
import com.hcl.library.model.*;
import com.hcl.library.repository.InMemoryRepository;
import com.hcl.library.util.SearchCache;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class LibraryService {
    private final InMemoryRepository<LibraryItem, Integer> itemRepo = new InMemoryRepository<>(LibraryItem::getId);
    private final InMemoryRepository<Member, Integer> memberRepo = new InMemoryRepository<>(Member::getId);
    private final Set<String> isbnSet = new HashSet<>();
    private final Map<Integer, Queue<Member>> waitlist = new HashMap<>();
    private final SearchCache searchCache = new SearchCache();

    // 1. Add Item with duplicate ISBN check
    public void addItem(LibraryItem item) {
        if (item instanceof Book) {
            Book book = (Book) item;
            if (isbnSet.contains(book.getIsbn())) {
                throw new LibraryException("Book with ISBN " + book.getIsbn() + " already exists.");
            }
            isbnSet.add(book.getIsbn());
        }
        itemRepo.save(item);
    }

    // 2. Register Member
    public void registerMember(Member member) {
        memberRepo.save(member);
    }

    // 3. Borrow Item with business exceptions
    public LibraryItem borrowItem(int memberId, int itemId) {
        Member member = memberRepo.findById(memberId)
                .orElseThrow(() -> new LibraryException("Member ID " + memberId + " not found."));

        LibraryItem item = itemRepo.findById(itemId)
                .orElseThrow(() -> new ItemNotFoundException("Item " + itemId + " not found."));

        if (!item.isAvailable()) {
            throw new ItemNotAvailableException("Item " + itemId + " is not available.");
        }
        if (member.getBorrowedCount() >= 3) {
            throw new BorrowLimitExceededException("Borrow limit exceeded (Maximum 3 items).");
        }

        item.borrow();
        member.addBorrowedItem(item);
        return item;
    }

    // 4. Return Item, calculate fee, and check waitlist
    public double returnItem(int itemId, int daysLate) {
        LibraryItem item = itemRepo.findById(itemId)
                .orElseThrow(() -> new ItemNotFoundException("Item " + itemId + " not found."));

        if (item.isAvailable()) {
            throw new LibraryException("Item " + itemId + " is not currently borrowed.");
        }

        for (Member m : memberRepo.findAll()) {
            if (m.getBorrowedItems().contains(item)) {
                m.removeBorrowedItem(item);
                break;
            }
        }
        item.giveBack();

        double fee = item.lateFee(daysLate);
        System.out.println("Late fee: " + fee);

        // Check waitlist
        Queue<Member> queue = waitlist.get(itemId);
        if (queue != null && !queue.isEmpty()) {
            Member next = queue.poll();
            System.out.println("Notification: Member " + next.getId() + " (" + next.getName() + ") is next in the waitlist for " + itemId + ".");
        }

        return fee;
    }

    // 5. Join Waitlist
    public void addToWaitlist(int itemId, int memberId) {
        itemRepo.findById(itemId)
                .orElseThrow(() -> new ItemNotFoundException("Item " + itemId + " not found."));
        Member member = memberRepo.findById(memberId)
                .orElseThrow(() -> new LibraryException("Member " + memberId + " not found."));

        waitlist.computeIfAbsent(itemId, k -> new ArrayDeque<>()).offer(member);
    }

    // 6. Search with LRU Cache
    public Optional<LibraryItem> searchByTitle(String title) {
        String key = title.toLowerCase().trim();
        if (searchCache.containsKey(key)) {
            return Optional.of(searchCache.get(key));
        }
        for (LibraryItem item : itemRepo.findAll()) {
            if (item.getTitle().equalsIgnoreCase(title.trim())) {
                searchCache.put(key, item);
                return Optional.of(item);
            }
        }
        return Optional.empty();
    }

    // 7. Sorting
    public List<LibraryItem> getItemsSortedByTitle() {
        List<LibraryItem> list = itemRepo.findAll();
        Collections.sort(list);
        return Collections.unmodifiableList(list);
    }

    public List<LibraryItem> getItemsSortedByYearDescThenTitle() {
        List<LibraryItem> list = itemRepo.findAll();
        list.sort(Comparator.comparing(LibraryItem::getYear, Comparator.reverseOrder())
                .thenComparing(LibraryItem::getTitle, String.CASE_INSENSITIVE_ORDER));
        return Collections.unmodifiableList(list);
    }

    // 8. Remove old items using Iterator.remove()
    public int removeOldItems(int beforeYear) {
        int count = 0;
        Map<Integer, LibraryItem> map = itemRepo.getStorageMap();
        Iterator<Map.Entry<Integer, LibraryItem>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            LibraryItem item = it.next().getValue();
            if (item.getYear() < beforeYear) {
                if (item instanceof Book) {
                    isbnSet.remove(((Book) item).getIsbn());
                }
                it.remove();
                count++;
            }
        }
        return count;
    }

    // 9. Statistics using Map.merge
    public Map<String, Integer> getItemTypeStatistics() {
        Map<String, Integer> stats = new HashMap<>();
        for (LibraryItem item : itemRepo.findAll()) {
            stats.merge(item.getClass().getSimpleName(), 1, Integer::sum);
        }
        return Collections.unmodifiableMap(stats);
    }

    // 10. Export report with try-with-resources
    public void exportReport(String path) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            bw.write("=== LIBRARY REPORT ===\n");
            for (LibraryItem item : itemRepo.findAll()) {
                bw.write(item.getId() + " | " + item.getTitle() + " | " + (item.isAvailable() ? "AVAILABLE" : "BORROWED") + "\n");
            }
        } catch (IOException e) {
            throw new LibraryException("Export failed", e);
        }
    }

    public List<LibraryItem> getAllItems() { return itemRepo.findAll(); }
    public Map<Integer, Queue<Member>> getWaitlistMap() { return Collections.unmodifiableMap(waitlist); }
}
