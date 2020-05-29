package com.ts.webservice;

import com.ts.general.Controller.ModemResReadSaveController;
import com.ts.general.Controller.TS_StatusShowerController;
import com.ts.general.Controller.TS_StatusUpdaterController;
import com.ts.junction.tableClasses.BaldeobagInfo;
import com.ts.junction.tableClasses.BandariyaTirahaInfo;
import com.ts.junction.tableClasses.BloomChowkInfo;
import com.ts.junction.tableClasses.CollectorateInfo;
import com.ts.junction.tableClasses.DamohNakaInfo;
import com.ts.junction.tableClasses.DeendayalChowkInfo;
import com.ts.junction.tableClasses.GohalPurInfo;
import com.ts.junction.tableClasses.HighCourtInfo;
import com.ts.junction.tableClasses.History;
import com.ts.junction.tableClasses.Junction;
import com.ts.junction.tableClasses.KatangaInfo;
import com.ts.junction.tableClasses.LabourChowkInfo;
import com.ts.junction.tableClasses.MadanMahalInfo;
import com.ts.junction.tableClasses.PlanInfo;
import com.ts.junction.tableClasses.RanitalInfo;
import com.ts.junction.tableClasses.TaiyabaliChowkInfo;
import com.ts.junction.tableClasses.TeenPatti_Info;
import com.ts.junction.tableClasses.YatayatThanaInfo;
import com.ts.tcpServer.ClientResponderModel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.sql.DriverManager;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

