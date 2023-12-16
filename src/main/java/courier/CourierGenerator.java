package courier;

import models.Courier;

public class CourierGenerator {

    public static Courier randomCourier() {
        return new Courier()
                .setLogin(CourierCreds.login)
                .setPassword(CourierCreds.password)
                .setFirstName(CourierCreds.firstName);
    }

    public static Courier randomCourierWithoutLogin() {
        return new Courier()
                .setLogin(CourierCreds.emptyLogin)
                .setPassword(CourierCreds.password)
                .setFirstName(CourierCreds.firstName);
    }

    public static Courier randomCourierWithoutPassword() {
        return new Courier()
                .setLogin(CourierCreds.login)
                .setPassword(CourierCreds.emptyPassword)
                .setFirstName(CourierCreds.firstName);
    }

    public static Courier randomCourierWithWrongLogin() {
        return new Courier()
                .setLogin(CourierCreds.wrongLogin)
                .setPassword(CourierCreds.password)
                .setFirstName(CourierCreds.firstName);
    }

    public static Courier randomCourierWithWrongPassword() {
        return new Courier()
                .setLogin(CourierCreds.login)
                .setPassword(CourierCreds.wrongPassword)
                .setFirstName(CourierCreds.firstName);
    }

    public static Courier notExistCourier() {
        return new Courier()
                .setLogin(CourierCreds.wrongLogin)
                .setPassword(CourierCreds.wrongPassword)
                .setFirstName(CourierCreds.wrongFirstName);
    }


}
