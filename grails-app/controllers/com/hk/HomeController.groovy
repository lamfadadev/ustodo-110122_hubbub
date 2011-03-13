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
		O.o("in indexhome")
		redirect(controller: 'home', action: 'indexhome');
		
	}
	def indexhome = 
	{
		O.o("in home indexhome")
		
	}
	
	def autocompleteSearch = {
//		def results = User.withCriteria {
//			like('keyword', params.term + '%')
//		}
		def results = User.findAll()
		render results as JSON
	 }
	

}	
