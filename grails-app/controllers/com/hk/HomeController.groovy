package com.hk

import com.grailsinaction.User;
import com.hk.util.O;
import grails.converters.JSON;

class HomeController {
	def	 newUser =	{
		redirect(controller: 'authentication', action: 'index');	
	}
	def index = 
	{
		O.o("in home index")
		//redirect(controller: 'home', action: 'indexhomeajax1');
		//redirect(controller: 'home', action: 'indexhome2');
		//redirect(controller: 'home', action: 'indexhome3');
		//redirect(controller: 'home', action: 'indexhome4');
		redirect(controller: 'home', action: 'indexhome5');
	}
	def indexhomeajax1 = {}
	def indexhomeajax2 = {}
	def indexhomeajax3 = {}
	def indexhomeajax4 = {}
	def indexhome2 ={}
	def indexhomeajaxnojq1 ={}
	def indexhomeajaxnojq2 ={}
	def indexhomeajaxnojq3 ={}
	def indexhomeajaxnojq4 ={}
	def indexhomeajaxnojq5 ={}
	
	
	def autocompleteSearch = {
		O.o("in home.autocompleteSearch")
		def results = com.grailsinaction.User.findAllByUserIdLike("%g%").userId
		//def results = User.findAll()
		render results as JSON
	 }
	//		def results = User.withCriteria {
	//			like('keyword', params.term + '%')
	//		}
	
	def lkp1 = 
	{
		def res = User.findAllByUserIdLike("%g%").userId
		render res 
	}
	def lkp2 =
	{
		def res = User.findAllByUserIdLike("%g%")
		render res as JSON
	}
	// from Book in http://alexduan.com/2011/02/17/grails-jquery-and-ajax/
	def ajaxFindUserByTopic = {
		render User.findAllByUserIdLike("%g%").userId
	}
	
	def delete = {
		render "Book was deleted"
  }


}	
