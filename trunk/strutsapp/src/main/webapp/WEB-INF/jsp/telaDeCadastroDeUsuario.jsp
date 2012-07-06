<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@taglib uri="http://struts.apache.org/tags-html-el" prefix="html" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<head>

<script type="text/javascript">

function definirMetodo(metodo) {
  var paramMetodo = document.getElementById("metodo");
  paramMetodo.value = metodo;
  document.forms[0].submit();
}

</script>

<meta http-equiv="Content-Type" 
  content="text/html; charset=ISO-8859-1" />
<title>Tela de cadastro de usuário</title>
<style type="text/css">
<!--
body {
	font: 100% Verdana, Arial, Helvetica, sans-serif;
	background: #666666;
	margin: 0; /* it's good practice to zero the margin and padding of the body element to account for differing browser defaults */
	padding: 0;
	text-align: center; /* this centers the container in IE 5* browsers. The text is then set to the left aligned default in the #container selector */
	color: #000000;
}

/* Tips for Elastic layouts 
1. Since the elastic layouts overall sizing is based on the user's default fonts size, they are more unpredictable. Used correctly, they are also more accessible for those that need larger fonts size since the line length remains proportionate.
2. Sizing of divs in this layout are based on the 100% font size in the body element. If you decrease the text size overall by using a font-size: 80% on the body element or the #container, remember that the entire layout will downsize proportionately. You may want to increase the widths of the various divs to compensate for this.
3. If font sizing is changed in differing amounts on each div instead of on the overall design (ie: #sidebar1 is given a 70% font size and #mainContent is given an 85% font size), this will proportionately change each of the divs overall size. You may want to adjust based on your final font sizing.
*/
.oneColElsCtrHdr #container {
	width: 46em;  /* this width will create a container that will fit in an 800px browser window if text is left at browser default font sizes */
	background: #FFFFFF;
	margin: 0 auto; /* the auto margins (in conjunction with a width) center the page */
	border: 1px solid #000000;
	text-align: left; /* this overrides the text-align: center on the body element. */
}
.oneColElsCtrHdr #header { 
	background: #DDDDDD; 
	padding: 0 10px 0 20px;  /* this padding matches the left alignment of the elements in the divs that appear beneath it. If an image is used in the #header instead of text, you may want to remove the padding. */
} 
.oneColElsCtrHdr #header h1 {
	margin: 0; /* zeroing the margin of the last element in the #header div will avoid margin collapse - an unexplainable space between divs. If the div has a border around it, this is not necessary as that also avoids the margin collapse */
	padding: 10px 0; /* using padding instead of margin will allow you to keep the element away from the edges of the div */
}
.oneColElsCtrHdr #mainContent {
	padding: 0 20px; /* remember that padding is the space inside the div box and margin is the space outside the div box */
	background: #FFFFFF;
}
.oneColElsCtrHdr #footer { 
	padding: 0 10px; /* this padding matches the left alignment of the elements in the divs that appear above it. */
	background:#DDDDDD;
} 
.oneColElsCtrHdr #footer p {
	margin: 0; /* zeroing the margins of the first element in the footer will avoid the possibility of margin collapse - a space between divs */
	padding: 10px 0; /* padding on this element will create space, just as the the margin would have, without the margin collapse issue */
}
-->
</style></head>

<body class="oneColElsCtrHdr">

<html:form action="/telaDeCadastroDeUsuario">

<html:hidden property="metodo" styleId="metodo"/>
<html:hidden property="id"/>

<div id="container">

  <div id="header">
    <a href="#">Usuários</a>
    <!-- end #header --></div>
<br/>
  <div id="mainContent">
<table width="90%" border="1">
  <tr>
    <th scope="row">* Nome:</th>
    <td>
      <html:text property="nome" size="60"
        maxlength="255"></html:text>
    </td>
  </tr>
  <tr>
    <th scope="row">* Login:</th>
    <td>
      <html:text property="login" size="6"
        maxlength="6"></html:text>
    </td>
  </tr>
  <tr>
    <th scope="row">* E-mail:</th>
    <td>
      <html:text property="email" size="32"
        maxlength="64"></html:text>
    </td>
  </tr>
  <tr>
    <td colspan="2">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="2" align="center">
      <html:link href="#"
        onclick="definirMetodo('salvar');">
        Salvar
      </html:link>
      &nbsp;
      <html:link page="/telaDeGestaoDeUsuarios.do">
        Cancelar
      </html:link>
    </td>
  </tr>
</table>
<!-- end #mainContent --></div>
<br/>
  <div id="footer">
    <p align="right">Belo Horizonte, 01/12/2007 8:30.</p>
    <!-- end #footer --></div>
<!-- end #container --></div>

</html:form>

</body>
</html>
