package com.hk

class HomeController {
	def newUser =	{
		redirect(controller: 'authentication', action: 'index');	
	}
}	
