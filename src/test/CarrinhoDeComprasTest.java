package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import main.Pizza;
import main.CarrinhoDeCompras;


class CarrinhoDeComprasTest {
	
	Pizza p1 = new Pizza("Queijo","Presunto","Cebola");
	CarrinhoDeCompras carrinho = new CarrinhoDeCompras(p1);
	
	@Test
	void testGetPizza() {
		List<Pizza> pizzas = new ArrayList<Pizza>();
		pizzas.add(p1);
		assertEquals(pizzas, carrinho.getPizzas());
	}

	@Test
	void testImprimeTotal() {
		assertEquals("20",carrinho.imprimeTotal());
	}
	
}
