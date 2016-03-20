<%-- 
    Document   : Elements
    Created on : 21.03.2016, 0:02:23
    Author     : Piotr
--%>

<%@page import="java.util.List"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="palic.dao.ListElementsRemoteStatefull"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%!
    private static ListElementsRemoteStatefull values;

    public void init() {
        try {
            InitialContext ic = new InitialContext();
            values = (ListElementsRemoteStatefull)ic.lookup("java:global/EJBLab1/ListElementsStateFullDAO/");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
%>

<%
    if(request.getParameter("addNumber")!=null){
        int element = Integer.parseInt(request.getParameter("t1"));
        values.addElements(element);                
    }
    if(request.getParameter("removeNumber") != null)
    {
        int element = Integer.parseInt(request.getParameter("t1"));
        values.removeElement(element);
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
            <input type="submit" value="Remove" name="removeNumber"><br/>
            
            <%
                if(values!=null){
                    List<Integer> numbers = values.getAllElements();
                    for(int value : numbers){
                        out.println("<br>" + value);
                    }
                    out.println("<br> Size = " + numbers.size());
                }
                %>
            
        </form>
    </body>
</html>
