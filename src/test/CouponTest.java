package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import mainPackage.Coupon;
import mainPackage.Enums.DiscountType;

public class CouponTest {

	private Coupon coupon;

	@Before
	public void setUp() {
		coupon = new Coupon(100, 10, DiscountType.RATE);
	}

	@Test
	public void testCreateCoupon() {
		assertNotNull(coupon);
	}

	@Test
	public void testMinAmount() {
		assertEquals(100.0, coupon.getMinAmount(), 1);
	}

	@Test
	public void testSetWrongMinAmount() {
		try {
			coupon.setMinAmount(-50);
		} catch (IndexOutOfBoundsException n) {
			assertEquals(new IndexOutOfBoundsException().toString(), n.toString());
		}
	}

	@Test
	public void testDiscountAmount() {
		assertEquals(10.0, coupon.getDiscountAmount(), 1);
	}

	@Test
	public void testSetWrongDiscountAmount() {
		try {
			coupon.setDiscountAmount(-50);
		} catch (IndexOutOfBoundsException n) {
			assertEquals(new IndexOutOfBoundsException().toString(), n.toString());
		}
	}

	@Test
	public void testDiscountTypeRate() {
		assertEquals(DiscountType.RATE, coupon.getType());
	}

	@Test
	public void testSetDiscountType() {
		coupon.setType(DiscountType.AMOUNT);
		assertEquals(DiscountType.AMOUNT, coupon.getType());
	}

	@Test
	public void testSetNullDiscountType() {
		try {
			coupon.setType(null);
		} catch (NullPointerException n) {
			assertEquals(new NullPointerException().toString(), n.toString());
		}
	}

}
