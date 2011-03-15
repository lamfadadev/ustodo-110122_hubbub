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
		--%><%--
		
		<script type="text/javascript"> 
	     $(document).ready($(function() {
	    	    $("#kw").autocomplete('${g.createLink(action: 'autocompleteSearch')}'
	    	    });
	    	});); 
	   </script>
	   
	   
	   --%>
	   <script>
		$(function() {
			var availableTags = [
				"ActionScript",
				"AppleScript",
				"Asp",
				"BASIC",
				"C",
				"C++",
				"Clojure",
				"COBOL",
				"ColdFusion",
				"Erlang",
				"Fortran",
				"Groovy",
				"Haskell",
				"Java",
				"JavaScript",
				"Lisp",
				"Perl",
				"PHP",
				"Python",
				"Ruby",
				"Scala",
				"Scheme"
			];
			$( "#tags" ).autocomplete({
				source: availableTags
			});
		});
		</script>
	
	
	
	
	
</head>	
 <body bgcolor="FFFFFF">

<div class="ui-widget">
	<label for="tags">Tags: </label>
	<input id="tags">
</div>

<g:textField name="kw"/>

<g:if test="${flash.message}">
	${fqFileName}
</g:if>

</body>
</html>