package pl.example.api.requests;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import pl.example.api.properties.AppProperties;

public class BaseRequest {

    private static RequestSpecBuilder requestBuilder;


    public static RequestSpecification requestSpec() {
        requestBuilder = new RequestSpecBuilder();
        requestBuilder.setBaseUri(AppProperties.getBaseUri()); //ustawiamy bazowy adres api
        requestBuilder.setContentType(ContentType.JSON); //ustawiamy ContentType
        requestBuilder.addHeader("api_key", AppProperties.getAppKey()); //ustawiamy key
        requestBuilder.addFilter(new AllureRestAssured());//dodanie raportu allurowego

        return requestBuilder.build();
    }

    public static RequestSpecification requestSpecWithLogs() {
        requestBuilder = new RequestSpecBuilder();
        requestBuilder.setBaseUri(AppProperties.getBaseUri()); //ustawiamy bazowy adres api
        requestBuilder.setContentType(ContentType.JSON); //ustawiamy ContentType
        requestBuilder.addHeader("api_key", AppProperties.getAppKey()); //ustawiamy key
        requestBuilder.addFilter(new RequestLoggingFilter()); //logowanie resquestu
        requestBuilder.addFilter(new ResponseLoggingFilter()); //logowanie response
        requestBuilder.addFilter(new AllureRestAssured()); //dodanie raportu allurowego

        return requestBuilder.build();
    }

}
