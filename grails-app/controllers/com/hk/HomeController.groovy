package com.hk

class HomeController {
	def nexxwUser =	{
		redirect(controller: 'authentication', action: 'index');	
	}
	def indexhome = 
	{
		
	}
	
	def autocompleteSearch = {
		def results = User.withCriteria {
			ilike 'keyword', params.term + '%'
		}
		//render results as JSON
	 }
	

}	
