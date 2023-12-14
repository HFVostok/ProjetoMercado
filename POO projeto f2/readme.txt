import javax.swing.*;
import java.awt.*;

public class DoisPaineis extends JFrame {

    public DoisPaineis() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        // Criação dos dois painéis
        JPanel painel1 = new JPanel();
        JPanel painel2 = new JPanel();

        // Adiciona algum conteúdo aos painéis (exemplo com rótulos)
        painel1.add(new JLabel("Conteúdo do Painel 1"));
        painel2.add(new JLabel("Conteúdo do Painel 2"));

        // Define o layout do contentor principal (JFrame) como BoxLayout
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Adiciona os painéis à janela (empilhados verticalmente)
        add(painel1);
        add(painel2);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DoisPaineis());
    }
}
