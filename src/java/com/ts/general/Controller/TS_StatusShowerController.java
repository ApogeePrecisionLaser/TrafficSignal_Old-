package com.ts.general.Controller;

import com.ts.general.Model.Signal;
import com.ts.junction.tableClasses.BaldeobagInfo;
import com.ts.junction.tableClasses.BandariyaTirahaInfo;
import com.ts.junction.tableClasses.BloomChowkInfo;
import com.ts.junction.tableClasses.CollectorateInfo;
import com.ts.junction.tableClasses.DamohNakaInfo;
import com.ts.junction.tableClasses.DeendayalChowkInfo;
import com.ts.junction.tableClasses.GohalPurInfo;
import com.ts.junction.tableClasses.HighCourtInfo;
import com.ts.junction.tableClasses.History;
import com.ts.junction.tableClasses.KatangaInfo;
import com.ts.junction.tableClasses.LabourChowkInfo;
import com.ts.junction.tableClasses.MadanMahalInfo;
import com.ts.junction.tableClasses.PlanInfo;
import com.ts.junction.tableClasses.RanitalInfo;
import com.ts.junction.tableClasses.TaiyabaliChowkInfo;
import com.ts.junction.tableClasses.TeenPatti_Info;
import com.ts.junction.tableClasses.YatayatThanaInfo;
import com.ts.tcpServer.ClientResponder;
import com.ts.tcpServer.ClientResponderModel;
import com.ts.webservice.ClientResponderWS;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;

public class TS_StatusShowerController extends HttpServlet {

    private PlanInfo planInfoList;
    private TeenPatti_Info teenPattiInfoList;
    private RanitalInfo ranitalInfoList;
    private DamohNakaInfo damohNakaInfoList;
    private YatayatThanaInfo yatayatThanaInfoList;
    private KatangaInfo katangaInfoList;
    private BaldeobagInfo baldeobagInfoList;
    private DeendayalChowkInfo deendayalChowkInfoList;
    private HighCourtInfo highCourtInfoList;
    private BloomChowkInfo bloomChowkInfoList;
    private MadanMahalInfo madanMahalInfoList;
    private GohalPurInfo gohalPurInfoList;
    private ClientResponder clientResponder;
    private BandariyaTirahaInfo bandariyaTirahaInfoList;
    private LabourChowkInfo labourChowkInfoList;
    private TaiyabaliChowkInfo taiyabaliChowkInfoList;
    private CollectorateInfo collectorateInfoList;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ServletContext ctx = getServletContext();
            // for testing

            HttpSession session = request.getSession(false);
        if(session != null){
            try{
            String userName = session.getAttribute("userName").toString();
            if(userName.equals("")){
                request.getRequestDispatcher("/beforeLoginView").forward(request, response);
                return;
            }
            }catch(Exception e){
                System.out.println(e);
                request.getRequestDispatcher("/beforeLoginView").forward(request, response);
                return;
            }
        }

