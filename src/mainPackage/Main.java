package mainPackage;

import mainPackage.Enums.DiscountType;

public class Main {

	public static void main(String[] args) {

		Category food = new Category("Food");

		Category car = new Category("Car");

		Category electronic = new Category("Electronic");

		Product apple = new Product("Apple", 100.0, food);

		Product almond = new Product("Almond", 150.0, food);

		Product tablet = new Product("Tablet", 250.0, electronic);

		Product bmw = new Product("BMW", 500.0, car);

		ShoppingCart cart = new ShoppingCart();

		cart.addItem(apple, 3);

		cart.addItem(almond, 1);

		cart.addItem(bmw, 1);

		cart.addItem(tablet, 2);

		Campaign campaign1 = new Campaign(food, 20.0, 0, DiscountType.RATE);

		Campaign campaign2 = new Campaign(food, 50.0, 0, DiscountType.RATE);

		Campaign campaign3 = new Campaign(food, 5.0, 0, DiscountType.AMOUNT);

		Campaign campaign4 = new Campaign(car, 5.0, 0, DiscountType.AMOUNT);

		Coupon coupon = new Coupon(100, 10, DiscountType.RATE);

		Coupon coupon2 = new Coupon(100, 20, DiscountType.AMOUNT);

		cart.applyCoupon(coupon);

		cart.applyCoupon(coupon2);

		cart.applyDiscount(campaign1, campaign2, campaign3, campaign4);

		cart.print();

		DeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculator(10.0, 1.0, 2.99);

		deliveryCostCalculator.calculateFor(cart);

		System.out.println("Total Amount: " + cart.getTotalAmount() + "TL\tTotal DeliveryCost: "
				+ String.format("%.2f", cart.getDeliveryCost()) + "TL");
		
		/*
		 * System.out.println(cart.getTotalAmountAfterDiscounts());
		 * 
		 * System.out.println(cart.getCouponDiscounts());
		 * 
		 * System.out.println(cart.numberOfDeliveries());
		 * 
		 * System.out.println(cart.numberOfDeliveries());
		 * 
		 * System.out.println(cart.getCampaignDiscount());
		 * 
		 * System.out.println(cart.getDeliveryCost());
		 * 
		 */
	}
}
