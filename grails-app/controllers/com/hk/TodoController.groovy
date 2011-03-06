package com.hk

import com.hk.util.O;
import com.hk.util.FileLine;
import com.hk.util.O;
import com.grailsinaction.User;

import org.apache.tomcat.util.digester.ObjectParamRule;
import org.codehaus.groovy.grails.commons.DefaultGrailsDomainClass


class TodoController {
	def authenticationService
	
	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	private static int seq = 0;
	
	
	private String getUser()
	{
		String s = auth.user();
		//O.o ("index auth.user():" + s);
		return s;
	}
	
	def index = {
		
		if (!authenticationService.isLoggedIn(request))
		{
			//response.sendError(403)
			redirect(controller:"authentication", action:"index")
		} else {
			//			O.o ("index user:" + getUser());
			String user1 = getUser();
			String fqFileName = '/Users/hkon/sw/ustodo/favs' + user1 + '.csv';
						
			seq++;
			String mode = ""
	
			boolean bwrite = false;
	
			final String srchstr = (String) params.srchstr;
	
			def alFileLines = new ArrayList<FileLine>();
			if (srchstr == null || (srchstr).trim().equals("")) {
				srchstr = "recurring"
			}
	
			srchstr = srchstr.trim()
			String s3="blue"
			if (seq %2 == 0)
				s3="purple"
	
			if ( !(new File(fqFileName).exists()))
			{
				O.o("create new file:" + fqFileName);		
				File f = new File(fqFileName);
				f.write("");
			}
//			else
//				O.o("SEEMS LIKE FILE EXISTS:" + fqFileName);
				
				// WRITE
			if (srchstr.trim().startsWith("w ") || srchstr.trim().endsWith(" w") )
			{
				bwrite = true;
				if (srchstr.trim().startsWith("w "))
					srchstr = srchstr[2..-1] // remove "w "
				if (srchstr.trim().endsWith(" w"))
					srchstr = srchstr[0..(srchstr.length()-2)] // remove " w"
	
				String lineout = com.hk.util.UtilDate.getDateForFile() + " " + srchstr;
	
				O.o("try existing file:" + fqFileName);
				new File(fqFileName).withWriterAppend { out ->
					out.writeLine(lineout);
				}
				
				flash['message'] = " [" + lineout + "]"
				srchstr = srchstr [0..(srchstr.lastIndexOf('/')-1)]
				if (seq %2 == 0)
					s3="red"
				else
					s3="orange"
				mode = "wrote [" + srchstr+ "] &nbsp;"
			}
	
			String srchstrPostWriteStripInstance = srchstr;
	
			//now do multi-search output
			File f = new File(fqFileName)
			int i = 0
			// FOR EACH FILE LINE
			f.eachLine
			{
				String fileLineRaw = ((String) it).trim();
				// FOR EACH SRCH WORD
				boolean hitRemove = false;
				(srchstrPostWriteStripInstance.split(" ")).eachWithIndex
				{ srchWrd, ii ->
					//O.o("working on word " + ii + " [" + srchWrd + "]");
					srchWrd = srchWrd.trim();
					if (!hitRemove) 
					{
						if (srchWrd.startsWith("-")) // subtractive search
	                    {
							O.o ((new Date()).toString() + "in sub testing neg on [" + srchWrd[1..-1] + "]");
							if (fileLineRaw.contains(srchWrd[1..-1]))
							{
								hitRemove = true;
								O.o ((new Date()).toString() + "hit remove yes false");
							}						
						}
						else // positive search
						{
							if (!fileLineRaw.contains(srchWrd))
							{
								hitRemove = true;
							}
						}
					}
				}
				// IF ALL WORDS MATCHED THIS LINE, KEEP THE LINE
				if (hitRemove == false)
					alFileLines.add (new FileLine(i++, fileLineRaw));
	
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
