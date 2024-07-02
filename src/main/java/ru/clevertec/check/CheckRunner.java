package main.java.ru.clevertec.check;

import main.java.ru.clevertec.check.entity.DiscountCard;
import main.java.ru.clevertec.check.entity.Order;
import main.java.ru.clevertec.check.entity.Stock;
import main.java.ru.clevertec.check.mapper.OrderMapper;
import main.java.ru.clevertec.check.service.CsvReaderService;
import main.java.ru.clevertec.check.service.ServiceProvider;

import java.util.List;

public class CheckRunner {
    public static void main(String[] args) {
        CsvReaderService csvReaderService = ServiceProvider.getInstance().getCsvReaderService();
        Stock stock = csvReaderService.readStock();
        System.out.println(stock.getProducts());
        List<DiscountCard> discountCards = csvReaderService.readAllDiscountCards();
        discountCards.forEach(System.out::println);
        Order order=new OrderMapper().map(args);
        System.out.println(order);
    }
}
