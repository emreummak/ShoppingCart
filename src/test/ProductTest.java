package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import mainPackage.Category;
import mainPackage.Product;

public class ProductTest {

	private Category category;
	private Product apple;

	@Before
	public void setUp() {
		category = new Category("category");
		apple = new Product("Apple", 100.0, category);
	}

	@Test
	public void testCreateProduct() {
		assertNotNull(apple);
	}

	@Test
	public void testProductName() {
		assertEquals("Apple", apple.getTitle());
	}

	@Test
	public void testSetWrongProductName() {
		try {
			apple.setTitle("");
		} catch (NullPointerException n) {
			assertEquals(new NullPointerException().toString(), n.toString());
		}
	}

	@Test
	public void testSetNullProductName() {
		try {
			apple.setTitle(null);
		} catch (NullPointerException n) {
			assertEquals(new NullPointerException().toString(), n.toString());
		}
	}

	@Test
	public void testProductCost() {
		assertEquals(100.0, apple.getCost(), 1);
	}

	@Test
	public void testSetWrongCost() {
		try {
			apple.setCost(-50);
		} catch (IndexOutOfBoundsException n) {
			assertEquals(new IndexOutOfBoundsException().toString(), n.toString());
		}
	}

	@Test
	public void testProductCategory() {
		assertEquals(category, apple.getCategory());
	}

	@Test
	public void testSetNullCategory() {
		try {
			apple.setCategory(null);
		} catch (NullPointerException n) {
			assertEquals(new NullPointerException().toString(), n.toString());
		}
	}

}
