package models;

import java.util.*;

import play.data.validation.Constraints.*;
import play.db.ebean.*;
import play.data.format.*;
import javax.persistence.*;

@Entity
public class Image extends Model {
    
	@Id
	public Long id;
	@Required
	public String name;
	@Required
	public int orientation;
	@Required
	public Boolean isEditorial;
	@Required
	public Boolean isPopulated;
	@Required
	public String useTerm;
	@Required
	@Formats.DateTime(pattern = "dd/MM/yyyy")
	public Date valDate;
	@OneToMany(cascade=CascadeType.ALL)
	public List<ChildImage> childImage;
	public String tag;
	@ManyToMany(cascade=CascadeType.PERSIST)
	public List<Category> category;
    
	
	public Image(String name, int orientation, Boolean isEditorial,
			Boolean isPopulated, String useTerm, Date valDate,
			List<ChildImage> childImage, String tag, List<Category> category) {
		super();
		this.name = name;
		this.orientation = orientation;
		this.isEditorial = isEditorial;
		this.isPopulated = isPopulated;
		this.useTerm = useTerm;
		this.valDate = valDate;
		this.childImage = childImage;
		this.tag = tag;
		this.category = category;
	}

	public static Finder<Long, Image> find = new Finder(Long.class,
			Image.class);

	public static List<Image> all() {
		return find.all();
	}

	public static void create(Image image) {
		image.save();
	}

	public static void update(Image image) {
		image.update();
	}
	
	public static void delete(Long id) {
		find.ref(id).delete();
	}
	
	public static List<Image> filterImages() {
		return find.all();
	}
	
	public static List<Image> findByTag(String search) {
		return find.where().like("tag", "%"+search+"%").findList();
	}
}