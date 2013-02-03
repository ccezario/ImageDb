import static org.imgscalr.Scalr.resize;
import play.*;
import play.libs.*;
import com.avaje.ebean.Ebean;
import models.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr.Method;
import org.imgscalr.Scalr.Mode;
import org.joda.time.DateTime;

public class Global extends GlobalSettings {
    @Override
    public void onStart(Application app){
        // Check if the database is empty
        if (User.find.findRowCount() == 0) {
            User.create(new User("1","1","1@1.com",true,true,1));
            User.create(new User("2","2","2@2.com",true,true,2));
            User.create(new User("3","3","3@3.com",true,true,3));
        }
        if (Category.find.findRowCount() == 0) {
        	Category.create(new Category("Polinesia"));
        	Category.create(new Category("Yosemite"));
        }
        if (Image.find.findRowCount() == 0){
        	try {
        		File file = new File("C:/play-2.0/ImageDB/public/images/DSC_0261.jpg");
        		File fOutput = new File("C:/play-2.0/ImageDB/public/images/DSC_0261_thumb.jpg");
        		ImageIO.write(createThumbnail(ImageIO.read(file)),
						"jpeg", fOutput);
        		fOutput = new File("C:/play-2.0/ImageDB/public/images/DSC_0261_low.jpg");
        		ImageIO.write(createLowResolution(ImageIO.read(file)),
						"jpeg", fOutput);
        		List<ChildImage> ciList = new ArrayList<ChildImage>();
        		ChildImage ci = new ChildImage("thumb", "images/DSC_0261_thumb.jpg");
        		ciList.add(ci);
        		ci = new ChildImage("low", "images/DSC_0261_low.jpg");
        		ciList.add(ci);
        		ci = new ChildImage("high", "images/DSC_0261.jpg");
        		ciList.add(ci);
        		List<Category> ctList = new ArrayList<Category>();
        		ctList.add(Category.findByName("Yosemite"));
        		Image.create(new Image("DCS_0261", 1, true, false, "trererdfgs asdfhbas asdf", DateTime.now().toDate(),
        			ciList, "praia, bora, pacifico, resort", ctList));
        	// outra imagem
        		file = new File("C:/play-2.0/ImageDB/public/images/DSC_0268.jpg");
        		fOutput = new File("C:/play-2.0/ImageDB/public/images/DSC_0268_thumb.jpg");
        		ImageIO.write(createThumbnail(ImageIO.read(file)),
						"jpeg", fOutput);
        		fOutput = new File("C:/play-2.0/ImageDB/public/images/DSC_0268_low.jpg");
        		ImageIO.write(createLowResolution(ImageIO.read(file)),
						"jpeg", fOutput);
        		ciList.clear();
        		ci = new ChildImage("thumb", "images/DSC_0268_thumb.jpg");
        		ciList.add(ci);
        		ci = new ChildImage("low", "images/DSC_0268_low.jpg");
        		ciList.add(ci);
        		ci = new ChildImage("high", "images/DSC_0268.jpg");
        		ciList.add(ci);
        		ctList.clear();
        		ctList.add(Category.findByName("Yosemite"));
        		Image.create(new Image("DCS_0268", 1, true, false, "trererdfgs asdfhbas asdf", DateTime.now().toDate(),
        			ciList, "praia, bora, pacifico, resort", ctList));
        		// outra imagem
        		file = new File("C:/play-2.0/ImageDB/public/images/DSC_0328.jpg");
        		fOutput = new File("C:/play-2.0/ImageDB/public/images/DSC_0328_thumb.jpg");
        		ImageIO.write(createThumbnail(ImageIO.read(file)),
						"jpeg", fOutput);
        		fOutput = new File("C:/play-2.0/ImageDB/public/images/DSC_0328_low.jpg");
        		ImageIO.write(createLowResolution(ImageIO.read(file)),
						"jpeg", fOutput);
        		ciList.clear();
        		ci = new ChildImage("thumb", "images/DSC_0328_thumb.jpg");
        		ciList.add(ci);
        		ci = new ChildImage("low", "images/DSC_0328_low.jpg");
        		ciList.add(ci);
        		ci = new ChildImage("high", "images/DSC_0328.jpg");
        		ciList.add(ci);
        		ctList.clear();
        		ctList.add(Category.findByName("Yosemite"));
        		Image.create(new Image("DCS_0328", 2, true, false, "trererdfgs asdfhbas asdf", DateTime.now().toDate(),
        			ciList, "praia, bora, pacifico, resort", ctList));
        		// outra imagem
        		file = new File("C:/play-2.0/ImageDB/public/images/DSC_0331.jpg");
        		fOutput = new File("C:/play-2.0/ImageDB/public/images/DSC_0331_thumb.jpg");
        		ImageIO.write(createThumbnail(ImageIO.read(file)),
						"jpeg", fOutput);
        		fOutput = new File("C:/play-2.0/ImageDB/public/images/DSC_0331_low.jpg");
        		ImageIO.write(createLowResolution(ImageIO.read(file)),
						"jpeg", fOutput);
        		ciList.clear();
        		ci = new ChildImage("thumb", "images/DSC_0331_thumb.jpg");
        		ciList.add(ci);
        		ci = new ChildImage("low", "images/DSC_0331_low.jpg");
        		ciList.add(ci);
        		ci = new ChildImage("high", "images/DSC_0331.jpg");
        		ciList.add(ci);
        		ctList.clear();
        		ctList.add(Category.findByName("Yosemite"));
        		Image.create(new Image("DCS_0331", 1, true, false, "trererdfgs asdfhbas asdf", DateTime.now().toDate(),
        			ciList, "praia, bora, pacifico, resort", ctList));
        		// outra imagem
        		file = new File("C:/play-2.0/ImageDB/public/images/DSC_0348.jpg");
        		fOutput = new File("C:/play-2.0/ImageDB/public/images/DSC_0348_thumb.jpg");
        		ImageIO.write(createThumbnail(ImageIO.read(file)),
						"jpeg", fOutput);
        		fOutput = new File("C:/play-2.0/ImageDB/public/images/DSC_0348_low.jpg");
        		ImageIO.write(createLowResolution(ImageIO.read(file)),
						"jpeg", fOutput);
        		ciList.clear();
        		ci = new ChildImage("thumb", "images/DSC_0348_thumb.jpg");
        		ciList.add(ci);
        		ci = new ChildImage("low", "images/DSC_0348_low.jpg");
        		ciList.add(ci);
        		ci = new ChildImage("high", "images/DSC_0348.jpg");
        		ciList.add(ci);
        		ctList.clear();
        		ctList.add(Category.findByName("Yosemite"));
        		Image.create(new Image("DCS_0348", 1, true, false, "trererdfgs asdfhbas asdf", DateTime.now().toDate(),
        			ciList, "praia, bora, pacifico, resort", ctList));
        		// outra imagem
        		file = new File("C:/play-2.0/ImageDB/public/images/DSC_0371.jpg");
        		fOutput = new File("C:/play-2.0/ImageDB/public/images/DSC_0371_thumb.jpg");
        		ImageIO.write(createThumbnail(ImageIO.read(file)),
						"jpeg", fOutput);
        		fOutput = new File("C:/play-2.0/ImageDB/public/images/DSC_0371_low.jpg");
        		ImageIO.write(createLowResolution(ImageIO.read(file)),
						"jpeg", fOutput);
        		ciList.clear();
        		ci = new ChildImage("thumb", "images/DSC_0371_thumb.jpg");
        		ciList.add(ci);
        		ci = new ChildImage("low", "images/DSC_0371_low.jpg");
        		ciList.add(ci);
        		ci = new ChildImage("high", "images/DSC_0371.jpg");
        		ciList.add(ci);
        		ctList.clear();
        		ctList.add(Category.findByName("Polinesia"));
        		Image.create(new Image("DCS_0371", 1, true, false, "trererdfgs asdfhbas asdf", DateTime.now().toDate(),
        			ciList, "praia, bora, pacifico, resort", ctList));
        		// outra imagem
        		file = new File("C:/play-2.0/ImageDB/public/images/DSC_0382.jpg");
        		fOutput = new File("C:/play-2.0/ImageDB/public/images/DSC_0382_thumb.jpg");
        		ImageIO.write(createThumbnail(ImageIO.read(file)),
						"jpeg", fOutput);
        		fOutput = new File("C:/play-2.0/ImageDB/public/images/DSC_0382_low.jpg");
        		ImageIO.write(createLowResolution(ImageIO.read(file)),
						"jpeg", fOutput);
        		ciList.clear();
        		ci = new ChildImage("thumb", "images/DSC_0382_thumb.jpg");
        		ciList.add(ci);
        		ci = new ChildImage("low", "images/DSC_0382_low.jpg");
        		ciList.add(ci);
        		ci = new ChildImage("high", "images/DSC_0382.jpg");
        		ciList.add(ci);
        		ctList.clear();
        		ctList.add(Category.findByName("Polinesia"));
        		Image.create(new Image("DCS_0382", 1, true, false, "trererdfgs asdfhbas asdf", DateTime.now().toDate(),
        			ciList, "praia, bora, pacifico, resort", ctList));
        		// outra imagem
        		file = new File("C:/play-2.0/ImageDB/public/images/DSC_0398.jpg");
        		fOutput = new File("C:/play-2.0/ImageDB/public/images/DSC_0398_thumb.jpg");
        		ImageIO.write(createThumbnail(ImageIO.read(file)),
						"jpeg", fOutput);
        		fOutput = new File("C:/play-2.0/ImageDB/public/images/DSC_0398_low.jpg");
        		ImageIO.write(createLowResolution(ImageIO.read(file)),
						"jpeg", fOutput);
        		ciList.clear();
        		ci = new ChildImage("thumb", "images/DSC_0398_thumb.jpg");
        		ciList.add(ci);
        		ci = new ChildImage("low", "images/DSC_0398_low.jpg");
        		ciList.add(ci);
        		ci = new ChildImage("high", "images/DSC_0398.jpg");
        		ciList.add(ci);
        		ctList.clear();
        		ctList.add(Category.findByName("Polinesia"));
        		Image.create(new Image("DCS_0398", 1, true, false, "trererdfgs asdfhbas asdf", DateTime.now().toDate(),
        			ciList, "praia, bora, pacifico, resort", ctList));
        		// outra imagem
        		file = new File("C:/play-2.0/ImageDB/public/images/DSC_0470.jpg");
        		fOutput = new File("C:/play-2.0/ImageDB/public/images/DSC_0470_thumb.jpg");
        		ImageIO.write(createThumbnail(ImageIO.read(file)),
						"jpeg", fOutput);
        		fOutput = new File("C:/play-2.0/ImageDB/public/images/DSC_0470_low.jpg");
        		ImageIO.write(createLowResolution(ImageIO.read(file)),
						"jpeg", fOutput);
        		ciList.clear();
        		ci = new ChildImage("thumb", "images/DSC_0470_thumb.jpg");
        		ciList.add(ci);
        		ci = new ChildImage("low", "images/DSC_0470_low.jpg");
        		ciList.add(ci);
        		ci = new ChildImage("high", "images/DSC_0470.jpg");
        		ciList.add(ci);
        		ctList.clear();
        		ctList.add(Category.findByName("Yosemite"));
        		Image.create(new Image("DCS_0470", 2, true, false, "trererdfgs asdfhbas asdf", DateTime.now().toDate(),
        			ciList, "praia, bora, pacifico, resort", ctList));
        		// outra imagem
        		file = new File("C:/play-2.0/ImageDB/public/images/DSC_0507.jpg");
        		fOutput = new File("C:/play-2.0/ImageDB/public/images/DSC_0507_thumb.jpg");
        		ImageIO.write(createThumbnail(ImageIO.read(file)),
						"jpeg", fOutput);
        		fOutput = new File("C:/play-2.0/ImageDB/public/images/DSC_0507_low.jpg");
        		ImageIO.write(createLowResolution(ImageIO.read(file)),
						"jpeg", fOutput);
        		ciList.clear();
        		ci = new ChildImage("thumb", "images/DSC_0507_thumb.jpg");
        		ciList.add(ci);
        		ci = new ChildImage("low", "images/DSC_0507_low.jpg");
        		ciList.add(ci);
        		ci = new ChildImage("high", "images/DSC_0507.jpg");
        		ciList.add(ci);
        		ctList.clear();
        		ctList.add(Category.findByName("Yosemite"));
        		Image.create(new Image("DCS_0507", 1, true, false, "trererdfgs asdfhbas asdf", DateTime.now().toDate(),
        			ciList, "praia, bora, pacifico, resort", ctList));
        		// outra imagem
        		file = new File("C:/play-2.0/ImageDB/public/images/DSC_0529.jpg");
        		fOutput = new File("C:/play-2.0/ImageDB/public/images/DSC_0529_thumb.jpg");
        		ImageIO.write(createThumbnail(ImageIO.read(file)),
						"jpeg", fOutput);
        		fOutput = new File("C:/play-2.0/ImageDB/public/images/DSC_0529_low.jpg");
        		ImageIO.write(createLowResolution(ImageIO.read(file)),
						"jpeg", fOutput);
        		ciList.clear();
        		ci = new ChildImage("thumb", "images/DSC_0529_thumb.jpg");
        		ciList.add(ci);
        		ci = new ChildImage("low", "images/DSC_0529_low.jpg");
        		ciList.add(ci);
        		ci = new ChildImage("high", "images/DSC_0529.jpg");
        		ciList.add(ci);
        		ctList.clear();
        		ctList.add(Category.findByName("Yosemite"));
        		Image.create(new Image("DCS_0529", 1, true, false, "trererdfgs asdfhbas asdf", DateTime.now().toDate(),
        			ciList, "praia, bora, pacifico, resort", ctList));
        		// outra imagem
        		file = new File("C:/play-2.0/ImageDB/public/images/DSC_0569.jpg");
        		fOutput = new File("C:/play-2.0/ImageDB/public/images/DSC_0569_thumb.jpg");
        		ImageIO.write(createThumbnail(ImageIO.read(file)),
						"jpeg", fOutput);
        		fOutput = new File("C:/play-2.0/ImageDB/public/images/DSC_0569_low.jpg");
        		ImageIO.write(createLowResolution(ImageIO.read(file)),
						"jpeg", fOutput);
        		ciList.clear();
        		ci = new ChildImage("thumb", "images/DSC_0569_thumb.jpg");
        		ciList.add(ci);
        		ci = new ChildImage("low", "images/DSC_0569_low.jpg");
        		ciList.add(ci);
        		ci = new ChildImage("high", "images/DSC_0569.jpg");
        		ciList.add(ci);
        		ctList.clear();
        		ctList.add(Category.findByName("Polinesia"));
        		Image.create(new Image("DCS_0569", 1, true, false, "trererdfgs asdfhbas asdf", DateTime.now().toDate(),
        			ciList, "praia, bora, pacifico, resort", ctList));
        		// outra imagem
        		file = new File("C:/play-2.0/ImageDB/public/images/DSC_0580.jpg");
        		fOutput = new File("C:/play-2.0/ImageDB/public/images/DSC_0580_thumb.jpg");
        		ImageIO.write(createThumbnail(ImageIO.read(file)),
						"jpeg", fOutput);
        		fOutput = new File("C:/play-2.0/ImageDB/public/images/DSC_0580_low.jpg");
        		ImageIO.write(createLowResolution(ImageIO.read(file)),
						"jpeg", fOutput);
        		ciList.clear();
        		ci = new ChildImage("thumb", "images/DSC_0580_thumb.jpg");
        		ciList.add(ci);
        		ci = new ChildImage("low", "images/DSC_0580_low.jpg");
        		ciList.add(ci);
        		ci = new ChildImage("high", "images/DSC_0580.jpg");
        		ciList.add(ci);
        		ctList.clear();
        		ctList.add(Category.findByName("Polinesia"));
        		Image.create(new Image("DCS_0580", 1, true, false, "trererdfgs asdfhbas asdf", DateTime.now().toDate(),
        			ciList, "praia, bora, pacifico, resort", ctList));
        		// outra imagem
        		file = new File("C:/play-2.0/ImageDB/public/images/DSC_0606.jpg");
        		fOutput = new File("C:/play-2.0/ImageDB/public/images/DSC_0606_thumb.jpg");
        		ImageIO.write(createThumbnail(ImageIO.read(file)),
						"jpeg", fOutput);
        		fOutput = new File("C:/play-2.0/ImageDB/public/images/DSC_0606_low.jpg");
        		ImageIO.write(createLowResolution(ImageIO.read(file)),
						"jpeg", fOutput);
        		ciList.clear();
        		ci = new ChildImage("thumb", "images/DSC_0606_thumb.jpg");
        		ciList.add(ci);
        		ci = new ChildImage("low", "images/DSC_0606_low.jpg");
        		ciList.add(ci);
        		ci = new ChildImage("high", "images/DSC_0606.jpg");
        		ciList.add(ci);
        		ctList.clear();
        		ctList.add(Category.findByName("Polinesia"));
        		Image.create(new Image("DCS_0606", 1, true, false, "trererdfgs asdfhbas asdf", DateTime.now().toDate(),
        			ciList, "praia, bora, pacifico, resort", ctList));
        		// outra imagem
        		file = new File("C:/play-2.0/ImageDB/public/images/DSC_0609.jpg");
        		fOutput = new File("C:/play-2.0/ImageDB/public/images/DSC_0609_thumb.jpg");
        		ImageIO.write(createThumbnail(ImageIO.read(file)),
						"jpeg", fOutput);
        		fOutput = new File("C:/play-2.0/ImageDB/public/images/DSC_0609_low.jpg");
        		ImageIO.write(createLowResolution(ImageIO.read(file)),
						"jpeg", fOutput);
        		ciList.clear();
        		ci = new ChildImage("thumb", "images/DSC_0609_thumb.jpg");
        		ciList.add(ci);
        		ci = new ChildImage("low", "images/DSC_0609_low.jpg");
        		ciList.add(ci);
        		ci = new ChildImage("high", "images/DSC_0609.jpg");
        		ciList.add(ci);
        		ctList.clear();
        		ctList.add(Category.findByName("Yosemite"));
        		Image.create(new Image("DCS_0609", 2, true, false, "trererdfgs asdfhbas asdf", DateTime.now().toDate(),
        			ciList, "praia, bora, pacifico, resort", ctList));
        		// outra imagem
        		file = new File("C:/play-2.0/ImageDB/public/images/DSC_0710.jpg");
        		fOutput = new File("C:/play-2.0/ImageDB/public/images/DSC_0710_thumb.jpg");
        		ImageIO.write(createThumbnail(ImageIO.read(file)),
						"jpeg", fOutput);
        		fOutput = new File("C:/play-2.0/ImageDB/public/images/DSC_0710_low.jpg");
        		ImageIO.write(createLowResolution(ImageIO.read(file)),
						"jpeg", fOutput);
        		ciList.clear();
        		ci = new ChildImage("thumb", "images/DSC_0710_thumb.jpg");
        		ciList.add(ci);
        		ci = new ChildImage("low", "images/DSC_0710_low.jpg");
        		ciList.add(ci);
        		ci = new ChildImage("high", "images/DSC_0710.jpg");
        		ciList.add(ci);
        		ctList.clear();
        		ctList.add(Category.findByName("Yosemite"));
        		Image.create(new Image("DCS_0710", 2, true, false, "trererdfgs asdfhbas asdf", DateTime.now().toDate(),
        			ciList, "praia, bora, pacifico, resort", ctList));
        		// outra imagem
        		file = new File("C:/play-2.0/ImageDB/public/images/DSC_0738.jpg");
        		fOutput = new File("C:/play-2.0/ImageDB/public/images/DSC_0738_thumb.jpg");
        		ImageIO.write(createThumbnail(ImageIO.read(file)),
						"jpeg", fOutput);
        		fOutput = new File("C:/play-2.0/ImageDB/public/images/DSC_0738_low.jpg");
        		ImageIO.write(createLowResolution(ImageIO.read(file)),
						"jpeg", fOutput);
        		ciList.clear();
        		ci = new ChildImage("thumb", "images/DSC_0738_thumb.jpg");
        		ciList.add(ci);
        		ci = new ChildImage("low", "images/DSC_0738_low.jpg");
        		ciList.add(ci);
        		ci = new ChildImage("high", "images/DSC_0738.jpg");
        		ciList.add(ci);
        		ctList.clear();
        		ctList.add(Category.findByName("Yosemite"));
        		Image.create(new Image("DCS_0738", 2, true, false, "trererdfgs asdfhbas asdf", DateTime.now().toDate(),
        			ciList, "praia, bora, pacifico, resort", ctList));
        		// outra imagem
        		file = new File("C:/play-2.0/ImageDB/public/images/DSC_0744.jpg");
        		fOutput = new File("C:/play-2.0/ImageDB/public/images/DSC_0744_thumb.jpg");
        		ImageIO.write(createThumbnail(ImageIO.read(file)),
						"jpeg", fOutput);
        		fOutput = new File("C:/play-2.0/ImageDB/public/images/DSC_0744_low.jpg");
        		ImageIO.write(createLowResolution(ImageIO.read(file)),
						"jpeg", fOutput);
        		ciList.clear();
        		ci = new ChildImage("thumb", "images/DSC_0744_thumb.jpg");
        		ciList.add(ci);
        		ci = new ChildImage("low", "images/DSC_0744_low.jpg");
        		ciList.add(ci);
        		ci = new ChildImage("high", "images/DSC_0744.jpg");
        		ciList.add(ci);
        		ctList.clear();
        		ctList.add(Category.findByName("Yosemite"));
        		Image.create(new Image("DCS_0744", 2, true, false, "trererdfgs asdfhbas asdf", DateTime.now().toDate(),
        			ciList, "praia, bora, pacifico, resort", ctList));
        		// outra imagem
        		file = new File("C:/play-2.0/ImageDB/public/images/DSC_0745.jpg");
        		fOutput = new File("C:/play-2.0/ImageDB/public/images/DSC_0745_thumb.jpg");
        		ImageIO.write(createThumbnail(ImageIO.read(file)),
						"jpeg", fOutput);
        		fOutput = new File("C:/play-2.0/ImageDB/public/images/DSC_0745_low.jpg");
        		ImageIO.write(createLowResolution(ImageIO.read(file)),
						"jpeg", fOutput);
        		ciList.clear();
        		ci = new ChildImage("thumb", "images/DSC_0745_thumb.jpg");
        		ciList.add(ci);
        		ci = new ChildImage("low", "images/DSC_0745_low.jpg");
        		ciList.add(ci);
        		ci = new ChildImage("high", "images/DSC_0745.jpg");
        		ciList.add(ci);
        		ctList.clear();
        		ctList.add(Category.findByName("Yosemite"));
        		Image.create(new Image("DCS_0745", 2, true, false, "trererdfgs asdfhbas asdf", DateTime.now().toDate(),
        			ciList, "praia, bora, pacifico, resort", ctList));
        		// outra imagem
        		file = new File("C:/play-2.0/ImageDB/public/images/DSC_0753.jpg");
        		fOutput = new File("C:/play-2.0/ImageDB/public/images/DSC_0753_thumb.jpg");
        		ImageIO.write(createThumbnail(ImageIO.read(file)),
						"jpeg", fOutput);
        		fOutput = new File("C:/play-2.0/ImageDB/public/images/DSC_0753_low.jpg");
        		ImageIO.write(createLowResolution(ImageIO.read(file)),
						"jpeg", fOutput);
        		ciList.clear();
        		ci = new ChildImage("thumb", "images/DSC_0753_thumb.jpg");
        		ciList.add(ci);
        		ci = new ChildImage("low", "images/DSC_0753_low.jpg");
        		ciList.add(ci);
        		ci = new ChildImage("high", "images/DSC_0753.jpg");
        		ciList.add(ci);
        		ctList.clear();
        		ctList.add(Category.findByName("Yosemite"));
        		Image.create(new Image("DCS_0753", 1, true, false, "trererdfgs asdfhbas asdf", DateTime.now().toDate(),
        			ciList, "praia, bora, pacifico, resort", ctList));
        		// outra imagem
        		file = new File("C:/play-2.0/ImageDB/public/images/DSC_0268.jpg");
        		fOutput = new File("C:/play-2.0/ImageDB/public/images/DSC_0268_thumb.jpg");
        		ImageIO.write(createThumbnail(ImageIO.read(file)),
						"jpeg", fOutput);
        		fOutput = new File("C:/play-2.0/ImageDB/public/images/DSC_0268_low.jpg");
        		ImageIO.write(createLowResolution(ImageIO.read(file)),
						"jpeg", fOutput);
        		ciList.clear();
        		ci = new ChildImage("thumb", "images/DSC_0268_thumb.jpg");
        		ciList.add(ci);
        		ci = new ChildImage("low", "images/DSC_0268_low.jpg");
        		ciList.add(ci);
        		ci = new ChildImage("high", "images/DSC_0268.jpg");
        		ciList.add(ci);
        		ctList.clear();
        		ctList.add(Category.findByName("Yosemite"));
        		Image.create(new Image("DCS_0268", 1, true, false, "trererdfgs asdfhbas asdf", DateTime.now().toDate(),
        			ciList, "praia, bora, pacifico, resort", ctList));
        		// outra imagem
        		file = new File("C:/play-2.0/ImageDB/public/images/DSC_0755.jpg");
        		fOutput = new File("C:/play-2.0/ImageDB/public/images/DSC_0755_thumb.jpg");
        		ImageIO.write(createThumbnail(ImageIO.read(file)),
						"jpeg", fOutput);
        		fOutput = new File("C:/play-2.0/ImageDB/public/images/DSC_0755_low.jpg");
        		ImageIO.write(createLowResolution(ImageIO.read(file)),
						"jpeg", fOutput);
        		ciList.clear();
        		ci = new ChildImage("thumb", "images/DSC_0755_thumb.jpg");
        		ciList.add(ci);
        		ci = new ChildImage("low", "images/DSC_0755_low.jpg");
        		ciList.add(ci);
        		ci = new ChildImage("high", "images/DSC_0755.jpg");
        		ciList.add(ci);
        		ctList.clear();
        		ctList.add(Category.findByName("Yosemite"));
        		Image.create(new Image("DCS_0755", 2, true, false, "trererdfgs asdfhbas asdf", DateTime.now().toDate(),
        			ciList, "praia, bora, pacifico, resort", ctList));
        		// outra imagem
        		file = new File("C:/play-2.0/ImageDB/public/images/DSC_0756.jpg");
        		fOutput = new File("C:/play-2.0/ImageDB/public/images/DSC_0756_thumb.jpg");
        		ImageIO.write(createThumbnail(ImageIO.read(file)),
						"jpeg", fOutput);
        		fOutput = new File("C:/play-2.0/ImageDB/public/images/DSC_0756_low.jpg");
        		ImageIO.write(createLowResolution(ImageIO.read(file)),
						"jpeg", fOutput);
        		ciList.clear();
        		ci = new ChildImage("thumb", "images/DSC_0756_thumb.jpg");
        		ciList.add(ci);
        		ci = new ChildImage("low", "images/DSC_0756_low.jpg");
        		ciList.add(ci);
        		ci = new ChildImage("high", "images/DSC_0756.jpg");
        		ciList.add(ci);
        		ctList.clear();
        		ctList.add(Category.findByName("Yosemite"));
        		Image.create(new Image("DCS_0756", 2, true, false, "trererdfgs asdfhbas asdf", DateTime.now().toDate(),
        			ciList, "praia, bora, pacifico, resort", ctList));
        		// outra imagem
        		file = new File("C:/play-2.0/ImageDB/public/images/DSC_0792.jpg");
        		fOutput = new File("C:/play-2.0/ImageDB/public/images/DSC_0792_thumb.jpg");
        		ImageIO.write(createThumbnail(ImageIO.read(file)),
						"jpeg", fOutput);
        		fOutput = new File("C:/play-2.0/ImageDB/public/images/DSC_0792_low.jpg");
        		ImageIO.write(createLowResolution(ImageIO.read(file)),
						"jpeg", fOutput);
        		ciList.clear();
        		ci = new ChildImage("thumb", "images/DSC_0792_thumb.jpg");
        		ciList.add(ci);
        		ci = new ChildImage("low", "images/DSC_0792_low.jpg");
        		ciList.add(ci);
        		ci = new ChildImage("high", "images/DSC_0792.jpg");
        		ciList.add(ci);
        		ctList.clear();
        		ctList.add(Category.findByName("Yosemite"));
        		Image.create(new Image("DCS_0792", 1, true, false, "trererdfgs asdfhbas asdf", DateTime.now().toDate(),
        			ciList, "praia, bora, pacifico, resort", ctList));
        		// outra imagem
        		file = new File("C:/play-2.0/ImageDB/public/images/DSC_0944.jpg");
        		fOutput = new File("C:/play-2.0/ImageDB/public/images/DSC_0944_thumb.jpg");
        		ImageIO.write(createThumbnail(ImageIO.read(file)),
						"jpeg", fOutput);
        		fOutput = new File("C:/play-2.0/ImageDB/public/images/DSC_0944_low.jpg");
        		ImageIO.write(createLowResolution(ImageIO.read(file)),
						"jpeg", fOutput);
        		ciList.clear();
        		ci = new ChildImage("thumb", "images/DSC_0944_thumb.jpg");
        		ciList.add(ci);
        		ci = new ChildImage("low", "images/DSC_0944_low.jpg");
        		ciList.add(ci);
        		ci = new ChildImage("high", "images/DSC_0944.jpg");
        		ciList.add(ci);
        		ctList.clear();
        		ctList.add(Category.findByName("Yosemite"));
        		Image.create(new Image("DCS_0944", 1, true, false, "trererdfgs asdfhbas asdf", DateTime.now().toDate(),
        			ciList, "praia, bora, pacifico, resort", ctList));
        		// outra imagem
        		file = new File("C:/play-2.0/ImageDB/public/images/DSC_0999.jpg");
        		fOutput = new File("C:/play-2.0/ImageDB/public/images/DSC_0999_thumb.jpg");
        		ImageIO.write(createThumbnail(ImageIO.read(file)),
						"jpeg", fOutput);
        		fOutput = new File("C:/play-2.0/ImageDB/public/images/DSC_0999_low.jpg");
        		ImageIO.write(createLowResolution(ImageIO.read(file)),
						"jpeg", fOutput);
        		ciList.clear();
        		ci = new ChildImage("thumb", "images/DSC_0999_thumb.jpg");
        		ciList.add(ci);
        		ci = new ChildImage("low", "images/DSC_0999_low.jpg");
        		ciList.add(ci);
        		ci = new ChildImage("high", "images/DSC_0999.jpg");
        		ciList.add(ci);
        		ctList.clear();
        		ctList.add(Category.findByName("Yosemite"));
        		Image.create(new Image("DCS_0999", 1, true, false, "trererdfgs asdfhbas asdf", DateTime.now().toDate(),
        			ciList, "praia, bora, pacifico, resort", ctList));
        		// outra imagem










        	} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
    
	public static BufferedImage createThumbnail(BufferedImage img) {
		img = resize(img, Method.ULTRA_QUALITY, Mode.FIT_TO_WIDTH, 250, 200);
		return img;
	}
	
	public static BufferedImage createLowResolution(BufferedImage img) {
		img = resize(img, Method.ULTRA_QUALITY, Mode.FIT_TO_WIDTH, 1500, 1000);
		return img;
	}
}