package main.java.ru.clevertec.check;

import main.java.ru.clevertec.check.entity.Check;
import main.java.ru.clevertec.check.entity.DiscountCard;
import main.java.ru.clevertec.check.entity.Stock;
import main.java.ru.clevertec.check.exception.BadRequestException;
import main.java.ru.clevertec.check.exception.NotEnoughMoneyException;
import main.java.ru.clevertec.check.service.CsvReaderService;
import main.java.ru.clevertec.check.service.ServiceProvider;

import java.util.List;

public class CheckRunner {
    public static void main(String[] args) {
        CsvReaderService csvReaderService = ServiceProvider.getInstance().getCsvReaderService();
        Stock stock = csvReaderService.readStock();
        List<DiscountCard> discountCards = csvReaderService.readAllDiscountCards();
        discountCards.forEach(System.out::println);
        try {
            Check check = new CheckCreator().create(args);

            // TODO print result
        } catch (BadRequestException | NotEnoughMoneyException e) {
            //TODO print error result

        }

    }
}
