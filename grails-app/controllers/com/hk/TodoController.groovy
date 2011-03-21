package com.hk

import com.hk.util.O;
import com.hk.util.FileLine;
import com.hk.util.UrlConverterHttp;;
import com.grailsinaction.User;
import grails.converters.JSON;

import org.apache.tomcat.util.digester.ObjectParamRule;
import org.codehaus.groovy.grails.commons.DefaultGrailsDomainClass


class TodoController {
	def authenticationService
	private List<String> alfilelines = null;
	private List<String> altags = null;
	private List<String> alTagsHistoricalNewFirst = null;
	private String user1 = null;
	private String fqFileName = null;
	private String fqFileName_srchHist = null;
	private String fqFileName_cachedCagtegs = null;
	private Set<String> setTagsAlreadyKnown = null;
	//fqFileName_srchHist = '/Users/hkon/sw/ustodo/favs' + user1 + '_srchHist.csv';
	//fqFileName_cachedCagtegs = '/Users/hkon/sw/ustodo/favs' + user1 + '_cachedCagtegs.csv';

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	private static int seq = 0;

	def autocompleteSearch = {
		String srchStr = params['textstr'];
		String autocomp = params['autocomp'];
		if (autocomp == null)
		{
			render "empty autocomp";
			return;
		}
		StringBuffer sb = new StringBuffer();
		if (alfilelines == null) // needs to be session-scoped probably 
		{
			O.o("re-read file");
			user1 = getUser();
			fqFileName = '/Users/hkon/sw/ustodo/favs' + user1 + '.csv';
			setTagsAlreadyKnown = new HashSet();
			long fileLen = com.hk.util.UtilFile.fileLen (fqFileName);
			long lineCountToStartKeepAt = fileLen - 500;
			if (lineCountToStartKeepAt < 0)
				lineCountToStartKeepAt = 0
			alfilelines = com.hk.util.UtilFile.fileAsList (fqFileName, new Boolean(false), "", lineCountToStartKeepAt);
			alTagsHistoricalNewFirst = com.hk.util.UtilTags.convertMultiTagLinesToTagArray  (alfilelines, setTagsAlreadyKnown, autocomp);
		}
		int iCnt = 0;
		alTagsHistoricalNewFirst.each {
			iCnt++;
			String tag = it;
			sb.append (iCnt + ". " + it.toString() +  "  /  ");
		}		
		String sRtn = sb.toString();
		//O.o("rtn [" + sRtn + "]")
		render sRtn;
		//def results = User.findAll()
		//render results as JSON
		//render new java.util.Date().toString();
	}


	private String getUser()
	{
		String s = auth.user();
		//O.o ("index auth.user():" + s);
		return s;
	}

	def mainhk1 = {

	}

	def index2 = {
		def results = User.findAll()
		render results as JSON
	}

