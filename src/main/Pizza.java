package main;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class Pizza {

	private static Map<String, Integer> todosIngredientes = new HashMap<String, Integer>();
	private List<String> ingredientes = new ArrayList<String>();

	public static Map<String, Integer> getTodosIngredientes() {
		return todosIngredientes;
	}
	
	public Pizza() {}
	
	public Pizza(String... ingredientes) {
		for(String ingrediente: ingredientes) {
			adicionaIngrediente(ingrediente);
		}
	}

	
	private static void contabilizaIngrediente(String nome) {
		int qnt = 0;
		nome = nome.toLowerCase();
		
		if (todosIngredientes.containsKey(nome)) {
			qnt = todosIngredientes.get(nome);
		}
		todosIngredientes.put(nome, ++qnt);
	}

	public List<String> getIngredientes() {
		return ingredientes;
	}
	
	public String imprimeTodosIngredientes() {
		String ingredientes = "";
		
		for (String key : todosIngredientes.keySet()) {
            
            int value = todosIngredientes.get(key);
            ingredientes += key + " = " + String.valueOf(value) + "\n";
		}
		
		return ingredientes;
	}
	
	public void adicionaIngrediente(String nome) {
		ingredientes.add(nome);
		contabilizaIngrediente(nome);
	}
	
	public int getPreco() {
		int qnt = ingredientes.size();
		int preco;
		
		if (qnt <= 2) {
			preco = 15;
		} else if (qnt > 2 && qnt <= 5) {
			preco = 20;
		} else {
			preco = 23;
		}
		
		return preco;
	}
}
