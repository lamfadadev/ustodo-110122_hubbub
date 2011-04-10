package com.hk

import com.hk.util.O;
import com.hk.util.FileLine;
import com.hk.util.UrlConverterHttp;;
import com.hk.util.UtilFile;
import com.hk.util.UtilSearch;
import com.hk.util.UtilDate;
import com.grailsinaction.User;
import grails.converters.JSON;

import org.apache.tomcat.util.digester.ObjectParamRule;
import org.codehaus.groovy.grails.commons.DefaultGrailsDomainClass
import org.codehaus.groovy.grails.commons.ConfigurationHolder


class TodoController {
	def authenticationService
	private List<String> alfilelines = null;
	private List<String> altags = null;
	private String user1 = null;
	private String fqFileName = null;
	private def timeToRestartAuthCheck = "2011-04-10 17:00:00";
	
	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	private static int seq = 0;

	//	def index2 = {
	//		def results = User.findAll()
	//		render results as JSON
	//	}
	
	
	def index = {
		//O.o("authenticationService:" + authenticationService.getClass().getName());
		//O.o("params.lineout:" + params.lineout);
		//O.o("params.srchstr:" + params.srchstr);
		//O.o("params.textareablotter:" + params.textareablotter);
		
		def paramsInSearch = params.srchstr2;
		def paramsInSearched = params.srchstr;
		def paramsInLineout = params.lineout; // saved
		def paramsIntextareablotter = params.textareablotter; // scratch
		def textareablotter = params.textareablotter; 
		
		O.o ("TODO index request.class:" + request.getClass().getName());
		
		boolean inNoAuthMode = false;
	    String userNoAuth = null;
		// CAUTION: DEBUGGING FEATURE: TURN OFF AUTH UNTIL THIS TIME
		if (UtilDate.getDateForFile().compareTo (timeToRestartAuthCheck) > 0 && !authenticationService.isLoggedIn(request))
		{
			//O.o "inside:: compared [" + now + " to [" + "2011-03-22 03:12:54" + "] get [" + now.compareTo ("2011-03-22 03:12:54") + "]"
			//response.sendError(403)
			redirect(controller:"authentication", action:"index", success:"[controller:'todo', action:'index']")
			O.o ("do we ever get here?")
		}
		else
		{
			inNoAuthMode = true;
			user1 = org.codehaus.groovy.grails.commons.ConfigurationHolder.getFlatConfig().get('mystuff.settings.noauthuser')
			O.o "using the no auth user!!!!!!!!!!!!!!!!!!: " + user1.dump()
		}
		if (!inNoAuthMode) {
			user1 = getUser();
			O.o ("using auth, user1:");  // user1.toString();
		}

		def apath = org.codehaus.groovy.grails.commons.ConfigurationHolder.getFlatConfig()
		//println apath.dump();
		def p = apath.get('mystuff.settings.filepath')
		
		//O.o p.dump()
		//O.o p, false
		def j = ConfigurationHolder.getConfig().getProperty('mystuff.settings.filepath')
		//O.o j.dump()
		fqFileName = p+'/favs' + user1 + '.csv';
        //O.o fqFileName, false
		seq++;
		String mode = ""

		boolean bwrite = false;

		//final String srchstr = (String) params.srchstr;
		// make the search box same as autocomplete
		String srchstr = (String) params.srchstr2;
		O.o("srchstr2:" + params.srchstr2);

		def alFileLines = new ArrayList<FileLine>();
		if (srchstr == null || (srchstr).trim().equals("")) {
			srchstr = "grails"; // was grails 
		}

		srchstr = srchstr.trim()
		//if (seq %2 == 0)
		//s3="purple"

		if ( !(new File(fqFileName).exists()))
		{
			O.o("create new file/for new user:" + fqFileName);
			File f = new File(fqFileName);
			f.write("");
		}
		
		
		// search history file 
		//			if ( !(new File(fqFileName_srchHist).exists()))
		//			{
		//				O.o("create new file/for new user:" + fqFileName_srchHist);
		//				File f2 = new File(fqFileName_srchHist);
		//				f2.write("");
		//			}
		//			else
		//				O.o("SEEMS LIKE FILE EXISTS:" + fqFileName);

		// WRITE WRITE WRITE WRITE WRITE WRITE WRITE WRITE WRITE WRITE WRITE WRITE WRITE
		String lineout = params.lineout;
		boolean bDidWrite = false;
		String srchstrout = paramsInSearch;
		if (srchstr.trim().startsWith("w ") || srchstr.trim().endsWith(" w") )
		{
			bDidWrite=true;
			O.o("pre trimmed srchstr in write:" + srchstr);
			bwrite = true;
			if (srchstr.trim().startsWith("w "))
				srchstr = srchstr[2..-1] // remove "w "
			if (srchstr.trim().endsWith(" w"))
				srchstr = srchstr[0..(srchstr.length()-2)] // remove " w"
				
			srchstr = srchstr.trim();
			O.o("post trimmed srchstr in write:" + srchstr);
			lineout = com.hk.util.UtilDate.getDateForFile() + " " + srchstr;
			//O.o("saved lineout:" + lineout.toString());
			//O.o("try existing file:" + fqFileName );
			new File(fqFileName).withWriterAppend { out ->
				out.writeLine(lineout);
			}
			//mode = "Saved: [" + lineout+ "] &nbsp; to ["+fqFileName+"]"
			// SAVE WRITE LINE TO IN MEM ARRAY REFLECTION OF FILE FOR AUTOCOMPLETE
			// MAY NOT BE IN USE - READING FILE EVERY TIME CURRENTLY
			ArrayList alfilelines = session.getAttribute ("alfilelines");
			if (alfilelines != null) 
			{
				O.o "in index added to alfilelines [" + lineout + "]"
				alfilelines.add(lineout);
				session.setAttribute("alfilelines", alfilelines);
			}
			else
				O.o "in index OOPS: added to alfilelines [" + lineout + "]"
			

			if (lineout != null && lineout.trim().equals(""))					
				flash['message'] = " [" + lineout + "]"

			//O.o("write pos last / [" + srchstr.lastIndexOf('/') + "] on str [" + srchstr + "]");
			//srchstr = srchstr [0..(srchstr.lastIndexOf(' / ')-1)] + " / "
			textareablotter = "[w " + lineout[20..-1] + "]   " + textareablotter 
			//O.o "ss:" + srchstr;
			srchstrout = srchstr [0..(srchstr.lastIndexOf(' / '))] + " / "
		}
		else // no write
		{
			textareablotter = "[" + srchstr + "]   " + textareablotter
		}

		// SRCH SRCH SRCH SRCH SRCH SRCH

		String srchstrPostWriteStripInstance = srchstr;

		//O.o("time:" + new java.util.Date() ) ;
		O.o("user [" + user1 +  "] search [" + srchstrPostWriteStripInstance+ "]") ;

		
		//now do multi-search output
		//include only last X lines of favs file for autosearch results 
		File f = new File(fqFileName)
		int i = 0
		def start = 0;
		if (srchstr.equals(""))
			start = UtilFile.fileLen(fqFileName) - 1000;
			
		// FOR EACH FILE LINE
		f.eachLine
		{
			boolean keepline = false;
			i++; // 1based line num counter
			String fileLineRaw = ((String) it).trim();
			String fileLineRawLower = fileLineRaw.toLowerCase();
			if (i > start)
			{
				keepline = UtilSearch.match(fileLineRawLower, srchstrPostWriteStripInstance)
			}
			// IF ALL WORDS MATCHED THIS LINE, KEEP THE LINE
			if (i > start && keepline)
			{
				String fileLineRawURL = null;
				fileLineRawURL = UrlConverterHttp.detectAndConvertURLs (fileLineRaw);
				if (fileLineRawURL != null)
				{
					fileLineRaw = fileLineRawURL;
					//O.o("converted fileLineRaw: "+fileLineRaw);

				}
				alFileLines.add (new FileLine(i, fileLineRaw));
				//O.o("fileLineRaw: "+fileLineRaw);
			}

		}
		//				if (s.contains(srchstr))
		//					alRtnToDos.add (s[2..9]+s[19..-1]);

		alFileLines = alFileLines.reverse()

		if (params.maxAge == null || params.maxAge.trim().equals(""))
			params.maxAge = "3y"

		//		O.o("textareablotterx: "+textareablotter);
		if (bDidWrite)
		{
			lineout = lineout[19..-1] + "  |  " + paramsInLineout; // KEEP HISTORY OF SAVES
		}
		
		O.o ("sending back: srchstr2: srchstrout[" + srchstrout +"]");
		O.o ("sending back: srchstr: srchstr[" + srchstr + "  |  " +  paramsInSearched +"]");
		[srchstr2: srchstrout , srchstr: srchstr + "  |  " +  paramsInSearched , seq:seq, alFileLines: alFileLines , cbword: params.cbword
					, cborder: params.cborder, hktest: "hkteststr", maxAge: params.maxAge, alFileLines: alFileLines,
					fqFileName: ("<font color=\"GREEN\">"+mode), seq:seq,  user1:user1, 
					lineout: lineout,
					textareablotter: textareablotter
					]
	}
	

	
	
	
	def autocompleteSearch = {

		boolean inNoAuthMode = false;
		String user1 = null;
		
		// CAUTION: DEBUGGING FEATURE: TURN OFF AUTH UNTIL THIS TIME
		if (UtilDate.getDateForFile().compareTo (timeToRestartAuthCheck) > 0 && !authenticationService.isLoggedIn(request))
		{
			//O.o "inside:: compared [" + now + " to [" + "2011-03-22 03:12:54" + "] get [" + now.compareTo ("2011-03-22 03:12:54") + "]"
			//response.sendError(403)
			redirect(controller:"authentication", action:"index", success:"[controller:'todo', action:'index']")
			O.o ("autocompleteSearch do we ever get here?")
		}
		else
		{
			inNoAuthMode = true;
			user1 = org.codehaus.groovy.grails.commons.ConfigurationHolder.getFlatConfig().get('mystuff.settings.noauthuser')
			//O.o "autocompleteSearch using the no auth user!!!!!!!!!!!!!!!!!!: " + user1.dump()
		}
		if (!inNoAuthMode) {
			user1 = getUser();
			//O.o ("using auth, user1:");  // user1.toString();
			if (!authenticationService.isLoggedIn(request) || user1 == null)
			{
				render "<H1>not logged in</H3>";
			}
		}

		def apath = org.codehaus.groovy.grails.commons.ConfigurationHolder.getFlatConfig()
		//println apath.dump();
		def p = apath.get('mystuff.settings.filepath')
		
		//O.o p.dump()
		//O.o p, false
		def j = ConfigurationHolder.getConfig().getProperty('mystuff.settings.filepath')
		//O.o j.dump()
		fqFileName = p+'/favs' + user1 + '.csv';
		
		//O.o ("TODO autocompleteSearch fqFileName :" + fqFileName );
		//O.o ("TODO autocompleteSearch request.class:" + request.getClass().getName());
		String srchStr = params['textstr'];
		String autocomp_userInput = params['autocomp'];
		//O.o ("autocomplete fqFileName [" + fqFileName + "] autocomp [" + autocomp_userInput + "]");
		if (autocomp_userInput == null)
		{
			//O.o ("autocomplete empty");
			render "empty autocomp";
			return;
		}
		StringBuffer sb = new StringBuffer();
		
		//MAINTAIN STATIC COLLECTION OF TAGS AND SEARCH HISTORY IN MEMORY 
		//CHANGE TO SESSION VARIABLES
		//O.o("session user:" + session.user)
		
		alfilelines = session.getAttribute ("alfilelines");
		if (autocomp_userInput.length() > 2) //if user's entry is now > 2 char's (no point searching 2 char substrings)
		{
			if (true || alfilelines == null) // needs to be session-scoped probably
			//if (alfilelines == null) // needs to be session-scoped probably
			{
				//O.o("re-read file for auto [" + autocomp_userInput + "] to build alfilelines", true);
				//O.o("TODOC. BEGIN re-read file lineCountToStartKeepAt:"+lineCountToStartKeepAt);
				alfilelines = com.hk.util.UtilFile.fileAsList (fqFileName, new Boolean(false), "", 300);
				//O.o("TODOC. DONE re-read file alfilelines.size():"+alfilelines.size());
				// cache file in mem
				session.setAttribute "alfilelines", alfilelines
			}
			else
			{
				O.o ("in autocomplete no read file");
			}
			//O.o("autocomp seeking matches to [" + autocomp_userInput + "] across [" + alfilelines.size() + "] lines.");
			
			int iCnt = 0;
			com.hk.util.UtilSearch.getAutoCompleteLinesOut  (alfilelines, autocomp_userInput, 300, fqFileName).each {
				iCnt++;
				String autoCompleteLineOut = it;
				//O.o("in todo ((((((((((((((((  autoCompleteLineOut [" + autoCompleteLineOut + "]");
				autoCompleteLineOut = UrlConverterHttp.detectAndConvertURLs (autoCompleteLineOut);
				autoCompleteLineOut = UtilSearch.colorTags(autoCompleteLineOut, autocomp_userInput, "green");
				// = tag.replaceAll (autocomp_userInput,"<font color=blue>"+autocomp_userInput+"</font>");
				
				sb.append ("<br>" + iCnt + ". " + autoCompleteLineOut +  "&nbsp;&nbsp;");
			}
			//O.o("autocomp got# [" + iCnt + "] matches to [" + autocomp_userInput + "] across [" + alfilelines.size() + "] lines.");
		} // if len > 2
		//else
		//	O.o("too short for autocomplete [" + autocomp_userInput+ "]");
		
		String sRtn = sb.toString();
		//O.o("rtn [" + sRtn + "]")
		render "<H3>Quick/Recent Display (hit enter for a full historial search):</H3>" + sRtn;
	}

	public String getUser()
	{
		//O.o ("auth.getClass:" + auth.getClass().getName().toString());
		//O.o ("session.getClass:" + session.getClass().getName().toString());
		//auth.getClass:org.codehaus.groovy.grails.web.taglib.NamespacedTagDispatcher
		//session.getClass:org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession

		//Object o = auth.user();
		//O.oc ("auth.user.getClass", o);
		// this is an auto-cast
		String s = auth.user();
		//O.oc ("auth.user string .getClass", s);
		return s;
	}




}
