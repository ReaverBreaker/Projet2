import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Set;


public class Commande {
	private Panier p;
	
	public Commande(){
		p=new Panier(new Magasin());
	}
	
	//vérifie si str est un nombre entier
	public boolean isNumeric(String str){
		try{
			Integer.parseInt(str);
		}catch(NumberFormatException nfe){
			return false;
		}
		return true;
	}
	
	//applique la réduction
	public Double reduit(double d,int taux){
		return d-(d*taux/100);
	}
	
	// Convertis les marchandises d'un panier en leur prix
	public HashMap<String,ArrayList<Double>> marVersPrix(){
		HashMap<String,ArrayList<Double>> money=new HashMap<String,ArrayList<Double>>();
		Set<String> type=p.getPanier().keySet();
		for(String s : type){
			ArrayList<Double> l=new ArrayList<Double>();
			for(int i=0;i<p.getPanier().get(s).size();i++)
				l.add(i,p.getPanier().get(s).get(i).getPrix());
			money.put(s,l);
		}
		return money;
	}
	
	//Evite la duplication de ce for que l'on utilise à plusieurs reprises
	public void petitfor(ArrayList<Double> reven, int catarina, int taux){
		for (int i=0;i<catarina;i++)
			reven.set(i,reduit(reven.get(i),taux));
	}
	
	// applique la reduction au panier en fonction du code remis par le client
	public HashMap<String,ArrayList<Double>> reduction(String reduc){
		HashMap<String,ArrayList<Double>> red=marVersPrix();
		if(reduc.length()==4 && isNumeric(reduc)){
			ArrayList<Double> v=red.get("Habit");
			ArrayList<Double> em=red.get("ElectroMenager");
			ArrayList<Double> m=red.get("Mobilier");
			if(reduc.substring(0,2).equals("40")){
				petitfor(v,Math.min(3,v.size()),40);
				petitfor(em,em.size(),20);
				petitfor(m,m.size(),10);
			}
			else if(reduc.substring(0,2).equals("30")){
				petitfor(v,v.size(),30);
				petitfor(em,em.size(),20);
				petitfor(m,m.size(),10);
			}
			else if(reduc.substring(0, 2).equals("20"))
				petitfor(v,v.size(),20);
			else
				System.out.println("Code de reduction non valide");
		}
		return red;
	}

	//Renvoie le prix total d'un panier triee
	public Double total(HashMap<String,ArrayList<Double>> PrixPanier){
		double tot=0;
		for(String s: PrixPanier.keySet()){
			for(int i=0;i<PrixPanier.get(s).size();i++)
				tot+=PrixPanier.get(s).get(i);
		}
		return tot;
	}


	
	
	// Renvoie la date de livraison en fonction du type
	public String getDate(String livrai){
		Calendar calendar=Calendar.getInstance();
		//définir le format de la date
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		//String livrai=read("Type de livraison a choisir( Taper : 1) relais colis(2j) | 2) deroute(4j) | 3) a domicile (7j)");
		if(livrai.equals("1"))
			calendar.add(Calendar.DATE,2);
		else if (livrai.equals("2"))
			calendar.add(Calendar.DATE,4);
		else
			calendar.add(Calendar.DATE,7);
		return sdf.format(calendar.getTime());
			
	}
	
	public String getTicket(String reduc,String date){
		String ticket="           M&I Shop \n           Sophia Bienvenue \n           TEL: 0637043945 \n           du Lundi au Vendredi de 8h45 a 22h00 \n           le Samedi de 08h45 a 21h00";
		ticket+="\n\nDesignation                   Qtite*P.U       Montant";
		int nbreArticle=0;
		HashMap<String,ArrayList<Double>> prixDansPanier=marVersPrix(),pDPReduit=reduction(reduc);
		double montant=total(prixDansPanier),montantreduit=total(pDPReduit),reduction=montant-montantreduit;
		//System.out.println(prixDansPanier);System.out.println(pDPReduit);
		for(String s :p.getPanier().keySet()){
			ArrayList<Marchandise> m=p.getPanier().get(s);
			if(m.size() != 0){
				ticket+="\n\n"+s+"      :";
				while(m.size()!=0){
					Marchandise mar=m.get(0);
					int count=0;
					while(m.remove(mar))
						count++;
					ticket+="\n"+mar.toString()+"       "+count+"*"+mar.getPrix()+"          " +count*mar.getPrix();
					nbreArticle+=count;
		}	}	}
		String tiret="\n======================================================";
		ticket+=tiret;ticket+="\n   "+nbreArticle+" Articles    "+"TOTAL :"+montant+"€";
		ticket+=tiret;ticket+="\n                 Reduction :"+reduction+"€";
		ticket+=tiret;ticket+="\n                TOTAL A Payer:"+montantreduit+"€";
		String dateLivraison=getDate(date);
		while(dateLivraison.equals("erreur"))
			dateLivraison=getDate(date);
		ticket+="\n\n         Vous serez livré le "+dateLivraison;
		return ticket;
	}

	public Panier getP() {
		return p;
	}
	
	
	
	
	

}
