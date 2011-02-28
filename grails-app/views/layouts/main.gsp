<!DOCTYPE html>
<%--<html>
<head>
<title><g:layoutTitle default="Grails" /></title>
<link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />
<link rel="shortcut icon"
	href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
<g:layoutHead />
<g:javascript library="application" />
</head>
<body>
<div id="spinner" class="spinner" style="display: none;"><img
	src="${resource(dir:'images',file:'spinner.gif')}"
	alt="${message(code:'spinner.alt',default:'Loading...')}" /></div>
<div id="grailsLogo"><a href="http://grails.org"><img
	src="${resource(dir:'images',file:'grails_logo.png')}" alt="Grails"
	border="0" /></a></div>
<g:layoutBody />
</body>
</html>

--%>

<html>
<head>
<title>Hubbub &raquo; <g:layoutTitle default="Welcome" /></title>
<link rel="stylesheet"
	href="<g:createLinkTo dir='css'
file='hubbub.css'/>" />
<g:layoutHead />
</head>
<body>
<div>
<div id="hd"><a href="<g:createLinkTo dir="/"/>"> <img
	id="logo" src="${createLinkTo(
dir: 'images',
file: 'headerlogo.png')}"
	alt="hubbub logo" /> </a></div>
<div id="bd"><!-- start body --> <g:layoutBody /></div>
<!-- end body -->
<div id="ft">
<div id="footerText">Hubbub - Social Networking on Grails</div>
</div>
</div>
</body>
</html>