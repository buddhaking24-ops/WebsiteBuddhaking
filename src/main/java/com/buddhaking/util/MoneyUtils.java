package com.buddhaking.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class MoneyUtils {

    private static final DecimalFormat FORMAT;

    static {
        DecimalFormatSymbols symbols =
                new DecimalFormatSymbols(Locale.forLanguageTag("vi-VN"));
        symbols.setGroupingSeparator('.');
        FORMAT = new DecimalFormat("#,###", symbols);
    }

    public static String format(BigDecimal amount) {
        return FORMAT.format(amount);
    }
}
