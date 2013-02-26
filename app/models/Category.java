package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Category extends Model {
    
	@Id
	public Long id;
	@Required
	public String name;
    @Required
    public boolean isSegment;

	public Category(String name, boolean isSegment) {
		super();
		this.name = name;
        this.isSegment = isSegment;
	}

	public static Finder<Long, Category> find = new Finder(Long.class, Category.class);

	public static List<Category> all() {
		return find.all();
	}

	public static void create(Category category) {
		category.save();
	}

	public static void delete(Long id) {
		find.ref(id).delete();
	}
	
	public static Category findByName(String name) {
		return find.where().eq("name", name).findUnique();
	}
  
}