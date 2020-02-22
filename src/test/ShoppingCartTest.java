package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import mainPackage.Campaign;
import mainPackage.Category;
import mainPackage.Coupon;
import mainPackage.Enums.DiscountType;
import mainPackage.Product;
import mainPackage.ShoppingCart;

public class ShoppingCartTest {

	private ShoppingCart cart;
	private Category category;
	private Product product;

	@Before
	public void setUp() {
		cart = new ShoppingCart();
		category = new Category("category");
		product = new Product("Product", 100.0, category);
		cart.addItem(product, 4);
	}

	@Test
	public void testCreateShoppingCart() {
		assertNotNull(cart);
	}

	@Test
	public void testTotalAmount() {
		assertEquals(400.0, cart.getTotalAmount(), 1);
	}

	@Test
	public void testTotalAmountAfterDiscountCampaignRateCouponRate() {
		Campaign campaign = new Campaign(category, 10.0, 0, DiscountType.RATE);
		Coupon coupon = new Coupon(100, 10, DiscountType.RATE);
		cart.applyDiscount(campaign);
		cart.applyCoupon(coupon);
		assertEquals(320.0, cart.getTotalAmountAfterDiscounts(), 1);
	}

	@Test
	public void testTotalAmountAfterDiscountCampaignAmountCouponAmount() {
		Campaign campaign = new Campaign(category, 10.0, 0, DiscountType.AMOUNT);
		Coupon coupon = new Coupon(100, 10, DiscountType.AMOUNT);
		cart.applyDiscount(campaign);
		cart.applyCoupon(coupon);
		assertEquals(380.0, cart.getTotalAmountAfterDiscounts(), 1);
	}

	@Test
	public void testTotalAmountAfterDiscountCampaignRateCouponAmount() {
		Campaign campaign = new Campaign(category, 10.0, 0, DiscountType.RATE);
		Coupon coupon = new Coupon(100, 10, DiscountType.AMOUNT);
		cart.applyDiscount(campaign);
		cart.applyCoupon(coupon);
		assertEquals(350.0, cart.getTotalAmountAfterDiscounts(), 1);
	}

	@Test
	public void testTotalAmountAfterDiscountCampaignAmountCouponRate() {
		Campaign campaign = new Campaign(category, 10.0, 0, DiscountType.AMOUNT);
		Coupon coupon = new Coupon(100, 10, DiscountType.RATE);
		cart.applyDiscount(campaign);
		cart.applyCoupon(coupon);
		assertEquals(350.0, cart.getTotalAmountAfterDiscounts(), 1);
	}

	@Test
	public void testApplyCampaignRate() {
		Campaign campaign = new Campaign(category, 10.0, 0, DiscountType.RATE);
		cart.applyDiscount(campaign);
		assertEquals(360.0, cart.getTotalAmountAfterDiscounts(), 1);
	}

	@Test
	public void testApplyCampaignAmount() {
		Campaign campaign = new Campaign(category, 10.0, 0, DiscountType.AMOUNT);
		cart.applyDiscount(campaign);
		assertEquals(390.0, cart.getTotalAmountAfterDiscounts(), 1);
	}

	@Test
	public void testApplyCouponRate() {
		Coupon coupon = new Coupon(100, 10, DiscountType.RATE);
		cart.applyCoupon(coupon);
		assertEquals(360.0, cart.getTotalAmountAfterDiscounts(), 1);
	}

	@Test
	public void testApplyCouponAmount() {
		Coupon coupon = new Coupon(100, 10, DiscountType.AMOUNT);
		cart.applyCoupon(coupon);
		assertEquals(390.0, cart.getTotalAmountAfterDiscounts(), 1);
	}

	@Test
	public void testSumOfCategoryAmount() {
		Product product1 = new Product("Product1", 200.0, category);
		cart.addItem(product1, 3);
		assertEquals(1000.0, cart.sumOfCategory(category), 1);
	}

	@Test
	public void testTotalCouponDiscounts() {
		Coupon coupon = new Coupon(100, 10, DiscountType.AMOUNT);
		cart.applyCoupon(coupon);
		assertEquals(10.0, cart.getCouponDiscounts(), 1);
	}

	@Test
	public void testTotalCampaignDiscounts() {
		Campaign campaign = new Campaign(category, 10.0, 0, DiscountType.AMOUNT);
		cart.applyDiscount(campaign);
		assertEquals(10.0, cart.getCampaignDiscount(), 1);
	}

}
