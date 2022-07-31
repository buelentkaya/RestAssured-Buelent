package com.cydeo.Day04;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CBTrainingApiWithJsonPath {

    @DisplayName("GET individual Student")
    @BeforeAll   //from Junit
    public static void init() {

        baseURI = "http://api.cybertektraining.com";

    }
    //send a get request to student id 23401 as a path parameter and accept header application/json
    //verify status code=200 /content type=application/json;charset=UTF-8 /Content-Encoding = gzip
    //verify Date header exists
    //assert that
            /*
                firstName Vera
                batch 14
                section 12
                emailAddress aaa@gmail.com
                companyName Cybertek
                state IL
                zipCode 60606

                using JsonPath
             */
    @DisplayName("GET Request to individual student")
    @Test
    public void test1() {

        //send a get request to student id 23401 as a path parameter and accept header application/json
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 32881)
                .when()
                .get("/students/{id}");


        // verify status code=200
        // content type=application/json;charset=UTF-8
        // Content-Encoding = gzip
        System.out.println(response.statusCode());//200
        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("gzip", response.header("Content-Encoding"));
        Assertions.assertEquals("application/json;charset=UTF-8", response.contentType());
        //verify Date header exists
        Assertions.assertNotNull(response.header("Date"));
        assertTrue(response.headers().hasHeaderWithName("Date"));

        JsonPath jsonPath = response.jsonPath();
// firstName Vera
        String firstName = jsonPath.getString("firstName");
        assertTrue(firstName.equals("Vera"));
//  batch 14
        String batch = jsonPath.getString("batch");
        assertTrue(batch.equals("14"));
        //    section 12
        String section = jsonPath.getString("section");
        assertTrue(section.equals("12"));
// emailAddress aaa@gmail.com
        String emailAddress = jsonPath.getString("contact.emailAddress");
        assertTrue(emailAddress.equals("aaa@gmail.com"));
//   companyName Cybertek
        String companyName = jsonPath.getString("company.companyName");
        assertTrue(companyName.equals("Cybertek"));
// state IL
        String state = jsonPath.getString("company.address.state");
        assertTrue(state.equals("IL"));
//   zipCode 60606
        String zipCode = jsonPath.getString("company.address.zipCode");
        assertTrue(zipCode.equals("60606"));


        System.out.println(firstName);
        System.out.println(batch);
        System.out.println(section);
        System.out.println(emailAddress);
        System.out.println(companyName);
        System.out.println(state);
        System.out.println(zipCode);

    }
}
