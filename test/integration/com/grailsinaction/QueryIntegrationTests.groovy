package com.grailsinaction

import grails.test.*
import com.grailsinaction.Profile
import com.grailsinaction.User
import com.hk.util.O;

class QueryIntegrationTests extends GrailsUnitTestCase {

	void testBasicDynamicFinders()
	{
		O.o "hi hk:"
		new User(userId: 'glen', password: 'secret',
		profile: new Profile(email: 'glen@glensmith.com')).save(flush: true)
		new User(userId: 'peter', password: 'sesame',
		profile: new Profile(homepage: 'http://www.peter.com/')).save()
		def user = User.findByPassword('sesame')
		assertEquals 'peter', user.userId
		user = User.findByUserIdAndPassword('glen',
		'secret')
		assertEquals 'glesn', user.userId
		def now = new Date()
		def users =
		User.findAllByDateCreatedBetween(now-1, now)
		assertEquals 2, users.size()
		def profiles =
		Profile.findAllByEmailIsNotNull()
		assertEquals 1, profiles.size()
	}

	// p 111 in book
	void testQueryByExample() {
		try {
			new User(userId: 'glen', password: 'password').save()
			new User(userId: 'peter', password: 'password').save()
			new User(userId: 'cynthia', password: 'sesame').save()
			def userToFind = new User(userId: 'glen')
			def u1 = User.find(userToFind)
			assertEquals('password', u1.password)
			userToFind = new User(userId: 'cynthia')
			def u2 = User.find(userToFind)
			assertEquals('cynthia', u2.userId)
			userToFind = new User(password: 'password')
			def u3 = User.findAll(userToFind)
			assertEquals(['glen', 'peter'], u3*.userId)
		} catch ( Exception e ) {
			e.printStackTrace()
		}
	}

	void testHbkcoding() {
		try {
			O.oc (Post);

			def users = User.list([sort: 'userId', order: 'asc',
				max: 5, fetch: [posts: 'eager']])

			users.each { println it }

			O.oc (createCriteria());
			O.oc (createCriteria());

			def entries = Post.createCriteria().withCriteria {
				and {
					eq('user', user)
					between('created', new Date()-1, new Date())
				}
			}

			entries.each { println it }
			O.o "done"
			O.o("done2")
		} catch ( Exception e) {
			e.printStackTrace();
			O.o("done")
		}
	}

	void xxtesthbk() {
		try {
			def users = User.list([sort: 'userId', order: 'asc',
				max: 5, fetch: [posts: 'eager']])
			entries.each {
				println it
			}
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
	}
}





















