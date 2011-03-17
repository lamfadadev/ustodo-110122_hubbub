<%@page import="com.hk.util.FileLine"%>
<%@page import="org.junit.internal.matchers.CombinableMatcher"%>
<html>
<head>
 <TITLE>UsToDo [${srchstr}]</TITLE>
 <g:javascript library="jquery"/>
 <meta name="layout" content="main"></meta>
 
 <%-- from http://grails.1312388.n4.nabble.com/jQuery-Autocomplete-td2526439.html--%>
 
	<%--<script type="text/javascript"> 
	     $(document).ready(function() { 
	       $('#kw').autocomplete({ 
	         source: '${g:createLink(action: 'autocompleteSearch')}' 
	       }); 
	     }); 
	</script>
		--%>

	<script type="text/javascript">
	var firstname;
	firstname="Hege";
	document.write(firstname);
	document.write("<br />");
	firstname="Tove";
	document.write("kw:" + $("#kw"))
	document.write("document:" + $(document))
	document.write(firstname);
	</script>

<a href="${createLink(controller: "home", action:'autocompleteSearch')}">my link</a>


		<script type="text/javascript"> 
	     $(document).ready($(function() {
	    	    $("#kw").autocomplete('${g.createLink(controller: "home", action: "autocompleteSearch")}', {
	    	        max: 100,
	    	        width: 300
	    	    })
	    	}))
	   </script>






</head>	
 <body bgcolor="FFFFFF">

<g:textField name="kw"/>

<g:if test="${flash.message}">
	${fqFileName}
</g:if>

</body>
</html>