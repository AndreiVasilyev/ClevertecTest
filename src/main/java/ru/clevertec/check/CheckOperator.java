package main.java.ru.clevertec.check;

import main.java.ru.clevertec.check.entity.*;
import main.java.ru.clevertec.check.exception.BadRequestException;
import main.java.ru.clevertec.check.exception.InternalServerException;
import main.java.ru.clevertec.check.exception.NotEnoughMoneyException;
import main.java.ru.clevertec.check.mapper.OrderMapper;
import main.java.ru.clevertec.check.service.CsvFileService;
import main.java.ru.clevertec.check.service.ServiceProvider;
import main.java.ru.clevertec.check.validator.Validator;
import main.java.ru.clevertec.check.view.ConsolePrinter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CheckOperator {

    private static CheckOperator instance;
    private Stock stock;
    private List<DiscountCard> discountCards;

    private CheckOperator() throws InternalServerException {
        CsvFileService csvFileService = ServiceProvider.getInstance().getCsvFileService();
        stock = csvFileService.readStock();
        discountCards = csvFileService.readAllDiscountCards();
    }

    public static CheckOperator getInstance() throws InternalServerException {
        if (instance == null) {
            instance = new CheckOperator();
        }
        return instance;
    }

    public Check create(String[] args) throws BadRequestException, NotEnoughMoneyException {
        Order order = new OrderMapper().map(args);
        Check check = new Check();
        addProductsToCheck(check, order);
        addDiscountCardToCheck(check, order);
        if (Validator.getInstance().isEnoughBalance(check, order)) {
            throw new NotEnoughMoneyException();
        }
        return check;
    }

    public void save(Check check) throws InternalServerException {
        CsvFileService csvFileService = ServiceProvider.getInstance().getCsvFileService();
        csvFileService.saveCheck(check);
    }

    public void printToConsole(Check check) {
        ConsolePrinter consolePrinter = ConsolePrinter.getInstance();
        consolePrinter.printCheckToConsole(check);
    }

    private void addProductsToCheck(Check check, Order order) throws BadRequestException {
        HashMap<Long, Integer> products = order.getProducts();
        for (long id : products.keySet()) {
            Product product = stock.getProducts()
                    .keySet()
                    .stream()
                    .filter(prod -> prod.getId() == id)
                    .findFirst()
                    .orElseThrow(BadRequestException::new);
            if (stock.getProducts().get(product) < products.get(id)) {
                throw new BadRequestException();
            }
            check.getProducts().put(product, products.get(id));
        }
    }

    private void addDiscountCardToCheck(Check check, Order order) throws BadRequestException {
        if (order.getDiscountCardNumber() != 0) {
            DiscountCard discountCard = discountCards.stream()
                    .filter(card -> card.getNumber() == order.getDiscountCardNumber())
                    .findFirst()
                    .orElseThrow(BadRequestException::new);
            check.setDiscountCard(discountCard);
        }
    }
}
