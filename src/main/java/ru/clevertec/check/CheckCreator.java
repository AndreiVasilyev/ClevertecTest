package main.java.ru.clevertec.check;

import main.java.ru.clevertec.check.entity.*;
import main.java.ru.clevertec.check.exception.BadRequestException;
import main.java.ru.clevertec.check.exception.NotEnoughMoneyException;
import main.java.ru.clevertec.check.mapper.OrderMapper;
import main.java.ru.clevertec.check.service.CsvReaderService;
import main.java.ru.clevertec.check.service.ServiceProvider;

import java.util.HashMap;
import java.util.List;

public class CheckCreator {

    private Stock stock;
    private List<DiscountCard> discountCards;

    public CheckCreator() {
        CsvReaderService csvReaderService = ServiceProvider.getInstance().getCsvReaderService();
        stock = csvReaderService.readStock();
        discountCards = csvReaderService.readAllDiscountCards();
    }

    public Check create(String[] args) throws BadRequestException, NotEnoughMoneyException {
        Order order = new OrderMapper().map(args);
        Check check = new Check();
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
        if (order.getDiscountCardNumber() != 0) {
            DiscountCard discountCard = discountCards.stream()
                    .filter(card -> card.getNumber() == order.getDiscountCardNumber())
                    .peek(System.out::println)
                    .findFirst()
                    .orElseThrow(BadRequestException::new);
            check.setDiscountCard(discountCard);
        }
        if ((check.calculateTotalPrice() - check.calculateTotalDiscount()) > order.getBalanceDebitCard()) {
            throw new NotEnoughMoneyException();
        }
        return check;
    }
}
