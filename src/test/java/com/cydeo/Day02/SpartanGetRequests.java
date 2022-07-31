package com.cydeo.Day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class SpartanGetRequests {


    //Given Accept type application/json
    // When user send GET request to api/spartans end XPointerHandler
    // Then status code must 200
    // And response Content Type must be application/json
    // And response body should include spartan result

    String baseUrl = "http://44.202.63.224:8000";

    @Test
    public void test1() {

        Response response = RestAssured.given().accept(ContentType.JSON)
                // RestAssured.given().accept(ContentType.JSON)   this is also enough
                .when()
                .get(baseUrl + "/api/spartans");


        System.out.println(response.statusCode());//200
        System.out.println(response.contentType());// application/json

        //print whole result body
        response.prettyPrint();


        //how to do API testing
        //verify status code
        Assertions.assertEquals(response.statusCode(), 200);    //from junit    not testNG  (Assert.assert)
        //verify content type
        Assertions.assertEquals(response.contentType(), "application/json");


    }

    @DisplayName("GET one sparten /api/spartan/3 and verify")
    @Test
    public void test2() {
        Response response = RestAssured.given().accept(ContentType.JSON).
                when().get(baseUrl + "/api/spartan/3");

        //verify statusCode
        Assertions.assertEquals(response.statusCode(), 200);

        //verify content TYPe

        Assertions.assertEquals(response.contentType(), "application/json");

        Assertions.assertTrue(response.body().asString().contains("Fidole"));// we get the whole body as string a
        // nd check it if it contains our employee or not

    }



    /*
    Given no headers provided
    When users send GET request to api/hello
    Then response status code should be 200
    And Content type header should be "text7plain;charset=UTF-8"
    And header should contain date
    And content length should be 17
    And body should be "Hello from Sparta"
     */


    @DisplayName("GET request to /api/hello")
    @Test
    public void test3() {

        //send request and save response inside the response object
        Response response = RestAssured.when().get(baseUrl + "/api/hello");

        //verify status code
        Assertions.assertEquals(response.statusCode(), 200);

        //verify content type

        Assertions.assertEquals(response.contentType(), response.contentType(), "text/plain;charset=UTF-8");

//Verify we have headers named date
        //we use hasHeaderWithName method to verify
        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));   // return me boolean


//how to get and header from response using header key?
        //we use response.header(String headername) metod to get any header value
        System.out.println("response.header(\"Content-length\") = " + response.header("Content-length"));
        System.out.println("response.headers(\"Date\") = " + response.header("Date"));


        //verify content length is 17
        Assertions.assertEquals("Hello from Sparta", response.body().asString());

        System.out.println("response.body() = " + response.body());//response.body() = io.restassured.internal.RestAssuredResponseImpl@3659d7b1
        System.out.println("response.body().asString() = " + response.body().asString());  //= Hello from Sparta

        //response.header("Content-length") = 17
        //response.headers("Date") = Thu, 14 Jul 2022 16:09:57 GMT

    }


}
