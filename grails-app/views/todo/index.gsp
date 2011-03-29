<%@page import="com.hk.util.FileLine"%>
<%@page import="org.junit.internal.matchers.CombinableMatcher"%>
<html>
<head>
 <TITLE>UTD/${srchstr}</TITLE>
 <meta name="layout" content="main"></meta>
<script type="text/javascript">


function postautocomplete (s)
{
	//r = "hihi"
	//alert("in postautocomplete3r:" + r);
	//alert("in postautocomplete3s:" + s);
	 previousInnerHTML = document.getElementById('autocompletetgt').innerHTML;
//alert("0")
	 document.getElementById('autocompletetgt').innerHTML = "enhanced autocomplete:" + previousInnerHTML;
//alert("1")

//	 previousInnerHTML = document.getElementById('autotarget2').innerHTML;
//alert("a")
	 document.getElementById('autotarget2').innerHTML = "enhanced autocomplete:" + previousInnerHTML;
//alert("b")

	 //previousInnerHTML2 = document.getElementById('hkhkhk').innerHTML;
//alert("2")
	 //alert ("previousInnerHTML:" + previousInnerHTML2.length);
	 //document.getElementById('hkhkhk').value = "enhanced autocomplete2:" + previousInnerHTML2;
	 //alert ("auto2L:" + document.getElementById('autotarget2'));
	 //document.getElementById('autotarget2').text = "enhanced autocomplete2:" + previousInnerHTML2;
	 	 
}
</script> 
</head>
 <body bgcolor="FFFFFF">
<%--<script>
alert("hi beth");
</script>--%>
<%--  ===== HEADER AND SEARCH FORM / TEXT BOX ====== --%>
<font color=black face="Arial"> 
<div class="form"><%--<formset>--%> <g:form action="index">
	<label for="userId"></label>
	<g:textField size="240"  name="srchstr"
		value="${srchstr}" />
<br>		

<g:textArea name="autotarget2" value="text area for editing and autocomp testing" rows="1" cols="170"  onclick="this.focus();this.select()"/>

<br>


<input id='userinputauto' type="text" size="25" value="autocomplete test tag substrings type here" 
	onkeyup="${
		remoteFunction (action:'autocompleteSearch',
		update:[success:'autocompletetgt', failure:'autocompletetgt'], 
		params:'\'autocomp=\' + this.value', 
		onComplete:'postautocomplete();')} "> 
			
<div id='autocompletetgt' >
</div>

	<br>
	<g:submitButton name="search" value="Do" /><%--
	word order close
	--%><g:checkBox name="cbword" value="${cbword}" /> word 	<g:checkBox name="cborder" value="${cborder}" /> order<g:checkBox name="cbclose" value="${cbclose}" /> close    <g:textField size="1" name="maxAge" value="${maxAge}" />&nbsp;old
	<auth:logoutLink success="[controller:'home', action:'newUser']"
    error="[controller:'userProfile', action:'error']">&nbsp;${user1} logoff</auth:logoutLink>
</g:form> <%--</formset>--%></div>

<g:if test="${flash.message}">
FLASH:	${fqFileName}
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