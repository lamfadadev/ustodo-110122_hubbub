<%@page import="com.hk.util.FileLine"%>
<%@page import="org.junit.internal.matchers.CombinableMatcher"%>
<html>
<head>
<TITLE>UTD/${srchstr}
</TITLE>
<meta name="layout" content="main"></meta>
<script type="text/javascript">


function postautocomplete (s)
{
	//alert("Aasassa");
	//r = "hihi"
	//alert("in postautocomplete3r:" + r);
	 //autotgtdivHTMLpre = document.getElementById('autotgtdiv').innerHTML;
	 //document.getElementById('listoutput').innerHTML = ""; // clear 
	var a = document.getElementById('userinputauto').value;
	//def a = document.getElementById('srchstr2').value;
	// alert("success")
	 
	 var al=a.length;
	 var last_char=a.charAt(al-1)
	 if (last_char != ' ')
	{	
		 document.getElementById('listoutput').innerHTML = ""; // clear 
			//Console.Writeln("testing to console")//alert("in here")
		 //document.getElementById('autotgtdivintable').innerHTML = "in here"; // clear 
	}
	 else
		 {
		 //document.getElementById('autotgtdivintable').innerHTML = "not in here"; // clear 
		 }


//document.getElementById('autotgtdiv').innerHTML = "ready for action" + autotgtdivHTMLpre;
	 //document.getElementById('autotgtdivintable').innerHTML = "ready for action2" + autotgtdivHTMLpre;
		 
	 
	 //document.getElementById('autotgttextarea').innerHTML = "enhanced autocomplete:" + autotgtdivHTMLpre;
	//alert("b")
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

	<%-- ========== AUTOTGTDIV --%>
	<br>

	<table>
		<tr>
			<td>
			<table>
				<tr>
					<%-- ========== BUTTON AND AJAX FIELD -- INPUT REMOTE FUNCTION AUTOCOMPLETE SOURCE --%>
					<td><font size=-2> <g:submitButton name="search"
						value="Do" /></font>
					</td>
					<td><input name='srchstr2' id='userinputauto' type="text"
						size="125" value="${srchstr}"
						onkeyup="${
							remoteFunction (action:'autocompleteSearch',
							update:[success:'autotgtdiv', failure:'autotgtdiv'], 
							params:'\'autocomp=\' + this.value', 
							onComplete:'postautocomplete();')} ">
					</td>
				</tr>
				<tr>
					<%-- ========== TEXTFIELD SRCHSTR --%>
					<td><font size=-2>Last done:</font>&nbsp;&nbsp;
					</td>
					<td><g:textField size="130" name="srchstr" value="${srchstr}" />
					</td>
				</tr>
				<tr>
					<td><font size=-2>Last save:</font>&nbsp;&nbsp;
					</td>
					<td><g:textField size="130" name="lineout" value="${lineout}" />
					</td>
				</tr>
				<tr>
					<%-- ========== BLOTTER TEXTAREA --%>
					<td><font size=-2>Blotter:</font>&nbsp;&nbsp;
					</td>
					<td><g:textArea name="textareablotter" value="hh:"+"gg:" rows="1" cols="105"
						onclick="this.focus();this.select()" />
					</td>
				</tr>

			</table>
			</td>
			<td>	
				<table>
					<tr>
						<td>	<div id='autotgtdivintable'></div></td>
					</tr>
					<tr>
						<td><%--H2</td>--%>
					</tr>
				</table>
			</td>
		</tr>

	</table>


	<br>
	<%--
	word order close
	--%>
	<g:checkBox name="cbword" value="${cbword}" /> word 	<g:checkBox
		name="cborder" value="${cborder}" /> order<g:checkBox name="cbclose"
		value="${cbclose}" /> close    <g:textField size="1" name="maxAge"
		value="${maxAge}" />&nbsp;old
	<auth:logoutLink success="[controller:'home', action:'newUser']"
		error="[controller:'userProfile', action:'error']">&nbsp;${user1} logoff</auth:logoutLink>
</g:form> <%--</formset>--%></div>
</div>
<g:if test="${flash.message}">
FLASH:	${fqFileName}
</g:if> <script><%--
alert($ == jQuery)--%>
</script> <%--  ===== LIST OUTPUT HERE ====== --%>

<div id='listoutput'>
<table>
	<tbody>
		<g:each in="${alFileLines}" status="i" var="fl">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">


				<td><g:if test="${maxAge.contains('+')}">
					<font size=-1 color="green"> ${ fl.date} </font>
				</g:if></td>

				<%--REVERSE COUNTER--%>
				<td><font color="purple" size=-1> ${fl.ageLetter} </font></td>

				<td><g:if test="${maxAge.contains('+')}">
					<td><font color="blue" size=-1> ${(i+1).toString()}
					</font>
				</g:if> </td>
				<td><g:checkBox name="cbword" value="${cbword}" /></td>
				<td>
				<g:if test="${maxAge.contains('+')}">
					 <SELECT NAME="gourl">
						<OPTION VALUE="def">
						<OPTION VALUE="del">del
						<OPTION VALUE="touch">upd
						
				</g:if>
				</td>
				</SELECT>
				</td>


				<g:if test="${maxAge.contains('+')}">
					<font size=-1 color="green"> ${fl.fileLineNumThisLine1Based} </font> : 
				</g:if>
				</td>

				<td><font color=${ (i % 2) ==0? "grey" : "black" }>&nbsp;
				${fl.lineMinusDateStr} </font></td>
				<%--<g:createLink url="[controller:'milesAccount', action:'generateTooltip', params:[id:${it.id}, extra:'extra']]" />--%>

			</tr>
		</g:each>
	</tbody>
</table>
</div>
</font>
<div id='autotgtdiv'></div>



</body>
</html>