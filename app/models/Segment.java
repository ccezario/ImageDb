package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Segment extends Model {
    
	@Id
	public Long id;
	@Required
	public String name;

	public Segment(String name) {
		super();
		this.name = name;
	}

	public static Finder<Long, Segment> find = new Finder(Long.class, Segment.class);

	public static List<Segment> all() {
		return find.all();
	}

	public static void create(Segment segment) {
		segment.save();
	}

	public static void delete(Long id) {
		find.ref(id).delete();
	}
	
	public static Segment findByName(String name) {
		return find.where().eq("name", name).findUnique();
	}
  
}