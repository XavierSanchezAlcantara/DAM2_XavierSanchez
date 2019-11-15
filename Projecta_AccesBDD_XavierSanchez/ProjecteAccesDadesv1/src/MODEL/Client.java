package MODEL;



public class Client {

	String cif;
	String nom;
	String cognom;
	String direccio;
	String bankCode;
	String telefono;
	
	public Client(String cif, String nom, String cognom, String direccio, String bankCode, String telefono) {
		super();
		this.cif = cif;
		this.nom = nom;
		this.cognom = cognom;
		this.direccio = direccio;
		this.bankCode = bankCode;
		this.telefono = telefono;
	}
	public void setCif(String cif) {
		this.cif = cif;
	}
	public String getCif() {
		return cif;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getNom() {
		return nom;
	}
	public void setCognom(String cognom) {
		this.cognom = cognom;
	}
	public String getCognom() {
		return cognom;
	}
	public void setDireccio(String direccio) {
		this.direccio = direccio;
	}
	public String getDireccio() {
		return direccio;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getTelefono() {
		return telefono;
	}
}
