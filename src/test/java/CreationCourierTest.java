import courier.CourierClient;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import jdk.jfr.Description;
import models.Courier;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import static courier.CourierGenerator.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CreationCourierTest {


    @Before
    public void setUp() {
        RestAssured.baseURI = Locators.BASE_URL;
    }

    @Description("Проверка работы API по созданию курьера")
    @Test
    public void creationCourier() {
        Courier courier = randomCourier();
        CourierClient courierClient = new CourierClient();
        Response response = courierClient.create(courier);
        String responseBody = response.asString();
        assertEquals("Неверный статус код", 201, response.statusCode());
        assertTrue(responseBody.contains("ok\":true"));
    }

    @Description("Проверка работы API по созданию дубликата курьера")
    @Test
    public void creationCopyCourier() {
        Courier courier = randomCourier();
        CourierClient courierClient = new CourierClient();
        courierClient.create(courier);
        Response failResponse = courierClient.create(courier);
        assertEquals("Неверный статус код", 409, failResponse.statusCode());

    }

    @Description("Проверка работы API по созданию курьера с одинаковым логином")
    @Test
    public void creationCourierWithCopyLogin() {
        Courier courier = randomCourier();
        CourierClient courierClient = new CourierClient();
        courierClient.create(courier);
        Courier copyCourier = randomCourierWithWrongPassword();
        Response failResponse = courierClient.create(copyCourier);
        assertEquals("Неверный статус код", 409, failResponse.statusCode());

    }

    @Description("Проверка работы API по созданию курьера без логина")
    @Test
    public void creationCourierWithoutLogin() {
        Courier courier = randomCourierWithoutLogin();
        CourierClient courierClient = new CourierClient();
        Response response = courierClient.create(courier);
        assertEquals("Неверный статус код", 400, response.statusCode());

    }

    @Description("Проверка работы API по созданию курьера без пароля")
    @Test
    public void creationCourierWithoutPassword() {
        Courier courier = randomCourierWithoutPassword();
        CourierClient courierClient = new CourierClient();
        Response response = courierClient.create(courier);
        assertEquals("Неверный статус код", 400, response.statusCode());

    }

    @Description("Проверка работы API по удалению курьера")
    @AfterClass
    public static void deleteCourier() {
        Courier courier = randomCourier();
        CourierClient courierClient = new CourierClient();
        courierClient.delete(courier);
    }
}
