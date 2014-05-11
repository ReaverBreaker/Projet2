public interface Commandable {
	
	// cle de l'article à commander
	int cle();
	
	// enleve n articles du stock
	// PRECOND : il y a suffisamment d'articles en stock
	void enleveStock(int n);
	
	// pour savoir s'il y a assez d'articles dans le stock
	boolean assez(int n) ;

}
