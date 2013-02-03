package controllers;

import java.io.IOException;

import javax.validation.Constraint;

import auth.Secured;

import models.ChildImage;
import models.Image;
import models.User;
import play.data.Form;
import play.data.validation.Constraints.Required;
import play.mvc.Controller;
import play.mvc.Security;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;
import static play.libs.Json.toJson;

public class Account extends Controller {

	static Form<UserForm> userForm = form(UserForm.class);
	static Form<LoginForm> loginForm = form(LoginForm.class);
	
	public static Result login() {
		return ok(views.html.login.render(loginForm));
	}
	
	public static Result user() {
		return ok(views.html.createUser.render(userForm));
	}
	
	@Security.Authenticated(Secured.class)
	public static Result users() {
		return ok(views.html.users.render("", userForm));
	}
	
	@Security.Authenticated(Secured.class)
	public static Result userList() {
		return ok(toJson(User.all()));
	}
	
	@Security.Authenticated(Secured.class)
	public static Result deleteUser(String login) {
		User.delete(null, login);
		return redirect(routes.Account.users());
	}
	
	public static Result authenticate() throws IOException {
		Form<LoginForm> filledForm = loginForm.bindFromRequest();
		if (filledForm.hasErrors()) {
			return badRequest(views.html.login.render(filledForm));
		} else {
			session("login", filledForm.get().login);
			return redirect(routes.Home.index(1,"id", "asc"));
		}
	}
	
	public static Result logout() {
	    session().clear();
	    flash("success", "You've been logged out");
	    return redirect(
	        routes.Account.login()
	    );
	}

	public static Result newUser() throws IOException {
		Form<UserForm> filledForm = userForm.bindFromRequest();
		User newUser = User.findByLogin(filledForm.get().login);
		if (newUser != null)
			return badRequest(views.html.createUser.render(filledForm));
		User user = new User(filledForm.get().login, filledForm.get().password, filledForm.get().email, false, false, 1);
		User.create(user);
		return redirect(routes.Account.login());
	}
	
	@Security.Authenticated(Secured.class)
	public static Result editUser() throws IOException {
		Form<UserForm> filledForm = userForm.bindFromRequest();
		if (filledForm.hasErrors())
			return badRequest(views.html.user.render(filledForm));
		User user = new User(filledForm.get().login, filledForm.get().password, filledForm.get().email, false, false, 1);
		User.update(user);
		return redirect(routes.Account.userList());
	}
	
	public static class LoginForm {
		 
		  @Required
		  public String login;
		  @Required
		  public String password;
		  
		  public String validate() {
			if (User.authenticate(login, password) == null)
				return "Usurio ou senha invlida";
		    return null;
		  }
		 
	}
	
	public static class UserForm {
		 
		@Required
		public String login;
		@Required
		public String password;
		@Required
		public String email;
		public Boolean isActive;
		public Boolean isApproved;
		@Required
		public int role;
				  
	}
}