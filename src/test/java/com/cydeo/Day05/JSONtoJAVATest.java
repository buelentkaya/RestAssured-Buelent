package com.cydeo.Day05;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class JSONtoJAVATest extends SpartanTestBase {

    @DisplayName("GET one spartan and deserialize to MAP")
    @Test
    public void oneSpartanToMap() {

    Response response= given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200)
                .extract().response();  //works perfect.


        Map<String,Object> jsonMap=response.as(Map.class);

        System.out.println("jsonMap = " + jsonMap);
        //jsonMap = {id=15, name=Meta, gender=Female, phone=1938695106}
        System.out.println("jsonMap.toString() = " + jsonMap.toString());
        //jsonMap.toString() = {id=15, name=Meta, gender=Female, phone=1938695106}

//After we got the map,we can use hamcrest or junit assertions to do assertion
        String actualName=(String)jsonMap.get("name");
                assertThat(actualName,is("Meta"));
        System.out.println("actualName = " + actualName);
        //actualName = Meta


    }
    @DisplayName("GET all spartans  to JAVA ")
    @Test
    public void oneSpartanToJava() {

        Response response = get("/api/spartans").then().statusCode(200)
                .extract().response();

        List<Map<String,Object>> jsonList=response.as(List.class);
        System.out.println(jsonList.get(3).get("name"));  //Blythe

        Map<String,Object> spartans3=jsonList.get(2);
        System.out.println("spartans3 = " + spartans3);
        //spartans3 = {id=4, name=Paige, gender=Female, phone=3786741487}

    }

}
