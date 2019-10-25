package MODEL;

public class LineaComanda {

	int idLiniaC;
	int idComanda;
	String idArticle;
	int quantitat;
	int unitatsServides;
	float preuUnitari;
	float preuLinia;
	char status;

	public LineaComanda(int idLiniaC, int idComanda, String idArticle, int quantitat, float preuUnitari, char status) {
		this.idLiniaC = idLiniaC;
		this.idComanda = idComanda;
		this.idArticle = idArticle;
		this.quantitat = quantitat;
		this.unitatsServides = 0;
		this.preuUnitari = preuUnitari;
		this.preuLinia = preuUnitari * quantitat;
		this.status = status;
	}

	boolean comprovaStock(int idArticle) {
		boolean stock = false;

		return stock;
	}

	boolean comprovaStockComponents(int idArticle) {
		boolean stock = false;

		return stock;
	}

	void llençarOF(boolean comprovarStock, boolean comprovaStockComponents) {
		OF of12 = new OF(12, 321, 13, 'O', 12);
		System.out.println(of12);
	}

	public float getPreuPerLinea() {
		return preuLinia;
	}

	public void setIdLiniaC(int idLiniaC) {
		this.idLiniaC = idLiniaC;
	}

	public int getIdLiniaC() {
		return idLiniaC;
	}

	public void setIdComanda(int idComanda) {
		this.idComanda = idComanda;
	}

	public int getIdComanda() {
		return idComanda;
	}

	public void setIdArticle(String idArticle) {
		this.idArticle = idArticle;
	}

	public String getIdArticle() {
		return idArticle;
	}

	public void setQuantitat(int quantitat) {
		this.quantitat = quantitat;
	}

	public int getQuantitat() {
		return quantitat;
	}
	
	//To string
	@Override
	public String toString() {
		return "\nLineaComanda [idLiniaC=" + idLiniaC + ", idComanda=" + idComanda + ", idArticle=" + idArticle
				+ ", quantitat=" + quantitat + ", unitatsServides=" + unitatsServides + ", preuUnitari=" + preuUnitari
				+ ", preuLinia=" + preuLinia + ", status=" + status + "]";
	}

}
