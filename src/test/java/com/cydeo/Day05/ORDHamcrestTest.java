package com.cydeo.Day05;

import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ORDHamcrestTest extends HRTestBase {


    @DisplayName("GET request to employees IT_PROG endpoint and chaining")
    @Test
    public void employeesTest() {

        List<String> names = Arrays.asList("Alexander", "Bruce", "David", "Valli", "Diana");

        //send a get request to employees end point with query parameter job_id IT_PROG
        given().accept(ContentType.JSON)
                .queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .when()
                .get("/employees").then().statusCode(200).

                //verify each job_id is ID_PROG
                        body("items.job_id", everyItem(equalTo("IT_PROG"))).

                //verify first names are .............()find method to checklist against list) containsInRelativeOrder deyince ayni düzende olmalilar ma any order deyince sadece obektler olsun ama nerede yar aldiklari onemli degil
                        body("items.first_name", containsInRelativeOrder("Alexander", "Bruce", "David", "Valli", "Diana")).
                body("items.first_name", containsInAnyOrder("Bruce", "David", "Valli", "Diana", "Alexander")).

                //verify emails without checking order (provide emails in different order, just make it has same emails)
                        body("items.email", containsInAnyOrder("VPATABAL", "DAUSTIN", "BERNST", "AHUNOLD", "DLORENTZ")) //(emails9contains without order
                .body("items.first_name", equalTo(names)); // equality of lists assertion


    }


    @Test
    public void employeesTest2() {
        //we want to chain and also get response object


        JsonPath jsonPath = given().accept(ContentType.JSON)
                .queryParam("q")
                .get("/employees").
                then().
                statusCode(200).

                //verify each job_id is ID_PROG
                        body("items.job_id", everyItem(equalTo("IT_PROG")))

                //extract() --> method that allow us to get response object after we use then() method.
                .extract().jsonPath();


        //assert that we have only 5 firstnames
        assertThat(jsonPath.getList("items.firs_name"), hasSize(5));

//assert firstname order
        //assert firstnames order
        assertThat(jsonPath.getList("items.first_name"), containsInRelativeOrder("Alexander", "Bruce", "David", "Valli", "Diana"));






        /*
        Response response = given().accept(ContentType.JSON)
                .queryParam("q")
                .get("/employees").
                then().
                statusCode(200).

                //verify each job_id is ID_PROG
                        body("items.job_id", everyItem(equalTo("IT_PROG")))
                .extract().response();
         */


        /*
          int statusCode= given().accept(ContentType.JSON)
                .queryParam("q")
                .get("/employees").
                then().
                statusCode(200).

                //verify each job_id is ID_PROG
                        body("items.job_id", everyItem(equalTo("IT_PROG")))
                .extract().response().statusCode();


         */


    }

}
