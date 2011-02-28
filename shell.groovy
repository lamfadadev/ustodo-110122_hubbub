def t()
{
    try
    { 
       def user = new com.grailsinaction.User(userId: 'joedd',     homepage:'http:jjj.kk.com',           password: 'secrdet')
        
       // user.save()
        
        user.userId
 //       user.save(flush:true)
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
    e.printStackTrace()
}

}

t()