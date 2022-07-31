package com.cydeo.DAY08;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.get;
import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.given;

public class SpartanWithAuthTests extends SpartanAuthTestBase {

    @DisplayName("GET /api/spartans as a public user(guest) expect 401")
    @Test
    public void test1(){
        given().accept(ContentType.JSON)
                .when()
                .get("/api/spartans")
                .then().statusCode(401)
                .log().all();

    }

    @DisplayName("GET /api/spartans as admin user expect 200")
    @Test
    public void test2(){
        //how to pass admin as a username and password

        given().
                auth().basic("admin","admin")
                .accept(ContentType.JSON)
                .when()
                .get("/api/spartans")
                .then().statusCode(401)
                .log().all();

    }

    @DisplayName("DELETE /api/spartans7{ID] as editor user expect 200")
    @Test
    public void test3(){

        given().
                auth().basic("editor","editor")
                .accept(ContentType.JSON)
                .pathParam("id","30")
                .when()
                .delete("/api/spartans/{id}")
                .then().statusCode(403)
                .log().body();
    }


     /*
        As a homework,write a detealied test for Role Base Access Control(RBAC)
            in Spartan Auth app (7000)
            Admin should be able take all CRUD
            Editor should be able to take all CRUD
                other than DELETE
            User should be able to only READ data
                not update,delete,create (POST,PUT,PATCH,DELETE)
       --------------------------------------------------------
        Can guest even read data ? 401 for all

     */




}
