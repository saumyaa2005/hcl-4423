package com.hcl.library.util;

import com.hcl.library.model.LibraryItem;
import java.util.LinkedHashMap;
import java.util.Map;

// LRU cache keeping last 5 searched titles
public class SearchCache extends LinkedHashMap<String, LibraryItem> {
    private static final long serialVersionUID = 1L;
    public SearchCache() {
        super(5, 0.75f, true);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<String, LibraryItem> eldest) {
        return size() > 5;
    }
}
