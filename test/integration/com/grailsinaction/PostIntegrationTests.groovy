package com.grailsinaction

import grails.test.*

class PostIntegrationTests extends GroovyTestCase {
	protected void setUp() {
		super.setUp()
	}

	protected void tearDown() {
		super.tearDown()
	}

	void testSomething() {

	}

	void testFirstPost() {
		def user = new User(userId: 'joedd',				password: 'secrdet')
		def post1 = new Post(content: "First post... W00t!")
		user.addToPosts(post1)
		def post2 = new Post(content: "Second post...")
		user.addToPosts(post2)
		def post3 = new Post(content: "Third post...")
		user.addToPosts(post3)
		user.save()
		assertEquals 3, User.get(user.userId).posts.size()
	}
}
