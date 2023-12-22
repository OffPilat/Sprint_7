import courier.CourierClient;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import jdk.jfr.Description;
import models.Courier;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static courier.CourierGenerator.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginCourierTest {


    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = Locators.BASE_URL;
        Courier courier = randomCourier();
        CourierClient courierClient = new CourierClient();
        courierClient.create(courier);
    }

    @Description("Проверка работы API по авторизации курьера")
    @Test
    public void loginCourier() {
        Courier courier = randomCourier();
        CourierClient courierClient = new CourierClient();
        Response response = courierClient.login(courier);
        String responseBody = response.asString();
        assertEquals("Неверный статус код", 200, response.statusCode());
        assertTrue(responseBody.contains("id"));
    }

    @Description("Проверка работы API по авторизации курьера с неверным логином")
    @Test
    public void loginCourierWithWrongLogin() {
        Courier courier = randomCourierWithWrongLogin();
        CourierClient courierClient = new CourierClient();
        Response response = courierClient.login(courier);
        assertEquals("Неверный статус код", 404, response.statusCode());
    }

    @Description("Проверка работы API по авторизации курьера с неверным паролем")
    @Test
    public void loginCourierWithWrongPassword() {
        Courier courier = randomCourierWithWrongPassword();
        CourierClient courierClient = new CourierClient();
        Response response = courierClient.login(courier);
        assertEquals("Неверный статус код", 400, response.statusCode());
    }

    @Description("Проверка работы API по авторизации курьера без логина")
    @Test
    public void loginCourierWithoutLogin() {
        Courier courier = randomCourierWithoutLogin();
        CourierClient courierClient = new CourierClient();
        Response response = courierClient.login(courier);
        assertEquals("Неверный статус код", 400, response.statusCode());
    }

    @Description("Проверка работы API по авторизации курьера без пароля")
    @Test
    public void loginCourierWithWithoutPassword() {
        Courier courier = randomCourierWithoutPassword();
        CourierClient courierClient = new CourierClient();
        Response response = courierClient.login(courier);
        assertEquals("Неверный статус код", 400, response.statusCode());
    }

    @Description("Проверка работы API по авторизации несуществующего курьера")
    @Test
    public void loginNotExistCourier() {
        Courier courier = notExistCourier();
        CourierClient courierClient = new CourierClient();
        Response response = courierClient.login(courier);
        assertEquals("Неверный статус код", 404, response.statusCode());
    }


    @AfterClass
    public static void deleteCourier() {
        Courier courier = randomCourier();
        CourierClient courierClient = new CourierClient();
        courierClient.delete(courier);
    }
}
