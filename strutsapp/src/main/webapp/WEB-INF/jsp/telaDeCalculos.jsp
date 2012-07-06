<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://struts.apache.org/tags-html-el" 
  prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" 
  prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script type="text/javascript">

function definirMetodo(metodo)
{
  var paramMetodo = 
    document.getElementById("metodo");
  paramMetodo.value = metodo;
}

</script>

</head>
<body>

<html:form action="/telaDeCalculos">

  <html:hidden property="metodo" styleId="metodo"/>

	<html:text property="valorA" size="10" maxlength="10"></html:text>
	<html:errors property="valorA"/>
	<br />
	<html:text property="valorB" size="10" maxlength="10"></html:text>
	<html:errors property="valorB"/>
	<br/>

	<html:submit value="Somar" 
	  onclick="definirMetodo('somar');"></html:submit>
  <html:submit value="Subtrair" 
    onclick="definirMetodo('subtrair');"></html:submit>
  <html:submit value="Multiplicar" 
    onclick="definirMetodo('multiplicar');"></html:submit>
  <html:submit value="Dividir" 
    onclick="definirMetodo('dividir');"></html:submit>
<br/>
Resultado: <c:out value="${form.resultado}"></c:out>
<br/>
Resultado: ${form.resultado}

</html:form>

</body>
</html>