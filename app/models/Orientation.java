package models;

public enum Orientation {
	Horizontal ("Horizontal"),
	Vertical ("Vertical");
	
	private String orientation;
	
    private Orientation(String type) {
        this.orientation = type;
    }
    
    public String toString() {
    	return orientation;
    }
}