import io.restassured.RestAssured;
import jdk.jfr.Description;
import models.Order;
import order.OrderClient;
import org.junit.Before;
import org.junit.Test;

import static order.OrderGenerator.randomOrder;

public class ListOrdersTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = Locators.BASE_URL;
    }

    @Description("Проверка работы API по получению списка товаров")
    @Test
    public void getOrderList() {
        Order order = randomOrder();
        OrderClient orderClient = new OrderClient();
        orderClient.getOrder(orderClient.getTrack(order));
    }

}
