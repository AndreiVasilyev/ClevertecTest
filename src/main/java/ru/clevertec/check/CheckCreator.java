package main.java.ru.clevertec.check;

import main.java.ru.clevertec.check.entity.Check;
import main.java.ru.clevertec.check.entity.Order;

public class CheckCreator {
    public Check create (Order order){

        return new Check();
    }
}
