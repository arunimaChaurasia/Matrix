package com.tiaa.matrix;
import java.util.Arrays;
import java.util.List;

import com.graphhopper.directions.api.client.ApiException;
import com.graphhopper.directions.api.client.api.GeocodingApi;
import com.graphhopper.directions.api.client.api.MatrixApi;
import com.graphhopper.directions.api.client.model.GeocodingLocation;
import com.graphhopper.directions.api.client.model.GeocodingResponse;
import com.graphhopper.directions.api.client.model.MatrixResponse;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        MatrixApi apiInstance = new MatrixApi();
        String key = "f16e909e-1b36-4507-967a-22f8b8816e04";
        GeocodingApi geocoding = new GeocodingApi();
    
        try {
            GeocodingResponse geocodingResponse = geocoding.geocodeGet(key, "kharadi,pune", "en", 5, false, "", "default");
            GeocodingLocation loc0 = geocodingResponse.getHits().get(0);
            System.out.println(loc0.getPoint() + ", " + loc0.getName() + ", " + loc0.getCountry() + ", " + loc0.getState());
        } catch (Exception ex) {
        	
        }
          // String | Get your key at graphhopper.com
        List<String> point = Arrays.asList("18.559003,73.786763","18.551450,73.934786","18.579305,73.982345"); // List<String> | Specifiy multiple points for which the weight-, route-, time- or distance-matrix should be calculated. In this case the starts are identical to the destinations. If there are N points, then NxN entries will be calculated. The order of the point parameter is important. Specify at least three points. Cannot be used together with from_point or to_point. Is a string with the format latitude,longitude.
//        String fromPoint = "fromPoint_example"; // String | The starting points for the routes. E.g. if you want to calculate the three routes A-&gt;1, A-&gt;2, A-&gt;3 then you have one from_point parameter and three to_point parameters. Is a string with the format latitude,longitude.
//        String toPoint = "toPoint_example"; // String | The destination points for the routes. Is a string with the format latitude,longitude.
        List<String> outArray = Arrays.asList("weights","times","distances"); // List<String> | Specifies which arrays should be included in the response. Specify one or more of the following options 'weights', 'times', 'distances'. To specify more than one array use e.g. out_array=times&out_array=distances. The units of the entries of distances are meters, of times are seconds and of weights is arbitrary and it can differ for different vehicles or versions of this API.
        String vehicle = "car"; // String | The vehicle for which the route should be calculated. Other vehicles are foot, bike, mtb, racingbike, motorcycle, small_truck, bus and truck. See here for the details.
        try {
            MatrixResponse result = apiInstance.matrixGet(key, point, null, null, outArray, vehicle);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling MatrixApi#matrixGet");
            e.printStackTrace();
        }
        
    }
}


