package mainPackage;

import mainPackage.Enums.DiscountType;

public class Coupon {

	private double minAmount;
	private double discountAmount;
	private DiscountType type;

	public double getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(double minAmount) {
		if (minAmount > -1)
			this.minAmount = minAmount;
		else {
			throw new IndexOutOfBoundsException();
		}
	}

	public double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(double discountAmount) {
		if (discountAmount > 0)
			this.discountAmount = discountAmount;
		else {
			throw new IndexOutOfBoundsException();
		}
	}

	public DiscountType getType() {
		return type;
	}

	public void setType(DiscountType type) {
		if (type != null)
			this.type = type;
		else {
			throw new NullPointerException();
		}
	}

	public Coupon(double minAmount, double discountAmount, DiscountType type) {
		try {
			setMinAmount(minAmount);
			setDiscountAmount(discountAmount);
			setType(type);
		} catch (NullPointerException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
