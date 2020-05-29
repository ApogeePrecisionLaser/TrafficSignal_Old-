/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ts.util;

import com.luckycatlabs.sunrisesunset.SunriseSunsetCalculator;
import com.luckycatlabs.sunrisesunset.dto.Location;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Ritesh
 */
public class SunRiseSetCalc {

    public static void main(String[] args) {
        Longitude_LatitudeCalculator llCalculator = new Longitude_LatitudeCalculator();
        String city_name = "Delhi";
        String[] ll = llCalculator.findLatitudeLongitude(city_name);
        String latitude = ll[0];
        String longitude = ll[1];
        Location location = new Location(latitude, longitude);
        SunriseSunsetCalculator calculator = new SunriseSunsetCalculator(location, city_name);
        Calendar myCal = Calendar.getInstance();
        myCal.setTime(setDate("31-12-2014"));
        System.out.println("time---" + myCal.getTime());
//        Calendar date = Calendar.getInstance();
//        String dawn = calculator.getCivilSunriseForDate(date);
//        String dusk = calculator.getCivilSunsetForDate(date);
        String sunrise = calculator.getOfficialSunriseForDate(myCal);
        String sunset = calculator.getOfficialSunsetForDate(myCal);
        String[] str1 = sunrise.split(":");
        int sunrise_hr = Integer.parseInt(str1[0]) + 5;
        int sunrise_min = Integer.parseInt(str1[1]) + 30;
        int sunrise_min_difference;
        if (sunrise_min > 60) {
            sunrise_min_difference = sunrise_min - 60;
            sunrise_hr = sunrise_hr + 1;
            sunrise_min = sunrise_min_difference;
        }
        String[] str2 = sunset.split(":");
        int sunset_hr = Integer.parseInt(str2[0]) + 5;
        int sunset_min = Integer.parseInt(str2[1]) + 30;
        int sunset_min_difference;
        if (sunset_min > 60) {
            sunset_min_difference = sunset_min - 60;
            sunset_hr = sunset_hr + 1;
            sunset_min = sunset_min_difference;
        }
        System.out.println("officialSunrise" + sunrise + "---------officialSunset" + sunset);
    }

    public static java.sql.Date setDate(String date) {
        java.sql.Date finalDate = null;

        String strD = date;
        String[] str1 = strD.split("-");
        strD = str1[1] + "/" + str1[0] + "/" + str1[2]; // Format: mm/dd/yy
        try {
            finalDate = new java.sql.Date(DateFormat.getDateInstance(DateFormat.SHORT, new Locale("en", "US")).parse(strD).getTime());
        } catch (Exception ex) {
            System.out.println("junction sunrise sunset : setdate error" + ex);
        }
        return finalDate;
    }
}
