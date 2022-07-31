package com.cydeo.Day05;

import com.cydeo.utilities.DBUtils;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.get;
import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import java.util.Map;

public class SpartanAPIvsDB extends SpartanTestBase {

    @DisplayName("GET one spartan from api and database")
    @Test
    public void testDB1(){
        //get id,name,gender phone  from database
        //get same information from api
        //compare

        //1. get id,name,gender phone  from database
        String query = "select spartan_id,name,gender,phone from spartans\n" +
                "where spartan_id = 15";

        //save data inside the map
        Map<String, Object> dbMap = DBUtils.getRowMap(query);
        System.out.println(dbMap);
        //{PHONE=1938695106, GENDER=Female, SPARTAN_ID=15, NAME=Meta}   this info comes from database

//get info from API
//one way
        Map<String,Object> apiMap=given().accept(ContentType.JSON)
                .pathParam("id",15)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).and()
                .contentType("application/json")
                .extract().response()
                .as(Map.class);
        System.out.println("apiMap = " + apiMap);

        //apiMap = {id=15, name=Meta, gender=Female, phone=1938695106}

//another way
        Response response=given().accept(ContentType.JSON)
                .pathParam("id",15)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).and()
                .contentType("application/json")
                .extract().response();

        Map<String,Object> mapFromApi=response.as(Map.class);
        System.out.println("mapFromApi = " + mapFromApi);
//mapFromApi = {id=15, name=Meta, gender=Female, phone=1938695106}

        //3.compare database vs api --> we assume expected result is db
        assertThat(apiMap.get("id").toString(),is(dbMap.get("SPARTAN_ID").toString()));
        assertThat(mapFromApi.get("name"),is(dbMap.get("NAME")));
        assertThat(mapFromApi.get("gender"),is(dbMap.get("GENDER")));
        assertThat(mapFromApi.get("phone").toString(),is(dbMap.get("PHONE").toString()));





    }




}
