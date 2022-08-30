package main;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class View implements ActionListener{
	
	private JFrame f;
	
	private JPanel pMain, pAdd, pAddForm, pAddBtn, pEnd;
	
	private JLabel lblTitleIngdt, lblPrecoIngdt, lblIngdt, lblPrecoFim, lblQntFim;
	
	private JTextField txtIngdtField;
	
	private JButton btnIngdt, btnRemove, btnFimPizza, btnEnd;
	
	private DefaultTableModel modelo;
	
	private JTable tabela;
	
	private JScrollPane sp;
	
	private CarrinhoDeCompras cart = new CarrinhoDeCompras();
	
	private List<String> ingredientes = new ArrayList<String>();
	
	public View(){
		
		f = new JFrame();
		f.setTitle("Pizzaria");
		f.setBounds(50, 50, 500, 500);
		f.setLayout(new BorderLayout());
		
		pMain = new JPanel();
		pMain.setLayout(new BorderLayout());
		f.add("Center", pMain);
		f.add("North", new JLabel(" "));
		f.add("West", new JLabel(" "));
		f.add("East", new JLabel(" "));
		f.add("South", new JLabel(" "));
		
		pAdd = new JPanel();
		pAdd.setLayout(new BorderLayout());
		pMain.add("North", pAdd);
		
		pAddForm = new JPanel();
		pAddForm.setLayout(new GridLayout(2,2));
		pAdd.add("Center", pAddForm);
		
		pAddBtn = new JPanel();
		pAddBtn.setLayout(new GridLayout(3,3));
		pAdd.add("South", pAddBtn);
		
		lblTitleIngdt = new JLabel("Adicionar Ingedientes", SwingConstants.CENTER);
		pAddForm.add(lblTitleIngdt);
		
		lblPrecoIngdt = new JLabel("Preço da pizza: -", SwingConstants.CENTER);
		pAddForm.add(lblPrecoIngdt);
		
		lblIngdt = new JLabel("Nome do ingrediente", SwingConstants.CENTER);
		pAddForm.add(lblIngdt);
		
		txtIngdtField = new JTextField();
		pAddForm.add(txtIngdtField);
		

		pAddBtn.add(new JLabel(""));
		pAddBtn.add(new JLabel(""));
		pAddBtn.add(new JLabel(""));
		
		btnIngdt = new JButton("Adicionar Ingrediente");
		btnIngdt.addActionListener(this);
		pAddBtn.add(btnIngdt);
		
		btnFimPizza = new JButton("Finalizar Pizza");
		btnFimPizza.setEnabled(false);
		btnFimPizza.addActionListener(this);
		pAddBtn.add(btnFimPizza);
		
		btnRemove = new JButton("Remover Pizza");
		btnRemove.setEnabled(false);
		btnRemove.addActionListener(this);
		pAddBtn.add(btnRemove);
		
		pAddBtn.add(new JLabel(""));
		pAddBtn.add(new JLabel(""));
		pAddBtn.add(new JLabel(""));
		
		pEnd = new JPanel();
		pEnd.setLayout(new GridLayout(5, 1));
		pMain.add("South", pEnd);
		
		String colunas[] = {"Ingrediente", "Quantidade"};
		String dados[][] = { {"-", "-"} };
		
		modelo = new DefaultTableModel(dados, colunas);
		tabela = new JTable(modelo);
		sp = new JScrollPane(tabela);
		pMain.add("Center", sp);
		
		lblPrecoFim = new JLabel("Preço Total: -", SwingConstants.CENTER);
		pEnd.add(lblPrecoFim);
		
		lblQntFim = new JLabel("Total de pizzas: -", SwingConstants.CENTER);
		pEnd.add(lblQntFim);
		
		btnEnd = new JButton("Finalizar Pedido");
		btnEnd.setEnabled(false);
		btnEnd.addActionListener(this);
		pEnd.add(btnEnd);
		
		f.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		String btnClicked = e.getActionCommand();
		
		if(btnClicked.equals("Adicionar Ingrediente")) {
			
			if(txtIngdtField.getText().trim().equals("")) {
				return;
			}
			
			ingredientes.add(txtIngdtField.getText());
			
			int qnt = ingredientes.size();
			int preco = 0;
			
			if (qnt <= 2 && qnt > 0) {
				preco = 15;
			} else if (qnt > 2 && qnt <= 5) {
				preco = 20;
			} else if (qnt > 5){
				preco = 23;
			}
			
			lblPrecoIngdt.setText("Preço da pizza: " + String.valueOf(preco));
			
			if(qnt > 0) {
				btnFimPizza.setEnabled(true);
				btnRemove.setEnabled(true);
			} else {
				btnFimPizza.setEnabled(false);
				btnRemove.setEnabled(false);
			}
			
			txtIngdtField.setText("");
			
		} else if (btnClicked.equals("Remover Pizza")) {
			
			ingredientes.clear();
			txtIngdtField.setText("");
			lblPrecoIngdt.setText("Preço da pizza: -");
			btnFimPizza.setEnabled(false);
			btnRemove.setEnabled(false);
			
		} else if (btnClicked.equals("Finalizar Pizza")) {
			Pizza p = new Pizza();
			
			for(int i = 0 ; i < ingredientes.size(); i++){
				p.adicionaIngrediente(ingredientes.get(i));
			}
			
			cart.addPizza(p);
			
			String total = String.valueOf(cart.getPizzas().size());
			String preco = cart.imprimeTotal();
			
			Map<String, Integer> todosIngredientes = Pizza.getTodosIngredientes();
			
			for(int a = 0 ; a < modelo.getRowCount(); a++){
				modelo.removeRow(a);
			}
			
			for (String key : todosIngredientes.keySet()) {
	            
	            int value = todosIngredientes.get(key);
	            modelo.addRow(new Object[]{key, String.valueOf(value)}); 
			}
			
			lblPrecoFim.setText("Preço Total: " + preco);
			lblQntFim.setText("Total de pizzas: " + total);
			
			ingredientes.clear();
			txtIngdtField.setText("");
			lblPrecoIngdt.setText("Preço da pizza: -");
			btnFimPizza.setEnabled(false);
			btnRemove.setEnabled(false);
			btnEnd.setEnabled(true);
			
		} else if (btnClicked.equals("Finalizar Pedido")) {
			
			JOptionPane.showMessageDialog(null, "Finalizou");
			System.exit(0);
			
		}
	}
}
