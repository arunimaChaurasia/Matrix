package com.tiaa.matrix;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.graphhopper.directions.api.client.ApiException;
import com.graphhopper.directions.api.client.api.GeocodingApi;
import com.graphhopper.directions.api.client.api.MatrixApi;
import com.graphhopper.directions.api.client.model.GeocodingLocation;
import com.graphhopper.directions.api.client.model.GeocodingPoint;
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
       ExcelRead exRead=new ExcelRead();
       ArrayList<String> address=new ArrayList<String>();
       String file="C:\\Users\\Inspiron\\Desktop\\address.xlsx";
       try {
		address=exRead.readFile(file);
	} catch (EncryptedDocumentException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (InvalidFormatException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
        MatrixApi apiInstance = new MatrixApi();
        String key = "f16e909e-1b36-4507-967a-22f8b8816e04";
        GeocodingApi geocoding = new GeocodingApi();
        GeocodingResponse geocodingResponse;
        GeocodingLocation loc0;
        GeocodingPoint locPoint;
        List<String> point = new ArrayList<String>();
        String val;
        System.out.println(address);
    
        try {
        	for(String object:address){
        		//System.out.println(object);
        		  geocodingResponse = geocoding.geocodeGet(key, object, "en", 5, false, "", "default");
                  loc0 = geocodingResponse.getHits().get(0);
                  locPoint=loc0.getPoint();
                  val=locPoint.getLat() +"," + locPoint.getLng();
                  point.add(val);
                 System.out.println(val);
        	}
           
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
        
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


