<%@page import="com.hk.util.FileLine"%>
<%@page import="org.junit.internal.matchers.CombinableMatcher"%>
<html>
<head><%--
document.body.onLoad=onloadhk2()
--%><TITLE>UTD/${srchstr}
</TITLE>
<meta name="layout" content="main"></meta>
<script type="text/javascript">

function onloadhk()
{
	//document.bgColor='#AAAAAA';
	document.bgColor='rgb(232,232,232)'
	document.hkformname.srchstr2.focus();
}

function hist()
{
	alert("in hist");
}

function home()
{
	document.getElementById('fld1userinput').focus();
}

function fnmakescratchbig()
{
	//alert("hi fnmakescratchbig");
	//document.hkformname.textareablotter.height=50
	if (document.getElementById('fld4textareablotter').rows == 3) {
		document.getElementById('fld4textareablotter').rows = 20; // clear name="textareablotter" id="fld4textareablotter" v
		//document.hkformname.button4More.value="more"
		document.getElementById('fld4textareablotter').focus();
	}
	else // make smaller
	{
		document.getElementById('fld4textareablotter').rows = 3; // clear name="textareablotter" id="fld4textareablotter" v
		document.getElementById('fld1userinput').focus();
	}
}

function clearfields()
{
	document.getElementById('fld1userinput').value = ""; // clear 
	document.getElementById('fld2sought').value = ""; // clear 
	document.getElementById('fld3saved').value = ""; // clear 
	document.getElementById('fld4textareablotter').value = ""; // clear 
	document.getElementById('fld1userinput').focus();
}

function fncopyseek()
{
	addtoblotter (document.getElementById('fld1userinput').value); //copy sought categ 
	document.getElementById('fld4textareablotter').focus();
}
function fncopysought()
{
	addtoblotter (document.getElementById('fld2sought').value); //copy sought categ 
	document.getElementById('fld4textareablotter').focus();
}
function fncopysaved()
{
	addtoblotter (document.getElementById('fld3saved').value); //copy sought categ 
	document.getElementById('fld4textareablotter').focus();
}
function fncopyscratch()
{
	document.getElementById('fld1userinput').value = document.getElementById('fld4textareablotter').value + ' w'  
	document.getElementById('fld1userinput').focus();
}

function fnclearsaved()
{
	document.getElementById('fld3saved').value = ''
}

function addtoblotter(s)
{
	document.getElementById('fld4textareablotter').value = 	document.getElementById('fld4textareablotter').value  + "  |  " + s 
}


function postautocomplete (s)
{
	var a = document.getElementById('fld1userinput').value;
	 
	 var al=a.length;
	 var last_char=a.charAt(al-1)
	 if (last_char != ' ')
	 {	
		 document.getElementById('listoutput').innerHTML = ""; // clear 
	 }
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
				<%-- ========== 1 BUTTON AND AJAX FIELD -- INPUT REMOTE FUNCTION AUTOCOMPLETE SOURCE fld1userinput --%>
				<tr>
					<td><font size=-2> <g:submitButton  name="search"
						value="Do" /></font><%--
						<font size=-2>Seek:</font>&nbsp;&nbsp;
						
					--%></td>
					<td><input title="Search - fulltext with substring - over instances, categories, workflow step names, field names." name='srchstr2' id='fld1userinput' type="text"
						size="125" value="${srchstr2}"
						onkeyup="${
							remoteFunction (action:'autocompleteSearch',
							update:[success:'autotgtdiv', failure:'autotgtdiv'], 
							params:'\'autocomp=\' + this.value', 
							onComplete:'postautocomplete();')} ">
						<INPUT type="button" value="" title="Copy to Scratch." name="buttoncopyseek" onClick="fncopyseek()">
						<INPUT type="button" value="" title="Clear." onClick="document.getElementById('fld1userinput').value = ''">
						
					</td>

				<%-- ========== 2 TEXTFIELD Searched fld2sought --%>
				</tr>
				<tr>
					<td><font size=-2>Searched:</font>&nbsp;&nbsp;
					</td>
					<td>
						<g:textField size="130" title="Searched. What you last Searched for." id="fld2sought" name="srchstr" value="${srchstr}" />
	  					<INPUT type="button" value="" name="buttoncopysought" title="Copy to Scratch." onClick="fncopysought()">
	  					<INPUT type="button" value="" title="Clear." onClick="document.getElementById('fld2sought').value = ''">
	  					
					</td>
					<%--
					</td>
						<td><font size=-2>Saved:</font>&nbsp;&nbsp;
					</td>
					<td><g:textField size="130" name="lineout" value="${lineout}" />
					</td>
				--%></tr>

				<%-- ========== 3 TEXTFIELD SAVED fld3saved --%>
				<tr>
					</td>
						<td><font size=-2>Saved:</font>&nbsp;&nbsp;
					</td>
					<td>
						<g:textField size="130"   title="Saved.  What you last Saved." id="fld3saved" name="lineout" value="${lineout}" />
						<INPUT type="button" value="" name="buttoncopysaved" title="Copy to Scratch."onClick="fncopysaved()">
	  					<INPUT type="button" value=""  title="Clear." onClick="fnclearsaved()">
					</td>
				</tr>
				
				<%-- ========== 4 TEXTAREA SCRATCH fld4textareablotter --%>
				<tr>
					<td><font size=-2>Scratch:</font>&nbsp;&nbsp;
					</td>
					<td><g:textArea name="textareablotter" title="Scratch.  Saved, Searhed, and whatever else you do in here." id="fld4textareablotter" value="${textareablotter}" rows="3" cols="100"
						onclick="this.focus();this.select()" />
						<FORM>
					 		<%--<INPUT type="button" value="Grey" name="button3" onClick="document.bgColor='rgb(232,232,232)'">--%>
					 		<INPUT type="button" title="Copy to Search ('w'rite enabled)." value=""  onClick="fncopyscratch()">
		  					<INPUT type="button" value=""  title="Clear." onClick="document.getElementById('fld4textareablotter').value = ''">
					 		<INPUT type="button" value="" title="Toggle Scratch size." name="button4Morename" id="button4More" onClick="fnmakescratchbig()">
					 		
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
	<INPUT type="button" value="Clear" name="button2" onClick="clearfields();	document.getElementById('fld1userinput').focus();">
	<INPUT type="button" value="Hist" name="buttonhist" onClick="hist();">
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

--%>


<%--  ===== LIST LIST LIST OUTPUT ======================== --%>

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