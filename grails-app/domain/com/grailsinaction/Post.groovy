package com.grailsinaction
class Post {
	String content
	Date dateCreated
	static constraints = {
		content(blank: false)
	}
	static belongsTo = [ user : User ]
	static mapping = {
		profile lazy:false
		posts sort:'dateCreated'
	}
	static hasMany = [ tags : Tag ]

}

