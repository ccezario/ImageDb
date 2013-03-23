package models;

public enum Type {
	Photo ("Foto"),
	Illustration ("Ilustracao"),
	Vector ("Vetor");
	
	private String type;
	
    private Type(String type) {
        this.type = type;
    }
    
    public String toString() {
    	return type;
    }
}