            ClientResponderModel clientResponderModel = new ClientResponderModel();
            ServletContext ctx1 = getServletContext();
             clientResponderModel.setDriverClass(ctx1.getInitParameter("driverClass"));
             clientResponderModel.setConnectionString(ctx1.getInitParameter("connectionString"));
             clientResponderModel.setDb_userName(ctx1.getInitParameter("db_userName"));
            clientResponderModel.setDb_userPasswrod(ctx1.getInitParameter("db_userPassword"));
            clientResponderModel.setConnection();
            // end of testing
            String task = request.getParameter("task");
            int a = 0;
            //int current_junction_id1 = Integer.parseInt(request.getParameter("selected_junction_id"));
            String abc = (String) request.getAttribute("selected_junction_id");
            String map_junction_id = request.getParameter("selected_junction_id");
            if (task == null) {
                task = "";
            }
            if (abc == null) {
                abc = "";
            }else{
             a = Integer.parseInt(abc);
            }
            if (map_junction_id == null) {
                map_junction_id = "";
            }else{
             a = Integer.parseInt(map_junction_id);
            }
            if (task.equals("jQueryRequest")) {
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                String time = null;
                Signal signal = (Signal) ctx.getAttribute("signal");
                if (signal != null) {
                    synchronized (signal) {
                        time = signal.getCurrentTime();
                    }
                } else {
                    System.out.println("signal object was not found");
                }

                out.print(time);
                out.flush();
                return;
            }
            int i = 1;
            if (task.equals("getLatestStatus")) {
                PrintWriter out = response.getWriter();
                JSONObject jsonObj = new JSONObject();
                 int current_junction_id = Integer.parseInt(request.getParameter("current_junction_id"));
                //ClientResponderWS client = new ClientResponderWS();
//                if(i == 1){
//                    ClientResponderWS.main("abc");
//                    i++;
//                }
                this.planInfoList = ((PlanInfo) ctx.getAttribute("planInfolist"));//ClientResponderWS.getPlanInfoRefreshList();
                this.teenPattiInfoList = ((TeenPatti_Info) ctx.getAttribute("teenPattiInfoList"));//ClientResponderWS.getPlanInfoRefreshList();
                this.ranitalInfoList = ((RanitalInfo) ctx.getAttribute("ranitalInfoList"));//ClientResponderWS.getPlanInfoRefreshList();
                this.damohNakaInfoList = ((DamohNakaInfo) ctx.getAttribute("damohNakaInfoList"));//ClientResponderWS.getPlanInfoRefreshList();
                this.yatayatThanaInfoList = ((YatayatThanaInfo) ctx.getAttribute("yatayatThanaInfoList"));//ClientResponderWS.getPlanInfoRefreshList();
                this.katangaInfoList = ((KatangaInfo) ctx.getAttribute("katangaInfoList"));//ClientResponderWS.getPlanInfoRefreshList();
                this.baldeobagInfoList = ((BaldeobagInfo) ctx.getAttribute("baldeobagInfoList"));//ClientResponderWS.getPlanInfoRefreshList();
                this.deendayalChowkInfoList = ((DeendayalChowkInfo) ctx.getAttribute("deendayalChowkInfoList"));//ClientResponderWS.getPlanInfoRefreshList();
                this.highCourtInfoList = ((HighCourtInfo) ctx.getAttribute("highCourtInfoList"));//ClientResponderWS.getPlanInfoRefreshList();
                this.bloomChowkInfoList = ((BloomChowkInfo) ctx.getAttribute("bloomChowkInfoList"));//ClientResponderWS.getPlanInfoRefreshList();
                this.madanMahalInfoList = ((MadanMahalInfo) ctx.getAttribute("madanMahalInfoList"));//ClientResponderWS.getPlanInfoRefreshList();
                this.gohalPurInfoList = ((GohalPurInfo) ctx.getAttribute("gohalPurInfoList"));//ClientResponderWS.getPlanInfoRefreshList();
                this.bandariyaTirahaInfoList = ((BandariyaTirahaInfo) ctx.getAttribute("bandariyaTirahaInfoList"));//ClientResponderWS.getPlanInfoRefreshList();
                this.labourChowkInfoList = ((LabourChowkInfo)ctx.getAttribute("labourChowkInfoList"));
                this.taiyabaliChowkInfoList = ((TaiyabaliChowkInfo)ctx.getAttribute("taiyabaliChowkInfoList"));
                this.collectorateInfoList = ((CollectorateInfo)ctx.getAttribute("collectorateInfoList"));
//int junction_id1 = this.ranitalInfoList.getJunction_id();
//                if(current_junction_id == 13){
//                this.planInfoList = null;
//                BeanUtils.copyProperties(planInfoList, teenPattiInfoList);
//                }
//                if(current_junction_id == 11){
//                this.planInfoList = null;
//                BeanUtils.copyProperties(planInfoList, ranitalInfoList);
//                }

               //     for teen patti

                if(current_junction_id == 13){
                if (this.teenPattiInfoList != null) {
                    boolean responseFromModemForRefresh = this.planInfoList.isResponseFromModemForRefresh();
                    //boolean responseFromModemForClearnace = this.planInfoList.isResponseFromModemForClearance();

                   // if ((responseFromModemForRefresh == true) || (responseFromModemForClearnace == true)) {
                    if ((responseFromModemForRefresh == true)) {
                        //if(junction_id1 == 2){
                    int functionNo = this.teenPattiInfoList.getFunction_no();
                    int junction_id = this.teenPattiInfoList.getJunction_id();
                    int program_version_no = this.teenPattiInfoList.getProgram_version_no();
                    int fileNo = this.teenPattiInfoList.getFileNo();
                    int activity = this.teenPattiInfoList.getActivity();
                    int side_no = this.teenPattiInfoList.getSide_no();
                    int plan_no = this.teenPattiInfoList.getPlan_no();
                    String mode = this.teenPattiInfoList.getMode();
                    String junctionName = this.teenPattiInfoList.getJunction_name();
                    String sideName = this.teenPattiInfoList.getSideName();

                    String onTime = this.teenPattiInfoList.getOnTime();
                    String offTime = this.teenPattiInfoList.getOffTime();

                    String side1Name = this.teenPattiInfoList.getSide1Name();
                    String side2Name = this.teenPattiInfoList.getSide2Name();
                    String side3Name = this.teenPattiInfoList.getSide3Name();
                    String side4Name = this.teenPattiInfoList.getSide4Name();
                    String side5Name = this.teenPattiInfoList.getSide5Name();

                    int side1Time = this.teenPattiInfoList.getSide1_time();
                    int side2Time = this.teenPattiInfoList.getSide2_time();
                    int side3Time = this.teenPattiInfoList.getSide3_time();
                    int side4Time = this.teenPattiInfoList.getSide4_time();
                    int side1LeftStatus = this.teenPattiInfoList.getSide1_left_status();
                    int side1RightStatus = this.teenPattiInfoList.getSide1_right_status();
                    int side1UpStatus = this.teenPattiInfoList.getSide1_up_status();
                    int side1DownStatus = this.teenPattiInfoList.getSide1_down_status();
                    int side2LeftStatus = this.teenPattiInfoList.getSide2_left_status();
                    int side2RightStatus = this.teenPattiInfoList.getSide2_right_status();
                    int side2UpStatus = this.teenPattiInfoList.getSide2_up_status();
                    int side2DownStatus = this.teenPattiInfoList.getSide2_down_status();
                    int side3LeftStatus = this.teenPattiInfoList.getSide3_left_status();
                    int side3RightStatus = this.teenPattiInfoList.getSide3_right_status();
                    int side3UpStatus = this.teenPattiInfoList.getSide3_up_status();
                    int side3DownStatus = this.teenPattiInfoList.getSide3_down_status();
                    int side4LeftStatus = this.teenPattiInfoList.getSide4_left_status();
                    int side4RightStatus = this.teenPattiInfoList.getSide4_right_status();
                    int side4UpStatus = this.teenPattiInfoList.getSide4_up_status();
                    int side4DownStatus = this.teenPattiInfoList.getSide4_down_status();

                    int juncHr = this.teenPattiInfoList.getJuncHr();
                    int juncMin = this.teenPattiInfoList.getJuncMin();
                    int juncDat = this.teenPattiInfoList.getJuncDate();
                    int juncMonth = this.teenPattiInfoList.getJuncMonth();
                    int juncYear = this.teenPattiInfoList.getJuncYear();

                        String response_data = "junction_id=" + junction_id + "#$" + "program_version_no=" + program_version_no + "#$" + "fileNo=" + fileNo
                                + "#$" + "functionNo=" + functionNo + "#$" + "activity=" + activity
                                + "#$" + "side_no=" + side_no + "#$" + "plan_no=" + plan_no
                                + "#$" + "junctionName=" + junctionName + "#$" + "sideName=" + sideName + "#$" + "onTime=" + onTime + "#$" + "offTime=" + offTime
                                + "#$" + "mode=" + mode + "#$" + "side1Name=" + side1Name + "#$" + "side2Name=" + side2Name + "#$" + "side3Name=" + side3Name
                                + "#$" + "side4Name=" + side4Name + "#$" + "side5Name=" + side5Name + "#$" + "side1Time=" + side1Time + "#$" + "side2Time=" + side2Time
                                + "#$" + "side3Time=" + side3Time + "#$" + "side4Time=" + side4Time + "#$" + "side1LeftStatus=" + side1LeftStatus
                                + "#$" + "side1RightStatus=" + side1RightStatus + "#$" + "side1UpStatus=" + side1UpStatus + "#$" + "side1DownStatus=" + side1DownStatus
                                + "#$" + "side2LeftStatus=" + side2LeftStatus + "#$" + "side2RightStatus=" + side2RightStatus + "#$" + "side2UpStatus=" + side2UpStatus
                                + "#$" + "side2DownStatus=" + side2DownStatus + "#$" + "side3LeftStatus=" + side3LeftStatus + "#$" + "side3RightStatus=" + side3RightStatus
                                + "#$" + "side3UpStatus=" + side3UpStatus + "#$" + "side3DownStatus=" + side3DownStatus + "#$" + "side4LeftStatus=" + side4LeftStatus
                                + "#$" + "side4RightStatus=" + side4RightStatus + "#$" + "side4UpStatus=" + side4UpStatus + "#$" + "side4DownStatus=" + side4DownStatus
                                + "#$" + "juncHr=" + juncHr + "#$" + "juncMin=" + juncMin + "#$" + "juncDat=" + juncDat + "#$" + "juncMonth=" + juncMonth
                                + "#$" + "juncYear=" + juncYear;
                        jsonObj.put("junction_id", junction_id + "");
                        jsonObj.put("program_version_no", program_version_no + "");
                        jsonObj.put("fileNo", fileNo + "");
                        jsonObj.put("functionNo", functionNo + "");
                        jsonObj.put("activity", activity + "");
                        jsonObj.put("side_no", side_no + "");
                        jsonObj.put("plan_no", plan_no + "");
                        jsonObj.put("junctionName", junctionName);
                        jsonObj.put("sideName", sideName);
                        jsonObj.put("onTime", onTime);
                        jsonObj.put("offTime", offTime);
                        jsonObj.put("mode", mode);
                        jsonObj.put("side1Name", side1Name);
                        jsonObj.put("side2Name", side2Name);
                        jsonObj.put("side3Name", side3Name);
                        jsonObj.put("side4Name", side4Name);
                        jsonObj.put("side5Name", side5Name);
                        jsonObj.put("side1Time", side1Time + "");
                        jsonObj.put("side2Time", side2Time + "");
                        jsonObj.put("side3Time", side3Time + "");
                        jsonObj.put("side4Time", side4Time + "");
                        jsonObj.put("side1LeftStatus", side1LeftStatus + "");
                        jsonObj.put("side1RightStatus", side1RightStatus + "");
                        jsonObj.put("side1UpStatus", side1UpStatus + "");
                        jsonObj.put("side1DownStatus", side1DownStatus + "");
                        jsonObj.put("side2LeftStatus", side2LeftStatus + "");
                        jsonObj.put("side2RightStatus", side2RightStatus + "");
                        jsonObj.put("side2UpStatus", side2UpStatus + "");
                        jsonObj.put("side2DownStatus", side2DownStatus + "");
                        jsonObj.put("side3LeftStatus", side3LeftStatus + "");
                        jsonObj.put("side3RightStatus", side3RightStatus + "");
                        jsonObj.put("side3UpStatus", side3UpStatus + "");
                        jsonObj.put("side3DownStatus", side3DownStatus + "");
                        jsonObj.put("side4LeftStatus", side4LeftStatus + "");
                        jsonObj.put("side4RightStatus", side4RightStatus + "");
                        jsonObj.put("side4UpStatus", side4UpStatus + "");
                        jsonObj.put("side4DownStatus", side4DownStatus + "");
                        jsonObj.put("juncHr", juncHr + "");
                        jsonObj.put("juncMin", juncMin + "");
                        jsonObj.put("juncDat", juncDat + "");
                        jsonObj.put("juncMonth", juncMonth + "");
                        jsonObj.put("juncYear", juncYear + "");
                        out.println(jsonObj);
                        out.flush();
                    }
                } else {
                    out.println("");
                }
                }
                
                if(current_junction_id == 16){
                if (this.labourChowkInfoList != null) {
                    boolean responseFromModemForRefresh = this.planInfoList.isResponseFromModemForRefresh();
                    //boolean responseFromModemForClearnace = this.planInfoList.isResponseFromModemForClearance();

                   // if ((responseFromModemForRefresh == true) || (responseFromModemForClearnace == true)) {
                    if ((responseFromModemForRefresh == true)) {
                        //if(junction_id1 == 2){
                    int functionNo = this.labourChowkInfoList.getFunction_no();
                    int junction_id = this.labourChowkInfoList.getJunction_id();
                    int program_version_no = this.labourChowkInfoList.getProgram_version_no();
                    int fileNo = this.labourChowkInfoList.getFileNo();
                    int activity = this.labourChowkInfoList.getActivity();
                    int side_no = this.labourChowkInfoList.getSide_no();
                    int plan_no = this.labourChowkInfoList.getPlan_no();
                    String mode = this.labourChowkInfoList.getMode();
                    String junctionName = this.labourChowkInfoList.getJunction_name();
                    String sideName = this.labourChowkInfoList.getSideName();

                    String onTime = this.labourChowkInfoList.getOnTime();
                    String offTime = this.labourChowkInfoList.getOffTime();

                    String side1Name = this.labourChowkInfoList.getSide1Name();
                    String side2Name = this.labourChowkInfoList.getSide2Name();
                    String side3Name = this.labourChowkInfoList.getSide3Name();
                    String side4Name = this.labourChowkInfoList.getSide4Name();
                    String side5Name = this.labourChowkInfoList.getSide5Name();

                    int side1Time = this.labourChowkInfoList.getSide1_time();
                    int side2Time = this.labourChowkInfoList.getSide2_time();
                    int side3Time = this.labourChowkInfoList.getSide3_time();
                    int side4Time = this.labourChowkInfoList.getSide4_time();
                    int side1LeftStatus = this.labourChowkInfoList.getSide1_left_status();
                    int side1RightStatus = this.labourChowkInfoList.getSide1_right_status();
                    int side1UpStatus = this.labourChowkInfoList.getSide1_up_status();
                    int side1DownStatus = this.labourChowkInfoList.getSide1_down_status();
                    int side2LeftStatus = this.labourChowkInfoList.getSide2_left_status();
                    int side2RightStatus = this.labourChowkInfoList.getSide2_right_status();
                    int side2UpStatus = this.labourChowkInfoList.getSide2_up_status();
                    int side2DownStatus = this.labourChowkInfoList.getSide2_down_status();
                    int side3LeftStatus = this.labourChowkInfoList.getSide3_left_status();
                    int side3RightStatus = this.labourChowkInfoList.getSide3_right_status();
                    int side3UpStatus = this.labourChowkInfoList.getSide3_up_status();
                    int side3DownStatus = this.labourChowkInfoList.getSide3_down_status();
                    int side4LeftStatus = this.labourChowkInfoList.getSide4_left_status();
                    int side4RightStatus = this.labourChowkInfoList.getSide4_right_status();
                    int side4UpStatus = this.labourChowkInfoList.getSide4_up_status();
                    int side4DownStatus = this.labourChowkInfoList.getSide4_down_status();

                    int juncHr = this.labourChowkInfoList.getJuncHr();
                    int juncMin = this.labourChowkInfoList.getJuncMin();
                    int juncDat = this.labourChowkInfoList.getJuncDate();
                    int juncMonth = this.labourChowkInfoList.getJuncMonth();
                    int juncYear = this.labourChowkInfoList.getJuncYear();

                        String response_data = "junction_id=" + junction_id + "#$" + "program_version_no=" + program_version_no + "#$" + "fileNo=" + fileNo
                                + "#$" + "functionNo=" + functionNo + "#$" + "activity=" + activity
                                + "#$" + "side_no=" + side_no + "#$" + "plan_no=" + plan_no
                                + "#$" + "junctionName=" + junctionName + "#$" + "sideName=" + sideName + "#$" + "onTime=" + onTime + "#$" + "offTime=" + offTime
                                + "#$" + "mode=" + mode + "#$" + "side1Name=" + side1Name + "#$" + "side2Name=" + side2Name + "#$" + "side3Name=" + side3Name
                                + "#$" + "side4Name=" + side4Name + "#$" + "side5Name=" + side5Name + "#$" + "side1Time=" + side1Time + "#$" + "side2Time=" + side2Time
                                + "#$" + "side3Time=" + side3Time + "#$" + "side4Time=" + side4Time + "#$" + "side1LeftStatus=" + side1LeftStatus
                                + "#$" + "side1RightStatus=" + side1RightStatus + "#$" + "side1UpStatus=" + side1UpStatus + "#$" + "side1DownStatus=" + side1DownStatus
                                + "#$" + "side2LeftStatus=" + side2LeftStatus + "#$" + "side2RightStatus=" + side2RightStatus + "#$" + "side2UpStatus=" + side2UpStatus
                                + "#$" + "side2DownStatus=" + side2DownStatus + "#$" + "side3LeftStatus=" + side3LeftStatus + "#$" + "side3RightStatus=" + side3RightStatus
                                + "#$" + "side3UpStatus=" + side3UpStatus + "#$" + "side3DownStatus=" + side3DownStatus + "#$" + "side4LeftStatus=" + side4LeftStatus
                                + "#$" + "side4RightStatus=" + side4RightStatus + "#$" + "side4UpStatus=" + side4UpStatus + "#$" + "side4DownStatus=" + side4DownStatus
                                + "#$" + "juncHr=" + juncHr + "#$" + "juncMin=" + juncMin + "#$" + "juncDat=" + juncDat + "#$" + "juncMonth=" + juncMonth
                                + "#$" + "juncYear=" + juncYear;
                        jsonObj.put("junction_id", junction_id + "");
                        jsonObj.put("program_version_no", program_version_no + "");
                        jsonObj.put("fileNo", fileNo + "");
                        jsonObj.put("functionNo", functionNo + "");
                        jsonObj.put("activity", activity + "");
                        jsonObj.put("side_no", side_no + "");
                        jsonObj.put("plan_no", plan_no + "");
                        jsonObj.put("junctionName", junctionName);
                        jsonObj.put("sideName", sideName);
                        jsonObj.put("onTime", onTime);
                        jsonObj.put("offTime", offTime);
                        jsonObj.put("mode", mode);
                        jsonObj.put("side1Name", side1Name);
                        jsonObj.put("side2Name", side2Name);
                        jsonObj.put("side3Name", side3Name);
                        jsonObj.put("side4Name", side4Name);
                        jsonObj.put("side5Name", side5Name);
                        jsonObj.put("side1Time", side1Time + "");
                        jsonObj.put("side2Time", side2Time + "");
                        jsonObj.put("side3Time", side3Time + "");
                        jsonObj.put("side4Time", side4Time + "");
                        jsonObj.put("side1LeftStatus", side1LeftStatus + "");
                        jsonObj.put("side1RightStatus", side1RightStatus + "");
                        jsonObj.put("side1UpStatus", side1UpStatus + "");
                        jsonObj.put("side1DownStatus", side1DownStatus + "");
                        jsonObj.put("side2LeftStatus", side2LeftStatus + "");
                        jsonObj.put("side2RightStatus", side2RightStatus + "");
                        jsonObj.put("side2UpStatus", side2UpStatus + "");
                        jsonObj.put("side2DownStatus", side2DownStatus + "");
                        jsonObj.put("side3LeftStatus", side3LeftStatus + "");
                        jsonObj.put("side3RightStatus", side3RightStatus + "");
                        jsonObj.put("side3UpStatus", side3UpStatus + "");
                        jsonObj.put("side3DownStatus", side3DownStatus + "");
                        jsonObj.put("side4LeftStatus", side4LeftStatus + "");
                        jsonObj.put("side4RightStatus", side4RightStatus + "");
                        jsonObj.put("side4UpStatus", side4UpStatus + "");
                        jsonObj.put("side4DownStatus", side4DownStatus + "");
                        jsonObj.put("juncHr", juncHr + "");
                        jsonObj.put("juncMin", juncMin + "");
                        jsonObj.put("juncDat", juncDat + "");
                        jsonObj.put("juncMonth", juncMonth + "");
                        jsonObj.put("juncYear", juncYear + "");
                        out.println(jsonObj);
                        out.flush();
                    }
                } else {
                    out.println("");
                }
                }
                
                if(current_junction_id == 17){
                if (this.taiyabaliChowkInfoList != null) {
                    boolean responseFromModemForRefresh = this.planInfoList.isResponseFromModemForRefresh();
                    //boolean responseFromModemForClearnace = this.planInfoList.isResponseFromModemForClearance();

                   // if ((responseFromModemForRefresh == true) || (responseFromModemForClearnace == true)) {
                    if ((responseFromModemForRefresh == true)) {
                        //if(junction_id1 == 2){
                    int functionNo = this.taiyabaliChowkInfoList.getFunction_no();
                    int junction_id = this.taiyabaliChowkInfoList.getJunction_id();
                    int program_version_no = this.taiyabaliChowkInfoList.getProgram_version_no();
                    int fileNo = this.taiyabaliChowkInfoList.getFileNo();
                    int activity = this.taiyabaliChowkInfoList.getActivity();
                    int side_no = this.taiyabaliChowkInfoList.getSide_no();
                    int plan_no = this.taiyabaliChowkInfoList.getPlan_no();
                    String mode = this.taiyabaliChowkInfoList.getMode();
                    String junctionName = this.taiyabaliChowkInfoList.getJunction_name();
                    String sideName = this.taiyabaliChowkInfoList.getSideName();

                    String onTime = this.taiyabaliChowkInfoList.getOnTime();
                    String offTime = this.taiyabaliChowkInfoList.getOffTime();

                    String side1Name = this.taiyabaliChowkInfoList.getSide1Name();
                    String side2Name = this.taiyabaliChowkInfoList.getSide2Name();
                    String side3Name = this.taiyabaliChowkInfoList.getSide3Name();
                    String side4Name = this.taiyabaliChowkInfoList.getSide4Name();
                    String side5Name = this.taiyabaliChowkInfoList.getSide5Name();

                    int side1Time = this.taiyabaliChowkInfoList.getSide1_time();
                    int side2Time = this.taiyabaliChowkInfoList.getSide2_time();
                    int side3Time = this.taiyabaliChowkInfoList.getSide3_time();
                    int side4Time = this.taiyabaliChowkInfoList.getSide4_time();
                    int side1LeftStatus = this.taiyabaliChowkInfoList.getSide1_left_status();
                    int side1RightStatus = this.taiyabaliChowkInfoList.getSide1_right_status();
                    int side1UpStatus = this.taiyabaliChowkInfoList.getSide1_up_status();
                    int side1DownStatus = this.taiyabaliChowkInfoList.getSide1_down_status();
                    int side2LeftStatus = this.taiyabaliChowkInfoList.getSide2_left_status();
                    int side2RightStatus = this.taiyabaliChowkInfoList.getSide2_right_status();
                    int side2UpStatus = this.taiyabaliChowkInfoList.getSide2_up_status();
                    int side2DownStatus = this.taiyabaliChowkInfoList.getSide2_down_status();
                    int side3LeftStatus = this.taiyabaliChowkInfoList.getSide3_left_status();
                    int side3RightStatus = this.taiyabaliChowkInfoList.getSide3_right_status();
                    int side3UpStatus = this.taiyabaliChowkInfoList.getSide3_up_status();
                    int side3DownStatus = this.taiyabaliChowkInfoList.getSide3_down_status();
                    int side4LeftStatus = this.taiyabaliChowkInfoList.getSide4_left_status();
                    int side4RightStatus = this.taiyabaliChowkInfoList.getSide4_right_status();
                    int side4UpStatus = this.taiyabaliChowkInfoList.getSide4_up_status();
                    int side4DownStatus = this.taiyabaliChowkInfoList.getSide4_down_status();

                    int juncHr = this.taiyabaliChowkInfoList.getJuncHr();
                    int juncMin = this.taiyabaliChowkInfoList.getJuncMin();
                    int juncDat = this.taiyabaliChowkInfoList.getJuncDate();
                    int juncMonth = this.taiyabaliChowkInfoList.getJuncMonth();
                    int juncYear = this.taiyabaliChowkInfoList.getJuncYear();

                        String response_data = "junction_id=" + junction_id + "#$" + "program_version_no=" + program_version_no + "#$" + "fileNo=" + fileNo
                                + "#$" + "functionNo=" + functionNo + "#$" + "activity=" + activity
                                + "#$" + "side_no=" + side_no + "#$" + "plan_no=" + plan_no
                                + "#$" + "junctionName=" + junctionName + "#$" + "sideName=" + sideName + "#$" + "onTime=" + onTime + "#$" + "offTime=" + offTime
                                + "#$" + "mode=" + mode + "#$" + "side1Name=" + side1Name + "#$" + "side2Name=" + side2Name + "#$" + "side3Name=" + side3Name
                                + "#$" + "side4Name=" + side4Name + "#$" + "side5Name=" + side5Name + "#$" + "side1Time=" + side1Time + "#$" + "side2Time=" + side2Time
                                + "#$" + "side3Time=" + side3Time + "#$" + "side4Time=" + side4Time + "#$" + "side1LeftStatus=" + side1LeftStatus
                                + "#$" + "side1RightStatus=" + side1RightStatus + "#$" + "side1UpStatus=" + side1UpStatus + "#$" + "side1DownStatus=" + side1DownStatus
                                + "#$" + "side2LeftStatus=" + side2LeftStatus + "#$" + "side2RightStatus=" + side2RightStatus + "#$" + "side2UpStatus=" + side2UpStatus
                                + "#$" + "side2DownStatus=" + side2DownStatus + "#$" + "side3LeftStatus=" + side3LeftStatus + "#$" + "side3RightStatus=" + side3RightStatus
                                + "#$" + "side3UpStatus=" + side3UpStatus + "#$" + "side3DownStatus=" + side3DownStatus + "#$" + "side4LeftStatus=" + side4LeftStatus
                                + "#$" + "side4RightStatus=" + side4RightStatus + "#$" + "side4UpStatus=" + side4UpStatus + "#$" + "side4DownStatus=" + side4DownStatus
                                + "#$" + "juncHr=" + juncHr + "#$" + "juncMin=" + juncMin + "#$" + "juncDat=" + juncDat + "#$" + "juncMonth=" + juncMonth
                                + "#$" + "juncYear=" + juncYear;
                        jsonObj.put("junction_id", junction_id + "");
                        jsonObj.put("program_version_no", program_version_no + "");
                        jsonObj.put("fileNo", fileNo + "");
                        jsonObj.put("functionNo", functionNo + "");
                        jsonObj.put("activity", activity + "");
                        jsonObj.put("side_no", side_no + "");
                        jsonObj.put("plan_no", plan_no + "");
                        jsonObj.put("junctionName", junctionName);
                        jsonObj.put("sideName", sideName);
                        jsonObj.put("onTime", onTime);
                        jsonObj.put("offTime", offTime);
                        jsonObj.put("mode", mode);
                        jsonObj.put("side1Name", side1Name);
                        jsonObj.put("side2Name", side2Name);
                        jsonObj.put("side3Name", side3Name);
                        jsonObj.put("side4Name", side4Name);
                        jsonObj.put("side5Name", side5Name);
                        jsonObj.put("side1Time", side1Time + "");
                        jsonObj.put("side2Time", side2Time + "");
                        jsonObj.put("side3Time", side3Time + "");
                        jsonObj.put("side4Time", side4Time + "");
                        jsonObj.put("side1LeftStatus", side1LeftStatus + "");
                        jsonObj.put("side1RightStatus", side1RightStatus + "");
                        jsonObj.put("side1UpStatus", side1UpStatus + "");
                        jsonObj.put("side1DownStatus", side1DownStatus + "");
                        jsonObj.put("side2LeftStatus", side2LeftStatus + "");
                        jsonObj.put("side2RightStatus", side2RightStatus + "");
                        jsonObj.put("side2UpStatus", side2UpStatus + "");
                        jsonObj.put("side2DownStatus", side2DownStatus + "");
                        jsonObj.put("side3LeftStatus", side3LeftStatus + "");
                        jsonObj.put("side3RightStatus", side3RightStatus + "");
                        jsonObj.put("side3UpStatus", side3UpStatus + "");
                        jsonObj.put("side3DownStatus", side3DownStatus + "");
                        jsonObj.put("side4LeftStatus", side4LeftStatus + "");
                        jsonObj.put("side4RightStatus", side4RightStatus + "");
                        jsonObj.put("side4UpStatus", side4UpStatus + "");
                        jsonObj.put("side4DownStatus", side4DownStatus + "");
                        jsonObj.put("juncHr", juncHr + "");
                        jsonObj.put("juncMin", juncMin + "");
                        jsonObj.put("juncDat", juncDat + "");
                        jsonObj.put("juncMonth", juncMonth + "");
                        jsonObj.put("juncYear", juncYear + "");
                        out.println(jsonObj);
                        out.flush();
                    }
                } else {
                    out.println("");
                }
                }

                // end for tayiabali
                
                // start of collectorate
                if(current_junction_id == 18){
                if (this.collectorateInfoList != null) {
                    boolean responseFromModemForRefresh = this.planInfoList.isResponseFromModemForRefresh();
                    //boolean responseFromModemForClearnace = this.planInfoList.isResponseFromModemForClearance();

                   // if ((responseFromModemForRefresh == true) || (responseFromModemForClearnace == true)) {
                    if ((responseFromModemForRefresh == true)) {
                        //if(junction_id1 == 2){
                    int functionNo = this.collectorateInfoList.getFunction_no();
                    int junction_id = this.collectorateInfoList.getJunction_id();
                    int program_version_no = this.collectorateInfoList.getProgram_version_no();
                    int fileNo = this.collectorateInfoList.getFileNo();
                    int activity = this.collectorateInfoList.getActivity();
                    int side_no = this.collectorateInfoList.getSide_no();
                    int plan_no = this.collectorateInfoList.getPlan_no();
                    String mode = this.collectorateInfoList.getMode();
                    String junctionName = this.collectorateInfoList.getJunction_name();
                    String sideName = this.collectorateInfoList.getSideName();

                    String onTime = this.collectorateInfoList.getOnTime();
                    String offTime = this.collectorateInfoList.getOffTime();

                    String side1Name = this.collectorateInfoList.getSide1Name();
                    String side2Name = this.collectorateInfoList.getSide2Name();
                    String side3Name = this.collectorateInfoList.getSide3Name();
                    String side4Name = this.collectorateInfoList.getSide4Name();
                    String side5Name = this.collectorateInfoList.getSide5Name();

                    int side1Time = this.collectorateInfoList.getSide1_time();
                    int side2Time = this.collectorateInfoList.getSide2_time();
                    int side3Time = this.collectorateInfoList.getSide3_time();
                    int side4Time = this.collectorateInfoList.getSide4_time();
                    int side1LeftStatus = this.collectorateInfoList.getSide1_left_status();
                    int side1RightStatus = this.collectorateInfoList.getSide1_right_status();
                    int side1UpStatus = this.collectorateInfoList.getSide1_up_status();
                    int side1DownStatus = this.collectorateInfoList.getSide1_down_status();
                    int side2LeftStatus = this.collectorateInfoList.getSide2_left_status();
                    int side2RightStatus = this.collectorateInfoList.getSide2_right_status();
                    int side2UpStatus = this.collectorateInfoList.getSide2_up_status();
                    int side2DownStatus = this.collectorateInfoList.getSide2_down_status();
                    int side3LeftStatus = this.collectorateInfoList.getSide3_left_status();
                    int side3RightStatus = this.collectorateInfoList.getSide3_right_status();
                    int side3UpStatus = this.collectorateInfoList.getSide3_up_status();
                    int side3DownStatus = this.collectorateInfoList.getSide3_down_status();
                    int side4LeftStatus = this.collectorateInfoList.getSide4_left_status();
                    int side4RightStatus = this.collectorateInfoList.getSide4_right_status();
                    int side4UpStatus = this.collectorateInfoList.getSide4_up_status();
                    int side4DownStatus = this.collectorateInfoList.getSide4_down_status();

                    int juncHr = this.collectorateInfoList.getJuncHr();
                    int juncMin = this.collectorateInfoList.getJuncMin();
                    int juncDat = this.collectorateInfoList.getJuncDate();
                    int juncMonth = this.collectorateInfoList.getJuncMonth();
                    int juncYear = this.collectorateInfoList.getJuncYear();

                        String response_data = "junction_id=" + junction_id + "#$" + "program_version_no=" + program_version_no + "#$" + "fileNo=" + fileNo
                                + "#$" + "functionNo=" + functionNo + "#$" + "activity=" + activity
                                + "#$" + "side_no=" + side_no + "#$" + "plan_no=" + plan_no
                                + "#$" + "junctionName=" + junctionName + "#$" + "sideName=" + sideName + "#$" + "onTime=" + onTime + "#$" + "offTime=" + offTime
                                + "#$" + "mode=" + mode + "#$" + "side1Name=" + side1Name + "#$" + "side2Name=" + side2Name + "#$" + "side3Name=" + side3Name
                                + "#$" + "side4Name=" + side4Name + "#$" + "side5Name=" + side5Name + "#$" + "side1Time=" + side1Time + "#$" + "side2Time=" + side2Time
                                + "#$" + "side3Time=" + side3Time + "#$" + "side4Time=" + side4Time + "#$" + "side1LeftStatus=" + side1LeftStatus
                                + "#$" + "side1RightStatus=" + side1RightStatus + "#$" + "side1UpStatus=" + side1UpStatus + "#$" + "side1DownStatus=" + side1DownStatus
                                + "#$" + "side2LeftStatus=" + side2LeftStatus + "#$" + "side2RightStatus=" + side2RightStatus + "#$" + "side2UpStatus=" + side2UpStatus
                                + "#$" + "side2DownStatus=" + side2DownStatus + "#$" + "side3LeftStatus=" + side3LeftStatus + "#$" + "side3RightStatus=" + side3RightStatus
                                + "#$" + "side3UpStatus=" + side3UpStatus + "#$" + "side3DownStatus=" + side3DownStatus + "#$" + "side4LeftStatus=" + side4LeftStatus
                                + "#$" + "side4RightStatus=" + side4RightStatus + "#$" + "side4UpStatus=" + side4UpStatus + "#$" + "side4DownStatus=" + side4DownStatus
                                + "#$" + "juncHr=" + juncHr + "#$" + "juncMin=" + juncMin + "#$" + "juncDat=" + juncDat + "#$" + "juncMonth=" + juncMonth
                                + "#$" + "juncYear=" + juncYear;
                        jsonObj.put("junction_id", junction_id + "");
                        jsonObj.put("program_version_no", program_version_no + "");
                        jsonObj.put("fileNo", fileNo + "");
                        jsonObj.put("functionNo", functionNo + "");
                        jsonObj.put("activity", activity + "");
                        jsonObj.put("side_no", side_no + "");
                        jsonObj.put("plan_no", plan_no + "");
                        jsonObj.put("junctionName", junctionName);
                        jsonObj.put("sideName", sideName);
                        jsonObj.put("onTime", onTime);
                        jsonObj.put("offTime", offTime);
                        jsonObj.put("mode", mode);
                        jsonObj.put("side1Name", side1Name);
                        jsonObj.put("side2Name", side2Name);
                        jsonObj.put("side3Name", side3Name);
                        jsonObj.put("side4Name", side4Name);
                        jsonObj.put("side5Name", side5Name);
                        jsonObj.put("side1Time", side1Time + "");
                        jsonObj.put("side2Time", side2Time + "");
                        jsonObj.put("side3Time", side3Time + "");
                        jsonObj.put("side4Time", side4Time + "");
                        jsonObj.put("side1LeftStatus", side1LeftStatus + "");
                        jsonObj.put("side1RightStatus", side1RightStatus + "");
                        jsonObj.put("side1UpStatus", side1UpStatus + "");
                        jsonObj.put("side1DownStatus", side1DownStatus + "");
                        jsonObj.put("side2LeftStatus", side2LeftStatus + "");
                        jsonObj.put("side2RightStatus", side2RightStatus + "");
                        jsonObj.put("side2UpStatus", side2UpStatus + "");
                        jsonObj.put("side2DownStatus", side2DownStatus + "");
                        jsonObj.put("side3LeftStatus", side3LeftStatus + "");
                        jsonObj.put("side3RightStatus", side3RightStatus + "");
                        jsonObj.put("side3UpStatus", side3UpStatus + "");
                        jsonObj.put("side3DownStatus", side3DownStatus + "");
                        jsonObj.put("side4LeftStatus", side4LeftStatus + "");
                        jsonObj.put("side4RightStatus", side4RightStatus + "");
                        jsonObj.put("side4UpStatus", side4UpStatus + "");
                        jsonObj.put("side4DownStatus", side4DownStatus + "");
                        jsonObj.put("juncHr", juncHr + "");
                        jsonObj.put("juncMin", juncMin + "");
                        jsonObj.put("juncDat", juncDat + "");
                        jsonObj.put("juncMonth", juncMonth + "");
                        jsonObj.put("juncYear", juncYear + "");
                        out.println(jsonObj);
                        out.flush();
                    }
                } else {
                    out.println("");
                }
                }
                //end of collectorate
                
                //start of bandariya tiraha
                if(current_junction_id == 15){
                if (this.bandariyaTirahaInfoList != null) {
                    boolean responseFromModemForRefresh = this.planInfoList.isResponseFromModemForRefresh();
                    //boolean responseFromModemForClearnace = this.planInfoList.isResponseFromModemForClearance();

                   // if ((responseFromModemForRefresh == true) || (responseFromModemForClearnace == true)) {
                    if ((responseFromModemForRefresh == true)) {
                        //if(junction_id1 == 2){
                    int functionNo = this.bandariyaTirahaInfoList.getFunction_no();
                    int junction_id = this.bandariyaTirahaInfoList.getJunction_id();
                    int program_version_no = this.bandariyaTirahaInfoList.getProgram_version_no();
                    int fileNo = this.bandariyaTirahaInfoList.getFileNo();
                    int activity = this.bandariyaTirahaInfoList.getActivity();
                    int side_no = this.bandariyaTirahaInfoList.getSide_no();
                    int plan_no = this.bandariyaTirahaInfoList.getPlan_no();
                    String mode = this.bandariyaTirahaInfoList.getMode();
                    String junctionName = this.bandariyaTirahaInfoList.getJunction_name();
                    String sideName = this.bandariyaTirahaInfoList.getSideName();

                    String onTime = this.bandariyaTirahaInfoList.getOnTime();
                    String offTime = this.bandariyaTirahaInfoList.getOffTime();

                    String side1Name = this.bandariyaTirahaInfoList.getSide1Name();
                    String side2Name = this.bandariyaTirahaInfoList.getSide2Name();
                    String side3Name = this.bandariyaTirahaInfoList.getSide3Name();
                    String side4Name = this.bandariyaTirahaInfoList.getSide4Name();
                    String side5Name = this.bandariyaTirahaInfoList.getSide5Name();

                    int side1Time = this.bandariyaTirahaInfoList.getSide1_time();
                    int side2Time = this.bandariyaTirahaInfoList.getSide2_time();
                    int side3Time = this.bandariyaTirahaInfoList.getSide3_time();
                    int side4Time = this.bandariyaTirahaInfoList.getSide4_time();
                    int side1LeftStatus = this.bandariyaTirahaInfoList.getSide1_left_status();
                    int side1RightStatus = this.bandariyaTirahaInfoList.getSide1_right_status();
                    int side1UpStatus = this.bandariyaTirahaInfoList.getSide1_up_status();
                    int side1DownStatus = this.bandariyaTirahaInfoList.getSide1_down_status();
                    int side2LeftStatus = this.bandariyaTirahaInfoList.getSide2_left_status();
                    int side2RightStatus = this.bandariyaTirahaInfoList.getSide2_right_status();
                    int side2UpStatus = this.bandariyaTirahaInfoList.getSide2_up_status();
                    int side2DownStatus = this.bandariyaTirahaInfoList.getSide2_down_status();
                    int side3LeftStatus = this.bandariyaTirahaInfoList.getSide3_left_status();
                    int side3RightStatus = this.bandariyaTirahaInfoList.getSide3_right_status();
                    int side3UpStatus = this.bandariyaTirahaInfoList.getSide3_up_status();
                    int side3DownStatus = this.bandariyaTirahaInfoList.getSide3_down_status();
                    int side4LeftStatus = this.bandariyaTirahaInfoList.getSide4_left_status();
                    int side4RightStatus = this.bandariyaTirahaInfoList.getSide4_right_status();
                    int side4UpStatus = this.bandariyaTirahaInfoList.getSide4_up_status();
                    int side4DownStatus = this.bandariyaTirahaInfoList.getSide4_down_status();

                    int juncHr = this.bandariyaTirahaInfoList.getJuncHr();
                    int juncMin = this.bandariyaTirahaInfoList.getJuncMin();
                    int juncDat = this.bandariyaTirahaInfoList.getJuncDate();
                    int juncMonth = this.bandariyaTirahaInfoList.getJuncMonth();
                    int juncYear = this.bandariyaTirahaInfoList.getJuncYear();

                        String response_data = "junction_id=" + junction_id + "#$" + "program_version_no=" + program_version_no + "#$" + "fileNo=" + fileNo
                                + "#$" + "functionNo=" + functionNo + "#$" + "activity=" + activity
                                + "#$" + "side_no=" + side_no + "#$" + "plan_no=" + plan_no
                                + "#$" + "junctionName=" + junctionName + "#$" + "sideName=" + sideName + "#$" + "onTime=" + onTime + "#$" + "offTime=" + offTime
                                + "#$" + "mode=" + mode + "#$" + "side1Name=" + side1Name + "#$" + "side2Name=" + side2Name + "#$" + "side3Name=" + side3Name
                                + "#$" + "side4Name=" + side4Name + "#$" + "side5Name=" + side5Name + "#$" + "side1Time=" + side1Time + "#$" + "side2Time=" + side2Time
                                + "#$" + "side3Time=" + side3Time + "#$" + "side4Time=" + side4Time + "#$" + "side1LeftStatus=" + side1LeftStatus
                                + "#$" + "side1RightStatus=" + side1RightStatus + "#$" + "side1UpStatus=" + side1UpStatus + "#$" + "side1DownStatus=" + side1DownStatus
                                + "#$" + "side2LeftStatus=" + side2LeftStatus + "#$" + "side2RightStatus=" + side2RightStatus + "#$" + "side2UpStatus=" + side2UpStatus
                                + "#$" + "side2DownStatus=" + side2DownStatus + "#$" + "side3LeftStatus=" + side3LeftStatus + "#$" + "side3RightStatus=" + side3RightStatus
                                + "#$" + "side3UpStatus=" + side3UpStatus + "#$" + "side3DownStatus=" + side3DownStatus + "#$" + "side4LeftStatus=" + side4LeftStatus
                                + "#$" + "side4RightStatus=" + side4RightStatus + "#$" + "side4UpStatus=" + side4UpStatus + "#$" + "side4DownStatus=" + side4DownStatus
                                + "#$" + "juncHr=" + juncHr + "#$" + "juncMin=" + juncMin + "#$" + "juncDat=" + juncDat + "#$" + "juncMonth=" + juncMonth
                                + "#$" + "juncYear=" + juncYear;
                        jsonObj.put("junction_id", junction_id + "");
                        jsonObj.put("program_version_no", program_version_no + "");
                        jsonObj.put("fileNo", fileNo + "");
                        jsonObj.put("functionNo", functionNo + "");
                        jsonObj.put("activity", activity + "");
                        jsonObj.put("side_no", side_no + "");
                        jsonObj.put("plan_no", plan_no + "");
                        jsonObj.put("junctionName", junctionName);
                        jsonObj.put("sideName", sideName);
                        jsonObj.put("onTime", onTime);
                        jsonObj.put("offTime", offTime);
                        jsonObj.put("mode", mode);
                        jsonObj.put("side1Name", side1Name);
                        jsonObj.put("side2Name", side2Name);
                        jsonObj.put("side3Name", side3Name);
                        jsonObj.put("side4Name", side4Name);
                        jsonObj.put("side5Name", side5Name);
                        jsonObj.put("side1Time", side1Time + "");
                        jsonObj.put("side2Time", side2Time + "");
                        jsonObj.put("side3Time", side3Time + "");
                        jsonObj.put("side4Time", side4Time + "");
                        jsonObj.put("side1LeftStatus", side1LeftStatus + "");
                        jsonObj.put("side1RightStatus", side1RightStatus + "");
                        jsonObj.put("side1UpStatus", side1UpStatus + "");
                        jsonObj.put("side1DownStatus", side1DownStatus + "");
                        jsonObj.put("side2LeftStatus", side2LeftStatus + "");
                        jsonObj.put("side2RightStatus", side2RightStatus + "");
                        jsonObj.put("side2UpStatus", side2UpStatus + "");
                        jsonObj.put("side2DownStatus", side2DownStatus + "");
                        jsonObj.put("side3LeftStatus", side3LeftStatus + "");
                        jsonObj.put("side3RightStatus", side3RightStatus + "");
                        jsonObj.put("side3UpStatus", side3UpStatus + "");
                        jsonObj.put("side3DownStatus", side3DownStatus + "");
                        jsonObj.put("side4LeftStatus", side4LeftStatus + "");
                        jsonObj.put("side4RightStatus", side4RightStatus + "");
                        jsonObj.put("side4UpStatus", side4UpStatus + "");
                        jsonObj.put("side4DownStatus", side4DownStatus + "");
                        jsonObj.put("juncHr", juncHr + "");
                        jsonObj.put("juncMin", juncMin + "");
                        jsonObj.put("juncDat", juncDat + "");
                        jsonObj.put("juncMonth", juncMonth + "");
                        jsonObj.put("juncYear", juncYear + "");
                        out.println(jsonObj);
                        out.flush();
                    }
                } else {
                    out.println("");
                }
                }
                //end of bandariya tiraha

                // start for damohnaka

                if(current_junction_id == 2){
                if (this.damohNakaInfoList != null) {
                    boolean responseFromModemForRefresh = this.planInfoList.isResponseFromModemForRefresh();
                    //boolean responseFromModemForClearnace = this.planInfoList.isResponseFromModemForClearance();

                   // if ((responseFromModemForRefresh == true) || (responseFromModemForClearnace == true)) {
                    if ((responseFromModemForRefresh == true)) {
                        //if(junction_id1 == 2){
                    int functionNo = this.damohNakaInfoList.getFunction_no();
                    int junction_id = this.damohNakaInfoList.getJunction_id();
                    int program_version_no = this.damohNakaInfoList.getProgram_version_no();
                    int fileNo = this.damohNakaInfoList.getFileNo();
                    int activity = this.damohNakaInfoList.getActivity();
                    int side_no = this.damohNakaInfoList.getSide_no();
                    int plan_no = this.damohNakaInfoList.getPlan_no();
                    String mode = this.damohNakaInfoList.getMode();
                    String junctionName = this.damohNakaInfoList.getJunction_name();
                    String sideName = this.damohNakaInfoList.getSideName();

                    String onTime = this.damohNakaInfoList.getOnTime();
                    String offTime = this.damohNakaInfoList.getOffTime();

                    String side1Name = this.damohNakaInfoList.getSide1Name();
                    String side2Name = this.damohNakaInfoList.getSide2Name();
                    String side3Name = this.damohNakaInfoList.getSide3Name();
                    String side4Name = this.damohNakaInfoList.getSide4Name();
                    String side5Name = this.damohNakaInfoList.getSide5Name();

                    int side1Time = this.damohNakaInfoList.getSide1_time();
                    int side2Time = this.damohNakaInfoList.getSide2_time();
                    int side3Time = this.damohNakaInfoList.getSide3_time();
                    int side4Time = this.damohNakaInfoList.getSide4_time();
                    int side1LeftStatus = this.damohNakaInfoList.getSide1_left_status();
                    int side1RightStatus = this.damohNakaInfoList.getSide1_right_status();
                    int side1UpStatus = this.damohNakaInfoList.getSide1_up_status();
                    int side1DownStatus = this.damohNakaInfoList.getSide1_down_status();
                    int side2LeftStatus = this.damohNakaInfoList.getSide2_left_status();
                    int side2RightStatus = this.damohNakaInfoList.getSide2_right_status();
                    int side2UpStatus = this.damohNakaInfoList.getSide2_up_status();
                    int side2DownStatus = this.damohNakaInfoList.getSide2_down_status();
                    int side3LeftStatus = this.damohNakaInfoList.getSide3_left_status();
                    int side3RightStatus = this.damohNakaInfoList.getSide3_right_status();
                    int side3UpStatus = this.damohNakaInfoList.getSide3_up_status();
                    int side3DownStatus = this.damohNakaInfoList.getSide3_down_status();
                    int side4LeftStatus = this.damohNakaInfoList.getSide4_left_status();
                    int side4RightStatus = this.damohNakaInfoList.getSide4_right_status();
                    int side4UpStatus = this.damohNakaInfoList.getSide4_up_status();
                    int side4DownStatus = this.damohNakaInfoList.getSide4_down_status();

                    int juncHr = this.damohNakaInfoList.getJuncHr();
                    int juncMin = this.damohNakaInfoList.getJuncMin();
                    int juncDat = this.damohNakaInfoList.getJuncDate();
                    int juncMonth = this.damohNakaInfoList.getJuncMonth();
                    int juncYear = this.damohNakaInfoList.getJuncYear();

                        String response_data = "junction_id=" + junction_id + "#$" + "program_version_no=" + program_version_no + "#$" + "fileNo=" + fileNo
                                + "#$" + "functionNo=" + functionNo + "#$" + "activity=" + activity
                                + "#$" + "side_no=" + side_no + "#$" + "plan_no=" + plan_no
                                + "#$" + "junctionName=" + junctionName + "#$" + "sideName=" + sideName + "#$" + "onTime=" + onTime + "#$" + "offTime=" + offTime
                                + "#$" + "mode=" + mode + "#$" + "side1Name=" + side1Name + "#$" + "side2Name=" + side2Name + "#$" + "side3Name=" + side3Name
                                + "#$" + "side4Name=" + side4Name + "#$" + "side5Name=" + side5Name + "#$" + "side1Time=" + side1Time + "#$" + "side2Time=" + side2Time
                                + "#$" + "side3Time=" + side3Time + "#$" + "side4Time=" + side4Time + "#$" + "side1LeftStatus=" + side1LeftStatus
                                + "#$" + "side1RightStatus=" + side1RightStatus + "#$" + "side1UpStatus=" + side1UpStatus + "#$" + "side1DownStatus=" + side1DownStatus
                                + "#$" + "side2LeftStatus=" + side2LeftStatus + "#$" + "side2RightStatus=" + side2RightStatus + "#$" + "side2UpStatus=" + side2UpStatus
                                + "#$" + "side2DownStatus=" + side2DownStatus + "#$" + "side3LeftStatus=" + side3LeftStatus + "#$" + "side3RightStatus=" + side3RightStatus
                                + "#$" + "side3UpStatus=" + side3UpStatus + "#$" + "side3DownStatus=" + side3DownStatus + "#$" + "side4LeftStatus=" + side4LeftStatus
                                + "#$" + "side4RightStatus=" + side4RightStatus + "#$" + "side4UpStatus=" + side4UpStatus + "#$" + "side4DownStatus=" + side4DownStatus
                                + "#$" + "juncHr=" + juncHr + "#$" + "juncMin=" + juncMin + "#$" + "juncDat=" + juncDat + "#$" + "juncMonth=" + juncMonth
                                + "#$" + "juncYear=" + juncYear;
                        jsonObj.put("junction_id", junction_id + "");
                        jsonObj.put("program_version_no", program_version_no + "");
                        jsonObj.put("fileNo", fileNo + "");
                        jsonObj.put("functionNo", functionNo + "");
                        jsonObj.put("activity", activity + "");
                        jsonObj.put("side_no", side_no + "");
                        jsonObj.put("plan_no", plan_no + "");
                        jsonObj.put("junctionName", junctionName);
                        jsonObj.put("sideName", sideName);
                        jsonObj.put("onTime", onTime);
                        jsonObj.put("offTime", offTime);
                        jsonObj.put("mode", mode);
                        jsonObj.put("side1Name", side1Name);
                        jsonObj.put("side2Name", side2Name);
                        jsonObj.put("side3Name", side3Name);
                        jsonObj.put("side4Name", side4Name);
                        jsonObj.put("side5Name", side5Name);
                        jsonObj.put("side1Time", side1Time + "");
                        jsonObj.put("side2Time", side2Time + "");
                        jsonObj.put("side3Time", side3Time + "");
                        jsonObj.put("side4Time", side4Time + "");
                        jsonObj.put("side1LeftStatus", side1LeftStatus + "");
                        jsonObj.put("side1RightStatus", side1RightStatus + "");
                        jsonObj.put("side1UpStatus", side1UpStatus + "");
                        jsonObj.put("side1DownStatus", side1DownStatus + "");
                        jsonObj.put("side2LeftStatus", side2LeftStatus + "");
                        jsonObj.put("side2RightStatus", side2RightStatus + "");
                        jsonObj.put("side2UpStatus", side2UpStatus + "");
                        jsonObj.put("side2DownStatus", side2DownStatus + "");
                        jsonObj.put("side3LeftStatus", side3LeftStatus + "");
                        jsonObj.put("side3RightStatus", side3RightStatus + "");
                        jsonObj.put("side3UpStatus", side3UpStatus + "");
                        jsonObj.put("side3DownStatus", side3DownStatus + "");
                        jsonObj.put("side4LeftStatus", side4LeftStatus + "");
                        jsonObj.put("side4RightStatus", side4RightStatus + "");
                        jsonObj.put("side4UpStatus", side4UpStatus + "");
                        jsonObj.put("side4DownStatus", side4DownStatus + "");
                        jsonObj.put("juncHr", juncHr + "");
                        jsonObj.put("juncMin", juncMin + "");
                        jsonObj.put("juncDat", juncDat + "");
                        jsonObj.put("juncMonth", juncMonth + "");
                        jsonObj.put("juncYear", juncYear + "");
                        out.println(jsonObj);
                        out.flush();
                    }
                } else {
                    out.println("");
                }
                }

                // end for damonaka

                // start for ranital signal

                if(current_junction_id == 11){
                if (this.ranitalInfoList != null) {
                    boolean responseFromModemForRefresh = this.planInfoList.isResponseFromModemForRefresh();
                    //boolean responseFromModemForClearnace = this.planInfoList.isResponseFromModemForClearance();

                   // if ((responseFromModemForRefresh == true) || (responseFromModemForClearnace == true)) {
                    if ((responseFromModemForRefresh == true)) {
                        //if(junction_id1 == 2){
                    int functionNo = this.ranitalInfoList.getFunction_no();
                    int junction_id = this.ranitalInfoList.getJunction_id();
                    int program_version_no = this.ranitalInfoList.getProgram_version_no();
                    int fileNo = this.ranitalInfoList.getFileNo();
                    int activity = this.ranitalInfoList.getActivity();
                    int side_no = this.ranitalInfoList.getSide_no();
                    int plan_no = this.ranitalInfoList.getPlan_no();
                    String mode = this.ranitalInfoList.getMode();
                    String junctionName = this.ranitalInfoList.getJunction_name();
                    String sideName = this.ranitalInfoList.getSideName();

                    String onTime = this.ranitalInfoList.getOnTime();
                    String offTime = this.ranitalInfoList.getOffTime();

                    String side1Name = this.ranitalInfoList.getSide1Name();
                    String side2Name = this.ranitalInfoList.getSide2Name();
                    String side3Name = this.ranitalInfoList.getSide3Name();
                    String side4Name = this.ranitalInfoList.getSide4Name();
                    String side5Name = this.ranitalInfoList.getSide5Name();

                    int side1Time = this.ranitalInfoList.getSide1_time();
                    int side2Time = this.ranitalInfoList.getSide2_time();
                    int side3Time = this.ranitalInfoList.getSide3_time();
                    int side4Time = this.ranitalInfoList.getSide4_time();
                    int side1LeftStatus = this.ranitalInfoList.getSide1_left_status();
                    int side1RightStatus = this.ranitalInfoList.getSide1_right_status();
                    int side1UpStatus = this.ranitalInfoList.getSide1_up_status();
                    int side1DownStatus = this.ranitalInfoList.getSide1_down_status();
                    int side2LeftStatus = this.ranitalInfoList.getSide2_left_status();
                    int side2RightStatus = this.ranitalInfoList.getSide2_right_status();
                    int side2UpStatus = this.ranitalInfoList.getSide2_up_status();
                    int side2DownStatus = this.ranitalInfoList.getSide2_down_status();
                    int side3LeftStatus = this.ranitalInfoList.getSide3_left_status();
                    int side3RightStatus = this.ranitalInfoList.getSide3_right_status();
                    int side3UpStatus = this.ranitalInfoList.getSide3_up_status();
                    int side3DownStatus = this.ranitalInfoList.getSide3_down_status();
                    int side4LeftStatus = this.ranitalInfoList.getSide4_left_status();
                    int side4RightStatus = this.ranitalInfoList.getSide4_right_status();
                    int side4UpStatus = this.ranitalInfoList.getSide4_up_status();
                    int side4DownStatus = this.ranitalInfoList.getSide4_down_status();

                    int juncHr = this.ranitalInfoList.getJuncHr();
                    int juncMin = this.ranitalInfoList.getJuncMin();
                    int juncDat = this.ranitalInfoList.getJuncDate();
                    int juncMonth = this.ranitalInfoList.getJuncMonth();
                    int juncYear = this.ranitalInfoList.getJuncYear();

                        String response_data = "junction_id=" + junction_id + "#$" + "program_version_no=" + program_version_no + "#$" + "fileNo=" + fileNo
                                + "#$" + "functionNo=" + functionNo + "#$" + "activity=" + activity
                                + "#$" + "side_no=" + side_no + "#$" + "plan_no=" + plan_no
                                + "#$" + "junctionName=" + junctionName + "#$" + "sideName=" + sideName + "#$" + "onTime=" + onTime + "#$" + "offTime=" + offTime
                                + "#$" + "mode=" + mode + "#$" + "side1Name=" + side1Name + "#$" + "side2Name=" + side2Name + "#$" + "side3Name=" + side3Name
                                + "#$" + "side4Name=" + side4Name + "#$" + "side5Name=" + side5Name + "#$" + "side1Time=" + side1Time + "#$" + "side2Time=" + side2Time
                                + "#$" + "side3Time=" + side3Time + "#$" + "side4Time=" + side4Time + "#$" + "side1LeftStatus=" + side1LeftStatus
                                + "#$" + "side1RightStatus=" + side1RightStatus + "#$" + "side1UpStatus=" + side1UpStatus + "#$" + "side1DownStatus=" + side1DownStatus
                                + "#$" + "side2LeftStatus=" + side2LeftStatus + "#$" + "side2RightStatus=" + side2RightStatus + "#$" + "side2UpStatus=" + side2UpStatus
                                + "#$" + "side2DownStatus=" + side2DownStatus + "#$" + "side3LeftStatus=" + side3LeftStatus + "#$" + "side3RightStatus=" + side3RightStatus
                                + "#$" + "side3UpStatus=" + side3UpStatus + "#$" + "side3DownStatus=" + side3DownStatus + "#$" + "side4LeftStatus=" + side4LeftStatus
                                + "#$" + "side4RightStatus=" + side4RightStatus + "#$" + "side4UpStatus=" + side4UpStatus + "#$" + "side4DownStatus=" + side4DownStatus
                                + "#$" + "juncHr=" + juncHr + "#$" + "juncMin=" + juncMin + "#$" + "juncDat=" + juncDat + "#$" + "juncMonth=" + juncMonth
                                + "#$" + "juncYear=" + juncYear;
                        jsonObj.put("junction_id", junction_id + "");
                        jsonObj.put("program_version_no", program_version_no + "");
                        jsonObj.put("fileNo", fileNo + "");
                        jsonObj.put("functionNo", functionNo + "");
                        jsonObj.put("activity", activity + "");
                        jsonObj.put("side_no", side_no + "");
                        jsonObj.put("plan_no", plan_no + "");
                        jsonObj.put("junctionName", junctionName);
                        jsonObj.put("sideName", sideName);
                        jsonObj.put("onTime", onTime);
                        jsonObj.put("offTime", offTime);
                        jsonObj.put("mode", mode);
                        jsonObj.put("side1Name", side1Name);
                        jsonObj.put("side2Name", side2Name);
                        jsonObj.put("side3Name", side3Name);
                        jsonObj.put("side4Name", side4Name);
                        jsonObj.put("side5Name", side5Name);
                        jsonObj.put("side1Time", side1Time + "");
                        jsonObj.put("side2Time", side2Time + "");
                        jsonObj.put("side3Time", side3Time + "");
                        jsonObj.put("side4Time", side4Time + "");
                        jsonObj.put("side1LeftStatus", side1LeftStatus + "");
                        jsonObj.put("side1RightStatus", side1RightStatus + "");
                        jsonObj.put("side1UpStatus", side1UpStatus + "");
                        jsonObj.put("side1DownStatus", side1DownStatus + "");
                        jsonObj.put("side2LeftStatus", side2LeftStatus + "");
                        jsonObj.put("side2RightStatus", side2RightStatus + "");
                        jsonObj.put("side2UpStatus", side2UpStatus + "");
                        jsonObj.put("side2DownStatus", side2DownStatus + "");
                        jsonObj.put("side3LeftStatus", side3LeftStatus + "");
                        jsonObj.put("side3RightStatus", side3RightStatus + "");
                        jsonObj.put("side3UpStatus", side3UpStatus + "");
                        jsonObj.put("side3DownStatus", side3DownStatus + "");
                        jsonObj.put("side4LeftStatus", side4LeftStatus + "");
                        jsonObj.put("side4RightStatus", side4RightStatus + "");
                        jsonObj.put("side4UpStatus", side4UpStatus + "");
                        jsonObj.put("side4DownStatus", side4DownStatus + "");
                        jsonObj.put("juncHr", juncHr + "");
                        jsonObj.put("juncMin", juncMin + "");
                        jsonObj.put("juncDat", juncDat + "");
                        jsonObj.put("juncMonth", juncMonth + "");
                        jsonObj.put("juncYear", juncYear + "");
                        out.println(jsonObj);
                        out.flush();

                    }
                } else {
                    out.println("");
                }
                }

                // end of ranital
                // starrt for yatayat

                if(current_junction_id == 1){
                if (this.yatayatThanaInfoList != null) {
                    boolean responseFromModemForRefresh = this.planInfoList.isResponseFromModemForRefresh();
                    //boolean responseFromModemForClearnace = this.planInfoList.isResponseFromModemForClearance();

                   // if ((responseFromModemForRefresh == true) || (responseFromModemForClearnace == true)) {
                    if ((responseFromModemForRefresh == true)) {
                        //if(junction_id1 == 2){
                    int functionNo = this.yatayatThanaInfoList.getFunction_no();
                    int junction_id = this.yatayatThanaInfoList.getJunction_id();
                    int program_version_no = this.yatayatThanaInfoList.getProgram_version_no();
                    int fileNo = this.yatayatThanaInfoList.getFileNo();
                    int activity = this.yatayatThanaInfoList.getActivity();
                    int side_no = this.yatayatThanaInfoList.getSide_no();
                    int plan_no = this.yatayatThanaInfoList.getPlan_no();
                    String mode = this.yatayatThanaInfoList.getMode();
                    String junctionName = this.yatayatThanaInfoList.getJunction_name();
                    String sideName = this.yatayatThanaInfoList.getSideName();

                    String onTime = this.yatayatThanaInfoList.getOnTime();
                    String offTime = this.yatayatThanaInfoList.getOffTime();

                    String side1Name = this.yatayatThanaInfoList.getSide1Name();
                    String side2Name = this.yatayatThanaInfoList.getSide2Name();
                    String side3Name = this.yatayatThanaInfoList.getSide3Name();
                    String side4Name = this.yatayatThanaInfoList.getSide4Name();
                    String side5Name = this.yatayatThanaInfoList.getSide5Name();

                    int side1Time = this.yatayatThanaInfoList.getSide1_time();
                    int side2Time = this.yatayatThanaInfoList.getSide2_time();
                    int side3Time = this.yatayatThanaInfoList.getSide3_time();
                    int side4Time = this.yatayatThanaInfoList.getSide4_time();
                    int side1LeftStatus = this.yatayatThanaInfoList.getSide1_left_status();
                    int side1RightStatus = this.yatayatThanaInfoList.getSide1_right_status();
                    int side1UpStatus = this.yatayatThanaInfoList.getSide1_up_status();
                    int side1DownStatus = this.yatayatThanaInfoList.getSide1_down_status();
                    int side2LeftStatus = this.yatayatThanaInfoList.getSide2_left_status();
                    int side2RightStatus = this.yatayatThanaInfoList.getSide2_right_status();
                    int side2UpStatus = this.yatayatThanaInfoList.getSide2_up_status();
                    int side2DownStatus = this.yatayatThanaInfoList.getSide2_down_status();
                    int side3LeftStatus = this.yatayatThanaInfoList.getSide3_left_status();
                    int side3RightStatus = this.yatayatThanaInfoList.getSide3_right_status();
                    int side3UpStatus = this.yatayatThanaInfoList.getSide3_up_status();
                    int side3DownStatus = this.yatayatThanaInfoList.getSide3_down_status();
                    int side4LeftStatus = this.yatayatThanaInfoList.getSide4_left_status();
                    int side4RightStatus = this.yatayatThanaInfoList.getSide4_right_status();
                    int side4UpStatus = this.yatayatThanaInfoList.getSide4_up_status();
                    int side4DownStatus = this.yatayatThanaInfoList.getSide4_down_status();

                    int juncHr = this.yatayatThanaInfoList.getJuncHr();
                    int juncMin = this.yatayatThanaInfoList.getJuncMin();
                    int juncDat = this.yatayatThanaInfoList.getJuncDate();
                    int juncMonth = this.yatayatThanaInfoList.getJuncMonth();
                    int juncYear = this.yatayatThanaInfoList.getJuncYear();

                        String response_data = "junction_id=" + junction_id + "#$" + "program_version_no=" + program_version_no + "#$" + "fileNo=" + fileNo
                                + "#$" + "functionNo=" + functionNo + "#$" + "activity=" + activity
                                + "#$" + "side_no=" + side_no + "#$" + "plan_no=" + plan_no
                                + "#$" + "junctionName=" + junctionName + "#$" + "sideName=" + sideName + "#$" + "onTime=" + onTime + "#$" + "offTime=" + offTime
                                + "#$" + "mode=" + mode + "#$" + "side1Name=" + side1Name + "#$" + "side2Name=" + side2Name + "#$" + "side3Name=" + side3Name
                                + "#$" + "side4Name=" + side4Name + "#$" + "side5Name=" + side5Name + "#$" + "side1Time=" + side1Time + "#$" + "side2Time=" + side2Time
                                + "#$" + "side3Time=" + side3Time + "#$" + "side4Time=" + side4Time + "#$" + "side1LeftStatus=" + side1LeftStatus
                                + "#$" + "side1RightStatus=" + side1RightStatus + "#$" + "side1UpStatus=" + side1UpStatus + "#$" + "side1DownStatus=" + side1DownStatus
                                + "#$" + "side2LeftStatus=" + side2LeftStatus + "#$" + "side2RightStatus=" + side2RightStatus + "#$" + "side2UpStatus=" + side2UpStatus
                                + "#$" + "side2DownStatus=" + side2DownStatus + "#$" + "side3LeftStatus=" + side3LeftStatus + "#$" + "side3RightStatus=" + side3RightStatus
                                + "#$" + "side3UpStatus=" + side3UpStatus + "#$" + "side3DownStatus=" + side3DownStatus + "#$" + "side4LeftStatus=" + side4LeftStatus
                                + "#$" + "side4RightStatus=" + side4RightStatus + "#$" + "side4UpStatus=" + side4UpStatus + "#$" + "side4DownStatus=" + side4DownStatus
                                + "#$" + "juncHr=" + juncHr + "#$" + "juncMin=" + juncMin + "#$" + "juncDat=" + juncDat + "#$" + "juncMonth=" + juncMonth
                                + "#$" + "juncYear=" + juncYear;
                        jsonObj.put("junction_id", junction_id + "");
                        jsonObj.put("program_version_no", program_version_no + "");
                        jsonObj.put("fileNo", fileNo + "");
                        jsonObj.put("functionNo", functionNo + "");
                        jsonObj.put("activity", activity + "");
                        jsonObj.put("side_no", side_no + "");
                        jsonObj.put("plan_no", plan_no + "");
                        jsonObj.put("junctionName", junctionName);
                        jsonObj.put("sideName", sideName);
                        jsonObj.put("onTime", onTime);
                        jsonObj.put("offTime", offTime);
                        jsonObj.put("mode", mode);
                        jsonObj.put("side1Name", side1Name);
                        jsonObj.put("side2Name", side2Name);
                        jsonObj.put("side3Name", side3Name);
                        jsonObj.put("side4Name", side4Name);
                        jsonObj.put("side5Name", side5Name);
                        jsonObj.put("side1Time", side1Time + "");
                        jsonObj.put("side2Time", side2Time + "");
                        jsonObj.put("side3Time", side3Time + "");
                        jsonObj.put("side4Time", side4Time + "");
                        jsonObj.put("side1LeftStatus", side1LeftStatus + "");
                        jsonObj.put("side1RightStatus", side1RightStatus + "");
                        jsonObj.put("side1UpStatus", side1UpStatus + "");
                        jsonObj.put("side1DownStatus", side1DownStatus + "");
                        jsonObj.put("side2LeftStatus", side2LeftStatus + "");
                        jsonObj.put("side2RightStatus", side2RightStatus + "");
                        jsonObj.put("side2UpStatus", side2UpStatus + "");
                        jsonObj.put("side2DownStatus", side2DownStatus + "");
                        jsonObj.put("side3LeftStatus", side3LeftStatus + "");
                        jsonObj.put("side3RightStatus", side3RightStatus + "");
                        jsonObj.put("side3UpStatus", side3UpStatus + "");
                        jsonObj.put("side3DownStatus", side3DownStatus + "");
                        jsonObj.put("side4LeftStatus", side4LeftStatus + "");
                        jsonObj.put("side4RightStatus", side4RightStatus + "");
                        jsonObj.put("side4UpStatus", side4UpStatus + "");
                        jsonObj.put("side4DownStatus", side4DownStatus + "");
                        jsonObj.put("juncHr", juncHr + "");
                        jsonObj.put("juncMin", juncMin + "");
                        jsonObj.put("juncDat", juncDat + "");
                        jsonObj.put("juncMonth", juncMonth + "");
                        jsonObj.put("juncYear", juncYear + "");
                        out.println(jsonObj);
                        out.flush();

                    }
                } else {
                    out.println("");
                }
                }
                // end for yatayat
                // start for katanga

                if(current_junction_id == 4){
                if (this.katangaInfoList != null) {
                    boolean responseFromModemForRefresh = this.planInfoList.isResponseFromModemForRefresh();
                    //boolean responseFromModemForClearnace = this.planInfoList.isResponseFromModemForClearance();

                   // if ((responseFromModemForRefresh == true) || (responseFromModemForClearnace == true)) {
                    if ((responseFromModemForRefresh == true)) {
                        //if(junction_id1 == 2){
                    int functionNo = this.katangaInfoList.getFunction_no();
                    int junction_id = this.katangaInfoList.getJunction_id();
                    int program_version_no = this.katangaInfoList.getProgram_version_no();
                    int fileNo = this.katangaInfoList.getFileNo();
                    int activity = this.katangaInfoList.getActivity();
                    int side_no = this.katangaInfoList.getSide_no();
                    int plan_no = this.katangaInfoList.getPlan_no();
                    String mode = this.katangaInfoList.getMode();
                    String junctionName = this.katangaInfoList.getJunction_name();
                    String sideName = this.katangaInfoList.getSideName();

                    String onTime = this.katangaInfoList.getOnTime();
                    String offTime = this.katangaInfoList.getOffTime();

                    String side1Name = this.katangaInfoList.getSide1Name();
                    String side2Name = this.katangaInfoList.getSide2Name();
                    String side3Name = this.katangaInfoList.getSide3Name();
                    String side4Name = this.katangaInfoList.getSide4Name();
                    String side5Name = this.katangaInfoList.getSide5Name();

                    int side1Time = this.katangaInfoList.getSide1_time();
                    int side2Time = this.katangaInfoList.getSide2_time();
                    int side3Time = this.katangaInfoList.getSide3_time();
                    int side4Time = this.katangaInfoList.getSide4_time();
                    int side1LeftStatus = this.katangaInfoList.getSide1_left_status();
                    int side1RightStatus = this.katangaInfoList.getSide1_right_status();
                    int side1UpStatus = this.katangaInfoList.getSide1_up_status();
                    int side1DownStatus = this.katangaInfoList.getSide1_down_status();
                    int side2LeftStatus = this.katangaInfoList.getSide2_left_status();
                    int side2RightStatus = this.katangaInfoList.getSide2_right_status();
                    int side2UpStatus = this.katangaInfoList.getSide2_up_status();
                    int side2DownStatus = this.katangaInfoList.getSide2_down_status();
                    int side3LeftStatus = this.katangaInfoList.getSide3_left_status();
                    int side3RightStatus = this.katangaInfoList.getSide3_right_status();
                    int side3UpStatus = this.katangaInfoList.getSide3_up_status();
                    int side3DownStatus = this.katangaInfoList.getSide3_down_status();
                    int side4LeftStatus = this.katangaInfoList.getSide4_left_status();
                    int side4RightStatus = this.katangaInfoList.getSide4_right_status();
                    int side4UpStatus = this.katangaInfoList.getSide4_up_status();
                    int side4DownStatus = this.katangaInfoList.getSide4_down_status();

                    int juncHr = this.katangaInfoList.getJuncHr();
                    int juncMin = this.katangaInfoList.getJuncMin();
                    int juncDat = this.katangaInfoList.getJuncDate();
                    int juncMonth = this.katangaInfoList.getJuncMonth();
                    int juncYear = this.katangaInfoList.getJuncYear();

                        String response_data = "junction_id=" + junction_id + "#$" + "program_version_no=" + program_version_no + "#$" + "fileNo=" + fileNo
                                + "#$" + "functionNo=" + functionNo + "#$" + "activity=" + activity
                                + "#$" + "side_no=" + side_no + "#$" + "plan_no=" + plan_no
                                + "#$" + "junctionName=" + junctionName + "#$" + "sideName=" + sideName + "#$" + "onTime=" + onTime + "#$" + "offTime=" + offTime
                                + "#$" + "mode=" + mode + "#$" + "side1Name=" + side1Name + "#$" + "side2Name=" + side2Name + "#$" + "side3Name=" + side3Name
                                + "#$" + "side4Name=" + side4Name + "#$" + "side5Name=" + side5Name + "#$" + "side1Time=" + side1Time + "#$" + "side2Time=" + side2Time
                                + "#$" + "side3Time=" + side3Time + "#$" + "side4Time=" + side4Time + "#$" + "side1LeftStatus=" + side1LeftStatus
                                + "#$" + "side1RightStatus=" + side1RightStatus + "#$" + "side1UpStatus=" + side1UpStatus + "#$" + "side1DownStatus=" + side1DownStatus
                                + "#$" + "side2LeftStatus=" + side2LeftStatus + "#$" + "side2RightStatus=" + side2RightStatus + "#$" + "side2UpStatus=" + side2UpStatus
                                + "#$" + "side2DownStatus=" + side2DownStatus + "#$" + "side3LeftStatus=" + side3LeftStatus + "#$" + "side3RightStatus=" + side3RightStatus
                                + "#$" + "side3UpStatus=" + side3UpStatus + "#$" + "side3DownStatus=" + side3DownStatus + "#$" + "side4LeftStatus=" + side4LeftStatus
                                + "#$" + "side4RightStatus=" + side4RightStatus + "#$" + "side4UpStatus=" + side4UpStatus + "#$" + "side4DownStatus=" + side4DownStatus
                                + "#$" + "juncHr=" + juncHr + "#$" + "juncMin=" + juncMin + "#$" + "juncDat=" + juncDat + "#$" + "juncMonth=" + juncMonth
                                + "#$" + "juncYear=" + juncYear;
                        jsonObj.put("junction_id", junction_id + "");
                        jsonObj.put("program_version_no", program_version_no + "");
                        jsonObj.put("fileNo", fileNo + "");
                        jsonObj.put("functionNo", functionNo + "");
                        jsonObj.put("activity", activity + "");
                        jsonObj.put("side_no", side_no + "");
                        jsonObj.put("plan_no", plan_no + "");
                        jsonObj.put("junctionName", junctionName);
                        jsonObj.put("sideName", sideName);
                        jsonObj.put("onTime", onTime);
                        jsonObj.put("offTime", offTime);
                        jsonObj.put("mode", mode);
                        jsonObj.put("side1Name", side1Name);
                        jsonObj.put("side2Name", side2Name);
                        jsonObj.put("side3Name", side3Name);
                        jsonObj.put("side4Name", side4Name);
                        jsonObj.put("side5Name", side5Name);
                        jsonObj.put("side1Time", side1Time + "");
                        jsonObj.put("side2Time", side2Time + "");
                        jsonObj.put("side3Time", side3Time + "");
                        jsonObj.put("side4Time", side4Time + "");
                        jsonObj.put("side1LeftStatus", side1LeftStatus + "");
                        jsonObj.put("side1RightStatus", side1RightStatus + "");
                        jsonObj.put("side1UpStatus", side1UpStatus + "");
                        jsonObj.put("side1DownStatus", side1DownStatus + "");
                        jsonObj.put("side2LeftStatus", side2LeftStatus + "");
                        jsonObj.put("side2RightStatus", side2RightStatus + "");
                        jsonObj.put("side2UpStatus", side2UpStatus + "");
                        jsonObj.put("side2DownStatus", side2DownStatus + "");
                        jsonObj.put("side3LeftStatus", side3LeftStatus + "");
                        jsonObj.put("side3RightStatus", side3RightStatus + "");
                        jsonObj.put("side3UpStatus", side3UpStatus + "");
                        jsonObj.put("side3DownStatus", side3DownStatus + "");
                        jsonObj.put("side4LeftStatus", side4LeftStatus + "");
                        jsonObj.put("side4RightStatus", side4RightStatus + "");
                        jsonObj.put("side4UpStatus", side4UpStatus + "");
                        jsonObj.put("side4DownStatus", side4DownStatus + "");
                        jsonObj.put("juncHr", juncHr + "");
                        jsonObj.put("juncMin", juncMin + "");
                        jsonObj.put("juncDat", juncDat + "");
                        jsonObj.put("juncMonth", juncMonth + "");
                        jsonObj.put("juncYear", juncYear + "");
                        out.println(jsonObj);
                        out.flush();

                    }
                } else {
                    out.println("");
                }
                }

                // end for katanga
                // start for baldeobag

                if(current_junction_id == 5){
                if (this.baldeobagInfoList != null) {
                    boolean responseFromModemForRefresh = this.planInfoList.isResponseFromModemForRefresh();
                    //boolean responseFromModemForClearnace = this.planInfoList.isResponseFromModemForClearance();

                   // if ((responseFromModemForRefresh == true) || (responseFromModemForClearnace == true)) {
                    if ((responseFromModemForRefresh == true)) {
                        //if(junction_id1 == 2){
                    int functionNo = this.baldeobagInfoList.getFunction_no();
                    int junction_id = this.baldeobagInfoList.getJunction_id();
                    int program_version_no = this.baldeobagInfoList.getProgram_version_no();
                    int fileNo = this.baldeobagInfoList.getFileNo();
                    int activity = this.baldeobagInfoList.getActivity();
                    int side_no = this.baldeobagInfoList.getSide_no();
                    int plan_no = this.baldeobagInfoList.getPlan_no();
                    String mode = this.baldeobagInfoList.getMode();
                    String junctionName = this.baldeobagInfoList.getJunction_name();
                    String sideName = this.baldeobagInfoList.getSideName();

                    String onTime = this.baldeobagInfoList.getOnTime();
                    String offTime = this.baldeobagInfoList.getOffTime();

                    String side1Name = this.baldeobagInfoList.getSide1Name();
                    String side2Name = this.baldeobagInfoList.getSide2Name();
                    String side3Name = this.baldeobagInfoList.getSide3Name();
                    String side4Name = this.baldeobagInfoList.getSide4Name();
                    String side5Name = this.baldeobagInfoList.getSide5Name();

                    int side1Time = this.baldeobagInfoList.getSide1_time();
                    int side2Time = this.baldeobagInfoList.getSide2_time();
                    int side3Time = this.baldeobagInfoList.getSide3_time();
                    int side4Time = this.baldeobagInfoList.getSide4_time();
                    int side1LeftStatus = this.baldeobagInfoList.getSide1_left_status();
                    int side1RightStatus = this.baldeobagInfoList.getSide1_right_status();
                    int side1UpStatus = this.baldeobagInfoList.getSide1_up_status();
                    int side1DownStatus = this.baldeobagInfoList.getSide1_down_status();
                    int side2LeftStatus = this.baldeobagInfoList.getSide2_left_status();
                    int side2RightStatus = this.baldeobagInfoList.getSide2_right_status();
                    int side2UpStatus = this.baldeobagInfoList.getSide2_up_status();
                    int side2DownStatus = this.baldeobagInfoList.getSide2_down_status();
                    int side3LeftStatus = this.baldeobagInfoList.getSide3_left_status();
                    int side3RightStatus = this.baldeobagInfoList.getSide3_right_status();
                    int side3UpStatus = this.baldeobagInfoList.getSide3_up_status();
                    int side3DownStatus = this.baldeobagInfoList.getSide3_down_status();
                    int side4LeftStatus = this.baldeobagInfoList.getSide4_left_status();
                    int side4RightStatus = this.baldeobagInfoList.getSide4_right_status();
                    int side4UpStatus = this.baldeobagInfoList.getSide4_up_status();
                    int side4DownStatus = this.baldeobagInfoList.getSide4_down_status();

                    int juncHr = this.baldeobagInfoList.getJuncHr();
                    int juncMin = this.baldeobagInfoList.getJuncMin();
                    int juncDat = this.baldeobagInfoList.getJuncDate();
                    int juncMonth = this.baldeobagInfoList.getJuncMonth();
                    int juncYear = this.baldeobagInfoList.getJuncYear();

                        String response_data = "junction_id=" + junction_id + "#$" + "program_version_no=" + program_version_no + "#$" + "fileNo=" + fileNo
                                + "#$" + "functionNo=" + functionNo + "#$" + "activity=" + activity
                                + "#$" + "side_no=" + side_no + "#$" + "plan_no=" + plan_no
                                + "#$" + "junctionName=" + junctionName + "#$" + "sideName=" + sideName + "#$" + "onTime=" + onTime + "#$" + "offTime=" + offTime
                                + "#$" + "mode=" + mode + "#$" + "side1Name=" + side1Name + "#$" + "side2Name=" + side2Name + "#$" + "side3Name=" + side3Name
                                + "#$" + "side4Name=" + side4Name + "#$" + "side5Name=" + side5Name + "#$" + "side1Time=" + side1Time + "#$" + "side2Time=" + side2Time
                                + "#$" + "side3Time=" + side3Time + "#$" + "side4Time=" + side4Time + "#$" + "side1LeftStatus=" + side1LeftStatus
                                + "#$" + "side1RightStatus=" + side1RightStatus + "#$" + "side1UpStatus=" + side1UpStatus + "#$" + "side1DownStatus=" + side1DownStatus
                                + "#$" + "side2LeftStatus=" + side2LeftStatus + "#$" + "side2RightStatus=" + side2RightStatus + "#$" + "side2UpStatus=" + side2UpStatus
                                + "#$" + "side2DownStatus=" + side2DownStatus + "#$" + "side3LeftStatus=" + side3LeftStatus + "#$" + "side3RightStatus=" + side3RightStatus
                                + "#$" + "side3UpStatus=" + side3UpStatus + "#$" + "side3DownStatus=" + side3DownStatus + "#$" + "side4LeftStatus=" + side4LeftStatus
                                + "#$" + "side4RightStatus=" + side4RightStatus + "#$" + "side4UpStatus=" + side4UpStatus + "#$" + "side4DownStatus=" + side4DownStatus
                                + "#$" + "juncHr=" + juncHr + "#$" + "juncMin=" + juncMin + "#$" + "juncDat=" + juncDat + "#$" + "juncMonth=" + juncMonth
                                + "#$" + "juncYear=" + juncYear;
                        jsonObj.put("junction_id", junction_id + "");
                        jsonObj.put("program_version_no", program_version_no + "");
                        jsonObj.put("fileNo", fileNo + "");
                        jsonObj.put("functionNo", functionNo + "");
                        jsonObj.put("activity", activity + "");
                        jsonObj.put("side_no", side_no + "");
                        jsonObj.put("plan_no", plan_no + "");
                        jsonObj.put("junctionName", junctionName);
                        jsonObj.put("sideName", sideName);
                        jsonObj.put("onTime", onTime);
                        jsonObj.put("offTime", offTime);
                        jsonObj.put("mode", mode);
                        jsonObj.put("side1Name", side1Name);
                        jsonObj.put("side2Name", side2Name);
                        jsonObj.put("side3Name", side3Name);
                        jsonObj.put("side4Name", side4Name);
                        jsonObj.put("side5Name", side5Name);
                        jsonObj.put("side1Time", side1Time + "");
                        jsonObj.put("side2Time", side2Time + "");
                        jsonObj.put("side3Time", side3Time + "");
                        jsonObj.put("side4Time", side4Time + "");
                        jsonObj.put("side1LeftStatus", side1LeftStatus + "");
                        jsonObj.put("side1RightStatus", side1RightStatus + "");
                        jsonObj.put("side1UpStatus", side1UpStatus + "");
                        jsonObj.put("side1DownStatus", side1DownStatus + "");
                        jsonObj.put("side2LeftStatus", side2LeftStatus + "");
                        jsonObj.put("side2RightStatus", side2RightStatus + "");
                        jsonObj.put("side2UpStatus", side2UpStatus + "");
                        jsonObj.put("side2DownStatus", side2DownStatus + "");
                        jsonObj.put("side3LeftStatus", side3LeftStatus + "");
                        jsonObj.put("side3RightStatus", side3RightStatus + "");
                        jsonObj.put("side3UpStatus", side3UpStatus + "");
                        jsonObj.put("side3DownStatus", side3DownStatus + "");
                        jsonObj.put("side4LeftStatus", side4LeftStatus + "");
                        jsonObj.put("side4RightStatus", side4RightStatus + "");
                        jsonObj.put("side4UpStatus", side4UpStatus + "");
                        jsonObj.put("side4DownStatus", side4DownStatus + "");
                        jsonObj.put("juncHr", juncHr + "");
                        jsonObj.put("juncMin", juncMin + "");
                        jsonObj.put("juncDat", juncDat + "");
                        jsonObj.put("juncMonth", juncMonth + "");
                        jsonObj.put("juncYear", juncYear + "");
                        out.println(jsonObj);
                        out.flush();

                    }
                } else {
                    out.println("");
                }
                }

                // end for baldeobag
                // start for deendayal
                
                if(current_junction_id == 6){
                if (this.deendayalChowkInfoList != null) {
                    boolean responseFromModemForRefresh = this.planInfoList.isResponseFromModemForRefresh();
                    //boolean responseFromModemForClearnace = this.planInfoList.isResponseFromModemForClearance();

                   // if ((responseFromModemForRefresh == true) || (responseFromModemForClearnace == true)) {
                    if ((responseFromModemForRefresh == true)) {
                        //if(junction_id1 == 2){
                    int functionNo = this.deendayalChowkInfoList.getFunction_no();
                    int junction_id = this.deendayalChowkInfoList.getJunction_id();
                    int program_version_no = this.deendayalChowkInfoList.getProgram_version_no();
                    int fileNo = this.deendayalChowkInfoList.getFileNo();
                    int activity = this.deendayalChowkInfoList.getActivity();
                    int side_no = this.deendayalChowkInfoList.getSide_no();
                    int plan_no = this.deendayalChowkInfoList.getPlan_no();
                    String mode = this.deendayalChowkInfoList.getMode();
                    String junctionName = this.deendayalChowkInfoList.getJunction_name();
                    String sideName = this.deendayalChowkInfoList.getSideName();

                    String onTime = this.deendayalChowkInfoList.getOnTime();
                    String offTime = this.deendayalChowkInfoList.getOffTime();

                    String side1Name = this.deendayalChowkInfoList.getSide1Name();
                    String side2Name = this.deendayalChowkInfoList.getSide2Name();
                    String side3Name = this.deendayalChowkInfoList.getSide3Name();
                    String side4Name = this.deendayalChowkInfoList.getSide4Name();
                    String side5Name = this.deendayalChowkInfoList.getSide5Name();

                    int side1Time = this.deendayalChowkInfoList.getSide1_time();
                    int side2Time = this.deendayalChowkInfoList.getSide2_time();
                    int side3Time = this.deendayalChowkInfoList.getSide3_time();
                    int side4Time = this.deendayalChowkInfoList.getSide4_time();
                    int side1LeftStatus = this.deendayalChowkInfoList.getSide1_left_status();
                    int side1RightStatus = this.deendayalChowkInfoList.getSide1_right_status();
                    int side1UpStatus = this.deendayalChowkInfoList.getSide1_up_status();
                    int side1DownStatus = this.deendayalChowkInfoList.getSide1_down_status();
                    int side2LeftStatus = this.deendayalChowkInfoList.getSide2_left_status();
                    int side2RightStatus = this.deendayalChowkInfoList.getSide2_right_status();
                    int side2UpStatus = this.deendayalChowkInfoList.getSide2_up_status();
                    int side2DownStatus = this.deendayalChowkInfoList.getSide2_down_status();
                    int side3LeftStatus = this.deendayalChowkInfoList.getSide3_left_status();
                    int side3RightStatus = this.deendayalChowkInfoList.getSide3_right_status();
                    int side3UpStatus = this.deendayalChowkInfoList.getSide3_up_status();
                    int side3DownStatus = this.deendayalChowkInfoList.getSide3_down_status();
                    int side4LeftStatus = this.deendayalChowkInfoList.getSide4_left_status();
                    int side4RightStatus = this.deendayalChowkInfoList.getSide4_right_status();
                    int side4UpStatus = this.deendayalChowkInfoList.getSide4_up_status();
                    int side4DownStatus = this.deendayalChowkInfoList.getSide4_down_status();

                    int juncHr = this.deendayalChowkInfoList.getJuncHr();
                    int juncMin = this.deendayalChowkInfoList.getJuncMin();
                    int juncDat = this.deendayalChowkInfoList.getJuncDate();
                    int juncMonth = this.deendayalChowkInfoList.getJuncMonth();
                    int juncYear = this.deendayalChowkInfoList.getJuncYear();

                        String response_data = "junction_id=" + junction_id + "#$" + "program_version_no=" + program_version_no + "#$" + "fileNo=" + fileNo
                                + "#$" + "functionNo=" + functionNo + "#$" + "activity=" + activity
                                + "#$" + "side_no=" + side_no + "#$" + "plan_no=" + plan_no
                                + "#$" + "junctionName=" + junctionName + "#$" + "sideName=" + sideName + "#$" + "onTime=" + onTime + "#$" + "offTime=" + offTime
                                + "#$" + "mode=" + mode + "#$" + "side1Name=" + side1Name + "#$" + "side2Name=" + side2Name + "#$" + "side3Name=" + side3Name
                                + "#$" + "side4Name=" + side4Name + "#$" + "side5Name=" + side5Name + "#$" + "side1Time=" + side1Time + "#$" + "side2Time=" + side2Time
                                + "#$" + "side3Time=" + side3Time + "#$" + "side4Time=" + side4Time + "#$" + "side1LeftStatus=" + side1LeftStatus
                                + "#$" + "side1RightStatus=" + side1RightStatus + "#$" + "side1UpStatus=" + side1UpStatus + "#$" + "side1DownStatus=" + side1DownStatus
                                + "#$" + "side2LeftStatus=" + side2LeftStatus + "#$" + "side2RightStatus=" + side2RightStatus + "#$" + "side2UpStatus=" + side2UpStatus
                                + "#$" + "side2DownStatus=" + side2DownStatus + "#$" + "side3LeftStatus=" + side3LeftStatus + "#$" + "side3RightStatus=" + side3RightStatus
                                + "#$" + "side3UpStatus=" + side3UpStatus + "#$" + "side3DownStatus=" + side3DownStatus + "#$" + "side4LeftStatus=" + side4LeftStatus
                                + "#$" + "side4RightStatus=" + side4RightStatus + "#$" + "side4UpStatus=" + side4UpStatus + "#$" + "side4DownStatus=" + side4DownStatus
                                + "#$" + "juncHr=" + juncHr + "#$" + "juncMin=" + juncMin + "#$" + "juncDat=" + juncDat + "#$" + "juncMonth=" + juncMonth
                                + "#$" + "juncYear=" + juncYear;
                        jsonObj.put("junction_id", junction_id + "");
                        jsonObj.put("program_version_no", program_version_no + "");
                        jsonObj.put("fileNo", fileNo + "");
                        jsonObj.put("functionNo", functionNo + "");
                        jsonObj.put("activity", activity + "");
                        jsonObj.put("side_no", side_no + "");
                        jsonObj.put("plan_no", plan_no + "");
                        jsonObj.put("junctionName", junctionName);
                        jsonObj.put("sideName", sideName);
                        jsonObj.put("onTime", onTime);
                        jsonObj.put("offTime", offTime);
                        jsonObj.put("mode", mode);
                        jsonObj.put("side1Name", side1Name);
                        jsonObj.put("side2Name", side2Name);
                        jsonObj.put("side3Name", side3Name);
                        jsonObj.put("side4Name", side4Name);
                        jsonObj.put("side5Name", side5Name);
                        jsonObj.put("side1Time", side1Time + "");
                        jsonObj.put("side2Time", side2Time + "");
                        jsonObj.put("side3Time", side3Time + "");
                        jsonObj.put("side4Time", side4Time + "");
                        jsonObj.put("side1LeftStatus", side1LeftStatus + "");
                        jsonObj.put("side1RightStatus", side1RightStatus + "");
                        jsonObj.put("side1UpStatus", side1UpStatus + "");
                        jsonObj.put("side1DownStatus", side1DownStatus + "");
                        jsonObj.put("side2LeftStatus", side2LeftStatus + "");
                        jsonObj.put("side2RightStatus", side2RightStatus + "");
                        jsonObj.put("side2UpStatus", side2UpStatus + "");
                        jsonObj.put("side2DownStatus", side2DownStatus + "");
                        jsonObj.put("side3LeftStatus", side3LeftStatus + "");
                        jsonObj.put("side3RightStatus", side3RightStatus + "");
                        jsonObj.put("side3UpStatus", side3UpStatus + "");
                        jsonObj.put("side3DownStatus", side3DownStatus + "");
                        jsonObj.put("side4LeftStatus", side4LeftStatus + "");
                        jsonObj.put("side4RightStatus", side4RightStatus + "");
                        jsonObj.put("side4UpStatus", side4UpStatus + "");
                        jsonObj.put("side4DownStatus", side4DownStatus + "");
                        jsonObj.put("juncHr", juncHr + "");
                        jsonObj.put("juncMin", juncMin + "");
                        jsonObj.put("juncDat", juncDat + "");
                        jsonObj.put("juncMonth", juncMonth + "");
                        jsonObj.put("juncYear", juncYear + "");
                        out.println(jsonObj);
                        out.flush();

                    }
                } else {
                    out.println("");
                }
                }

                // end for deendayal
                // start for high court
                
                if(current_junction_id == 7){
                if (this.highCourtInfoList != null) {
                    boolean responseFromModemForRefresh = this.planInfoList.isResponseFromModemForRefresh();
                    //boolean responseFromModemForClearnace = this.planInfoList.isResponseFromModemForClearance();

                   // if ((responseFromModemForRefresh == true) || (responseFromModemForClearnace == true)) {
                    if ((responseFromModemForRefresh == true)) {
                        //if(junction_id1 == 2){
                    int functionNo = this.highCourtInfoList.getFunction_no();
                    int junction_id = this.highCourtInfoList.getJunction_id();
                    int program_version_no = this.highCourtInfoList.getProgram_version_no();
                    int fileNo = this.highCourtInfoList.getFileNo();
                    int activity = this.highCourtInfoList.getActivity();
                    int side_no = this.highCourtInfoList.getSide_no();
                    int plan_no = this.highCourtInfoList.getPlan_no();
                    String mode = this.highCourtInfoList.getMode();
                    String junctionName = this.highCourtInfoList.getJunction_name();
                    String sideName = this.highCourtInfoList.getSideName();

                    String onTime = this.highCourtInfoList.getOnTime();
                    String offTime = this.highCourtInfoList.getOffTime();

                    String side1Name = this.highCourtInfoList.getSide1Name();
                    String side2Name = this.highCourtInfoList.getSide2Name();
                    String side3Name = this.highCourtInfoList.getSide3Name();
                    String side4Name = this.highCourtInfoList.getSide4Name();
                    String side5Name = this.highCourtInfoList.getSide5Name();

                    int side1Time = this.highCourtInfoList.getSide1_time();
                    int side2Time = this.highCourtInfoList.getSide2_time();
                    int side3Time = this.highCourtInfoList.getSide3_time();
                    int side4Time = this.highCourtInfoList.getSide4_time();
                    int side1LeftStatus = this.highCourtInfoList.getSide1_left_status();
                    int side1RightStatus = this.highCourtInfoList.getSide1_right_status();
                    int side1UpStatus = this.highCourtInfoList.getSide1_up_status();
                    int side1DownStatus = this.highCourtInfoList.getSide1_down_status();
                    int side2LeftStatus = this.highCourtInfoList.getSide2_left_status();
                    int side2RightStatus = this.highCourtInfoList.getSide2_right_status();
                    int side2UpStatus = this.highCourtInfoList.getSide2_up_status();
                    int side2DownStatus = this.highCourtInfoList.getSide2_down_status();
                    int side3LeftStatus = this.highCourtInfoList.getSide3_left_status();
                    int side3RightStatus = this.highCourtInfoList.getSide3_right_status();
                    int side3UpStatus = this.highCourtInfoList.getSide3_up_status();
                    int side3DownStatus = this.highCourtInfoList.getSide3_down_status();
                    int side4LeftStatus = this.highCourtInfoList.getSide4_left_status();
                    int side4RightStatus = this.highCourtInfoList.getSide4_right_status();
                    int side4UpStatus = this.highCourtInfoList.getSide4_up_status();
                    int side4DownStatus = this.highCourtInfoList.getSide4_down_status();

                    int juncHr = this.highCourtInfoList.getJuncHr();
                    int juncMin = this.highCourtInfoList.getJuncMin();
                    int juncDat = this.highCourtInfoList.getJuncDate();
                    int juncMonth = this.highCourtInfoList.getJuncMonth();
                    int juncYear = this.highCourtInfoList.getJuncYear();

                        String response_data = "junction_id=" + junction_id + "#$" + "program_version_no=" + program_version_no + "#$" + "fileNo=" + fileNo
                                + "#$" + "functionNo=" + functionNo + "#$" + "activity=" + activity
                                + "#$" + "side_no=" + side_no + "#$" + "plan_no=" + plan_no
                                + "#$" + "junctionName=" + junctionName + "#$" + "sideName=" + sideName + "#$" + "onTime=" + onTime + "#$" + "offTime=" + offTime
                                + "#$" + "mode=" + mode + "#$" + "side1Name=" + side1Name + "#$" + "side2Name=" + side2Name + "#$" + "side3Name=" + side3Name
                                + "#$" + "side4Name=" + side4Name + "#$" + "side5Name=" + side5Name + "#$" + "side1Time=" + side1Time + "#$" + "side2Time=" + side2Time
                                + "#$" + "side3Time=" + side3Time + "#$" + "side4Time=" + side4Time + "#$" + "side1LeftStatus=" + side1LeftStatus
                                + "#$" + "side1RightStatus=" + side1RightStatus + "#$" + "side1UpStatus=" + side1UpStatus + "#$" + "side1DownStatus=" + side1DownStatus
                                + "#$" + "side2LeftStatus=" + side2LeftStatus + "#$" + "side2RightStatus=" + side2RightStatus + "#$" + "side2UpStatus=" + side2UpStatus
                                + "#$" + "side2DownStatus=" + side2DownStatus + "#$" + "side3LeftStatus=" + side3LeftStatus + "#$" + "side3RightStatus=" + side3RightStatus
                                + "#$" + "side3UpStatus=" + side3UpStatus + "#$" + "side3DownStatus=" + side3DownStatus + "#$" + "side4LeftStatus=" + side4LeftStatus
                                + "#$" + "side4RightStatus=" + side4RightStatus + "#$" + "side4UpStatus=" + side4UpStatus + "#$" + "side4DownStatus=" + side4DownStatus
                                + "#$" + "juncHr=" + juncHr + "#$" + "juncMin=" + juncMin + "#$" + "juncDat=" + juncDat + "#$" + "juncMonth=" + juncMonth
                                + "#$" + "juncYear=" + juncYear;
                        jsonObj.put("junction_id", junction_id + "");
                        jsonObj.put("program_version_no", program_version_no + "");
                        jsonObj.put("fileNo", fileNo + "");
                        jsonObj.put("functionNo", functionNo + "");
                        jsonObj.put("activity", activity + "");
                        jsonObj.put("side_no", side_no + "");
                        jsonObj.put("plan_no", plan_no + "");
                        jsonObj.put("junctionName", junctionName);
                        jsonObj.put("sideName", sideName);
                        jsonObj.put("onTime", onTime);
                        jsonObj.put("offTime", offTime);
                        jsonObj.put("mode", mode);
                        jsonObj.put("side1Name", side1Name);
                        jsonObj.put("side2Name", side2Name);
                        jsonObj.put("side3Name", side3Name);
                        jsonObj.put("side4Name", side4Name);
                        jsonObj.put("side5Name", side5Name);
                        jsonObj.put("side1Time", side1Time + "");
                        jsonObj.put("side2Time", side2Time + "");
                        jsonObj.put("side3Time", side3Time + "");
                        jsonObj.put("side4Time", side4Time + "");
                        jsonObj.put("side1LeftStatus", side1LeftStatus + "");
                        jsonObj.put("side1RightStatus", side1RightStatus + "");
                        jsonObj.put("side1UpStatus", side1UpStatus + "");
                        jsonObj.put("side1DownStatus", side1DownStatus + "");
                        jsonObj.put("side2LeftStatus", side2LeftStatus + "");
                        jsonObj.put("side2RightStatus", side2RightStatus + "");
                        jsonObj.put("side2UpStatus", side2UpStatus + "");
                        jsonObj.put("side2DownStatus", side2DownStatus + "");
                        jsonObj.put("side3LeftStatus", side3LeftStatus + "");
                        jsonObj.put("side3RightStatus", side3RightStatus + "");
                        jsonObj.put("side3UpStatus", side3UpStatus + "");
                        jsonObj.put("side3DownStatus", side3DownStatus + "");
                        jsonObj.put("side4LeftStatus", side4LeftStatus + "");
                        jsonObj.put("side4RightStatus", side4RightStatus + "");
                        jsonObj.put("side4UpStatus", side4UpStatus + "");
                        jsonObj.put("side4DownStatus", side4DownStatus + "");
                        jsonObj.put("juncHr", juncHr + "");
                        jsonObj.put("juncMin", juncMin + "");
                        jsonObj.put("juncDat", juncDat + "");
                        jsonObj.put("juncMonth", juncMonth + "");
                        jsonObj.put("juncYear", juncYear + "");
                        out.println(jsonObj);
                        out.flush();

                    }
                } else {
                    out.println("");
                }
                }
                // end for high court
                // strat for bloom chowk
                
                if(current_junction_id == 8){
                if (this.bloomChowkInfoList != null) {
                    boolean responseFromModemForRefresh = this.planInfoList.isResponseFromModemForRefresh();
                    //boolean responseFromModemForClearnace = this.planInfoList.isResponseFromModemForClearance();

                   // if ((responseFromModemForRefresh == true) || (responseFromModemForClearnace == true)) {
                    if ((responseFromModemForRefresh == true)) {
                        //if(junction_id1 == 2){
                    int functionNo = this.bloomChowkInfoList.getFunction_no();
                    int junction_id = this.bloomChowkInfoList.getJunction_id();
                    int program_version_no = this.bloomChowkInfoList.getProgram_version_no();
                    int fileNo = this.bloomChowkInfoList.getFileNo();
                    int activity = this.bloomChowkInfoList.getActivity();
                    int side_no = this.bloomChowkInfoList.getSide_no();
                    int plan_no = this.bloomChowkInfoList.getPlan_no();
                    String mode = this.bloomChowkInfoList.getMode();
                    String junctionName = this.bloomChowkInfoList.getJunction_name();
                    String sideName = this.bloomChowkInfoList.getSideName();

                    String onTime = this.bloomChowkInfoList.getOnTime();
                    String offTime = this.bloomChowkInfoList.getOffTime();

                    String side1Name = this.bloomChowkInfoList.getSide1Name();
                    String side2Name = this.bloomChowkInfoList.getSide2Name();
                    String side3Name = this.bloomChowkInfoList.getSide3Name();
                    String side4Name = this.bloomChowkInfoList.getSide4Name();
                    String side5Name = this.bloomChowkInfoList.getSide5Name();

                    int side1Time = this.bloomChowkInfoList.getSide1_time();
                    int side2Time = this.bloomChowkInfoList.getSide2_time();
                    int side3Time = this.bloomChowkInfoList.getSide3_time();
                    int side4Time = this.bloomChowkInfoList.getSide4_time();
                    int side1LeftStatus = this.bloomChowkInfoList.getSide1_left_status();
                    int side1RightStatus = this.bloomChowkInfoList.getSide1_right_status();
                    int side1UpStatus = this.bloomChowkInfoList.getSide1_up_status();
                    int side1DownStatus = this.bloomChowkInfoList.getSide1_down_status();
                    int side2LeftStatus = this.bloomChowkInfoList.getSide2_left_status();
                    int side2RightStatus = this.bloomChowkInfoList.getSide2_right_status();
                    int side2UpStatus = this.bloomChowkInfoList.getSide2_up_status();
                    int side2DownStatus = this.bloomChowkInfoList.getSide2_down_status();
                    int side3LeftStatus = this.bloomChowkInfoList.getSide3_left_status();
                    int side3RightStatus = this.bloomChowkInfoList.getSide3_right_status();
                    int side3UpStatus = this.bloomChowkInfoList.getSide3_up_status();
                    int side3DownStatus = this.bloomChowkInfoList.getSide3_down_status();
                    int side4LeftStatus = this.bloomChowkInfoList.getSide4_left_status();
                    int side4RightStatus = this.bloomChowkInfoList.getSide4_right_status();
                    int side4UpStatus = this.bloomChowkInfoList.getSide4_up_status();
                    int side4DownStatus = this.bloomChowkInfoList.getSide4_down_status();

                    int juncHr = this.bloomChowkInfoList.getJuncHr();
                    int juncMin = this.bloomChowkInfoList.getJuncMin();
                    int juncDat = this.bloomChowkInfoList.getJuncDate();
                    int juncMonth = this.bloomChowkInfoList.getJuncMonth();
                    int juncYear = this.bloomChowkInfoList.getJuncYear();

                        String response_data = "junction_id=" + junction_id + "#$" + "program_version_no=" + program_version_no + "#$" + "fileNo=" + fileNo
                                + "#$" + "functionNo=" + functionNo + "#$" + "activity=" + activity
                                + "#$" + "side_no=" + side_no + "#$" + "plan_no=" + plan_no
                                + "#$" + "junctionName=" + junctionName + "#$" + "sideName=" + sideName + "#$" + "onTime=" + onTime + "#$" + "offTime=" + offTime
                                + "#$" + "mode=" + mode + "#$" + "side1Name=" + side1Name + "#$" + "side2Name=" + side2Name + "#$" + "side3Name=" + side3Name
                                + "#$" + "side4Name=" + side4Name + "#$" + "side5Name=" + side5Name + "#$" + "side1Time=" + side1Time + "#$" + "side2Time=" + side2Time
                                + "#$" + "side3Time=" + side3Time + "#$" + "side4Time=" + side4Time + "#$" + "side1LeftStatus=" + side1LeftStatus
                                + "#$" + "side1RightStatus=" + side1RightStatus + "#$" + "side1UpStatus=" + side1UpStatus + "#$" + "side1DownStatus=" + side1DownStatus
                                + "#$" + "side2LeftStatus=" + side2LeftStatus + "#$" + "side2RightStatus=" + side2RightStatus + "#$" + "side2UpStatus=" + side2UpStatus
                                + "#$" + "side2DownStatus=" + side2DownStatus + "#$" + "side3LeftStatus=" + side3LeftStatus + "#$" + "side3RightStatus=" + side3RightStatus
                                + "#$" + "side3UpStatus=" + side3UpStatus + "#$" + "side3DownStatus=" + side3DownStatus + "#$" + "side4LeftStatus=" + side4LeftStatus
                                + "#$" + "side4RightStatus=" + side4RightStatus + "#$" + "side4UpStatus=" + side4UpStatus + "#$" + "side4DownStatus=" + side4DownStatus
                                + "#$" + "juncHr=" + juncHr + "#$" + "juncMin=" + juncMin + "#$" + "juncDat=" + juncDat + "#$" + "juncMonth=" + juncMonth
                                + "#$" + "juncYear=" + juncYear;
                        jsonObj.put("junction_id", junction_id + "");
                        jsonObj.put("program_version_no", program_version_no + "");
                        jsonObj.put("fileNo", fileNo + "");
                        jsonObj.put("functionNo", functionNo + "");
                        jsonObj.put("activity", activity + "");
                        jsonObj.put("side_no", side_no + "");
                        jsonObj.put("plan_no", plan_no + "");
                        jsonObj.put("junctionName", junctionName);
                        jsonObj.put("sideName", sideName);
                        jsonObj.put("onTime", onTime);
                        jsonObj.put("offTime", offTime);
                        jsonObj.put("mode", mode);
                        jsonObj.put("side1Name", side1Name);
                        jsonObj.put("side2Name", side2Name);
                        jsonObj.put("side3Name", side3Name);
                        jsonObj.put("side4Name", side4Name);
                        jsonObj.put("side5Name", side5Name);
                        jsonObj.put("side1Time", side1Time + "");
                        jsonObj.put("side2Time", side2Time + "");
                        jsonObj.put("side3Time", side3Time + "");
                        jsonObj.put("side4Time", side4Time + "");
                        jsonObj.put("side1LeftStatus", side1LeftStatus + "");
                        jsonObj.put("side1RightStatus", side1RightStatus + "");
                        jsonObj.put("side1UpStatus", side1UpStatus + "");
                        jsonObj.put("side1DownStatus", side1DownStatus + "");
                        jsonObj.put("side2LeftStatus", side2LeftStatus + "");
                        jsonObj.put("side2RightStatus", side2RightStatus + "");
                        jsonObj.put("side2UpStatus", side2UpStatus + "");
                        jsonObj.put("side2DownStatus", side2DownStatus + "");
                        jsonObj.put("side3LeftStatus", side3LeftStatus + "");
                        jsonObj.put("side3RightStatus", side3RightStatus + "");
                        jsonObj.put("side3UpStatus", side3UpStatus + "");
                        jsonObj.put("side3DownStatus", side3DownStatus + "");
                        jsonObj.put("side4LeftStatus", side4LeftStatus + "");
                        jsonObj.put("side4RightStatus", side4RightStatus + "");
                        jsonObj.put("side4UpStatus", side4UpStatus + "");
                        jsonObj.put("side4DownStatus", side4DownStatus + "");
                        jsonObj.put("juncHr", juncHr + "");
                        jsonObj.put("juncMin", juncMin + "");
                        jsonObj.put("juncDat", juncDat + "");
                        jsonObj.put("juncMonth", juncMonth + "");
                        jsonObj.put("juncYear", juncYear + "");
                        out.println(jsonObj);
                        out.flush();

                    }
                } else {
                    out.println("");
                }
                }
                // end for bloom chowk
                // start for madan mahal

                if(current_junction_id == 12){
                if (this.madanMahalInfoList != null) {
                    boolean responseFromModemForRefresh = this.planInfoList.isResponseFromModemForRefresh();
                    //boolean responseFromModemForClearnace = this.planInfoList.isResponseFromModemForClearance();

                   // if ((responseFromModemForRefresh == true) || (responseFromModemForClearnace == true)) {
                    if ((responseFromModemForRefresh == true)) {
                        //if(junction_id1 == 2){
                    int functionNo = this.madanMahalInfoList.getFunction_no();
                    int junction_id = this.madanMahalInfoList.getJunction_id();
                    int program_version_no = this.madanMahalInfoList.getProgram_version_no();
                    int fileNo = this.madanMahalInfoList.getFileNo();
                    int activity = this.madanMahalInfoList.getActivity();
                    int side_no = this.madanMahalInfoList.getSide_no();
                    int plan_no = this.madanMahalInfoList.getPlan_no();
                    String mode = this.madanMahalInfoList.getMode();
                    String junctionName = this.madanMahalInfoList.getJunction_name();
                    String sideName = this.madanMahalInfoList.getSideName();

                    String onTime = this.madanMahalInfoList.getOnTime();
                    String offTime = this.madanMahalInfoList.getOffTime();

                    String side1Name = this.madanMahalInfoList.getSide1Name();
                    String side2Name = this.madanMahalInfoList.getSide2Name();
                    String side3Name = this.madanMahalInfoList.getSide3Name();
                    String side4Name = this.madanMahalInfoList.getSide4Name();
                    String side5Name = this.madanMahalInfoList.getSide5Name();

                    int side1Time = this.madanMahalInfoList.getSide1_time();
                    int side2Time = this.madanMahalInfoList.getSide2_time();
                    int side3Time = this.madanMahalInfoList.getSide3_time();
                    int side4Time = this.madanMahalInfoList.getSide4_time();
                    int side1LeftStatus = this.madanMahalInfoList.getSide1_left_status();
                    int side1RightStatus = this.madanMahalInfoList.getSide1_right_status();
                    int side1UpStatus = this.madanMahalInfoList.getSide1_up_status();
                    int side1DownStatus = this.madanMahalInfoList.getSide1_down_status();
                    int side2LeftStatus = this.madanMahalInfoList.getSide2_left_status();
                    int side2RightStatus = this.madanMahalInfoList.getSide2_right_status();
                    int side2UpStatus = this.madanMahalInfoList.getSide2_up_status();
                    int side2DownStatus = this.madanMahalInfoList.getSide2_down_status();
                    int side3LeftStatus = this.madanMahalInfoList.getSide3_left_status();
                    int side3RightStatus = this.madanMahalInfoList.getSide3_right_status();
                    int side3UpStatus = this.madanMahalInfoList.getSide3_up_status();
                    int side3DownStatus = this.madanMahalInfoList.getSide3_down_status();
                    int side4LeftStatus = this.madanMahalInfoList.getSide4_left_status();
                    int side4RightStatus = this.madanMahalInfoList.getSide4_right_status();
                    int side4UpStatus = this.madanMahalInfoList.getSide4_up_status();
                    int side4DownStatus = this.madanMahalInfoList.getSide4_down_status();

                    int juncHr = this.madanMahalInfoList.getJuncHr();
                    int juncMin = this.madanMahalInfoList.getJuncMin();
                    int juncDat = this.madanMahalInfoList.getJuncDate();
                    int juncMonth = this.madanMahalInfoList.getJuncMonth();
                    int juncYear = this.madanMahalInfoList.getJuncYear();

                        String response_data = "junction_id=" + junction_id + "#$" + "program_version_no=" + program_version_no + "#$" + "fileNo=" + fileNo
                                + "#$" + "functionNo=" + functionNo + "#$" + "activity=" + activity
                                + "#$" + "side_no=" + side_no + "#$" + "plan_no=" + plan_no
                                + "#$" + "junctionName=" + junctionName + "#$" + "sideName=" + sideName + "#$" + "onTime=" + onTime + "#$" + "offTime=" + offTime
                                + "#$" + "mode=" + mode + "#$" + "side1Name=" + side1Name + "#$" + "side2Name=" + side2Name + "#$" + "side3Name=" + side3Name
                                + "#$" + "side4Name=" + side4Name + "#$" + "side5Name=" + side5Name + "#$" + "side1Time=" + side1Time + "#$" + "side2Time=" + side2Time
                                + "#$" + "side3Time=" + side3Time + "#$" + "side4Time=" + side4Time + "#$" + "side1LeftStatus=" + side1LeftStatus
                                + "#$" + "side1RightStatus=" + side1RightStatus + "#$" + "side1UpStatus=" + side1UpStatus + "#$" + "side1DownStatus=" + side1DownStatus
                                + "#$" + "side2LeftStatus=" + side2LeftStatus + "#$" + "side2RightStatus=" + side2RightStatus + "#$" + "side2UpStatus=" + side2UpStatus
                                + "#$" + "side2DownStatus=" + side2DownStatus + "#$" + "side3LeftStatus=" + side3LeftStatus + "#$" + "side3RightStatus=" + side3RightStatus
                                + "#$" + "side3UpStatus=" + side3UpStatus + "#$" + "side3DownStatus=" + side3DownStatus + "#$" + "side4LeftStatus=" + side4LeftStatus
                                + "#$" + "side4RightStatus=" + side4RightStatus + "#$" + "side4UpStatus=" + side4UpStatus + "#$" + "side4DownStatus=" + side4DownStatus
                                + "#$" + "juncHr=" + juncHr + "#$" + "juncMin=" + juncMin + "#$" + "juncDat=" + juncDat + "#$" + "juncMonth=" + juncMonth
                                + "#$" + "juncYear=" + juncYear;
                        jsonObj.put("junction_id", junction_id + "");
                        jsonObj.put("program_version_no", program_version_no + "");
                        jsonObj.put("fileNo", fileNo + "");
                        jsonObj.put("functionNo", functionNo + "");
                        jsonObj.put("activity", activity + "");
                        jsonObj.put("side_no", side_no + "");
                        jsonObj.put("plan_no", plan_no + "");
                        jsonObj.put("junctionName", junctionName);
                        jsonObj.put("sideName", sideName);
                        jsonObj.put("onTime", onTime);
                        jsonObj.put("offTime", offTime);
                        jsonObj.put("mode", mode);
                        jsonObj.put("side1Name", side1Name);
                        jsonObj.put("side2Name", side2Name);
                        jsonObj.put("side3Name", side3Name);
                        jsonObj.put("side4Name", side4Name);
                        jsonObj.put("side5Name", side5Name);
                        jsonObj.put("side1Time", side1Time + "");
                        jsonObj.put("side2Time", side2Time + "");
                        jsonObj.put("side3Time", side3Time + "");
                        jsonObj.put("side4Time", side4Time + "");
                        jsonObj.put("side1LeftStatus", side1LeftStatus + "");
                        jsonObj.put("side1RightStatus", side1RightStatus + "");
                        jsonObj.put("side1UpStatus", side1UpStatus + "");
                        jsonObj.put("side1DownStatus", side1DownStatus + "");
                        jsonObj.put("side2LeftStatus", side2LeftStatus + "");
                        jsonObj.put("side2RightStatus", side2RightStatus + "");
                        jsonObj.put("side2UpStatus", side2UpStatus + "");
                        jsonObj.put("side2DownStatus", side2DownStatus + "");
                        jsonObj.put("side3LeftStatus", side3LeftStatus + "");
                        jsonObj.put("side3RightStatus", side3RightStatus + "");
                        jsonObj.put("side3UpStatus", side3UpStatus + "");
                        jsonObj.put("side3DownStatus", side3DownStatus + "");
                        jsonObj.put("side4LeftStatus", side4LeftStatus + "");
                        jsonObj.put("side4RightStatus", side4RightStatus + "");
                        jsonObj.put("side4UpStatus", side4UpStatus + "");
                        jsonObj.put("side4DownStatus", side4DownStatus + "");
                        jsonObj.put("juncHr", juncHr + "");
                        jsonObj.put("juncMin", juncMin + "");
                        jsonObj.put("juncDat", juncDat + "");
                        jsonObj.put("juncMonth", juncMonth + "");
                        jsonObj.put("juncYear", juncYear + "");
                        out.println(jsonObj);
                        out.flush();

                    }
                } else {
                    out.println("");
                }
                }

                // end for madanmahal
                
                // start for gohalpur
                
                if(current_junction_id == 14){
                if (this.gohalPurInfoList != null) {
                    boolean responseFromModemForRefresh = this.gohalPurInfoList.isResponseFromModemForRefresh();
                    //boolean responseFromModemForClearnace = this.planInfoList.isResponseFromModemForClearance();

                   // if ((responseFromModemForRefresh == true) || (responseFromModemForClearnace == true)) {
                    if ((responseFromModemForRefresh == true)) {
                        //if(junction_id1 == 2){
                    int functionNo = this.gohalPurInfoList.getFunction_no();
                    int junction_id = this.gohalPurInfoList.getJunction_id();
                    int program_version_no = this.gohalPurInfoList.getProgram_version_no();
                    int fileNo = this.gohalPurInfoList.getFileNo();
                    int activity = this.gohalPurInfoList.getActivity();
                    int side_no = this.gohalPurInfoList.getSide_no();
                    int plan_no = this.gohalPurInfoList.getPlan_no();
                    String mode = this.gohalPurInfoList.getMode();
                    String junctionName = this.gohalPurInfoList.getJunction_name();
                    String sideName = this.gohalPurInfoList.getSideName();

                    String onTime = this.gohalPurInfoList.getOnTime();
                    String offTime = this.gohalPurInfoList.getOffTime();

                    String side1Name = this.gohalPurInfoList.getSide1Name();
                    String side2Name = this.gohalPurInfoList.getSide2Name();
                    String side3Name = this.gohalPurInfoList.getSide3Name();
                    String side4Name = this.gohalPurInfoList.getSide4Name();
                    String side5Name = this.gohalPurInfoList.getSide5Name();

                    int side1Time = this.gohalPurInfoList.getSide1_time();
                    int side2Time = this.gohalPurInfoList.getSide2_time();
                    int side3Time = this.gohalPurInfoList.getSide3_time();
                    int side4Time = this.gohalPurInfoList.getSide4_time();
                    int side1LeftStatus = this.gohalPurInfoList.getSide1_left_status();
                    int side1RightStatus = this.gohalPurInfoList.getSide1_right_status();
                    int side1UpStatus = this.gohalPurInfoList.getSide1_up_status();
                    int side1DownStatus = this.gohalPurInfoList.getSide1_down_status();
                    int side2LeftStatus = this.gohalPurInfoList.getSide2_left_status();
                    int side2RightStatus = this.gohalPurInfoList.getSide2_right_status();
                    int side2UpStatus = this.gohalPurInfoList.getSide2_up_status();
                    int side2DownStatus = this.gohalPurInfoList.getSide2_down_status();
                    int side3LeftStatus = this.gohalPurInfoList.getSide3_left_status();
                    int side3RightStatus = this.gohalPurInfoList.getSide3_right_status();
                    int side3UpStatus = this.gohalPurInfoList.getSide3_up_status();
                    int side3DownStatus = this.gohalPurInfoList.getSide3_down_status();
                    int side4LeftStatus = this.gohalPurInfoList.getSide4_left_status();
                    int side4RightStatus = this.gohalPurInfoList.getSide4_right_status();
                    int side4UpStatus = this.gohalPurInfoList.getSide4_up_status();
                    int side4DownStatus = this.gohalPurInfoList.getSide4_down_status();

                    int juncHr = this.gohalPurInfoList.getJuncHr();
                    int juncMin = this.gohalPurInfoList.getJuncMin();
                    int juncDat = this.gohalPurInfoList.getJuncDate();
                    int juncMonth = this.gohalPurInfoList.getJuncMonth();
                    int juncYear = this.gohalPurInfoList.getJuncYear();

                        String response_data = "junction_id=" + junction_id + "#$" + "program_version_no=" + program_version_no + "#$" + "fileNo=" + fileNo
                                + "#$" + "functionNo=" + functionNo + "#$" + "activity=" + activity
                                + "#$" + "side_no=" + side_no + "#$" + "plan_no=" + plan_no
                                + "#$" + "junctionName=" + junctionName + "#$" + "sideName=" + sideName + "#$" + "onTime=" + onTime + "#$" + "offTime=" + offTime
                                + "#$" + "mode=" + mode + "#$" + "side1Name=" + side1Name + "#$" + "side2Name=" + side2Name + "#$" + "side3Name=" + side3Name
                                + "#$" + "side4Name=" + side4Name + "#$" + "side5Name=" + side5Name + "#$" + "side1Time=" + side1Time + "#$" + "side2Time=" + side2Time
                                + "#$" + "side3Time=" + side3Time + "#$" + "side4Time=" + side4Time + "#$" + "side1LeftStatus=" + side1LeftStatus
                                + "#$" + "side1RightStatus=" + side1RightStatus + "#$" + "side1UpStatus=" + side1UpStatus + "#$" + "side1DownStatus=" + side1DownStatus
                                + "#$" + "side2LeftStatus=" + side2LeftStatus + "#$" + "side2RightStatus=" + side2RightStatus + "#$" + "side2UpStatus=" + side2UpStatus
                                + "#$" + "side2DownStatus=" + side2DownStatus + "#$" + "side3LeftStatus=" + side3LeftStatus + "#$" + "side3RightStatus=" + side3RightStatus
                                + "#$" + "side3UpStatus=" + side3UpStatus + "#$" + "side3DownStatus=" + side3DownStatus + "#$" + "side4LeftStatus=" + side4LeftStatus
                                + "#$" + "side4RightStatus=" + side4RightStatus + "#$" + "side4UpStatus=" + side4UpStatus + "#$" + "side4DownStatus=" + side4DownStatus
                                + "#$" + "juncHr=" + juncHr + "#$" + "juncMin=" + juncMin + "#$" + "juncDat=" + juncDat + "#$" + "juncMonth=" + juncMonth
                                + "#$" + "juncYear=" + juncYear;
                        jsonObj.put("junction_id", junction_id + "");
                        jsonObj.put("program_version_no", program_version_no + "");
                        jsonObj.put("fileNo", fileNo + "");
                        jsonObj.put("functionNo", functionNo + "");
                        jsonObj.put("activity", activity + "");
                        jsonObj.put("side_no", side_no + "");
                        jsonObj.put("plan_no", plan_no + "");
                        jsonObj.put("junctionName", junctionName);
                        jsonObj.put("sideName", sideName);
                        jsonObj.put("onTime", onTime);
                        jsonObj.put("offTime", offTime);
                        jsonObj.put("mode", mode);
                        jsonObj.put("side1Name", side1Name);
                        jsonObj.put("side2Name", side2Name);
                        jsonObj.put("side3Name", side3Name);
                        jsonObj.put("side4Name", side4Name);
                        jsonObj.put("side5Name", side5Name);
                        jsonObj.put("side1Time", side1Time + "");
                        jsonObj.put("side2Time", side2Time + "");
                        jsonObj.put("side3Time", side3Time + "");
                        jsonObj.put("side4Time", side4Time + "");
                        jsonObj.put("side1LeftStatus", side1LeftStatus + "");
                        jsonObj.put("side1RightStatus", side1RightStatus + "");
                        jsonObj.put("side1UpStatus", side1UpStatus + "");
                        jsonObj.put("side1DownStatus", side1DownStatus + "");
                        jsonObj.put("side2LeftStatus", side2LeftStatus + "");
                        jsonObj.put("side2RightStatus", side2RightStatus + "");
                        jsonObj.put("side2UpStatus", side2UpStatus + "");
                        jsonObj.put("side2DownStatus", side2DownStatus + "");
                        jsonObj.put("side3LeftStatus", side3LeftStatus + "");
                        jsonObj.put("side3RightStatus", side3RightStatus + "");
                        jsonObj.put("side3UpStatus", side3UpStatus + "");
                        jsonObj.put("side3DownStatus", side3DownStatus + "");
                        jsonObj.put("side4LeftStatus", side4LeftStatus + "");
                        jsonObj.put("side4RightStatus", side4RightStatus + "");
                        jsonObj.put("side4UpStatus", side4UpStatus + "");
                        jsonObj.put("side4DownStatus", side4DownStatus + "");
                        jsonObj.put("juncHr", juncHr + "");
                        jsonObj.put("juncMin", juncMin + "");
                        jsonObj.put("juncDat", juncDat + "");
                        jsonObj.put("juncMonth", juncMonth + "");
                        jsonObj.put("juncYear", juncYear + "");
                        out.println(jsonObj);
                        out.flush();

                    }
                } else {
                    out.println("");
                }
                }
                
                // end for gohalpur


            }
            //  for testing

