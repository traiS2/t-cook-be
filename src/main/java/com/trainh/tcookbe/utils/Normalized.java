package com.trainh.tcookbe.utils;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class Normalized {
    private Normalized() {
        // Hàm khởi tạo private để ngăn việc tạo instance của lớp
    }

    public static String normalize(String title) {
        String normalized = Normalizer.normalize(title, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalized).replaceAll("").toLowerCase().replace(" ", "-");
    }
}
