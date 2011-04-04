package com.hk

import com.hk.util.O;
import com.hk.util.FileLine;
import com.hk.util.UrlConverterHttp;;
import com.hk.util.UtilFile;
import com.grailsinaction.User;
import grails.converters.JSON;

import org.apache.tomcat.util.digester.ObjectParamRule;
import org.codehaus.groovy.grails.commons.DefaultGrailsDomainClass


class TodoController {
	def authenticationService
	private List<String> alfilelines = null;
	private List<String> altags = null;
	private List<String> alFileLinesHistoricalNewFirst = null;
	private String user1 = null;
	private String fqFileName = null;

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	private static int seq = 0;

	//	def mainhk1 = {
	//
	//	}
	//
	//	def index2 = {
	//		def results = User.findAll()
	//		render results as JSON
	//	}

	def index = {
		//O.o(new java.util.Date().toString() + ": in todo controller index");
		// DISABLED FEATURE TURNS OFF AUTH UNTIL TIME timeToRestoreAuth
		//String now = com.hk.util.UtilDate.getDateForFile();
		//String timeToRestoreAuth = "2011-03-22 03:12:54"; 
		//if (now.compareTo (timeToRestoreAuth) > 0 && !authenticationService.isLoggedIn(request))
		//O.o("authenticationService:" + authenticationService.getClass().getName());
		//O.o("params.lineout:" + params.lineout);
		//O.o("params.srchstr:" + params.srchstr);
		//O.o("params.textareablotter:" + params.textareablotter);
		def textareablotter = params.textareablotter; 
		O.o ("TODO index request.class:" + request.getClass().getName());
		if (!authenticationService.isLoggedIn(request))
		{
			//O.o "inside:: compared [" + now + " to [" + "2011-03-22 03:12:54" + "] get [" + now.compareTo ("2011-03-22 03:12:54") + "]"
			//response.sendError(403)
			redirect(controller:"authentication", action:"index", success:"[controller:'todo', action:'index']")
		} else {
			//O.o "inside else :: compared [" + now + " to [" + "2011-03-22 03:12:54" + "] get [" + now.compareTo ("2011-03-22 03:12:54") + "]"
			//			O.o ("index user:" + getUser());
			user1 = getUser();
			//			// enable temp no auth
			//			if (user1 == null || user1.trim().equals("") )
			//			{
			//				if(now.compareTo (timeToRestoreAuth) < 0)
			//					user1 = "ckckck"
			//				else
			//					user1 = ""
			//				
			//			}
			
			
			fqFileName = '/Users/hkon/sw/ustodo/favs' + user1 + '.csv';

			seq++;
			String mode = ""

			boolean bwrite = false;

			//final String srchstr = (String) params.srchstr;
			// make the search box same as autocomplete
			final String srchstr = (String) params.srchstr2;
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
			if (srchstr.trim().startsWith("w ") || srchstr.trim().endsWith(" w") )
			{
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
				textareablotter = "[" + lineout + "]   " + textareablotter 
				//O.o "ss:" + srchstr;
			}
			else
			{
				textareablotter = "[" + srchstr + "]   " + textareablotter 
			}

			// SRCH SRCH SRCH SRCH SRCH SRCH


			//			// maintain search history here - default search should be last search
			//			File f = new File(fqFileName)
			//			int i = 0
			//			// FOR EACH FILE LINE in srch history
			//			String lastline =
			//			f.eachLine
			//			{
			//				i++; // 1based line num counter
			//				String fileLineRaw = ((String) it).trim();
			//				String fileLineRawLower = fileLineRaw.toLowerCase();
			//				// FOR EACH SRCH WORD MATCH MATCH MATCH MATCH MATCH
			//				boolean hitRemove = false;
			//				(srchstrPostWriteStripInstance.split(" ")).eachWithIndex
			//				{ srchWrd, ii ->
			//					srchWrd = srchWrd.trim().toLowerCase();
			//					if (!hitRemove)
			//					...
			//				}
			//			}
			//			// END maintain search history here

			String srchstrPostWriteStripInstance = srchstr;

			O.o("time:" + new java.util.Date() ) ;
			O.o("user [" + user1 +  "] search [" + srchstrPostWriteStripInstance+ "]") ;
			//(srchstrPostWriteStripInstance.split(" ")).eachWithIndex
			//{ srchWrd, ii ->
			//  O.o("################## search word set" + ii + " [" + srchWrd + "]");
			//}
			//now do multi-search output
			File f = new File(fqFileName)
			int i = 0
			def start = 0;
			if (srchstr.equals(""))
				start = UtilFile.fileLen(fqFileName) - 1000;
				
			// FOR EACH FILE LINE
			f.eachLine
			{
				boolean hitRemove = false;
				i++; // 1based line num counter
				String fileLineRaw = ((String) it).trim();
				String fileLineRawLower = fileLineRaw.toLowerCase();
				if (i > start)
				{
					// FOR EACH SRCH WORD MATCH MATCH MATCH MATCH MATCH
					(srchstrPostWriteStripInstance.split(" ")).eachWithIndex
					{ srchWrd, ii ->
						srchWrd = srchWrd.trim().toLowerCase();
						if (!hitRemove)
						{
							if (srchWrd.startsWith("-")) // subtractive search
							{
								//O.o ((new Date()).toString() + "********* in sub testing neg on [" + srchWrd + "]");
								if (srchWrd.length() > 1 && fileLineRawLower.contains(srchWrd[1..-1])) // / ignoreif there is a "-" alone
								{
									hitRemove = true;
									//O.o ((new Date()).toString() + "hit remove yes false");
								}
							}
							else // positive search
							{
								if (!fileLineRawLower.contains(srchWrd))
								{
									hitRemove = true;
								} else
								{
									//O.o ((new Date()).toString() + "found match on searchword [" + srchWrd + "]");
								}
							}
						}
					}
				}
				// IF ALL WORDS MATCHED THIS LINE, KEEP THE LINE
				if (hitRemove == false)
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
			[srchstr: srchstr, seq:seq, alFileLines: alFileLines , cbword: params.cbword
						, cborder: params.cborder, hktest: "hkteststr", maxAge: params.maxAge, alFileLines: alFileLines,
						fqFileName: ("<font color=\"GREEN\">"+mode), seq:seq,  user1:user1, lineout: lineout,
						textareablotter: textareablotter
						]
		}
	}



	
	def autocompleteSearch = {
		user1 = getUser();
		fqFileName = '/Users/hkon/sw/ustodo/favs' + user1 + '.csv';
		O.o ("TODO autocompleteSearch fqFileName :" + fqFileName );
		//O.o ("TODO autocompleteSearch request.class:" + request.getClass().getName());
		if (!authenticationService.isLoggedIn(request) || user1 == null)
		{
			render "not logged in";
		}
		String srchStr = params['textstr'];
		String autocomp_userInput = params['autocomp'];
		O.o ("autocomplete user [" + getUser() + "] autocomp [" + autocomp_userInput + "]");
		if (autocomp_userInput == null)
		{
			O.o ("autocomplete empty");
			render "empty autocomp";
			return;
		}
		StringBuffer sb = new StringBuffer();
		
		//MAINTAIN STATIC COLLECTION OF TAGS AND SEARCH HISTORY IN MEMORY 
		//CHANGE TO SESSION VARIABLES
		//O.o("session user:" + session.user)
		
		alfilelines = session.getAttribute ("alfilelines");
		if (autocomp_userInput.length() > 2)
		{
			if (true || alfilelines == null) // needs to be session-scoped probably
			//if (alfilelines == null) // needs to be session-scoped probably
			{
				//O.o("re-read file for auto [" + autocomp_userInput + "] to build alfilelines", true);
				//O.o("TODOC. BEGIN re-read file lineCountToStartKeepAt:"+lineCountToStartKeepAt);
				alfilelines = com.hk.util.UtilFile.fileAsList (fqFileName, new Boolean(false), "", 500);
				//O.o("TODOC. DONE re-read file alfilelines.size():"+alfilelines.size());
				// cache file in mem
				session.setAttribute "alfilelines", alfilelines
			}
			else
			{
				O.o ("in autocomplete no read file");
			}
			O.o("autocomp seeking matches to [" + autocomp_userInput + "] across [" + alfilelines.size() + "] lines.");
			alFileLinesHistoricalNewFirst = com.hk.util.UtilTags.getAutoCompleteLinesOut  (alfilelines, autocomp_userInput, 200, getUser());
			O.o("autocomp got# [" + alFileLinesHistoricalNewFirst.size() + "] matches to [" + autocomp_userInput + "] across [" + alfilelines.size() + "] lines.");
			
			int iCnt = 0;
			alFileLinesHistoricalNewFirst.each {
				iCnt++;
				String tag = it;
				//O.o("in todo ((((((((((((((((  tag [" + tag + "]");
				//tag = tag.replaceAll (autocomp_userInput,"<font color=blue>"+autocomp_userInput+"</font>");
				sb.append ("<br>" + iCnt + ". " + tag +  "&nbsp;&nbsp;");
			}
		} // if len > 2
		else
			O.o("too short for autocomplete [" + autocomp_userInput+ "]");
		String sRtn = sb.toString();
		//O.o("rtn [" + sRtn + "]")
		render sRtn;
		//def results = User.findAll()
		//render results as JSON
		//render new java.util.Date().toString();
	}

	public String getUser()
	{
		//O.o ("auth.getClass:" + auth.getClass().getName().toString());
		//O.o ("session.getClass:" + session.getClass().getName().toString());
		//auth.getClass:org.codehaus.groovy.grails.web.taglib.NamespacedTagDispatcher
		//session.getClass:org.codehaus.groovy.grails.web.servlet.mvc.GrailsHttpSession

		//		//SESSION VARIABLE TEST WORKS
		//		HashMap hmtestSessionScope = session.getAttribute("hmtestSessionScope")
		//		if (hmtestSessionScope == null)
		//		{
		//			O.o("create new hmtestSessionScope")
		//			session.setAttribute "hmtestSessionScope", new HashMap<String, String>();
		//		}
		//		else
		//			O.o("found old htmlsession");
		//		// END SESSION VARIABLE TEST WORKS

		//Object o = auth.user();
		//O.oc ("auth.user.getClass", o);
		// this is an auto-cast
		String s = auth.user();
		//O.oc ("auth.user string .getClass", s);
		return s;
	}


	//
	//
	//	def list = {
	//		params.max = Math.min(params.max ? params.int('max') : 10, 100)
	//		[userInstanceList: User.list(params), userInstanceTotal: User.count()]
	//	}
	//
	//	def search = {
	//		System.err.println ("sys erro in todo:search: params[:id]:" + params.id);
	//		System.out.println ("sys outp in todo:search: params[:id]:" + params.id);
	//		println ("just println in todo:search: params[:id]:" + params.id);
	//		O.o ("O.o")
	//	}
	//
	//	// search page results
	//	def results = {
	//		def users = User.findAllByUserIdLike("%${params.userId}%")
	//		//println user
	//		flash['message'] = 'hello world!'
	//		return [ users: users, term : params.userId ]
	//	}
	//
	//
	//	def create = {
	//		def userInstance = new User()
	//		userInstance.properties = params
	//		//userInstance.setUserId "gggggggggggggg"
	//		return [userInstance: userInstance]
	//	}
	//
	//	def save = {
	//		def userInstance = new User(params)
	//		if (userInstance.save(flush: true)) {
	//			flash.message = "${message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])}"
	//			redirect(action: "show", id: userInstance.id)
	//		}
	//		else {
	//			render(view: "create", model: [userInstance: userInstance])
	//		}
	//	}

	//	def o(String s) {
	//		println(s)
	//	}
	//
	//	def timeline = {
	//
	//		def user = User.findByUserId(params.id)
	//		[ user : user ]
	//	}
	//
	//
	//	def show = {
	//		def userInstance = User.get(params.id)
	//		if (!userInstance) {
	//			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
	//			redirect(action: "list")
	//		}
	//		else {
	//			[userInstance: userInstance]
	//		}
	//	}
	//
	//	def edit = {
	//		def userInstance = User.get(params.id)
	//		if (!userInstance) {
	//			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
	//			redirect(action: "list")
	//		}
	//		else {
	//			return [userInstance: userInstance]
	//		}
	//	}
	//
	//	def update = {
	//		def userInstance = User.get(params.id)
	//		if (userInstance) {
	//			if (params.version) {
	//				def version = params.version.toLong()
	//				if (userInstance.version > version) {
	//
	//					userInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [
	//						message(code: 'user.label', default: 'User')]
	//					as Object[], "Another user has updated this User while you were editing")
	//					render(view: "edit", model: [userInstance: userInstance])
	//					return
	//				}
	//			}
	//			userInstance.properties = params
	//			if (!userInstance.hasErrors() && userInstance.save(flush: true)) {
	//				flash.message = "${message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])}"
	//				redirect(action: "show", id: userInstance.id)
	//			}
	//			else {
	//				render(view: "edit", model: [userInstance: userInstance])
	//			}
	//		}
	//		else {
	//			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
	//			redirect(action: "list")
	//		}
	//	}
	//
	//	def delete = {
	//		def userInstance = User.get(params.id)
	//		if (userInstance) {
	//			try {
	//				userInstance.delete(flush: true)
	//				flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
	//				redirect(action: "list")
	//			}
	//			catch (org.springframework.dao.DataIntegrityViolationException e) {
	//				flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
	//				redirect(action: "show", id: params.id)
	//			}
	//		}
	//		else {
	//			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
	//			redirect(action: "list")
	//		}
	//	}

	//	def emit= // emit(String color, String o)
	//	{
	//		return "<font color=\""+ params.color  +"\">"+ params.o + "</font>";
	//	}



}
