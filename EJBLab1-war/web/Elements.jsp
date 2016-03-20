<%-- 
    Document   : Elements
    Created on : 21.03.2016, 0:02:23
    Author     : Piotr
--%>

<%@page import="javax.naming.InitialContext"%>
<%@page import="palic.dao.ListElementsRemoteStatefull"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%!
    private static ListElementsRemoteStatefull values;

    public void init() {
        try {
            InitialContext ic = new InitialContext();
            values = (ListElementsRemoteStatefull)ic.lookup("java:global/EJBLab1/ListElementsStateFullDAO");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Welcome</h1>
        <form name="form1" method="post">
            <input type="text" name="t1"><br/>
            <input type="submit" value="Add" name="addNumber"><br/>
            <input type="submit" value="Remove" name="RemoveNumber"><br/>
        </form>
    </body>
</html>
