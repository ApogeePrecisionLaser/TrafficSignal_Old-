/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ts.junction.tableClasses;

/**
 *
 * @author jpss
 */
public class DamohNakaInfo {

    private int junction_id;
    private int program_version_no;
    private int fileNo;
    private String junction_name;
    private int plan_no;
    private int plan_revision_no;
    private int is_edited;
    private int on_time_hour;
    private int on_time_min;
    private int off_time_hour;
    private int off_time_min;
    private String mode;
    private String onTime;
    private String offTime;
    private int side1_green_time;
    private int side2_green_time;
    private int side3_green_time;
    private int side4_green_time;
    private int side5_green_time;
    private int side1_amber_time;
    private int side2_amber_time;
    private int side3_amber_time;
    private int side4_amber_time;
    private int side5_amber_time;
    private int function_no;
    private int hours;
    private int mins;
    private int secs;
    private int side_no;
    private int side_no1;
    private String sideName;
    private int no_of_sides;
    private int on_time_sec;
    private int off_time_sec;
    private int side1_up_status;
    private int side1_down_status;
    private int side1_left_status;
    private int side1_right_status;
    private int side2_up_status;
    private int side2_down_status;
    private int side2_left_status;
    private int side2_right_status;
    private int side3_up_status;
    private int side3_down_status;
    private int side3_left_status;
    private int side3_right_status;
    private int side4_up_status;
    private int side4_down_status;
    private int side4_left_status;
    private int side4_right_status;
    private int side5_up_status;
    private int side5_down_status;
    private int side5_left_status;
    private int side5_right_status;
    private int side1_time;
    private int side2_time;
    private int side3_time;
    private int side4_time;
    private int side5_time;
    private String side1Name;
    private String side2Name;
    private String side3Name;
    private String side4Name;
    private String side5Name;
    private boolean responseFromModemForRefresh;
    private boolean responseFromModemForClearance;
    private int activity;
    private int juncHr;
    private int juncMin;
    private int juncDate;
    private int juncMonth;
    private int juncYear;

    public int getJuncDate() {
        return juncDate;
    }

    public void setJuncDate(int juncDate) {
        this.juncDate = juncDate;
    }

    public int getJuncHr() {
        return juncHr;
    }

    public void setJuncHr(int juncHr) {
        this.juncHr = juncHr;
    }

    public int getJuncMin() {
        return juncMin;
    }

    public void setJuncMin(int juncMin) {
        this.juncMin = juncMin;
    }

    public int getJuncMonth() {
        return juncMonth;
    }

    public void setJuncMonth(int juncMonth) {
        this.juncMonth = juncMonth;
    }

    public int getJuncYear() {
        return juncYear;
    }

    public void setJuncYear(int juncYear) {
        this.juncYear = juncYear;
    }


    public int getActivity() {
        return activity;
    }

    public void setActivity(int activity) {
        this.activity = activity;
    }

    public int getIs_edited() {
        return is_edited;
    }

    public void setIs_edited(int is_edited) {
        this.is_edited = is_edited;
    }

    public int getPlan_revision_no() {
        return plan_revision_no;
    }

    public void setPlan_revision_no(int plan_revision_no) {
        this.plan_revision_no = plan_revision_no;
    }

    public int getProgram_version_no() {
        return program_version_no;
    }

    public void setProgram_version_no(int program_version_no) {
        this.program_version_no = program_version_no;
    }

    public int getFileNo() {
        return fileNo;
    }

    public void setFileNo(int fileNo) {
        this.fileNo = fileNo;
    }

    public int getFunction_no() {
        return this.function_no;
    }

    public void setFunction_no(int function_no) {
        this.function_no = function_no;
    }

    public String getSide1Name() {
        return this.side1Name;
    }

    public void setSide1Name(String side1Name) {
        this.side1Name = side1Name;
    }

    public int getSide_no1() {
        return this.side_no1;
    }

    public void setSide_no1(int side_no1) {
        this.side_no1 = side_no1;
    }

    public String getSide2Name() {
        return this.side2Name;
    }

    public void setSide2Name(String side2Name) {
        this.side2Name = side2Name;
    }

    public String getSide3Name() {
        return this.side3Name;
    }

    public void setSide3Name(String side3Name) {
        this.side3Name = side3Name;
    }

    public String getSide4Name() {
        return this.side4Name;
    }

    public void setSide4Name(String side4Name) {
        this.side4Name = side4Name;
    }

    public String getSide5Name() {
        return this.side5Name;
    }

    public void setSide5Name(String side5Name) {
        this.side5Name = side5Name;
    }

    public String getSideName() {
        return this.sideName;
    }

