package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.avaje.ebean.Page;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class ChildImage extends Model {
    
  @Id
  public Long id;
  @Required
  public String resolution;
  @Required
  public String link;
    
  
  public ChildImage(String resolution, String link) {
	super();
	this.resolution = resolution;
	this.link = link;
}

public static Finder<Long,ChildImage> find = new Finder(
    Long.class, ChildImage.class
  );
  
public static List<ChildImage> all() {
  return find.all();
}

public static void create(ChildImage ChildImage) {
  ChildImage.save();
}

public static void delete(Long id) {
  find.ref(id).delete();
}

public static Page<ChildImage> page(int page, int pageSize, String sortBy, String order, List<Image> images) {
    return
        find.where()
        	.eq("resolution", "thumb")
            .orderBy(sortBy + " " + order)
            .findPagingList(pageSize)
            .getPage(page);
}
  
}