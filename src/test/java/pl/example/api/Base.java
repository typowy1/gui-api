package pl.example.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.specification.RequestSpecification;
import pl.example.api.properties.AppProperties;

import static io.restassured.config.ObjectMapperConfig.objectMapperConfig;

public class Base {

    private static RequestSpecBuilder requestBuilder;


    public static RequestSpecification requestSpec() {
        requestBuilder = new RequestSpecBuilder();
        requestBuilder.setConfig(RestAssuredConfig.config().objectMapperConfig(objectMapperConfig().defaultObjectMapperType(ObjectMapperType.GSON))); //służy do serializacji i deserializacji JSONów
        requestBuilder.setBaseUri(AppProperties.getBaseUri()); //ustawiamy bazowy adres api
        requestBuilder.setContentType(ContentType.JSON); //ustawiamy ContentType
        requestBuilder.addHeader("api_key", AppProperties.getAppKey()); //ustawiamy key
        requestBuilder.addFilter(new AllureRestAssured());//dodanie raportu allurowego

        return requestBuilder.build();
    }

    public static RequestSpecification requestSpecWithLogs() {
        requestBuilder = new RequestSpecBuilder();
        requestBuilder.setConfig(RestAssuredConfig.config().objectMapperConfig(objectMapperConfig().defaultObjectMapperType(ObjectMapperType.GSON))); //służy do serializacji i deserializacji JSONów
        requestBuilder.setBaseUri(AppProperties.getBaseUri()); //ustawiamy bazowy adres api
        requestBuilder.setContentType(ContentType.JSON); //ustawiamy ContentType
        requestBuilder.addHeader("api_key", AppProperties.getAppKey()); //ustawiamy key
        requestBuilder.addFilter(new RequestLoggingFilter()); //logowanie resquestu
        requestBuilder.addFilter(new ResponseLoggingFilter()); //logowanie response
        requestBuilder.addFilter(new AllureRestAssured()); //dodanie raportu allurowego

        return requestBuilder.build();
    }

}