    public void setSideName(String sideName) {
        this.sideName = sideName;
    }

    public int getSide1_down_status() {
        return this.side1_down_status;
    }

    public void setSide1_down_status(int side1_down_status) {
        this.side1_down_status = side1_down_status;
    }

    public int getSide1_left_status() {
        return this.side1_left_status;
    }

    public void setSide1_left_status(int side1_left_status) {
        this.side1_left_status = side1_left_status;
    }

    public int getSide1_right_status() {
        return this.side1_right_status;
    }

    public void setSide1_right_status(int side1_right_status) {
        this.side1_right_status = side1_right_status;
    }

    public int getSide1_up_status() {
        return this.side1_up_status;
    }

    public void setSide1_up_status(int side1_up_status) {
        this.side1_up_status = side1_up_status;
    }

    public int getSide2_down_status() {
        return this.side2_down_status;
    }

    public void setSide2_down_status(int side2_down_status) {
        this.side2_down_status = side2_down_status;
    }

    public int getSide2_left_status() {
        return this.side2_left_status;
    }

    public void setSide2_left_status(int side2_left_status) {
        this.side2_left_status = side2_left_status;
    }

    public int getSide2_right_status() {
        return this.side2_right_status;
    }

    public void setSide2_right_status(int side2_right_status) {
        this.side2_right_status = side2_right_status;
    }

    public int getSide2_up_status() {
        return this.side2_up_status;
    }

    public void setSide2_up_status(int side2_up_status) {
        this.side2_up_status = side2_up_status;
    }

    public int getSide3_down_status() {
        return this.side3_down_status;
    }

    public void setSide3_down_status(int side3_down_status) {
        this.side3_down_status = side3_down_status;
    }

    public int getSide3_left_status() {
        return this.side3_left_status;
    }

    public void setSide3_left_status(int side3_left_status) {
        this.side3_left_status = side3_left_status;
    }

    public int getSide3_right_status() {
        return this.side3_right_status;
    }

    public void setSide3_right_status(int side3_right_status) {
        this.side3_right_status = side3_right_status;
    }

    public int getSide3_up_status() {
        return this.side3_up_status;
    }

    public void setSide3_up_status(int side3_up_status) {
        this.side3_up_status = side3_up_status;
    }

    public int getSide4_down_status() {
        return this.side4_down_status;
    }

    public void setSide4_down_status(int side4_down_status) {
        this.side4_down_status = side4_down_status;
    }

    public int getSide4_left_status() {
        return this.side4_left_status;
    }

    public void setSide4_left_status(int side4_left_status) {
        this.side4_left_status = side4_left_status;
    }

    public int getSide4_right_status() {
        return this.side4_right_status;
    }

    public void setSide4_right_status(int side4_right_status) {
        this.side4_right_status = side4_right_status;
    }

    public int getSide4_up_status() {
        return this.side4_up_status;
    }

    public void setSide4_up_status(int side4_up_status) {
        this.side4_up_status = side4_up_status;
    }

    public int getSide5_down_status() {
        return this.side5_down_status;
    }

    public void setSide5_down_status(int side5_down_status) {
        this.side5_down_status = side5_down_status;
    }

    public int getSide5_left_status() {
        return this.side5_left_status;
    }

    public void setSide5_left_status(int side5_left_status) {
        this.side5_left_status = side5_left_status;
    }

    public int getSide5_right_status() {
        return this.side5_right_status;
    }

    public void setSide5_right_status(int side5_right_status) {
        this.side5_right_status = side5_right_status;
    }

    public int getSide5_up_status() {
        return this.side5_up_status;
    }

    public void setSide5_up_status(int side5_up_status) {
        this.side5_up_status = side5_up_status;
    }

