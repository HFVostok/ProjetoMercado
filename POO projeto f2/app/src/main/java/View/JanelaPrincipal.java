package View;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class JanelaPrincipal extends JFrame {
    
 private JTabbedPane jTPane;

 public JanelaPrincipal() {
    jTPane =  new JTabbedPane();
    add(jTPane);

    VendasPainel tab1 = new  VendasPainel();
    ProdutoPainel tab2 = new ProdutoPainel();
    ListarProdutosView tab3 = new ListarProdutosView(); 
    jTPane.add("Vendas", tab1);
    jTPane.add("Produtos", tab2);
     jTPane.add("Registro Vendas", tab3);
    setBounds(100, 100, 600, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 }

  public void run ()  {
    this.setVisible(true);
    
  }

}
