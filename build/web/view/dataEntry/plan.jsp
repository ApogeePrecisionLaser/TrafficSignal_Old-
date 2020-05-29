<%-- 
    Document   : plan
    Created on : Aug 14, 2012, 4:44:30 PM
    Author     : Shruti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html><head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <title> Add/Remove dynamic rows in HTML table </title>
        <script language="javascript">
            function addRow(tableID) {

                var table = document.getElementById(tableID);

                var rowCount = table.rows.length;
                alert(rowCount);
                var row = table.insertRow(rowCount);

                var cell1 = row.insertCell(0);
                var element1 = document.createElement("input");
                element1.type = "checkbox";
                cell1.appendChild(element1);

                var cell2 = row.insertCell(1);
                cell2.innerHTML = rowCount;

                var cell3 = row.insertCell(2);
                var element2 = document.createElement("input");
                element2.type = "text";
                cell3.appendChild(element2);


            }

            function deleteRow(tableID) {
                try {
                    var table = document.getElementById(tableID);
                    var rowCount = table.rows.length;
                    alert(rowCount);
                    for(var i=0; i<rowCount; i++) {
                            
                        var row = table.rows[i];
                        alert(row);
                        var chkbox = row.cells[0].childNodes[0];
                        alert(chkbox);
                        if(null != chkbox && true == chkbox.checked) {
                            table.deleteRow(i);
                            rowCount--;
                            i--;
                        }
                    }
                }catch(e) {
                    alert(e);
                }
            }

        </script>
    </head>
    <body>
        <input value="Add Row" onclick="addRow('dataTable')" type="button">

        <input value="Delete Row" onclick="deleteRow('dataTable')" type="button">

        <table id="dataTable" style="border-collapse: collapse;" border="1" width="350px">
            <tbody>
                <tr>
                    <th>Select</th>
                    <th>Sr. No.</th>
                    <th>Value</th>
                </tr>
                <tr>
                    <td><input name="chk" type="checkbox"></td>
                    <td> 1 </td>
                    <td> <input type="text"> </td>
                </tr>
            </tbody>
        </table>


    </body></html>