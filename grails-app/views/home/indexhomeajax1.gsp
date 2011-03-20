
<%@page import="org.junit.internal.matchers.CombinableMatcher"%>

<html>
<head>
<g:javascript library="jquery" plugin="jquery"/>
	<TITLE>UsToDo [${srchstr}]</TITLE>
	<g:javascript library="jquery"/>
	<meta name="layout" content="main"></meta>
	<script type="text/javascript"> 
   ${
	   	remoteFunction(
		controller: 'home',
		action:'autocompleteSearch',
		update:[success:'kw', failure: 'kw'],
		params:'\'userId=\'+$(\'#kw\').val()'
	)}
	</script>
   

</head>
<body>
<g:javascript library="jquery" plugin="jquery"/>
	<g:textField name="kw"/>
	<g:if test="${flash.message}">
		${fqFileName}
	</g:if>
</body>
</html>