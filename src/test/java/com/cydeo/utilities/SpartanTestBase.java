package com.cydeo.utilities;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class SpartanTestBase {


    //BeforeAll is a annotaion equals to @BeforeClass  in testNG, we use with static method name
    @BeforeAll   //from Junit
    public static void init() {

        baseURI = "http://44.202.63.224:8000";      //  for  api

        String dbUrl = "jdbc:oracle:thin:@44.202.63.224:1521:xe";
        String dbUsername = "SP";
        String dbPassword = "SP";

        DBUtils.createConnection(dbUrl,dbUsername,dbPassword);   // for database testing
    }


    @AfterAll
    public static void teardown(){
        DBUtils.destroy();
    }
}
