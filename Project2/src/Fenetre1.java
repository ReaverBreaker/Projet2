
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.*;
import java.awt.*;

     
public class Fenetre1 extends JFrame implements ActionListener,ListSelectionListener {
/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
/**
*
*/

	JButton retirer,validerReduction;
	JButton fin;
	JTextField reduction;
    
    Interaction inter;
    JLabel prixDesAchats;
    private JList list = new JList();
    JComboBox jc=new JComboBox();
    int quantite=0;
    
    
    public Fenetre1(){
    	inter=new Interaction();
        setTitle("M&I Shop");
        setSize(800,600);
        this.setLayout(new GridLayout(4,1));
        
        // Bouton retirer
        JPanel panelDuBoutonRetirer = new JPanel();
        retirer = new JButton("retirer");
        retirer.addActionListener(this);
        panelDuBoutonRetirer.add(retirer);
        panelDuBoutonRetirer.add(jc);
        add(panelDuBoutonRetirer);
        
        
        
        // liste d'elements
        
       
        inter.panier();
        list = new JList();
        DefaultListModel model = new DefaultListModel();
        for (int i=0;i<inter.getC().getP().venteArray().length;i++)
            model.addElement(inter.getC().getP().venteArray()[i][0]);

        
        
        //model.addElement("lel");
        list.setModel(model);
        
        JScrollPane scListe = new JScrollPane(list);
        //scListe.add(list);  
        //scListe.createVerticalScrollBar();
        
        //add(list);
        
        add(scListe);
        // prix de la liste d'elements
        prixDesAchats = new JLabel("Prix Total :");
        add(prixDesAchats);
        
        // boutons retirer et reduction
        JPanel panelBoutons = new JPanel();
        fin=new JButton("fin");
        fin.addActionListener(this);
        validerReduction=new JButton("Valider la reduction");
        validerReduction.addActionListener(this);
        panelBoutons.add(fin);
        reduction=new JTextField(" Code de Reduction ");
        reduction.addActionListener(this);
        panelBoutons.add(reduction);
        panelBoutons.add(validerReduction);
        add(panelBoutons);
        prixDesAchats.setText("Total a payer :"+inter.getC().total(inter.getC().marVersPrix())+" €");
        //System.out.println("Reduction :"+reduction.getText());
        
        list.addListSelectionListener(this);
        
         }
    
    public void mettreAJourPrix(){
   	 prixDesAchats.setText("Total a payer :"+inter.getC().total(inter.getC().reduction(reduction.getText()))+" €");
    }
    
    
    @Override 
     public void actionPerformed(ActionEvent a){
    
         if (a.getSource()==retirer){
        	 try{
             DefaultListModel listModel = (DefaultListModel) list.getModel();
                 quantite=Integer.parseInt(jc.getSelectedItem().toString());
            	 //quantite=jc.getSelectedIndex()+1;
                 //System.out.println("Liste :"+listModel.toString());
             int selectedIndex = list.getSelectedIndex();
             listModel.remove(list.getSelectedIndex());
             Marchandise m=(Marchandise) inter.getC().getP().venteArray()[selectedIndex][1];
             if(m.estUnHabit()){
            	 Habit h=(Habit) m;
            	 //System.out.println("quantite ds if"+quantite);
            	 inter.getC().getP().changePanier(h.getReference(),h.getTaille(),quantite,"retirer");
             }
             else{
            	 inter.getC().getP().changePanier(m.getReference(),"0",quantite,"retirer");
             }
             mettreAJourPrix();
             DefaultListModel model = new DefaultListModel();
             for (int i=0;i<inter.getC().getP().venteArray().length;i++)
                 model.addElement(inter.getC().getP().venteArray()[i][0]);

             //model.addElement("lel");
             list.setModel(model);
             //System.out.println("Reduction :"+reduction.getText());
             //System.out.println(inter.getP());
        	 }catch(Exception e){}
             
         } else if(a.getSource()==fin){	
        System.out.println(inter.getC().getP().getDeroute());
         String[] livraison = {"1) relais colis(2j)","2) deroute(4j)","3) a domicile (7j)"};
         try{
         String date = (String)JOptionPane.showInputDialog(null, "Format de livraison","Saisir :",JOptionPane.QUESTION_MESSAGE,null,livraison,livraison[2]);
         this.dispose();
         JOptionPane.showMessageDialog(null,inter.getC().getTicket(reduction.getText(),date.substring(0,1)), "Ticket", JOptionPane.INFORMATION_MESSAGE);
         }catch(Exception e){}
         }
         else// if(a.getSource()==validerReduction)
        	 mettreAJourPrix();
     }
	@Override
	public void valueChanged(ListSelectionEvent e) {
		int index=list.getMaxSelectionIndex();//e.getFirstIndex();//list.getSelectedIndex();
		if(index>=0){
			Marchandise m=(Marchandise) inter.getC().getP().venteArray()[index][1];
		 int quant=inter.getC().getP().getVente().get(m);
		 //System.out.println("quantite dans le panier"+quant);
		 
		Object[] tabQuantite=new Integer[quant];
		for(int i=0;i<quant;i++)
			tabQuantite[i]=i+1;
		DefaultComboBoxModel cm=new DefaultComboBoxModel(tabQuantite);
		jc.setModel(cm);
		}
	}
} 