          if (task.equals("getLatestMapSignal")) {
                this.planInfoList = ((PlanInfo) ctx.getAttribute("planInfolist"));
                if (this.planInfoList != null) {
                    int functionNo = this.planInfoList.getFunction_no();
                    int junction_id = this.planInfoList.getJunction_id();
                    int program_version_no = this.planInfoList.getProgram_version_no();
                    int fileNo = this.planInfoList.getFileNo();
                    int activity = this.planInfoList.getActivity();
                    int side_no = this.planInfoList.getSide_no();
                    int plan_no = this.planInfoList.getPlan_no();
                    String mode = this.planInfoList.getMode();
                    String junctionName = this.planInfoList.getJunction_name();
                    String sideName = this.planInfoList.getSideName();

                    String onTime = this.planInfoList.getOnTime();
                    String offTime = this.planInfoList.getOffTime();

                    String side1Name = this.planInfoList.getSide1Name();
                    String side2Name = this.planInfoList.getSide2Name();
                    String side3Name = this.planInfoList.getSide3Name();
                    String side4Name = this.planInfoList.getSide4Name();
                    String side5Name = this.planInfoList.getSide5Name();

                    int side1Time = this.planInfoList.getSide1_time();
                    int side2Time = this.planInfoList.getSide2_time();
                    int side3Time = this.planInfoList.getSide3_time();
                    int side4Time = this.planInfoList.getSide4_time();
                    int side1LeftStatus = this.planInfoList.getSide1_left_status();
                    int side1RightStatus = this.planInfoList.getSide1_right_status();
                    int side1UpStatus = this.planInfoList.getSide1_up_status();
                    int side1DownStatus = this.planInfoList.getSide1_down_status();
                    int side2LeftStatus = this.planInfoList.getSide2_left_status();
                    int side2RightStatus = this.planInfoList.getSide2_right_status();
                    int side2UpStatus = this.planInfoList.getSide2_up_status();
                    int side2DownStatus = this.planInfoList.getSide2_down_status();
                    int side3LeftStatus = this.planInfoList.getSide3_left_status();
                    int side3RightStatus = this.planInfoList.getSide3_right_status();
                    int side3UpStatus = this.planInfoList.getSide3_up_status();
                    int side3DownStatus = this.planInfoList.getSide3_down_status();
                    int side4LeftStatus = this.planInfoList.getSide4_left_status();
                    int side4RightStatus = this.planInfoList.getSide4_right_status();
                    int side4UpStatus = this.planInfoList.getSide4_up_status();
                    int side4DownStatus = this.planInfoList.getSide4_down_status();

                    int juncHr = this.planInfoList.getJuncHr();
                    int juncMin = this.planInfoList.getJuncMin();
                    int juncDat = this.planInfoList.getJuncDate();
                    int juncMonth = this.planInfoList.getJuncMonth();
                    int juncYear = this.planInfoList.getJuncYear();

                  //  String map_image= clientResponderModel.getMapImage(junction_id);
                    request.setAttribute("juncHr", juncHr);
                  //  request.setAttribute("map_image", map_image);
                    request.setAttribute("juncMin", juncMin);
                    request.setAttribute("juncDat", juncDat);
                    request.setAttribute("juncMonth", juncMonth);
                    request.setAttribute("juncYear", juncYear);

                    request.setAttribute("functionNo", Integer.valueOf(functionNo));
                    request.setAttribute("junctionId", Integer.valueOf(junction_id));
                    request.setAttribute("program_version_no", Integer.valueOf(program_version_no));
                    request.setAttribute("fileNo", Integer.valueOf(fileNo));
                    request.setAttribute("junctionName", junctionName);
                    request.setAttribute("activity", activity);
                    request.setAttribute("sideNo", Integer.valueOf(side_no));
                    request.setAttribute("sideName", sideName);
                    request.setAttribute("side1Name", side1Name);
                    request.setAttribute("side2Name", side2Name);
                    request.setAttribute("side3Name", side3Name);
                    request.setAttribute("side4Name", side4Name);
                    request.setAttribute("side5Name", side5Name);

                    request.setAttribute("onTime", onTime);
                    request.setAttribute("offTime", offTime);
                    request.setAttribute("plan_no", plan_no);
                    request.setAttribute("mode", mode);

                    request.setAttribute("side1Time", Integer.valueOf(side1Time));
                    request.setAttribute("side2Time", Integer.valueOf(side2Time));
                    request.setAttribute("side3Time", Integer.valueOf(side3Time));
                    request.setAttribute("side4Time", Integer.valueOf(side4Time));
                    request.setAttribute("side1LeftStatus", Integer.valueOf(side1LeftStatus));
                    request.setAttribute("side1RightStatus", Integer.valueOf(side1RightStatus));
                    request.setAttribute("side1UpStatus", Integer.valueOf(side1UpStatus));
                    request.setAttribute("side1DownStatus", Integer.valueOf(side1DownStatus));
                    request.setAttribute("side2LeftStatus", Integer.valueOf(side2LeftStatus));
                    request.setAttribute("side2RightStatus", Integer.valueOf(side2RightStatus));
                    request.setAttribute("side2UpStatus", Integer.valueOf(side2UpStatus));
                    request.setAttribute("side2DownStatus", Integer.valueOf(side2DownStatus));
                    request.setAttribute("side3LeftStatus", Integer.valueOf(side3LeftStatus));
                    request.setAttribute("side3RightStatus", Integer.valueOf(side3RightStatus));
                    request.setAttribute("side3UpStatus", Integer.valueOf(side3UpStatus));
                    request.setAttribute("side3DownStatus", Integer.valueOf(side3DownStatus));
                    request.setAttribute("side4LeftStatus", Integer.valueOf(side4LeftStatus));
                    request.setAttribute("side4RightStatus", Integer.valueOf(side4RightStatus));
                    request.setAttribute("side4UpStatus", Integer.valueOf(side4UpStatus));
                    request.setAttribute("side4DownStatus", Integer.valueOf(side4DownStatus));
                    request.setAttribute("side4DownStatus", Integer.valueOf(side4DownStatus));

                    request.getRequestDispatcher("/view/general/showMapOnTrafficSignal.jsp").forward(request, response);
                    return;
                } else {
                    String jSON_format = "Oops... No junction is active right now.";

                    request.setAttribute("message", jSON_format);
                    request.getRequestDispatcher("errorView").forward(request, response);
                    return;
                }

            }

