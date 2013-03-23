package controllers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.codehaus.jackson.format.DataFormatMatcher;
import org.imgscalr.Scalr.Mode;

import controllers.Account.UserForm;

import models.Image;
import models.Category;
import models.ChildImage;
import models.Segment;
import models.User;
import play.api.libs.Crypto;
import play.data.Form;
import play.data.validation.Constraints.Required;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;
import play.mvc.Security;
import auth.Secured;

import static org.imgscalr.Scalr.*;

public class Application extends Controller {

	static Form<Image> imageForm = form(Image.class);
	static Form<SearchForm> searchForm = form(SearchForm.class);

	//get: /imagem
	@Security.Authenticated(Secured.class)
	public static Result index() {
		return ok(views.html.createImage.render(imageForm, Category.all(), Segment.all(), User.findByLogin((request().username()))));
	}
	
	//get: /imagem/edit/id
	@Security.Authenticated(Secured.class)
	public static Result image(Long id) {
		imageForm = form(Image.class).fill(Image.find.ref(id));
		return ok(views.html.editImage.render(imageForm, Category.all(), Segment.all(), User.findByLogin((request().username()))));
	}
	
	//get: /imagem/detail/id
	@Security.Authenticated(Secured.class)
	public static Result imageDetail(Long id) {
		return ok(views.html.imageDetail.render(Image.find.byId(id), User.findByLogin((request().username()))));
	}
	
	//get: /search
	@Security.Authenticated(Secured.class)
    public static Result listImages() {
		Form<SearchForm> filledForm = searchForm.bindFromRequest();
        return ok(
            views.html.index.render(
                ChildImage.page(0, 10, "id", "asc", Image.findBySearch(filledForm.get().search)),
                "id", "asc", User.findByLogin((request().username())), Category.all()
            )
        );
    }
    
    //get: todo
	@Security.Authenticated(Secured.class)
    public static Result filtros(int filtro, int value) {
        return ok(
            views.html.index.render(
                ChildImage.page(0, 10, "id", "asc", Image.filterImages()),
                "id", "asc", User.findByLogin((request().username())), Category.all()
            )
        );
    }
	
	// post: /imagem
	@Security.Authenticated(Secured.class)
	public static Result newImage() throws IOException {
		Form<Image> filledForm = imageForm.bindFromRequest();
		if (filledForm.hasErrors()) {
			return badRequest(views.html.createImage.render(filledForm, Category.all(), Segment.all(),
								User.findByLogin((request().username()))));
		} else {
			Image img = filledForm.get();
			FilePart imgFile = request().body().asMultipartFormData()
					.getFile("picture");
			saveImages(img, imgFile);
			Image.create(img);
			return redirect(routes.Home.index(1,"id", "asc"));
		}
	}
	
	// post: /imagens/edit/:id
	@Security.Authenticated(Secured.class)
	public static Result editImage() throws IOException {
		Form<Image> filledForm = imageForm.bindFromRequest();
		if (filledForm.hasErrors()) {
			return badRequest(views.html.editImage.render(filledForm, Category.all(), Segment.all(),
					User.findByLogin((request().username()))));
		} else {
			Image img = filledForm.get();
			Image.update(img);
			return redirect(routes.Home.index(0,"id", "asc"));
		}
	}

	// post: /imagens/:id/delete
	@Security.Authenticated(Secured.class)
	public static Result deleteImage(Long id) {
		Image.delete(id);
		return redirect(routes.Home.index(1,"id", "asc"));
	}

	private static void saveImages(Image img, FilePart imgFile)
			throws IOException {
		List<ChildImage> child = new ArrayList();
		for (int i = 0; i <= 2; i++) {
			String extension = imgFile.getContentType()
					.substring(imgFile.getContentType().lastIndexOf("/"))
					.replace("/", ".");
			String fileName = Crypto.sign(img.name).concat(String.valueOf(i)).concat(
					extension);
			File file;
			if (i == 0){
				file = new File("C:/play-2.0/ImageDB/public/images/".concat(fileName));
				ImageIO.write(createThumbnail(ImageIO.read(imgFile.getFile())),
						extension.replace(".", ""), file);
				child.add(new ChildImage("thumb", "images/".concat(file.getName())));
			}
			if (i == 1){
				file = new File("C:/play-2.0/ImageDB/public/images/".concat(fileName));
				ImageIO.write(createLowResolution(ImageIO.read(imgFile.getFile())),
						extension.replace(".", ""), file);
				child.add(new ChildImage("low", "images/".concat(file.getName())));
			}
			if (i == 2){
				file = new File("C:/play-2.0/ImageDB/public/images/".concat(fileName));
				ImageIO.write(ImageIO.read(imgFile.getFile()),
						extension.replace(".", ""), file);
				child.add(new ChildImage("high", "images/".concat(file.getName())));
			}
		}
		img.childImage = child;
	}

	//post: todo
	@Security.Authenticated(Secured.class)
	public static Result newCategory(String name) throws IOException {
		Category category = new Category(name);
		Category.create(category);
		return redirect(routes.Home.index(1,"id", "asc"));
	}

	public static BufferedImage createThumbnail(BufferedImage img) {
		img = resize(img, Method.ULTRA_QUALITY, Mode.FIT_TO_WIDTH, 250, 200);
		return img;
	}
	
	public static BufferedImage createLowResolution(BufferedImage img) {
		img = resize(img, Method.ULTRA_QUALITY, Mode.FIT_TO_WIDTH, 1500, 1000);
		return img;
	}
	
	public static class SearchForm {
		@Required
		public String search;
	}
}
