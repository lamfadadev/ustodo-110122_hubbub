package com.grailsinaction
import com.hk.util.O;

import org.apache.tomcat.util.digester.ObjectParamRule;
import org.codehaus.groovy.grails.commons.DefaultGrailsDomainClass



class UserController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def index = {
		redirect(action: "list", params: params)
	}





	def hbktest4 = {

		def sb = new StringBuffer()
		sb.append "<p>hi hen"
		try
		{
			User u = new User(userId: "useriHKd", password: "passwdHK");
			if (null == u.save())
			{
				sb.append "<p>save fail"
				throw new Exception ("bad bad")
			}	
			else
			{
				sb.append "<p>save OK"
			}
		} catch ( Exception e ) {
			e.printStackTrace()
		}
		O.o sb+""
		render sb+""
	}
	// list all user ids
	def hbktest3 = {
		def sb = new StringBuffer();
		def h = User.findAll()
		h.eachWithIndex { x, i ->
			sb.append "<hr><font color=blue>NEW USER #" + i + "</font>:" + x.class.name
			//sb.append it.persistentProperties
			//			def d = new DefaultGrailsDomainClass(User.class)
			def mpv = x.getMetaPropertyValues()
			mpv.eachWithIndex { y,j ->
				sb.append "<hr>i.j:" + i +"." + j + ". <font color=red>y.class.name</font>:" + y.class.name
				sb.append "<br>i.j:" + i +"." + j + ". y.getName():" + y.getName()
				sb.append "<br>i.j:" + i +"." + j + ". y.getValue():" + y.getValue()
				sb.append "<br>i.j:" + i +"." + j + ". y.getType():" + y.getType()
			}

		}
		render sb
	}

	// list persistent properties of a sample user instance object
	def hbktest2 = {
		// see http://stackoverflow.com/questions/4555150/gorm-persistent-properties
		def sb = new StringBuffer();
		def d = new DefaultGrailsDomainClass(User.class)
		def abc = d.persistentProperties
		sb.append "<p><font color=red>prop list class</font>:" + abc.getClass()
		abc.each {
			sb.append "<p><font color=green>prop instance</font>" +it
		}
		render sb
	}
	def hbktest = {
		def sb = new StringBuffer();
		def h = User.findAll()

		h.each {
			User u = (User) it;
			//			sb.append "<br>"+u.getClass().getName();
			sb.append "<p>userid:"+u.userId;
			def nonPersistent = ["log", "class", "constraints", "properties", "errors", "mapping", "metaClass"]
			def newMap = [:]
			u.getProperties().each { property ->
				if (nonPersistent.contains(property.key)) {
					newMap.put property.key, property.value;
					sb.append "<br>prop:key [[[[" + property.key + "]]]] [[" + property.value + "]]"
				}
			}

			render sb;

			//        def h = User.getMetaPropertyValues()
			//
			//
			//
			//		o ("hkhkhk:" + h)
			//		h.eachWithIndex { x, i ->
			//			//groovy.lang.PropertyValue p = it;
			//			sb.append "<br> hbk:" + i + "  " ;
			//			sb.append "x.name [" + x.name + "] ";
			////			sb.append "x.getClass() [" + x.getClass() +"] ";
			////			sb.append "x.value [" + x.value +"] ";
			////			if (x.value != null)
			////				sb.append "x.getValue().getClass()  [" + x.getValue().getClass() +"] ";
			//		}


		}
	}









	def list = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[userInstanceList: User.list(params), userInstanceTotal: User.count()]
	}

	def search = {
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

	def o(String s)
	{
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

					userInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'user.label', default: 'User')] as Object[], "Another user has updated this User while you were editing")
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
}
