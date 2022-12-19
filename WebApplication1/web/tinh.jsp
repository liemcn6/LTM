<%-- 
    Document   : tinh
    Created on : Nov 22, 2022, 3:03:15 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form action="tinh" method="post">
            enter r : <input type="text" name="r" /><br/>
            <input type="submit" value="submit" />
        </form>
        <% 
        if(request.getAttribute("dt") != null){
            String s = (String)request.getAttribute("dt");
            %>
            <h2>Dien tich: <%= s %></h2>
            <script type="text/javascript">
            alert("Đăng kí thành công!");
           </script>
           <%
        }else{
            %>
            <script type="text/javascript">
             alert("Lỗi đăng kí!");
             //history.back();
            </script>
            <%
}
        %>
    </body>
</html>
