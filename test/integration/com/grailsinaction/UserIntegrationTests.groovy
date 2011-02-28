package com.grailsinaction

import grails.test.*

class UserIntegrationTests extends GroovyTestCase {
	protected void setUp() {
		super.setUp()
	}

	protected void tearDown() {
		super.tearDown()
	}

	void testSomething() {

	}
	
void testhk2()
{
	def user = new com.grailsinaction.User(userId: 'joedd',                password: 'secrdet')
	
	user.userId
	user.save(flush:true)
	
   println "r:" + user.password
   
	   println "count:" + com.grailsinaction.User.count()
}
	
	void testFirstSaveEver() {
		println "in here hkhkhk"
		def user = new User(userId: 'joe', password: 'secret',
		homepage: 'http://www.grailsinaction.com')
		assertNotNull user.save()
		assertNotNull user.id
		def foundUser = User.get(user.id)
		assertEquals 'joe', foundUser.userId
	}
	
	void testSaveThenDelete() {
		def user = new User(userId: 'joe', password: 'secret',
		homepage: 'http://www.grailsinaction.com')
		assertNotNull user.save()
		def foundUser = User.get(user.id)
		foundUser.delete()
		assertFalse User.exists(foundUser.id)
		}
	
//	void testEvilSave() {
//		def user = new User(userId: 'chuck_norris',
//		password: 'tiny', homepage: 'not-a-url')
//		assertFalse user.validate()
//		assertTrue user.hasErrors()
//		def errors = user.errors
//		assertEquals "size.toosmall",
//		errors.getFieldError("password").code
//		assertEquals "tiny",
//		errors.getFieldError("password").rejectedValue
//		assertEquals "url.invalid",
//		errors.getFieldError("homepage").code
//		assertEquals "not-a-url",
//		errors.getFieldError("homepage").rejectedValue
//		assertNull errors.getFieldError("userId")
//		}
	
	
	void testEvilSaveCorrected() {
		def user = new User(userId: 'chuck_norris',
		password: 'tiny', homepage: 'not-a-url')
		assertFalse(user.validate())
		assertTrue(user.hasErrors())
		assertNull user.save()
		user.password = "fistfist"
		user.homepage = "http://www.chucknorrisfacts.com"
		assertTrue(user.validate())
		assertFalse(user.hasErrors())
		assertNotNull user.save()
		}
	
	def t()
	{
		try
		{
		   //def user = new com.grailsinaction.User(userId: 'joedd',     homepage:'http:jjj.kk.com',           password: 'secrdet')
		   def user = new com.grailsinaction.User(userId: 'joedd',     password: 'secrdet')
		   
	        user.save(flush:true)
			
			user.userId
			   if( !user.save() )
			   {
	   user.errors.each {
			println it
	   }
	}
		   println "r:" + user.password
		   
			   println com.grailsinaction.User.count()
			   
	}
	catch(Throwable t)
	{
	println"asdasdas:"
		t.printStackTrace()
	}
	
	}
	
	void testhk()
	{
		t()
	}
	
}

