package mainPackage;

import mainPackage.Enums.DiscountType;

public class Campaign {

	private Category category;
	private double discount;
	private int minItem;
	private DiscountType type;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		if (category != null)
			this.category = category;
		else {
			throw new NullPointerException();
		}
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		if (discount > 0)
			this.discount = discount;
		else {
			throw new IndexOutOfBoundsException();
		}
	}

	public int getMinItem() {
		return minItem;
	}

	public void setMinItem(int minItem) {
		if (minItem > -1)
			this.minItem = minItem;
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

	public Campaign(Category category, double discount, int minItem, DiscountType type) {
		try {
			setCategory(category);
			setDiscount(discount);
			setMinItem(minItem);
			setType(type);
		} catch (NullPointerException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
