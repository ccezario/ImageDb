package auth;

import controllers.routes;
import play.mvc.Result;
import play.mvc.Http.Context;
import play.mvc.Security;

public class Secured extends Security.Authenticator {

    public String getUsername(Context ctx) {
        return ctx.session().get("login");
    }
   
    public Result onUnauthorized(Context ctx) {
        return redirect(routes.Account.login());
    }
}
