package com.cydeo.DAY10;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class SpartanWithXml extends SpartanAuthTestBase {



    @DisplayName("Get request ")
    @Test
    public void getSpartanXml(){
        //we will ask for xml response
        given().accept(ContentType.XML)
                .and()
                .auth().basic("admin","admin")
                .when()
                .get("/api/spartans")
                .then()
                //assert status code 200
                .statusCode(200)
                .contentType("application/xml;charset=UTF-8") //we verify we got xml through header
                .body("List.item[0].name",is("Meade"))
                .body("List.item[0].gender",is("Male"))
                .log().all();



        //assert content type is xml (we got xml response)
        //verify first spartan name is Meade



    }

    @DisplayName("GET request /api/spartans with xmlPath")
    @Test
    public void testXmlPath() {

        Response response = given()
                .accept(ContentType.XML) // we want xml response
                .and()
                .auth().basic("admin", "admin")
                .when()
                .get("/api/spartans");

        //get response xml body/payload and save inside the xmlpath object
        XmlPath xmlPath = response.xmlPath();

        //get first spartan name
    String name=xmlPath.getString("List.item[0].name");
        System.out.println("name = " + name);
        //get the 3rd spartan id number

        int id= xmlPath.getInt("List.item[2].id");
        System.out.println("id = " + id);

        //how to get all names and save into list of string
        List<String> allNames=xmlPath.getList("List.item.name");
        System.out.println("allNames = " + allNames);

    }


}
