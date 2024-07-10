package ru.clevertec.check.mapper;

public class MapperConstantRepository {

    public static String FIRST_PARAMETER_REGEX = "\\d.+";
    public static String PRODUCT_PARAMETER_REGEX = "\\d+-\\d+";
    public static String DISCOUNT_CARD_NUMBER_REGEX = "\\d{4}";
    public static String ARGUMENTS_SEPARATOR = " ";
    public static String FIRST_PARAMETER_SEPARATOR = "-";
    public static String PARAMETERS_SEPARATOR = "=";
    public static String DISCOUNT_CARD_PARAM_PREFIX = "discountCard";
    public static String BALANCE_DEBIT_CARD_PARAM_PREFIX = "balanceDebitCard";
    public static String SAVE_TO_FILE_PARAM_PREFIX = "saveToFile";
    public static String PATH_TO_FILE_PARAM_PREFIX = "pathToFile";
    public static String DATASOURCE_URL_PARAM_PREFIX = "datasource.url";
    public static String DATASOURCE_USERNAME_PARAM_PREFIX = "datasource.username";
    public static String DATASOURCE_PASSWORD_PARAM_PREFIX = "datasource.password";
    public static String DATASOURCE_PARAM_PREFIX = "datasource";
    public static String EMPTY_STRING = "";


    private MapperConstantRepository() {
    }
}
