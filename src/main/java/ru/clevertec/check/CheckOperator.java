package ru.clevertec.check;

import java.util.HashMap;
import java.util.List;


import ru.clevertec.check.entity.*;
import ru.clevertec.check.exception.BadRequestException;
import ru.clevertec.check.exception.InternalServerException;
import ru.clevertec.check.exception.NotEnoughMoneyException;
import ru.clevertec.check.mapper.FilePathMapper;
import ru.clevertec.check.mapper.OrderMapper;
import ru.clevertec.check.service.CsvFileService;
import ru.clevertec.check.service.ServiceProvider;
import ru.clevertec.check.validator.Validator;
import ru.clevertec.check.view.ConsolePrinter;


public class CheckOperator {

    private static CheckOperator instance;
    private Stock stock;
    private List<DiscountCard> discountCards;
    private String saveToFile;

    private CheckOperator() throws InternalServerException {
        discountCards = ServiceProvider.getInstance().getCsvFileService().readAllDiscountCards();
    }

    public static CheckOperator getInstance() throws InternalServerException {
        if (instance == null) {
            instance = new CheckOperator();
        }
        return instance;
    }

    public Check create(String[] args) throws BadRequestException, NotEnoughMoneyException, InternalServerException {
        if (!Validator.getInstance().isInputDataValid(args)) {
            throw new BadRequestException();
        }
        saveToFile = FilePathMapper.mapSaveToFile(args);
        stock = ServiceProvider.getInstance().getDbProductService().getStock(args);
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
        csvFileService.saveCheck(check, saveToFile);
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
