package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import mainPackage.Category;

public class CategoryTest {
	private Category category;

	@Before
	public void setUp() {
		category = new Category("category");
	}

	@Test
	public void testCreateCategory() {
		assertNotNull(category);
	}

	@Test
	public void testCategoryTitle() {
		assertEquals("category", category.getTitle());
	}

	@Test
	public void testWrongSetCategoryTitle() {
		try {
			category.setTitle("");
		} catch (NullPointerException n) {
			assertEquals(new NullPointerException().toString(), n.toString());
		}
	}

	@Test
	public void testNullSetCategoryTitle() {
		try {
			category.setTitle(null);
		} catch (NullPointerException n) {
			assertEquals(new NullPointerException().toString(), n.toString());
		}
	}

}
