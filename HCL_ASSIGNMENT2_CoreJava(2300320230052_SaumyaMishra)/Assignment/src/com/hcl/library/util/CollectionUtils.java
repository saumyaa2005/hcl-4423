package com.hcl.library.util;

import com.hcl.library.model.LibraryItem;
import java.util.Collections;
import java.util.List;

public class CollectionUtils {

    // Bounded generic: <T extends Comparable<T>>
    public static <T extends Comparable<T>> T maxOf(List<T> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("List cannot be empty");
        }
        return Collections.max(list);
    }

    // Bounded wildcard: List<? extends LibraryItem>
    public static double totalFees(List<? extends LibraryItem> items, int daysLate) {
        if (items == null || daysLate <= 0) return 0.0;
        double total = 0.0;
        for (LibraryItem item : items) {
            total += item.lateFee(daysLate);
        }
        return total;
    }
}
