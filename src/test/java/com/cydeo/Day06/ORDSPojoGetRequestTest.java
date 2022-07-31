package com.cydeo.Day06;

import com.cydeo.pojo.Employee;
import com.cydeo.pojo.Region;
import com.cydeo.pojo.Regions;
import com.cydeo.utilities.HRTestBase;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.get;
import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.given;

public class ORDSPojoGetRequestTest extends HRTestBase {
    @Test
    public  void regionTest(){

        JsonPath jsonPath= get("/regions").then().statusCode(200).extract().jsonPath();

        Region region1= jsonPath.getObject("items[0]", Region.class);
        System.out.println("region1 = " + region1);
        //region1 = Region{region_id=1, region_name='Europe', links=[Link{rel='self', href='http://44.202.63.224:1000/ords/hr/regions/1'}]}

        System.out.println("region1.getRegion_id() = " + region1.getRegionId());
        System.out.println("region1.getRegion_name() = " + region1.getRegionName());
        System.out.println("region1.getLinks().get(0).getHref() = " + region1.getLinks().get(0).getHref());
        System.out.println("region1.getLinks().get(0).getRel() = " + region1.getLinks().get(0).getRel());
//region1.getRegion_id() = 1
//region1.getRegion_name() = Europe
//region1.getLinks().get(0).getHref() = http://44.202.63.224:1000/ords/hr/regions/1

    }
    @DisplayName("GET request to /employees and only get couple of values as a Pojo class")
    @Test
    public void employeeGet(){
        Employee employee1= get("/employees").then().statusCode(200)
                .extract().jsonPath().getObject("items[0]", Employee.class);
        System.out.println(employee1);
        //Employee(firstName=Steven, lastName=King, jobId=AD_PRES, salary=24000)
    }


/* send a get request to regions
        verify that region ids are 1,2,3,4
        verify that regions names Europe ,Americas , Asia, Middle East and Africa
        verify that count is 4
        try to use pojo as much as possible
        ignore non used fields

     */

    @DisplayName("GET request to region only some fields test")
    @Test
    public void regionPojoTest(){
//         send a get request and save everthing inside the regions object
//       since we prepare pojo also for the all properties we dont need to use any path so as() method is enough
//        Regions regions = get("/regions").then().statusCode(200).extract().response().as(Regions.class);

        Regions regions = get("/regions").then().statusCode(200).extract().as(Regions.class);

        //verify count is 4

        assertThat(regions.getCount(),is(4));


//create empty list to store values
        List<String> regionNames=new ArrayList<>();
        List<Integer> regionIds=new ArrayList<>();

        //get list of regions out of regions object
        List<Region> items=regions.getItems();

        //loop through each of the region, save their ids and names to empty list that we prepare
        for (Region region : items) {
            regionIds.add(region.getRegionId());
            regionNames.add(region.getRegionName());
        }

        System.out.println("regionIds = " + regionIds);
        System.out.println("regionNames = " + regionNames);
//regionIds = [1, 2, 3, 4]
//regionNames = [Europe, Americas, Asia, Middle East and Africa]

        //prepare expected result
        List<Integer> expectedRegionIds = Arrays.asList(1,2,3,4);
        List<String> expectedRegionNames = Arrays.asList("Europe", "Americas", "Asia", "Middle East and Africa");

        //compare two result

        assertThat(regionIds,is(expectedRegionIds));
        assertThat(regionNames,is(expectedRegionNames));



    }






}
