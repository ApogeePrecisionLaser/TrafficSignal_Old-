/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ts.listeners;

import com.ts.general.Model.Signal;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

/**
 * Web application lifecycle listener.
 * @author Tarun
 */
public class MyServletContextAttributeListener implements ServletContextAttributeListener {

    public void attributeAdded(ServletContextAttributeEvent event) {
        Signal signal;
        Object obj = event.getValue();
        if (obj instanceof Signal) {
            signal = (Signal) obj;
            System.out.println("\n\nattribute Added. Current Time: " + signal.getCurrentTime());
        }
        System.out.println("\n\nattribute Added. Name: " + event.getName() + " ObjHC: " + obj.hashCode() + " ObjAddr: " + obj);
    }

    public void attributeRemoved(ServletContextAttributeEvent event) {
        Signal signal;
        Object obj = event.getValue();
        if (obj instanceof Signal) {
            signal = (Signal) obj;
            System.out.println("\n\nattribute Removed. Current Time: " + signal.getCurrentTime());
        }
        System.out.println("\n\nattribute Removed. Name: " + event.getName() + " ObjHC: " + obj.hashCode() + " ObjAddr: " + obj);
    }

    public void attributeReplaced(ServletContextAttributeEvent event) {
        Signal signal;
        Object obj = event.getValue();
        if (obj instanceof Signal) {
            signal = (Signal) obj;
            System.out.println("\n\nattribute Replaced. Current Time: " + signal.getCurrentTime());
        }
        System.out.println("\n\nattribute Replaced. Name: " + event.getName() + " ObjHC: " + obj.hashCode() + " ObjAddr: " + obj);
    }
}
