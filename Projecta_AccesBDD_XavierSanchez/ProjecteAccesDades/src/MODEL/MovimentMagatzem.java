package MODEL;

public class MovimentMagatzem {
	//Atributs
	int idMoviment;
	int idMagatzem;
	char tipusMoviment;
	String idComponent;
	String idArticle;
	int quantitat;
	char ubicacioX;
	char ubicacioY;
	char ubicacioZ;
	public void MovimentMagatzemComponent(int idMoviment,int idMagatzem,char tipusMoviment,String idComponent) {
		this.idMoviment=idMoviment;
		this.idMagatzem=idMagatzem;
		this.tipusMoviment=tipusMoviment;
		this.idComponent=idComponent;
	}
	public void MovimentMagatzemArticle(int idMoviment,int idMagatzem,char tipusMoviment,String idArticle) {
		this.idMoviment=idMoviment;
		this.idMagatzem=idMagatzem;
		this.tipusMoviment=tipusMoviment;
		this.idArticle=idArticle;
	}
	void setUbicacio(char ubicacioX,char ubicacioY,char ubicacioZ){
		this.ubicacioX=ubicacioX;
		this.ubicacioY=ubicacioY;
		this.ubicacioZ=ubicacioZ;
	}
	void getUbicacio(){
		
	}
	void calculaStock() {
		
	}
}
