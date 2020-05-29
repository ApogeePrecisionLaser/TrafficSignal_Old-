/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ts.general.Controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Tarun
 */
public class ModemRequestFilter implements Filter {

    private FilterConfig filterConfig = null;
//    private boolean proceedExecution;   // It is NOT thread safe.

    public ModemRequestFilter() {
    }

    private boolean doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        boolean proceedExecution; // It is thread safe.

        String requester = request.getParameter("req");

        
        if (requester != null && requester.equals("modem")) {
            proceedExecution = false;
            System.out.println("\nRequested URL: " + req.getRequestURL());
            System.out.println("Client IP: " + req.getRemoteAddr() + " Port: " + req.getRemotePort());
            Set<Map.Entry<String, String[]>> reqParamsMap = req.getParameterMap().entrySet();
            if (reqParamsMap.size() > 1) {
                System.out.println("Following parameters were sent with the request...");
            }
            for (Map.Entry<String, String[]> entry : reqParamsMap) {
                System.out.print(entry.getKey() + ": ");
                String value = "";
                for (String val : entry.getValue()) {
                    value += val + ", ";
                }
                value = value.substring(0, value.lastIndexOf(", "));
                System.out.println(value);
            }
            Enumeration<String> headerNames = req.getHeaderNames();
            String headerName, headerValue;
            while (headerNames.hasMoreElements()) {
                headerName = headerNames.nextElement();
                headerValue = req.getHeader(headerName);
//                System.out.println(headerName + ": " + headerValue);
            }
            proceedExecution = false;
            request.getRequestDispatcher("ts_statusUpdaterCont").forward(request, response);
        } else {
            proceedExecution = true;
        }
        return proceedExecution;
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        boolean proceedExecution = doBeforeProcessing(request, response);
        if (proceedExecution) {
            chain.doFilter(request, response);
            doAfterProcessing(request, response);
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Init method for this filter 
     */
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }
}
