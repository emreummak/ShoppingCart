package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import mainPackage.Category;
import mainPackage.DeliveryCostCalculator;
import mainPackage.Product;
import mainPackage.ShoppingCart;

public class DeliveryCostCalculatorTest {

	private DeliveryCostCalculator deliveryCostCalculator;
	private ShoppingCart cart;
	private Category category;
	private Product product;

	@Before
	public void setUp() {
		deliveryCostCalculator = new DeliveryCostCalculator(10.0, 1.0, 2.99);
		cart = new ShoppingCart();
		category = new Category("category");
		product = new Product("Product", 100.0, category);
		cart.addItem(product, 4);
	}

	@Test
	public void testCreateProduct() {
		assertNotNull(deliveryCostCalculator);
	}

	@Test
	public void testCostPerDelivery() {
		assertEquals(10.0, deliveryCostCalculator.getCostPerDelivery(), 1);
	}

	@Test
	public void testSetWrongCostPerDelivery() {
		try {
			deliveryCostCalculator.setCostPerDelivery(-50);
		} catch (IndexOutOfBoundsException n) {
			assertEquals(new IndexOutOfBoundsException().toString(), n.toString());
		}
	}

	@Test
	public void testCostPerProduct() {
		assertEquals(1.0, deliveryCostCalculator.getCostPerProduct(), 1);
	}

	@Test
	public void testSetWrongCostPerProduct() {
		try {
			deliveryCostCalculator.setCostPerProduct(-50);
		} catch (IndexOutOfBoundsException n) {
			assertEquals(new IndexOutOfBoundsException().toString(), n.toString());
		}
	}

	@Test
	public void testFixedCost() {
		assertEquals(2.99, deliveryCostCalculator.getFixedCost(), 1);
	}

	@Test
	public void testSetWrongFixedCost() {
		try {
			deliveryCostCalculator.setFixedCost(-50);
		} catch (IndexOutOfBoundsException n) {
			assertEquals(new IndexOutOfBoundsException().toString(), n.toString());
		}
	}

	@Test
	public void testCalculateFor() {
		assertEquals(13.9, deliveryCostCalculator.calculateFor(cart), 1);
	}

	@Test
	public void testAddDifItemCalculateFor() {
		Product product1 = new Product("Product1", 200.0, category);
		cart.addItem(product1, 1);
		assertEquals(14.9, deliveryCostCalculator.calculateFor(cart), 1);
	}

	@Test
	public void testAddDifCategoryCalculateFor() {
		Category category1 = new Category("category1");
		Product product1 = new Product("Product1", 200.0, category1);
		cart.addItem(product1, 1);
		assertEquals(24.9, deliveryCostCalculator.calculateFor(cart), 1);
	}

	@Test
	public void testNullCalculateFor() {
		try {
			deliveryCostCalculator.calculateFor(null);
		} catch (NullPointerException n) {
			assertEquals(new NullPointerException().toString(), n.toString());
		}
	}

}
