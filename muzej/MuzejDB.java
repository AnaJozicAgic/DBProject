package muzej;

public class MuzejDB {
	private int ID;
	private String naziv;
	private String opis;
	private int procjenjenaStarost;
	private String lokalitet;
	//Constructor
	public MuzejDB(int ID, String naziv, String opis, int procjenjenaStarost, String lokalitet) {
	
		this.ID= ID;
		this.naziv = naziv;
		this.opis = opis;
		this.procjenjenaStarost = procjenjenaStarost;
		this.lokalitet = lokalitet;
	}
	//getters
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public int getProcjenjenaStarost() {
		return procjenjenaStarost;
	}
	public void setProcjenjenaStarost(int procjenjenaStarost) {
		this.procjenjenaStarost = procjenjenaStarost;
	}
	public String getLokalitet() {
		return lokalitet;
	}
	public void setLokalitet(String lokalitet) {
		this.lokalitet = lokalitet;
	}
	@Override
	public String toString() {
		return "Exhibit : ID=" + ID + ", naziv=" + naziv + ", opis=" + opis + ", procjenjenaStarost="
				+ procjenjenaStarost + ", lokalitet=" + lokalitet ;
	}
	
	
}
