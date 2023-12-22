import io.restassured.RestAssured;
import io.restassured.response.Response;
import jdk.jfr.Description;
import models.Order;
import order.OrderClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static order.OrderGenerator.randomOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(Parameterized.class)
public class CreationOrderTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = Locators.BASE_URL;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new String[]{"BLACK"}},
                {new String[]{"GREY"}},
                {new String[]{"BLACK", "GREY"}},
                {new String[0]}
        });
    }

    @Parameterized.Parameter
    public String[] colors;

    @Description("Проверка работы API по созданию списка товаров")
    @Test
    public void creationOrderListWithColor() {
        Order order = randomOrder();
        order.setColor(colors);
        OrderClient orderClient = new OrderClient();
        Response response = orderClient.create(order);
        String responseBody = response.asString();
        assertEquals("Неверный статус код", 201, response.statusCode());
        assertTrue(responseBody.contains("track"));
    }

}
