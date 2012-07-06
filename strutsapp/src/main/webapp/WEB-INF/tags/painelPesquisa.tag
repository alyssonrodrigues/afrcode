<%@ tag display-name="painelPesquisa" 
  description="Tag para exibiÃ§Ã£o de um painel de pesquisa."  %>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" 
  prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-html-el" 
  prefix="html" %>

<%@ attribute name="url" 
  description="URL associada ao comando de pesquisa" 
  required="false" %>
<%@ attribute name="rotuloPesquisar" 
  description="Rótulo associado ao comando de pesquisa" 
  required="false" %>
<%@ attribute name="funcaoJavascript" 
  description="Função Javascript associada ao evento onclick do comando de pesquisa" 
  required="false" %>

<c:if test="${empty url}">
  <c:set var="url" value="#"/>
</c:if>
<c:if test="${empty rotuloPesquisar}">
  <c:set var="rotuloPesquisar" value="Pesquisar"/>
</c:if>
<c:if test="${empty funcaoJavascript}">
  <c:set var="funcaoJavascript" value="executarPesquisa()"/>
</c:if>

<script type="text/javascript">
function executarPesquisa() {
  var elemMetodo = document.getElementById("metodo");
  if (null != elemMetodo) {
    elemMetodo.value = "pesquisar";
  }
  document.forms[0].submit();
}
</script>

<table width="60%" border="1" align="center">
  <%-- Chamada ao corpo do painel de pesquisa. --%>
  <jsp:doBody />
  <%-- InclusÃ£o do comando de pesquisa. --%>
  <tr><td colspan="2" align="center">
      <html:link href="${url}" onclick="${funcaoJavascript};">
          ${rotuloPesquisar}
      </html:link>
  </td></tr>
</table>