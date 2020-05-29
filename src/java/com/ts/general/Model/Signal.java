/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ts.general.Model;

/**
 *
 * @author Tarun
 */
public class Signal {

    private volatile String currentTime = "Not Set";

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }
}
