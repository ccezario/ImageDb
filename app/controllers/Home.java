package controllers;

import auth.Secured;
import play.mvc.Security;
import models.Category;
import models.ChildImage;
import models.Image;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class Home extends Controller {

	static Form<Image> imageForm = form(Image.class);

	// get: /
	@Security.Authenticated(Secured.class)
	public static Result index(int page, String sortBy, String order) {
		return ok(
	            views.html.index.render(
	                    ChildImage.page(page, 10, sortBy, order, Image.all()),
	                    sortBy, order, User.findByLogin((request().username())), Category.all()
	                )
		);
	}
}
