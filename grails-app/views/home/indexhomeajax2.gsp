<%@page import="org.junit.internal.matchers.CombinableMatcher"%>

<html>
<head>
<g:javascript library="jquery" plugin="jquery"/>
	<TITLE>UsToDo [${srchstr}]</TITLE>
	<meta name="layout" content="main"></meta>
</head>
<body>
<g:javascript library="jquery" plugin="jquery"/>
	<g:textField name="kw"/>
<select onchange="${remoteFunction(action:'autocompleteSearch',update:[success:'kw', failure:'ohno'], params:'\'bookName=\' + this.value' )}">
    <option>first</option>
    <option>second</option>
</select>

</body>
</html>