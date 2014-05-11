import java.util.HashMap;



public class Magasin implements CommandableAvecTaille,Commandable{
	//entrepot est l'entrepot de toutes les marchandises (clés : les Marchandises ;valeurs : les quantités)
	private HashMap<Marchandise,Integer> entrepotAvecTaille;
	private HashMap<Marchandise,Integer> entrepot;
	private Marchandise ObjetEnQuestion;
	
	Marchandise rds36=new Vetement(59.99,"1234506","36","robedesoiree");Marchandise rds38=new Vetement(59.99,"1234506","38","robedesoiree");Marchandise rds40=new Vetement(59.99,"1234506","40","robedesoiree");
	
	Marchandise riec38=new Vetement(35.45,"1238845","38","robeimprimeencoton");Marchandise riec40=new Vetement(35.45,"1238845","40","robeimprimeencoton");Marchandise riec42=new Vetement(35.45,"1238845","42","robeimprimeencoton");Marchandise riec44=new Vetement(35.45,"1238845","44","robeimprimeencoton");
	
	Marchandise pj40=new Vetement(52.69,"1234507","40","pantalonjean");Marchandise pj42=new Vetement(52.69,"1234507","42","pantalonjean");Marchandise pj44=new Vetement(52.69,"1234507","44","pantalonjean");
	
	Marchandise pa38=new Vetement(32.45,"1204566","38","pullacrylique");Marchandise pa40=new Vetement(32.45,"1204566","40","pullacrylique");Marchandise pa42=new Vetement(32.45,"1204566","42","pullacrylique");Marchandise pa44=new Vetement(32.45,"1204566","44","pullacrylique");
	
	Marchandise batE=new ElectroMenager(26.10,"2554507","batteurelectrique");
	Marchandise rasE=new ElectroMenager(350.40,"2553563","rasoirelectrique");
	Marchandise br=new ElectroMenager(125.40,"2552333","brand");
	Marchandise we=new ElectroMenager(140.40,"2552332","weber");
	Marchandise fmo=new ElectroMenager(120.90,"2004507","fourmicroonde");
	
	Marchandise bcc=new Mobilier(420.50,"2002345","banquetteclicclac");
	Marchandise bbz=new Mobilier(399.99,"2002330","braquettebz");
	Marchandise eta=new Mobilier(36.20,"2002320","etagere");
	
	Marchandise d00 = new Chaussure(70.00,"4567801", "36","Nike");
	Marchandise d01 = new Chaussure(70.00,"4567801", "38","Nike");
	Marchandise d02 = new Chaussure(70.00,"4567801", "40","Nike");
	Marchandise d03 = new Chaussure(70.00,"4567801", "42","Nike");
	Marchandise d10 = new Chaussure(60.00,"4567802", "38","Addidas");
	Marchandise d11 = new Chaussure(60.00,"4567802", "40","Addidas");
	Marchandise d12 = new Chaussure(60.00,"4567802", "42","Addidas");
	Marchandise d13 = new Chaussure(60.00,"4567802", "44","Addidas");
	Marchandise d20 = new Chaussure(50.00,"4567803", "36","Converse");
	Marchandise d21 = new Chaussure(50.00,"4567803", "40","Converse");
	Marchandise d22 = new Chaussure(50.00,"4567803", "44","Converse");

	//crée un objet de type asin
	public Magasin() {
		entrepot=new HashMap<Marchandise,Integer>();
		entrepotAvecTaille=new HashMap<Marchandise,Integer>();
		addItem(rds36,1);addItem(rds38,4);addItem(rds40,5);
		addItem(riec38,1);addItem(riec40,1);addItem(riec42,2);addItem(riec44,2);
		addItem(pj40,1);addItem(pj42,2);addItem(pj44,2);
		addItem(pa38,1);addItem(pa40,1);addItem(pa42,2);addItem(pa44,2);
		addItem(batE,100);
		addItem(rasE,10);
		addItem(br,3);
		addItem(we,2);
		addItem(fmo,10);
		addItem(bcc,3);
		addItem(bbz,3);
		addItem(eta,100);
		addItem(d00, 5);
		addItem(d01, 1);
		addItem(d02, 6);
		addItem(d03, 2);
		addItem(d10, 3);
		addItem(d11, 2);
		addItem(d12, 9);
		addItem(d13, 5);
		addItem(d20, 7);
		addItem(d21, 2);
		addItem(d22, 4);
		
	}
	