public class ClientResponderWS extends HttpServlet
        implements Runnable {

    private InputStream inputStream;
    private OutputStream outputStream;
    private Socket clientSocket;
    private BufferedReader reader;
    private OutputStreamWriter outputStreamWriter;
    private String driverClass;
    private PrintWriter out;
    private String connectionString;
    private String db_userName;
    private Map<String, ClientResponderWS> map;
    private String db_userPassword;
    private Map<Integer, Junction> junctionList;
    private String ipAddress;
    private String ipPort;
    private String ipLoginTimstamp;
    private Boolean ipStatus;
    ClientResponderModel clientResponderModel = new ClientResponderModel();
    TS_StatusShowerController tsStatusShowerCont = new TS_StatusShowerController();
    private Junction junction;
    private int totalNoOfSides;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private ServletContext ctx;
    private ServerSocket serverSocket;
    private Async async;
    private Date date;
    private TS_StatusUpdaterController tsUpdaterCont;
    private Calendar cal;
    private static PlanInfo planInfoRefreshList = new PlanInfo();
    private static TeenPatti_Info teenPattiInfoRefreshList = new TeenPatti_Info();
    private static GohalPurInfo gohalPurInfoRefreshList = new GohalPurInfo();
    private static RanitalInfo ranitalInfoRefreshList = new RanitalInfo();
    private static DamohNakaInfo damohNakaInfoRefreshList = new DamohNakaInfo();
    private static KatangaInfo katangaInfoRefreshList = new KatangaInfo();
    private static YatayatThanaInfo yatayatThanaInfoRefreshList = new YatayatThanaInfo();
    private static BaldeobagInfo baldeobagInfoRefreshList = new BaldeobagInfo();
    private static DeendayalChowkInfo deendayalChowkInfoRefreshList = new DeendayalChowkInfo();
    private static HighCourtInfo highCourtInfoRefreshList = new HighCourtInfo();
    private static BloomChowkInfo bloomChowkInfoRefreshList = new BloomChowkInfo();
    private static MadanMahalInfo madanMahalInfoRefreshList = new MadanMahalInfo();
    private static BandariyaTirahaInfo bandariyaTirahaInfoRefreshList = new BandariyaTirahaInfo();
    private static LabourChowkInfo labourChowkInfoRefreshList = new LabourChowkInfo();
    private static TaiyabaliChowkInfo taiyabaliChowkInfoRefreshList = new TaiyabaliChowkInfo();
    private static CollectorateInfo collectorateInfoRefreshList = new CollectorateInfo();

    String currentTime;
    private String lastVisitedTime;
    DateFormat dateFormat;
    private boolean requestForClearance;
    private boolean requestForActivity;
    private boolean requestToUpdateProgramVersionNo;
    private boolean isJunctionLive;
    private int clearanceSide;
    private int activityNo;
    private int activitySide;
    private ModemResReadSaveController modemResReadSaveCont;
    private int noOfRequestReceived;
    public static byte[] arr1;
    public static byte[] arr13;
    public static byte[] arr11;
    public static byte[] arrkatanga;
    public static byte[] arryatayat;
    public static byte[] arrbaldeobag;
    public static byte[] arrbloomchowk;
    public static byte[] arrdeendayal;
    public static byte[] arrhighcourt;
    public static byte[] arrmadanmahal;
    public static byte[] arrgohalpur;
    public static byte[] arrbandariya;

    public ClientResponderWS(InputStream inputStream, OutputStream outputStream) {
        this.outputStream = outputStream;
        this.inputStream = inputStream;
        this.reader = new BufferedReader(new InputStreamReader(inputStream));
        this.outputStreamWriter = new OutputStreamWriter(outputStream);
        this.out = new PrintWriter(outputStream, true);
        this.async = new Async();
        this.modemResReadSaveCont = new ModemResReadSaveController();
        this.tsUpdaterCont = new TS_StatusUpdaterController();
    }

    public ClientResponderWS() {
    }

    public void run() {
        this.clientResponderModel.setDriverClass(this.driverClass);
        this.clientResponderModel.setConnectionString(this.connectionString);
        this.clientResponderModel.setDb_userName(this.db_userName);
        this.clientResponderModel.setDb_userPasswrod(this.db_userPassword);
        this.clientResponderModel.setConnection();

        this.async.setClientSocket(this.clientSocket);
        this.async.setTcpServer(this.serverSocket);
        this.async.setDriverClass(this.driverClass);
        this.async.setConnectionString(this.connectionString);
        this.async.setDb_userName(this.db_userName);
        this.async.setDb_userPasswrod(this.db_userPassword);
        this.ctx.setAttribute("planInfoRefreshList", this.planInfoRefreshList);
        this.tsUpdaterCont.setCtx(this.ctx);

        System.out.println("Reading data from Client...");
        try {
            this.dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            this.cal = Calendar.getInstance();
            this.currentTime = this.dateFormat.format(this.cal.getTime());
            this.lastVisitedTime = this.currentTime;
            System.out.println("run:cal -lastVisitedTime--" + this.lastVisitedTime);
            this.junction.setLastVisitedTime(this.lastVisitedTime);
            //this.junction.setClientResponder(this);
            this.async.setJunction(this.junction);
            this.async.setPlanInfoRefreshList(this.planInfoRefreshList);
            this.async.setTeenPattiInfoRefreshList(this.teenPattiInfoRefreshList);
            this.async.setGohalPurInfoRefreshList(this.gohalPurInfoRefreshList);
            this.async.setRanitalInfoRefreshList(this.ranitalInfoRefreshList);
            this.async.setDamohNakaInfoRefreshList(this.damohNakaInfoRefreshList);
            this.async.setYatayatThanaInfoRefreshList(this.yatayatThanaInfoRefreshList);
            this.async.setKatangaInfoRefreshList(this.katangaInfoRefreshList);
            this.async.setBaldeobagInfoRefreshList(this.baldeobagInfoRefreshList);
            this.async.setDeendayalChowkInfoRefreshList(this.deendayalChowkInfoRefreshList);
            this.async.setHighCourtInfoRefreshList(this.highCourtInfoRefreshList);
            this.async.setBloomChowkInfoRefreshList(this.bloomChowkInfoRefreshList);
            this.async.setMadanMahalInfoRefreshList(this.madanMahalInfoRefreshList);
            this.async.setBandariyaTirahaInfoRefreshList(this.bandariyaTirahaInfoRefreshList);
            this.ctx.setAttribute("planInfoRefreshList", this.planInfoRefreshList);
            this.ctx.setAttribute("teenPattiInfoRefreshList", this.teenPattiInfoRefreshList);
            this.ctx.setAttribute("gohalPurInfoRefreshList", this.gohalPurInfoRefreshList);
            this.ctx.setAttribute("ranitalInfoRefreshList", this.ranitalInfoRefreshList);
            this.ctx.setAttribute("damohNakaInfoRefreshList", this.damohNakaInfoRefreshList);
            this.ctx.setAttribute("yatayatThanaInfoRefreshList", this.yatayatThanaInfoRefreshList);
            this.ctx.setAttribute("katangaInfoRefreshList", this.katangaInfoRefreshList);
            this.ctx.setAttribute("baldeobagInfoRefreshList", this.baldeobagInfoRefreshList);
            this.ctx.setAttribute("deendayalChowkInfoRefreshList", this.deendayalChowkInfoRefreshList);
            this.ctx.setAttribute("highCourtInfoRefreshList", this.highCourtInfoRefreshList);
            this.ctx.setAttribute("bloomChowkInfoRefreshList", this.bloomChowkInfoRefreshList);
            this.ctx.setAttribute("madanMahalInfoRefreshList", this.madanMahalInfoRefreshList);
            this.ctx.setAttribute("bandariyaTirahaInfoRefreshList", this.bandariyaTirahaInfoRefreshList);
            this.tsUpdaterCont.setCtx(this.ctx);
            this.ctx.setAttribute("junction", this.junction);
            this.modemResReadSaveCont.setCtx(this.ctx);
            System.out.println("clientSocket closed Status #######" + this.clientSocket.isClosed());
            System.out.println("client responder obj=" + this.junction.getClientResponder());

            while (readClientResponse(new byte[0]).isEmpty()) {
                try {
                    synchronized (this) {
                        wait(2000L);
                    }
                } catch (InterruptedException interruptedEx) {
                    System.out.println("ClientResponseReader run() Error: " + interruptedEx);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientResponderWS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public void setConnection(Connection con) {
//        this.clientResponderModel.setConnection(con);
//    }
    public void setConnection() {
        try {
            System.out.println("hii");
            Class.forName("com.mysql.jdbc.Driver");
            this.clientResponderModel.setConnection(DriverManager.getConnection("jdbc:mysql://localhost:3306/traffic_signals", "jpss_2", "jpss_1277"));
        } catch (Exception e) {
            System.out.println("ReadMailModel setConnection() Error: " + e);
        }
    }

    public static void main(String... args) {
        //byte[] receivedBytes = {126,126,0,28,1,0,0,2,1,48,49,51,55,55,55,48,48,48,54,48,57,56,51,50,48,48,56,48,50,55,53,57,87,125,125};// function 1
        //byte[] receivedBytes = {126,126,0,9,1,16,0,2,9,0,0,0,0,0,-13,125,125};// function 9
        //byte[] receivedBytes = { 126, 126, 0, 4, 11, 1, 1, 3, -17, 125, 125};//{126,126,0,46,1,16,1,1,2,90,-49,90,-38,90,-55,0,0,0,23,0,0,0,0,0,1,0,1,0,3,0,3,0,0,7,-6,1,59,59,59,1,1,18,42,5,31,11,34,12,5,16,-111,125};// fun 2
        //byte[] receivedBytes = {126, 126, 1, 19, 32, 11, 4, 8, 35, 35, 35, 35, -125, -120, -103, 2, 3, 1, 14, 14, 15, 15, 15, 107, 125, 125};
        byte[] receivedBytes = {126, 126, 1, 19, 2, 10, 4, 8, 35, 35, 35, 35, -120, -125, -103, 2, 3, 1, 14, 14, 15, 15, 15, 107, 125, 125};
        ClientResponderWS obj = new ClientResponderWS();
        obj.setConnection();
        try {
            obj.setJunction(new Junction());
            obj.setAsync(new Async());
            String res = obj.JunctionRefreshFunction(receivedBytes, 4, false);
            obj.sendResponse(res);
            obj.closeConnection();
        } catch (Exception ex) {
            System.out.println("ERROR : in response " + ex);
        }
        //System.out.println(res);
    }

    public void closeConnection() {
        this.clientResponderModel.closeConnection();
    }

    public String readClientResponse(byte[] receivedBytes)
            throws IOException {
        boolean isConnectionClosed = false;
        String currentVisitedTime = null;
        String res = null;
        try {
            //byte[] bytes = new byte[1000];


            //int read = this.inputStream.read(bytes);
            byte[] bytes = receivedBytes;
            int read = bytes.length;
            //while (read > 1) {
            noOfRequestReceived++;
            System.out.println("number of bytes actualy read: " + read);
            System.out.println("received Request No: " + noOfRequestReceived);
            this.dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            this.cal = Calendar.getInstance();
            this.currentTime = this.dateFormat.format(this.cal.getTime());
            this.lastVisitedTime = this.currentTime;
            //System.out.println("clientSocket closed Status #######" + this.clientSocket.isClosed());
            System.out.println("cal -currentTime--" + this.currentTime);
            this.junction.setLastVisitedTime(this.currentTime);
            this.async.setJunction(this.junction);
            int firstStartDataPosition = 0;
            int minimumDatabytes = 6;
            int initialflag1 = 0;
            int endflag1 = 0;
            int initialflag2 = 0;
            int endflag2 = 0;
            try {
                for (int i = 0; i <= (bytes.length - minimumDatabytes) && (initialflag1 != 1 || initialflag2 != 1); i++) {
                    if (bytes[i] == 126 && (initialflag1 != 1 || initialflag2 != 1)) {
                        if (bytes[i + 1] == 126) {
                            firstStartDataPosition = i + 4;
                            initialflag2 = 1;
                        } else {
                            firstStartDataPosition = i + 3;
                            initialflag1 = 1;
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("ClientResponseReader readClientResponse initial delimiter Exception: " + e);
            }
            if (initialflag1 == 1 || initialflag2 == 1) {
                byte dataSize[] = new byte[2];
                dataSize[0] = 0;//bytes[firstStartDataPosition - 2];
                dataSize[1] = bytes[firstStartDataPosition - 1];
                int receivedDataSize = new BigInteger(dataSize).intValue();
                int function_no = bytes[firstStartDataPosition + 3];

                if (matchDataLength(receivedDataSize, function_no)) {

                    if (bytes[firstStartDataPosition + receivedDataSize + 1] == 125) {

                       // if (validateDataByCrc(bytes, firstStartDataPosition, receivedDataSize)) {
                            boolean testRequestFromJunction = false;
                            int junctionID = (int) bytes[firstStartDataPosition];
                            int program_version_no = (int) bytes[firstStartDataPosition + 1];
                            int fileNo = (int) bytes[firstStartDataPosition + 2];
                            int functionNo = (int) bytes[firstStartDataPosition + 3];
                            currentVisitedTime = this.currentTime;
                            System.out.println("readClientResponse----currentVisitedTime--- " + currentVisitedTime + ", " + "lastVisitedTime--- " + this.lastVisitedTime);

                            int calculateLastTime = calculateTimeInSeconds(this.lastVisitedTime);
                            int calculateCurrentTime = calculateTimeInSeconds(currentVisitedTime);
                            int calculatedDifference = calculateCurrentTime - calculateLastTime;

                            this.junction.setLastVisitedTime(currentVisitedTime);
                            this.junction.setJunction_id(junctionID);
                            this.junction.setProgram_version_no(program_version_no);
                            this.junction.setFile_no(fileNo);
                            //this.junction.setClientResponder(this);
                            this.async.setJunction(this.junction);
                            this.async.setPlanInfoRefreshList(this.planInfoRefreshList);
                            this.ctx.setAttribute("junction", this.junction);
                            this.modemResReadSaveCont.setCtx(this.ctx);
                            History historyList = new History();
                            historyList.setJunction_id(junctionID);
                            historyList.setProgram_version_no(program_version_no);
                            historyList.setIp_address(ipAddress);
                            historyList.setPort(Integer.parseInt(ipPort));
                            historyList.setStatus(false);
                            if (!clientResponderModel.checkJunctionIfLive(ipAddress, ipPort, junctionID, program_version_no)) {
                                //System.out.println("Async :serverSocket.isClosed()------" + closedStatus);
                                this.clientResponderModel.insertRecord(historyList);
                                //j = 1;
                            }

                            if (functionNo != 1 ? clientResponderModel.checkJunctionId(junctionID) ? true : functionNo == 9 ? true : false : true) {

                                if (!clientResponderModel.checkJunctionIfLive(ipAddress, ipPort, junctionID, program_version_no)) {
                                 if(functionNo!=4){
                                    if (checkIfTestRequest(bytes, firstStartDataPosition)) {
                                        setRequestForActivity(false);
                                        setRequestForClearance(false);
                                        testRequestFromJunction = true;

                                    }
                                    }
                                    if (clientResponderModel.checkJunctionLastSynchronisation(ipAddress, ipPort, junctionID, program_version_no, testRequestFromJunction)) {
                                        confirmRegistration(junctionID, program_version_no, testRequestFromJunction);
                                    }
                                } else {
                                    isJunctionLive = true;
                                }
                                if (functionNo == 8 ? isJunctionLive : true) {
                                    if (functionNo == 1) {
                                        if (this.clientResponderModel.isValidJunction(junctionID, program_version_no)) {

                                            res = doRegistration(bytes, firstStartDataPosition);
                                            try {
                                                // System.out.println("register status response" + res);
                                            } catch (Exception ex) {
                                                System.out.println("Error - " + ex);
                                            }
                                            sendResponse(res);
                                        } else {
                                            res = checkRegistration(bytes, firstStartDataPosition);
                                            // System.out.println("register modem response" + res);

                                            sendResponse(res);
                                        }

                                    } else if (functionNo == 2) {
                                        res = slaveFunction(bytes, firstStartDataPosition);
                                        //System.out.println(res);

                                        sendResponse(res);
                                    } else if (functionNo == 3) {

                                        res = JunctionSideNames(bytes, firstStartDataPosition);
                                        //System.out.println(res);

                                        sendJunctionResponse(res);
                                    } else if (functionNo == 4) {
                                        res = planFunction(bytes, firstStartDataPosition);
                                        //System.out.println(res);

                                        sendResponse(res);
                                    } else if (functionNo == 5) {
                                        res = SunriseSunsetDetails(bytes, firstStartDataPosition);
                                        //System.out.println(res);

                                        sendResponse(res);

                                    } else if (functionNo == 8) {

                                        res = JunctionRefreshFunction(bytes, firstStartDataPosition, testRequestFromJunction);

                                        //System.out.println(res);

                                        //sendResponse(res);

                                    } else if (functionNo == 9) {
                                        res = getJunctionTime(bytes, firstStartDataPosition);
                                        //System.out.println(res);
                                        sendResponse(res);

                                    } else {
                                        System.out.println("@@@@@@@@@@@@@@@@@@@@@@  Sent Error: Invalid function no  @@@@@@@@@@@@@@@@@@@@@@");
                                        sendErrorResponse(6);
                                    }
                                } else {
                                    System.out.println("@@@@@@@@@@@@@@@@@@@@@@ Sent Error: Junction is not live  @@@@@@@@@@@@@@@@@@@@@@");
                                    sendErrorResponse(10);
                                }
                            } else {
                                System.out.println("@@@@@@@@@@@@@@@@@@@@@@  Sent Error: junction_id not found  @@@@@@@@@@@@@@@@@@@@@@");
                                sendErrorResponse(5);
                            }
//                        }
//                        else {
//                            System.out.println("@@@@@@@@@@@@@@@@@@@@@@  Sent Error: Crc not matched @@@@@@@@@@@@@@@@@@@@@@");
//                            sendErrorResponse(4);
//                        }
                    } else {
                        if (initialflag2 == 1) {
                            System.out.println("@@@@@@@@@@@@@@@@@@@@@@ Sent Error: Last delimiters not found  @@@@@@@@@@@@@@@@@@@@@@");
                            sendErrorResponse(3);
                        } else {
                            System.out.println("@@@@@@@@@@@@@@@@@@@@@@  Error: Delimiters not found  @@@@@@@@@@@@@@@@@@@@@@");
                            // sendResponse("126 126 126 126 16 2 4 8 0 0 0 0 125 125");
                        }
                    }
                } else {
                    if (initialflag2 == 1) {
                        System.out.println("@@@@@@@@@@@@@@@@@@@@@@ Sent Error: Data length not matched  @@@@@@@@@@@@@@@@@@@@@@");
                        sendErrorResponse(2);
                    } else {
                        System.out.println("@@@@@@@@@@@@@@@@@@@@@@  Error: corrupted data according to data length and single starting byte  @@@@@@@@@@@@@@@@@@@@@@");
                        // sendResponse("126 126 126 126 16 2 4 8 0 0 0 0 125 125");
                    }
                }
            } else {
                try {
                    for (int i = minimumDatabytes; i < (bytes.length) - 1 && endflag2 != 1; i++) {
                        if (bytes[i] == 125) {
                            if (bytes[i + 1] == 125) {
                                endflag2 = 1;
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println("ClientResponseReader readClientResponse last delimiter Exception: " + e);
                }
                if (endflag2 == 1) {
                    System.out.println("@@@@@@@@@@@@@@@@@@@@@@ Sent Error: Starting delimiters not found  @@@@@@@@@@@@@@@@@@@@@@");
                    sendErrorResponse(1);
                } else {
                    System.out.println("@@@@@@@@@@@@@@@@@@@@@@ Delimiters not found  @@@@@@@@@@@@@@@@@@@@@@");
                    // sendResponse("126 126 126 126 16 2 4 8 0 0 0 0 125 125");
                }


            }
            //read = this.inputStream.read(bytes);
            //}
        } catch (IOException ioEx) {
            this.clientSocket.close();
            isConnectionClosed = true;
            System.out.println("ClientResponseReader readClientResponse() Error: " + ioEx);
        }
        //setConnectedIpList();
        return res;//!isConnectionClosed;
    }

    public boolean validateDataByCrc(byte[] bytes, int firstStartDataPosition, int receivedDataSize) {

        int calculatedCrc = 0;
        int receivedCrc = 0;
        int dSum = 0;
        try {
            for (int i = firstStartDataPosition; i < (firstStartDataPosition + receivedDataSize); i++) {
                int d = bytes[i];
                dSum += d;
            }
            calculatedCrc = (0xFF - (dSum & 0xFF)); // get lowest 8-bit and subtract lowest 8-bit from 255
            receivedCrc = bytes[firstStartDataPosition + receivedDataSize] & 0xFF;
            System.out.println("Receivd crc: " + receivedCrc + "  and calculatedCrc: " + calculatedCrc);
        } catch (Exception e) {
            System.out.println("Exception in ClientResponder validateData: " + e);
        }
        return (calculatedCrc == receivedCrc);
    }

    public boolean matchDataLength(int dataLength, int functionNo) {
        if (functionNo == 1) {
            return (dataLength == 19 || dataLength == 22 || dataLength == 38);
        } else if (functionNo == 2) {
            return (dataLength == 12 || dataLength == 13);
        } else if (functionNo == 3) {
            return (dataLength == 4);
        } else if (functionNo == 4) {
            return (dataLength == 5 || dataLength == 20);
        } else if (functionNo == 5) {
            return (dataLength == 5);
        } else if (functionNo == 6) {
            return (dataLength == 5);
        } else if (functionNo == 7) {
            return (dataLength == 5);
        } else if (functionNo == 8) {
            return (dataLength == 19);
            //  return (dataLength == 31);
        } else if (functionNo == 9) {
            return (dataLength == 9);
        } else {
            return false;
        }
    }

    public boolean checkIfTestRequest(byte[] dataToProcess, int firstStartDataPosition) {
        boolean result = false;

        int side1Time = dataToProcess[firstStartDataPosition + 4];
        int side2Time = dataToProcess[firstStartDataPosition + 5];
        int side3Time = dataToProcess[firstStartDataPosition + 6];
        int side4Time = dataToProcess[firstStartDataPosition + 7];

        int unsignedSide13 = dataToProcess[firstStartDataPosition + 8];
        int unsignedSide24 = dataToProcess[firstStartDataPosition + 9];

        int unsignedMiscByte = dataToProcess[firstStartDataPosition + 10];

        if (side1Time == 1 && side2Time == 1 && side3Time == 1 && side4Time == 1
                && unsignedSide13 == 1 && unsignedSide24 == 1 && unsignedMiscByte == 1) {
            result = true;
        }

        return result;
    }

    public String checkRegistration(byte[] dataToProcess, int firstStartDataPosition) {
        String imeiNo = "";
        String response = "";
        String extraBytes = "";
        try {
            int firstStartDelimiter = dataToProcess[firstStartDataPosition - 4];
            int secStartDelimiter = dataToProcess[firstStartDataPosition - 3];
            int dataSize1 = dataToProcess[firstStartDataPosition - 2];
            int dataSize2 = dataToProcess[firstStartDataPosition - 1];
            int junctionID = dataToProcess[firstStartDataPosition];
            int program_version_no = dataToProcess[firstStartDataPosition + 1];
            int fileNo = dataToProcess[firstStartDataPosition + 2];
            int functionNo = dataToProcess[firstStartDataPosition + 3];

            for (int i = (firstStartDataPosition + 4); i <= (firstStartDataPosition + 18); i++) {
                char ch = (char) dataToProcess[i];
                imeiNo = imeiNo + ch;
            }

//            for (int i = (firstStartDataPosition + 19); i <= (firstStartDataPosition + 30); i++) {
//                //char ch = (char) dataToProcess[i];
//                if (extraBytes == "") {
//                    extraBytes = extraBytes + dataToProcess[i];
//                } else {
//                    extraBytes = extraBytes + " " + dataToProcess[i];
//                }
//
//            }

            //int crc = dataToProcess[firstStartDataPosition + 19] & 0xFF;
            int crc = dataToProcess[firstStartDataPosition + 19] & 0xFF;
            //int firstLastDelimiter = dataToProcess[firstStartDataPosition + 20];
            int firstLastDelimiter = dataToProcess[firstStartDataPosition + 20];
            //int secLastDelimiter = dataToProcess[firstStartDataPosition + 21];
            int secLastDelimiter = firstLastDelimiter;

            int junctIDFromDB = this.clientResponderModel.getJunctionID(imeiNo);
            int programVersionNoFromDB = junctIDFromDB == 0 ? 0 : this.clientResponderModel.getProgramVersionNo(junctIDFromDB);

            int registrationStatus = junctIDFromDB == 0 ? 0 : this.clientResponderModel.updateJunctionTransferredStatus(junctionID, program_version_no, "NO") ? 0 : 0;

            int noOfPlans = this.clientResponderModel.getNoOfPlans(junctIDFromDB, programVersionNoFromDB);
            int noOfSides = this.clientResponderModel.getNoOfSides(junctIDFromDB, programVersionNoFromDB);
            int pedestrianTime = this.clientResponderModel.getPedestrianTime(junctIDFromDB, programVersionNoFromDB);
            int fileNoFromDB = this.clientResponderModel.getFileNo(junctIDFromDB, programVersionNoFromDB);

            if (fileNo != fileNoFromDB) {
                this.clientResponderModel.updateFileNo(junctIDFromDB, fileNo, programVersionNoFromDB);
            }
            fileNoFromDB = this.clientResponderModel.getFileNo(junctIDFromDB, programVersionNoFromDB);

            System.out.println("register modem request--- FirstStartDelimliter=" + firstStartDelimiter + " SecondStartDelimliter=" + secStartDelimiter + " dataSize1=" + dataSize1 + " dataSize2=" + dataSize2 + " junctionID=" + junctionID + " program_version_no=" + program_version_no + " file_no=" + fileNo + " functionNo=" + functionNo + " imeiNo= " + imeiNo + " crc=" + crc + " firstLastDelimiter= " + firstLastDelimiter + " secLastDelimiter= " + secLastDelimiter);
            response = secStartDelimiter + " " + secStartDelimiter + " " + secStartDelimiter + " " + secStartDelimiter + " " + junctIDFromDB + " " + programVersionNoFromDB + " " + fileNoFromDB + " " + functionNo + " " + registrationStatus + " " + noOfSides + " " + noOfPlans + " " + pedestrianTime +  " " + firstLastDelimiter + " " + firstLastDelimiter;
            System.out.println(response);
        } catch (Exception e) {
            System.out.println("ClientResponder checkRegistration exception: " + e);
        }
        return response;
    }



    public String doRegistration(byte[] dataToProcess, int firstStartDataPosition) {
        String response = null;
        String imeiNo = "";
        String extraByte = "";
        try {
            int firstStartDelimiter = dataToProcess[firstStartDataPosition - 4];
            int secStartDelimiter = dataToProcess[firstStartDataPosition - 3];
            int dataSize1 = dataToProcess[firstStartDataPosition - 2];
            int dataSize2 = dataToProcess[firstStartDataPosition - 1];
            int junctionID = dataToProcess[firstStartDataPosition];
            int program_version_no = dataToProcess[firstStartDataPosition + 1];
            int fileNo = dataToProcess[firstStartDataPosition + 2];
            int functionNo = dataToProcess[firstStartDataPosition + 3];
            for (int i = (firstStartDataPosition + 4); i <= (firstStartDataPosition + 18); i++) {
                char ch = (char) dataToProcess[i];
                imeiNo = imeiNo + ch;
            }
            int noOfSides = dataToProcess[firstStartDataPosition + 19];
            int noOfPlans = dataToProcess[firstStartDataPosition + 20];
            int pedestrianTime = dataToProcess[firstStartDataPosition + 21];

//            for (int i = (firstStartDataPosition + 22); i <= (firstStartDataPosition + 33); i++) {
//                if (extraByte == "") {
//                    extraByte = extraByte + dataToProcess[i];
//                } else {
//                    extraByte = extraByte + " " + dataToProcess[i];
//                }
//            }
            int crc = dataToProcess[firstStartDataPosition + 22] & 0xFF;
            int firstLastDelimiter = dataToProcess[firstStartDataPosition + 23];
            int secLastDelimiter = dataToProcess[firstStartDataPosition + 24];

            int programVersionNoFromDB = this.clientResponderModel.getProgramVersionNo(imeiNo, junctionID);
            int fileNoFromDB = this.clientResponderModel.getFileNo(junctionID, programVersionNoFromDB);
            int noOfPlansFromDB = this.clientResponderModel.getNoOfPlans(junctionID, programVersionNoFromDB);
            int noOfSidesFromDB = this.clientResponderModel.getNoOfSides(junctionID, programVersionNoFromDB);
            int pedestrianTimeFromDB = this.clientResponderModel.getPedestrianTime(junctionID, programVersionNoFromDB);

            if (fileNo != fileNoFromDB) {
                this.clientResponderModel.updateFileNo(junctionID, fileNo, programVersionNoFromDB);
            }
            fileNoFromDB = this.clientResponderModel.getFileNo(junctionID, programVersionNoFromDB);
            Junction juncID = (Junction) this.junctionList.get(Integer.valueOf(junctionID));
            int registrationStatus = 0;
            if (noOfPlans == noOfPlansFromDB && noOfSides == noOfSidesFromDB && pedestrianTime == pedestrianTimeFromDB) {
                if (program_version_no != 127 && program_version_no != 0) {
                    if (program_version_no != programVersionNoFromDB) {
                        registrationStatus = 1;
                    } else {
                        if (!this.clientResponderModel.checkJunctionIfLive(ipAddress, ipPort, junctionID, program_version_no)) {
                            confirmRegistration(junctionID, program_version_no, false);
                        }
                        registrationStatus = 2;
                        juncID.setRegistration_status(true);

                    }
                }
            } else {
                registrationStatus = 0;
            }

            System.out.println("acknowledgement request--- " + firstStartDelimiter + " " + secStartDelimiter + " " + dataSize1 + " " + dataSize2 + " " + junctionID + " " + program_version_no + " " + fileNo + " " + functionNo + " " + imeiNo + " " + noOfPlans + " " + noOfSides + " " + pedestrianTime + " " + crc + " " + firstLastDelimiter + " " + secLastDelimiter);

            response = secStartDelimiter + " " + secStartDelimiter + " " + secStartDelimiter + " " + secStartDelimiter + " " + junctionID + " " + programVersionNoFromDB + " " + fileNoFromDB + " " + functionNo + " " + registrationStatus + " " + noOfSidesFromDB + " " + noOfPlansFromDB + " " + pedestrianTimeFromDB + " " + firstLastDelimiter + " " + firstLastDelimiter;
        } catch (Exception e) {
            System.out.println("ClientResponder doRegistration exception: " + e);
        }

        //System.out.println("Response: " + response);
        return response;
    }
    private boolean confirmRegistration(int junctionID, int program_version_no, boolean testRequestFromJunction) {
        boolean result = false;
        try {
            boolean updateJunctionTransferredStatus = testRequestFromJunction ? true : this.clientResponderModel.updateJunctionTransferredStatus(junctionID, program_version_no, "YES");
            if (updateJunctionTransferredStatus == true) {
                setIsJunctionLive(true);
                //new Thread(this.async).start();
                async.setIpAddress(ipAddress);
                async.setIpPort(ipPort);
                async.setClientResponderModel(clientResponderModel);
                this.async.run();
                System.out.println("*********************ASYNC STARTED******************");
                result = true;
            }
        } catch (Exception e) {
            result = false;
            setIsJunctionLive(false);
            System.out.println("Error - clientResponder - " + e);
        }
        return result;
    }

    private String planFunction(byte[] dataToProcess, int firstStartDataPosition) {
        String responseVal = null;
        int firstStartDelimiter = dataToProcess[firstStartDataPosition - 4];
        int secStartDelimiter = dataToProcess[firstStartDataPosition - 3];
        int dataSize1 = dataToProcess[firstStartDataPosition - 2];
        int dataSize2 = dataToProcess[firstStartDataPosition - 1];
        int junctionID = dataToProcess[firstStartDataPosition];
        int program_version_no = dataToProcess[firstStartDataPosition + 1];
        int fileNo = dataToProcess[firstStartDataPosition + 2];
        int functionNo = dataToProcess[firstStartDataPosition + 3];
        int planNo = 0;
        int firstLastDelimiter = 0;
        int secLastDelimiter = 0;
        int crc = 0;
        int fileNoFromDB = this.clientResponderModel.getFileNo(junctionID, program_version_no);
        if (fileNo != fileNoFromDB) {
            this.clientResponderModel.updateFileNo(junctionID, fileNo, program_version_no);
        }
        fileNoFromDB = this.clientResponderModel.getFileNo(junctionID, program_version_no);
        int programVersionNoFromDB = this.clientResponderModel.getProgramVersionNo(junctionID);
        if ((dataToProcess[firstStartDataPosition + 6] == 125) && (dataToProcess[firstStartDataPosition + 4] != 127)) {
            planNo = dataToProcess[firstStartDataPosition + 4];
            crc = dataToProcess[firstStartDataPosition + 5] & 0xFF;
            firstLastDelimiter = dataToProcess[firstStartDataPosition + 6];
            secLastDelimiter = firstLastDelimiter;
            System.out.println("plan function first request -- " + firstStartDelimiter + " " + secStartDelimiter + " " + dataSize1 + " " + dataSize2 + " " + junctionID + " " + program_version_no + " " + fileNo + " " + functionNo + " " + planNo + " " + crc + " " + firstLastDelimiter + " " + secLastDelimiter);
            try {
                String strPlanMode = this.clientResponderModel.getPlanMode(planNo, junctionID, program_version_no);
                String planMode = "0";
                if (strPlanMode.equals("Blinker")) {
                    planMode = "1";
                } else {
                    if (strPlanMode.equals("Signal")) {
                        if (!this.clientResponderModel.isPedestrian(junctionID, program_version_no)) {
                            planMode = "2";
                        } else {
                            planMode = "3";
                        }
                    }
                }
                List planTimings = this.clientResponderModel.getPlanTiming(planNo, junctionID);
                int onTimeHrFromDB = ((Integer) planTimings.get(0)).intValue();
                int onTimeMinFromDB = ((Integer) planTimings.get(1)).intValue();
                int offTimeHrFromDB = ((Integer) planTimings.get(2)).intValue();
                int offTimeMinFromDB = ((Integer) planTimings.get(3)).intValue();

                int noOfSides = this.clientResponderModel.getNoOfSides(junctionID, program_version_no);
                List sideGreenlist = new ArrayList();
                List sideAmberlist = new ArrayList();
                for (int i = 1; i <= 5; i++) {
                    int sideGreenTime = this.clientResponderModel.getPlanGreenTime(i, planNo, junctionID, program_version_no);
                    int sideAmberTime = this.clientResponderModel.getPlanAmberTime(i, planNo, junctionID, program_version_no);
                    sideGreenlist.add(Integer.valueOf(sideGreenTime));
                    sideAmberlist.add(Integer.valueOf(sideAmberTime));
                }
                responseVal = secStartDelimiter + " " + secStartDelimiter + " " + secStartDelimiter + " " + secStartDelimiter + " " + junctionID + " " + programVersionNoFromDB + " " + fileNoFromDB + " " + functionNo + " " + planNo + " " + onTimeHrFromDB + " " + onTimeMinFromDB + " " + offTimeHrFromDB + " " + offTimeMinFromDB;
                Iterator itr = sideGreenlist.iterator();
                while (itr.hasNext()) {
                    responseVal = responseVal + " " + (Integer) itr.next();
                }

                itr = null;
                itr = sideAmberlist.iterator();
                while (itr.hasNext()) {
                    responseVal = responseVal + " " + (Integer) itr.next();
                }
                responseVal = responseVal + " " + planMode + " " + firstLastDelimiter + " " + firstLastDelimiter;
            } catch (Exception e) {
                System.out.println("Error - clientResponder - " + e);
            }
        } else if (dataToProcess[firstStartDataPosition + 6] != 125) {
            planNo = dataToProcess[firstStartDataPosition + 4];
            int onTimeHR = dataToProcess[firstStartDataPosition + 5];
            int onTimeMin = dataToProcess[firstStartDataPosition + 6];
            int offTimeHr = dataToProcess[firstStartDataPosition + 7];
            int offTimeMin = dataToProcess[firstStartDataPosition + 8];

            int side1GT = dataToProcess[firstStartDataPosition + 9];
            int side2GT = dataToProcess[firstStartDataPosition + 10];
            int side3GT = dataToProcess[firstStartDataPosition + 11];
            int side4GT = dataToProcess[firstStartDataPosition + 12];
            int side5GT = dataToProcess[firstStartDataPosition + 13];

            int side1AT = dataToProcess[firstStartDataPosition + 14];
            int side2AT = dataToProcess[firstStartDataPosition + 15];
            int side3AT = dataToProcess[firstStartDataPosition + 16];
            int side4AT = dataToProcess[firstStartDataPosition + 17];
            int side5AT = dataToProcess[firstStartDataPosition + 18];

            int mode = dataToProcess[firstStartDataPosition + 19];
            crc = dataToProcess[firstStartDataPosition + 20] & 0xFF;
            firstLastDelimiter = dataToProcess[firstStartDataPosition + 21];
            secLastDelimiter = firstLastDelimiter;
            System.out.println("plan function first request -- " + firstStartDelimiter + " " + secStartDelimiter + " " + dataSize1 + " " + dataSize2 + " " + junctionID + " " + program_version_no + " " + fileNo + " " + functionNo
                    + " " + planNo + " " + onTimeHR + " " + onTimeMin + " " + offTimeHr + " " + offTimeMin
                    + " " + side1GT + " " + side2GT + " " + side3GT + " " + side4GT + " " + side5GT
                    + " " + side1AT + " " + side2AT + " " + side3AT + " " + side4AT + " " + side5AT + " " + crc + " " + firstLastDelimiter + " " + secLastDelimiter);
            try {
                String strPlanMode = this.clientResponderModel.getPlanMode(planNo, junctionID, program_version_no);
                String planMode = "0";
                if (strPlanMode.equals("Blinker")) {
                    planMode = "1";
                } else {
                    if (strPlanMode.equals("Signal")) {
                        if (!this.clientResponderModel.isPedestrian(junctionID, program_version_no)) {
                            planMode = "2";
                        } else {
                            planMode = "3";
                        }
                    }
                }
                List planTimings = this.clientResponderModel.getPlanTiming(planNo, junctionID);
                int onTimeHrFromDB = ((Integer) planTimings.get(0)).intValue();
                int onTimeMinFromDB = ((Integer) planTimings.get(1)).intValue();
                int offTimeHrFromDB = ((Integer) planTimings.get(2)).intValue();
                int offTimeMinFromDB = ((Integer) planTimings.get(3)).intValue();

                int noOfSides = this.clientResponderModel.getNoOfSides(junctionID, program_version_no);
                List sideGreenlist = new ArrayList();
                List sideAmberlist = new ArrayList();
                for (int i = 1; i <= 5; i++) {
                    int sideGreenTime = this.clientResponderModel.getPlanGreenTime(i, planNo, junctionID, program_version_no);
                    int sideAmberTime = this.clientResponderModel.getPlanAmberTime(i, planNo, junctionID, program_version_no);
                    sideGreenlist.add(Integer.valueOf(sideGreenTime));
                    sideAmberlist.add(Integer.valueOf(sideAmberTime));
                }
                Iterator itr = sideGreenlist.iterator();
                String GT = "";
                while (itr.hasNext()) {
                    GT = GT + " " + (Integer) itr.next();
                }
                String[] GTsplit = GT.split(" ", GT.length());
                itr = null;
                itr = sideAmberlist.iterator();
                String AT = "";
                while (itr.hasNext()) {
                    AT = AT + " " + (Integer) itr.next();
                }
                int planStatus = 0;
                String[] ATsplit = AT.split(" ", AT.length());
                if ((onTimeHR == onTimeHrFromDB) && (onTimeMin == onTimeMinFromDB) && (offTimeHr == offTimeHrFromDB)
                        && (offTimeMin == offTimeMinFromDB) && (side1GT == Integer.parseInt(GTsplit[1]))
                        && (side2GT == Integer.parseInt(GTsplit[2])) && (side3GT == Integer.parseInt(GTsplit[3]))
                        && (side4GT == Integer.parseInt(GTsplit[4])) && (side5GT == Integer.parseInt(GTsplit[5]))
                        && (side1AT == Integer.parseInt(ATsplit[1])) && (side2AT == Integer.parseInt(ATsplit[2]))
                        && (side3AT == Integer.parseInt(ATsplit[3])) && (side4AT == Integer.parseInt(ATsplit[4]))
                        && (side5AT == Integer.parseInt(ATsplit[5])) && (mode == Integer.parseInt(planMode))) {
                    planStatus = 1;
                    boolean updatePlanTransferredStatus = this.clientResponderModel.updatePlanTransferredStatus(junctionID, program_version_no, planNo);
                    if (updatePlanTransferredStatus == true) {
                        responseVal = secStartDelimiter + " " + secStartDelimiter + " " + secStartDelimiter + " " + secStartDelimiter + " " + junctionID + " " + programVersionNoFromDB + " " + fileNoFromDB + " " + functionNo + " " + planNo + " " + planStatus + " " + firstLastDelimiter + " " + firstLastDelimiter;
                    }
                } else {
                    responseVal = secStartDelimiter + " " + secStartDelimiter + " " + secStartDelimiter + " " + secStartDelimiter + " " + junctionID + " " + programVersionNoFromDB + " " + fileNoFromDB + " " + functionNo + " " + planNo + " " + planStatus + " " + firstLastDelimiter + " " + firstLastDelimiter;
                }
            } catch (Exception e) {
                System.out.println("Error - clientResponder - " + e);
            }
        }

//        Junction juncID = (Junction) this.junctionList.get(Integer.valueOf(junctionID));
//        boolean regisStatus = juncID.isRegistration_status();

        return responseVal;
    }

    public String getJunctionTime(byte[] dataToProcess, int firstStartDataPosition) {
        String response = null;
        int firstStartDelimiter = dataToProcess[firstStartDataPosition - 4];
        int secStartDelimiter = dataToProcess[firstStartDataPosition - 3];
        int dataSize1 = dataToProcess[firstStartDataPosition - 2];
        int dataSize2 = dataToProcess[firstStartDataPosition - 1];
        int junctionID = dataToProcess[firstStartDataPosition];
        int program_version_no = dataToProcess[firstStartDataPosition + 1];
        int fileNo = dataToProcess[firstStartDataPosition + 2];
        int functionNo = dataToProcess[firstStartDataPosition + 3];
        int juncHr = dataToProcess[firstStartDataPosition + 4];
        int juncMin = dataToProcess[firstStartDataPosition + 5];
        int juncDat = dataToProcess[firstStartDataPosition + 6];
        int juncMonth = dataToProcess[firstStartDataPosition + 7];
        int juncYear = dataToProcess[firstStartDataPosition + 8];
        int crc = dataToProcess[firstStartDataPosition + 9] & 0xFF;
        int firstLastDelimiter = dataToProcess[firstStartDataPosition + 10];
        int secLastDelimiter = firstLastDelimiter;
        int appHr = 0, appMin = 0, appDat = 0, appMonth = 0, appYear = 0;
        Calendar cald = Calendar.getInstance();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = sd.format(cald.getTime());

        String appDate = dateTime.split(" ")[0];
        String appTime = dateTime.split(" ")[1];

        String time[] = appTime.split(":");
        String dte[] = appDate.split("-");

        appHr = Integer.parseInt(time[0]);
        appMin = Integer.parseInt(time[1]);

        appDat = Integer.parseInt(dte[2]);
        appMonth = Integer.parseInt(dte[1]);
        appYear = Integer.parseInt(dte[0].substring(2));

        /*---------------------------- code started to manage one minute difference in junction time and in application time  ------------------------------------------*/

        int monthNo = (juncMonth - appMonth) == Math.abs(1) ? juncMonth > appMonth ? appMonth : juncMonth : 0;

        if (((juncYear - appYear) == Math.abs(1)) && ((juncMonth - appMonth) == Math.abs(11)) && ((juncDat - appDat) == Math.abs(30)) && ((juncHr - appHr) == Math.abs(23)) && ((juncMin - appMin) == Math.abs(59))) { //last minute of last hour of last day of last month of a year
            juncHr = appHr;
            juncMin = appMin;
            juncDat = appDat;
            juncMonth = appMonth;
            juncYear = appYear;
        } else if (((juncMonth - appMonth) == Math.abs(1)) && ((juncDat - appDat) == Math.abs(getNoOfDays(monthNo, appYear) - 1)) && ((juncHr - appHr) == Math.abs(23)) && ((juncMin - appMin) == Math.abs(59))) { //last minute of last hour of last day of a month
            juncHr = appHr;
            juncMin = appMin;
            juncDat = appDat;
            juncMonth = appMonth;
        } else if (((juncDat - appDat) == Math.abs(1)) && ((juncHr - appHr) == Math.abs(23)) && ((juncMin - appMin) == Math.abs(59))) { //last minute of last hour of a day
            juncHr = appHr;
            juncMin = appMin;
            juncDat = appDat;
        } else if (((juncHr - appHr) == Math.abs(1)) && ((juncMin - appMin) == Math.abs(59))) { //last minute of an hour
            juncHr = appHr;
            juncMin = appMin;
        } else if ((juncMin - appMin) == Math.abs(1)) { //a minute
            juncMin = appMin;
        }
        /*---------------------------- code endeded to manage one minute difference in junction time and in application time  ------------------------------------------*/

        int fileNoFromDB = this.clientResponderModel.getFileNo(junctionID, program_version_no);
        if (fileNo != fileNoFromDB && fileNoFromDB != 0) {
            this.clientResponderModel.updateFileNo(junctionID, fileNo, program_version_no);
        }
        fileNoFromDB = this.clientResponderModel.getFileNo(junctionID, program_version_no);
        int programVersionNoFromDB = this.clientResponderModel.getProgramVersionNo(junctionID);

        this.clientResponderModel.synchronizeTimeData(junctionID, program_version_no, juncHr, juncMin, juncDat, juncMonth, juncYear, appHr, appMin, appDat, appMonth, appYear);
        String currentTimeSynchronizationStatus = this.clientResponderModel.getCurrentTimeSynchronizationStatus(juncHr, juncMin, juncDat, juncMonth, juncYear, appHr, appMin, appDat, appMonth, appYear);

        int currTimeSynchronizationStatus = currentTimeSynchronizationStatus.equals("Y") ? 1 : 0;

        System.out.println("junction time info request--- " + firstStartDelimiter + " " + secStartDelimiter + " " + dataSize1 + " " + dataSize2 + " " + junctionID + " " + program_version_no + " " + fileNo + " " + functionNo + " " + juncHr + " " + juncMin + " " + juncDat + " " + juncMonth + " " + juncYear + " " + crc + " " + firstLastDelimiter + " " + secLastDelimiter);

        response = secStartDelimiter + " " + secStartDelimiter + " " + secStartDelimiter + " " + secStartDelimiter + " " + junctionID + " " + programVersionNoFromDB + " " + fileNoFromDB + " " + functionNo + " " + appHr + " " + appMin + " " + appDat + " " + appMonth + " " + appYear + " " + currTimeSynchronizationStatus + " " + firstLastDelimiter + " " + firstLastDelimiter;

        //System.out.println("Response of junction info:" + response);
        return response;
    }

    private String slaveFunction(byte[] dataToProcess, int firstStartDataPosition) {
        String responseVal = null;
        String slave_id = "";
        int db_side_no = 0;
        int firstStartDelimiter = dataToProcess[firstStartDataPosition - 4];
        int secStartDelimiter = dataToProcess[firstStartDataPosition - 3];
        int dataSize1 = dataToProcess[firstStartDataPosition - 2];
        int dataSize2 = dataToProcess[firstStartDataPosition - 1];
        int junctionID = dataToProcess[firstStartDataPosition];
        int program_version_no = dataToProcess[firstStartDataPosition + 1];
        int fileNo = dataToProcess[firstStartDataPosition + 2];
        int functionNo = dataToProcess[firstStartDataPosition + 3];
        for (int i = (firstStartDataPosition + 4); i <= (firstStartDataPosition + 11); i++) {
            char ch = (char) dataToProcess[i];
            slave_id = slave_id + ch;
        }

        db_side_no = this.clientResponderModel.getSideNo(junctionID, program_version_no, slave_id);

        int firstLastDelimiter = 0;
        int secLastDelimiter = 0;
        int crc = 0;
        int fileNoFromDB = this.clientResponderModel.getFileNo(junctionID, program_version_no);
        if (fileNo != fileNoFromDB) {
            this.clientResponderModel.updateFileNo(junctionID, fileNo, program_version_no);
        }
        fileNoFromDB = this.clientResponderModel.getFileNo(junctionID, program_version_no);
        int programVersionNoFromDB = this.clientResponderModel.getProgramVersionNo(junctionID);
        if ((dataToProcess[firstStartDataPosition + 13] == 125)) { //request from modem for slave detail
            crc = dataToProcess[firstStartDataPosition + 12];
            firstLastDelimiter = dataToProcess[firstStartDataPosition + 13];
            secLastDelimiter = dataToProcess[firstStartDataPosition + 14];
            System.out.println("junction slave request--- " + firstStartDelimiter + " " + secStartDelimiter + " " + dataSize1 + " " + dataSize2 + " " + junctionID + " " + program_version_no + " " + fileNo + " " + functionNo + " " + slave_id + " " + crc + " " + firstLastDelimiter + " " + secLastDelimiter);
            responseVal = secStartDelimiter + " " + secStartDelimiter + " " + secStartDelimiter + " " + secStartDelimiter + " " + junctionID + " " + programVersionNoFromDB + " " + fileNoFromDB + " " + functionNo + " " + slave_id + " " + db_side_no + " " + firstLastDelimiter + " " + firstLastDelimiter;

        } else if (dataToProcess[firstStartDataPosition + 14] == 125) { //slave detail confirmation request from modem
            int side_no = dataToProcess[firstStartDataPosition + 12];
            int status = 0;
            crc = dataToProcess[firstStartDataPosition + 13];
            firstLastDelimiter = dataToProcess[firstStartDataPosition + 14];
            secLastDelimiter = dataToProcess[firstStartDataPosition + 15];
            System.out.println("junction slave request--- " + firstStartDelimiter + " " + secStartDelimiter + " " + dataSize1 + " " + dataSize2 + " " + junctionID + " " + program_version_no + " " + fileNo + " " + functionNo + " " + slave_id + " " + side_no + " " + crc + " " + firstLastDelimiter + " " + secLastDelimiter);

            if (side_no == db_side_no) {
                try {
                    boolean result = this.clientResponderModel.updateSlaveTransferredStatus(junctionID, program_version_no, side_no);
                    status = result ? 1 : 0;
                } catch (Exception e) {
                    System.out.println("Error - clientResponder - " + e);
                }
            }
            responseVal = secStartDelimiter + " " + secStartDelimiter + " " + secStartDelimiter + " " + secStartDelimiter + " " + junctionID + " " + programVersionNoFromDB + " " + fileNoFromDB + " " + functionNo + " " + slave_id + " " + db_side_no + " " + status + " " + firstLastDelimiter + " " + firstLastDelimiter;
        }
        return responseVal;
    }

    public String JunctionRefreshFunction(byte[] dataToProcess, int firstStartDataPosition, boolean testRequestFromJunction) {
        String response = null;
        String extraBytes = "";
        PlanInfo planInfo = new PlanInfo();
        TeenPatti_Info teenPattiInfo = new TeenPatti_Info();
        DamohNakaInfo damohNakaInfo = new DamohNakaInfo();
        RanitalInfo ranitalInfo = new RanitalInfo();
        YatayatThanaInfo yatayatThanaInfo = new YatayatThanaInfo();
        KatangaInfo katangaInfo = new KatangaInfo();
        BaldeobagInfo baldeobagInfo = new BaldeobagInfo();
        DeendayalChowkInfo deendayalChowkInfo = new DeendayalChowkInfo();
        HighCourtInfo highCourtInfo = new HighCourtInfo();
        BloomChowkInfo bloomChowkInfo = new BloomChowkInfo();
        MadanMahalInfo madanMahalInfo = new MadanMahalInfo();
        BandariyaTirahaInfo bandariyaTirahaInfo = new BandariyaTirahaInfo();
        LabourChowkInfo labourChowkInfo = new LabourChowkInfo();
        TaiyabaliChowkInfo taiyabaliChowkInfo = new TaiyabaliChowkInfo();
        GohalPurInfo gohalPurInfo = new GohalPurInfo();
        CollectorateInfo collectorateInfo = new CollectorateInfo();
        TrafficSignalWebServices tsws = new TrafficSignalWebServices();
        String[] side13 = null;
        String[] side24 = null;
        try {
            int firstStartDelimiter = dataToProcess[firstStartDataPosition - 4];
            int secStartDelimiter = dataToProcess[firstStartDataPosition - 3];
            int dataSize1 = dataToProcess[firstStartDataPosition - 2];
            int dataSize2 = dataToProcess[firstStartDataPosition - 1];
            int junctionID = dataToProcess[firstStartDataPosition];
            int program_version_no = dataToProcess[firstStartDataPosition + 1];
            int fileNo = dataToProcess[firstStartDataPosition + 2];
            int functionNo = dataToProcess[firstStartDataPosition + 3];

            int side1Time = dataToProcess[firstStartDataPosition + 4] & 0xFF;
            int side2Time = dataToProcess[firstStartDataPosition + 5] & 0xFF;
            int side3Time = dataToProcess[firstStartDataPosition + 6] & 0xFF;
            int side4Time = dataToProcess[firstStartDataPosition + 7] & 0xFF;

            int unsignedSide13 = dataToProcess[firstStartDataPosition + 8] & 0xFF;
            int unsignedSide24 = dataToProcess[firstStartDataPosition + 9] & 0xFF;

            int unsignedMiscByte = dataToProcess[firstStartDataPosition + 10] & 0xFF;

            int plan_no = dataToProcess[firstStartDataPosition + 11];
            int plan_mode = dataToProcess[firstStartDataPosition + 12];
            int activity = dataToProcess[firstStartDataPosition + 13];
            int activity1 = 1;
            int activity2 = 1;
            int activity3 = 1;
            int receivedActivity = activity;
            String currentTimeSynchronizationStatus="Y";
            // data at firstStartDataPosition + 13/14/15 are default 1, when activity byte value is 2(clearance) then only firstStartDataPosition + 13 byte value will be used as clearance side.

            String onTime = this.clientResponderModel.getPlanOnTime(junctionID, program_version_no, plan_no);
            String offTime = this.clientResponderModel.getPlanOffTime(junctionID, program_version_no, plan_no);

            int juncHr = dataToProcess[firstStartDataPosition + 14];
            int juncMin = dataToProcess[firstStartDataPosition + 15];
            int juncDat = dataToProcess[firstStartDataPosition + 16];
            int juncMonth = dataToProcess[firstStartDataPosition + 17];
            int juncYear = dataToProcess[firstStartDataPosition + 18];
            
//            for (int i = (firstStartDataPosition + 19); i <= (firstStartDataPosition + 30); i++) {
//                //char ch = (char) dataToProcess[i];
////                if (extraBytes == "") {
////                    extraBytes = extraBytes + dataToProcess[i];
////                } else {
////                    extraBytes = extraBytes + " " + dataToProcess[i];
////                }
//
//            }

            int crc = dataToProcess[firstStartDataPosition + 19] & 0xFF;
            int firstLastDelimiter = dataToProcess[firstStartDataPosition + 20];
            int secLastDelimiter = firstLastDelimiter;//dataToProcess[firstStartDataPosition + 21];
                       

            if(junctionID == 13){
            arr13 = new byte[]{dataToProcess[firstStartDataPosition - 4],dataToProcess[firstStartDataPosition - 3],dataToProcess[firstStartDataPosition - 2]
                   ,dataToProcess[firstStartDataPosition - 1],dataToProcess[firstStartDataPosition],dataToProcess[firstStartDataPosition + 1],
                  dataToProcess[firstStartDataPosition + 2],dataToProcess[firstStartDataPosition + 3],dataToProcess[firstStartDataPosition + 4 & 0xFF],dataToProcess[firstStartDataPosition + 5 & 0xFF]
                 ,dataToProcess[firstStartDataPosition + 6 & 0xFF],dataToProcess[firstStartDataPosition + 7 & 0xFF],dataToProcess[firstStartDataPosition + 8 & 0xFF]
                 ,dataToProcess[firstStartDataPosition + 9 & 0xFF],dataToProcess[firstStartDataPosition + 10 & 0xFF],dataToProcess[firstStartDataPosition + 11]
                 ,dataToProcess[firstStartDataPosition + 12],dataToProcess[firstStartDataPosition + 13],dataToProcess[firstStartDataPosition + 14]
                 ,dataToProcess[firstStartDataPosition + 15],dataToProcess[firstStartDataPosition + 16],dataToProcess[firstStartDataPosition + 17]
                 ,dataToProcess[firstStartDataPosition + 18],dataToProcess[firstStartDataPosition + 19],dataToProcess[firstStartDataPosition + 20],
                 dataToProcess[firstStartDataPosition + 20]};
           }
           if(junctionID == 11){
            arr11 = new byte[]{dataToProcess[firstStartDataPosition - 4],dataToProcess[firstStartDataPosition - 3],dataToProcess[firstStartDataPosition - 2]
                   ,dataToProcess[firstStartDataPosition - 1],dataToProcess[firstStartDataPosition],dataToProcess[firstStartDataPosition + 1],
                  dataToProcess[firstStartDataPosition + 2],dataToProcess[firstStartDataPosition + 3],dataToProcess[firstStartDataPosition + 4 & 0xFF],dataToProcess[firstStartDataPosition + 5 & 0xFF]
                 ,dataToProcess[firstStartDataPosition + 6 & 0xFF],dataToProcess[firstStartDataPosition + 7 & 0xFF],dataToProcess[firstStartDataPosition + 8 & 0xFF]
                 ,dataToProcess[firstStartDataPosition + 9 & 0xFF],dataToProcess[firstStartDataPosition + 10 & 0xFF],dataToProcess[firstStartDataPosition + 11]
                 ,dataToProcess[firstStartDataPosition + 12],dataToProcess[firstStartDataPosition + 13],dataToProcess[firstStartDataPosition + 14]
                 ,dataToProcess[firstStartDataPosition + 15],dataToProcess[firstStartDataPosition + 16],dataToProcess[firstStartDataPosition + 17]
                 ,dataToProcess[firstStartDataPosition + 18],dataToProcess[firstStartDataPosition + 19],dataToProcess[firstStartDataPosition + 20],
                 dataToProcess[firstStartDataPosition + 20]};
           }
           if(junctionID == 2){
            arr1 = new byte[]{dataToProcess[firstStartDataPosition - 4],dataToProcess[firstStartDataPosition - 3],dataToProcess[firstStartDataPosition - 2]
                   ,dataToProcess[firstStartDataPosition - 1],dataToProcess[firstStartDataPosition],dataToProcess[firstStartDataPosition + 1],
                  dataToProcess[firstStartDataPosition + 2],dataToProcess[firstStartDataPosition + 3],dataToProcess[firstStartDataPosition + 4 & 0xFF],dataToProcess[firstStartDataPosition + 5 & 0xFF]
                 ,dataToProcess[firstStartDataPosition + 6 & 0xFF],dataToProcess[firstStartDataPosition + 7 & 0xFF],dataToProcess[firstStartDataPosition + 8 & 0xFF]
                 ,dataToProcess[firstStartDataPosition + 9 & 0xFF],dataToProcess[firstStartDataPosition + 10 & 0xFF],dataToProcess[firstStartDataPosition + 11]
                 ,dataToProcess[firstStartDataPosition + 12],dataToProcess[firstStartDataPosition + 13],dataToProcess[firstStartDataPosition + 14]
                 ,dataToProcess[firstStartDataPosition + 15],dataToProcess[firstStartDataPosition + 16],dataToProcess[firstStartDataPosition + 17]
                 ,dataToProcess[firstStartDataPosition + 18],dataToProcess[firstStartDataPosition + 19],dataToProcess[firstStartDataPosition + 20],
                 dataToProcess[firstStartDataPosition + 20]};
           }

           if(junctionID == 1){
            arryatayat = new byte[]{dataToProcess[firstStartDataPosition - 4],dataToProcess[firstStartDataPosition - 3],dataToProcess[firstStartDataPosition - 2]
                   ,dataToProcess[firstStartDataPosition - 1],dataToProcess[firstStartDataPosition],dataToProcess[firstStartDataPosition + 1],
                  dataToProcess[firstStartDataPosition + 2],dataToProcess[firstStartDataPosition + 3],dataToProcess[firstStartDataPosition + 4 & 0xFF],dataToProcess[firstStartDataPosition + 5 & 0xFF]
                 ,dataToProcess[firstStartDataPosition + 6 & 0xFF],dataToProcess[firstStartDataPosition + 7 & 0xFF],dataToProcess[firstStartDataPosition + 8 & 0xFF]
                 ,dataToProcess[firstStartDataPosition + 9 & 0xFF],dataToProcess[firstStartDataPosition + 10 & 0xFF],dataToProcess[firstStartDataPosition + 11]
                 ,dataToProcess[firstStartDataPosition + 12],dataToProcess[firstStartDataPosition + 13],dataToProcess[firstStartDataPosition + 14]
                 ,dataToProcess[firstStartDataPosition + 15],dataToProcess[firstStartDataPosition + 16],dataToProcess[firstStartDataPosition + 17]
                 ,dataToProcess[firstStartDataPosition + 18],dataToProcess[firstStartDataPosition + 19],dataToProcess[firstStartDataPosition + 20],
                 dataToProcess[firstStartDataPosition + 20]};
           }
           if(junctionID == 4){
            arrkatanga = new byte[]{dataToProcess[firstStartDataPosition - 4],dataToProcess[firstStartDataPosition - 3],dataToProcess[firstStartDataPosition - 2]
                   ,dataToProcess[firstStartDataPosition - 1],dataToProcess[firstStartDataPosition],dataToProcess[firstStartDataPosition + 1],
                  dataToProcess[firstStartDataPosition + 2],dataToProcess[firstStartDataPosition + 3],dataToProcess[firstStartDataPosition + 4 & 0xFF],dataToProcess[firstStartDataPosition + 5 & 0xFF]
                 ,dataToProcess[firstStartDataPosition + 6 & 0xFF],dataToProcess[firstStartDataPosition + 7 & 0xFF],dataToProcess[firstStartDataPosition + 8 & 0xFF]
                 ,dataToProcess[firstStartDataPosition + 9 & 0xFF],dataToProcess[firstStartDataPosition + 10 & 0xFF],dataToProcess[firstStartDataPosition + 11]
                 ,dataToProcess[firstStartDataPosition + 12],dataToProcess[firstStartDataPosition + 13],dataToProcess[firstStartDataPosition + 14]
                 ,dataToProcess[firstStartDataPosition + 15],dataToProcess[firstStartDataPosition + 16],dataToProcess[firstStartDataPosition + 17]
                 ,dataToProcess[firstStartDataPosition + 18],dataToProcess[firstStartDataPosition + 19],dataToProcess[firstStartDataPosition + 20],
                 dataToProcess[firstStartDataPosition + 20]};
           }
           if(junctionID == 5){
            arrbaldeobag = new byte[]{dataToProcess[firstStartDataPosition - 4],dataToProcess[firstStartDataPosition - 3],dataToProcess[firstStartDataPosition - 2]
                   ,dataToProcess[firstStartDataPosition - 1],dataToProcess[firstStartDataPosition],dataToProcess[firstStartDataPosition + 1],
                  dataToProcess[firstStartDataPosition + 2],dataToProcess[firstStartDataPosition + 3],dataToProcess[firstStartDataPosition + 4 & 0xFF],dataToProcess[firstStartDataPosition + 5 & 0xFF]
                 ,dataToProcess[firstStartDataPosition + 6 & 0xFF],dataToProcess[firstStartDataPosition + 7 & 0xFF],dataToProcess[firstStartDataPosition + 8 & 0xFF]
                 ,dataToProcess[firstStartDataPosition + 9 & 0xFF],dataToProcess[firstStartDataPosition + 10 & 0xFF],dataToProcess[firstStartDataPosition + 11]
                 ,dataToProcess[firstStartDataPosition + 12],dataToProcess[firstStartDataPosition + 13],dataToProcess[firstStartDataPosition + 14]
                 ,dataToProcess[firstStartDataPosition + 15],dataToProcess[firstStartDataPosition + 16],dataToProcess[firstStartDataPosition + 17]
                 ,dataToProcess[firstStartDataPosition + 18],dataToProcess[firstStartDataPosition + 19],dataToProcess[firstStartDataPosition + 20],
                 dataToProcess[firstStartDataPosition + 20]};
           }
           if(junctionID == 6){
            arrdeendayal = new byte[]{dataToProcess[firstStartDataPosition - 4],dataToProcess[firstStartDataPosition - 3],dataToProcess[firstStartDataPosition - 2]
                   ,dataToProcess[firstStartDataPosition - 1],dataToProcess[firstStartDataPosition],dataToProcess[firstStartDataPosition + 1],
                  dataToProcess[firstStartDataPosition + 2],dataToProcess[firstStartDataPosition + 3],dataToProcess[firstStartDataPosition + 4 & 0xFF],dataToProcess[firstStartDataPosition + 5 & 0xFF]
                 ,dataToProcess[firstStartDataPosition + 6 & 0xFF],dataToProcess[firstStartDataPosition + 7 & 0xFF],dataToProcess[firstStartDataPosition + 8 & 0xFF]
                 ,dataToProcess[firstStartDataPosition + 9 & 0xFF],dataToProcess[firstStartDataPosition + 10 & 0xFF],dataToProcess[firstStartDataPosition + 11]
                 ,dataToProcess[firstStartDataPosition + 12],dataToProcess[firstStartDataPosition + 13],dataToProcess[firstStartDataPosition + 14]
                 ,dataToProcess[firstStartDataPosition + 15],dataToProcess[firstStartDataPosition + 16],dataToProcess[firstStartDataPosition + 17]
                 ,dataToProcess[firstStartDataPosition + 18],dataToProcess[firstStartDataPosition + 19],dataToProcess[firstStartDataPosition + 20],
                 dataToProcess[firstStartDataPosition + 20]};
           }
           if(junctionID == 7){
            arrhighcourt = new byte[]{dataToProcess[firstStartDataPosition - 4],dataToProcess[firstStartDataPosition - 3],dataToProcess[firstStartDataPosition - 2]
                   ,dataToProcess[firstStartDataPosition - 1],dataToProcess[firstStartDataPosition],dataToProcess[firstStartDataPosition + 1],
                  dataToProcess[firstStartDataPosition + 2],dataToProcess[firstStartDataPosition + 3],dataToProcess[firstStartDataPosition + 4 & 0xFF],dataToProcess[firstStartDataPosition + 5 & 0xFF]
                 ,dataToProcess[firstStartDataPosition + 6 & 0xFF],dataToProcess[firstStartDataPosition + 7 & 0xFF],dataToProcess[firstStartDataPosition + 8 & 0xFF]
                 ,dataToProcess[firstStartDataPosition + 9 & 0xFF],dataToProcess[firstStartDataPosition + 10 & 0xFF],dataToProcess[firstStartDataPosition + 11]
                 ,dataToProcess[firstStartDataPosition + 12],dataToProcess[firstStartDataPosition + 13],dataToProcess[firstStartDataPosition + 14]
                 ,dataToProcess[firstStartDataPosition + 15],dataToProcess[firstStartDataPosition + 16],dataToProcess[firstStartDataPosition + 17]
                 ,dataToProcess[firstStartDataPosition + 18],dataToProcess[firstStartDataPosition + 19],dataToProcess[firstStartDataPosition + 20],
                 dataToProcess[firstStartDataPosition + 20]};
           }
           if(junctionID == 8){
            arrbloomchowk = new byte[]{dataToProcess[firstStartDataPosition - 4],dataToProcess[firstStartDataPosition - 3],dataToProcess[firstStartDataPosition - 2]
                   ,dataToProcess[firstStartDataPosition - 1],dataToProcess[firstStartDataPosition],dataToProcess[firstStartDataPosition + 1],
                  dataToProcess[firstStartDataPosition + 2],dataToProcess[firstStartDataPosition + 3],dataToProcess[firstStartDataPosition + 4 & 0xFF],dataToProcess[firstStartDataPosition + 5 & 0xFF]
                 ,dataToProcess[firstStartDataPosition + 6 & 0xFF],dataToProcess[firstStartDataPosition + 7 & 0xFF],dataToProcess[firstStartDataPosition + 8 & 0xFF]
                 ,dataToProcess[firstStartDataPosition + 9 & 0xFF],dataToProcess[firstStartDataPosition + 10 & 0xFF],dataToProcess[firstStartDataPosition + 11]
                 ,dataToProcess[firstStartDataPosition + 12],dataToProcess[firstStartDataPosition + 13],dataToProcess[firstStartDataPosition + 14]
                 ,dataToProcess[firstStartDataPosition + 15],dataToProcess[firstStartDataPosition + 16],dataToProcess[firstStartDataPosition + 17]
                 ,dataToProcess[firstStartDataPosition + 18],dataToProcess[firstStartDataPosition + 19],dataToProcess[firstStartDataPosition + 20],
                 dataToProcess[firstStartDataPosition + 20]};
           }
           if(junctionID == 12){
            arrmadanmahal = new byte[]{dataToProcess[firstStartDataPosition - 4],dataToProcess[firstStartDataPosition - 3],dataToProcess[firstStartDataPosition - 2]
                   ,dataToProcess[firstStartDataPosition - 1],dataToProcess[firstStartDataPosition],dataToProcess[firstStartDataPosition + 1],
                  dataToProcess[firstStartDataPosition + 2],dataToProcess[firstStartDataPosition + 3],dataToProcess[firstStartDataPosition + 4 & 0xFF],dataToProcess[firstStartDataPosition + 5 & 0xFF]
                 ,dataToProcess[firstStartDataPosition + 6 & 0xFF],dataToProcess[firstStartDataPosition + 7 & 0xFF],dataToProcess[firstStartDataPosition + 8 & 0xFF]
                 ,dataToProcess[firstStartDataPosition + 9 & 0xFF],dataToProcess[firstStartDataPosition + 10 & 0xFF],dataToProcess[firstStartDataPosition + 11]
                 ,dataToProcess[firstStartDataPosition + 12],dataToProcess[firstStartDataPosition + 13],dataToProcess[firstStartDataPosition + 14]
                 ,dataToProcess[firstStartDataPosition + 15],dataToProcess[firstStartDataPosition + 16],dataToProcess[firstStartDataPosition + 17]
                 ,dataToProcess[firstStartDataPosition + 18],dataToProcess[firstStartDataPosition + 19],dataToProcess[firstStartDataPosition + 20],
                 dataToProcess[firstStartDataPosition + 20]};
           }
           if(junctionID == 14){
            arrgohalpur = new byte[]{dataToProcess[firstStartDataPosition - 4],dataToProcess[firstStartDataPosition - 3],dataToProcess[firstStartDataPosition - 2]
                   ,dataToProcess[firstStartDataPosition - 1],dataToProcess[firstStartDataPosition],dataToProcess[firstStartDataPosition + 1],
                  dataToProcess[firstStartDataPosition + 2],dataToProcess[firstStartDataPosition + 3],dataToProcess[firstStartDataPosition + 4 & 0xFF],dataToProcess[firstStartDataPosition + 5 & 0xFF]
                 ,dataToProcess[firstStartDataPosition + 6 & 0xFF],dataToProcess[firstStartDataPosition + 7 & 0xFF],dataToProcess[firstStartDataPosition + 8 & 0xFF]
                 ,dataToProcess[firstStartDataPosition + 9 & 0xFF],dataToProcess[firstStartDataPosition + 10 & 0xFF],dataToProcess[firstStartDataPosition + 11]
                 ,dataToProcess[firstStartDataPosition + 12],dataToProcess[firstStartDataPosition + 13],dataToProcess[firstStartDataPosition + 14]
                 ,dataToProcess[firstStartDataPosition + 15],dataToProcess[firstStartDataPosition + 16],dataToProcess[firstStartDataPosition + 17]
                 ,dataToProcess[firstStartDataPosition + 18],dataToProcess[firstStartDataPosition + 19],dataToProcess[firstStartDataPosition + 20],
                 dataToProcess[firstStartDataPosition + 20]};
           }
           
           if(junctionID == 15){
            arrbandariya = new byte[]{dataToProcess[firstStartDataPosition - 4],dataToProcess[firstStartDataPosition - 3],dataToProcess[firstStartDataPosition - 2]
                   ,dataToProcess[firstStartDataPosition - 1],dataToProcess[firstStartDataPosition],dataToProcess[firstStartDataPosition + 1],
                  dataToProcess[firstStartDataPosition + 2],dataToProcess[firstStartDataPosition + 3],dataToProcess[firstStartDataPosition + 4 & 0xFF],dataToProcess[firstStartDataPosition + 5 & 0xFF]
                 ,dataToProcess[firstStartDataPosition + 6 & 0xFF],dataToProcess[firstStartDataPosition + 7 & 0xFF],dataToProcess[firstStartDataPosition + 8 & 0xFF]
                 ,dataToProcess[firstStartDataPosition + 9 & 0xFF],dataToProcess[firstStartDataPosition + 10 & 0xFF],dataToProcess[firstStartDataPosition + 11]
                 ,dataToProcess[firstStartDataPosition + 12],dataToProcess[firstStartDataPosition + 13],dataToProcess[firstStartDataPosition + 14]
                 ,dataToProcess[firstStartDataPosition + 15],dataToProcess[firstStartDataPosition + 16],dataToProcess[firstStartDataPosition + 17]
                 ,dataToProcess[firstStartDataPosition + 18],dataToProcess[firstStartDataPosition + 19],dataToProcess[firstStartDataPosition + 20],
                 dataToProcess[firstStartDataPosition + 20]};
           }

            int appHr = 0, appMin = 0, appDat = 0, appMonth = 0, appYear = 0;
            Calendar cald = Calendar.getInstance();
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateTime = sd.format(cald.getTime());

            String appDate = dateTime.split(" ")[0];
            String appTime = dateTime.split(" ")[1];

            String time[] = appTime.split(":");
            String dte[] = appDate.split("-");

            appHr = Integer.parseInt(time[0]);
            appMin = Integer.parseInt(time[1]);

            appDat = Integer.parseInt(dte[2]);
            appMonth = Integer.parseInt(dte[1]);
            appYear = Integer.parseInt(dte[0].substring(2));

            //---------------------------- code started to manage one minute difference in junction time and in application time  ------------------------------------------

            int monthNo = (juncMonth - appMonth) == Math.abs(1) ? juncMonth > appMonth ? appMonth : juncMonth : 0;

            if (((juncYear - appYear) == Math.abs(1)) && ((juncMonth - appMonth) == Math.abs(11)) && ((juncDat - appDat) == Math.abs(30)) && ((juncHr - appHr) == Math.abs(23)) && ((juncMin - appMin) == Math.abs(59))) { //last minute of last hour of last day of last month of a year
                juncHr = appHr;
                juncMin = appMin;
                juncDat = appDat;
                juncMonth = appMonth;
                juncYear = appYear;
            } else if (((juncMonth - appMonth) == Math.abs(1)) && ((juncDat - appDat) == Math.abs(getNoOfDays(monthNo, appYear) - 1)) && ((juncHr - appHr) == Math.abs(23)) && ((juncMin - appMin) == Math.abs(59))) { //last minute of last hour of last day of a month
                juncHr = appHr;
                juncMin = appMin;
                juncDat = appDat;
                juncMonth = appMonth;
            } else if (((juncDat - appDat) == Math.abs(1)) && ((juncHr - appHr) == Math.abs(23)) && ((juncMin - appMin) == Math.abs(59))) { //last minute of last hour of a day
                juncHr = appHr;
                juncMin = appMin;
                juncDat = appDat;
            } else if (((juncHr - appHr) == Math.abs(1)) && ((juncMin - appMin) == Math.abs(59))) { //last minute of an hour
                juncHr = appHr;
                juncMin = appMin;
            } else if ((juncMin - appMin) == Math.abs(1)) { //a minute
                juncMin = appMin;
            }
            //---------------------------- code endeded to manage one minute difference in junction time and in application time  ------------------------------------------


         //   String currentTimeSynchronizationStatus = this.clientResponderModel.getCurrentTimeSynchronizationStatus(juncHr, juncMin, juncDat, juncMonth, juncYear, appHr, appMin, appDat, appMonth, appYear);


            String side13Status = Integer.toBinaryString(unsignedSide13);
            String side24Status = Integer.toBinaryString(unsignedSide24);

            String miscByte = Integer.toBinaryString(unsignedMiscByte);

            int fileNoFromDB = this.clientResponderModel.getFileNo(junctionID, program_version_no);
            if (fileNo != fileNoFromDB) {
                this.clientResponderModel.updateFileNo(junctionID, fileNo, program_version_no);
            }
            fileNoFromDB = this.clientResponderModel.getFileNo(junctionID, program_version_no);
            int programVersionNoFromDB = this.clientResponderModel.getProgramVersionNo(junctionID);

            String junctionName = this.clientResponderModel.getJunctionName(junctionID, program_version_no);
            int noOfSides = this.clientResponderModel.getNoOfSides(junctionID, program_version_no);
            List sideNameList = new ArrayList(5);
            for (int j = 1; j <= noOfSides; j++) {
                String sideName = this.clientResponderModel.getSideName(j, junctionID, program_version_no);
                sideNameList.add(sideName);
            }

            System.out.println("refresh  request from modem--- " + firstStartDelimiter + " " + secStartDelimiter + " " + dataSize1 + " " + dataSize2
                    + " " + junctionID + " " + program_version_no + " " + fileNo + " " + functionNo + " " + side1Time + " " + side2Time + " " + side3Time + " " + side4Time + " "
                    + unsignedSide13 + " " + unsignedSide24 + " " + unsignedMiscByte + " " + plan_no + " " + plan_mode + " " + activity + " "
                    + juncHr + " " + juncMin + " " + juncDat + " " + juncMonth + " " + juncYear + " "
                    + crc + " " + firstLastDelimiter + " " + secLastDelimiter);

            switch (side13Status.length()) {
                case 5:
                    side13Status = "000" + side13Status;
                    break;
                case 6:
                    side13Status = "00" + side13Status;
                    break;
                case 7:
                    side13Status = "0" + side13Status;
            }

            switch (side24Status.length()) {
                case 5:
                    side24Status = "000" + side24Status;
                    break;
                case 6:
                    side24Status = "00" + side24Status;
                    break;
                case 7:
                    side24Status = "0" + side24Status;
            }

            switch (miscByte.length()) {
                case 5:
                    miscByte = "000" + miscByte;
                    break;
                case 6:
                    miscByte = "00" + miscByte;
                    break;
                case 7:
                    miscByte = "0" + miscByte;
            }

            System.out.println("side13Status : " + side13Status.length() + " , " + side13Status);
            System.out.println("side24Status : " + side24Status.length() + " , " + side24Status);
            System.out.println("miscByte     : " + miscByte.length() + " , " + miscByte);

            side13 = side13Status.trim().split("");
            side24 = side24Status.trim().split("");

            if (!testRequestFromJunction) {

                planInfo.setFunction_no(functionNo);
                planInfo.setJunction_name(junctionName);
                if (noOfSides == 2) {
                    planInfo.setSide1Name((String) sideNameList.get(0));
                    planInfo.setSide2Name((String) sideNameList.get(1));
                } else if (noOfSides == 3) {
                    planInfo.setSide1Name((String) sideNameList.get(0));
                    planInfo.setSide2Name((String) sideNameList.get(1));
                    planInfo.setSide3Name((String) sideNameList.get(2));
                } else if (noOfSides == 4) {
                    planInfo.setSide1Name((String) sideNameList.get(0));
                    planInfo.setSide2Name((String) sideNameList.get(1));
                    planInfo.setSide3Name((String) sideNameList.get(2));
                    planInfo.setSide4Name((String) sideNameList.get(3));
                } else if (noOfSides == 5) {
                    planInfo.setSide1Name((String) sideNameList.get(0));
                    planInfo.setSide2Name((String) sideNameList.get(1));
                    planInfo.setSide3Name((String) sideNameList.get(2));
                    planInfo.setSide4Name((String) sideNameList.get(3));
                    planInfo.setSide5Name((String) sideNameList.get(4));
                }
                planInfo.setJunction_id(junctionID);
                planInfo.setProgram_version_no(program_version_no);
                planInfo.setFileNo(fileNo);
                planInfo.setPlan_no(plan_no);
                planInfo.setOnTime(onTime);
                planInfo.setOffTime(offTime);
                planInfo.setMode(plan_mode == 1 ? "Blinker" : plan_mode == 2 ? "Signal" : "Signal With Pedestrian");
                planInfo.setSide1_time(side1Time);
                planInfo.setSide2_time(side2Time);
                planInfo.setSide3_time(side3Time);
                planInfo.setSide4_time(side4Time);

                System.out.println("side13 size: " + side13.length);
                System.out.println("side24 size: " + side24.length);
                int beginIndex = 0;
                try {
                    if (side13.length == 9) {
                        beginIndex = 1;
                    }
                    planInfo.setSide1_left_status(Integer.parseInt(side13[beginIndex]));
                    planInfo.setSide1_right_status(Integer.parseInt(side13[beginIndex + 1]));
                    planInfo.setSide1_up_status(Integer.parseInt(side13[beginIndex + 2]));
                    planInfo.setSide1_down_status(Integer.parseInt(side13[beginIndex + 3]));
                    planInfo.setSide3_left_status(Integer.parseInt(side13[beginIndex + 4]));
                    planInfo.setSide3_right_status(Integer.parseInt(side13[beginIndex + 5]));
                    planInfo.setSide3_up_status(Integer.parseInt(side13[beginIndex + 6]));
                    planInfo.setSide3_down_status(Integer.parseInt(side13[beginIndex + 7]));

                    planInfo.setSide2_left_status(Integer.parseInt(side24[beginIndex]));
                    planInfo.setSide2_right_status(Integer.parseInt(side24[beginIndex + 1]));
                    planInfo.setSide2_up_status(Integer.parseInt(side24[beginIndex + 2]));
                    planInfo.setSide2_down_status(Integer.parseInt(side24[beginIndex + 3]));
                    planInfo.setSide4_left_status(Integer.parseInt(side24[beginIndex + 4]));
                    planInfo.setSide4_right_status(Integer.parseInt(side24[beginIndex + 5]));
                    planInfo.setSide4_up_status(Integer.parseInt(side24[beginIndex + 6]));
                    planInfo.setSide4_down_status(Integer.parseInt(side24[beginIndex + 7]));

                } catch (Exception e) {
                    System.out.println("Error in side1 2 3 4 status: " + e);
                }

                planInfo.setJuncHr(juncHr);
                planInfo.setJuncMin(juncMin);
                planInfo.setJuncDate(juncDat);
                planInfo.setJuncMonth(juncMonth);
                planInfo.setJuncYear(juncYear);

                try {
                    this.planInfoRefreshList.setResponseFromModemForRefresh(true);
                    this.ctx.setAttribute("planInfolist", this.planInfoRefreshList);
                    if (activity == 2) {
                        this.junction.setResponseFromModemForClearance(true);
                    } else {
                        this.junction.setResponseFromModemForActivity(true);
                    }
                    System.out.println("client responder RequestForClearance: " + isRequestForClearance());
                    System.out.println("client responder requestForActivity: " + isRequestForActivity());

                    this.ctx.setAttribute("junction", this.junction);
                    this.modemResReadSaveCont.setCtx(this.ctx);

                    this.requestForActivity = (Boolean) (ctx.getAttribute("requestForActivity" + junctionID) == null ? false : ctx.getAttribute("requestForActivity" + junctionID));
                    this.requestForClearance = (Boolean) (ctx.getAttribute("requestForClearance" + junctionID) == null ? false : ctx.getAttribute("requestForClearance" + junctionID));
                    this.activityNo = (Integer) (ctx.getAttribute("activityNo" + junctionID) == null ? 0 : ctx.getAttribute("activityNo" + junctionID));
                    this.activitySide = (Integer) (ctx.getAttribute("activitySide" + junctionID) == null ? 0 : ctx.getAttribute("activitySide" + junctionID));
                    this.clearanceSide = (Integer) (ctx.getAttribute("clearanceSide" + junctionID) == null ? 0 : ctx.getAttribute("clearanceSide" + junctionID));

                    activity = receivedActivity == 4 || receivedActivity == 6 ? 1 : this.requestForActivity ? this.activityNo : this.requestForClearance ? 2 : 1;
                    activity1 = receivedActivity == 4 || receivedActivity == 6 ? 1
                            : this.requestForActivity ? this.activityNo == 4 || this.activityNo == 6 ? this.activitySide : 1
                            : this.requestForClearance ? this.clearanceSide : 1;

                    ctx.setAttribute("requestForActivity" + junctionID, false);
         //           ctx.setAttribute("requestForClearance" + junctionID, false);
                    ctx.setAttribute("activityNo" + junctionID, 0);
                    ctx.setAttribute("activitySide" + junctionID, 0);
       //             ctx.setAttribute("clearanceSide" + junctionID, 0);

                    planInfo.setActivity(activity);
                    planInfo.setSide_no(activity1);

                    if (receivedActivity == 4 || receivedActivity == 6) {
                        this.requestForActivity = false;
                    }

                    planInfo.setResponseFromModemForRefresh(true);

                    this.planInfoRefreshList = planInfo;
                    this.ctx.setAttribute("planInfolist", this.planInfoRefreshList);                   
                    if(junctionID == 2){
                      BeanUtils.copyProperties(damohNakaInfo, planInfo);
                      damohNakaInfoRefreshList = damohNakaInfo;
                    this.ctx.setAttribute("damohNakaInfoList", this.damohNakaInfoRefreshList);
                    }
                    if(junctionID == 13){
                      BeanUtils.copyProperties(teenPattiInfo, planInfo);
                      teenPattiInfoRefreshList = teenPattiInfo;
                    this.ctx.setAttribute("teenPattiInfoList", this.teenPattiInfoRefreshList);
                    }
                    if(junctionID == 14){
                      BeanUtils.copyProperties(gohalPurInfo, planInfo);
                      gohalPurInfoRefreshList = gohalPurInfo;
                    this.ctx.setAttribute("gohalPurInfoList", this.gohalPurInfoRefreshList);
                    }
                    if(junctionID == 15){
                      BeanUtils.copyProperties(bandariyaTirahaInfo, planInfo);
                      bandariyaTirahaInfoRefreshList = bandariyaTirahaInfo;
                    this.ctx.setAttribute("bandariyaTirahaInfoList", this.bandariyaTirahaInfoRefreshList);
                    }
                    if(junctionID == 16){
                      BeanUtils.copyProperties(labourChowkInfo, planInfo);
                      labourChowkInfoRefreshList = labourChowkInfo;
                    this.ctx.setAttribute("labourChowkInfoList", this.labourChowkInfoRefreshList);
                    }
                    if(junctionID == 17){
                      BeanUtils.copyProperties(taiyabaliChowkInfo, planInfo);
                      taiyabaliChowkInfoRefreshList = taiyabaliChowkInfo;
                    this.ctx.setAttribute("taiyabaliChowkInfoList", this.taiyabaliChowkInfoRefreshList);
                    }
                    
                    if(junctionID == 18){
                      BeanUtils.copyProperties(collectorateInfo, planInfo);
                      collectorateInfoRefreshList = collectorateInfo;
                    this.ctx.setAttribute("collectorateInfoList", this.collectorateInfoRefreshList);
                    }
                    if(junctionID == 11){
                    BeanUtils.copyProperties(ranitalInfo, planInfo);
                    ranitalInfoRefreshList = ranitalInfo;
                    this.ctx.setAttribute("ranitalInfoList", this.ranitalInfoRefreshList);
                    }
                    if(junctionID == 1){
                    BeanUtils.copyProperties(yatayatThanaInfo, planInfo);
                    yatayatThanaInfoRefreshList = yatayatThanaInfo;
                    this.ctx.setAttribute("yatayatThanaInfoList", this.yatayatThanaInfoRefreshList);
                    }
                    if(junctionID == 4){
                    BeanUtils.copyProperties(katangaInfo, planInfo);
                    katangaInfoRefreshList = katangaInfo;
                    this.ctx.setAttribute("katangaInfoList", this.katangaInfoRefreshList);
                    }
                    if(junctionID == 5){
                    BeanUtils.copyProperties(baldeobagInfo, planInfo);
                    baldeobagInfoRefreshList = baldeobagInfo;
                    this.ctx.setAttribute("baldeobagInfoList", this.baldeobagInfoRefreshList);
                    }
                    if(junctionID == 6){
                    BeanUtils.copyProperties(deendayalChowkInfo, planInfo);
                    deendayalChowkInfoRefreshList = deendayalChowkInfo;
                    this.ctx.setAttribute("deendayalChowkInfoList", this.deendayalChowkInfoRefreshList);
                    }
                    if(junctionID == 7){
                    BeanUtils.copyProperties(highCourtInfo, planInfo);
                    highCourtInfoRefreshList = highCourtInfo;
                    this.ctx.setAttribute("highCourtInfoList", this.highCourtInfoRefreshList);
                    }
                    if(junctionID == 8){
                    BeanUtils.copyProperties(bloomChowkInfo, planInfo);
                    bloomChowkInfoRefreshList = bloomChowkInfo;
                    this.ctx.setAttribute("bloomChowkInfoList", this.bloomChowkInfoRefreshList);
                    }
                    if(junctionID == 12){
                    BeanUtils.copyProperties(madanMahalInfo, planInfo);
                    madanMahalInfoRefreshList = madanMahalInfo;
                    this.ctx.setAttribute("madanMahalInfoList", this.madanMahalInfoRefreshList);
                    }
                    this.ctx.setAttribute("junction", this.junction);
                    this.modemResReadSaveCont.setCtx(this.ctx);
                    //this.planInfoRefreshList = planInfo;
                } catch (Exception e) {
                    System.out.println("ClientResponder junctionRefresh PlanInfoListContext error :" + e);
                }
            }

            activity = program_version_no == programVersionNoFromDB ? fileNo == fileNoFromDB ? currentTimeSynchronizationStatus.equals("Y") ? activity : testRequestFromJunction ? 1 : 9 : 8 : 7;
            activity1 = activity == 7 || activity == 8 || activity == 9 ? 1 : activity1;

            String pureBytes = null;
           // pureBytes = tsws.arr2;
            //String activeReq = clientResponderModel.getActiveRequest(junctionID);
            int junction_id = 0;
            if(mapData() != null){
                if(!mapData().equals("normal")) {
                String data = mapData();
                junction_id = Integer.parseInt(data.split("_")[0]);
                if (junction_id == junctionID) {
                        activity = 2;
                        activity1 = Integer.parseInt(data.split("_")[1]);
                    }else {
                    activity = 1;
                    activity1 = 1;
                }
            }else {
                activity = 1;
                    activity1 = 1;
                }
                
            }
//            
            
            
            System.out.println("activity bytes: " + activity + " " + activity1 + " " + activity2 + " " + activity3);
            response = secStartDelimiter + " " + secStartDelimiter + " " + secStartDelimiter + " " + secStartDelimiter + " " + junctionID + " " + programVersionNoFromDB + " " + fileNoFromDB + " " + functionNo + " " + activity + " " + activity1 + " " + activity2 + " " + activity3 +  " " + firstLastDelimiter + " " + firstLastDelimiter;
            System.out.println("response ::: "+response);
//            if(activeReq.equals("Y")){
//            response = pureBytes;
//            }
//            else{
//            response = secStartDelimiter + " " + secStartDelimiter + " " + secStartDelimiter + " " + secStartDelimiter + " " + junctionID + " " + programVersionNoFromDB + " " + fileNoFromDB + " " + functionNo + " " + activity + " " + activity1 + " " + activity2 + " " + activity3 + " " + firstLastDelimiter + " " + firstLastDelimiter;
//            }

        } catch (Exception e) {
            System.out.println("PlanInfoListContext error :" + e);
        }
        return response;
    }
    
    /*public String JunctionRefreshFunction(byte[] dataToProcess, int firstStartDataPosition, boolean testRequestFromJunction) {
        String response = null;
        String extraBytes = "";
        PlanInfo planInfo = new PlanInfo();
        TeenPatti_Info teenPattiInfo = new TeenPatti_Info();
        DamohNakaInfo damohNakaInfo = new DamohNakaInfo();
        RanitalInfo ranitalInfo = new RanitalInfo();
        YatayatThanaInfo yatayatThanaInfo = new YatayatThanaInfo();
        KatangaInfo katangaInfo = new KatangaInfo();
        BaldeobagInfo baldeobagInfo = new BaldeobagInfo();
        DeendayalChowkInfo deendayalChowkInfo = new DeendayalChowkInfo();
        HighCourtInfo highCourtInfo = new HighCourtInfo();
        BloomChowkInfo bloomChowkInfo = new BloomChowkInfo();
        MadanMahalInfo madanMahalInfo = new MadanMahalInfo();
        TrafficSignalWebServices tsws = new TrafficSignalWebServices();
        String[] side13 = null;
        String[] side24 = null;
        try {
            int firstStartDelimiter = dataToProcess[firstStartDataPosition - 4];
            int secStartDelimiter = dataToProcess[firstStartDataPosition - 3];
            int dataSize1 = dataToProcess[firstStartDataPosition - 2];
            int dataSize2 = dataToProcess[firstStartDataPosition - 1];
            int junctionID = dataToProcess[firstStartDataPosition];
            int program_version_no = dataToProcess[firstStartDataPosition + 1];
            int fileNo = dataToProcess[firstStartDataPosition + 2];
            int functionNo = dataToProcess[firstStartDataPosition + 3];

            int side1Time = dataToProcess[firstStartDataPosition + 4] & 0xFF;
            int side2Time = dataToProcess[firstStartDataPosition + 5] & 0xFF;
            int side3Time = dataToProcess[firstStartDataPosition + 6] & 0xFF;
            int side4Time = dataToProcess[firstStartDataPosition + 7] & 0xFF;

            int unsignedSide13 = dataToProcess[firstStartDataPosition + 8] & 0xFF;
            int unsignedSide24 = dataToProcess[firstStartDataPosition + 9] & 0xFF;

            int unsignedMiscByte = dataToProcess[firstStartDataPosition + 10] & 0xFF;

            int plan_no = dataToProcess[firstStartDataPosition + 11];
            int plan_mode = dataToProcess[firstStartDataPosition + 12];
            int activity = dataToProcess[firstStartDataPosition + 13];
            int activity1 = 1;
            int activity2 = 1;
            int activity3 = 1;
            int receivedActivity = activity;
            // String currentTimeSynchronizationStatus="Y";
            // data at firstStartDataPosition + 13/14/15 are default 1, when activity byte value is 2(clearance) then only firstStartDataPosition + 13 byte value will be used as clearance side.

            String onTime = this.clientResponderModel.getPlanOnTime(junctionID, program_version_no, plan_no);
            String offTime = this.clientResponderModel.getPlanOffTime(junctionID, program_version_no, plan_no);

            int juncHr = dataToProcess[firstStartDataPosition + 14];
            int juncMin = dataToProcess[firstStartDataPosition + 15];
            int juncDat = dataToProcess[firstStartDataPosition + 16];
            int juncMonth = dataToProcess[firstStartDataPosition + 17];
            int juncYear = dataToProcess[firstStartDataPosition + 18];
            

            for (int i = (firstStartDataPosition + 19); i <= (firstStartDataPosition + 30); i++) {
                //char ch = (char) dataToProcess[i];
                if (extraBytes == "") {
                    extraBytes = extraBytes + dataToProcess[i];
                } else {
                    extraBytes = extraBytes + " " + dataToProcess[i];
                }

            }
            int crc = dataToProcess[firstStartDataPosition + 31] & 0xFF;
            int firstLastDelimiter = dataToProcess[firstStartDataPosition + 32];
            int secLastDelimiter = firstLastDelimiter;
//            dataToProcess[firstStartDataPosition + 21];
//            int arr2[] = {firstStartDelimiter,secStartDelimiter,dataSize1,dataSize2,junctionID,program_version_no,fileNo,functionNo,side1Time,side2Time
//                           ,side3Time,side4Time,unsignedSide13,unsignedSide24,unsignedMiscByte,plan_no,plan_mode,activity,juncHr,
//                          juncMin,juncDat,juncMonth,juncYear,crc,firstLastDelimiter,firstLastDelimiter};
//            ByteBuffer byteBuffer = ByteBuffer.allocate(arr2.length);        
//            IntBuffer intBuffer = byteBuffer.asIntBuffer();
//            intBuffer.put(arr2);
//
//            byte[] array = byteBuffer.array();
//            arr1 = array;
//            arr1 = new byte[arr2.length * 4];
//            for(int i=0, k=0 ;i< arr2.length ;i++){
//             int temp = arr2[i];
//             for(int j=0; j<4 ; j++,k++)
//             {
//             arr1[k]=(byte)((temp >> (8 * j ) & 0xff));
//             }            
//        }
            if (junctionID == 13) {
                arr13 = new byte[]{dataToProcess[firstStartDataPosition - 4], dataToProcess[firstStartDataPosition - 3], dataToProcess[firstStartDataPosition - 2], dataToProcess[firstStartDataPosition - 1], dataToProcess[firstStartDataPosition], dataToProcess[firstStartDataPosition + 1],
                    dataToProcess[firstStartDataPosition + 2], dataToProcess[firstStartDataPosition + 3], dataToProcess[firstStartDataPosition + 4 & 0xFF], dataToProcess[firstStartDataPosition + 5 & 0xFF], dataToProcess[firstStartDataPosition + 6 & 0xFF], dataToProcess[firstStartDataPosition + 7 & 0xFF], dataToProcess[firstStartDataPosition + 8 & 0xFF], dataToProcess[firstStartDataPosition + 9 & 0xFF], dataToProcess[firstStartDataPosition + 10 & 0xFF], dataToProcess[firstStartDataPosition + 11], dataToProcess[firstStartDataPosition + 12], dataToProcess[firstStartDataPosition + 13], dataToProcess[firstStartDataPosition + 14], dataToProcess[firstStartDataPosition + 15], dataToProcess[firstStartDataPosition + 16], dataToProcess[firstStartDataPosition + 17], dataToProcess[firstStartDataPosition + 18], dataToProcess[firstStartDataPosition + 19], dataToProcess[firstStartDataPosition + 20],
                    dataToProcess[firstStartDataPosition + 20]};
            }
            if (junctionID == 11) {
                arr11 = new byte[]{dataToProcess[firstStartDataPosition - 4], dataToProcess[firstStartDataPosition - 3], dataToProcess[firstStartDataPosition - 2], dataToProcess[firstStartDataPosition - 1], dataToProcess[firstStartDataPosition], dataToProcess[firstStartDataPosition + 1],
                    dataToProcess[firstStartDataPosition + 2], dataToProcess[firstStartDataPosition + 3], dataToProcess[firstStartDataPosition + 4 & 0xFF], dataToProcess[firstStartDataPosition + 5 & 0xFF], dataToProcess[firstStartDataPosition + 6 & 0xFF], dataToProcess[firstStartDataPosition + 7 & 0xFF], dataToProcess[firstStartDataPosition + 8 & 0xFF], dataToProcess[firstStartDataPosition + 9 & 0xFF], dataToProcess[firstStartDataPosition + 10 & 0xFF], dataToProcess[firstStartDataPosition + 11], dataToProcess[firstStartDataPosition + 12], dataToProcess[firstStartDataPosition + 13], dataToProcess[firstStartDataPosition + 14], dataToProcess[firstStartDataPosition + 15], dataToProcess[firstStartDataPosition + 16], dataToProcess[firstStartDataPosition + 17], dataToProcess[firstStartDataPosition + 18], dataToProcess[firstStartDataPosition + 19], dataToProcess[firstStartDataPosition + 20],
                    dataToProcess[firstStartDataPosition + 20]};
            }
            if (junctionID == 2) {
                arr1 = new byte[]{dataToProcess[firstStartDataPosition - 4], dataToProcess[firstStartDataPosition - 3], dataToProcess[firstStartDataPosition - 2], dataToProcess[firstStartDataPosition - 1], dataToProcess[firstStartDataPosition], dataToProcess[firstStartDataPosition + 1],
                    dataToProcess[firstStartDataPosition + 2], dataToProcess[firstStartDataPosition + 3], dataToProcess[firstStartDataPosition + 4 & 0xFF], dataToProcess[firstStartDataPosition + 5 & 0xFF], dataToProcess[firstStartDataPosition + 6 & 0xFF], dataToProcess[firstStartDataPosition + 7 & 0xFF], dataToProcess[firstStartDataPosition + 8 & 0xFF], dataToProcess[firstStartDataPosition + 9 & 0xFF], dataToProcess[firstStartDataPosition + 10 & 0xFF], dataToProcess[firstStartDataPosition + 11], dataToProcess[firstStartDataPosition + 12], dataToProcess[firstStartDataPosition + 13], dataToProcess[firstStartDataPosition + 14], dataToProcess[firstStartDataPosition + 15], dataToProcess[firstStartDataPosition + 16], dataToProcess[firstStartDataPosition + 17], dataToProcess[firstStartDataPosition + 18], dataToProcess[firstStartDataPosition + 19], dataToProcess[firstStartDataPosition + 20],
                    dataToProcess[firstStartDataPosition + 20]};
            }
            if (junctionID == 1) {
                arryatayat = new byte[]{dataToProcess[firstStartDataPosition - 4], dataToProcess[firstStartDataPosition - 3], dataToProcess[firstStartDataPosition - 2], dataToProcess[firstStartDataPosition - 1], dataToProcess[firstStartDataPosition], dataToProcess[firstStartDataPosition + 1],
                    dataToProcess[firstStartDataPosition + 2], dataToProcess[firstStartDataPosition + 3], dataToProcess[firstStartDataPosition + 4 & 0xFF], dataToProcess[firstStartDataPosition + 5 & 0xFF], dataToProcess[firstStartDataPosition + 6 & 0xFF], dataToProcess[firstStartDataPosition + 7 & 0xFF], dataToProcess[firstStartDataPosition + 8 & 0xFF], dataToProcess[firstStartDataPosition + 9 & 0xFF], dataToProcess[firstStartDataPosition + 10 & 0xFF], dataToProcess[firstStartDataPosition + 11], dataToProcess[firstStartDataPosition + 12], dataToProcess[firstStartDataPosition + 13], dataToProcess[firstStartDataPosition + 14], dataToProcess[firstStartDataPosition + 15], dataToProcess[firstStartDataPosition + 16], dataToProcess[firstStartDataPosition + 17], dataToProcess[firstStartDataPosition + 18], dataToProcess[firstStartDataPosition + 19], dataToProcess[firstStartDataPosition + 20],
                    dataToProcess[firstStartDataPosition + 20]};
            }
            if (junctionID == 4) {
                arrkatanga = new byte[]{dataToProcess[firstStartDataPosition - 4], dataToProcess[firstStartDataPosition - 3], dataToProcess[firstStartDataPosition - 2], dataToProcess[firstStartDataPosition - 1], dataToProcess[firstStartDataPosition], dataToProcess[firstStartDataPosition + 1],
                    dataToProcess[firstStartDataPosition + 2], dataToProcess[firstStartDataPosition + 3], dataToProcess[firstStartDataPosition + 4 & 0xFF], dataToProcess[firstStartDataPosition + 5 & 0xFF], dataToProcess[firstStartDataPosition + 6 & 0xFF], dataToProcess[firstStartDataPosition + 7 & 0xFF], dataToProcess[firstStartDataPosition + 8 & 0xFF], dataToProcess[firstStartDataPosition + 9 & 0xFF], dataToProcess[firstStartDataPosition + 10 & 0xFF], dataToProcess[firstStartDataPosition + 11], dataToProcess[firstStartDataPosition + 12], dataToProcess[firstStartDataPosition + 13], dataToProcess[firstStartDataPosition + 14], dataToProcess[firstStartDataPosition + 15], dataToProcess[firstStartDataPosition + 16], dataToProcess[firstStartDataPosition + 17], dataToProcess[firstStartDataPosition + 18], dataToProcess[firstStartDataPosition + 19], dataToProcess[firstStartDataPosition + 20],
                    dataToProcess[firstStartDataPosition + 20]};
            }
            if (junctionID == 5) {
                arrbaldeobag = new byte[]{dataToProcess[firstStartDataPosition - 4], dataToProcess[firstStartDataPosition - 3], dataToProcess[firstStartDataPosition - 2], dataToProcess[firstStartDataPosition - 1], dataToProcess[firstStartDataPosition], dataToProcess[firstStartDataPosition + 1],
                    dataToProcess[firstStartDataPosition + 2], dataToProcess[firstStartDataPosition + 3], dataToProcess[firstStartDataPosition + 4 & 0xFF], dataToProcess[firstStartDataPosition + 5 & 0xFF], dataToProcess[firstStartDataPosition + 6 & 0xFF], dataToProcess[firstStartDataPosition + 7 & 0xFF], dataToProcess[firstStartDataPosition + 8 & 0xFF], dataToProcess[firstStartDataPosition + 9 & 0xFF], dataToProcess[firstStartDataPosition + 10 & 0xFF], dataToProcess[firstStartDataPosition + 11], dataToProcess[firstStartDataPosition + 12], dataToProcess[firstStartDataPosition + 13], dataToProcess[firstStartDataPosition + 14], dataToProcess[firstStartDataPosition + 15], dataToProcess[firstStartDataPosition + 16], dataToProcess[firstStartDataPosition + 17], dataToProcess[firstStartDataPosition + 18], dataToProcess[firstStartDataPosition + 19], dataToProcess[firstStartDataPosition + 20],
                    dataToProcess[firstStartDataPosition + 20]};
            }
            if (junctionID == 6) {
                arrdeendayal = new byte[]{dataToProcess[firstStartDataPosition - 4], dataToProcess[firstStartDataPosition - 3], dataToProcess[firstStartDataPosition - 2], dataToProcess[firstStartDataPosition - 1], dataToProcess[firstStartDataPosition], dataToProcess[firstStartDataPosition + 1],
                    dataToProcess[firstStartDataPosition + 2], dataToProcess[firstStartDataPosition + 3], dataToProcess[firstStartDataPosition + 4 & 0xFF], dataToProcess[firstStartDataPosition + 5 & 0xFF], dataToProcess[firstStartDataPosition + 6 & 0xFF], dataToProcess[firstStartDataPosition + 7 & 0xFF], dataToProcess[firstStartDataPosition + 8 & 0xFF], dataToProcess[firstStartDataPosition + 9 & 0xFF], dataToProcess[firstStartDataPosition + 10 & 0xFF], dataToProcess[firstStartDataPosition + 11], dataToProcess[firstStartDataPosition + 12], dataToProcess[firstStartDataPosition + 13], dataToProcess[firstStartDataPosition + 14], dataToProcess[firstStartDataPosition + 15], dataToProcess[firstStartDataPosition + 16], dataToProcess[firstStartDataPosition + 17], dataToProcess[firstStartDataPosition + 18], dataToProcess[firstStartDataPosition + 19], dataToProcess[firstStartDataPosition + 20],
                    dataToProcess[firstStartDataPosition + 20]};
            }
            if (junctionID == 7) {
                arrhighcourt = new byte[]{dataToProcess[firstStartDataPosition - 4], dataToProcess[firstStartDataPosition - 3], dataToProcess[firstStartDataPosition - 2], dataToProcess[firstStartDataPosition - 1], dataToProcess[firstStartDataPosition], dataToProcess[firstStartDataPosition + 1],
                    dataToProcess[firstStartDataPosition + 2], dataToProcess[firstStartDataPosition + 3], dataToProcess[firstStartDataPosition + 4 & 0xFF], dataToProcess[firstStartDataPosition + 5 & 0xFF], dataToProcess[firstStartDataPosition + 6 & 0xFF], dataToProcess[firstStartDataPosition + 7 & 0xFF], dataToProcess[firstStartDataPosition + 8 & 0xFF], dataToProcess[firstStartDataPosition + 9 & 0xFF], dataToProcess[firstStartDataPosition + 10 & 0xFF], dataToProcess[firstStartDataPosition + 11], dataToProcess[firstStartDataPosition + 12], dataToProcess[firstStartDataPosition + 13], dataToProcess[firstStartDataPosition + 14], dataToProcess[firstStartDataPosition + 15], dataToProcess[firstStartDataPosition + 16], dataToProcess[firstStartDataPosition + 17], dataToProcess[firstStartDataPosition + 18], dataToProcess[firstStartDataPosition + 19], dataToProcess[firstStartDataPosition + 20],
                    dataToProcess[firstStartDataPosition + 20]};
            }
            if (junctionID == 8) {
                arrbloomchowk = new byte[]{dataToProcess[firstStartDataPosition - 4], dataToProcess[firstStartDataPosition - 3], dataToProcess[firstStartDataPosition - 2], dataToProcess[firstStartDataPosition - 1], dataToProcess[firstStartDataPosition], dataToProcess[firstStartDataPosition + 1],
                    dataToProcess[firstStartDataPosition + 2], dataToProcess[firstStartDataPosition + 3], dataToProcess[firstStartDataPosition + 4 & 0xFF], dataToProcess[firstStartDataPosition + 5 & 0xFF], dataToProcess[firstStartDataPosition + 6 & 0xFF], dataToProcess[firstStartDataPosition + 7 & 0xFF], dataToProcess[firstStartDataPosition + 8 & 0xFF], dataToProcess[firstStartDataPosition + 9 & 0xFF], dataToProcess[firstStartDataPosition + 10 & 0xFF], dataToProcess[firstStartDataPosition + 11], dataToProcess[firstStartDataPosition + 12], dataToProcess[firstStartDataPosition + 13], dataToProcess[firstStartDataPosition + 14], dataToProcess[firstStartDataPosition + 15], dataToProcess[firstStartDataPosition + 16], dataToProcess[firstStartDataPosition + 17], dataToProcess[firstStartDataPosition + 18], dataToProcess[firstStartDataPosition + 19], dataToProcess[firstStartDataPosition + 20],
                    dataToProcess[firstStartDataPosition + 20]};
            }
            if (junctionID == 12) {
                arrmadanmahal = new byte[]{dataToProcess[firstStartDataPosition - 4], dataToProcess[firstStartDataPosition - 3], dataToProcess[firstStartDataPosition - 2], dataToProcess[firstStartDataPosition - 1], dataToProcess[firstStartDataPosition], dataToProcess[firstStartDataPosition + 1],
                    dataToProcess[firstStartDataPosition + 2], dataToProcess[firstStartDataPosition + 3], dataToProcess[firstStartDataPosition + 4 & 0xFF], dataToProcess[firstStartDataPosition + 5 & 0xFF], dataToProcess[firstStartDataPosition + 6 & 0xFF], dataToProcess[firstStartDataPosition + 7 & 0xFF], dataToProcess[firstStartDataPosition + 8 & 0xFF], dataToProcess[firstStartDataPosition + 9 & 0xFF], dataToProcess[firstStartDataPosition + 10 & 0xFF], dataToProcess[firstStartDataPosition + 11], dataToProcess[firstStartDataPosition + 12], dataToProcess[firstStartDataPosition + 13], dataToProcess[firstStartDataPosition + 14], dataToProcess[firstStartDataPosition + 15], dataToProcess[firstStartDataPosition + 16], dataToProcess[firstStartDataPosition + 17], dataToProcess[firstStartDataPosition + 18], dataToProcess[firstStartDataPosition + 19], dataToProcess[firstStartDataPosition + 20],
                    dataToProcess[firstStartDataPosition + 20]};
            }

            //byte[] arr2 = arr1;
            int appHr = 0, appMin = 0, appDat = 0, appMonth = 0, appYear = 0;
            Calendar cald = Calendar.getInstance();
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateTime = sd.format(cald.getTime());

            String appDate = dateTime.split(" ")[0];
            String appTime = dateTime.split(" ")[1];

            String time[] = appTime.split(":");
            String dte[] = appDate.split("-");

            appHr = Integer.parseInt(time[0]);
            appMin = Integer.parseInt(time[1]);

            appDat = Integer.parseInt(dte[2]);
            appMonth = Integer.parseInt(dte[1]);
            appYear = Integer.parseInt(dte[0].substring(2));

            //---------------------------- code started to manage one minute difference in junction time and in application time  ------------------------------------------
            int monthNo = (juncMonth - appMonth) == Math.abs(1) ? juncMonth > appMonth ? appMonth : juncMonth : 0;

            if (((juncYear - appYear) == Math.abs(1)) && ((juncMonth - appMonth) == Math.abs(11)) && ((juncDat - appDat) == Math.abs(30)) && ((juncHr - appHr) == Math.abs(23)) && ((juncMin - appMin) == Math.abs(59))) { //last minute of last hour of last day of last month of a year
                juncHr = appHr;
                juncMin = appMin;
                juncDat = appDat;
                juncMonth = appMonth;
                juncYear = appYear;
            } else if (((juncMonth - appMonth) == Math.abs(1)) && ((juncDat - appDat) == Math.abs(getNoOfDays(monthNo, appYear) - 1)) && ((juncHr - appHr) == Math.abs(23)) && ((juncMin - appMin) == Math.abs(59))) { //last minute of last hour of last day of a month
                juncHr = appHr;
                juncMin = appMin;
                juncDat = appDat;
                juncMonth = appMonth;
            } else if (((juncDat - appDat) == Math.abs(1)) && ((juncHr - appHr) == Math.abs(23)) && ((juncMin - appMin) == Math.abs(59))) { //last minute of last hour of a day
                juncHr = appHr;
                juncMin = appMin;
                juncDat = appDat;
            } else if (((juncHr - appHr) == Math.abs(1)) && ((juncMin - appMin) == Math.abs(59))) { //last minute of an hour
                juncHr = appHr;
                juncMin = appMin;
            } else if ((juncMin - appMin) == Math.abs(1)) { //a minute
                juncMin = appMin;
            }
            //---------------------------- code endeded to manage one minute difference in junction time and in application time  ------------------------------------------

            String currentTimeSynchronizationStatus = this.clientResponderModel.getCurrentTimeSynchronizationStatus(juncHr, juncMin, juncDat, juncMonth, juncYear, appHr, appMin, appDat, appMonth, appYear);

            String side13Status = Integer.toBinaryString(unsignedSide13);
            String side24Status = Integer.toBinaryString(unsignedSide24);

            String miscByte = Integer.toBinaryString(unsignedMiscByte);

            int fileNoFromDB = this.clientResponderModel.getFileNo(junctionID, program_version_no);
            if (fileNo != fileNoFromDB) {
                this.clientResponderModel.updateFileNo(junctionID, fileNo, program_version_no);
            }
            fileNoFromDB = this.clientResponderModel.getFileNo(junctionID, program_version_no);
            int programVersionNoFromDB = this.clientResponderModel.getProgramVersionNo(junctionID);

            String junctionName = this.clientResponderModel.getJunctionName(junctionID, program_version_no);
            int noOfSides = this.clientResponderModel.getNoOfSides(junctionID, program_version_no);
            List sideNameList = new ArrayList(5);
            for (int j = 1; j <= noOfSides; j++) {
                String sideName = this.clientResponderModel.getSideName(j, junctionID, program_version_no);
                sideNameList.add(sideName);
            }

            System.out.println("refresh  request from modem--- " + firstStartDelimiter + " " + secStartDelimiter + " " + dataSize1 + " " + dataSize2
                    + " " + junctionID + " " + program_version_no + " " + fileNo + " " + functionNo + " " + side1Time + " " + side2Time + " " + side3Time + " " + side4Time + " "
                    + unsignedSide13 + " " + unsignedSide24 + " " + unsignedMiscByte + " " + plan_no + " " + plan_mode + " " + activity + " "
                    + juncHr + " " + juncMin + " " + juncDat + " " + juncMonth + " " + juncYear + " "
                    + crc + " " + firstLastDelimiter + " " + secLastDelimiter);

            switch (side13Status.length()) {
                case 5:
                    side13Status = "000" + side13Status;
                    break;
                case 6:
                    side13Status = "00" + side13Status;
                    break;
                case 7:
                    side13Status = "0" + side13Status;
            }

            switch (side24Status.length()) {
                case 5:
                    side24Status = "000" + side24Status;
                    break;
                case 6:
                    side24Status = "00" + side24Status;
                    break;
                case 7:
                    side24Status = "0" + side24Status;
            }

            switch (miscByte.length()) {
                case 5:
                    miscByte = "000" + miscByte;
                    break;
                case 6:
                    miscByte = "00" + miscByte;
                    break;
                case 7:
                    miscByte = "0" + miscByte;
            }

            System.out.println("side13Status : " + side13Status.length() + " , " + side13Status);
            System.out.println("side24Status : " + side24Status.length() + " , " + side24Status);
            System.out.println("miscByte     : " + miscByte.length() + " , " + miscByte);

            side13 = side13Status.trim().split("");
            side24 = side24Status.trim().split("");

            if (!testRequestFromJunction) {

                planInfo.setFunction_no(functionNo);
                planInfo.setJunction_name(junctionName);
                if (noOfSides == 2) {
                    planInfo.setSide1Name((String) sideNameList.get(0));
                    planInfo.setSide2Name((String) sideNameList.get(1));
                } else if (noOfSides == 3) {
                    planInfo.setSide1Name((String) sideNameList.get(0));
                    planInfo.setSide2Name((String) sideNameList.get(1));
                    planInfo.setSide3Name((String) sideNameList.get(2));
                } else if (noOfSides == 4) {
                    planInfo.setSide1Name((String) sideNameList.get(0));
                    planInfo.setSide2Name((String) sideNameList.get(1));
                    planInfo.setSide3Name((String) sideNameList.get(2));
                    planInfo.setSide4Name((String) sideNameList.get(3));
                } else if (noOfSides == 5) {
                    planInfo.setSide1Name((String) sideNameList.get(0));
                    planInfo.setSide2Name((String) sideNameList.get(1));
                    planInfo.setSide3Name((String) sideNameList.get(2));
                    planInfo.setSide4Name((String) sideNameList.get(3));
                    planInfo.setSide5Name((String) sideNameList.get(4));
                }
                planInfo.setJunction_id(junctionID);
                planInfo.setProgram_version_no(program_version_no);
                planInfo.setFileNo(fileNo);
                planInfo.setPlan_no(plan_no);
                planInfo.setOnTime(onTime);
                planInfo.setOffTime(offTime);
                planInfo.setMode(plan_mode == 1 ? "Blinker" : plan_mode == 2 ? "Signal" : "Signal With Pedestrian");
                planInfo.setSide1_time(side1Time);
                planInfo.setSide2_time(side2Time);
                planInfo.setSide3_time(side3Time);
                planInfo.setSide4_time(side4Time);

                System.out.println("side13 size: " + side13.length);
                System.out.println("side24 size: " + side24.length);
                int beginIndex = 0;
                try {
                    if (side13.length == 9) {
                        beginIndex = 1;
                    }
                    planInfo.setSide1_left_status(Integer.parseInt(side13[beginIndex]));
                    planInfo.setSide1_right_status(Integer.parseInt(side13[beginIndex + 1]));
                    planInfo.setSide1_up_status(Integer.parseInt(side13[beginIndex + 2]));
                    planInfo.setSide1_down_status(Integer.parseInt(side13[beginIndex + 3]));
                    planInfo.setSide3_left_status(Integer.parseInt(side13[beginIndex + 4]));
                    planInfo.setSide3_right_status(Integer.parseInt(side13[beginIndex + 5]));
                    planInfo.setSide3_up_status(Integer.parseInt(side13[beginIndex + 6]));
                    planInfo.setSide3_down_status(Integer.parseInt(side13[beginIndex + 7]));

                    planInfo.setSide2_left_status(Integer.parseInt(side24[beginIndex]));
                    planInfo.setSide2_right_status(Integer.parseInt(side24[beginIndex + 1]));
                    planInfo.setSide2_up_status(Integer.parseInt(side24[beginIndex + 2]));
                    planInfo.setSide2_down_status(Integer.parseInt(side24[beginIndex + 3]));
                    planInfo.setSide4_left_status(Integer.parseInt(side24[beginIndex + 4]));
                    planInfo.setSide4_right_status(Integer.parseInt(side24[beginIndex + 5]));
                    planInfo.setSide4_up_status(Integer.parseInt(side24[beginIndex + 6]));
                    planInfo.setSide4_down_status(Integer.parseInt(side24[beginIndex + 7]));

                } catch (Exception e) {
                    System.out.println("Error in side1 2 3 4 status: " + e);
                }

                planInfo.setJuncHr(juncHr);
                planInfo.setJuncMin(juncMin);
                planInfo.setJuncDate(juncDat);
                planInfo.setJuncMonth(juncMonth);
                planInfo.setJuncYear(juncYear);

                try {
                    this.planInfoRefreshList.setResponseFromModemForRefresh(true);
                    this.ctx.setAttribute("planInfolist", this.planInfoRefreshList);
//                    if(junctionID == 13){
//                    this.teenPattiInfoRefreshList.setResponseFromModemForRefresh(true);
//                    this.ctx.setAttribute("teenPattiInfolist", this.teenPattiInfoRefreshList);
//                    }
//                    if(junctionID == 11){
//                    this.ranitalInfoRefreshList.setResponseFromModemForRefresh(true);
//                    this.ctx.setAttribute("ranitalInfolist", this.ranitalInfoRefreshList);  
//                    }                   
                    if (activity == 2) {
                        this.junction.setResponseFromModemForClearance(true);
                    } else {
                        this.junction.setResponseFromModemForActivity(true);
                    }
                    System.out.println("client responder RequestForClearance: " + isRequestForClearance());
                    System.out.println("client responder requestForActivity: " + isRequestForActivity());
                    
                    //PUTTING ctx
                    this.ctx.setAttribute("junction", this.junction);
                    this.modemResReadSaveCont.setCtx(this.ctx);

                    this.requestForActivity = (Boolean) (ctx.getAttribute("requestForActivity" + junctionID) == null ? false : ctx.getAttribute("requestForActivity" + junctionID));
                    this.requestForClearance = (Boolean) (ctx.getAttribute("requestForClearance" + junctionID) == null ? false : ctx.getAttribute("requestForClearance" + junctionID));
                    this.activityNo = (Integer) (ctx.getAttribute("activityNo" + junctionID) == null ? 0 : ctx.getAttribute("activityNo" + junctionID));
                    this.activitySide = (Integer) (ctx.getAttribute("activitySide" + junctionID) == null ? 0 : ctx.getAttribute("activitySide" + junctionID));
                    this.clearanceSide = (Integer) (ctx.getAttribute("clearanceSide" + junctionID) == null ? 0 : ctx.getAttribute("clearanceSide" + junctionID));

                    activity = receivedActivity == 4 || receivedActivity == 6 ? 1 : this.requestForActivity ? this.activityNo : this.requestForClearance ? 2 : 1;
                    activity1 = receivedActivity == 4 || receivedActivity == 6 ? 1
                            : this.requestForActivity ? this.activityNo == 4 || this.activityNo == 6 ? this.activitySide : 1
                                    : this.requestForClearance ? this.clearanceSide : 1;

                    ctx.setAttribute("requestForActivity" + junctionID, false);
                    // ctx.setAttribute("requestForActivity" + junctionID, true);
                    //                   ctx.setAttribute("requestForClearance" + junctionID, false);
                    // ctx.setAttribute("activityNo" + junctionID, 0);
                    ctx.setAttribute("activitySide" + junctionID, 0);
                    //                   ctx.setAttribute("clearanceSide" + junctionID, 0);

                    planInfo.setActivity(activity);
                    planInfo.setSide_no(activity1);

                    if (receivedActivity == 4 || receivedActivity == 6) {
                        this.requestForActivity = false;
                    }

                    planInfo.setResponseFromModemForRefresh(true);

                    this.planInfoRefreshList = planInfo;
                    this.ctx.setAttribute("planInfolist", this.planInfoRefreshList);
                    if (junctionID == 2) {
//                        BeanUtils.copyProperties(damohNakaInfo, planInfo);
//                        damohNakaInfoRefreshList = damohNakaInfo;
                        //this.ctx.setAttribute("damohNakaInfoList", this.damohNakaInfoRefreshList);
                        this.ctx.setAttribute("damohNakaInfoList", this.planInfoRefreshList);
                    }
                    if (junctionID == 13) {
//                        BeanUtils.copyProperties(teenPattiInfo, planInfo);
//                        teenPattiInfoRefreshList = teenPattiInfo;
//                        this.ctx.setAttribute("teenPattiInfoList", this.teenPattiInfoRefreshList);
                          this.ctx.setAttribute("teenPattiInfoList", this.planInfoRefreshList);
                    }
                    if (junctionID == 11) {
//                        BeanUtils.copyProperties(ranitalInfo, planInfo);
//                        ranitalInfoRefreshList = ranitalInfo;
//                        this.ctx.setAttribute("ranitalInfoList", this.ranitalInfoRefreshList);
                          this.ctx.setAttribute("ranitalInfoList", this.planInfoRefreshList);
                    }
                    if (junctionID == 1) {
//                        BeanUtils.copyProperties(yatayatThanaInfo, planInfo);
//                        yatayatThanaInfoRefreshList = yatayatThanaInfo;
//                        this.ctx.setAttribute("yatayatThanaInfoList", this.yatayatThanaInfoRefreshList);
                          this.ctx.setAttribute("yatayatThanaInfoList", this.planInfoRefreshList);
                    }
                    if (junctionID == 4) {
//                        BeanUtils.copyProperties(katangaInfo, planInfo);
//                        katangaInfoRefreshList = katangaInfo;
//                        this.ctx.setAttribute("katangaInfoList", this.katangaInfoRefreshList);
                          this.ctx.setAttribute("katangaInfoList", this.planInfoRefreshList);
                    }
                    if (junctionID == 5) {
//                        BeanUtils.copyProperties(baldeobagInfo, planInfo);
//                        baldeobagInfoRefreshList = baldeobagInfo;
//                        this.ctx.setAttribute("baldeobagInfoList", this.baldeobagInfoRefreshList);
                        this.ctx.setAttribute("baldeobagInfoList", this.planInfoRefreshList);
                    }
                    if (junctionID == 6) {
//                        BeanUtils.copyProperties(deendayalChowkInfo, planInfo);
//                        deendayalChowkInfoRefreshList = deendayalChowkInfo;
//                        this.ctx.setAttribute("deendayalChowkInfoList", this.deendayalChowkInfoRefreshList);
                          this.ctx.setAttribute("deendayalChowkInfoList", this.planInfoRefreshList);
                    }
                    if (junctionID == 7) {
//                        BeanUtils.copyProperties(highCourtInfo, planInfo);
//                        highCourtInfoRefreshList = highCourtInfo;
//                        this.ctx.setAttribute("highCourtInfoList", this.highCourtInfoRefreshList);
                          this.ctx.setAttribute("highCourtInfoList", this.planInfoRefreshList);
                    }
                    if (junctionID == 8) {
//                        BeanUtils.copyProperties(bloomChowkInfo, planInfo);
//                        bloomChowkInfoRefreshList = bloomChowkInfo;
//                        this.ctx.setAttribute("bloomChowkInfoList", this.bloomChowkInfoRefreshList);
                          this.ctx.setAttribute("bloomChowkInfoList", this.planInfoRefreshList);
                    }
                    if (junctionID == 12) {
//                        BeanUtils.copyProperties(madanMahalInfo, planInfo);
//                        madanMahalInfoRefreshList = madanMahalInfo;
//                        this.ctx.setAttribute("madanMahalInfoList", this.madanMahalInfoRefreshList);
                          this.ctx.setAttribute("madanMahalInfoList", this.planInfoRefreshList);
                    }

                    this.ctx.setAttribute("junction", this.junction);
                    this.modemResReadSaveCont.setCtx(this.ctx);
                    //this.planInfoRefreshList = planInfo;
                } catch (Exception e) {
                    System.out.println("ClientResponder junctionRefresh PlanInfoListContext error :" + e);
                }
            }

            activity = program_version_no == programVersionNoFromDB ? fileNo == fileNoFromDB ? currentTimeSynchronizationStatus.equals("Y") ? activity : testRequestFromJunction ? 1 : 9 : 8 : 7;
            activity1 = activity == 7 || activity == 8 || activity == 9 ? 1 : activity1;

            String pureBytes = null;
            pureBytes = tsws.arr2;
            String activeReq = clientResponderModel.getActiveRequest(junctionID);
//            activity1 = pureBytes[11];
            if(mapData() != null) {
                String data = mapData();
                junctionID = Integer.parseInt(data.split("_")[0]);
                if (junctionID == 2) {
                        activity = 2;
                        activity1 = Integer.parseInt(data.split("_")[1]);
                    }
                    if (junctionID == 13) {
                        activity = 2;
                        activity1 = Integer.parseInt(data.split("_")[1]);
                    }
                    if (junctionID == 11) {
                        activity = 2;
                        activity1 = Integer.parseInt(data.split("_")[1]);
                    }
                    if (junctionID == 1) {
                        activity = 2;
                        activity1 = Integer.parseInt(data.split("_")[1]);
                    }
                    if (junctionID == 4) {
                        activity = 2;
                        activity1 = Integer.parseInt(data.split("_")[1]);
                    }
                    if (junctionID == 5) {
                        activity = 2;
                        activity1 = Integer.parseInt(data.split("_")[1]);
                    }
                    if (junctionID == 6) {
                        activity = 2;
                        activity1 = Integer.parseInt(data.split("_")[1]);
                    }
                    if (junctionID == 7) {
                        activity = 2;
                        activity1 = Integer.parseInt(data.split("_")[1]);
                    }
                    if (junctionID == 8) {
                        activity = 2;
                        activity1 = Integer.parseInt(data.split("_")[1]);
                    }
                    if (junctionID == 12) {
                        activity = 2;
                        activity1 = Integer.parseInt(data.split("_")[1]);
                    }
            }

            System.out.println("activity bytes: " + activity + " " + activity1 + " " + activity2 + " " + activity3);

            if (activeReq.equals("Y")) {
                response = pureBytes;
            } else {
                response = secStartDelimiter + " " + secStartDelimiter + " " + secStartDelimiter + " " + secStartDelimiter + " " + junctionID + " " + programVersionNoFromDB + " " + fileNoFromDB + " " + functionNo + " " + activity + " " + activity1 + " " + activity2 + " " + activity3 + " " + extraBytes + " " + firstLastDelimiter + " " + firstLastDelimiter;
            }
        } catch (Exception e) {
            System.out.println("PlanInfoListContext error :" + e);
        }
        return response;
    }*/
    
    public String mapData() {
        if(TrafficSignalWebServices.flag == 0 || TrafficSignalWebServices.mapData.equals("normal")) {
            TrafficSignalWebServices.flag = 0;
                return null;
        }
         return TrafficSignalWebServices.mapData;
        
    }

    public String SunriseSunsetDetails(byte[] dataToProcess, int firstStartDataPosition) {
        String response = null;
        int firstStartDelimiter = dataToProcess[firstStartDataPosition - 4];
        int secStartDelimiter = dataToProcess[firstStartDataPosition - 3];
        int dataSize1 = dataToProcess[firstStartDataPosition - 2];
        int dataSize2 = dataToProcess[firstStartDataPosition - 1];
        int junctionID = dataToProcess[firstStartDataPosition];
        int program_version_no = dataToProcess[firstStartDataPosition + 1];
        int fileNo = dataToProcess[firstStartDataPosition + 2];
        int functionNo = dataToProcess[firstStartDataPosition + 3];
        int month = dataToProcess[firstStartDataPosition + 4];
        int crc = dataToProcess[firstStartDataPosition + 5] & 0xFF;
        int firstLastDelimiter = dataToProcess[firstStartDataPosition + 6];
        int secLastDelimiter = dataToProcess[firstStartDataPosition + 7];
        int cityID = this.clientResponderModel.getCityName(junctionID);
        System.out.println("sunrise sunset  request--- " + firstStartDelimiter + " " + secStartDelimiter + " " + dataSize1 + " " + dataSize2 + " " + junctionID + " " + program_version_no + " " + fileNo + " " + functionNo + " " + month + " " + crc + " " + firstLastDelimiter + " " + secLastDelimiter);
        List riseSetTime = this.clientResponderModel.getCitySunriseSunset(month, cityID);

        int fileNoFromDB = this.clientResponderModel.getFileNo(junctionID, program_version_no);
        if (fileNo != fileNoFromDB) {
            this.clientResponderModel.updateFileNo(junctionID, fileNo, program_version_no);
        }
        fileNoFromDB = this.clientResponderModel.getFileNo(junctionID, program_version_no);
        int programVersionNoFromDB = this.clientResponderModel.getProgramVersionNo(junctionID);

        response = firstStartDelimiter + " " + secStartDelimiter + " " + firstStartDelimiter + " " + secStartDelimiter + " " + junctionID + " " + programVersionNoFromDB + " " + fileNoFromDB + " " + functionNo + " " + month;
        Iterator iter = riseSetTime.iterator();
        while (iter.hasNext()) {
            response = response + " " + (Integer) iter.next();
        }
        response = response + " " + firstLastDelimiter + " " + secLastDelimiter;
        //System.out.println("Response of sunrisr sunset :" + response);
        return response;
    }

    public String JunctionSideNames(byte[] dataToProcess, int firstStartDataPosition) {
        String response = null;
        int firstStartDelimiter = dataToProcess[firstStartDataPosition - 4];
        int secStartDelimiter = dataToProcess[firstStartDataPosition - 3];
        int dataSize1 = dataToProcess[firstStartDataPosition - 2];
        int dataSize2 = dataToProcess[firstStartDataPosition - 1];
        int junctionID = dataToProcess[firstStartDataPosition];
        int program_version_no = dataToProcess[firstStartDataPosition + 1];
        int fileNo = dataToProcess[firstStartDataPosition + 2];
        int functionNo = dataToProcess[firstStartDataPosition + 3];
        int crc = dataToProcess[firstStartDataPosition + 4] & 0xFF;
        int firstLastDelimiter = dataToProcess[firstStartDataPosition + 5];
        int secLastDelimiter = dataToProcess[firstStartDataPosition + 6];

        int fileNoFromDB = this.clientResponderModel.getFileNo(junctionID, program_version_no);
        if (fileNo != fileNoFromDB) {
            this.clientResponderModel.updateFileNo(junctionID, fileNo, program_version_no);
        }
        fileNoFromDB = this.clientResponderModel.getFileNo(junctionID, program_version_no);
        int programVersionNoFromDB = this.clientResponderModel.getProgramVersionNo(junctionID);

//        Junction juncID = (Junction) this.junctionList.get(Integer.valueOf(junctionID));
//        boolean regisStatus = juncID.isRegistration_status();
        System.out.println("junction side names request--- " + firstStartDelimiter + " " + secStartDelimiter + " " + dataSize1 + " " + dataSize2 + " " + junctionID + " " + program_version_no + " " + fileNo + " " + functionNo + " " + firstLastDelimiter + " " + secLastDelimiter);
        int noOfSides = this.clientResponderModel.getNoOfSides(junctionID, program_version_no);
        // response = firstStartDelimiter + " " + secStartDelimiter + " " + firstStartDelimiter + " " + secStartDelimiter + " " + junctionID + " " + functionNo ;
        response = junctionID + " " + program_version_no + " " + fileNoFromDB + " " + functionNo;
        List sideNameList = new ArrayList();
        sideNameList.add(this.clientResponderModel.getJunctionName(junctionID, program_version_no));
        for (int j = 1; j <= noOfSides; j++) {
            String sideName = this.clientResponderModel.getSideName(j, junctionID, program_version_no);
            sideNameList.add(sideName);
        }
        Iterator itr = sideNameList.iterator();
        while (itr.hasNext()) {
            response = response + " " + (String) itr.next();
        }
        response = firstStartDelimiter + " " + secStartDelimiter + " " + firstStartDelimiter + " " + secStartDelimiter + " " + response + " " + firstLastDelimiter + " " + secLastDelimiter;
        this.totalNoOfSides = noOfSides;

        return response;
    }

    public boolean sendResponse(String response)
            throws IOException {
        boolean status = false;
        byte[] finalBytes = null;
        if (response != null) {
            String[] b1 = response.split(" ");
            // byte[] bytes = new byte[b1.length];
            finalBytes = new byte[b1.length + 3];
            int[] k = new int[b1.length];
            for (int j = 0; j < b1.length; j++) {
                k[j] = Integer.parseInt(b1[j]);
                //   bytes[j] = ((byte) k[j]);
            }
            finalBytes = getFinalBytes(k);
            //this.outputStream.write(finalBytes);
            System.out.print("Server Response: ");
            for (int j = 0; j < finalBytes.length; j++) {
                System.out.print(finalBytes[j] + " ");
            }
            System.out.println(" ");
        }
        status = true;
        return status;
    }

    public byte[] sendResponseData(String response)
            throws IOException {
        boolean status = false;
        byte[] finalBytes = null;
        if (response != null) {
            String[] b1 = response.split(" ");
            // byte[] bytes = new byte[b1.length];
            finalBytes = new byte[b1.length + 3];
            int[] k = new int[b1.length];
            for (int j = 0; j < b1.length; j++) {
                k[j] = Integer.parseInt(b1[j]);
                //   bytes[j] = ((byte) k[j]);
            }
            finalBytes = getFinalBytes(k);
            //this.outputStream.write(finalBytes);
            System.out.print("Server Response: ");
            for (int j = 0; j < finalBytes.length; j++) {
                System.out.print(finalBytes[j] + " ");
            }
            System.out.println(" ");
        }
        status = true;
        return finalBytes;
    }

    public void sendJunctionResponse(String response) throws IOException {
        if (response != null) {
            String[] b1 = response.split(" ");
//            byte[] bytes = new byte[b1.length - (this.totalNoOfSides + 2)];
//            byte[] bytes1 = null;
//            byte[] bytes2 = new byte[2];
//            int[] k = new int[b1.length];
//            int j = 0;
//            for (j = 0; j <= 3; j++) {
//                k[j] = Integer.parseInt(b1[j]);
//                bytes[j] = ((byte) k[j]);
//            }
//
//            this.outputStream.write(bytes);
//            for (; j < b1.length - 2; j++) {
//                this.outputStream.write(32);
//
//                bytes1 = b1[j].getBytes();
//                this.outputStream.write(bytes1);
//                bytes1 = null;
//            }
//            this.outputStream.write(32);
//            for (int i = 0; j < b1.length; i++) {
//                k[j] = Integer.parseInt(b1[j]);
//                bytes2[i] = ((byte) k[j]);
//
//                j++;
//            }
//            this.outputStream.write(bytes2);

            byte[] startbytes = new byte[6];
            byte[] midbytes = null;
            byte[] endbytes = new byte[2];
            int[] k = new int[b1.length];
            int j = 0;
            for (j = 0; j < startbytes.length; j++) {
                k[j] = Integer.parseInt(b1[j]);
                startbytes[j] = ((byte) k[j]);
            }
            this.outputStream.write(startbytes);

            for (; j < b1.length - 2; j++) {
                this.outputStream.write(32);
                midbytes = b1[j].getBytes();
                this.outputStream.write(midbytes);
                midbytes = null;
            }

            this.outputStream.write(32);

            for (int i = 0; j < b1.length; j++) {
                k[j] = Integer.parseInt(b1[j]);
                endbytes[i] = ((byte) k[j]);
                i++;
            }
            this.outputStream.write(endbytes);
            System.out.println("Done!!");
        }
    }

    public boolean sendErrorResponse(int error) throws IOException {
        String errorResponse = "126 126 126 126 0 0 0 6 " + error + " 0 0 0 125 125";
        return sendResponse(errorResponse);
    }

    public boolean sendErrorResponse(String response)
            throws IOException {
        boolean status = false;
        if (response != null) {
            byte[] bytes = response.getBytes();
            this.outputStream.write(bytes);
        }
        status = true;
        return status;
    }

    public byte[] getFinalBytes(int[] k) {
        int length = k.length;

        int dSize = 0;
        int dSum = 0;

        byte dataSize[] = new byte[2];
        byte[] finalBytes = new byte[length + 3];
        try {

            for (int i = 4; i < (length - 2); i++) {
                dSum += k[i];
                dSize++;
            }

            dataSize[0] = (byte) ((dSize >> 8) & 0xFF); //>> 8 discards the lowest 8 bits by moving all bits 8 places to the right.
            dataSize[1] = (byte) (dSize & 0xFF); //& 0xFF masks the lowest eight bits.

            byte lowByte = (byte) (dSum & 0xFF); //get lowest 8-bit
            byte crcByte = (byte) (0xFF - lowByte); // subtract lowest 8-bit from 255

            for (int i = 0, j = 0; j < finalBytes.length; j++) {
                if (j == 4) {
                    finalBytes[j] = dataSize[0];
                } else if (j == 5) {
                    finalBytes[j] = dataSize[1];
                } else if (j == finalBytes.length - 3) {
                    finalBytes[j] = crcByte;
                } else {
                    finalBytes[j] = ((byte) k[i]);
                    i++;
                }
            }

        } catch (Exception e) {
            System.out.println("Exception in ClientResponder getFinalBytes: " + e);
        }
        return finalBytes;
    }

    public int calculateTimeInSeconds(String Time) {
        String[] currTime = Time.split(" ");
        String strTime = currTime[1];

        String[] currStr = strTime.split(":");
        int Hr = Integer.parseInt(currStr[0]);
        int Min = Integer.parseInt(currStr[1]);
        int Sec = Integer.parseInt(currStr[2]);
        int calculatedTime = Hr * 60 * 60 + Min * 60 + Sec;

        return calculatedTime;
    }

    public int getNoOfDays(int month_no, int year) {
        int noOfDays = 0;
        try {
            if (month_no == 2) { // february
                if (year % 4 == 0) {
                    noOfDays = 29; //leap year
                } else {
                    noOfDays = 28;
                }
            } else if (month_no == 4 || month_no == 6 || month_no == 9 || month_no == 11) {
                noOfDays = 30;
            } else {
                noOfDays = 31;
            }
        } catch (Exception e) {
            System.out.println("ClientResponder getNoOfDays error: " + e);
        }
        return noOfDays;
    }

    private String callURL(String strURL) {
        String responseMsg;
        try {
            URL urlObj = new URL(strURL);
            HttpURLConnection httpReq = (HttpURLConnection) urlObj.openConnection();
            httpReq.setDoOutput(true);
            httpReq.setInstanceFollowRedirects(true);
            httpReq.setRequestMethod("GET");
            responseMsg = readWebServerResponse(httpReq.getInputStream());
        } catch (MalformedURLException mfUrlEx) {
            responseMsg = "Invalid URL or unknown protocol.";
        } catch (IOException ioEx) {
            responseMsg = "Unable to connect to the WebServer.";
        } catch (Exception ex) {
            responseMsg = ex.toString();
        }
        return responseMsg;
    }

    private String readWebServerResponse(InputStream inputStream) {
        BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
        String response = "";
        try {
            String nextLine = rd.readLine();
            while (nextLine != null) {
                response = response + nextLine;
                nextLine = rd.readLine();
            }
        } catch (IOException ioEx) {
            response = "Unable to read response from the WebServer.";
            System.out.println("ClientResponseReader readWebServerResponse() Error: " + ioEx);
        }
        return response;
    }

    public void setClientResponse(String message) {
        try {
            message = URLEncoder.encode(message, "UTF-8");
        } catch (UnsupportedEncodingException uEx) {
            System.out.println("ClientResponseReader setClientResponse() Error: " + uEx);
        }

        String url = "http://localhost/ts/modemResReadSaveCont?task=setClientResponse&message=" + message;
        callURL(url);
    }

    public String updateModemTime(String[] inputs) {
        String response;
        try {
            String hours = inputs[1];
            String minutes = inputs[2];
            String seconds = inputs[3];

            String url = "http://localhost/ts/?req=modem&h=" + hours + "&m=" + minutes + "&s=" + seconds;
            response = callURL(url);
        } catch (Exception ex) {
            response = "Invalid update arguments.";
            System.out.println("ClientResponseReader updateModemTime() Error: " + ex);
        }
        return response;
    }

    public void setConnectedIpList() {
        List<ClientResponderWS> connectedIpList = (List<ClientResponderWS>) this.ctx.getAttribute("connectedIp");
        if (this.clientSocket.isClosed()) {
            if (connectedIpList != null && connectedIpList.contains(this)) {
                connectedIpList.remove(this);
                this.ctx.setAttribute("connectedIp", connectedIpList);
            }
        }
    }

    public void setMap(Map<String, ClientResponderWS> map) {
        this.map = map;
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public Socket getClientSocket() {
        return this.clientSocket;
    }

    public void setTcpServer(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void setJunction(Junction junction) {
        this.junction = junction;
    }

    public void setAsync(Async async) {
        this.async = async;
    }

    public void setModemResReadSaveCont(ModemResReadSaveController modemResReadSaveCont) {
        this.modemResReadSaveCont = modemResReadSaveCont;
    }

    public void setContext(ServletContext ctx) {
        this.ctx = ctx;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public void setIPAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void setJunctionList(Map<Integer, Junction> junctionList) {
        this.junctionList = junctionList;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    public void setDb_userPasswrod(String db_userPasswrod) {
        this.db_userPassword = db_userPasswrod;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public void setDb_userName(String db_userName) {
        this.db_userName = db_userName;
    }

    public void setPlanInfoRefreshList(PlanInfo planInfoRefreshList) {
        this.planInfoRefreshList = planInfoRefreshList;
    }

    public static PlanInfo getPlanInfoRefreshList() {
        return planInfoRefreshList;
    }

    public int getClearanceSide() {
        return clearanceSide;
    }

    public void setClearanceSide(int clearanceSide) {
        this.clearanceSide = clearanceSide;
    }

    public boolean isRequestToUpdateProgramVersionNo() {
        return requestToUpdateProgramVersionNo;
    }

    public void setRequestToUpdateProgramVersionNo(boolean requestToUpdateProgramVersionNo) {
        this.requestToUpdateProgramVersionNo = requestToUpdateProgramVersionNo;
    }

    public boolean isRequestForClearance() {
        return requestForClearance;
    }

    public void setRequestForClearance(boolean requestForClearance) {
        this.requestForClearance = requestForClearance;
    }

    public int getActivityNo() {
        return activityNo;
    }

    public void setActivityNo(int activityNo) {
        this.activityNo = activityNo;
    }

    public int getActivitySide() {
        return activitySide;
    }

    public void setActivitySide(int activitySide) {
        this.activitySide = activitySide;
    }

    public boolean isRequestForActivity() {
        return requestForActivity;
    }

    public void setRequestForActivity(boolean requestForActivity) {
        this.requestForActivity = requestForActivity;
    }

    public boolean isIsJunctionLive() {
        return isJunctionLive;
    }

    public void setIsJunctionLive(boolean isJunctionLive) {
        this.isJunctionLive = isJunctionLive;
    }

    public String getIpLoginTimstamp() {
        return ipLoginTimstamp;
    }

    public void setIpLoginTimstamp(String ipLoginTimstamp) {
        this.ipLoginTimstamp = ipLoginTimstamp;
    }

    public Boolean getIpStatus() {
        return ipStatus;
    }

    public void setIpStatus(Boolean ipStatus) {
        this.ipStatus = ipStatus;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getIpPort() {
        return ipPort;
    }

    public void setIpPort(String ipPort) {
        this.ipPort = ipPort;
    }

    public int getNoOfRequestReceived() {
        return noOfRequestReceived;
    }

    public void setNoOfRequestReceived(int noOfRequestReceived) {
        this.noOfRequestReceived = noOfRequestReceived;
    }
}
