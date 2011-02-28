import com.grailsinaction.User
import grails.test.*
import com.grailsinaction.Profile
import com.grailsinaction.User
import com.hk.util.O;

try { 
    User u = new User(userId: "us3riHKd", password: "pas3wdHK");
    if( !u.save() ) {
        u.errors.each {
            println "ERA:" + it
        }
    }
    else
        O.o "looks good on save"
        
} catch ( Exception e ) {
    e.printStackTrace()
}


def h = User.findAll()

O.o "asdasd:" + h.getClass()
O.o "asdasd:" + h.size()

h.each {it.persistentProperties}


h.

        O.o ("start");

try {
    new User(userId: 'hank5', password: 'hiqwep5',
            profile: new Profile(email: 'hank5@hanksmith5.com')).save(flush: true)
    //        new User(userId: 'peter', password: 'sesame',
    //                profile: new Profile(homepage: 'http://www.peter.com/')).save()
    def user = User.findByPassword('hiqwep5')

    //assertEquals 'peter', user.userId

    O.o (String.format("retrieved newly saved user.userId:%s", user.userId));
} catch ( Exception e ) {
    e.printStackTrace()
    O.o("errorhk")
}

def user = User.findByUserId('glen', [fetch:[posts:'eager']])


def now = new Date()
def users = User.findAllBySignupDateBetween(now-100, now)
assertEquals 2, users.size()


O.o ("done");

