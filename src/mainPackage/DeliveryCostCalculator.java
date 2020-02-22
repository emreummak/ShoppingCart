package mainPackage;

import java.util.Hashtable;

public class DeliveryCostCalculator {
	private double costPerDelivery;
	private double costPerProduct;
	private double fixedCost;

	public double getCostPerDelivery() {
		return costPerDelivery;
	}

	public void setCostPerDelivery(double costPerDelivery) {
		if (costPerDelivery > -1)
			this.costPerDelivery = costPerDelivery;
		else {
			throw new IndexOutOfBoundsException();
		}
	}

	public double getCostPerProduct() {
		return costPerProduct;
	}

	public void setCostPerProduct(double costPerProduct) {
		if (costPerProduct > -1)
			this.costPerProduct = costPerProduct;
		else {
			throw new IndexOutOfBoundsException();
		}
	}

	public double getFixedCost() {
		return fixedCost;
	}

	public void setFixedCost(double fixedCost) {
		if (fixedCost > -1)
			this.fixedCost = fixedCost;
		else {
			throw new IndexOutOfBoundsException();
		}
	}

	public DeliveryCostCalculator(double costPerDelivery, double costPerProduct, double fixedCost) {
		try {
			setCostPerDelivery(costPerDelivery);
			setCostPerProduct(costPerProduct);
			setFixedCost(fixedCost);
		} catch (NullPointerException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/*
	 * Eger cart nesnesi null degilse, formule gore hesaplanan teslimat maliyeti
	 * double tipinde geri deger dondurur.
	 */
	public double calculateFor(ShoppingCart cart) {
		if (cart != null) {
			double deliveryCost = (this.costPerDelivery * cart.numberOfDeliveries())
					+ (this.costPerProduct * cart.numberOfProducts()) + this.fixedCost;
			cart.setDeliveryCost(deliveryCost);
			return deliveryCost;
		} else {
			throw new NullPointerException();
		}

	}

}
