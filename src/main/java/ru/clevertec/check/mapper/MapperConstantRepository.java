package main.java.ru.clevertec.check.mapper;

public class MapperConstantRepository {

    public static String FIRST_PARAMETER_REGEX = "\\d.+";
    public static String PRODUCT_PARAMETER_REGEX = "\\d+-\\d+";
    public static String DISCOUNT_CARD_NUMBER_REGEX = "\\d{4}";
    public static String ARGUMENTS_SEPARATOR = " ";
    public static String FIRST_PARAMETER_SEPARATOR = "-";
    public static String PARAMETERS_SEPARATOR = "=";
    public static String DISCOUNT_CARD_PARAM_PREFIX = "discountCard";
    public static String BALANCE_DEBIT_CARD_PARAM_PREFIX = "balanceDebitCard";

    private MapperConstantRepository() {
    }
}
