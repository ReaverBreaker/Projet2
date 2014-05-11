
public class Habit  extends Marchandise{
	private String taille;
	
	public Habit(String taille,String ref, double prix,String nom){
		super(prix,ref,nom);
		this.taille=taille;
	}
	
	public Habit(String ref,String taille){
		super(ref);
		this.taille=taille;
	}
	
	
	
	public String getTaille() {
		return taille;
	}

	public void setTaille(String taille) {
		this.taille = taille;
	}
	
	@Override
	public String toString() {
		return super.toString()+"("+getTaille()+")";
	}

	@Override
	public boolean equals(Object obj) {
		Habit other = (Habit) obj;
		if(super.equals(obj) && taille.equals(other.taille)){
			System.out.println("je suis rentrer");
			return true;
		}
		return false;
	}
	
	
	
	

}
