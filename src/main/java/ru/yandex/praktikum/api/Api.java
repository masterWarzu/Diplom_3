package ru.yandex.praktikum.api;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class Api
{
    public static final String BASE_PATH_USER_REGISTER = "/api/auth/register";
    public static final String BASE_PATH_USER_LOGIN = "/api/auth/login";
    public static final String BASE_PATH_USER_DELETE = "/api/auth/user";

    public static Response createUser(Object json)
    {
        return (Response) given()
                .header("Content-type", "application/json")
                .and()
                .body(json)
                .when()
                .post(BASE_PATH_USER_REGISTER);
    }

    public static Response getUserLoginByEmailAndPasswordResponse(Object json)
    {
        return (Response) given()
                .header("Content-type", "application/json")
                .and()
                .body(json)
                .when()
                .post(BASE_PATH_USER_LOGIN);
    }

    public static void deleteUserByLoginAndPassword(Object json)
    {
        String accessToken = getUserLoginByEmailAndPasswordResponse(json)
                .then().extract().body().path("accessToken");

        if (accessToken != null)
        {
            given()
                    .header("Authorization", accessToken)
                    .delete(BASE_PATH_USER_DELETE);
        }
    }
}
