# Assessment 2: Library Management System
**HCL Tech Training | Java Full Stack Development**  
*Content © ABES Engineering College*

---

## 1. Student Details
- **Name:** Saumya Mishra
- **Roll Number:** 2300320230052
- **Batch:** HCL Tech Training / ABES Engineering College
- **Submission Date:** 13 October 2026

---

## 2. How to Compile and Run

Make sure you have JDK 17 or later installed.

### Option A: From Terminal

1. Navigate to the `Assignment` directory:
   ```bash
   cd "HCL_ASSIGNMENT2_CoreJava(2300320230052_SaumyaMishra)/Assignment"
   ```

2. Compile all Java source files into the `bin` directory:
   ```bash
   javac -d bin src/com/hcl/library/*.java \
                src/com/hcl/library/model/*.java \
                src/com/hcl/library/exception/*.java \
                src/com/hcl/library/repository/*.java \
                src/com/hcl/library/service/*.java \
                src/com/hcl/library/util/*.java
   ```

3. Run the application:
   ```bash
   java -cp bin com.hcl.library.Main
   ```

---

## 3. Design Note & Class Relationships

### Architecture Overview
The project follows a layered architecture with clean separation of concerns:
1. **Model Layer (`com.hcl.library.model`)**: Contains domain entities and interfaces.
2. **Exception Layer (`com.hcl.library.exception`)**: Custom exception hierarchy rooted at `LibraryException`.
3. **Repository Layer (`com.hcl.library.repository`)**: Generic repository pattern (`Repository<T, ID>`) backed by in-memory `HashMap`.
4. **Service Layer (`com.hcl.library.service`)**: Handles business logic, waitlist management, reporting, and caching.
5. **Utility Layer (`com.hcl.library.util`)**: Generic utility functions (`CollectionUtils`) and bounded LRU cache (`SearchCache`).
6. **Main Controller (`com.hcl.library.Main`)**: Console UI and interactive menu loop.

### Class Hierarchy and Relationships

```
                            <<interface>>
                             Borrowable
                                 ▲
                                 │ implements
                       ┌─────────┴─────────┐
                       │   LibraryItem     │ (abstract)
                       │   implements      │
                       │ Comparable<Item>  │
                       └─────────┬─────────┘
                                 │ extends
         ┌───────────────────────┼───────────────────────┐
         ▼                       ▼                       ▼
      Book                   Magazine                   DVD
(author, isbn)            (issueNumber)         (durationMinutes)
lateFee: 2.0/day          lateFee: 1.0/day       lateFee: 5.0/day

──────────────────────────────────────────────────────────────────

                       <<interface>>
                     Repository<T, ID>
                             ▲
                             │ implements
                  InMemoryRepository<T, ID>
                   (backed by HashMap<ID, T>)

──────────────────────────────────────────────────────────────────

                    RuntimeException
                           ▲
                           │ extends
                    LibraryException
                           ▲
         ┌─────────────────┼─────────────────┐
         │                 │                 │
ItemNotFoundException  ItemNotAvailable  BorrowLimitExceeded
                            Exception         Exception

──────────────────────────────────────────────────────────────────

                      LinkedHashMap<K, V>
                               ▲
                               │ extends
                          SearchCache (LRU, max 5 entries)
```

- **`Borrowable` (interface):** Declares `isAvailable()`, `borrow()`, `giveBack()`.
- **`LibraryItem` (abstract class):** Implements `Borrowable` and `Comparable<LibraryItem>`. Holds common fields (`id`, `title`, `year`, `available`). Defines abstract method `lateFee(int daysLate)`. Overrides `equals()` and `hashCode()` based on `id`.
- **`Book`, `Magazine`, `DVD` (concrete classes):** Inherit from `LibraryItem` and implement polymorphic `lateFee()`.
- **`Member`:** Manages member identity and borrowed items list (returns unmodifiable views via `Collections.unmodifiableList()`).
- **`SearchCache`:** Extends `LinkedHashMap<String, LibraryItem>` with access-order eviction via `removeEldestEntry()`.

---

## 4. Five Test Cases (Input and Expected Output)

### Test Case 1: Successful Borrow
- **Input:**
  - Menu Choice: `3`
  - Member id: `1`
  - Item id: `1001`
- **Expected Output:**
  ```text
  Issued: Book[1001, Clean Code, 2008, Robert Martin]
  ```

### Test Case 2: Borrowing an Unavailable Item & Joining Waitlist
- **Input:**
  - Menu Choice: `3`
  - Member id: `2`
  - Item id: `1001` (already borrowed by Member 1)
  - Added to waitlist? `y`
- **Expected Output:**
  ```text
  ERROR: Item 1001 is not available. Added to waitlist? (y/n): y
  Member 2 successfully added to waitlist for Item 1001.
  ```

### Test Case 3: Exceeding Borrow Limit (> 3 Items)
- **Input:**
  - Member 1 already has 3 books checked out.
  - Menu Choice: `3`
  - Member id: `1`
  - Item id: `1004`
- **Expected Output:**
  ```text
  ERROR: Borrow limit exceeded - Member 1 has reached the maximum borrowing limit of 3 items.
  ```

### Test Case 4: Invalid Item ID (Item Not Found)
- **Input:**
  - Menu Choice: `3`
  - Member id: `1`
  - Item id: `9999`
- **Expected Output:**
  ```text
  ERROR: Item 9999 not found.
  ```

### Test Case 5: Item Return and Waitlist Notification
- **Input:**
  - Menu Choice: `4`
  - Item id: `1001`
  - Late by (days): `3`
- **Expected Output:**
  ```text
  Notification: Member 2 (Ravi) is next in the waitlist for 1001.
  Late fee: 6.0
  ```

---

## 5. LRU Cache and Memory Leaks (3 to 4 Lines Explanation)

An unbounded static `Map` retains references to every object ever placed inside it for the entire lifetime of the JVM application. Because static variables are rooted in GC roots, the garbage collector cannot reclaim these cached entries even if they are never accessed again. Over time, continuous insertion into an unbounded static map leads to heap exhaustion and an `OutOfMemoryError`. By implementing `SearchCache` with a bounded LRU eviction strategy (`LinkedHashMap` with `removeEldestEntry`), only the most recently searched entries are retained, preventing memory leaks.

---

## 6. Challenges Faced and Key Learnings

1. **Defensive Copying & Immutability:** Ensuring that exposing collections through getters (like `Member.getBorrowedItems()`) does not allow external code to mutate internal state required returning `Collections.unmodifiableList()`.
2. **Bounded Generics & Wildcards:** Implementing `CollectionUtils.totalFees(List<? extends LibraryItem> items, int daysLate)` helped understand Upper Bounded Wildcards (PECS - Producer Extends, Consumer Super), allowing lists of specific subtypes (like `List<Book>`) to be passed seamlessly.
3. **Preventing `ConcurrentModificationException`:** Removing older items based on publication year required using `Iterator.remove()` rather than iterating directly over a collection with a standard `for-each` loop.
4. **Structured Exception Hierarchy:** Grouping specific business rule exceptions under a root `LibraryException` allows granular catch blocks in `Main` while maintaining clean fallback recovery so the user always returns to the main prompt.
