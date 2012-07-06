<%@taglib uri="http://struts.apache.org/tags-html-el" prefix="html"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="tagscurso"%>

<script type="text/javascript">

function definirMetodo(metodo) {
  var paramMetodo = document.getElementById("metodo");
  paramMetodo.value = metodo;
  document.forms[0].submit();
}

</script>

<html:form action="/telaDeGestaoDeUsuarios">

	<html:hidden property="metodo" styleId="metodo" />
	<html:hidden property="id" />

  <tagscurso:painelPesquisa>
    <tr>
			<td>Login:</td>
			<td><html:text property="login" size="6" maxlength="6"></html:text>
			</td>
		</tr>
		<tr>
			<td>Nome:</td>
			<td><html:text property="nome" size="60" maxlength="255"></html:text>
			</td>
		</tr>
  </tagscurso:painelPesquisa>

	<br />
	<table width="90%" border="0" align="center">
		<tr>
			<td colspan="3"><html:link
				page="/telaDeCadastroDeUsuario.do?metodo=inserir">
        Inserir
      </html:link></td>
		</tr>
	</table>
	<table width="90%" border="1" align="center">
		<tr>
			<th scope="col">Login</th>
			<th scope="col">Nome</th>
			<th scope="col">E-mail</th>
			<td>&nbsp;</td>
		</tr>
		<c:forEach items="${usuarios}" var="usuario">
			<tr>
				<td>${usuario.login}</td>
				<td>${usuario.nome}</td>
				<td>${usuario.email}</td>
				<td><html:link
					page="/telaDeCadastroDeUsuario.do?metodo=alterar&id=${usuario.id}">
          Alterar
        </html:link> <html:link
					page="/telaDeGestaoDeUsuarios.do?metodo=excluir&id=${usuario.id}">
          Excluir
        </html:link></td>
			</tr>
		</c:forEach>
	</table>
	
</html:form>