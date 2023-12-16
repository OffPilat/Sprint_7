package order;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import models.Order;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class OrderClient {

    public static final String ORDER_URL = "/api/v1/orders";
    public static final String TRACK_ORDER_URL = "/api/v1/orders/track?t=";

    @Step(Steps.CREATE_ORDER)
    public Response create(Order order) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(order)
                .when()
                .post(ORDER_URL);
    }


    @Step(Steps.GET_TRACK_ORDER)
    public String getTrack(Order order) {
        Integer track = given()
                .header("Content-type", "application/json")
                .and()
                .body(order)
                .when()
                .post(ORDER_URL)
                .then()
                .extract().body().path("track");
        String trackAsString = String.valueOf(track);
        return trackAsString;
    }

    @Step(Steps.TRACK_ORDER)
    public ValidatableResponse getOrder(String track) {
        return given()
                .header("Content-type", "application/json")
                .get(TRACK_ORDER_URL + track)
                .then().assertThat().body("order.firstName", equalTo("Naruto"));
    }
}
