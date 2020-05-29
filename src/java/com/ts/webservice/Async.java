package com.ts.webservice;

import com.ts.junction.tableClasses.BaldeobagInfo;
import com.ts.junction.tableClasses.BandariyaTirahaInfo;
import com.ts.junction.tableClasses.BloomChowkInfo;
import com.ts.junction.tableClasses.DamohNakaInfo;
import com.ts.junction.tableClasses.DeendayalChowkInfo;
import com.ts.junction.tableClasses.GohalPurInfo;
import com.ts.junction.tableClasses.HighCourtInfo;
import com.ts.tcpServer.*;
import com.ts.junction.tableClasses.History;
import com.ts.junction.tableClasses.Junction;
import com.ts.junction.tableClasses.KatangaInfo;
import com.ts.junction.tableClasses.MadanMahalInfo;
import com.ts.junction.tableClasses.PlanInfo;
import com.ts.junction.tableClasses.RanitalInfo;
import com.ts.junction.tableClasses.TeenPatti_Info;
import com.ts.junction.tableClasses.YatayatThanaInfo;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Async extends HttpServlet
        implements Runnable {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private Map<String, Boolean> statusMap;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private String connectionString;
    private String driverClass;
    private String db_userName;
    private String db_userPassword;
    private String currentTime;
    private Junction junction;
    private String clientStatus = "N";
    private Calendar cal;
    private ServletContext ctx;
    private DateFormat dateFormat;
    private String lastVisitedTime;
    private PlanInfo planInfoRefreshList;
    private RanitalInfo ranitalInfoRefreshList;
    private TeenPatti_Info teenPattiInfoRefreshList;
    private GohalPurInfo gohalPurInfoRefreshList;
    private DamohNakaInfo damohNakaInfoRefreshList;
    private YatayatThanaInfo yatayatThanaInfoRefreshList;
    private KatangaInfo katangaInfoRefreshList;
    private BaldeobagInfo baldeobagInfoRefreshList;
    private DeendayalChowkInfo deendayalChowkInfoRefreshList;
    private HighCourtInfo highCourtInfoRefreshList;
    private BloomChowkInfo bloomChowkInfoRefreshList;
    private MadanMahalInfo madanMahalInfoRefreshList;
    private BandariyaTirahaInfo bandariyaTirahaInfoRefreshList;
    private boolean responseFromModemForRefresh;
    private String ipAddress;
    private String ipPort;
    ClientResponderModel clientResponderModel = new ClientResponderModel();

    public void run() {
        this.clientResponderModel.setDriverClass(this.driverClass);
        this.clientResponderModel.setConnectionString(this.connectionString);
        this.clientResponderModel.setDb_userName(this.db_userName);
        this.clientResponderModel.setDb_userPasswrod(this.db_userPassword);
        this.clientResponderModel.setConnection();
        try {
            int j = 0;
            //while (true) {
                //synchronized (this) {
                    this.dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    this.cal = Calendar.getInstance();

                    ClientResponder clientResponder = this.junction.getClientResponder();

                    String res = "";
                    String currentTime = this.dateFormat.format(this.cal.getTime());
                    this.lastVisitedTime = currentTime;
                    int calculateCurrentTimeInSeconds = calculateTimeInSeconds(currentTime);
                    int calculateLastVisitedTimeInSeconds = calculateTimeInSeconds(this.junction.getLastVisitedTime());

                    String lastVisitedTime1 = this.junction.getLastVisitedTime();
                    int junctionID = this.junction.getJunction_id();
                    int program_version_no = this.junction.getProgram_version_no();
                    //int fileNoFromDb = this.clientResponderModel.getFileNo(junctionID, program_version_no);
                    String junctionName = this.junction.getJunction_name();

                    //if (!this.clientSocket.isClosed()) ;
                    History historyList = new History();
                    //String ipAddress = this.clientSocket.getInetAddress().toString();
                    //int port = this.clientSocket.getPort();
                    //String str = ipAddress.toString();
                    //String ipaddr = str.substring(1, str.length());
                    String ipaddress = ipAddress + ":" + ipPort;
                    boolean closedStatus = false;//this.clientSocket.isClosed();
                    this.statusMap = new HashMap();
                    this.statusMap.put(ipAddress, Boolean.valueOf(closedStatus));
                    historyList.setJunction_id(junctionID);
                    historyList.setProgram_version_no(program_version_no);
                    historyList.setIp_address(ipAddress);
                    historyList.setPort(Integer.parseInt(ipPort));
                    historyList.setStatus(closedStatus);
                    try {
                        if (j == 0) {
                            System.out.println("Async :serverSocket.isClosed()------" + closedStatus);
                            this.clientResponderModel.insertRecord(historyList);
                            j = 1;
                        }
                        else if(true && (j == 1)) {//(this.clientSocket.isClosed())
                            this.clientResponderModel.updateRecord(historyList);
                            clientResponder.setRequestForActivity(false);
                            clientResponder.setRequestForClearance(false);
                            clientResponder.setIsJunctionLive(false);
                            clientResponder.setNoOfRequestReceived(0);
                            j = 3;
                        }
                        else if((calculateCurrentTimeInSeconds - calculateLastVisitedTimeInSeconds > 200) && (this.clientStatus.equals("N"))) {
                            System.out.println("CLOSE 200 ----- currentTime--- " + currentTime + ", " + "lastVisitedTime--- " + this.junction.getLastVisitedTime());
                            //this.clientSocket.close();
                          //  clientResponder.setConnectedIpList();
                            setClientStatus("Y");
                        }
//                        else if(calculateCurrentTimeInSeconds - calculateLastVisitedTimeInSeconds == 200) {
//                            PlanInfo planInfoList = getPlanInfoRefreshList();
//                            planInfoList.setResponseFromModemForRefresh(false);
//                            clientResponder.setPlanInfoRefreshList(planInfoList);
//                            res = "126 126 126 126 " + junctionID + " " +program_version_no+ " " +fileNoFromDb+ " " +"8" + " " + "125" + " " + "125";
//                            //System.out.println("Refresh request in Async ************ -- " + res);
//                            System.out.println("currentTime -- " + currentTime);
//                            System.out.println("Current Delay- " + (calculateCurrentTimeInSeconds - calculateLastVisitedTimeInSeconds));
//                            clientResponder.sendResponse(res);
//                            Thread.sleep(10000);
//                        }
//                        else if(calculateCurrentTimeInSeconds - calculateLastVisitedTimeInSeconds > 200) {
//                            if ((calculateCurrentTimeInSeconds - calculateLastVisitedTimeInSeconds < 400) && (this.clientStatus.equals("N"))) {
//                                Thread.sleep(10000);
//                                PlanInfo planInfoList = getPlanInfoRefreshList();
//                                planInfoList.setResponseFromModemForRefresh(false);
//                                clientResponder.setPlanInfoRefreshList(planInfoList);
//                                res = "126 126 126 126 " + junctionID + " " +program_version_no+ " " +fileNoFromDb+ " " +"8" + " " +"125" + " " + "125";
//                                clientResponder.sendResponse(res);
//                                System.out.println("currentTime -- " + currentTime + "lastTime -- " + this.junction.getLastVisitedTime());
//                                System.out.println("Current Delay- " + (calculateCurrentTimeInSeconds - calculateLastVisitedTimeInSeconds));
//                                //System.out.println("Refresh request in Async ************ -- " + res);
//                            }
                        else {

                        }
                    } catch (Exception e) {
                    }
                 
                //}
            //}
        } catch (Exception interruptedEx) {
            System.out.println("ASYNC run() Error: " + interruptedEx);
        }
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

    public void setTcpServer(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void setJunction(Junction junction) {
        this.junction = junction;
    }

    public String getClientStatus() {
        return this.clientStatus;
    }

    public void setClientStatus(String clientStatus) {
        this.clientStatus = clientStatus;
    }

    public PlanInfo getPlanInfoRefreshList() {
        return this.planInfoRefreshList;
    }

    public void setPlanInfoRefreshList(PlanInfo planInfoRefreshList) {
        this.planInfoRefreshList = planInfoRefreshList;
    }

    public RanitalInfo getRanitalInfoRefreshList() {
        return this.ranitalInfoRefreshList;
    }

    public void setRanitalInfoRefreshList(RanitalInfo ranitalInfoRefreshList) {
        this.ranitalInfoRefreshList = ranitalInfoRefreshList;
    }
    
    public GohalPurInfo getGohalPurInfoRefreshList() {
        return this.gohalPurInfoRefreshList;
    }

    public void setGohalPurInfoRefreshList(GohalPurInfo gohalPurInfoRefreshList) {
        this.gohalPurInfoRefreshList = gohalPurInfoRefreshList;
    }

    public DamohNakaInfo getDamohNakaInfoRefreshList() {
        return this.damohNakaInfoRefreshList;
    }

    public void setDamohNakaInfoRefreshList(DamohNakaInfo damohNakaInfoRefreshList) {
        this.damohNakaInfoRefreshList = damohNakaInfoRefreshList;
    }

    public TeenPatti_Info getTeenPattiInfoRefreshList() {
        return this.teenPattiInfoRefreshList;
    }

    public void setTeenPattiInfoRefreshList(TeenPatti_Info teenPattiInfoRefreshList) {
        this.teenPattiInfoRefreshList = teenPattiInfoRefreshList;
    }

    public KatangaInfo getKatangaInfoRefreshList() {
        return this.katangaInfoRefreshList;
    }

    public void setKatangaInfoRefreshList(KatangaInfo katangaInfoRefreshList) {
        this.katangaInfoRefreshList = katangaInfoRefreshList;
    }

    public YatayatThanaInfo getYatayatThanaInfoRefreshList() {
        return this.yatayatThanaInfoRefreshList;
    }

    public void setYatayatThanaInfoRefreshList(YatayatThanaInfo yatayatThanaInfoRefreshList) {
        this.yatayatThanaInfoRefreshList = yatayatThanaInfoRefreshList;
    }

    public BaldeobagInfo getBaldeobagInfoRefreshList() {
        return this.baldeobagInfoRefreshList;
    }

    public void setBaldeobagInfoRefreshList(BaldeobagInfo baldeobagInfoRefreshList) {
        this.baldeobagInfoRefreshList = baldeobagInfoRefreshList;
    }

    public DeendayalChowkInfo getDeendayalChowkInfoRefreshList() {
        return this.deendayalChowkInfoRefreshList;
    }

    public void setDeendayalChowkInfoRefreshList(DeendayalChowkInfo deendayalChowkInfoRefreshList) {
        this.deendayalChowkInfoRefreshList = deendayalChowkInfoRefreshList;
    }

    public HighCourtInfo getHighCourtInfoRefreshList() {
        return this.highCourtInfoRefreshList;
    }

    public void setHighCourtInfoRefreshList(HighCourtInfo highCourtInfoRefreshList) {
        this.highCourtInfoRefreshList = highCourtInfoRefreshList;
    }

    public BloomChowkInfo getBloomChowkInfoRefreshList() {
        return this.bloomChowkInfoRefreshList;
    }

    public void setBloomChowkInfoRefreshList(BloomChowkInfo bloomChowkInfoRefreshList) {
        this.bloomChowkInfoRefreshList = bloomChowkInfoRefreshList;
    }

    public MadanMahalInfo getMadanMahalInfoRefreshList() {
        return this.madanMahalInfoRefreshList;
    }

    public void setMadanMahalInfoRefreshList(MadanMahalInfo madanMahalInfoRefreshList) {
        this.madanMahalInfoRefreshList = madanMahalInfoRefreshList;
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

    public void setClientResponderModel(ClientResponderModel clientResponderModel) {
        this.clientResponderModel = clientResponderModel;
    }

    public ClientResponderModel getClientResponderModel() {
        return clientResponderModel;
    }

    public BandariyaTirahaInfo getBandariyaTirahaInfoRefreshList() {
        return bandariyaTirahaInfoRefreshList;
    }

    public void setBandariyaTirahaInfoRefreshList(BandariyaTirahaInfo bandariyaTirahaInfoRefreshList) {
        this.bandariyaTirahaInfoRefreshList = bandariyaTirahaInfoRefreshList;
    }
    
    

}
