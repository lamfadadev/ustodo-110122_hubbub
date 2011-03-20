<html>
   <head>
       <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
       <meta name="layout" content="main" />
		<TITLE>indexhomeajax4 prototype</TITLE>
	       <g:javascript library="prototype" />
   </head>
   <body>
       <div class="body">
       </div>
       
<select onchange="${remoteFunction(controller:'home', action:'continentSelected',update:'continent', params:'\'continent=\' + this.value' )}">
   <option>select continent</option>
   <option>America</option>
   <option>Asia</option>
   <option>Europa</option>
   <option>Africa</option>
   <option>Australia</option>
</select>
<div/>
TWO
<div id="continent">
<select onchange="${remoteFunction(controller:'home', action:'continentSelected',update:'continent', params:'\'continent=\' + this.value' )}">
   <option>select continent</option>
   <option>America</option>
   <option>Asia</option>
   <option>Europa</option>
   <option>Africa</option>
   <option>Australia</option>
</select></div>
   </body>
</html>