	//ajoute un item dans l'entrepot
	public void addItem(Marchandise theItem,Integer quantite){
		if(theItem.estUnHabit())
			entrepotAvecTaille.put(theItem,quantite);
		else
			entrepot.put(theItem,quantite);
	}
	
	//***************************************************
	public boolean estUneRefAvecTaille(String reference){
//		for(Marchandise m : entrepotAvecTaille.keySet()){
//			if(m.getReference().equals(reference))
//				return true;
//		}
		Marchandise m=new Marchandise(reference);
		if((entrepotAvecTaille.containsKey(m)))
			return true;
		return false;

	}
	//*******************************************************
	public boolean estUneRef(String reference){
		Marchandise m=new Marchandise(reference);
//		for(Marchandise m : entrepot.keySet()){
//			if(m.getReference().equals(reference))
//				return true;
//		}
		if(entrepot.containsKey(m))
			return true;
		return false;
	}	
	public Marchandise refVersBonneMarchandise(String taille,String reference){
		if(estUneRef(reference)){
			Marchandise mar=new Marchandise(reference);
			for(Marchandise m : entrepot.keySet()){
				if(mar.equals(m))
					return m;
			}
		}
		else{
			Habit v=new Habit(reference,taille);
			for(Marchandise m : entrepotAvecTaille.keySet()){
				if(v.equals((Habit) m)){
					return m;
				}
			}
		}
		System.out.println("nous ne vendons pas le produit que vous desirer");
		return null;
	}
	public HashMap<Marchandise, Integer> getEntrepotAvecTaille() {
		return entrepotAvecTaille;
	}

	public boolean quantiteOk(String ref,String taille,int quant){
		Marchandise m=refVersBonneMarchandise(taille, ref);
		setObjetEnQuestion(m);
		if( (!m.estUnHabit() && assez(quant)) || (m.estUnHabit() && assez(quant,Integer.parseInt(taille))))
				return true;
		if(m.estUnHabit())
			System.out.println("Quantite invalide! " +entrepotAvecTaille.get(m)+" "+m+" dispo en stock");
		else
			System.out.println("Quantite invalide! " +entrepot.get(m)+" "+m+" dispo en stock");
		return false;
	}
	
	public HashMap<Marchandise, Integer> getEntrepot() {
		return entrepot;
	}

	@Override
	public int cle() {
		return Integer.parseInt(ObjetEnQuestion.getReference());
	}

	@Override
	public boolean assez(int n) {
		if(entrepot.get(ObjetEnQuestion)<n)
			return false;
		return true;
	}
	
	@Override
	public void enleveStock(int n) {
		if(assez(n))
			entrepot.put(ObjetEnQuestion,entrepot.get(ObjetEnQuestion)-n);
	}

	@Override
	public void enleveStock(int n, int t) {
		if(assez(n,t))
			entrepotAvecTaille.put(ObjetEnQuestion,entrepotAvecTaille.get(ObjetEnQuestion)-n);
	}

	@Override
	public boolean assez(int n, int t) {
		if(entrepotAvecTaille.get(ObjetEnQuestion)<n)
			return false;
		return true;
	}

	public boolean estUnCommandable(){
		if(ObjetEnQuestion.estUnHabit())
			return false;
		return true;
	}
	
	public void setObjetEnQuestion(Marchandise objetEnQuestion) {
		ObjetEnQuestion = objetEnQuestion;
	}	
	
	public Marchandise getObjetEnQuestion() {
		return ObjetEnQuestion;
	}

	public String toString() {
		String str="Magasin [";
		Marchandise deuxieme=new Marchandise("toto");
		for(Marchandise m : entrepotAvecTaille.keySet()){
			Marchandise premiere=new Marchandise(m.getReference());
			if(premiere.equals(deuxieme))
				str+=m.toString()+" q="+ entrepotAvecTaille.get(m)+" ;";
			else
				str+="\n"+m.toString()+" q="+ entrepotAvecTaille.get(m)+" ;";
			deuxieme=premiere;

		}
		str+="\n";
		for(Marchandise m : entrepot.keySet())
			str+=m.toString()+" q="+ entrepot.get(m)+"; ";
		return str+"]";
	}
	
}
