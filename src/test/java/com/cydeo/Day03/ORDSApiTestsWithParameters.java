package com.cydeo.Day03;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiTestsWithParameters {
    //BeforeAll is a annotaion equals to @BeforeClass  in testNG, we use with static method name
    @BeforeAll   //from Junit
    public static void init() {
        //RestAssured.baseURI = "http://44.202.63.224:1000/ords/hr";
        // baseURI = "http://44.202.63.224:8000";
        baseURI = "http://44.202.63.224:1000/ords/hr";
    }


    /*
    Given accept type  is json
    And parameters:Q={region_id:2}
    When users dend a gET request to "/countries"
    Then status code is200
    And Content type is application/json
    And payload should contain "United States of America"
     */
    @DisplayName("GET request to /countries with query Param")
    @Test
    public void test1() {
        Response response= given().
                accept(ContentType.JSON).
                and().queryParam("q","{\"region_id\":2}").log().all()
                .when()
                .get("/countries");

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());
        assertEquals("application/json",response.header("Content-Type"));

        assertTrue(response.body().asString().contains("United States of America"));

response.prettyPrint();
    }
  /*
   Send a Get request to employees and get only employees who works as a IT_PROG
     */
    @DisplayName("Get request to employees and get only employees who works as a IT_PROG")
    @Test
    public void test2(){
        Response response=given().accept(ContentType.JSON).log().all()
                .and().queryParam("q","{\"job_id\": \"IT_PROG\"}")
                .when()
                .get("/employees");

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());
        assertEquals("application/json",response.header("Content-Type"));

        assertTrue(response.body().asString().contains("IT_PROG"));


        response.prettyPrint();
    }


}