	def index = {
		//O.o(new java.util.Date().toString() + ": in todo controller index");
		if (!authenticationService.isLoggedIn(request))
		{
			//response.sendError(403)
			redirect(controller:"authentication", action:"index")
		} else {
			//			O.o ("index user:" + getUser());
			user1 = getUser();
			fqFileName = '/Users/hkon/sw/ustodo/favs' + user1 + '.csv';
			fqFileName_srchHist = '/Users/hkon/sw/ustodo/favs' + user1 + '_srchHist.csv';
			fqFileName_cachedCagtegs = '/Users/hkon/sw/ustodo/favs' + user1 + '_cachedCagtegs.csv';

			seq++;
			String mode = ""

			boolean bwrite = false;

			final String srchstr = (String) params.srchstr;

			def alFileLines = new ArrayList<FileLine>();
			if (srchstr == null || (srchstr).trim().equals("")) {
				srchstr = "grails"
			}

			srchstr = srchstr.trim()
			String s3="blue"
			//if (seq %2 == 0)
			//s3="purple"

			if ( !(new File(fqFileName).exists()))
			{
				O.o("create new file/for new user:" + fqFileName);
				File f = new File(fqFileName);
				f.write("");
			}
			if ( !(new File(fqFileName_srchHist).exists()))
			{
				O.o("create new file/for new user:" + fqFileName_srchHist);
				File f2 = new File(fqFileName_srchHist);
				f2.write("");
			}
			//			else
			//				O.o("SEEMS LIKE FILE EXISTS:" + fqFileName);

			// WRITE WRITE WRITE WRITE WRITE WRITE WRITE WRITE WRITE WRITE WRITE WRITE WRITE
			if (srchstr.trim().startsWith("w ") || srchstr.trim().endsWith(" w") )
			{
				bwrite = true;
				if (srchstr.trim().startsWith("w "))
					srchstr = srchstr[2..-1] // remove "w "
				if (srchstr.trim().endsWith(" w"))
					srchstr = srchstr[0..(srchstr.length()-2)] // remove " w"

				srchstr = srchstr.trim();

				String lineout = com.hk.util.UtilDate.getDateForFile() + " " + srchstr;
				//O.o("saved lineout:" + lineout.toString());
				//O.o("try existing file:" + fqFileName );
				new File(fqFileName).withWriterAppend { out ->
					out.writeLine(lineout);
				}
				mode = "Saved: [" + lineout+ "] &nbsp; to ["+fqFileName+"]"

				flash['message'] = " [" + lineout + "]"
				//O.o("write pos last / [" + srchstr.lastIndexOf('/') + "] on str [" + srchstr + "]");
				srchstr = srchstr [0..(srchstr.lastIndexOf(' / ')-1)] + " / "
				//O.o "ss:" + srchstr;
				//if (seq %2 == 0)
				s3="green"
				//else
				//s3="orange"
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

			O.o("!!!!!!!!!! user [" + user1 +  "] search at " + new java.util.Date() + " [" + srchstrPostWriteStripInstance+ "]") ;
			//(srchstrPostWriteStripInstance.split(" ")).eachWithIndex
			//{ srchWrd, ii ->
			//  O.o("################## search word set" + ii + " [" + srchWrd + "]");
			//}
			//now do multi-search output
			File f = new File(fqFileName)
			int i = 0
			// FOR EACH FILE LINE
			f.eachLine
			{
				i++; // 1based line num counter
				String fileLineRaw = ((String) it).trim();
				String fileLineRawLower = fileLineRaw.toLowerCase();
				// FOR EACH SRCH WORD MATCH MATCH MATCH MATCH MATCH
				boolean hitRemove = false;
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

			[hk2: "aassdd", srchstr: srchstr, seq:seq, alFileLines: alFileLines , cbword: params.cbword
						, cborder: params.cborder, hktest: "hkteststr", maxAge: params.maxAge, alFileLines: alFileLines,
						fqFileName: ("<font color=\""+s3+"\">"+mode), seq:seq,  user1:user1]
		}
	}







	def list = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[userInstanceList: User.list(params), userInstanceTotal: User.count()]
	}

	def search = {
		System.err.println ("sys erro in todo:search: params[:id]:" + params.id);
		System.out.println ("sys outp in todo:search: params[:id]:" + params.id);
		println ("just println in todo:search: params[:id]:" + params.id);
		O.o ("O.o")
	}

	// search page results
	def results = {
		def users = User.findAllByUserIdLike("%${params.userId}%")
		//println user
		flash['message'] = 'hello world!'
		return [ users: users, term : params.userId ]
	}


	def create = {
		def userInstance = new User()
		userInstance.properties = params
		//userInstance.setUserId "gggggggggggggg"
		return [userInstance: userInstance]
	}

	def save = {
		def userInstance = new User(params)
		if (userInstance.save(flush: true)) {
			flash.message = "${message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])}"
			redirect(action: "show", id: userInstance.id)
		}
		else {
			render(view: "create", model: [userInstance: userInstance])
		}
	}

	def o(String s) {
		println(s)
	}

	def timeline = {

		def user = User.findByUserId(params.id)
		[ user : user ]
	}


	def show = {
		def userInstance = User.get(params.id)
		if (!userInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
			redirect(action: "list")
		}
		else {
			[userInstance: userInstance]
		}
	}

	def edit = {
		def userInstance = User.get(params.id)
		if (!userInstance) {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
			redirect(action: "list")
		}
		else {
			return [userInstance: userInstance]
		}
	}

	def update = {
		def userInstance = User.get(params.id)
		if (userInstance) {
			if (params.version) {
				def version = params.version.toLong()
				if (userInstance.version > version) {

					userInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [
						message(code: 'user.label', default: 'User')]
					as Object[], "Another user has updated this User while you were editing")
					render(view: "edit", model: [userInstance: userInstance])
					return
				}
			}
			userInstance.properties = params
			if (!userInstance.hasErrors() && userInstance.save(flush: true)) {
				flash.message = "${message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])}"
				redirect(action: "show", id: userInstance.id)
			}
			else {
				render(view: "edit", model: [userInstance: userInstance])
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
			redirect(action: "list")
		}
	}

	def delete = {
		def userInstance = User.get(params.id)
		if (userInstance) {
			try {
				userInstance.delete(flush: true)
				flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
				redirect(action: "list")
			}
			catch (org.springframework.dao.DataIntegrityViolationException e) {
				flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
				redirect(action: "show", id: params.id)
			}
		}
		else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
			redirect(action: "list")
		}
	}

	def emit= // emit(String color, String o)
	{
		return "<font color=\""+ params.color  +"\">"+ params.o + "</font>";
	}


}
