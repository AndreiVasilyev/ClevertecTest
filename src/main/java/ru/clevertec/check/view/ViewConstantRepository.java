package ru.clevertec.check.view;

public class ViewConstantRepository {

    public static String DATE_TIME_PATTERN = "dd.MM.yyyy;HH:mm:ss";
    public static String DATE_TIME_TEMPLATE = "Date;Time\n%s\n\n";
    public static String DISCOUNT_CARD_TEMPLATE = "DISCOUNT CARD;DISCOUNT PERCENTAGE\n%d;%d%%\n\n";
    public static String PRODUCTS_TITLE = "QTY;DESCRIPTION;PRICE;DISCOUNT;TOTAL\n";
    public static String PRODUCTS_TEMPLATE = "%d;%s;%.2f$;%.2f$;%.2f$\n";
    public static String NEW_LINE = "\n";
    public static String TOTAL_VALUES_TEMPLATE = "TOTAL PRICE;TOTAL DISCOUNT;TOTAL WITH DISCOUNT\n%.2f$;%.2f$;%.2f$";
    public static String ERROR_TEMPLATE = "ERROR\n%s";

    private ViewConstantRepository() {
    }
}
