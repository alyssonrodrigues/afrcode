<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form action="/strutsapp/helloWorld.do">

<input type="text" name="nome"
  id="nome" />
<input type="submit" value="Enviar" />
<br/>

<% 
  String nome = request.getParameter("nome");
  if (nome != null && nome.trim().length() != 0) {
	  out.println("Bem vindo " + nome + "!");
  }
%>
</form>

</body>
</html>