package ru.clevertec.check.dao;

public class DaoConstantRepository {
    public static final String CSV_VALUE_SEPARATOR = ";";
    public static final String POSTGRES_DRIVER_CLASSNAME = "org.postgresql.Driver";
    public static final String SQL_SELECT_ALL_PRODUCTS = "SELECT p.id, p.description, p.price, p.quantity_in_stock, " +
            "p.wholesale_product FROM product AS p";
    public static final String PRODUCT_ID = "id";
    public static final String PRODUCT_DESCRIPTION = "description";
    public static final String PRODUCT_PRICE = "price";
    public static final String PRODUCT_QUANTITY_IN_STOCK = "quantity_in_stock";
    public static final String PRODUCT_WHOLESALE_PRODUCT = "wholesale_product";

    private DaoConstantRepository() {
    }
}
