package com.hk

import com.grailsinaction.User;

class HomeController {
	def nexxwUser =	{
		redirect(controller: 'authentication', action: 'index');	
	}
	def indexhome = 
	{
		
	}
	
	def autocompleteSearch = {
		def results = User.withCriteria {
			like('keyword', params.term + '%')
		}
		//render results as JSON
	 }
	

}	
