<%@page import="com.hk.util.FileLine"%>
<%@page import="org.junit.internal.matchers.CombinableMatcher"%>
<html>
<head>
 <TITLE>UTD/${srchstr}</TITLE>
 <meta name="layout" content="main"></meta>
 
</head>
 <body bgcolor="FFFFFF">

<%--  ===== HEADER AND SEARCH FORM / TEXT BOX ====== --%>

<font color=black face="Arial"> 
<div class="form"><%--<formset>--%> <g:form action="index">
	<label for="userId"></label>
	<g:textField size="200" rows="3" cols="300" name="srchstr"
		value="${srchstr}" />
		
<br>		
<input type="text" size="25" value="autocomplete test" onkeyup		=
   "${remoteFunction(action:'autocompleteSearch',
   update:[success:'autocomplete', failure:'autocomplete'], 
   params:'\'autocomp=\' + this.value' )}"> 	
   
   <div id='autocomplete'>
   </div>
   	
		
	<br>
	<g:submitButton name="search" value="Do" />
	<g:checkBox name="cbword" value="${cbword}" /> word
	<g:checkBox name="cborder" value="${cborder}" /> order
	<g:checkBox name="cbclose" value="${cbclose}" /> close
    <g:textField size="1" name="maxAge" value="${maxAge}" />&nbsp;old
	<auth:logoutLink success="[controller:'home', action:'newUser']"
    error="[controller:'userProfile', action:'error']">&nbsp;${user1} logoff</auth:logoutLink>
</g:form> <%--</formset>--%></div>

<g:if test="${flash.message}">
	${fqFileName}
</g:if>


<script><%--
alert($ == jQuery)--%>
</script>



<%--  ===== LIST OUTPUT HERE ====== --%>
<table>
	<tbody>
		<g:each in="${alFileLines}" status="i" var="fl">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">


				<td><g:if test="${maxAge.contains('+')}">
					<font size=-1 color="green"> ${ fl.date} </font>
				</g:if></td>

				<%--REVERSE COUNTER--%>
				<td><font color="purple" size=-1>  ${fl.ageLetter} </font></td>

				<td><g:if test="${maxAge.contains('+')}">
				<td><font color="blue" size=-1>  ${(i+1).toString()}
				</g:if>


				</font></td>
				<td><g:checkBox name="cbword" value="${cbword}" /></td>
					<g:if test="${maxAge.contains('+')}">
						<td><SELECT NAME="gourl">
						<OPTION VALUE="def">
						<OPTION VALUE="del">del
						<OPTION VALUE="touch">upd
					</g:if></td>
				</SELECT></td>


				<g:if test="${maxAge.contains('+')}">
					<font size=-1 color="green"> ${fl.fileLineNumThisLine1Based} </font> : 
				</g:if></td>
				 
				<td><font color=${ (i % 2) ==0 ? "grey" : "black" }>&nbsp;
				${fl.lineMinusDateStr} </font></td>
				<%--<g:createLink url="[controller:'milesAccount', action:'generateTooltip', params:[id:${it.id}, extra:'extra']]" />--%>

			</tr>
		</g:each>
	</tbody>
</table>
</div>
</font>



</body>
</html>