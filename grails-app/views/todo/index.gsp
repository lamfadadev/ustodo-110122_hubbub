<%@page import="com.hk.util.FileLine"%>
<%@page import="org.junit.internal.matchers.CombinableMatcher"%>
<html>
<head><%--
document.body.onLoad=onloadhk2()
--%><TITLE>UTD/${srchstr}
</TITLE>
<meta name="layout" content="main"></meta>
<script type="text/javascript">


function fnmakescratchbig()
{
	//alert("hi fnmakescratchbig");
	//document.hkformname.textareablotter.height=50
	if (document.getElementById('fld4textareablotter').rows == 3) {
		document.getElementById('fld4textareablotter').rows = 20; // clear name="textareablotter" id="fld4textareablotter" v
		document.getElementById('button4More').value="Less"; // clear name="textareablotter" id="fld4textareablotter" v
		//document.hkformname.button4More.value="more"
	}
	else {
		document.getElementById('fld4textareablotter').rows = 3; // clear name="textareablotter" id="fld4textareablotter" v
		document.getElementById('button4More').value="More"; // clear name="textareablotter" id="fld4textareablotter" v
		
	}
}

function clearfields()
{
	//alert ("in hktest")
	document.getElementById('fld1userinput').value = ""; // clear 
	document.getElementById('fld2sought').value = ""; // clear 
	document.getElementById('fld3saved').value = ""; // clear 
	document.getElementById('fld4textareablotter').value = ""; // clear 
	document.bgColor='#AAAAAA';
}
function onloadhk()
{
	//alert("hi hk in onloadhk");
	//document.bgColor='#AAAAAA';
	document.bgColor='rgb(232,232,232)'
	document.hkformname.srchstr2.focus();
}
function fncopysought()
{
	document.getElementById('fld1userinput').value = document.getElementById('fld2sought').value; //copy sought categ 
}
function fncopysaved()
{
	document.getElementById('fld1userinput').value = document.getElementById('fld3saved').value; //copy sought categ 
}

function postautocomplete (s)
{
	//alert("Aasassa");
	//r = "hihi"
	//alert("in postautocomplete3r:" + r);
	 //autotgtdivHTMLpre = document.getElementById('autotgtdiv').innerHTML;
	 //document.getElementById('listoutput').innerHTML = ""; // clear 
	var a = document.getElementById('fld1userinput').value;
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

<body onLoad="onloadhk()"> 
   <%--

--%><%--  ===== HEADER AND SEARCH FORM / TEXT BOX ====== --%>
<font color=black face="Arial"><%--

TRY TO RETURN HERE AFTER AUTH - NOT WORKING YET - HOPEFULLY WILL APPEAR
--%><auth:form authAction="signup" success="[controller:'todo', action:'index']" error="[controller:'todo', action:'index']">
</auth:form>


<div class="form"><%--<formset>--%> <g:form name="hkformname" action="index">

	<label for="userId"></label>

	<%-- ========== AUTOCOMPLETE SEARCH --%>
	<br>

	<table>
		<tr>
			<td>
			<table>
				<%-- ========== 1 BUTTON AND AJAX FIELD -- INPUT REMOTE FUNCTION AUTOCOMPLETE SOURCE --%>
				<tr>
					<td><font size=-2> <g:submitButton  name="search"
						value="Do" /></font><%--
						<font size=-2>Seek:</font>&nbsp;&nbsp;
						
					--%></td>
					<td><input name='srchstr2' id='fld1userinput' type="text"
						size="125" value="${srchstr}"
						onkeyup="${
							remoteFunction (action:'autocompleteSearch',
							update:[success:'autotgtdiv', failure:'autotgtdiv'], 
							params:'\'autocomp=\' + this.value', 
							onComplete:'postautocomplete();')} ">
					</td>

				<%-- ========== 2 TEXTFIELD SOUGHT --%>
				</tr>
				<tr>
					<td><font size=-2>Sought:</font>&nbsp;&nbsp;
					</td>
					<td>
						<g:textField size="130" id="fld2sought" name="srchstr" value="${srchstr}" />
	  					<INPUT type="button" value="" name="buttoncopysought" onClick="fncopysought()">
					</td>
					<%--
					</td>
						<td><font size=-2>Saved:</font>&nbsp;&nbsp;
					</td>
					<td><g:textField size="130" name="lineout" value="${lineout}" />
					</td>
				--%></tr>

				<%-- ========== 3 TEXTFIELD SAVED --%>
				<tr>
					</td>
						<td><font size=-2>Saved:</font>&nbsp;&nbsp;
					</td>
					<td>
						<g:textField size="130"  id="fld3saved" name="lineout" value="${lineout}" />
						<INPUT type="button" value="" name="buttoncopysaved" onClick="fncopysaved()">
					</td>
				</tr>
				
				<%-- ========== 4 TEXTAREA SCRATCH --%>
				<tr>
					<td><font size=-2>Scratch:</font>&nbsp;&nbsp;
					</td>
					<td><g:textArea name="textareablotter" id="fld4textareablotter" value="${textareablotter}" rows="3" cols="100"
						onclick="this.focus();this.select()" />
						<FORM>
					 		<INPUT type="button" value="Clear" name="button2" onClick="clearfields()">
					 		<%--<INPUT type="button" value="Grey" name="button3" onClick="document.bgColor='rgb(232,232,232)'">--%>
					 		<INPUT type="button" value="More" name="button4Morename" id="button4More" onClick="fnmakescratchbig()">
					 		
						</FORM>
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
</g:if> 
<%--<script>
alert($ == jQuery)
</script> 

--%><%--  ===== LIST OUTPUT ======================== --%>

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

				<td>
					<td><font color="blue" size=-1> ${(i+1).toString()}
					</font>
				</td>
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