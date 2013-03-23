package models;

public enum Role {
	Administrador("Administrador" ),
	Usuario("Usuario" ),
	RH("RH" );
	
	private String role;
	
    private Role(String role) {
        this.role = role;
    }
    
    public String toString(){
		return role;
    }
}
