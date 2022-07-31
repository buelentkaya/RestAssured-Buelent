package com.cydeo.Day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HrGetRequests {

    //BeforeAll is a annotaion equals to @BeforeClass  in testNG, we use with static method name
    @BeforeAll   //from Junit
    public static void init() {
        //RestAssured.baseURI = "http://44.202.63.224:1000/ords/hr";
        baseURI = "http://44.202.63.224:1000/ords/hr";
    }

    @DisplayName("GET request to /regions")
    @Test
    public void test1() {
        Response response = RestAssured.get("/regions");
        System.out.println(response.statusCode());

    }

    @Test
    public void test2() {

        //Given Accept type application/json
        // When user send GET request to /regions/2
        // Then status code must 200
        // And response Content Type must be application/json
        // And response body should include or contain Americas

        //Response response=RestAssured.given().accept(ContentType.JSON).
        Response response = given().accept(ContentType.JSON).
                when().
                get("/regions/2");

        System.out.println(response.statusCode());//200
        assertEquals(response.statusCode(), 200);
        System.out.println(response.contentType());//application/json
        assertEquals(response.contentType(), "application/json");


        assertTrue(response.body().asString().contains("Americas"));


        System.out.println(response.body().asString());
        //  {"region_id":2,"region_name":"Americas",
        //  "links":[{"rel":"self","href":"http://44.202.63.224:1000/ords/hr/regions/2"},
        //  {"rel":"edit","href":"http://44.202.63.224:1000/ords/hr/regions/2"},
        //  {"rel":"describedby","href":"http://44.202.63.224:1000/ords/hr/metadata-catalog/regions/item"},
        //  {"rel":"collection","href":"http://44.202.63.224:1000/ords/hr/regions/"}]}

        response.prettyPrint();
    }




}
