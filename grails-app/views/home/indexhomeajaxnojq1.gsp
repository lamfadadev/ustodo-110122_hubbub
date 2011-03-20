<html>
<head>
</head>
<%-- works: 
example from http://grails.org/doc/latest/guide/6.%20The%20Web%20Layer.html#6.7.1.1 Remoting Linking
--%>
<body>
	<div id="message"></div>
	
	<g:remoteLink action="delete" id="1" update="message">Delete Book</g:remoteLink>

</body>
</html>


