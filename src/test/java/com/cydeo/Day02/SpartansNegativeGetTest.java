package com.cydeo.Day02;


import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class SpartansNegativeGetTest {

    //BeforeAll is a annotaion equals to @BeforeClass  in testNG, we use with static method name
    @BeforeAll   //from Junit
    public static void init() {
        //RestAssured.baseURI = "http://44.202.63.224:1000/ords/hr";
        baseURI = "http://44.202.63.224:1000/ords/hr";
    }

    @DisplayName("GET request to /api/spartans/10")
    @Test
    public void test1() {
        /*
        Given Accept type application/xml
        When user send GET request to/api/spartans/10 endpoint
        Then status code must be 406
        And response Content Type must be applicantion/xml;chaarset=UTF-8
         */
       Response response= given().
                         accept(ContentType.XML).
                when().
                           get("api/spartans/10");

        //verify status code is 406
        assertEquals(406,response.statusCode());
        //verify content type
        assertEquals("application/xml;charset=UTF-8",response.contentType());






    }
}
