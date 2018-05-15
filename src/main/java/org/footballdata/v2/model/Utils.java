package org.footballdata.v2.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

class Utils {
    static <T> Collection<T> getUnmodifiableOrEmptyCollection(Collection<T> original) {
        if (original == null) {
            return Collections.emptyList();
        } else {
            return Collections.unmodifiableCollection(new ArrayList<>(original));
        }
    }

    static String getValueOrEmptyString(String input) {
        return input == null ? "" : input;
    }
}
