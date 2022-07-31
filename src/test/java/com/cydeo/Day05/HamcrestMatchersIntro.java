package com.cydeo.Day05;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matcher;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

public class HamcrestMatchersIntro extends SpartanTestBase {

    @Test
    public void test1() {
        //request
        given().accept(ContentType.JSON).
                pathParam("id", 15).
                when().get("/api/spartans/{id}")
                .then().statusCode(200).and()
                .assertThat().contentType("application/json");

    }

    @Test
    public void test2() {
        given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .when().get("/api/spartans/{id}")
                .then().assertThat().statusCode(200)
                .and().assertThat().contentType("application/json")
                .and().assertThat().body("id", equalTo(15), "name", equalTo("Meta"), "gender",
                        equalTo("Female"), "phone", equalTo(1938695106));


    }

    @Test
    public void test3() {

        assertThat(5 + 5, is(10));  // is 5 +5 =10?
        assertThat(5 + 5, equalTo(10));  // is 5 +5 =10?
        assertThat(5 + 5, is(equalTo(10)));  // is 5 +5 =10?


        assertThat(5 + 5, not(9));
        assertThat(5 + 5, is(not(9)));
        assertThat(5 + 5, is(not(equalTo(9))));


    }

    @DisplayName("Assertion with String")
    @Test
    public void test4() {
        String text = "EU08 is learning Hamcrest";
//checking for uquality  is sme as numbers
        assertThat(text, is("EU08 is learning Hamcrest"));
        assertThat(text, equalTo("EU08 is learning Hamcrest"));
        assertThat(text, is(equalTo("EU08 is learning Hamcrest")));

        assertThat(text, startsWith("EU08"));
        assertThat(text, startsWithIgnoringCase("eu08"));
        assertThat(text, endsWith("rest"));



        //check if text contains String "learning"
        assertThat(text,containsString("learning"));

        //with ignoring case
        assertThat(text,containsStringIgnoringCase("LEarninG"));


       String str=" ";
       //check if above string is blank
        assertThat(str,blankString());

        assertThat(str.trim(),emptyString());


    }


    @DisplayName("Hamcrest for Collection")
    @Test
    public void test5(){

        List<Integer> listOfNumbers= Arrays.asList(1,4,5,6,32,54,66,77,45,23);

        //check size of the list

        assertThat(listOfNumbers,hasSize(10));

        assertThat(listOfNumbers,hasItem(77));
        assertThat(listOfNumbers,hasItems(77,54,23));



        //check if all numbers greater than 0 ,

        assertThat(listOfNumbers,everyItem(greaterThan(0)));










    }

}
