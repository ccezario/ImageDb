package controllers;

import java.io.IOException;

import javax.swing.Box.Filler;
import javax.validation.Constraint;

import auth.Secured;

import models.ChildImage;
import models.Image;
import models.User;
import play.api.libs.Crypto;
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
	
	//get: /user
	@Security.Authenticated(Secured.class)
	public static Result createUser() {
		return ok(views.html.createUser.render(userForm, User.findByLogin((request().username()))));
	}
	
	//get: /user/request
	public static Result requestUser() {
		return ok(views.html.requestUser.render(userForm));
	}
	
	//get: /user/edit
	@Security.Authenticated(Secured.class)
	public static Result edit(String login) {
		UserForm user = new UserForm(User.findByLogin(login));
		userForm.fill(user);
		return ok(views.html.user.render(userForm, User.findByLogin((request().username()))));
	}
	
	//get: /users
	@Security.Authenticated(Secured.class)
	public static Result users() {
		return ok(views.html.users.render("", userForm, User.findByLogin((request().username()))));
	}
	
	//get: /usuarios
	@Security.Authenticated(Secured.class)
	public static Result userList() {
		return ok(toJson(User.all()));
	}
	
	//get: /usuario/login/delete
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

	@Security.Authenticated(Secured.class)
	public static Result newUser() throws IOException {
		Form<UserForm> filledForm = userForm.bindFromRequest();
		if (filledForm.hasErrors())
			return badRequest(views.html.createUser.render(filledForm, User.findByLogin((request().username()))));
		User user = new User(filledForm.get().login, Crypto.sign(filledForm.get().password), filledForm.get().email, true, true, filledForm.get().role);
		User.create(user);
		return redirect(routes.Account.users());
	}
	
	public static Result requestNewUser() throws IOException {
		Form<UserForm> filledForm = userForm.bindFromRequest();
		if (filledForm.hasErrors())
			return badRequest(views.html.createUser.render(filledForm, User.findByLogin((request().username()))));
		User user = new User(filledForm.get().login, Crypto.sign(filledForm.get().password), filledForm.get().email, false, false, filledForm.get().role);
		User.create(user);
		return redirect(routes.Account.login());
	}
	
	@Security.Authenticated(Secured.class)
	public static Result editUser() throws IOException {
		Form<UserForm> filledForm = userForm.bindFromRequest();
		if (filledForm.hasErrors())
			return badRequest(views.html.user.render(filledForm, User.findByLogin((request().username()))));
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
			if (User.authenticate(login, Crypto.sign(password)) == null)
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
		public String confirm;
		@Required
		public String email;
		public Boolean isActive;
		public Boolean isApproved;
		@Required
		public int role;
		
		public UserForm(){
		}
		
		public UserForm(User user){
			this.login = user.login;
			this.password = user.password;
			this.email = user.email;
			this.isActive = user.isActive;
			this.isApproved = user.isApproved;
			this.role = user.role;
		}
		
        public String validate() {
            if (isBlank(login)) {
                return "Digite o login";
            }
            if (isBlank(password)) {
                return "Digite a senha";
            }
            if (isBlank(confirm)) {
                return "Digite a confirmacao da senha";
            }
            if (isBlank(email)) {
                return "Digite o e-mail";
            }
            if (role < 1 || role > 3) {
                return "escolha o perfil do usuario";
            }
            if (!password.matches(confirm)){
            	return "senha diferente da confirmacao";
            }
    		User newUser = User.findByLogin(login);
    		if (newUser != null){
    			return "usuario ja cadastrado, escolha outro usuario";
    		}
    		return null;
        }

        private boolean isBlank(String input) {
            return input == null || input.isEmpty() || input.trim().isEmpty();
        }
	}
}