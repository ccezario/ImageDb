package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class User extends Model {
    
	@Id
	public String login;
	public String password;
	public String email;
	public Boolean isActive;
	public Boolean isApproved;
	public int role;

	public User(String login, String password, String email, Boolean isActive, Boolean isApproved, int role) {
		super();
		this.login = login;
		this.password = password;
		this.email = email;
		this.isActive = isActive;
		this.isApproved = isApproved;
		this.role = role;
	}

	public static Finder<Long, User> find = new Finder(Long.class, User.class);

	public static List<User> all() {
		return find.all();
	}

	public static void create(User user) {
		user.save();
	}
	
	public static void update(User user) {
		user.update();
	}

	public static void delete(Long id, String login) {
		find.where().eq("login", login).findUnique().delete();
	}
  
	public static User findByLogin(String login) {
		return find.where().eq("login", login).findUnique();
	}
	
	public static User authenticate(String login, String password) {
		return find.where().eq("login", login).eq("password", password).findUnique();
	}
}