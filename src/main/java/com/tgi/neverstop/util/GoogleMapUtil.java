package com.tgi.neverstop.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.tgi.neverstop.exception.NeverStopExcpetion;

public class GoogleMapUtil {

  private String googleApiKey = "AIzaSyA_cdNO7Xl2az-HOhiYjo9LxTiG56N2shY";
  private String googleMapApiUrl = "https://maps.googleapis.com/maps/api/geocode/json";

  public Map<String, Double> latLng(String address) throws NeverStopExcpetion {

    HashMap<String, Double> values = new HashMap<>();
    Double latitude = null;
    Double longitude = null;

    try {
      String apiURL = getGoogleMapApiUrl() + "?key=" + getGoogleApiKey() + "&address=" + address;

      HttpGet getRequest = new HttpGet(apiURL);

      getRequest.setHeader("Content-Type", "application/json");
      CloseableHttpClient httpClient = HttpClients.createDefault();
      HttpResponse response = httpClient.execute(getRequest);
      String responseText = EntityUtils.toString(response.getEntity());

      Gson gson = new Gson();
      @SuppressWarnings("unchecked")
      HashMap<String, Object> res = gson.fromJson(responseText, HashMap.class);

      if (res.toString().split("location=").length > 1
          && res.toString().split("location=")[1].split("lat=").length > 1) {
        String latLng[] = res.toString().split("location=")[1].split("lat=")[1].trim().split(",");
        if (latLng.length > 1 && latLng[1].split("lng=").length > 1) {
          latitude = Double.valueOf(latLng[0].trim().replaceAll("[^-+.0-9]", ""));
          longitude = Double.valueOf(latLng[1].split("lng=")[1].trim().replaceAll("[^-+.0-9]", ""));
          values.put("latitude", latitude);
          values.put("longitude", longitude);
        }
      }

    } catch (MalformedURLException e) {
      e.printStackTrace();
      throw new NeverStopExcpetion("GOOGLE_MAP_LAT_LNG_GENERATION_ERROR");
    } catch (IOException e) {
      e.printStackTrace();
      throw new NeverStopExcpetion("GOOGLE_MAP_LAT_LNG_GENERATION_ERROR");
    } catch (Exception e) {
      e.printStackTrace();
      throw new NeverStopExcpetion("GOOGLE_MAP_LAT_LNG_GENERATION_ERROR");
    }
    
    System.out.println("Values"+values);
    return values;
  }

  public static void main(String[] args) {
    GoogleMapUtil googleMapUtil = new GoogleMapUtil();
    try {
      String ab = "Trattoria Trippa,Via Giorgio Vasari, 1, 20135 Milano MI, Italy";
      googleMapUtil.latLng(ab.replace(" ", "%20"));
    } catch (NeverStopExcpetion e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public String getGoogleApiKey() {
    return googleApiKey;
  }

  public void setGoogleApiKey(String googleApiKey) {
    this.googleApiKey = googleApiKey;
  }

  public String getGoogleMapApiUrl() {
    return googleMapApiUrl;
  }

  public void setGoogleMapApiUrl(String googleMapApiUrl) {
    this.googleMapApiUrl = googleMapApiUrl;
  }

}
