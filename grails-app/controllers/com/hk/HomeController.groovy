package com.hk

import com.grailsinaction.User;
import com.hk.util.O;
import grails.converters.JSON;

class HomeController {
	def nexxwUser =	{
		redirect(controller: 'authentication', action: 'index');	
	}
	def index = 
	{
		O.o("in home index")
		redirect(controller: 'home', action: 'indexhome3');
	}
	def indexhome = {}
	def indexhome2 ={}
	def indexhome3 ={}
	
	def autocompleteSearch = {
		O.o("in home.autocompleteSearch")
		def results = User.findAll()
		render results as JSON
	 }
	//		def results = User.withCriteria {
	//			like('keyword', params.term + '%')
	//		}
	

}	
