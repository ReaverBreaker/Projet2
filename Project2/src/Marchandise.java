
public class Marchandise {
	private double prix;
	private final String reference;
	private String nom="default";
	
	public Marchandise(double prix,String reference,String nom) {
		this.prix=prix;
		this.reference=reference;
		this.nom=nom;
	}
	
	public Marchandise(String reference) {
		this.reference=reference;
	}

	public String getReference() {
		return reference;
	}

	public double getPrix() {
		return prix;
	}
	
	public void setPrix(double prix) {
		this.prix = prix;
	}
	
	
	public boolean estUnHabit(){// verifie que la marchandise est bien un vetement
		if(this instanceof Habit)
			return true;
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
			+ ((reference == null) ? 0 : reference.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		Marchandise other = (Marchandise) obj;
		if(other.reference.equals(this.reference))
			return true;
		return false;
	}
	
	

	@Override
	public String toString(){// renvoie le nom de la marchandise et de sa reference
		return nom+": "+getReference();
	}
}
