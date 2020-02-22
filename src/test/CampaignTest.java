package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import mainPackage.Campaign;
import mainPackage.Category;
import mainPackage.Enums.DiscountType;

public class CampaignTest {

	private Campaign campaign;
	private Category category;

	@Before
	public void setUp() {
		category = new Category("category");
		campaign = new Campaign(category, 20.0, 0, DiscountType.RATE);
	}

	@Test
	public void testCreateProduct() {
		assertNotNull(campaign);
	}

	@Test
	public void testCategory() {
		assertEquals(category, campaign.getCategory());
	}

	@Test
	public void testSetNullCategory() {
		try {
			campaign.setType(null);
		} catch (NullPointerException n) {
			assertEquals(new NullPointerException().toString(), n.toString());
		}
	}

	@Test
	public void testDiscount() {
		assertEquals(20.0, campaign.getDiscount(), 1);
	}

	@Test
	public void testSetWrongDiscount() {
		try {
			campaign.setDiscount(-50);
		} catch (IndexOutOfBoundsException n) {
			assertEquals(new IndexOutOfBoundsException().toString(), n.toString());
		}
	}

	@Test
	public void testMinItem() {
		assertEquals(0.0, campaign.getMinItem(), 1);
	}

	@Test
	public void testSetWrongMinItem() {
		try {
			campaign.setMinItem(-50);
		} catch (IndexOutOfBoundsException n) {
			assertEquals(new IndexOutOfBoundsException().toString(), n.toString());
		}
	}

	@Test
	public void testType() {
		assertEquals(DiscountType.RATE, campaign.getType());
	}

	@Test
	public void testSetDiscountType() {
		campaign.setType(DiscountType.AMOUNT);
		assertEquals(DiscountType.AMOUNT, campaign.getType());
	}

	@Test
	public void testSetNullDiscountType() {
		try {
			campaign.setType(null);
		} catch (NullPointerException n) {
			assertEquals(new NullPointerException().toString(), n.toString());
		}
	}

}
