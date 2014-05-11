public interface CommandableAvecTaille {
	
	// cle de l'article à commander
	int cle();
	
	// enleve n articles de taille t du stock
	// PRECOND : il y a suffisamment d'articles en stock
	void enleveStock(int n, int t);
	
	// pour savoir s'il y a assez d'articles de taille t dans le stock
	boolean assez(int n, int t) ;

}
