
<%@page import="org.junit.internal.matchers.CombinableMatcher"%>

<html>
<head>

<%--
	/* 
	 * autocompleteSearch reference
	 * I got this working pretty easily in my app. The biggest "hurdle" I had was jQuery's "$" function 
	 * conflicting with the Prototype $ function that is included with Grails. In jQuery, the $() 
	 * function is just an alias for jQuery(). So if you use jQuery() instead of $() for everything 
	 * related to the jQuery autocomplete, you should be fine. 
	 */
--%>
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