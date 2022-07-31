package com.cydeo.DAY10;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class FormulaOneXmlTest {

    @BeforeAll
    public static void setUp(){
        //http://ergast.com/api/f1/drivers/alonso
        baseURI="http://ergast.com";
        basePath="/api/f1";
    }


    @Test
    public void test1(){
        Response response=given()
                .pathParam("driver","alonso")
                .when()
                .get("/drivers/{driver}")
                .then()
                .statusCode(200).log().all()
                .extract().response();

        XmlPath xmlPath=response.xmlPath();
        String name= xmlPath.getString("MRData.DriverTable.Driver.GivenName");
        System.out.println("name = " + name);
        String familyName= xmlPath.getString("MRData.DriverTable.Driver.FamilyName");
        System.out.println("familyName = " + familyName);
       String dateOfBirth= xmlPath.getString("MRData.DriverTable.Driver.DateOfBirth");
        System.out.println("dateOfBirth = " + dateOfBirth);
        String nationality= xmlPath.getString("MRData.DriverTable.Driver.Nationality");
        System.out.println("nationality = " + nationality);

        String driverId=xmlPath.getString("MRData.DriverTable.Driver.@driverId");
        System.out.println("driverId = " + driverId);
        String url=xmlPath.getString("MRData.DriverTable.Driver.@url");
        System.out.println("url = " + url);
        String code=xmlPath.getString("MRData.DriverTable.Driver.@code");
        System.out.println("code = " + code);
        //name = Fernando
        //familyName = Alonso
        //dateOfBirth = 1981-07-29
        //nationality = Spanish
        //driverId = alonso
        //url = http://en.wikipedia.org/wiki/Fernando_Alonso
        //code = ALO
    }


}