          // this is for google map
          if (task.equals("showAllStatus")) {
            List<History> List = clientResponderModel.showDataBean();
            int cordinateLength = List.size();
            request.setAttribute("cordinateLength", cordinateLength);
            request.setAttribute("CoordinatesList", List);
            //request.getRequestDispatcher("/view/general/allStatusMapWindow.jsp").forward(request, response);
            //request.getRequestDispatcher("/view/general/allJunctionMapBySupermap.jsp").forward(request, response);
            request.getRequestDispatcher("/view/general/showJunctionLightsOnSupermap.jsp").forward(request, response);
            return;
        }
          if (task.equals("showSingleJunctionStatus")) {
            String latlon = request.getParameter("latlon");
            String latitude = request.getParameter("latitude");                
            String longitude = request.getParameter("longitude");
            //int junction_id = clientResponderModel.getJunctionIdByLatLon(latitude,longitude);
            String List1 = clientResponderModel.showDataBeanForJunction(latitude,longitude);
                 //History bean = new History();
                 String junction_List[] = List1.split(",");
             int junction_id = Integer.parseInt(junction_List[3]);     
            //String lat1 = request.getParameter("lat");
            List<History> List = clientResponderModel.showDataBeanForSingleJunction(junction_id);
            int cordinateLength = List.size();
            request.setAttribute("cordinateLength", cordinateLength);
            request.setAttribute("latlon", latlon);
            request.setAttribute("junction_Id", junction_id);
            //request.setAttribute("lat1", lat1);
            request.setAttribute("CoordinatesList", List);
            //request.getRequestDispatcher("/view/general/allStatusMapWindow.jsp").forward(request, response);
            //request.getRequestDispatcher("/view/general/allJunctionMapBySupermap.jsp").forward(request, response);
            request.getRequestDispatcher("/view/general/showSingleJunctionBySupermap.jsp").forward(request, response);
            return;
        }
          if (task.equals("onlyTesting")) {
            PrintWriter out = response.getWriter();
                JSONObject jsonObj = new JSONObject();
                 int current_junction_id = Integer.parseInt(request.getParameter("current_junction_id"));                
                        jsonObj.put("side1LeftStatus", "0");
                        jsonObj.put("side1RightStatus", "0");
                        jsonObj.put("side1UpStatus", "1");
                        jsonObj.put("side1DownStatus", "1");
                        jsonObj.put("side2LeftStatus", "1");
                        jsonObj.put("side2RightStatus", "0");
                        jsonObj.put("side2UpStatus", "0");
                        jsonObj.put("side2DownStatus", "0");
                        jsonObj.put("side3LeftStatus", "1");
                        jsonObj.put("side3RightStatus", "0");
                        jsonObj.put("side3UpStatus", "0");
                        jsonObj.put("side3DownStatus", "0");
                        jsonObj.put("side4LeftStatus", "1");
                        jsonObj.put("side4RightStatus", "0");
                        jsonObj.put("side4UpStatus", "0");
                        jsonObj.put("side4DownStatus", "0");                     
                        jsonObj.put("side1Time", "30");                     
                        jsonObj.put("side2Time", "24");                     
                        jsonObj.put("side3Time", "45");                     
                        jsonObj.put("side4Time", "65");                     
                        out.println(jsonObj);
                        out.flush();
        }
          
