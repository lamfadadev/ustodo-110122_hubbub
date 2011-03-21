<%@page import="org.junit.internal.matchers.CombinableMatcher"%>

<html>
<head>

<g:javascript library="jquery" plugin="jquery"/>
	<TITLE>indexhomeajax3a jquery</TITLE>
	<meta name="layout" content="main"></meta>
</head>
<body>

<g:javascript library="jquery" plugin="jquery"/>
<%--<select onchange="${remoteFunction(action:'autocompleteSearch',update:[success:'kw', failure:'ohno'], params:'\'bookName=\' + this.value' )}">
--%>

<%--
	/* 
	 * autocompleteSearch reference
	 * I got this working pretty easily in my app. The biggest "hurdle" I had was jQuery's "$" function 
	 * conflicting with the Prototype $ function that is included with Grails. In jQuery, the $() 
	 * function is just an alias for jQuery(). So if you use jQuery() instead of $() for everything 
	 * related to the jQuery autocomplete, you should be fine. 
	 */
--%>

<input type="text" size="25" value="11111111111111111" onkeydown=
   "${remoteFunction(action:'autocompleteSearch',
   update:[success:'ertert1', failure:'ertert1'], 
   params:'\'textstr=\' + this.value' )}"> 


<div id='ertert1'>
</div>


<select onchange=
   "${remoteFunction(action:'autocompleteSearch',
   update:[success:'kw', failure:'kw'], 
   params:'\'bookName=\' + this.value' )}">
    <option>first</option>
    <option>second</option>
</select>

</body>
</html>