    public int getHours() {
        return this.hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMins() {
        return this.mins;
    }

    public void setMins(int mins) {
        this.mins = mins;
    }

    public int getSecs() {
        return this.secs;
    }

    public void setSecs(int secs) {
        this.secs = secs;
    }

    public int getNo_of_sides() {
        return this.no_of_sides;
    }

    public void setNo_of_sides(int no_of_sides) {
        this.no_of_sides = no_of_sides;
    }

    public int getSide1_time() {
        return this.side1_time;
    }

    public void setSide1_time(int side1_time) {
        this.side1_time = side1_time;
    }

    public int getSide2_time() {
        return this.side2_time;
    }

    public void setSide2_time(int side2_time) {
        this.side2_time = side2_time;
    }

    public int getSide3_time() {
        return this.side3_time;
    }

    public void setSide3_time(int side3_time) {
        this.side3_time = side3_time;
    }

    public int getSide4_time() {
        return this.side4_time;
    }

    public void setSide4_time(int side4_time) {
        this.side4_time = side4_time;
    }

    public int getSide5_time() {
        return this.side5_time;
    }

    public void setSide5_time(int side5_time) {
        this.side5_time = side5_time;
    }

    public int getOff_time_sec() {
        return this.off_time_sec;
    }

    public void setOff_time_sec(int off_time_sec) {
        this.off_time_sec = off_time_sec;
    }

    public int getOn_time_sec() {
        return this.on_time_sec;
    }

    public void setOn_time_sec(int on_time_sec) {
        this.on_time_sec = on_time_sec;
    }

    public int getSide_no() {
        return this.side_no;
    }

    public void setSide_no(int side_no) {
        this.side_no = side_no;
    }

    public int getSide1_amber_time() {
        return this.side1_amber_time;
    }

    public void setSide1_amber_time(int side1_amber_time) {
        this.side1_amber_time = side1_amber_time;
    }

    public int getSide2_amber_time() {
        return this.side2_amber_time;
    }

    public void setSide2_amber_time(int side2_amber_time) {
        this.side2_amber_time = side2_amber_time;
    }

    public int getSide3_amber_time() {
        return this.side3_amber_time;
    }

    public void setSide3_amber_time(int side3_amber_time) {
        this.side3_amber_time = side3_amber_time;
    }

    public int getSide4_amber_time() {
        return this.side4_amber_time;
    }

    public void setSide4_amber_time(int side4_amber_time) {
        this.side4_amber_time = side4_amber_time;
    }

    public int getSide5_amber_time() {
        return this.side5_amber_time;
    }

    public void setSide5_amber_time(int side5_amber_time) {
        this.side5_amber_time = side5_amber_time;
    }

    public int getJunction_id() {
        return this.junction_id;
    }

    public void setJunction_id(int junction_id) {
        this.junction_id = junction_id;
    }

    public String getJunction_name() {
        return this.junction_name;
    }

    public void setJunction_name(String junction_name) {
        this.junction_name = junction_name;
    }

    public String getMode() {
        return this.mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public int getOff_time_hour() {
        return this.off_time_hour;
    }

    public void setOff_time_hour(int off_time_hour) {
        this.off_time_hour = off_time_hour;
    }

    public int getOff_time_min() {
        return this.off_time_min;
    }

    public void setOff_time_min(int off_time_min) {
        this.off_time_min = off_time_min;
    }

    public int getOn_time_hour() {
        return this.on_time_hour;
    }

    public void setOn_time_hour(int on_time_hour) {
        this.on_time_hour = on_time_hour;
    }

    public int getOn_time_min() {
        return this.on_time_min;
    }

    public void setOn_time_min(int on_time_min) {
        this.on_time_min = on_time_min;
    }

    public int getPlan_no() {
        return this.plan_no;
    }

    public void setPlan_no(int plan_no) {
        this.plan_no = plan_no;
    }

    public int getSide1_green_time() {
        return this.side1_green_time;
    }

    public void setSide1_green_time(int side1_green_time) {
        this.side1_green_time = side1_green_time;
    }

    public int getSide2_green_time() {
        return this.side2_green_time;
    }

    public void setSide2_green_time(int side2_green_time) {
        this.side2_green_time = side2_green_time;
    }

    public int getSide3_green_time() {
        return this.side3_green_time;
    }

    public void setSide3_green_time(int side3_green_time) {
        this.side3_green_time = side3_green_time;
    }

    public int getSide4_green_time() {
        return this.side4_green_time;
    }

    public void setSide4_green_time(int side4_green_time) {
        this.side4_green_time = side4_green_time;
    }

    public int getSide5_green_time() {
        return this.side5_green_time;
    }

    public void setSide5_green_time(int side5_green_time) {
        this.side5_green_time = side5_green_time;
    }

    public boolean isResponseFromModemForRefresh() {
        return this.responseFromModemForRefresh;
    }

    public void setResponseFromModemForRefresh(boolean responseFromModemForRefresh) {
        this.responseFromModemForRefresh = responseFromModemForRefresh;
    }

    public boolean isResponseFromModemForClearance() {
        return this.responseFromModemForClearance;
    }

    public void setResponseFromModemForClearance(boolean responseFromModemForClearance) {
        this.responseFromModemForClearance = responseFromModemForClearance;
    }

    public String getOffTime() {
        return offTime;
    }

    public void setOffTime(String offTime) {
        this.offTime = offTime;
    }

    public String getOnTime() {
        return onTime;
    }

    public void setOnTime(String onTime) {
        this.onTime = onTime;
    }

}
