

public class Test {
	public static void main(String[] args) throws Exception {
		
		Interaction inter=new Interaction();
		
		if(inter.getC().getP().getDeroute().estUneRefAvecTaille("1234506"))
			System.out.println("gagner");
		else
			System.out.println("perdu");
		// Tests Positifs
		System.out.println("Tests positifs :");
		Marchandise rds36=new Vetement(59.99,"1234506","36","robedesoiree");
		if(rds36.estUnHabit())
			System.out.println(rds36+" est bien un habit");
		
		if(inter.tailleOk("38","1234506"))
			System.out.println("la taille est ok!");
		
		System.out.println("La reference 1234506 de taille 38 est l'objet:"+inter.getC().getP().getDeroute().refVersBonneMarchandise("38","1234506")); // pour un habit
		System.out.println("La reference 2002345 est l'objet:"+inter.getC().getP().getDeroute().refVersBonneMarchandise("0","2002345"));// autre que habit
		
		if(inter.getC().getP().getDeroute().quantiteOk("1234506","40",3))
			System.out.println("la quantite pour la marchandise est ok!");
		
		// lors de l'achat
		
		Marchandise m=inter.getC().getP().getDeroute().refVersBonneMarchandise("42","1234507");
		System.out.println("quantite avant achat dans le magasin : "+inter.getC().getP().getDeroute().getEntrepotAvecTaille().get(m));
		if(inter.getC().getP().achat("1234507","42",1,"ajouter")) // on enleve au stock du magasin
			System.out.println("on en achete 1\nquantite apres achat dans le magasin : "+inter.getC().getP().getDeroute().getEntrepotAvecTaille().get(m)); // la quantite a bien baisse de 1

		Marchandise m1=inter.getC().getP().getDeroute().refVersBonneMarchandise("40","1234506");
		System.out.println("\nquantite avant achat dans le magasin :"+inter.getC().getP().getDeroute().getEntrepotAvecTaille().get(m1));
		if(!inter.getC().getP().getVente().containsKey(m1))
			System.out.println("le panier ne contient donc pas encore de "+m1);
		inter.getC().getP().changePanier("1234506","40",2,"ajouter"); // on enleve au stock du magasin
		System.out.println("on en achete 2");
		System.out.println("quantite apres achat dans le magasin"+inter.getC().getP().getDeroute().getEntrepotAvecTaille().get(m1));
		System.out.println("quantite apres achat dans le panier"+inter.getC().getP().getVente().get(m1));
		
		
		// lorsque l'on retire du panier tout marche bien

		Marchandise m2=inter.getC().getP().getDeroute().refVersBonneMarchandise("0","2554507");
		System.out.println("\n quantite dans le magasin avant avoir retirer du panier : "+inter.getC().getP().getDeroute().getEntrepot().get(m2));
		if(inter.getC().getP().achat("2554507","0",1,"retirer")) // le panier est vide, on ne prend dc pas la marchandise qu'il nous donne
			System.out.println("on en achete 1\nquantite apres avoir retirer du panier : "+inter.getC().getP().getDeroute().getEntrepotAvecTaille().get(m2)); // la quantite a bien baisse de 1

		System.out.println("quantite dans le magasin apres avoir retirer du panier : "+inter.getC().getP().getDeroute().getEntrepot().get(m2));

		Marchandise m3=inter.getC().getP().getDeroute().refVersBonneMarchandise("0","2002345");
		inter.getC().getP().changePanier("2002345","0",2,"ajouter"); // on achete 2 m3

		System.out.println("\nquantite dans le magasin avant avoir retirer  :"+inter.getC().getP().getDeroute().getEntrepot().get(m3));
		System.out.println("quantite dans le panier avant avoir retirer"+inter.getC().getP().getVente().get(m3));
		if(inter.getC().getP().getVente().containsKey(m3))
			System.out.println("le panier contient bien "+m3);
		inter.getC().getP().changePanier("2002345","0",1,"retirer"); // on enleve au stock du magasin
		System.out.println("on en retire 1");
		System.out.println("quantite dans le magasin apres avoir retirer "+inter.getC().getP().getDeroute().getEntrepot().get(m3));// m3 retirer est bien allez dans le magasin 1 fois
		System.out.println("quantite dans le panier apres avoir retirer "+inter.getC().getP().getVente().get(m3)); // m3 a bien été retirer du panier
		


		//Tests negatifs
		System.out.println("\n\nTests Negatifs :");
		if(!inter.tailleOk("39","1234506"))
			System.out.println("ca marche donc bien!");
		
		if(!inter.getC().getP().getDeroute().quantiteOk("1234506","36",100))
			System.out.println("quantiteok marche donc bien");
		
		inter.getC().getP().getDeroute().refVersBonneMarchandise("0","allo maman bobo");
		
		//lors de l'achat
		Marchandise m4=inter.getC().getP().getDeroute().refVersBonneMarchandise("36","4567801");
		System.out.println("\nquantite avant achat dans le magasin : "+inter.getC().getP().getDeroute().getEntrepotAvecTaille().get(m4));
		if(!inter.getC().getP().achat("4567801","36",-1,"ajouter")) // // on tente d'acheter l'objet en quantite -1
			System.out.println("on en achete -1\n la quantite dans le magasin est : "+inter.getC().getP().getDeroute().getEntrepotAvecTaille().get(m4)); // la quantite a bien baisse de 1
		
		
		Marchandise m5=inter.getC().getP().getDeroute().refVersBonneMarchandise("0","2002330");
		System.out.println("\nquantite avant achat dans le magasin :"+inter.getC().getP().getDeroute().getEntrepot().get(m5));
		if(!inter.getC().getP().getVente().containsKey(m5))
			System.out.println("le panier ne contient donc pas encore de "+m5);
		inter.getC().getP().changePanier("2002330","0",-2,"ajouter"); // on enleve au stock du magasin
		System.out.println("on en achete -2");
		System.out.println("quantite apres achat dans le magasin"+inter.getC().getP().getDeroute().getEntrepot().get(m5)); // la quantite reste donc la meme
		System.out.println("quantite apres achat dans le panier"+inter.getC().getP().getVente().get(m5));// la marchandise n'est pas ajouter au panier
		
		
		// lorsque l'on retire du panier ( l'utilisateur ne peut pas rentrer des données erronnée, en effet avec l'interface graphique il ne peut choisir que des bonnes valeurs.
		
		// test sur les reductions
		
		System.out.println(inter.getC().getP());
		// banquette clic clac =420.50  robe de soirée=59.99  Total 2*robedesoirée+banquette=540,48
		System.out.println("Total du panier :"+inter.getC().total(inter.getC().marVersPrix()));
		System.out.println("Total du panier avec code de reduction invalide : "+inter.getC().total(inter.getC().reduction("qui vivra verra"))); // la reduction ne s'applique pas
		System.out.println(inter.getC().reduit(420.5,10));
		System.out.println(inter.getC().reduit(59.99,30));
		// prix reduit : 378.45+(41.993*2)=462.436
		System.out.println("Total du panier avec code de reduction(3012) : "+inter.getC().total(inter.getC().reduction("3012")));		
	}
	

}
