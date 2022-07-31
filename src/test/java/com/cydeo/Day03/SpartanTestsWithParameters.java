package com.cydeo.Day03;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class SpartanTestsWithParameters {

    //BeforeAll is a annotaion equals to @BeforeClass  in testNG, we use with static method name
    @BeforeAll   //from Junit
    public static void init() {
        //RestAssured.baseURI = "http://44.202.63.224:1000/ords/hr";
        baseURI = "http://44.202.63.224:8000";
    }

    /*

    Given accept type is Json
    And ID parameter value is 5
    When user sends GET request to api/spartans/{id}
    Then response status code should be 200
    And rsponse content type= application /json
    And "Blythe" should be in response playload
     */


    @DisplayName("GET request to /api/spartans/{id} with ID 5")
    @Test
    public void test1() {

        Response response = given().
                accept(ContentType.JSON)
                .and().pathParam("id", 5)
                .when()
                .get("/api/spartans/{id}");
        //verify status code
        assertEquals(200, response.statusCode());
        //verify content type
        assertEquals("application/json", response.contentType());
        //verify Blythe in the json payload/body
        assertTrue(response.body().asString().contains("Blythe"));
        System.out.println(response.body().asString());

    }


    @DisplayName("GET request to /api/spartans/{id} with ID 500")
    @Test
    public void test2() {
      /*
        TASK
        Given accept type is Json
        And Id parameter value is 500
        When user sends GET request to /api/spartans/{id}
        Then response status code should be 404
        And response content-type: application/json
        And "Not Found" message should be in response payload
     */

        Response response = given().
                accept(ContentType.JSON).log().all()
                .and().pathParam("id", 500)
                .when().get("/api/spartans/{id}");

        assertEquals("application/json", response.contentType());
        assertEquals(404, response.statusCode());
//verify NOFound in the json payload/body
        assertTrue(response.body().asString().contains("Not Found"));


    }

     /*
        Given accept type is Json
        And query parameter values are:
        gender|Female
        nameContains|e
        When user sends GET request to /api/spartans/search
        Then response status code should be 200
        And response content-type: application/json
        And "Female" should be in response payload
        And "Janette" should be in response payload
     */

    @DisplayName("GET request to /api/spartans/search with Query Params")
    @Test
    public void test3(){
        Response response= given().log().all().
                accept(ContentType.JSON)
               .and().queryParam("nameContains","e")

             .and().queryParam("gender","Female")

                .when()
                .get("/api/spartans/search");

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());
        assertTrue(response.body().asString().contains("Janette"));
        assertTrue(response.body().asString().contains("Female"));

    }

    @DisplayName("GET request to /api/spartans/search with Query Params (MAP)")
    @Test
    public void test4(){
        Map<String,Object> mapQuery=new HashMap<>();
        mapQuery.put("nameContains","e");
        mapQuery.put("gender","Female");

        Response response= given().log().all().
                accept(ContentType.JSON)
                .and().queryParams(mapQuery)
                .when()
                .get("/api/spartans/search");

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());
        assertTrue(response.body().asString().contains("Janette"));
        assertTrue(response.body().asString().contains("Female"));

    }


}