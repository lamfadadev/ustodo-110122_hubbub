<!DOCTYPE html>


<html>
<head>
<title><g:layoutTitle default="Welcome" /></title>

<g:javascript library="jquery" plugin="jquery"/>

<%--<link rel="stylesheet"
	href="<g:createLinkTo dir='css'
file='hubbub.css'/>" />
--%>
<g:layoutHead />
</head>
<body onload="${pageProperty(name:'body.onload')}">

<%--<div class="menu"><!--my common menu goes here-->
</menu>
--%><div>
<%--<div id="hd"><a href="<g:createLinkTo dir="/"/>"> <img
	id="logo" src="${createLinkTo(
dir: 'images',
file: 'headerlogo.png')}"
	alt="hubbub logo" /> </a></div>
--%><div id="bd"><!-- start body --> <g:layoutBody /></div>
</div>
</body>
</html>