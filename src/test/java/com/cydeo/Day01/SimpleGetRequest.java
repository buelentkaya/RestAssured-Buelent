package com.cydeo.Day01;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class SimpleGetRequest {

    String url = "http://44.202.63.224:8000/api/spartans";

@Test
    public void test1(){
// send a request and save response inside the response object

    Response response= RestAssured.get(url);

    //print response status code if we have any problem or not 200 ,400 or 500
    System.out.println(response.statusCode());

    //print response body

    response.prettyPrint();

}





}
