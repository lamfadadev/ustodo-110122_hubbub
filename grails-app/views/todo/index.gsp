<%@page import="com.hk.util.FileLine"%>
<%@page import="org.junit.internal.matchers.CombinableMatcher"%>
<TITLE>UsToDo ${seq} [${srchstr}] 
</TITLE>
<body bgcolor="white">
<font color=blue face="Arial"> <%--  ===== HEADER AND SEARCH FORM ====== --%>
<div class="form"><%--<formset>--%> <g:form action="index">
	<label for="userId"></label>
	<g:textField size="200" rows="3" cols="300" name="srchstr"
		value="${srchstr}" />
	<br>
	<g:submitButton name="search" value="Do" />
	<g:checkBox name="cbword" value="${cbword}" /> word
	 <g:checkBox name="cborder" value="${cborder}" /> order
	 <g:checkBox name="cbprox" value="${cbprox}" /> prox
	<g:textField size="1" name="maxAge" value="${maxAge}" />&nbsp;old
</g:form> <%--</formset>--%></div>

<g:if test="${flash.message}">
	${fqFileName}
	
</g:if>



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
				<td><font color="blue" size=-1>  ${(alFileLines.size-i).toString()}
				</font></td>
				<td><g:checkBox name="cbword" value="${cbword}" /></td>
				<td><SELECT NAME="gourl">
					<OPTION VALUE="c">
					<OPTION VALUE="Now1">m
					<OPTION VALUE="Today2">h
					<OPTION VALUE="Today2">d
					<OPTION VALUE="Today2">w
					<OPTION VALUE="Today2">m
				</SELECT></td>

				<td><font color=${ (i % 2) ==0?"black" : "grey" }>&nbsp;
				${fl.lineMinusDateStr} </font></td>

				<%--<g:createLink url="[controller:'milesAccount', action:'generateTooltip', params:[id:${it.id}, extra:'extra']]" />--%>

			</tr>
		</g:each>
	</tbody>
</table>
<g:if test="${flash.message}">
</g:if>
<g:else>
<p>	${fqFileName}
</g:else>




<%--${ alRtnToDos.size} records


<table>
	<tbody>
		<g:each in="${alRtnToDos}" status="i" var="userInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
				<td><font  color="${(i % 2) == 0 ? 'black' : 'grey'}">
				${userInstance}</font>
				</td>

			</tr>
		</g:each>
	</tbody>
</table>

--%>
</div>





</font>



</body>






<%--<div class="list">
<table>
	<ztbody>
		<g:each in="${userInstanceList}" status="i" var="userInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

				<td><g:link action="show" id="${userInstance.id}">
					${fieldValue(bean: userInstance, field: "id")}
				</g:link></td>

				<td>
				${fieldValue(bean: userInstance, field: "userId")}
				</td>

				<td>
				${fieldValue(bean: userInstance, field: "password")}
				</td>

				<td><g:formatDate date="${userInstance.dateCreated}" /></td>

				<td>
				${fieldValue(bean: userInstance, field: "profile")}
				</td>

			</tr>
		</g:each>
	</tbody>
</table>
</div>




<thead>
		<tr>

			<g:sortableColumn property="id"
				title="${message(code: 'user.id.label', default: 'Id')}" />

			<g:sortableColumn property="userId"
				title="${message(code: 'user.userId.label', default: 'User Id')}" />

			<g:sortableColumn property="password"
				title="${message(code: 'user.password.label', default: 'Password')}" />

			<g:sortableColumn property="dateCreated"
				title="${message(code: 'user.dateCreated.label', default: 'Date Created')}" />

			<th><g:message code="user.profile.label" default="Profile" /></th>

		</tr>
	</thead>
	--%>






<%--










Time 1: file srch (secs):0.981 len:5


<h4>Commands and advanced search</h4>
<ol>
	<li>TO SET CURRENT USER cmd/setuser/hkon</li>
	<li>TO SEARCH WHOLE WORD ONLY: word:w</li>
	<li>Remember the Milk and the Mikman's life story</li>
</ol>



<table width=1000>





	<tr>
		<td bgcolor=white>2w 0d 0. <b
			style="color: black; background-color: #ffff00">grails</b> instr /
		base commands / <b style="color: black; background-color: #ffff00">grails</b>
		<b style="color: black; background-color: #ffff00">create</b> / LIST /
		remem / <b style="color: black; background-color: #ffff00">grails</b>-app/<b
			style="color: black; background-color: #ffff00">view</b>s/gsp are
		also forms</td>
		</td>
		</td>


		</td>

	</tr>


	<tr>
		<td bgcolor=#bbbbbb>2w 1d 1. <b
			style="color: black; background-color: #ffff00">grails</b> instr /
		base commands / <b style="color: black; background-color: #ffff00">grails</b>
		<b style="color: black; background-color: #ffff00">create</b> / LIST /
		remem / <b style="color: black; background-color: #ffff00">grails</b>-app/<b
			style="color: black; background-color: #ffff00">view</b>s/gsp</td>
		</td>
		</td>


		</td>

	</tr>


	<tr>
		<td bgcolor=white>2w 4d 2. <b
			style="color: black; background-color: #ffff00">grails</b> instr /
		base commands / <b style="color: black; background-color: #ffff00">grails</b>
		<b style="color: black; background-color: #ffff00">create</b> / <b
			style="color: black; background-color: #ffff00">grails</b> generate-<b
			style="color: black; background-color: #ffff00">view</b>s com.<b
			style="color: black; background-color: #ffff00">grails</b>inaction.User
		/ including controller</td>
		</td>
		</td>


		</td>

	</tr>


	<tr>
		<td bgcolor=#bbbbbb>2w 5d 3. <b
			style="color: black; background-color: #ffff00">grails</b> instr /
		base commands / <b style="color: black; background-color: #ffff00">grails</b>
		<b style="color: black; background-color: #ffff00">create</b> / the
		global layout for your site by editing /<b
			style="color: black; background-color: #ffff00">grails</b>-app/<b
			style="color: black; background-color: #ffff00">view</b>s/layouts/main.gsp
		/ css style format</td>
		</td>
		</td>


		</td>

	</tr>


	<tr>
		<td bgcolor=white>4w 3d 4. <b
			style="color: black; background-color: #ffff00">grails</b> instr /
		controller package / <b
			style="color: black; background-color: #ffff00">create</b>-controller
		/ http://<b style="color: black; background-color: #ffff00">grails</b>.1312388.n4.nabble.com/controller-and-<b
			style="color: black; background-color: #ffff00">view</b>-namespace-question-td2318919.html
		</td>
		</td>
		</td>


		</td>

	</tr>


	Time 2: render (secs):0.997
	<BR>
	matches found 15 file [/Users/hkon/sw/ustodo/favs.csv ]

</table>

--%>





