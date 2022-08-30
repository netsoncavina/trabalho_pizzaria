package main;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class CarrinhoDeCompras {
	private List<Pizza> pizzas = new ArrayList<Pizza>();

	public CarrinhoDeCompras() {}
	
	public CarrinhoDeCompras(Pizza pizza) {
			addPizza(pizza);
	}
	
	public List<Pizza> getPizzas() {
		return pizzas;
	}
	
	public void addPizza(Pizza pizza) {
		try {	
			if (pizza.getIngredientes().size() > 0) {
				pizzas.add(pizza);
			} else {
				throw new Exception("Pizza sem ingredientes, por favor adicione ao carrinho apenas pizzas com ingredientes.");
			}
		} catch (Exception e) {
			  JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public String imprimeTotal() {
		int total = 0;
		
		for(int i = 0 ; i < pizzas.size(); i++){
			total += pizzas.get(i).getPreco();
		}
		
		return String.valueOf(total);
	}
}
