package courier;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.Courier;

import static io.restassured.RestAssured.given;


public class CourierClient {
    public static final String COURIER_URL = "/api/v1/courier";

    public static final String LOGIN_COURIER_URL = "/api/v1/courier/login";

    @Step(Steps.CREATE_COURIER)
    public Response create(Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post(COURIER_URL);
    }

    @Step(Steps.LOGIN_COURIER)
    public Response login(Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post(LOGIN_COURIER_URL);
    }

    @Step(Steps.DELETE_COURIER)
    public Response delete(Courier courier) {
        int id = given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post(LOGIN_COURIER_URL)
                .then()
                .extract().body().path("id");
        String idAsString = String.valueOf(id);
        return given()
                .header("Content-type", "application/json")
                .and()
                .when()
                .delete("/api/v1/courier/" + idAsString);
    }


}
