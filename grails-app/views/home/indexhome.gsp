<%@page import="com.hk.util.FileLine"%>
<%@page import="org.junit.internal.matchers.CombinableMatcher"%>
<html>
<head>
 <TITLE>UsToDo [${srchstr}]</TITLE>
 <meta name="layout" content="main"></meta>
 
 <%-- from http://grails.1312388.n4.nabble.com/jQuery-Autocomplete-td2526439.html--%>
 
 <script type="text/javascript"> 
     $(document).ready(function() { 
       $('#kw').autocomplete({source: '${g:createLink(action: 'autocompleteSearch')}' 
       }); 
     }); 
</script>
</head>
 <body bgcolor="FFFFFF">

<g:textField name="kw"/>

<g:if test="${flash.message}">
	${fqFileName}
</g:if>

</body>
</html>