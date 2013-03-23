package controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

import com.avaje.ebean.Expr;
import com.avaje.ebean.Page;

import javax.swing.Box.Filler;
import javax.validation.Constraint;

import auth.Secured;

import models.ChildImage;
import models.Image;
import models.Role;
import models.User;
import play.api.libs.Crypto;
import play.data.Form;
import play.data.validation.Constraints.Required;
import play.libs.Json;
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
		userForm = form(UserForm.class).fill(new UserForm(User.findByLogin(login)));
		return ok(views.html.editUser.render(userForm, User.findByLogin((request().username()))));
	}
	
	//get: /users
	@Security.Authenticated(Secured.class)
	public static Result users() {
		return ok(views.html.users.render(User.findByLogin((request().username()))));
	}
	
	//get: /user/approve
	@Security.Authenticated(Secured.class)
	public static Result userApprove(String login) {
		User user = User.findByLogin(login);
		User userLoggedIn = User.findByLogin(request().username());
		if (userLoggedIn.role==Role.Administrador){
			user.isApproved=true;
			User.update(user);
			return ok(toJson("sucess"));
		}
		return ok(toJson("authetication"));
	}
	
	//get: /user/activate
	@Security.Authenticated(Secured.class)
	public static Result userActivate(String login) {
		User user = User.findByLogin(login);
		User userLoggedIn = User.findByLogin(request().username());
		if (userLoggedIn.role==Role.Administrador){
			user.isActive=true;
			User.update(user);
			return ok(toJson("sucess"));
		}
		return ok(toJson("authentication"));
	}
	
	//get: /user/deactivate
	@Security.Authenticated(Secured.class)
	public static Result userDeactivate(String login) {
		User user = User.findByLogin(login);
		User userLoggedIn = User.findByLogin(request().username());
		if (userLoggedIn.role==Role.Administrador){
			user.isActive=false;
			User.update(user);
			return ok(toJson("sucess"));
		}
		return ok(toJson("authentication"));
	}
	
	//get: /usuarios
	@Security.Authenticated(Secured.class)
	public static Result userList() {
		Map<String, String[]> params = request().queryString();
	    Integer iTotalRecords = User.find.findRowCount();
	    String filter = params.get("sSearch")[0];
	    Integer pageSize = Integer.valueOf(params.get("iDisplayLength")[0]);
	    Integer page = Integer.valueOf(params.get("iDisplayStart")[0]) / pageSize;
		String sortBy = "login";
	    String order = params.get("sSortDir_0")[0];
	    
	    Page<User> usersPage = User.listUsers(page, pageSize, sortBy, order, filter);
	 
	    Integer iTotalDisplayRecords = usersPage.getTotalRowCount();
	    ObjectNode result = Json.newObject();
	 
	    result.put("sEcho", Integer.valueOf(params.get("sEcho")[0]));
	    result.put("iTotalRecords", iTotalRecords);
	    result.put("iTotalDisplayRecords", iTotalDisplayRecords);
	 
	    ArrayNode an = result.putArray("aaData");
	 
	    for(User u : usersPage.getList()) {
	      ObjectNode row = Json.newObject();
	      row.put("0", u.login);
	      row.put("1", u.email);
	      row.put("2", u.role.name());
	      if(u.isActive)
	    	  row.put("3", "<input type=checkbox id=isActive"+u.login+" value="+u.login+
	    			  " onclick=active(value) checked >");
	      else
		      row.put("3", "<input type=checkbox id=isActive"+u.login+" value="+u.login+
		    		  " onclick=active(value) >");
	      if(u.isApproved)
	    	  row.put("4", "<input type=checkbox id=isApproved"+u.login+" value="+u.login+
	    			  " onclick=approve(value) checked disabled>");
	      else
		      row.put("4", "<input type=checkbox id=isApproved"+u.login+" value="+u.login+
		    		  " onclick=approve(value) >");
	      row.put("5", "<a href=usuario/edit/"+u.login+"><i class=icon-pencil></i></a><a href=usuario/"+
	    		  		u.login+"/delete role=button data-toggle=modal><i class=icon-remove></i></a>");
	      an.add(row);
	    }
		return ok(result);
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
			return badRequest(views.html.editUser.render(filledForm, User.findByLogin((request().username()))));
		User user = new User(filledForm.get().login, filledForm.get().password, filledForm.get().email, false, false, filledForm.get().role);
		User.update(user);
		return redirect(routes.Account.users());
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
		 
		public String login;
		public String password;
		public String confirm;
		public String email;
		public Boolean isActive;
		public Boolean isApproved;
		public Role role;
		
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
            if (isBlank(email)) {
                return "Digite o e-mail";
            }
            if (!role.name().matches("RH") && !role.name().matches("Usuario") && !role.name().matches("Administrador")) {
                return "escolha o perfil do usuario";
            }
    		if (request().path().contains("edit")){
    			return null;
    		}
            if (isBlank(password)) {
       			return "Digite a senha";
            }
            if (isBlank(confirm)) {
                return "Digite a confirmacao da senha";
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