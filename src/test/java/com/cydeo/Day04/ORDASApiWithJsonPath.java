package com.cydeo.Day04;

import com.cydeo.utilities.HRTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDASApiWithJsonPath extends HRTestBase {
    @DisplayName("GET request to Countries")
    @Test

    public void test1(){

        Response response = get("/countries");

        //get the second country name with JsonPath

        //to use jsonpath we assign response to JsonPath
        JsonPath jsonPath = response.jsonPath();

        String countryName=jsonPath.getString("items[1].country_name");
        System.out.println("countryName = " + countryName);

        List<String>allCountriesID=jsonPath.getList("items.country_id");
        System.out.println("allCountriesID = " + allCountriesID);
        for (String each : allCountriesID) {
            System.out.println("each = " + each);

        }

        //get all country names where their region id is equal to 2

        List<String>countryNames=jsonPath.getList("items.findAll{it.region_id==2}.country_name");
        System.out.println("countryNames = " + countryNames);
//countryNames = [Argentina, Brazil, Canada, Mexico, United States of America]

    }

    @DisplayName("GET request to /employees with query param ")
    @Test
    public  void test2(){
        Response response = given().queryParam("limit", 107)
                .when()
                .get("/employees");

        //get  me all email
        JsonPath jsonPath=response.jsonPath();
        List<String> allEmails=jsonPath.getList("items.findAll{it.job_id==\"IT_PROG\"}.email");
        System.out.println("allEmails = " + allEmails);  //allEmails = [AHUNOLD, BERNST, DAUSTIN, VPATABAL, DLORENTZ]

        //get me first name of employees who is making more than 1000

        List<String> firstNames=jsonPath.getList("items.findAll{it.salary>10000}.first_name");
        System.out.println("firstNames = " + firstNames);
        //firstNames = [Steven, Neena, Lex, Nancy, Den, John, Karen, Alberto, Gerald, Eleni, Clara, Lisa, Ellen, Michael, Shelley]

        //get the max salary first_name
        String kingFirstName = jsonPath.getString("items.max {it.salary}.first_name");
        String kingNameWithPathMethod = response.path("items.max {it.salary}.first_name");
        System.out.println("kingFirstName = " + kingFirstName);
        System.out.println("kingNameWithPathMethod = " + kingNameWithPathMethod);


    }







}
