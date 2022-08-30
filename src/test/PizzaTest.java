package test;
import main.Pizza;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

class PizzaTest {
	Pizza p1 = new Pizza("Queijo","Presunto","Cebola");

	
	@Test
	void testeGetIngredientes() {
		List<String> ingredientes = new ArrayList<String>();
		ingredientes.add("Queijo");
		ingredientes.add("Presunto");
		ingredientes.add("Cebola");
		assertEquals(ingredientes, p1.getIngredientes());
	}
	
	@Test
	void testeAdicionaIngredientes() {
		Pizza p2 = new Pizza();
		p2.adicionaIngrediente("Queijo");
		assertEquals(true,p2.getIngredientes().contains("Queijo"));	
	}
	
	@Test
	void testeGetPreco() {
		assertEquals(20, p1.getPreco());
	}
	


}
