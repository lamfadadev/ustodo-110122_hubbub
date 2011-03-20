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

	/* 
	 * autocompleteSearch reference
	 * I got this working pretty easily in my app. The biggest "hurdle" I had was jQuery's "$" function 
	 * conflicting with the Prototype $ function that is included with Grails. In jQuery, the $() 
	 * function is just an alias for jQuery(). So if you use jQuery() instead of $() for everything 
	 * related to the jQuery autocomplete, you should be fine. 
	 */
	
	def indexhomeajax1 = {O.o("in indexhomeajax1")}
	def indexhomeajax2 = {O.o("in indexhomeajax2")}
	def indexhomeajax3 = {O.o("in indexhomeajax3")}
	def indexhomeajax3a = {O.o("in indexhomeajax3a")}
	def indexhomeajax4 = {O.o("in indexhomeajax4")}
	def indexhomeajax5 = {O.o("in indexhomeajax5")}
	def indexhomeajax6 = {O.o("in indexhomeajax6")}
	def indexhome2 ={}
	def indexhomeajaxnojq1 ={}
	def indexhomeajaxnojq2 ={}
	def indexhomeajaxnojq3 ={}
	def indexhomeajaxnojq4 ={}
	def indexhomeajaxnojq5 ={}
	
	
	def autocompleteSearch = {
		O.o("in home.autocompleteSearch [" + params['textstr'] + "]")
		def results = com.grailsinaction.User.findAllByUserIdLike("%g%").userId
		//def results = User.findAll()
		//render results as JSON
		render "hihkhkhk"
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
	
// FOR indexhomeajax4
	
def continentSelected = {
       def lst
       def continent = params['continent']
       if ( continent == 'America') {
           lst = ['USA', 'Argentina']
       } else if (continent == 'Asia') {
           lst = ['China', 'India']
       } else if (continent == 'Europa') {
           lst = ['Slovakia', 'Serbia']
       } else if (continent == 'Africa') {
           lst = ['Egypt', 'Tunis']
       } else if (continent == 'Australia') {
           render ""
           return
       } else {
           render ""
           return
       }
	   Object o = g.select (from:lst);
	   render "hi henry";
}

}	
