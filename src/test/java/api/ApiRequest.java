package api;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;

public class ApiRequest extends ApiUtils implements ApiVerbos{


    @Override
    public void GET() {
        response = given().
                relaxedHTTPSValidation().
                headers(headers).
                get(uri);
        super.log("GET");
    }

    @Override
    public void POST() {
        response = given().log().all().
                relaxedHTTPSValidation().
                headers(headers).
                body(body.toString()).
                post(uri);
        super.log("POST");
    }

    @Override
    public void PUT() {
        response = given().
                relaxedHTTPSValidation().
                headers(headers).
                body(body.toString()).
                put(uri);
        super.log("PUT");
    }

    @Override
    public void DELETE() {
        response = given().
                relaxedHTTPSValidation().
                headers(headers).
                delete(uri);
        super.log("DELETE");
    }
}
