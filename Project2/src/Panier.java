
import java.util.ArrayList;

import java.util.HashMap;


public class Panier {
	private HashMap<Marchandise,Integer> vente=new HashMap<Marchandise,Integer>();
	private HashMap<String,ArrayList<Marchandise>>  panier=new HashMap<String,ArrayList<Marchandise>> ();
	private Magasin deroute;
	
	public Panier(Magasin mag){
		ArrayList<Marchandise> d=new ArrayList<Marchandise>();
		panier.put("Habit",d);d=new ArrayList<Marchandise>();
		panier.put("Mobilier",d);d=new ArrayList<Marchandise>();
		panier.put("ElectroMenager",d);
		deroute=mag;
	}
	
	public Object[][] venteArray(){
	    Object[][] venteListe = new Object[vente.size()][2];
	    int i=0;
	    for(Marchandise m : vente.keySet()){
	        venteListe[i][0]=""+m.toString()+" q="+ vente.get(m)+";\n";
	        venteListe[i][1]=m;
	        i++;
	    }
	    return venteListe;
	}
	
	//Met à jour la quantité après l'achat
	public boolean achat(String ref,String taille,int quant,String ajouterOuRetirer){
		if(deroute.quantiteOk(ref,taille, quant) && ((ajouterOuRetirer.equals("ajouter") && quant>=0) || (ajouterOuRetirer.equals("retirer") && quant<0) )){
		//Marchandise m=deroute.refVersBonneMarchandise(taille, ref);
		//System.out.println("Marchandise :"+m);
		//deroute.setObjetEnQuestion(m);
		if(deroute.estUnCommandable()){
			if(deroute.assez(quant)){
				deroute.enleveStock(quant);
				return true;
		}
		}else{
			if(deroute.assez(quant,Integer.parseInt(taille)) ){
				deroute.enleveStock(quant,Integer.parseInt(taille));
				return true;
			}
		}}
		System.out.println("la quantite acheter doit etre positif, nous ne prenons rien de gratuit ici!");
		return false;
	}
	
	
	
	
		//ajoute ou retirer quantite de fois m dans panier
	public void changePanier(String ref,String taille, int quantite,String ajouterOuRetirer){
		if( (ajouterOuRetirer.equals("ajouter") && achat(ref,taille,quantite,"ajouter")) || (ajouterOuRetirer.equals("retirer")) && achat(ref,taille,-quantite,"retirer")){
			Marchandise m=deroute.getObjetEnQuestion();
			if(!vente.containsKey(m) && quantite >0)
				vente.put(m,0);
				//System.out.println("rentrer");
			for(int i=0;i<quantite;i++)
				if(m.estUnHabit())
					decision(panier.get(m.getClass().getSuperclass().getSimpleName()),ajouterOuRetirer,m);
				else
					decision(panier.get(m.getClass().getSimpleName()),ajouterOuRetirer,m);
		}	
	}
	
//	public void removePanier(String ref,String taille, int quantite){
//		Marchandise m=refVersBonneMarchandise(taille, ref);
//		//System.out.println("Avant           :"+vente.get(m));
//		if(achat(ref,taille,-quantite)){//|| (s.equals("retirer")) && achat(ref,taille,-quantite,s)){
//			//System.out.println("Apres :       "+vente.get(m));
//			for(int i=0;i<quantite;i++){
//				if(m.estUnHabit())
//					decision(panier.get(m.getClass().getSuperclass().getSimpleName()),"retirer",m);
//				else
//					decision(panier.get(m.getClass().getSimpleName()),"retirer",m);
//					
//			}
//		}	
//	}
	
	
	// ajoute ou retire la marchandise m du panier en fonction de s
	public void decision(ArrayList<Marchandise> am,String s,Marchandise m){
		if(s.equals("ajouter")){
			am.add(m);
			vente.put(m,vente.get(m)+1); 
		}
		else if(s.equals("retirer")){
			if(am.remove(m)){
				vente.put(m,vente.get(m)-1);
				if(vente.get(m)==0)
					vente.remove(m);
			}
		}
	}
	
	public HashMap<Marchandise, Integer> getVente() {
		return vente;
	}
	
	public HashMap<String, ArrayList<Marchandise>> getPanier() {
		return panier;
	}

	public Magasin getDeroute() {
		return deroute;
	}

	
	public String toString(){
		String str="Panier [";
		for(Marchandise m : vente.keySet())
			str+=m.toString()+" q="+ vente.get(m)+";";
		return str+"]";
	}
}
