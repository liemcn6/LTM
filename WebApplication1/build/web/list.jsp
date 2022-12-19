<%-- 
    Document   : list.jsp
    Created on : Nov 22, 2022, 3:42:41 PM
    Author     : DELL
--%>

<%@page import="model.Student"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>List of Students</h1>
        <table border="1px">
            <tr>
                <th>ID</th>
                <th>name</th>
                <th>dob</th>
                <th>gt</th>
            </tr>
            <% 
                List<Student> list =(List<Student>) request.getAttribute("data");
                for(Student i : list){
                    %>
                    <tr>
                        <td><%= i.getId() %></td>
                        <td><%= i.getName() %></td>
                        <td><%= i.getDob() %></td>
                        <td><%= (i.isGender()? "Nam" : "Nu") %></td>
                    </tr>
                    <%
                }
            %>
        </table>
    </body>
</html>
