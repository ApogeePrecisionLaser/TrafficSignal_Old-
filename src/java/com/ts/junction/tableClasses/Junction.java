/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ts.junction.tableClasses;

import com.ts.tcpServer.ClientResponder;
import com.ts.webservice.ClientResponderWS;

/**
 *
 * @author Shruti
 */
public class Junction {
//following fields r part of the junction table.

    private int junction_id;
    private int program_version_no;
    private String junction_name;
    private String address1;
    private String address2;
    private String state_name;
    private String city_name;
    private String controller_model;
    private int no_of_sides;
    private int amber_time;
    private int flash_rate;
    private int no_of_plans;
    private String mobile_no;
    private String sim_no;
    private String imei_no;
    private int instant_green_time;
    private String pedestrian;
    private int pedestrian_time;
    private String side1_name;
    private String side2_name;
    private String side3_name;
    private String side4_name;
    private String side5_name;
    private String remark;
    private String longitude;
    private String latitude;
    private int file_no;
// these fields r NOT the part of the junction table it only belongs to the Registration Status.
    private boolean registration_status;
    private String lastVisitedTime;
// ClientResponder object is created corresponding to every Modem.
    private ClientResponder clientResponder;
    private ClientResponderWS clientResponderws;
    private boolean responseFromModemForRefresh;
    private boolean responseFromModemForClearance;
    private boolean responseFromModemForActivity;

    public void setClientResponderws(ClientResponderWS clientResponderws) {
        this.clientResponderws = clientResponderws;
    }

    public ClientResponderWS getClientResponderws() {
        return clientResponderws;
    }

    public boolean isResponseFromModemForActivity() {
        return responseFromModemForActivity;
    }

    public void setResponseFromModemForActivity(boolean responseFromModemForActivity) {
        this.responseFromModemForActivity = responseFromModemForActivity;
    }
    
    public boolean isResponseFromModemForRefresh() {
        return responseFromModemForRefresh;
    }

    public void setResponseFromModemForRefresh(boolean responseFromModemForRefresh) {
        this.responseFromModemForRefresh = responseFromModemForRefresh;
    }

    public boolean isResponseFromModemForClearance() {
        return responseFromModemForClearance;
    }

    public void setResponseFromModemForClearance(boolean responseFromModemForClearance) {
        this.responseFromModemForClearance = responseFromModemForClearance;
    }

    public ClientResponder getClientResponder() {
        return clientResponder;
    }

    public String getLastVisitedTime() {
        return lastVisitedTime;
    }

    public void setLastVisitedTime(String lastVisitedTime) {
        System.out.println("lastVisitedTime" + lastVisitedTime);
        this.lastVisitedTime = lastVisitedTime;
    }

    public void setClientResponder(ClientResponder clientResponder) {
        this.clientResponder = clientResponder;
    }

    public boolean isRegistration_status() {
        return registration_status;
    }

    public void setRegistration_status(boolean registration_status) {
        this.registration_status = registration_status;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public int getAmber_time() {
        return amber_time;
    }

    public void setAmber_time(int amber_time) {
        this.amber_time = amber_time;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getController_model() {
        return controller_model;
    }

    public void setController_model(String controller_model) {
        this.controller_model = controller_model;
    }

    public int getFlash_rate() {
        return flash_rate;
    }

    public void setFlash_rate(int flash_rate) {
        this.flash_rate = flash_rate;
    }

    public int getInstant_green_time() {
        return instant_green_time;
    }

    public void setInstant_green_time(int instant_green_time) {
        this.instant_green_time = instant_green_time;
    }

    public int getJunction_id() {
        return junction_id;
    }

    public void setJunction_id(int junction_id) {
        this.junction_id = junction_id;
    }

    public String getJunction_name() {
        return junction_name;
    }

    public void setJunction_name(String junction_name) {
        this.junction_name = junction_name;
    }

    public int getNo_of_plans() {
        return no_of_plans;
    }

    public void setNo_of_plans(int no_of_plans) {
        this.no_of_plans = no_of_plans;
    }

    public int getNo_of_sides() {
        return no_of_sides;
    }

    public void setNo_of_sides(int no_of_sides) {
        this.no_of_sides = no_of_sides;
    }

    public String getPedestrian() {
        return pedestrian;
    }

    public void setPedestrian(String pedestrian) {
        this.pedestrian = pedestrian;
    }

    public int getPedestrian_time() {
        return pedestrian_time;
    }

    public void setPedestrian_time(int pedestrian_time) {
        this.pedestrian_time = pedestrian_time;
    }

    public String getSide1_name() {
        return side1_name;
    }

    public void setSide1_name(String side1_name) {
        this.side1_name = side1_name;
    }

    public String getSide2_name() {
        return side2_name;
    }

    public void setSide2_name(String side2_name) {
        this.side2_name = side2_name;
    }

    public String getSide3_name() {
        return side3_name;
    }

    public void setSide3_name(String side3_name) {
        this.side3_name = side3_name;
    }

    public String getSide4_name() {
        return side4_name;
    }

    public void setSide4_name(String side4_name) {
        this.side4_name = side4_name;
    }

    public String getSide5_name() {
        return side5_name;
    }

    public void setSide5_name(String side5_name) {
        this.side5_name = side5_name;
    }

    public String getImei_no() {
        return imei_no;
    }

    public void setImei_no(String imei_no) {
        this.imei_no = imei_no;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getSim_no() {
        return sim_no;
    }

    public void setSim_no(String sim_no) {
        this.sim_no = sim_no;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }
    
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getFile_no() {
        return file_no;
    }

    public void setFile_no(int file_no) {
        this.file_no = file_no;
    }

    public int getProgram_version_no() {
        return program_version_no;
    }

    public void setProgram_version_no(int program_version_no) {
        this.program_version_no = program_version_no;
    }
    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    

}
