package com.grailsinaction

import grails.test.*

class UserTests extends GrailsUnitTestCase {
	protected void setUp() {
		super.setUp()
	}

	protected void tearDown() {
		super.tearDown()
	}

	void testSomething() {

	}

	void testFirstSaveEver() {
		try 
		{
			def user = new User(userId: 'joe2', password: 'secre2t',
					homepage: 'http://www.grailsinac2tion.com')
			assertNotNull user.save()
			assertNotNull user.id
			def foundUser = User.get(user.id)
			assertEquals 'joe', foundUser.userId
		} catch ( Exception e ) 
		{
			e.printStackTrace();
		}
	}

}