          if (task.equals("getJunctionIdByLatLong")) {
            PrintWriter out = response.getWriter();
                JSONObject jsonObj = new JSONObject();
                 String latitude = request.getParameter("latitude");                
                 String longitude = request.getParameter("longitude");
                 String List = clientResponderModel.showDataBeanForJunction(latitude,longitude);
                 //History bean = new History();
                 String junction_List[] = List.split(",");
                 String junction_name = junction_List[0];
                 int no_of_sides = Integer.parseInt(junction_List[1]);
                 int no_of_plans = Integer.parseInt(junction_List[2]);
                 int junction_id = Integer.parseInt(junction_List[3]);
                        jsonObj.put("junction_id", junction_id);
                        jsonObj.put("junction_name", junction_name);
                        jsonObj.put("no_of_sides", no_of_sides);
                        jsonObj.put("no_of_plans", no_of_plans);                                                                  
                        out.println(jsonObj);
                        out.flush();
        }

            // end of testing

            else {
                this.planInfoList = ((PlanInfo) ctx.getAttribute("planInfolist"));
                this.teenPattiInfoList = ((TeenPatti_Info) ctx.getAttribute("teenPattiInfoList"));
                this.ranitalInfoList = ((RanitalInfo) ctx.getAttribute("ranitalInfoList"));
                this.damohNakaInfoList = ((DamohNakaInfo) ctx.getAttribute("damohNakaInfoList"));
                this.yatayatThanaInfoList = ((YatayatThanaInfo) ctx.getAttribute("yatayatThanaInfoList"));
                this.katangaInfoList = ((KatangaInfo) ctx.getAttribute("katangaInfoList"));
                this.baldeobagInfoList = ((BaldeobagInfo) ctx.getAttribute("baldeobagInfoList"));
                this.deendayalChowkInfoList = ((DeendayalChowkInfo) ctx.getAttribute("deendayalChowkInfoList"));
                this.highCourtInfoList = ((HighCourtInfo) ctx.getAttribute("highCourtInfoList"));
                this.bloomChowkInfoList = ((BloomChowkInfo) ctx.getAttribute("bloomChowkInfoList"));
                this.madanMahalInfoList = ((MadanMahalInfo) ctx.getAttribute("madanMahalInfoList"));
                this.gohalPurInfoList = ((GohalPurInfo) ctx.getAttribute("gohalPurInfoList"));
                this.bandariyaTirahaInfoList = ((BandariyaTirahaInfo) ctx.getAttribute("bandariyaTirahaInfoList"));
                this.labourChowkInfoList = ((LabourChowkInfo) ctx.getAttribute("labourChowkInfoList"));
                this.taiyabaliChowkInfoList = ((TaiyabaliChowkInfo) ctx.getAttribute("taiyabaliChowkInfoList"));
                this.collectorateInfoList = ((CollectorateInfo) ctx.getAttribute("collectorateInfoList"));
                //int junction_id1 = this.planInfoList.getJunction_id();
                if(a == 11){
                //BeanUtils.copyProperties(planInfoList, ranitalInfoList);
                if (this.planInfoList != null) {
                    int functionNo = this.ranitalInfoList.getFunction_no();
                    int junction_id = this.ranitalInfoList.getJunction_id();
                    int program_version_no = this.ranitalInfoList.getProgram_version_no();
                    int fileNo = this.ranitalInfoList.getFileNo();
                    int activity = this.ranitalInfoList.getActivity();
                    int side_no = this.ranitalInfoList.getSide_no();
                    int plan_no = this.ranitalInfoList.getPlan_no();
                    String mode = this.ranitalInfoList.getMode();
                    String junctionName = this.ranitalInfoList.getJunction_name();
                    String sideName = this.ranitalInfoList.getSideName();

                    String onTime = this.ranitalInfoList.getOnTime();
                    String offTime = this.ranitalInfoList.getOffTime();

                    String side1Name = this.ranitalInfoList.getSide1Name();
                    String side2Name = this.ranitalInfoList.getSide2Name();
                    String side3Name = this.ranitalInfoList.getSide3Name();
                    String side4Name = this.ranitalInfoList.getSide4Name();
                    String side5Name = this.ranitalInfoList.getSide5Name();

                    int side1Time = this.ranitalInfoList.getSide1_time();
                    int side2Time = this.ranitalInfoList.getSide2_time();
                    int side3Time = this.ranitalInfoList.getSide3_time();
                    int side4Time = this.ranitalInfoList.getSide4_time();
                    int side1LeftStatus = this.ranitalInfoList.getSide1_left_status();
                    int side1RightStatus = this.ranitalInfoList.getSide1_right_status();
                    int side1UpStatus = this.ranitalInfoList.getSide1_up_status();
                    int side1DownStatus = this.ranitalInfoList.getSide1_down_status();
                    int side2LeftStatus = this.ranitalInfoList.getSide2_left_status();
                    int side2RightStatus = this.ranitalInfoList.getSide2_right_status();
                    int side2UpStatus = this.ranitalInfoList.getSide2_up_status();
                    int side2DownStatus = this.ranitalInfoList.getSide2_down_status();
                    int side3LeftStatus = this.ranitalInfoList.getSide3_left_status();
                    int side3RightStatus = this.ranitalInfoList.getSide3_right_status();
                    int side3UpStatus = this.ranitalInfoList.getSide3_up_status();
                    int side3DownStatus = this.ranitalInfoList.getSide3_down_status();
                    int side4LeftStatus = this.ranitalInfoList.getSide4_left_status();
                    int side4RightStatus = this.ranitalInfoList.getSide4_right_status();
                    int side4UpStatus = this.ranitalInfoList.getSide4_up_status();
                    int side4DownStatus = this.ranitalInfoList.getSide4_down_status();

                    int juncHr = this.ranitalInfoList.getJuncHr();
                    int juncMin = this.ranitalInfoList.getJuncMin();
                    int juncDat = this.ranitalInfoList.getJuncDate();
                    int juncMonth = this.ranitalInfoList.getJuncMonth();
                    int juncYear = this.ranitalInfoList.getJuncYear();

                    request.setAttribute("juncHr", juncHr);
                    request.setAttribute("juncMin", juncMin);
                    request.setAttribute("juncDat", juncDat);
                    request.setAttribute("juncMonth", juncMonth);
                    request.setAttribute("juncYear", juncYear);

                    request.setAttribute("functionNo", Integer.valueOf(functionNo));
                    request.setAttribute("junctionId", Integer.valueOf(junction_id));
                    request.setAttribute("program_version_no", Integer.valueOf(program_version_no));
                    request.setAttribute("fileNo", Integer.valueOf(fileNo));
                    request.setAttribute("junctionName", junctionName);
                    request.setAttribute("activity", activity);
                    request.setAttribute("sideNo", Integer.valueOf(side_no));
                    request.setAttribute("sideName", sideName);
                    request.setAttribute("side1Name", side1Name);
                    request.setAttribute("side2Name", side2Name);
                    request.setAttribute("side3Name", side3Name);
                    request.setAttribute("side4Name", side4Name);
                    request.setAttribute("side5Name", side5Name);

                    request.setAttribute("onTime", onTime);
                    request.setAttribute("offTime", offTime);
                    request.setAttribute("plan_no", plan_no);
                    request.setAttribute("mode", mode);

                    request.setAttribute("side1Time", Integer.valueOf(side1Time));
                    request.setAttribute("side2Time", Integer.valueOf(side2Time));
                    request.setAttribute("side3Time", Integer.valueOf(side3Time));
                    request.setAttribute("side4Time", Integer.valueOf(side4Time));
                    request.setAttribute("side1LeftStatus", Integer.valueOf(side1LeftStatus));
                    request.setAttribute("side1RightStatus", Integer.valueOf(side1RightStatus));
                    request.setAttribute("side1UpStatus", Integer.valueOf(side1UpStatus));
                    request.setAttribute("side1DownStatus", Integer.valueOf(side1DownStatus));
                    request.setAttribute("side2LeftStatus", Integer.valueOf(side2LeftStatus));
                    request.setAttribute("side2RightStatus", Integer.valueOf(side2RightStatus));
                    request.setAttribute("side2UpStatus", Integer.valueOf(side2UpStatus));
                    request.setAttribute("side2DownStatus", Integer.valueOf(side2DownStatus));
                    request.setAttribute("side3LeftStatus", Integer.valueOf(side3LeftStatus));
                    request.setAttribute("side3RightStatus", Integer.valueOf(side3RightStatus));
                    request.setAttribute("side3UpStatus", Integer.valueOf(side3UpStatus));
                    request.setAttribute("side3DownStatus", Integer.valueOf(side3DownStatus));
                    request.setAttribute("side4LeftStatus", Integer.valueOf(side4LeftStatus));
                    request.setAttribute("side4RightStatus", Integer.valueOf(side4RightStatus));
                    request.setAttribute("side4UpStatus", Integer.valueOf(side4UpStatus));
                    request.setAttribute("side4DownStatus", Integer.valueOf(side4DownStatus));
                    request.setAttribute("side4DownStatus", Integer.valueOf(side4DownStatus));
                    if(map_junction_id!=""){
                     request.getRequestDispatcher("/view/general/showMapOnTrafficSignal.jsp").forward(request, response);
                    }else{
                    request.getRequestDispatcher("ts_statusShower_view").forward(request, response);
                    }
                    return;
                } else {
                    String jSON_format = "Oops... No junction is active right now.";

                    request.setAttribute("message", jSON_format);
                    request.getRequestDispatcher("errorView").forward(request, response);
                    return;
                }
                }
                
                if(a == 17){
                //BeanUtils.copyProperties(planInfoList, ranitalInfoList);
                if (this.planInfoList != null) {
                    int functionNo = this.taiyabaliChowkInfoList.getFunction_no();
                    int junction_id = this.taiyabaliChowkInfoList.getJunction_id();
                    int program_version_no = this.taiyabaliChowkInfoList.getProgram_version_no();
                    int fileNo = this.taiyabaliChowkInfoList.getFileNo();
                    int activity = this.taiyabaliChowkInfoList.getActivity();
                    int side_no = this.taiyabaliChowkInfoList.getSide_no();
                    int plan_no = this.taiyabaliChowkInfoList.getPlan_no();
                    String mode = this.taiyabaliChowkInfoList.getMode();
                    String junctionName = this.taiyabaliChowkInfoList.getJunction_name();
                    String sideName = this.taiyabaliChowkInfoList.getSideName();

                    String onTime = this.taiyabaliChowkInfoList.getOnTime();
                    String offTime = this.taiyabaliChowkInfoList.getOffTime();

                    String side1Name = this.taiyabaliChowkInfoList.getSide1Name();
                    String side2Name = this.taiyabaliChowkInfoList.getSide2Name();
                    String side3Name = this.taiyabaliChowkInfoList.getSide3Name();
                    String side4Name = this.taiyabaliChowkInfoList.getSide4Name();
                    String side5Name = this.taiyabaliChowkInfoList.getSide5Name();

                    int side1Time = this.taiyabaliChowkInfoList.getSide1_time();
                    int side2Time = this.taiyabaliChowkInfoList.getSide2_time();
                    int side3Time = this.taiyabaliChowkInfoList.getSide3_time();
                    int side4Time = this.taiyabaliChowkInfoList.getSide4_time();
                    int side1LeftStatus = this.taiyabaliChowkInfoList.getSide1_left_status();
                    int side1RightStatus = this.taiyabaliChowkInfoList.getSide1_right_status();
                    int side1UpStatus = this.taiyabaliChowkInfoList.getSide1_up_status();
                    int side1DownStatus = this.taiyabaliChowkInfoList.getSide1_down_status();
                    int side2LeftStatus = this.taiyabaliChowkInfoList.getSide2_left_status();
                    int side2RightStatus = this.taiyabaliChowkInfoList.getSide2_right_status();
                    int side2UpStatus = this.taiyabaliChowkInfoList.getSide2_up_status();
                    int side2DownStatus = this.taiyabaliChowkInfoList.getSide2_down_status();
                    int side3LeftStatus = this.taiyabaliChowkInfoList.getSide3_left_status();
                    int side3RightStatus = this.taiyabaliChowkInfoList.getSide3_right_status();
                    int side3UpStatus = this.taiyabaliChowkInfoList.getSide3_up_status();
                    int side3DownStatus = this.taiyabaliChowkInfoList.getSide3_down_status();
                    int side4LeftStatus = this.taiyabaliChowkInfoList.getSide4_left_status();
                    int side4RightStatus = this.taiyabaliChowkInfoList.getSide4_right_status();
                    int side4UpStatus = this.taiyabaliChowkInfoList.getSide4_up_status();
                    int side4DownStatus = this.taiyabaliChowkInfoList.getSide4_down_status();

                    int juncHr = this.taiyabaliChowkInfoList.getJuncHr();
                    int juncMin = this.taiyabaliChowkInfoList.getJuncMin();
                    int juncDat = this.taiyabaliChowkInfoList.getJuncDate();
                    int juncMonth = this.taiyabaliChowkInfoList.getJuncMonth();
                    int juncYear = this.taiyabaliChowkInfoList.getJuncYear();

                    request.setAttribute("juncHr", juncHr);
                    request.setAttribute("juncMin", juncMin);
                    request.setAttribute("juncDat", juncDat);
                    request.setAttribute("juncMonth", juncMonth);
                    request.setAttribute("juncYear", juncYear);

                    request.setAttribute("functionNo", functionNo);
                    request.setAttribute("junctionId", Integer.valueOf(junction_id));
                    request.setAttribute("program_version_no", program_version_no);
                    request.setAttribute("fileNo", Integer.valueOf(fileNo));
                    request.setAttribute("junctionName", junctionName);
                    request.setAttribute("activity", activity);
                    request.setAttribute("sideNo", Integer.valueOf(side_no));
                    request.setAttribute("sideName", sideName);
                    request.setAttribute("side1Name", side1Name);
                    request.setAttribute("side2Name", side2Name);
                    request.setAttribute("side3Name", side3Name);
                    request.setAttribute("side4Name", side4Name);
                    request.setAttribute("side5Name", side5Name);

                    request.setAttribute("onTime", onTime);
                    request.setAttribute("offTime", offTime);
                    request.setAttribute("plan_no", plan_no);
                    request.setAttribute("mode", mode);

                    request.setAttribute("side1Time", Integer.valueOf(side1Time));
                    request.setAttribute("side2Time", Integer.valueOf(side2Time));
                    request.setAttribute("side3Time", Integer.valueOf(side3Time));
                    request.setAttribute("side4Time", Integer.valueOf(side4Time));
                    request.setAttribute("side1LeftStatus", Integer.valueOf(side1LeftStatus));
                    request.setAttribute("side1RightStatus", Integer.valueOf(side1RightStatus));
                    request.setAttribute("side1UpStatus", Integer.valueOf(side1UpStatus));
                    request.setAttribute("side1DownStatus", Integer.valueOf(side1DownStatus));
                    request.setAttribute("side2LeftStatus", Integer.valueOf(side2LeftStatus));
                    request.setAttribute("side2RightStatus", Integer.valueOf(side2RightStatus));
                    request.setAttribute("side2UpStatus", Integer.valueOf(side2UpStatus));
                    request.setAttribute("side2DownStatus", Integer.valueOf(side2DownStatus));
                    request.setAttribute("side3LeftStatus", Integer.valueOf(side3LeftStatus));
                    request.setAttribute("side3RightStatus", Integer.valueOf(side3RightStatus));
                    request.setAttribute("side3UpStatus", Integer.valueOf(side3UpStatus));
                    request.setAttribute("side3DownStatus", Integer.valueOf(side3DownStatus));
                    request.setAttribute("side4LeftStatus", Integer.valueOf(side4LeftStatus));
                    request.setAttribute("side4RightStatus", Integer.valueOf(side4RightStatus));
                    request.setAttribute("side4UpStatus", Integer.valueOf(side4UpStatus));
                    request.setAttribute("side4DownStatus", Integer.valueOf(side4DownStatus));
                    request.setAttribute("side4DownStatus", Integer.valueOf(side4DownStatus));
                    if(map_junction_id!=""){
                     request.getRequestDispatcher("/view/general/showMapOnTrafficSignal.jsp").forward(request, response);
                    }else{
                    request.getRequestDispatcher("ts_statusShower_view").forward(request, response);
                    }
                    return;
                } else {
                    String jSON_format = "Oops... No junction is active right now.";

                    request.setAttribute("message", jSON_format);
                    request.getRequestDispatcher("errorView").forward(request, response);
                    return;
                }
                }
                
                if(a == 18){
                //BeanUtils.copyProperties(planInfoList, ranitalInfoList);
                if (this.planInfoList != null) {
                    int functionNo = this.collectorateInfoList.getFunction_no();
                    int junction_id = this.collectorateInfoList.getJunction_id();
                    int program_version_no = this.collectorateInfoList.getProgram_version_no();
                    int fileNo = this.collectorateInfoList.getFileNo();
                    int activity = this.collectorateInfoList.getActivity();
                    int side_no = this.collectorateInfoList.getSide_no();
                    int plan_no = this.collectorateInfoList.getPlan_no();
                    String mode = this.collectorateInfoList.getMode();
                    String junctionName = this.collectorateInfoList.getJunction_name();
                    String sideName = this.collectorateInfoList.getSideName();

                    String onTime = this.collectorateInfoList.getOnTime();
                    String offTime = this.collectorateInfoList.getOffTime();

                    String side1Name = this.collectorateInfoList.getSide1Name();
                    String side2Name = this.collectorateInfoList.getSide2Name();
                    String side3Name = this.collectorateInfoList.getSide3Name();
                    String side4Name = this.collectorateInfoList.getSide4Name();
                    String side5Name = this.collectorateInfoList.getSide5Name();

                    int side1Time = this.collectorateInfoList.getSide1_time();
                    int side2Time = this.collectorateInfoList.getSide2_time();
                    int side3Time = this.collectorateInfoList.getSide3_time();
                    int side4Time = this.collectorateInfoList.getSide4_time();
                    int side1LeftStatus = this.collectorateInfoList.getSide1_left_status();
                    int side1RightStatus = this.collectorateInfoList.getSide1_right_status();
                    int side1UpStatus = this.collectorateInfoList.getSide1_up_status();
                    int side1DownStatus = this.collectorateInfoList.getSide1_down_status();
                    int side2LeftStatus = this.collectorateInfoList.getSide2_left_status();
                    int side2RightStatus = this.collectorateInfoList.getSide2_right_status();
                    int side2UpStatus = this.collectorateInfoList.getSide2_up_status();
                    int side2DownStatus = this.collectorateInfoList.getSide2_down_status();
                    int side3LeftStatus = this.collectorateInfoList.getSide3_left_status();
                    int side3RightStatus = this.collectorateInfoList.getSide3_right_status();
                    int side3UpStatus = this.collectorateInfoList.getSide3_up_status();
                    int side3DownStatus = this.collectorateInfoList.getSide3_down_status();
                    int side4LeftStatus = this.collectorateInfoList.getSide4_left_status();
                    int side4RightStatus = this.collectorateInfoList.getSide4_right_status();
                    int side4UpStatus = this.collectorateInfoList.getSide4_up_status();
                    int side4DownStatus = this.collectorateInfoList.getSide4_down_status();

                    int juncHr = this.collectorateInfoList.getJuncHr();
                    int juncMin = this.collectorateInfoList.getJuncMin();
                    int juncDat = this.collectorateInfoList.getJuncDate();
                    int juncMonth = this.collectorateInfoList.getJuncMonth();
                    int juncYear = this.collectorateInfoList.getJuncYear();

                    request.setAttribute("juncHr", juncHr);
                    request.setAttribute("juncMin", juncMin);
                    request.setAttribute("juncDat", juncDat);
                    request.setAttribute("juncMonth", juncMonth);
                    request.setAttribute("juncYear", juncYear);

                    request.setAttribute("functionNo", functionNo);
                    request.setAttribute("junctionId", Integer.valueOf(junction_id));
                    request.setAttribute("program_version_no", program_version_no);
                    request.setAttribute("fileNo", Integer.valueOf(fileNo));
                    request.setAttribute("junctionName", junctionName);
                    request.setAttribute("activity", activity);
                    request.setAttribute("sideNo", Integer.valueOf(side_no));
                    request.setAttribute("sideName", sideName);
                    request.setAttribute("side1Name", side1Name);
                    request.setAttribute("side2Name", side2Name);
                    request.setAttribute("side3Name", side3Name);
                    request.setAttribute("side4Name", side4Name);
                    request.setAttribute("side5Name", side5Name);

                    request.setAttribute("onTime", onTime);
                    request.setAttribute("offTime", offTime);
                    request.setAttribute("plan_no", plan_no);
                    request.setAttribute("mode", mode);

                    request.setAttribute("side1Time", Integer.valueOf(side1Time));
                    request.setAttribute("side2Time", Integer.valueOf(side2Time));
                    request.setAttribute("side3Time", Integer.valueOf(side3Time));
                    request.setAttribute("side4Time", Integer.valueOf(side4Time));
                    request.setAttribute("side1LeftStatus", Integer.valueOf(side1LeftStatus));
                    request.setAttribute("side1RightStatus", Integer.valueOf(side1RightStatus));
                    request.setAttribute("side1UpStatus", Integer.valueOf(side1UpStatus));
                    request.setAttribute("side1DownStatus", Integer.valueOf(side1DownStatus));
                    request.setAttribute("side2LeftStatus", Integer.valueOf(side2LeftStatus));
                    request.setAttribute("side2RightStatus", Integer.valueOf(side2RightStatus));
                    request.setAttribute("side2UpStatus", Integer.valueOf(side2UpStatus));
                    request.setAttribute("side2DownStatus", Integer.valueOf(side2DownStatus));
                    request.setAttribute("side3LeftStatus", Integer.valueOf(side3LeftStatus));
                    request.setAttribute("side3RightStatus", Integer.valueOf(side3RightStatus));
                    request.setAttribute("side3UpStatus", Integer.valueOf(side3UpStatus));
                    request.setAttribute("side3DownStatus", Integer.valueOf(side3DownStatus));
                    request.setAttribute("side4LeftStatus", Integer.valueOf(side4LeftStatus));
                    request.setAttribute("side4RightStatus", Integer.valueOf(side4RightStatus));
                    request.setAttribute("side4UpStatus", Integer.valueOf(side4UpStatus));
                    request.setAttribute("side4DownStatus", Integer.valueOf(side4DownStatus));
                    request.setAttribute("side4DownStatus", Integer.valueOf(side4DownStatus));
                    if(map_junction_id!=""){
                     request.getRequestDispatcher("/view/general/showMapOnTrafficSignal.jsp").forward(request, response);
                    }else{
                    request.getRequestDispatcher("ts_statusShower_view").forward(request, response);
                    }
                    return;
                } else {
                    String jSON_format = "Oops... No junction is active right now.";

                    request.setAttribute("message", jSON_format);
                    request.getRequestDispatcher("errorView").forward(request, response);
                    return;
                }
                }
                if(a == 15){
                //BeanUtils.copyProperties(planInfoList, ranitalInfoList);
                if (this.bandariyaTirahaInfoList != null) {
                    int functionNo = this.bandariyaTirahaInfoList.getFunction_no();
                    int junction_id = this.bandariyaTirahaInfoList.getJunction_id();
                    int program_version_no = this.bandariyaTirahaInfoList.getProgram_version_no();
                    int fileNo = this.bandariyaTirahaInfoList.getFileNo();
                    int activity = this.bandariyaTirahaInfoList.getActivity();
                    int side_no = this.bandariyaTirahaInfoList.getSide_no();
                    int plan_no = this.bandariyaTirahaInfoList.getPlan_no();
                    String mode = this.bandariyaTirahaInfoList.getMode();
                    String junctionName = this.bandariyaTirahaInfoList.getJunction_name();
                    String sideName = this.bandariyaTirahaInfoList.getSideName();

                    String onTime = this.bandariyaTirahaInfoList.getOnTime();
                    String offTime = this.bandariyaTirahaInfoList.getOffTime();

                    String side1Name = this.bandariyaTirahaInfoList.getSide1Name();
                    String side2Name = this.bandariyaTirahaInfoList.getSide2Name();
                    String side3Name = this.bandariyaTirahaInfoList.getSide3Name();
                    String side4Name = this.bandariyaTirahaInfoList.getSide4Name();
                    String side5Name = this.bandariyaTirahaInfoList.getSide5Name();

                    int side1Time = this.bandariyaTirahaInfoList.getSide1_time();
                    int side2Time = this.bandariyaTirahaInfoList.getSide2_time();
                    int side3Time = this.bandariyaTirahaInfoList.getSide3_time();
                    int side4Time = this.bandariyaTirahaInfoList.getSide4_time();
                    int side1LeftStatus = this.bandariyaTirahaInfoList.getSide1_left_status();
                    int side1RightStatus = this.bandariyaTirahaInfoList.getSide1_right_status();
                    int side1UpStatus = this.bandariyaTirahaInfoList.getSide1_up_status();
                    int side1DownStatus = this.bandariyaTirahaInfoList.getSide1_down_status();
                    int side2LeftStatus = this.bandariyaTirahaInfoList.getSide2_left_status();
                    int side2RightStatus = this.bandariyaTirahaInfoList.getSide2_right_status();
                    int side2UpStatus = this.bandariyaTirahaInfoList.getSide2_up_status();
                    int side2DownStatus = this.bandariyaTirahaInfoList.getSide2_down_status();
                    int side3LeftStatus = this.bandariyaTirahaInfoList.getSide3_left_status();
                    int side3RightStatus = this.bandariyaTirahaInfoList.getSide3_right_status();
                    int side3UpStatus = this.bandariyaTirahaInfoList.getSide3_up_status();
                    int side3DownStatus = this.bandariyaTirahaInfoList.getSide3_down_status();
                    int side4LeftStatus = this.bandariyaTirahaInfoList.getSide4_left_status();
                    int side4RightStatus = this.bandariyaTirahaInfoList.getSide4_right_status();
                    int side4UpStatus = this.bandariyaTirahaInfoList.getSide4_up_status();
                    int side4DownStatus = this.bandariyaTirahaInfoList.getSide4_down_status();

                    int juncHr = this.bandariyaTirahaInfoList.getJuncHr();
                    int juncMin = this.bandariyaTirahaInfoList.getJuncMin();
                    int juncDat = this.bandariyaTirahaInfoList.getJuncDate();
                    int juncMonth = this.bandariyaTirahaInfoList.getJuncMonth();
                    int juncYear = this.bandariyaTirahaInfoList.getJuncYear();

                    request.setAttribute("juncHr", juncHr);
                    request.setAttribute("juncMin", juncMin);
                    request.setAttribute("juncDat", juncDat);
                    request.setAttribute("juncMonth", juncMonth);
                    request.setAttribute("juncYear", juncYear);

                    request.setAttribute("functionNo", functionNo);
                    request.setAttribute("junctionId", Integer.valueOf(junction_id));
                    request.setAttribute("program_version_no", Integer.valueOf(program_version_no));
                    request.setAttribute("fileNo", Integer.valueOf(fileNo));
                    request.setAttribute("junctionName", junctionName);
                    request.setAttribute("activity", activity);
                    request.setAttribute("sideNo", Integer.valueOf(side_no));
                    request.setAttribute("sideName", sideName);
                    request.setAttribute("side1Name", side1Name);
                    request.setAttribute("side2Name", side2Name);
                    request.setAttribute("side3Name", side3Name);
                    request.setAttribute("side4Name", side4Name);
                    request.setAttribute("side5Name", side5Name);

                    request.setAttribute("onTime", onTime);
                    request.setAttribute("offTime", offTime);
                    request.setAttribute("plan_no", plan_no);
                    request.setAttribute("mode", mode);

                    request.setAttribute("side1Time", Integer.valueOf(side1Time));
                    request.setAttribute("side2Time", Integer.valueOf(side2Time));
                    request.setAttribute("side3Time", Integer.valueOf(side3Time));
                    request.setAttribute("side4Time", Integer.valueOf(side4Time));
                    request.setAttribute("side1LeftStatus", Integer.valueOf(side1LeftStatus));
                    request.setAttribute("side1RightStatus", Integer.valueOf(side1RightStatus));
                    request.setAttribute("side1UpStatus", Integer.valueOf(side1UpStatus));
                    request.setAttribute("side1DownStatus", Integer.valueOf(side1DownStatus));
                    request.setAttribute("side2LeftStatus", Integer.valueOf(side2LeftStatus));
                    request.setAttribute("side2RightStatus", Integer.valueOf(side2RightStatus));
                    request.setAttribute("side2UpStatus", Integer.valueOf(side2UpStatus));
                    request.setAttribute("side2DownStatus", Integer.valueOf(side2DownStatus));
                    request.setAttribute("side3LeftStatus", Integer.valueOf(side3LeftStatus));
                    request.setAttribute("side3RightStatus", Integer.valueOf(side3RightStatus));
                    request.setAttribute("side3UpStatus", Integer.valueOf(side3UpStatus));
                    request.setAttribute("side3DownStatus", Integer.valueOf(side3DownStatus));
                    request.setAttribute("side4LeftStatus", Integer.valueOf(side4LeftStatus));
                    request.setAttribute("side4RightStatus", Integer.valueOf(side4RightStatus));
                    request.setAttribute("side4UpStatus", Integer.valueOf(side4UpStatus));
                    request.setAttribute("side4DownStatus", Integer.valueOf(side4DownStatus));
                    request.setAttribute("side4DownStatus", Integer.valueOf(side4DownStatus));
                    if(map_junction_id!=""){
                     request.getRequestDispatcher("/view/general/showMapOnTrafficSignal.jsp").forward(request, response);
                    }else{
                    request.getRequestDispatcher("ts_statusShower_view").forward(request, response);
                    }
                    return;
                } else {
                    String jSON_format = "Oops... No junction is active right now.";

                    request.setAttribute("message", jSON_format);
                    request.getRequestDispatcher("errorView").forward(request, response);
                    return;
                }
                }
                
                if(a == 16){
                //BeanUtils.copyProperties(planInfoList, ranitalInfoList);
                if (this.labourChowkInfoList != null) {
                    int functionNo = this.labourChowkInfoList.getFunction_no();
                    int junction_id = this.labourChowkInfoList.getJunction_id();
                    int program_version_no = this.labourChowkInfoList.getProgram_version_no();
                    int fileNo = this.labourChowkInfoList.getFileNo();
                    int activity = this.labourChowkInfoList.getActivity();
                    int side_no = this.labourChowkInfoList.getSide_no();
                    int plan_no = this.labourChowkInfoList.getPlan_no();
                    String mode = this.labourChowkInfoList.getMode();
                    String junctionName = this.labourChowkInfoList.getJunction_name();
                    String sideName = this.labourChowkInfoList.getSideName();

                    String onTime = this.labourChowkInfoList.getOnTime();
                    String offTime = this.labourChowkInfoList.getOffTime();

                    String side1Name = this.labourChowkInfoList.getSide1Name();
                    String side2Name = this.labourChowkInfoList.getSide2Name();
                    String side3Name = this.labourChowkInfoList.getSide3Name();
                    String side4Name = this.labourChowkInfoList.getSide4Name();
                    String side5Name = this.labourChowkInfoList.getSide5Name();

                    int side1Time = this.labourChowkInfoList.getSide1_time();
                    int side2Time = this.labourChowkInfoList.getSide2_time();
                    int side3Time = this.labourChowkInfoList.getSide3_time();
                    int side4Time = this.labourChowkInfoList.getSide4_time();
                    int side1LeftStatus = this.labourChowkInfoList.getSide1_left_status();
                    int side1RightStatus = this.labourChowkInfoList.getSide1_right_status();
                    int side1UpStatus = this.labourChowkInfoList.getSide1_up_status();
                    int side1DownStatus = this.labourChowkInfoList.getSide1_down_status();
                    int side2LeftStatus = this.labourChowkInfoList.getSide2_left_status();
                    int side2RightStatus = this.labourChowkInfoList.getSide2_right_status();
                    int side2UpStatus = this.labourChowkInfoList.getSide2_up_status();
                    int side2DownStatus = this.labourChowkInfoList.getSide2_down_status();
                    int side3LeftStatus = this.labourChowkInfoList.getSide3_left_status();
                    int side3RightStatus = this.labourChowkInfoList.getSide3_right_status();
                    int side3UpStatus = this.labourChowkInfoList.getSide3_up_status();
                    int side3DownStatus = this.labourChowkInfoList.getSide3_down_status();
                    int side4LeftStatus = this.labourChowkInfoList.getSide4_left_status();
                    int side4RightStatus = this.labourChowkInfoList.getSide4_right_status();
                    int side4UpStatus = this.labourChowkInfoList.getSide4_up_status();
                    int side4DownStatus = this.labourChowkInfoList.getSide4_down_status();

                    int juncHr = this.labourChowkInfoList.getJuncHr();
                    int juncMin = this.labourChowkInfoList.getJuncMin();
                    int juncDat = this.labourChowkInfoList.getJuncDate();
                    int juncMonth = this.labourChowkInfoList.getJuncMonth();
                    int juncYear = this.labourChowkInfoList.getJuncYear();

                    request.setAttribute("juncHr", juncHr);
                    request.setAttribute("juncMin", juncMin);
                    request.setAttribute("juncDat", juncDat);
                    request.setAttribute("juncMonth", juncMonth);
                    request.setAttribute("juncYear", juncYear);

                    request.setAttribute("functionNo", Integer.valueOf(functionNo));
                    request.setAttribute("junctionId", Integer.valueOf(junction_id));
                    request.setAttribute("program_version_no", Integer.valueOf(program_version_no));
                    request.setAttribute("fileNo", Integer.valueOf(fileNo));
                    request.setAttribute("junctionName", junctionName);
                    request.setAttribute("activity", activity);
                    request.setAttribute("sideNo", Integer.valueOf(side_no));
                    request.setAttribute("sideName", sideName);
                    request.setAttribute("side1Name", side1Name);
                    request.setAttribute("side2Name", side2Name);
                    request.setAttribute("side3Name", side3Name);
                    request.setAttribute("side4Name", side4Name);
                    request.setAttribute("side5Name", side5Name);

                    request.setAttribute("onTime", onTime);
                    request.setAttribute("offTime", offTime);
                    request.setAttribute("plan_no", plan_no);
                    request.setAttribute("mode", mode);

                    request.setAttribute("side1Time", Integer.valueOf(side1Time));
                    request.setAttribute("side2Time", Integer.valueOf(side2Time));
                    request.setAttribute("side3Time", Integer.valueOf(side3Time));
                    request.setAttribute("side4Time", Integer.valueOf(side4Time));
                    request.setAttribute("side1LeftStatus", Integer.valueOf(side1LeftStatus));
                    request.setAttribute("side1RightStatus", Integer.valueOf(side1RightStatus));
                    request.setAttribute("side1UpStatus", Integer.valueOf(side1UpStatus));
                    request.setAttribute("side1DownStatus", Integer.valueOf(side1DownStatus));
                    request.setAttribute("side2LeftStatus", Integer.valueOf(side2LeftStatus));
                    request.setAttribute("side2RightStatus", Integer.valueOf(side2RightStatus));
                    request.setAttribute("side2UpStatus", Integer.valueOf(side2UpStatus));
                    request.setAttribute("side2DownStatus", Integer.valueOf(side2DownStatus));
                    request.setAttribute("side3LeftStatus", Integer.valueOf(side3LeftStatus));
                    request.setAttribute("side3RightStatus", Integer.valueOf(side3RightStatus));
                    request.setAttribute("side3UpStatus", Integer.valueOf(side3UpStatus));
                    request.setAttribute("side3DownStatus", Integer.valueOf(side3DownStatus));
                    request.setAttribute("side4LeftStatus", Integer.valueOf(side4LeftStatus));
                    request.setAttribute("side4RightStatus", Integer.valueOf(side4RightStatus));
                    request.setAttribute("side4UpStatus", Integer.valueOf(side4UpStatus));
                    request.setAttribute("side4DownStatus", Integer.valueOf(side4DownStatus));
                    request.setAttribute("side4DownStatus", Integer.valueOf(side4DownStatus));
                    if(map_junction_id!=""){
                     request.getRequestDispatcher("/view/general/showMapOnTrafficSignal.jsp").forward(request, response);
                    }else{
                    request.getRequestDispatcher("ts_statusShower_view").forward(request, response);
                    }
                    return;
                } else {
                    String jSON_format = "Oops... No junction is active right now.";

                    request.setAttribute("message", jSON_format);
                    request.getRequestDispatcher("errorView").forward(request, response);
                    return;
                }
                }

                if(a == 13){
                //BeanUtils.copyProperties(planInfoList, ranitalInfoList);
                if (this.teenPattiInfoList != null) {
                    int functionNo = this.teenPattiInfoList.getFunction_no();
                    int junction_id = this.teenPattiInfoList.getJunction_id();
                    int program_version_no = this.teenPattiInfoList.getProgram_version_no();
                    int fileNo = this.teenPattiInfoList.getFileNo();
                    int activity = this.teenPattiInfoList.getActivity();
                    int side_no = this.teenPattiInfoList.getSide_no();
                    int plan_no = this.teenPattiInfoList.getPlan_no();
                    String mode = this.teenPattiInfoList.getMode();
                    String junctionName = this.teenPattiInfoList.getJunction_name();
                    String sideName = this.teenPattiInfoList.getSideName();

                    String onTime = this.teenPattiInfoList.getOnTime();
                    String offTime = this.teenPattiInfoList.getOffTime();

                    String side1Name = this.teenPattiInfoList.getSide1Name();
                    String side2Name = this.teenPattiInfoList.getSide2Name();
                    String side3Name = this.teenPattiInfoList.getSide3Name();
                    String side4Name = this.teenPattiInfoList.getSide4Name();
                    String side5Name = this.teenPattiInfoList.getSide5Name();

                    int side1Time = this.teenPattiInfoList.getSide1_time();
                    int side2Time = this.teenPattiInfoList.getSide2_time();
                    int side3Time = this.teenPattiInfoList.getSide3_time();
                    int side4Time = this.teenPattiInfoList.getSide4_time();
                    int side1LeftStatus = this.teenPattiInfoList.getSide1_left_status();
                    int side1RightStatus = this.teenPattiInfoList.getSide1_right_status();
                    int side1UpStatus = this.teenPattiInfoList.getSide1_up_status();
                    int side1DownStatus = this.teenPattiInfoList.getSide1_down_status();
                    int side2LeftStatus = this.teenPattiInfoList.getSide2_left_status();
                    int side2RightStatus = this.teenPattiInfoList.getSide2_right_status();
                    int side2UpStatus = this.teenPattiInfoList.getSide2_up_status();
                    int side2DownStatus = this.teenPattiInfoList.getSide2_down_status();
                    int side3LeftStatus = this.teenPattiInfoList.getSide3_left_status();
                    int side3RightStatus = this.teenPattiInfoList.getSide3_right_status();
                    int side3UpStatus = this.teenPattiInfoList.getSide3_up_status();
                    int side3DownStatus = this.teenPattiInfoList.getSide3_down_status();
                    int side4LeftStatus = this.teenPattiInfoList.getSide4_left_status();
                    int side4RightStatus = this.teenPattiInfoList.getSide4_right_status();
                    int side4UpStatus = this.teenPattiInfoList.getSide4_up_status();
                    int side4DownStatus = this.teenPattiInfoList.getSide4_down_status();

                    int juncHr = this.teenPattiInfoList.getJuncHr();
                    int juncMin = this.teenPattiInfoList.getJuncMin();
                    int juncDat = this.teenPattiInfoList.getJuncDate();
                    int juncMonth = this.teenPattiInfoList.getJuncMonth();
                    int juncYear = this.teenPattiInfoList.getJuncYear();

                    request.setAttribute("juncHr", juncHr);
                    request.setAttribute("juncMin", juncMin);
                    request.setAttribute("juncDat", juncDat);
                    request.setAttribute("juncMonth", juncMonth);
                    request.setAttribute("juncYear", juncYear);

                    request.setAttribute("functionNo", Integer.valueOf(functionNo));
                    request.setAttribute("junctionId", Integer.valueOf(junction_id));
                    request.setAttribute("program_version_no", Integer.valueOf(program_version_no));
                    request.setAttribute("fileNo", Integer.valueOf(fileNo));
                    request.setAttribute("junctionName", junctionName);
                    request.setAttribute("activity", activity);
                    request.setAttribute("sideNo", Integer.valueOf(side_no));
                    request.setAttribute("sideName", sideName);
                    request.setAttribute("side1Name", side1Name);
                    request.setAttribute("side2Name", side2Name);
                    request.setAttribute("side3Name", side3Name);
                    request.setAttribute("side4Name", side4Name);
                    request.setAttribute("side5Name", side5Name);

                    request.setAttribute("onTime", onTime);
                    request.setAttribute("offTime", offTime);
                    request.setAttribute("plan_no", plan_no);
                    request.setAttribute("mode", mode);

                    request.setAttribute("side1Time", Integer.valueOf(side1Time));
                    request.setAttribute("side2Time", Integer.valueOf(side2Time));
                    request.setAttribute("side3Time", Integer.valueOf(side3Time));
                    request.setAttribute("side4Time", Integer.valueOf(side4Time));
                    request.setAttribute("side1LeftStatus", Integer.valueOf(side1LeftStatus));
                    request.setAttribute("side1RightStatus", Integer.valueOf(side1RightStatus));
                    request.setAttribute("side1UpStatus", Integer.valueOf(side1UpStatus));
                    request.setAttribute("side1DownStatus", Integer.valueOf(side1DownStatus));
                    request.setAttribute("side2LeftStatus", Integer.valueOf(side2LeftStatus));
                    request.setAttribute("side2RightStatus", Integer.valueOf(side2RightStatus));
                    request.setAttribute("side2UpStatus", Integer.valueOf(side2UpStatus));
                    request.setAttribute("side2DownStatus", Integer.valueOf(side2DownStatus));
                    request.setAttribute("side3LeftStatus", Integer.valueOf(side3LeftStatus));
                    request.setAttribute("side3RightStatus", Integer.valueOf(side3RightStatus));
                    request.setAttribute("side3UpStatus", Integer.valueOf(side3UpStatus));
                    request.setAttribute("side3DownStatus", Integer.valueOf(side3DownStatus));
                    request.setAttribute("side4LeftStatus", Integer.valueOf(side4LeftStatus));
                    request.setAttribute("side4RightStatus", Integer.valueOf(side4RightStatus));
                    request.setAttribute("side4UpStatus", Integer.valueOf(side4UpStatus));
                    request.setAttribute("side4DownStatus", Integer.valueOf(side4DownStatus));
                    request.setAttribute("side4DownStatus", Integer.valueOf(side4DownStatus));
                    if(map_junction_id!=""){
                     request.getRequestDispatcher("/view/general/showMapOnTrafficSignal.jsp").forward(request, response);
                    }else{
                    request.getRequestDispatcher("ts_statusShower_view").forward(request, response);
                    }
                    return;
                } else {
                    String jSON_format = "Oops... No junction is active right now.";

                    request.setAttribute("message", jSON_format);
                    request.getRequestDispatcher("errorView").forward(request, response);
                    return;
                }
                }

                if(a == 1){
                //BeanUtils.copyProperties(planInfoList, ranitalInfoList);
                if (this.yatayatThanaInfoList != null) {
                    int functionNo = this.yatayatThanaInfoList.getFunction_no();
                    int junction_id = this.yatayatThanaInfoList.getJunction_id();
                    int program_version_no = this.yatayatThanaInfoList.getProgram_version_no();
                    int fileNo = this.yatayatThanaInfoList.getFileNo();
                    int activity = this.yatayatThanaInfoList.getActivity();
                    int side_no = this.yatayatThanaInfoList.getSide_no();
                    int plan_no = this.yatayatThanaInfoList.getPlan_no();
                    String mode = this.yatayatThanaInfoList.getMode();
                    String junctionName = this.yatayatThanaInfoList.getJunction_name();
                    String sideName = this.yatayatThanaInfoList.getSideName();

                    String onTime = this.yatayatThanaInfoList.getOnTime();
                    String offTime = this.yatayatThanaInfoList.getOffTime();

                    String side1Name = this.yatayatThanaInfoList.getSide1Name();
                    String side2Name = this.yatayatThanaInfoList.getSide2Name();
                    String side3Name = this.yatayatThanaInfoList.getSide3Name();
                    String side4Name = this.yatayatThanaInfoList.getSide4Name();
                    String side5Name = this.yatayatThanaInfoList.getSide5Name();

                    int side1Time = this.yatayatThanaInfoList.getSide1_time();
                    int side2Time = this.yatayatThanaInfoList.getSide2_time();
                    int side3Time = this.yatayatThanaInfoList.getSide3_time();
                    int side4Time = this.yatayatThanaInfoList.getSide4_time();
                    int side1LeftStatus = this.yatayatThanaInfoList.getSide1_left_status();
                    int side1RightStatus = this.yatayatThanaInfoList.getSide1_right_status();
                    int side1UpStatus = this.yatayatThanaInfoList.getSide1_up_status();
                    int side1DownStatus = this.yatayatThanaInfoList.getSide1_down_status();
                    int side2LeftStatus = this.yatayatThanaInfoList.getSide2_left_status();
                    int side2RightStatus = this.yatayatThanaInfoList.getSide2_right_status();
                    int side2UpStatus = this.yatayatThanaInfoList.getSide2_up_status();
                    int side2DownStatus = this.yatayatThanaInfoList.getSide2_down_status();
                    int side3LeftStatus = this.yatayatThanaInfoList.getSide3_left_status();
                    int side3RightStatus = this.yatayatThanaInfoList.getSide3_right_status();
                    int side3UpStatus = this.yatayatThanaInfoList.getSide3_up_status();
                    int side3DownStatus = this.yatayatThanaInfoList.getSide3_down_status();
                    int side4LeftStatus = this.yatayatThanaInfoList.getSide4_left_status();
                    int side4RightStatus = this.yatayatThanaInfoList.getSide4_right_status();
                    int side4UpStatus = this.yatayatThanaInfoList.getSide4_up_status();
                    int side4DownStatus = this.yatayatThanaInfoList.getSide4_down_status();

                    int juncHr = this.yatayatThanaInfoList.getJuncHr();
                    int juncMin = this.yatayatThanaInfoList.getJuncMin();
                    int juncDat = this.yatayatThanaInfoList.getJuncDate();
                    int juncMonth = this.yatayatThanaInfoList.getJuncMonth();
                    int juncYear = this.yatayatThanaInfoList.getJuncYear();

                    request.setAttribute("juncHr", juncHr);
                    request.setAttribute("juncMin", juncMin);
                    request.setAttribute("juncDat", juncDat);
                    request.setAttribute("juncMonth", juncMonth);
                    request.setAttribute("juncYear", juncYear);

                    request.setAttribute("functionNo", Integer.valueOf(functionNo));
                    request.setAttribute("junctionId", Integer.valueOf(junction_id));
                    request.setAttribute("program_version_no", Integer.valueOf(program_version_no));
                    request.setAttribute("fileNo", Integer.valueOf(fileNo));
                    request.setAttribute("junctionName", junctionName);
                    request.setAttribute("activity", activity);
                    request.setAttribute("sideNo", Integer.valueOf(side_no));
                    request.setAttribute("sideName", sideName);
                    request.setAttribute("side1Name", side1Name);
                    request.setAttribute("side2Name", side2Name);
                    request.setAttribute("side3Name", side3Name);
                    request.setAttribute("side4Name", side4Name);
                    request.setAttribute("side5Name", side5Name);

                    request.setAttribute("onTime", onTime);
                    request.setAttribute("offTime", offTime);
                    request.setAttribute("plan_no", plan_no);
                    request.setAttribute("mode", mode);

                    request.setAttribute("side1Time", Integer.valueOf(side1Time));
                    request.setAttribute("side2Time", Integer.valueOf(side2Time));
                    request.setAttribute("side3Time", Integer.valueOf(side3Time));
                    request.setAttribute("side4Time", Integer.valueOf(side4Time));
                    request.setAttribute("side1LeftStatus", Integer.valueOf(side1LeftStatus));
                    request.setAttribute("side1RightStatus", Integer.valueOf(side1RightStatus));
                    request.setAttribute("side1UpStatus", Integer.valueOf(side1UpStatus));
                    request.setAttribute("side1DownStatus", Integer.valueOf(side1DownStatus));
                    request.setAttribute("side2LeftStatus", Integer.valueOf(side2LeftStatus));
                    request.setAttribute("side2RightStatus", Integer.valueOf(side2RightStatus));
                    request.setAttribute("side2UpStatus", Integer.valueOf(side2UpStatus));
                    request.setAttribute("side2DownStatus", Integer.valueOf(side2DownStatus));
                    request.setAttribute("side3LeftStatus", Integer.valueOf(side3LeftStatus));
                    request.setAttribute("side3RightStatus", Integer.valueOf(side3RightStatus));
                    request.setAttribute("side3UpStatus", Integer.valueOf(side3UpStatus));
                    request.setAttribute("side3DownStatus", Integer.valueOf(side3DownStatus));
                    request.setAttribute("side4LeftStatus", Integer.valueOf(side4LeftStatus));
                    request.setAttribute("side4RightStatus", Integer.valueOf(side4RightStatus));
                    request.setAttribute("side4UpStatus", Integer.valueOf(side4UpStatus));
                    request.setAttribute("side4DownStatus", Integer.valueOf(side4DownStatus));
                    request.setAttribute("side4DownStatus", Integer.valueOf(side4DownStatus));
                    if(map_junction_id!=""){
                     request.getRequestDispatcher("/view/general/showMapOnTrafficSignal.jsp").forward(request, response);
                    }else{
                    request.getRequestDispatcher("ts_statusShower_view").forward(request, response);
                    }
                    return;
                } else {
                    String jSON_format = "Oops... No junction is active right now.";

                    request.setAttribute("message", jSON_format);
                    request.getRequestDispatcher("errorView").forward(request, response);
                    return;
                }
                }
                
                if(a == 2){
                //BeanUtils.copyProperties(planInfoList, ranitalInfoList);
                if (this.damohNakaInfoList != null) {
                    int functionNo = this.damohNakaInfoList.getFunction_no();
                    int junction_id = this.damohNakaInfoList.getJunction_id();
                    int program_version_no = this.damohNakaInfoList.getProgram_version_no();
                    int fileNo = this.damohNakaInfoList.getFileNo();
                    int activity = this.damohNakaInfoList.getActivity();
                    int side_no = this.damohNakaInfoList.getSide_no();
                    int plan_no = this.damohNakaInfoList.getPlan_no();
                    String mode = this.damohNakaInfoList.getMode();
                    String junctionName = this.damohNakaInfoList.getJunction_name();
                    String sideName = this.damohNakaInfoList.getSideName();

                    String onTime = this.damohNakaInfoList.getOnTime();
                    String offTime = this.damohNakaInfoList.getOffTime();

                    String side1Name = this.damohNakaInfoList.getSide1Name();
                    String side2Name = this.damohNakaInfoList.getSide2Name();
                    String side3Name = this.damohNakaInfoList.getSide3Name();
                    String side4Name = this.damohNakaInfoList.getSide4Name();
                    String side5Name = this.damohNakaInfoList.getSide5Name();

                    int side1Time = this.damohNakaInfoList.getSide1_time();
                    int side2Time = this.damohNakaInfoList.getSide2_time();
                    int side3Time = this.damohNakaInfoList.getSide3_time();
                    int side4Time = this.damohNakaInfoList.getSide4_time();
                    int side1LeftStatus = this.damohNakaInfoList.getSide1_left_status();
                    int side1RightStatus = this.damohNakaInfoList.getSide1_right_status();
                    int side1UpStatus = this.damohNakaInfoList.getSide1_up_status();
                    int side1DownStatus = this.damohNakaInfoList.getSide1_down_status();
                    int side2LeftStatus = this.damohNakaInfoList.getSide2_left_status();
                    int side2RightStatus = this.damohNakaInfoList.getSide2_right_status();
                    int side2UpStatus = this.damohNakaInfoList.getSide2_up_status();
                    int side2DownStatus = this.damohNakaInfoList.getSide2_down_status();
                    int side3LeftStatus = this.damohNakaInfoList.getSide3_left_status();
                    int side3RightStatus = this.damohNakaInfoList.getSide3_right_status();
                    int side3UpStatus = this.damohNakaInfoList.getSide3_up_status();
                    int side3DownStatus = this.damohNakaInfoList.getSide3_down_status();
                    int side4LeftStatus = this.damohNakaInfoList.getSide4_left_status();
                    int side4RightStatus = this.damohNakaInfoList.getSide4_right_status();
                    int side4UpStatus = this.damohNakaInfoList.getSide4_up_status();
                    int side4DownStatus = this.damohNakaInfoList.getSide4_down_status();

                    int juncHr = this.damohNakaInfoList.getJuncHr();
                    int juncMin = this.damohNakaInfoList.getJuncMin();
                    int juncDat = this.damohNakaInfoList.getJuncDate();
                    int juncMonth = this.damohNakaInfoList.getJuncMonth();
                    int juncYear = this.damohNakaInfoList.getJuncYear();

                    request.setAttribute("juncHr", juncHr);
                    request.setAttribute("juncMin", juncMin);
                    request.setAttribute("juncDat", juncDat);
                    request.setAttribute("juncMonth", juncMonth);
                    request.setAttribute("juncYear", juncYear);

                    request.setAttribute("functionNo", Integer.valueOf(functionNo));
                    request.setAttribute("junctionId", Integer.valueOf(junction_id));
                    request.setAttribute("program_version_no", Integer.valueOf(program_version_no));
                    request.setAttribute("fileNo", Integer.valueOf(fileNo));
                    request.setAttribute("junctionName", junctionName);
                    request.setAttribute("activity", activity);
                    request.setAttribute("sideNo", Integer.valueOf(side_no));
                    request.setAttribute("sideName", sideName);
                    request.setAttribute("side1Name", side1Name);
                    request.setAttribute("side2Name", side2Name);
                    request.setAttribute("side3Name", side3Name);
                    request.setAttribute("side4Name", side4Name);
                    request.setAttribute("side5Name", side5Name);

                    request.setAttribute("onTime", onTime);
                    request.setAttribute("offTime", offTime);
                    request.setAttribute("plan_no", plan_no);
                    request.setAttribute("mode", mode);

                    request.setAttribute("side1Time", Integer.valueOf(side1Time));
                    request.setAttribute("side2Time", Integer.valueOf(side2Time));
                    request.setAttribute("side3Time", Integer.valueOf(side3Time));
                    request.setAttribute("side4Time", Integer.valueOf(side4Time));
                    request.setAttribute("side1LeftStatus", Integer.valueOf(side1LeftStatus));
                    request.setAttribute("side1RightStatus", Integer.valueOf(side1RightStatus));
                    request.setAttribute("side1UpStatus", Integer.valueOf(side1UpStatus));
                    request.setAttribute("side1DownStatus", Integer.valueOf(side1DownStatus));
                    request.setAttribute("side2LeftStatus", Integer.valueOf(side2LeftStatus));
                    request.setAttribute("side2RightStatus", Integer.valueOf(side2RightStatus));
                    request.setAttribute("side2UpStatus", Integer.valueOf(side2UpStatus));
                    request.setAttribute("side2DownStatus", Integer.valueOf(side2DownStatus));
                    request.setAttribute("side3LeftStatus", Integer.valueOf(side3LeftStatus));
                    request.setAttribute("side3RightStatus", Integer.valueOf(side3RightStatus));
                    request.setAttribute("side3UpStatus", Integer.valueOf(side3UpStatus));
                    request.setAttribute("side3DownStatus", Integer.valueOf(side3DownStatus));
                    request.setAttribute("side4LeftStatus", Integer.valueOf(side4LeftStatus));
                    request.setAttribute("side4RightStatus", Integer.valueOf(side4RightStatus));
                    request.setAttribute("side4UpStatus", Integer.valueOf(side4UpStatus));
                    request.setAttribute("side4DownStatus", Integer.valueOf(side4DownStatus));
                    request.setAttribute("side4DownStatus", Integer.valueOf(side4DownStatus));
                    if(map_junction_id!=""){
                     request.getRequestDispatcher("/view/general/showMapOnTrafficSignal.jsp").forward(request, response);
                    }else{
                    request.getRequestDispatcher("ts_statusShower_view").forward(request, response);
                    }
                    return;
                } else {
                    String jSON_format = "Oops... No junction is active right now.";

                    request.setAttribute("message", jSON_format);
                    request.getRequestDispatcher("errorView").forward(request, response);
                    return;
                }
                }
                if(a == 4){
                //BeanUtils.copyProperties(planInfoList, ranitalInfoList);
                if (this.katangaInfoList != null) {
                    int functionNo = this.katangaInfoList.getFunction_no();
                    int junction_id = this.katangaInfoList.getJunction_id();
                    int program_version_no = this.katangaInfoList.getProgram_version_no();
                    int fileNo = this.katangaInfoList.getFileNo();
                    int activity = this.katangaInfoList.getActivity();
                    int side_no = this.katangaInfoList.getSide_no();
                    int plan_no = this.katangaInfoList.getPlan_no();
                    String mode = this.katangaInfoList.getMode();
                    String junctionName = this.katangaInfoList.getJunction_name();
                    String sideName = this.katangaInfoList.getSideName();

                    String onTime = this.katangaInfoList.getOnTime();
                    String offTime = this.katangaInfoList.getOffTime();

                    String side1Name = this.katangaInfoList.getSide1Name();
                    String side2Name = this.katangaInfoList.getSide2Name();
                    String side3Name = this.katangaInfoList.getSide3Name();
                    String side4Name = this.katangaInfoList.getSide4Name();
                    String side5Name = this.katangaInfoList.getSide5Name();

                    int side1Time = this.katangaInfoList.getSide1_time();
                    int side2Time = this.katangaInfoList.getSide2_time();
                    int side3Time = this.katangaInfoList.getSide3_time();
                    int side4Time = this.katangaInfoList.getSide4_time();
                    int side1LeftStatus = this.katangaInfoList.getSide1_left_status();
                    int side1RightStatus = this.katangaInfoList.getSide1_right_status();
                    int side1UpStatus = this.katangaInfoList.getSide1_up_status();
                    int side1DownStatus = this.katangaInfoList.getSide1_down_status();
                    int side2LeftStatus = this.katangaInfoList.getSide2_left_status();
                    int side2RightStatus = this.katangaInfoList.getSide2_right_status();
                    int side2UpStatus = this.katangaInfoList.getSide2_up_status();
                    int side2DownStatus = this.katangaInfoList.getSide2_down_status();
                    int side3LeftStatus = this.katangaInfoList.getSide3_left_status();
                    int side3RightStatus = this.katangaInfoList.getSide3_right_status();
                    int side3UpStatus = this.katangaInfoList.getSide3_up_status();
                    int side3DownStatus = this.katangaInfoList.getSide3_down_status();
                    int side4LeftStatus = this.katangaInfoList.getSide4_left_status();
                    int side4RightStatus = this.katangaInfoList.getSide4_right_status();
                    int side4UpStatus = this.katangaInfoList.getSide4_up_status();
                    int side4DownStatus = this.katangaInfoList.getSide4_down_status();

                    int juncHr = this.katangaInfoList.getJuncHr();
                    int juncMin = this.katangaInfoList.getJuncMin();
                    int juncDat = this.katangaInfoList.getJuncDate();
                    int juncMonth = this.katangaInfoList.getJuncMonth();
                    int juncYear = this.katangaInfoList.getJuncYear();

                    request.setAttribute("juncHr", juncHr);
                    request.setAttribute("juncMin", juncMin);
                    request.setAttribute("juncDat", juncDat);
                    request.setAttribute("juncMonth", juncMonth);
                    request.setAttribute("juncYear", juncYear);

                    request.setAttribute("functionNo", Integer.valueOf(functionNo));
                    request.setAttribute("junctionId", Integer.valueOf(junction_id));
                    request.setAttribute("program_version_no", Integer.valueOf(program_version_no));
                    request.setAttribute("fileNo", Integer.valueOf(fileNo));
                    request.setAttribute("junctionName", junctionName);
                    request.setAttribute("activity", activity);
                    request.setAttribute("sideNo", Integer.valueOf(side_no));
                    request.setAttribute("sideName", sideName);
                    request.setAttribute("side1Name", side1Name);
                    request.setAttribute("side2Name", side2Name);
                    request.setAttribute("side3Name", side3Name);
                    request.setAttribute("side4Name", side4Name);
                    request.setAttribute("side5Name", side5Name);

                    request.setAttribute("onTime", onTime);
                    request.setAttribute("offTime", offTime);
                    request.setAttribute("plan_no", plan_no);
                    request.setAttribute("mode", mode);

                    request.setAttribute("side1Time", Integer.valueOf(side1Time));
                    request.setAttribute("side2Time", Integer.valueOf(side2Time));
                    request.setAttribute("side3Time", Integer.valueOf(side3Time));
                    request.setAttribute("side4Time", Integer.valueOf(side4Time));
                    request.setAttribute("side1LeftStatus", Integer.valueOf(side1LeftStatus));
                    request.setAttribute("side1RightStatus", Integer.valueOf(side1RightStatus));
                    request.setAttribute("side1UpStatus", Integer.valueOf(side1UpStatus));
                    request.setAttribute("side1DownStatus", Integer.valueOf(side1DownStatus));
                    request.setAttribute("side2LeftStatus", Integer.valueOf(side2LeftStatus));
                    request.setAttribute("side2RightStatus", Integer.valueOf(side2RightStatus));
                    request.setAttribute("side2UpStatus", Integer.valueOf(side2UpStatus));
                    request.setAttribute("side2DownStatus", Integer.valueOf(side2DownStatus));
                    request.setAttribute("side3LeftStatus", Integer.valueOf(side3LeftStatus));
                    request.setAttribute("side3RightStatus", Integer.valueOf(side3RightStatus));
                    request.setAttribute("side3UpStatus", Integer.valueOf(side3UpStatus));
                    request.setAttribute("side3DownStatus", Integer.valueOf(side3DownStatus));
                    request.setAttribute("side4LeftStatus", Integer.valueOf(side4LeftStatus));
                    request.setAttribute("side4RightStatus", Integer.valueOf(side4RightStatus));
                    request.setAttribute("side4UpStatus", Integer.valueOf(side4UpStatus));
                    request.setAttribute("side4DownStatus", Integer.valueOf(side4DownStatus));
                    request.setAttribute("side4DownStatus", Integer.valueOf(side4DownStatus));
                    if(map_junction_id!=""){
                     request.getRequestDispatcher("/view/general/showMapOnTrafficSignal.jsp").forward(request, response);
                    }else{
                    request.getRequestDispatcher("ts_statusShower_view").forward(request, response);
                    }
                    return;
                } else {
                    String jSON_format = "Oops... No junction is active right now.";

                    request.setAttribute("message", jSON_format);
                    request.getRequestDispatcher("errorView").forward(request, response);
                    return;
                }
                }
                if(a == 5){
                //BeanUtils.copyProperties(planInfoList, ranitalInfoList);
                if (this.baldeobagInfoList != null) {
                    int functionNo = this.baldeobagInfoList.getFunction_no();
                    int junction_id = this.baldeobagInfoList.getJunction_id();
                    int program_version_no = this.baldeobagInfoList.getProgram_version_no();
                    int fileNo = this.baldeobagInfoList.getFileNo();
                    int activity = this.baldeobagInfoList.getActivity();
                    int side_no = this.baldeobagInfoList.getSide_no();
                    int plan_no = this.baldeobagInfoList.getPlan_no();
                    String mode = this.baldeobagInfoList.getMode();
                    String junctionName = this.baldeobagInfoList.getJunction_name();
                    String sideName = this.baldeobagInfoList.getSideName();

                    String onTime = this.baldeobagInfoList.getOnTime();
                    String offTime = this.baldeobagInfoList.getOffTime();

                    String side1Name = this.baldeobagInfoList.getSide1Name();
                    String side2Name = this.baldeobagInfoList.getSide2Name();
                    String side3Name = this.baldeobagInfoList.getSide3Name();
                    String side4Name = this.baldeobagInfoList.getSide4Name();
                    String side5Name = this.baldeobagInfoList.getSide5Name();

                    int side1Time = this.baldeobagInfoList.getSide1_time();
                    int side2Time = this.baldeobagInfoList.getSide2_time();
                    int side3Time = this.baldeobagInfoList.getSide3_time();
                    int side4Time = this.baldeobagInfoList.getSide4_time();
                    int side1LeftStatus = this.baldeobagInfoList.getSide1_left_status();
                    int side1RightStatus = this.baldeobagInfoList.getSide1_right_status();
                    int side1UpStatus = this.baldeobagInfoList.getSide1_up_status();
                    int side1DownStatus = this.baldeobagInfoList.getSide1_down_status();
                    int side2LeftStatus = this.baldeobagInfoList.getSide2_left_status();
                    int side2RightStatus = this.baldeobagInfoList.getSide2_right_status();
                    int side2UpStatus = this.baldeobagInfoList.getSide2_up_status();
                    int side2DownStatus = this.baldeobagInfoList.getSide2_down_status();
                    int side3LeftStatus = this.baldeobagInfoList.getSide3_left_status();
                    int side3RightStatus = this.baldeobagInfoList.getSide3_right_status();
                    int side3UpStatus = this.baldeobagInfoList.getSide3_up_status();
                    int side3DownStatus = this.baldeobagInfoList.getSide3_down_status();
                    int side4LeftStatus = this.baldeobagInfoList.getSide4_left_status();
                    int side4RightStatus = this.baldeobagInfoList.getSide4_right_status();
                    int side4UpStatus = this.baldeobagInfoList.getSide4_up_status();
                    int side4DownStatus = this.baldeobagInfoList.getSide4_down_status();

                    int juncHr = this.baldeobagInfoList.getJuncHr();
                    int juncMin = this.baldeobagInfoList.getJuncMin();
                    int juncDat = this.baldeobagInfoList.getJuncDate();
                    int juncMonth = this.baldeobagInfoList.getJuncMonth();
                    int juncYear = this.baldeobagInfoList.getJuncYear();

                    request.setAttribute("juncHr", juncHr);
                    request.setAttribute("juncMin", juncMin);
                    request.setAttribute("juncDat", juncDat);
                    request.setAttribute("juncMonth", juncMonth);
                    request.setAttribute("juncYear", juncYear);

                    request.setAttribute("functionNo", Integer.valueOf(functionNo));
                    request.setAttribute("junctionId", Integer.valueOf(junction_id));
                    request.setAttribute("program_version_no", Integer.valueOf(program_version_no));
                    request.setAttribute("fileNo", Integer.valueOf(fileNo));
                    request.setAttribute("junctionName", junctionName);
                    request.setAttribute("activity", activity);
                    request.setAttribute("sideNo", Integer.valueOf(side_no));
                    request.setAttribute("sideName", sideName);
                    request.setAttribute("side1Name", side1Name);
                    request.setAttribute("side2Name", side2Name);
                    request.setAttribute("side3Name", side3Name);
                    request.setAttribute("side4Name", side4Name);
                    request.setAttribute("side5Name", side5Name);

                    request.setAttribute("onTime", onTime);
                    request.setAttribute("offTime", offTime);
                    request.setAttribute("plan_no", plan_no);
                    request.setAttribute("mode", mode);

                    request.setAttribute("side1Time", Integer.valueOf(side1Time));
                    request.setAttribute("side2Time", Integer.valueOf(side2Time));
                    request.setAttribute("side3Time", Integer.valueOf(side3Time));
                    request.setAttribute("side4Time", Integer.valueOf(side4Time));
                    request.setAttribute("side1LeftStatus", Integer.valueOf(side1LeftStatus));
                    request.setAttribute("side1RightStatus", Integer.valueOf(side1RightStatus));
                    request.setAttribute("side1UpStatus", Integer.valueOf(side1UpStatus));
                    request.setAttribute("side1DownStatus", Integer.valueOf(side1DownStatus));
                    request.setAttribute("side2LeftStatus", Integer.valueOf(side2LeftStatus));
                    request.setAttribute("side2RightStatus", Integer.valueOf(side2RightStatus));
                    request.setAttribute("side2UpStatus", Integer.valueOf(side2UpStatus));
                    request.setAttribute("side2DownStatus", Integer.valueOf(side2DownStatus));
                    request.setAttribute("side3LeftStatus", Integer.valueOf(side3LeftStatus));
                    request.setAttribute("side3RightStatus", Integer.valueOf(side3RightStatus));
                    request.setAttribute("side3UpStatus", Integer.valueOf(side3UpStatus));
                    request.setAttribute("side3DownStatus", Integer.valueOf(side3DownStatus));
                    request.setAttribute("side4LeftStatus", Integer.valueOf(side4LeftStatus));
                    request.setAttribute("side4RightStatus", Integer.valueOf(side4RightStatus));
                    request.setAttribute("side4UpStatus", Integer.valueOf(side4UpStatus));
                    request.setAttribute("side4DownStatus", Integer.valueOf(side4DownStatus));
                    request.setAttribute("side4DownStatus", Integer.valueOf(side4DownStatus));
                    if(map_junction_id!=""){
                     request.getRequestDispatcher("/view/general/showMapOnTrafficSignal.jsp").forward(request, response);
                    }else{
                    request.getRequestDispatcher("ts_statusShower_view").forward(request, response);
                    }
                    return;
                } else {
                    String jSON_format = "Oops... No junction is active right now.";

                    request.setAttribute("message", jSON_format);
                    request.getRequestDispatcher("errorView").forward(request, response);
                    return;
                }
                }
                if(a == 6){
                //BeanUtils.copyProperties(planInfoList, ranitalInfoList);
                if (this.deendayalChowkInfoList != null) {
                    int functionNo = this.deendayalChowkInfoList.getFunction_no();
                    int junction_id = this.deendayalChowkInfoList.getJunction_id();
                    int program_version_no = this.deendayalChowkInfoList.getProgram_version_no();
                    int fileNo = this.deendayalChowkInfoList.getFileNo();
                    int activity = this.deendayalChowkInfoList.getActivity();
                    int side_no = this.deendayalChowkInfoList.getSide_no();
                    int plan_no = this.deendayalChowkInfoList.getPlan_no();
                    String mode = this.deendayalChowkInfoList.getMode();
                    String junctionName = this.deendayalChowkInfoList.getJunction_name();
                    String sideName = this.deendayalChowkInfoList.getSideName();

                    String onTime = this.deendayalChowkInfoList.getOnTime();
                    String offTime = this.deendayalChowkInfoList.getOffTime();

                    String side1Name = this.deendayalChowkInfoList.getSide1Name();
                    String side2Name = this.deendayalChowkInfoList.getSide2Name();
                    String side3Name = this.deendayalChowkInfoList.getSide3Name();
                    String side4Name = this.deendayalChowkInfoList.getSide4Name();
                    String side5Name = this.deendayalChowkInfoList.getSide5Name();

                    int side1Time = this.deendayalChowkInfoList.getSide1_time();
                    int side2Time = this.deendayalChowkInfoList.getSide2_time();
                    int side3Time = this.deendayalChowkInfoList.getSide3_time();
                    int side4Time = this.deendayalChowkInfoList.getSide4_time();
                    int side1LeftStatus = this.deendayalChowkInfoList.getSide1_left_status();
                    int side1RightStatus = this.deendayalChowkInfoList.getSide1_right_status();
                    int side1UpStatus = this.deendayalChowkInfoList.getSide1_up_status();
                    int side1DownStatus = this.deendayalChowkInfoList.getSide1_down_status();
                    int side2LeftStatus = this.deendayalChowkInfoList.getSide2_left_status();
                    int side2RightStatus = this.deendayalChowkInfoList.getSide2_right_status();
                    int side2UpStatus = this.deendayalChowkInfoList.getSide2_up_status();
                    int side2DownStatus = this.deendayalChowkInfoList.getSide2_down_status();
                    int side3LeftStatus = this.deendayalChowkInfoList.getSide3_left_status();
                    int side3RightStatus = this.deendayalChowkInfoList.getSide3_right_status();
                    int side3UpStatus = this.deendayalChowkInfoList.getSide3_up_status();
                    int side3DownStatus = this.deendayalChowkInfoList.getSide3_down_status();
                    int side4LeftStatus = this.deendayalChowkInfoList.getSide4_left_status();
                    int side4RightStatus = this.deendayalChowkInfoList.getSide4_right_status();
                    int side4UpStatus = this.deendayalChowkInfoList.getSide4_up_status();
                    int side4DownStatus = this.deendayalChowkInfoList.getSide4_down_status();

                    int juncHr = this.deendayalChowkInfoList.getJuncHr();
                    int juncMin = this.deendayalChowkInfoList.getJuncMin();
                    int juncDat = this.deendayalChowkInfoList.getJuncDate();
                    int juncMonth = this.deendayalChowkInfoList.getJuncMonth();
                    int juncYear = this.deendayalChowkInfoList.getJuncYear();

                    request.setAttribute("juncHr", juncHr);
                    request.setAttribute("juncMin", juncMin);
                    request.setAttribute("juncDat", juncDat);
                    request.setAttribute("juncMonth", juncMonth);
                    request.setAttribute("juncYear", juncYear);

                    request.setAttribute("functionNo", Integer.valueOf(functionNo));
                    request.setAttribute("junctionId", Integer.valueOf(junction_id));
                    request.setAttribute("program_version_no", Integer.valueOf(program_version_no));
                    request.setAttribute("fileNo", Integer.valueOf(fileNo));
                    request.setAttribute("junctionName", junctionName);
                    request.setAttribute("activity", activity);
                    request.setAttribute("sideNo", Integer.valueOf(side_no));
                    request.setAttribute("sideName", sideName);
                    request.setAttribute("side1Name", side1Name);
                    request.setAttribute("side2Name", side2Name);
                    request.setAttribute("side3Name", side3Name);
                    request.setAttribute("side4Name", side4Name);
                    request.setAttribute("side5Name", side5Name);

                    request.setAttribute("onTime", onTime);
                    request.setAttribute("offTime", offTime);
                    request.setAttribute("plan_no", plan_no);
                    request.setAttribute("mode", mode);

                    request.setAttribute("side1Time", Integer.valueOf(side1Time));
                    request.setAttribute("side2Time", Integer.valueOf(side2Time));
                    request.setAttribute("side3Time", Integer.valueOf(side3Time));
                    request.setAttribute("side4Time", Integer.valueOf(side4Time));
                    request.setAttribute("side1LeftStatus", Integer.valueOf(side1LeftStatus));
                    request.setAttribute("side1RightStatus", Integer.valueOf(side1RightStatus));
                    request.setAttribute("side1UpStatus", Integer.valueOf(side1UpStatus));
                    request.setAttribute("side1DownStatus", Integer.valueOf(side1DownStatus));
                    request.setAttribute("side2LeftStatus", Integer.valueOf(side2LeftStatus));
                    request.setAttribute("side2RightStatus", Integer.valueOf(side2RightStatus));
                    request.setAttribute("side2UpStatus", Integer.valueOf(side2UpStatus));
                    request.setAttribute("side2DownStatus", Integer.valueOf(side2DownStatus));
                    request.setAttribute("side3LeftStatus", Integer.valueOf(side3LeftStatus));
                    request.setAttribute("side3RightStatus", Integer.valueOf(side3RightStatus));
                    request.setAttribute("side3UpStatus", Integer.valueOf(side3UpStatus));
                    request.setAttribute("side3DownStatus", Integer.valueOf(side3DownStatus));
                    request.setAttribute("side4LeftStatus", Integer.valueOf(side4LeftStatus));
                    request.setAttribute("side4RightStatus", Integer.valueOf(side4RightStatus));
                    request.setAttribute("side4UpStatus", Integer.valueOf(side4UpStatus));
                    request.setAttribute("side4DownStatus", Integer.valueOf(side4DownStatus));
                    request.setAttribute("side4DownStatus", Integer.valueOf(side4DownStatus));
                    if(map_junction_id!=""){
                     request.getRequestDispatcher("/view/general/showMapOnTrafficSignal.jsp").forward(request, response);
                    }else{
                    request.getRequestDispatcher("ts_statusShower_view").forward(request, response);
                    }
                    return;
                } else {
                    String jSON_format = "Oops... No junction is active right now.";

                    request.setAttribute("message", jSON_format);
                    request.getRequestDispatcher("errorView").forward(request, response);
                    return;
                }
                }
                if(a == 7){
                //BeanUtils.copyProperties(planInfoList, ranitalInfoList);
                if (this.highCourtInfoList != null) {
                    int functionNo = this.highCourtInfoList.getFunction_no();
                    int junction_id = this.highCourtInfoList.getJunction_id();
                    int program_version_no = this.highCourtInfoList.getProgram_version_no();
                    int fileNo = this.highCourtInfoList.getFileNo();
                    int activity = this.highCourtInfoList.getActivity();
                    int side_no = this.highCourtInfoList.getSide_no();
                    int plan_no = this.highCourtInfoList.getPlan_no();
                    String mode = this.highCourtInfoList.getMode();
                    String junctionName = this.highCourtInfoList.getJunction_name();
                    String sideName = this.highCourtInfoList.getSideName();

                    String onTime = this.highCourtInfoList.getOnTime();
                    String offTime = this.highCourtInfoList.getOffTime();

                    String side1Name = this.highCourtInfoList.getSide1Name();
                    String side2Name = this.highCourtInfoList.getSide2Name();
                    String side3Name = this.highCourtInfoList.getSide3Name();
                    String side4Name = this.highCourtInfoList.getSide4Name();
                    String side5Name = this.highCourtInfoList.getSide5Name();

                    int side1Time = this.highCourtInfoList.getSide1_time();
                    int side2Time = this.highCourtInfoList.getSide2_time();
                    int side3Time = this.highCourtInfoList.getSide3_time();
                    int side4Time = this.highCourtInfoList.getSide4_time();
                    int side1LeftStatus = this.highCourtInfoList.getSide1_left_status();
                    int side1RightStatus = this.highCourtInfoList.getSide1_right_status();
                    int side1UpStatus = this.highCourtInfoList.getSide1_up_status();
                    int side1DownStatus = this.highCourtInfoList.getSide1_down_status();
                    int side2LeftStatus = this.highCourtInfoList.getSide2_left_status();
                    int side2RightStatus = this.highCourtInfoList.getSide2_right_status();
                    int side2UpStatus = this.highCourtInfoList.getSide2_up_status();
                    int side2DownStatus = this.highCourtInfoList.getSide2_down_status();
                    int side3LeftStatus = this.highCourtInfoList.getSide3_left_status();
                    int side3RightStatus = this.highCourtInfoList.getSide3_right_status();
                    int side3UpStatus = this.highCourtInfoList.getSide3_up_status();
                    int side3DownStatus = this.highCourtInfoList.getSide3_down_status();
                    int side4LeftStatus = this.highCourtInfoList.getSide4_left_status();
                    int side4RightStatus = this.highCourtInfoList.getSide4_right_status();
                    int side4UpStatus = this.highCourtInfoList.getSide4_up_status();
                    int side4DownStatus = this.highCourtInfoList.getSide4_down_status();

                    int juncHr = this.highCourtInfoList.getJuncHr();
                    int juncMin = this.highCourtInfoList.getJuncMin();
                    int juncDat = this.highCourtInfoList.getJuncDate();
                    int juncMonth = this.highCourtInfoList.getJuncMonth();
                    int juncYear = this.highCourtInfoList.getJuncYear();

                    request.setAttribute("juncHr", juncHr);
                    request.setAttribute("juncMin", juncMin);
                    request.setAttribute("juncDat", juncDat);
                    request.setAttribute("juncMonth", juncMonth);
                    request.setAttribute("juncYear", juncYear);

                    request.setAttribute("functionNo", Integer.valueOf(functionNo));
                    request.setAttribute("junctionId", Integer.valueOf(junction_id));
                    request.setAttribute("program_version_no", Integer.valueOf(program_version_no));
                    request.setAttribute("fileNo", Integer.valueOf(fileNo));
                    request.setAttribute("junctionName", junctionName);
                    request.setAttribute("activity", activity);
                    request.setAttribute("sideNo", Integer.valueOf(side_no));
                    request.setAttribute("sideName", sideName);
                    request.setAttribute("side1Name", side1Name);
                    request.setAttribute("side2Name", side2Name);
                    request.setAttribute("side3Name", side3Name);
                    request.setAttribute("side4Name", side4Name);
                    request.setAttribute("side5Name", side5Name);

                    request.setAttribute("onTime", onTime);
                    request.setAttribute("offTime", offTime);
                    request.setAttribute("plan_no", plan_no);
                    request.setAttribute("mode", mode);

                    request.setAttribute("side1Time", Integer.valueOf(side1Time));
                    request.setAttribute("side2Time", Integer.valueOf(side2Time));
                    request.setAttribute("side3Time", Integer.valueOf(side3Time));
                    request.setAttribute("side4Time", Integer.valueOf(side4Time));
                    request.setAttribute("side1LeftStatus", Integer.valueOf(side1LeftStatus));
                    request.setAttribute("side1RightStatus", Integer.valueOf(side1RightStatus));
                    request.setAttribute("side1UpStatus", Integer.valueOf(side1UpStatus));
                    request.setAttribute("side1DownStatus", Integer.valueOf(side1DownStatus));
                    request.setAttribute("side2LeftStatus", Integer.valueOf(side2LeftStatus));
                    request.setAttribute("side2RightStatus", Integer.valueOf(side2RightStatus));
                    request.setAttribute("side2UpStatus", Integer.valueOf(side2UpStatus));
                    request.setAttribute("side2DownStatus", Integer.valueOf(side2DownStatus));
                    request.setAttribute("side3LeftStatus", Integer.valueOf(side3LeftStatus));
                    request.setAttribute("side3RightStatus", Integer.valueOf(side3RightStatus));
                    request.setAttribute("side3UpStatus", Integer.valueOf(side3UpStatus));
                    request.setAttribute("side3DownStatus", Integer.valueOf(side3DownStatus));
                    request.setAttribute("side4LeftStatus", Integer.valueOf(side4LeftStatus));
                    request.setAttribute("side4RightStatus", Integer.valueOf(side4RightStatus));
                    request.setAttribute("side4UpStatus", Integer.valueOf(side4UpStatus));
                    request.setAttribute("side4DownStatus", Integer.valueOf(side4DownStatus));
                    request.setAttribute("side4DownStatus", Integer.valueOf(side4DownStatus));
                    if(map_junction_id!=""){
                     request.getRequestDispatcher("/view/general/showMapOnTrafficSignal.jsp").forward(request, response);
                    }else{
                    request.getRequestDispatcher("ts_statusShower_view").forward(request, response);
                    }
                    return;
                } else {
                    String jSON_format = "Oops... No junction is active right now.";

                    request.setAttribute("message", jSON_format);
                    request.getRequestDispatcher("errorView").forward(request, response);
                    return;
                }
                }
                if(a == 8){
                //BeanUtils.copyProperties(planInfoList, ranitalInfoList);
                if (this.bloomChowkInfoList != null) {
                    int functionNo = this.bloomChowkInfoList.getFunction_no();
                    int junction_id = this.bloomChowkInfoList.getJunction_id();
                    int program_version_no = this.bloomChowkInfoList.getProgram_version_no();
                    int fileNo = this.bloomChowkInfoList.getFileNo();
                    int activity = this.bloomChowkInfoList.getActivity();
                    int side_no = this.bloomChowkInfoList.getSide_no();
                    int plan_no = this.bloomChowkInfoList.getPlan_no();
                    String mode = this.bloomChowkInfoList.getMode();
                    String junctionName = this.bloomChowkInfoList.getJunction_name();
                    String sideName = this.bloomChowkInfoList.getSideName();

                    String onTime = this.bloomChowkInfoList.getOnTime();
                    String offTime = this.bloomChowkInfoList.getOffTime();

                    String side1Name = this.bloomChowkInfoList.getSide1Name();
                    String side2Name = this.bloomChowkInfoList.getSide2Name();
                    String side3Name = this.bloomChowkInfoList.getSide3Name();
                    String side4Name = this.bloomChowkInfoList.getSide4Name();
                    String side5Name = this.bloomChowkInfoList.getSide5Name();

                    int side1Time = this.bloomChowkInfoList.getSide1_time();
                    int side2Time = this.bloomChowkInfoList.getSide2_time();
                    int side3Time = this.bloomChowkInfoList.getSide3_time();
                    int side4Time = this.bloomChowkInfoList.getSide4_time();
                    int side1LeftStatus = this.bloomChowkInfoList.getSide1_left_status();
                    int side1RightStatus = this.bloomChowkInfoList.getSide1_right_status();
                    int side1UpStatus = this.bloomChowkInfoList.getSide1_up_status();
                    int side1DownStatus = this.bloomChowkInfoList.getSide1_down_status();
                    int side2LeftStatus = this.bloomChowkInfoList.getSide2_left_status();
                    int side2RightStatus = this.bloomChowkInfoList.getSide2_right_status();
                    int side2UpStatus = this.bloomChowkInfoList.getSide2_up_status();
                    int side2DownStatus = this.bloomChowkInfoList.getSide2_down_status();
                    int side3LeftStatus = this.bloomChowkInfoList.getSide3_left_status();
                    int side3RightStatus = this.bloomChowkInfoList.getSide3_right_status();
                    int side3UpStatus = this.bloomChowkInfoList.getSide3_up_status();
                    int side3DownStatus = this.bloomChowkInfoList.getSide3_down_status();
                    int side4LeftStatus = this.bloomChowkInfoList.getSide4_left_status();
                    int side4RightStatus = this.bloomChowkInfoList.getSide4_right_status();
                    int side4UpStatus = this.bloomChowkInfoList.getSide4_up_status();
                    int side4DownStatus = this.bloomChowkInfoList.getSide4_down_status();

                    int juncHr = this.bloomChowkInfoList.getJuncHr();
                    int juncMin = this.bloomChowkInfoList.getJuncMin();
                    int juncDat = this.bloomChowkInfoList.getJuncDate();
                    int juncMonth = this.bloomChowkInfoList.getJuncMonth();
                    int juncYear = this.bloomChowkInfoList.getJuncYear();

                    request.setAttribute("juncHr", juncHr);
                    request.setAttribute("juncMin", juncMin);
                    request.setAttribute("juncDat", juncDat);
                    request.setAttribute("juncMonth", juncMonth);
                    request.setAttribute("juncYear", juncYear);

                    request.setAttribute("functionNo", Integer.valueOf(functionNo));
                    request.setAttribute("junctionId", Integer.valueOf(junction_id));
                    request.setAttribute("program_version_no", Integer.valueOf(program_version_no));
                    request.setAttribute("fileNo", Integer.valueOf(fileNo));
                    request.setAttribute("junctionName", junctionName);
                    request.setAttribute("activity", activity);
                    request.setAttribute("sideNo", Integer.valueOf(side_no));
                    request.setAttribute("sideName", sideName);
                    request.setAttribute("side1Name", side1Name);
                    request.setAttribute("side2Name", side2Name);
                    request.setAttribute("side3Name", side3Name);
                    request.setAttribute("side4Name", side4Name);
                    request.setAttribute("side5Name", side5Name);

                    request.setAttribute("onTime", onTime);
                    request.setAttribute("offTime", offTime);
                    request.setAttribute("plan_no", plan_no);
                    request.setAttribute("mode", mode);

                    request.setAttribute("side1Time", Integer.valueOf(side1Time));
                    request.setAttribute("side2Time", Integer.valueOf(side2Time));
                    request.setAttribute("side3Time", Integer.valueOf(side3Time));
                    request.setAttribute("side4Time", Integer.valueOf(side4Time));
                    request.setAttribute("side1LeftStatus", Integer.valueOf(side1LeftStatus));
                    request.setAttribute("side1RightStatus", Integer.valueOf(side1RightStatus));
                    request.setAttribute("side1UpStatus", Integer.valueOf(side1UpStatus));
                    request.setAttribute("side1DownStatus", Integer.valueOf(side1DownStatus));
                    request.setAttribute("side2LeftStatus", Integer.valueOf(side2LeftStatus));
                    request.setAttribute("side2RightStatus", Integer.valueOf(side2RightStatus));
                    request.setAttribute("side2UpStatus", Integer.valueOf(side2UpStatus));
                    request.setAttribute("side2DownStatus", Integer.valueOf(side2DownStatus));
                    request.setAttribute("side3LeftStatus", Integer.valueOf(side3LeftStatus));
                    request.setAttribute("side3RightStatus", Integer.valueOf(side3RightStatus));
                    request.setAttribute("side3UpStatus", Integer.valueOf(side3UpStatus));
                    request.setAttribute("side3DownStatus", Integer.valueOf(side3DownStatus));
                    request.setAttribute("side4LeftStatus", Integer.valueOf(side4LeftStatus));
                    request.setAttribute("side4RightStatus", Integer.valueOf(side4RightStatus));
                    request.setAttribute("side4UpStatus", Integer.valueOf(side4UpStatus));
                    request.setAttribute("side4DownStatus", Integer.valueOf(side4DownStatus));
                    request.setAttribute("side4DownStatus", Integer.valueOf(side4DownStatus));
                    if(map_junction_id!=""){
                     request.getRequestDispatcher("/view/general/showMapOnTrafficSignal.jsp").forward(request, response);
                    }else{
                    request.getRequestDispatcher("ts_statusShower_view").forward(request, response);
                    }
                    return;
                } else {
                    String jSON_format = "Oops... No junction is active right now.";

                    request.setAttribute("message", jSON_format);
                    request.getRequestDispatcher("errorView").forward(request, response);
                    return;
                }
                }
                if(a == 12){
                //BeanUtils.copyProperties(planInfoList, ranitalInfoList);
                if (this.madanMahalInfoList != null) {
                    int functionNo = this.madanMahalInfoList.getFunction_no();
                    int junction_id = this.madanMahalInfoList.getJunction_id();
                    int program_version_no = this.madanMahalInfoList.getProgram_version_no();
                    int fileNo = this.madanMahalInfoList.getFileNo();
                    int activity = this.madanMahalInfoList.getActivity();
                    int side_no = this.madanMahalInfoList.getSide_no();
                    int plan_no = this.madanMahalInfoList.getPlan_no();
                    String mode = this.madanMahalInfoList.getMode();
                    String junctionName = this.madanMahalInfoList.getJunction_name();
                    String sideName = this.madanMahalInfoList.getSideName();

                    String onTime = this.madanMahalInfoList.getOnTime();
                    String offTime = this.madanMahalInfoList.getOffTime();

                    String side1Name = this.madanMahalInfoList.getSide1Name();
                    String side2Name = this.madanMahalInfoList.getSide2Name();
                    String side3Name = this.madanMahalInfoList.getSide3Name();
                    String side4Name = this.madanMahalInfoList.getSide4Name();
                    String side5Name = this.madanMahalInfoList.getSide5Name();

                    int side1Time = this.madanMahalInfoList.getSide1_time();
                    int side2Time = this.madanMahalInfoList.getSide2_time();
                    int side3Time = this.madanMahalInfoList.getSide3_time();
                    int side4Time = this.madanMahalInfoList.getSide4_time();
                    int side1LeftStatus = this.madanMahalInfoList.getSide1_left_status();
                    int side1RightStatus = this.madanMahalInfoList.getSide1_right_status();
                    int side1UpStatus = this.madanMahalInfoList.getSide1_up_status();
                    int side1DownStatus = this.madanMahalInfoList.getSide1_down_status();
                    int side2LeftStatus = this.madanMahalInfoList.getSide2_left_status();
                    int side2RightStatus = this.madanMahalInfoList.getSide2_right_status();
                    int side2UpStatus = this.madanMahalInfoList.getSide2_up_status();
                    int side2DownStatus = this.madanMahalInfoList.getSide2_down_status();
                    int side3LeftStatus = this.madanMahalInfoList.getSide3_left_status();
                    int side3RightStatus = this.madanMahalInfoList.getSide3_right_status();
                    int side3UpStatus = this.madanMahalInfoList.getSide3_up_status();
                    int side3DownStatus = this.madanMahalInfoList.getSide3_down_status();
                    int side4LeftStatus = this.madanMahalInfoList.getSide4_left_status();
                    int side4RightStatus = this.madanMahalInfoList.getSide4_right_status();
                    int side4UpStatus = this.madanMahalInfoList.getSide4_up_status();
                    int side4DownStatus = this.madanMahalInfoList.getSide4_down_status();

                    int juncHr = this.madanMahalInfoList.getJuncHr();
                    int juncMin = this.madanMahalInfoList.getJuncMin();
                    int juncDat = this.madanMahalInfoList.getJuncDate();
                    int juncMonth = this.madanMahalInfoList.getJuncMonth();
                    int juncYear = this.madanMahalInfoList.getJuncYear();

                    request.setAttribute("juncHr", juncHr);
                    request.setAttribute("juncMin", juncMin);
                    request.setAttribute("juncDat", juncDat);
                    request.setAttribute("juncMonth", juncMonth);
                    request.setAttribute("juncYear", juncYear);

                    request.setAttribute("functionNo", Integer.valueOf(functionNo));
                    request.setAttribute("junctionId", Integer.valueOf(junction_id));
                    request.setAttribute("program_version_no", Integer.valueOf(program_version_no));
                    request.setAttribute("fileNo", Integer.valueOf(fileNo));
                    request.setAttribute("junctionName", junctionName);
                    request.setAttribute("activity", activity);
                    request.setAttribute("sideNo", Integer.valueOf(side_no));
                    request.setAttribute("sideName", sideName);
                    request.setAttribute("side1Name", side1Name);
                    request.setAttribute("side2Name", side2Name);
                    request.setAttribute("side3Name", side3Name);
                    request.setAttribute("side4Name", side4Name);
                    request.setAttribute("side5Name", side5Name);

                    request.setAttribute("onTime", onTime);
                    request.setAttribute("offTime", offTime);
                    request.setAttribute("plan_no", plan_no);
                    request.setAttribute("mode", mode);

                    request.setAttribute("side1Time", Integer.valueOf(side1Time));
                    request.setAttribute("side2Time", Integer.valueOf(side2Time));
                    request.setAttribute("side3Time", Integer.valueOf(side3Time));
                    request.setAttribute("side4Time", Integer.valueOf(side4Time));
                    request.setAttribute("side1LeftStatus", Integer.valueOf(side1LeftStatus));
                    request.setAttribute("side1RightStatus", Integer.valueOf(side1RightStatus));
                    request.setAttribute("side1UpStatus", Integer.valueOf(side1UpStatus));
                    request.setAttribute("side1DownStatus", Integer.valueOf(side1DownStatus));
                    request.setAttribute("side2LeftStatus", Integer.valueOf(side2LeftStatus));
                    request.setAttribute("side2RightStatus", Integer.valueOf(side2RightStatus));
                    request.setAttribute("side2UpStatus", Integer.valueOf(side2UpStatus));
                    request.setAttribute("side2DownStatus", Integer.valueOf(side2DownStatus));
                    request.setAttribute("side3LeftStatus", Integer.valueOf(side3LeftStatus));
                    request.setAttribute("side3RightStatus", Integer.valueOf(side3RightStatus));
                    request.setAttribute("side3UpStatus", Integer.valueOf(side3UpStatus));
                    request.setAttribute("side3DownStatus", Integer.valueOf(side3DownStatus));
                    request.setAttribute("side4LeftStatus", Integer.valueOf(side4LeftStatus));
                    request.setAttribute("side4RightStatus", Integer.valueOf(side4RightStatus));
                    request.setAttribute("side4UpStatus", Integer.valueOf(side4UpStatus));
                    request.setAttribute("side4DownStatus", Integer.valueOf(side4DownStatus));
                    request.setAttribute("side4DownStatus", Integer.valueOf(side4DownStatus));
                    if(map_junction_id!=""){
                     request.getRequestDispatcher("/view/general/showMapOnTrafficSignal.jsp").forward(request, response);
                    }else{
                    request.getRequestDispatcher("ts_statusShower_view").forward(request, response);
                    }
                    return;
                } else {
                    String jSON_format = "Oops... No junction is active right now.";

                    request.setAttribute("message", jSON_format);
                    request.getRequestDispatcher("errorView").forward(request, response);
                    return;
                }
                }
                
                if(a == 14){
                //BeanUtils.copyProperties(planInfoList, ranitalInfoList);
                if (this.gohalPurInfoList != null) {
                    int functionNo = this.gohalPurInfoList.getFunction_no();
                    int junction_id = this.gohalPurInfoList.getJunction_id();
                    int program_version_no = this.gohalPurInfoList.getProgram_version_no();
                    int fileNo = this.gohalPurInfoList.getFileNo();
                    int activity = this.gohalPurInfoList.getActivity();
                    int side_no = this.gohalPurInfoList.getSide_no();
                    int plan_no = this.gohalPurInfoList.getPlan_no();
                    String mode = this.gohalPurInfoList.getMode();
                    String junctionName = this.gohalPurInfoList.getJunction_name();
                    String sideName = this.gohalPurInfoList.getSideName();

                    String onTime = this.gohalPurInfoList.getOnTime();
                    String offTime = this.gohalPurInfoList.getOffTime();

                    String side1Name = this.gohalPurInfoList.getSide1Name();
                    String side2Name = this.gohalPurInfoList.getSide2Name();
                    String side3Name = this.gohalPurInfoList.getSide3Name();
                    String side4Name = this.gohalPurInfoList.getSide4Name();
                    String side5Name = this.gohalPurInfoList.getSide5Name();

                    int side1Time = this.gohalPurInfoList.getSide1_time();
                    int side2Time = this.gohalPurInfoList.getSide2_time();
                    int side3Time = this.gohalPurInfoList.getSide3_time();
                    int side4Time = this.gohalPurInfoList.getSide4_time();
                    int side1LeftStatus = this.gohalPurInfoList.getSide1_left_status();
                    int side1RightStatus = this.gohalPurInfoList.getSide1_right_status();
                    int side1UpStatus = this.gohalPurInfoList.getSide1_up_status();
                    int side1DownStatus = this.gohalPurInfoList.getSide1_down_status();
                    int side2LeftStatus = this.gohalPurInfoList.getSide2_left_status();
                    int side2RightStatus = this.gohalPurInfoList.getSide2_right_status();
                    int side2UpStatus = this.gohalPurInfoList.getSide2_up_status();
                    int side2DownStatus = this.gohalPurInfoList.getSide2_down_status();
                    int side3LeftStatus = this.gohalPurInfoList.getSide3_left_status();
                    int side3RightStatus = this.gohalPurInfoList.getSide3_right_status();
                    int side3UpStatus = this.gohalPurInfoList.getSide3_up_status();
                    int side3DownStatus = this.gohalPurInfoList.getSide3_down_status();
                    int side4LeftStatus = this.gohalPurInfoList.getSide4_left_status();
                    int side4RightStatus = this.gohalPurInfoList.getSide4_right_status();
                    int side4UpStatus = this.gohalPurInfoList.getSide4_up_status();
                    int side4DownStatus = this.gohalPurInfoList.getSide4_down_status();

                    int juncHr = this.gohalPurInfoList.getJuncHr();
                    int juncMin = this.gohalPurInfoList.getJuncMin();
                    int juncDat = this.gohalPurInfoList.getJuncDate();
                    int juncMonth = this.gohalPurInfoList.getJuncMonth();
                    int juncYear = this.gohalPurInfoList.getJuncYear();

                    request.setAttribute("juncHr", juncHr);
                    request.setAttribute("juncMin", juncMin);
                    request.setAttribute("juncDat", juncDat);
                    request.setAttribute("juncMonth", juncMonth);
                    request.setAttribute("juncYear", juncYear);

                    request.setAttribute("functionNo", Integer.valueOf(functionNo));
                    request.setAttribute("junctionId", Integer.valueOf(junction_id));
                    request.setAttribute("program_version_no", Integer.valueOf(program_version_no));
                    request.setAttribute("fileNo", Integer.valueOf(fileNo));
                    request.setAttribute("junctionName", junctionName);
                    request.setAttribute("activity", activity);
                    request.setAttribute("sideNo", Integer.valueOf(side_no));
                    request.setAttribute("sideName", sideName);
                    request.setAttribute("side1Name", side1Name);
                    request.setAttribute("side2Name", side2Name);
                    request.setAttribute("side3Name", side3Name);
                    request.setAttribute("side4Name", side4Name);
                    request.setAttribute("side5Name", side5Name);

                    request.setAttribute("onTime", onTime);
                    request.setAttribute("offTime", offTime);
                    request.setAttribute("plan_no", plan_no);
                    request.setAttribute("mode", mode);

                    request.setAttribute("side1Time", Integer.valueOf(side1Time));
                    request.setAttribute("side2Time", Integer.valueOf(side2Time));
                    request.setAttribute("side3Time", Integer.valueOf(side3Time));
                    request.setAttribute("side4Time", Integer.valueOf(side4Time));
                    request.setAttribute("side1LeftStatus", Integer.valueOf(side1LeftStatus));
                    request.setAttribute("side1RightStatus", Integer.valueOf(side1RightStatus));
                    request.setAttribute("side1UpStatus", Integer.valueOf(side1UpStatus));
                    request.setAttribute("side1DownStatus", Integer.valueOf(side1DownStatus));
                    request.setAttribute("side2LeftStatus", Integer.valueOf(side2LeftStatus));
                    request.setAttribute("side2RightStatus", Integer.valueOf(side2RightStatus));
                    request.setAttribute("side2UpStatus", Integer.valueOf(side2UpStatus));
                    request.setAttribute("side2DownStatus", Integer.valueOf(side2DownStatus));
                    request.setAttribute("side3LeftStatus", Integer.valueOf(side3LeftStatus));
                    request.setAttribute("side3RightStatus", Integer.valueOf(side3RightStatus));
                    request.setAttribute("side3UpStatus", Integer.valueOf(side3UpStatus));
                    request.setAttribute("side3DownStatus", Integer.valueOf(side3DownStatus));
                    request.setAttribute("side4LeftStatus", Integer.valueOf(side4LeftStatus));
                    request.setAttribute("side4RightStatus", Integer.valueOf(side4RightStatus));
                    request.setAttribute("side4UpStatus", Integer.valueOf(side4UpStatus));
                    request.setAttribute("side4DownStatus", Integer.valueOf(side4DownStatus));
                    request.setAttribute("side4DownStatus", Integer.valueOf(side4DownStatus));
                    if(map_junction_id!=""){
                     request.getRequestDispatcher("/view/general/showMapOnTrafficSignal.jsp").forward(request, response);
                    }else{
                    request.getRequestDispatcher("ts_statusShower_view").forward(request, response);
                    }
                    return;
                } else {
                    String jSON_format = "Oops... No junction is active right now.";

                    request.setAttribute("message", jSON_format);
                    request.getRequestDispatcher("errorView").forward(request, response);
                    return;
                }
                }
                
                else{
                String jSON_format = "Oops... No junction is active right now.";

                    request.setAttribute("message", jSON_format);
                    request.getRequestDispatcher("errorView").forward(request, response);
                    return;
                }

            }
        } catch (Exception e) {
            System.out.println("TS_StatusShowerController- doPostError :" + e);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    public String getCurrentTime() {
        DateFormat df = DateFormat.getTimeInstance(2);
        return df.format(new Date());
    }

    public ClientResponder getClientResponder() {
        return this.clientResponder;
    }

    public void setClientResponder(ClientResponder clientResponder) {
        this.clientResponder = clientResponder;
    }
}
