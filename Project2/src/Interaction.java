import java.util.Scanner;

public class Interaction {
	Commande c;
	
	
	public Interaction(){
		c=new Commande();
	}
	
	public String read(String demande){
		Scanner reader = new Scanner(System.in);
		System.out.println(demande);
    	return reader.nextLine();
	}
	
	//**************************************************
	public boolean tailleOk(String taille,String ref){
		String s="Article disponible en taille : ";
		for(Marchandise m : c.getP().getDeroute().getEntrepotAvecTaille().keySet()){
			if(m.getReference().equals(ref)){
				Habit v=(Habit) m;
				s+=v.getTaille()+", ";
				if(v.getTaille().equals(taille))
					return true;
			}	
		}
		System.out.println("Taille invalide ("+s+ ")");
		return false;
	}
	 
	// redemande la taille si elle est erronée
	public String redemande(String ref){
		String taille = read("Entrer une taille");
		if(!tailleOk(taille, ref))
			return redemande(ref);
		return taille;
	}
	
	//Ajoute les marchandises achetés et leurs quantités dans une HashMap triée
public void  panier(){
	String ref,quant;
	do{
		String taille="0";
		//on affiche le contenu de l'entrepot
	    System.out.println(c.getP().getDeroute());
	   	System.out.println("Panier Client : "+c.getP());
	   	//on demande les caractéristiques de la commande
	   	ref = read("entrez la reference (non pour stopper)");
	   	Integer quantite=0;
	   	while(!c.getP().getDeroute().estUneRef(ref) && !c.getP().getDeroute().estUneRefAvecTaille(ref) && !ref.equals("non") && !ref.equals(""))
   			ref=read("Reference invalide ! Entrez la reference (non pour stopper)");
	   	if (!ref.equals("non") && !ref.equals("")){
	   		if(c.getP().getDeroute().estUneRefAvecTaille(ref))
	   			taille = redemande(ref);
	   		do{
	   		quant=read("entrez la quantite");
	   		}while( !c.isNumeric(quant) || !c.getP().getDeroute().quantiteOk(ref,taille,Integer.parseInt(quant)));
	   		quantite=Integer.parseInt(quant);
	    	//on stock le premier objet qui a la meme reference
	    	c.getP().changePanier(ref,taille,quantite,"ajouter");
	   	}
	} while (!ref.equals("non") && !ref.equals(""));	
}

public Commande getC() {
	return c;
}		


}
