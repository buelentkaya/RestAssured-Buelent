package com.cydeo.Day05;



import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class SpartanHamcrestTest extends SpartanTestBase {
    @DisplayName("GET spartan/search and chaining together")
    @Test
    public void test1(){

    List<String> names= given().accept(ContentType.JSON)
                .and().queryParams("nameContains","j",
                        "gender","Male").and().
                when().
                get("/api/spartans/search").then()

                .statusCode(200).body(
                        "totalElement",is(3))
               // .extract().response()  // whether you write reponse() or not does not make sense
                .extract()
                .jsonPath().getList("content.name");

        System.out.println("names = " + names);//names = [Julio, Juan, Julie]


    }

    @Test
    public void test2(){

        int statusCode= given().accept(ContentType.JSON)
                .and().queryParams("nameContains","j",
                        "gender","Male").and().
                when().
                get("/api/spartans/search").then()

                .statusCode(200).body(
                        "totalElement",is(3))
               // .extract().response().statusCode();
                .extract().statusCode();

        System.out.println("statusCode = " + statusCode);


    }

}
