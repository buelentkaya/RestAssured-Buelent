package com.cydeo.Day04;

import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiTestWithPath extends HRTestBase {
    @DisplayName("GET request to countries with PTH METHOD")
    @Test
    public void test1() {
        Response response = given().accept(ContentType.JSON)
                .queryParam("q", "{\"region_id\":2}")
                .when()
                .get("/countries");

        assertEquals(200, response.statusCode());
        System.out.println("response.path(\"limit\") = " + response.path("limit"));
        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));
        System.out.println("country_id= " + response.path("items[0].country_id"));
     /*
     response.path("limit") = 25
      response.path("hasMore") = false
      country_id= AR
      */

        System.out.println("response.path(\"items[0].country_name\") = " + response.path("items[1].country_name"));
        //response.path("items[0].country_name") = Argentina
        //response.path("items[1].country_name") = Brazil

        System.out.println("response.path(\"items[2].links[0].href\") = " + response.path("items[2].links[0].href"));
    //response.path("items[2].links[0].href") =http://44.202.63.224:1000/ords/hr/countries/CA

        List<String> allCountriesId=response.path("items.country_id");
        List<String> allCountries=response.path("items.country_name");
        System.out.println("allCountries = " + allCountries);
        System.out.println("allCountriesId = " + allCountriesId);
//allCountries = [Argentina, Brazil, Canada, Mexico, United States of America]
//allCountriesId = [AR, BR, CA, MX, US]


        for (String eachCountry : allCountries) {
            if(eachCountry.equals("Canada")){
                System.out.println("eachCountry = " + eachCountry);
                break;
            }

        }
        for (String each : allCountriesId) {
            if(each.equals("CA")){
                System.out.println("each = " + each);
                break;
            }
        }

        List<Integer> regionsID=response.path("items.region_id");

        for(Integer each:regionsID){
            System.out.println("regionsID = " + regionsID);
            assertEquals(2,each);
            if(each.equals(2)){
                System.out.println("each = " + each);
                break;
            }
        }



    }


    @DisplayName("GET request to /employees with PATH METHOD")
    @Test
    public void test2() {
        Response response=given().accept(ContentType.JSON)
                .and().queryParam("q","{\"job_id\": \"IT_PROG\"}")
                .when()
                .get("/employees");

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());
        assertEquals("application/json",response.header("Content-Type"));

        assertTrue(response.body().asString().contains("IT_PROG"));

//make sure we have onl IT_Prog as a job_id

        List<String> allJobIDs=response.path("items.job_id");

        for (String jobID : allJobIDs) {
            System.out.println("jobID = " + jobID);
            assertEquals("IT_PROG",jobID);
        }


        //print each name






    }




}
