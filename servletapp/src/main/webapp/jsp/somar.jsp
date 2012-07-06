<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Insert title here</title>
</head>
<body>

<form action="/servlet/somar.html">
<input type="text" id="valorA" name="valorA" /><br/>
<input type="text" id="valorB" name="valorB" /><br/>
<a href="#" onclick="document.forms[0].submit();">Somar</a>
</form>

<%
  String total = request.getParameter("resultado");
  if (total != null)
  {
  	out.println("Resultado:" + total);
  }
%>

</body>
</html>
