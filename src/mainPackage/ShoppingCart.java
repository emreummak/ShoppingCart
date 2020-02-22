package mainPackage;

import java.util.Hashtable;
import java.util.Map;
import mainPackage.Enums.DiscountType;

public class ShoppingCart {

	private Hashtable<Category, Hashtable<Product, Integer>> cart;
	private Hashtable<Campaign, Double> campaigns = new Hashtable<Campaign, Double>();
	private Hashtable<Coupon, Double> coupons = new Hashtable<Coupon, Double>();
	private double totalAmount;
	private double totalAmountAfterDiscounts;
	private double couponDiscounts;
	private double campaignDiscount;
	private double deliveryCost;

	public double getTotalAmount() {
		return totalAmount;
	}

	private void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getTotalAmountAfterDiscounts() {
		return totalAmountAfterDiscounts;
	}

	private void setTotalAmountAfterDiscounts(double totalAmountAfterDiscounts) {
		this.totalAmountAfterDiscounts = totalAmountAfterDiscounts;
	}

	public double getCouponDiscounts() {
		return couponDiscounts;
	}

	private void setCouponDiscounts(double couponDiscounts) {
		this.couponDiscounts = couponDiscounts;
	}

	public double getCampaignDiscount() {
		return campaignDiscount;
	}

	private void setCampaignDiscount(double campaignDiscount) {
		this.campaignDiscount = campaignDiscount;
	}

	public double getDeliveryCost() {
		return deliveryCost;
	}

	public void setDeliveryCost(double deliveryCost) {
		this.deliveryCost = deliveryCost;
	}

	public ShoppingCart() {

		cart = new Hashtable<Category, Hashtable<Product, Integer>>();
	}

	/*
	 * Toplam tutari hesaplamak icin calculateTotalAmount fonksiyonu kullanilir.
	 * Bunun icin Cart nesnesi icerisindeki urun miktari ile birim fiyat
	 * carpilir. totalCost degiskenine setlenir.
	 */
	private void calculateTotalAmount() {
		double totalCost = 0;
		if (cart.isEmpty() != true) {
			for (Map.Entry<Category, Hashtable<Product, Integer>> entry : cart.entrySet()) {
				for (Map.Entry<Product, Integer> product : entry.getValue().entrySet()) {
					totalCost += product.getValue() * product.getKey().getCost();
				}
			}
			setTotalAmount(totalCost);
		}
	}

	/*
	 * Bir sepet icin tum uygun kampanya ve kuponlar Campaign ve Coupon
	 * nesnelerinde ayri ayri tutulur. calculateTotalAmountAfterDiscount
	 * fonksiyonu cagrildiginda hesaplanir. Kupon ve kampanya indirimleri ayri
	 * degiskenlerde tutulur. Toplam tutarin indirimli halini saptamak icin
	 * uygun kampanya ve kuponlar toplanarak TotalAmountAfterDiscount
	 * degiskenine setlenir.
	 */

	protected void calculateTotalAmountAfterDiscount() {
		double totalDiscountCampaign = 0;
		double totalDiscountCoupon = 0;
		if (!campaigns.isEmpty()) {
			for (Map.Entry<Campaign, Double> campaign : campaigns.entrySet()) {
				totalDiscountCampaign += campaign.getValue();
			}
		}
		if (!coupons.isEmpty()) {
			for (Map.Entry<Coupon, Double> coupon : coupons.entrySet()) {
				totalDiscountCoupon += coupon.getValue();
			}
		}
		setCampaignDiscount(totalDiscountCampaign);
		setCouponDiscounts(totalDiscountCoupon);
		setTotalAmountAfterDiscounts(getTotalAmount() - totalDiscountCoupon - totalDiscountCampaign);
	}

	/*
	 * Yeni bir urun eklendiginde once hangi kategori urunu olduguna bakilir.
	 * Eger kategorisi daha onceden elimizde varsa ayni urun ayni kategori
	 * icerisinde var mi diye bakilir. Ayni urun ayni kategori icerisinde ise
	 * mevcut urun miktari guncellenir. Yok ise urun veya kategori eklenir. Her
	 * urun eklendikten sonra toplam maliyet hesaplanir. Indirim varsa
	 * uygulanir.
	 */

	public void addItem(Product product, int amount) {
		Hashtable<Product, Integer> temp = new Hashtable<Product, Integer>();
		if (cart.keySet().contains(product.getCategory())) {
			temp = cart.get(product.getCategory());
			if (temp.keySet().contains(product)) {
				int tempAmount = temp.get(product);
				tempAmount += amount;
				temp.put(product, tempAmount);
			} else {
				temp.put(product, amount);
			}
			cart.put(product.getCategory(), temp);
		} else {
			temp.put(product, amount);
			cart.put(product.getCategory(), temp);
		}
		calculateTotalAmount();
		calculateTotalAmountAfterDiscount();
	}

	/*
	 * Bir kategorideki urunlerin birim fiyati ile miktarini carparak ilgili
	 * kategorinin maliyetini hesaplar.
	 */

	public double sumOfCategory(Category category) {
		double totalCostOfCategory = 0;
		try {
			for (Map.Entry<Product, Integer> product : cart.get(category).entrySet()) {
				totalCostOfCategory += product.getKey().getCost() * product.getValue();
			}

		} catch (Exception e) {
			return 0;
		}
		return totalCostOfCategory;
	}

	/*
	 * numberOfProducts fonksiyonu bir sepetteki farkli urun miktarini dondurur.
	 */

	protected int numberOfProducts() {
		int productNumber = 0;
		try {
			for (Map.Entry<Category, Hashtable<Product, Integer>> entry : cart.entrySet()) {
				productNumber += entry.getValue().size();
			}
		} catch (Exception e) {
			return productNumber;
		}
		return productNumber;
	}

	/*
	 * numberOfDeliveries fonksiyonu bir sepetteki farkli kategori miktarini
	 * dondurur.
	 */

	protected int numberOfDeliveries() {
		try {
			return cart.keySet().size();
		} catch (Exception e) {
			return 0;
		}
	}

	/*
	 * Indirim uygulamak icin birden fazla kampanya parametresi gelebilir. En
	 * uygun kampanya parametresi bulunarak Campaign nesnesine eklenir. Eklenen
	 * uygun kampanyalarin uygulanmasi icin en son
	 * calculateTotalAmountAfterDiscount fonksiyonu cagirilir.
	 */

	public void applyDiscount(Campaign... args) {
		for (Campaign campaign : args) {
			if (cart.keySet().contains(campaign.getCategory())) {
				if (cart.get(campaign.getCategory()).size() > campaign.getMinItem()) {
					double discountAmount = 0;
					if (campaign.getType().equals(DiscountType.RATE)) {
						discountAmount = (sumOfCategory(campaign.getCategory()) * campaign.getDiscount() / 100);
					} else if (campaign.getType().equals(DiscountType.AMOUNT)) {
						discountAmount = campaign.getDiscount();
					}
					boolean campaignCheck = true;
					for (Campaign c : campaigns.keySet()) {
						if (c.getCategory().equals(campaign.getCategory())) {
							if (campaigns.get(c) < discountAmount) {
								campaigns.put(c, discountAmount);
								campaignCheck = false;
							} else {
								campaignCheck = false;
							}
						}
					}
					if (campaignCheck) {
						campaigns.put(campaign, discountAmount);
					}
				}
			}
		}
		calculateTotalAmountAfterDiscount();
	}

	/*
	 * Sepetin kuponun sartini saglayip saglamadigina bakilir ve sagliyorsa
	 * kupon coupons nesnesine eklenir. calculateTotalAmountAfterDiscount
	 * cagrilarak uygun kuponun eklenmesi saglanir.
	 */

	public void applyCoupon(Coupon coupon) {
		if (getTotalAmount() > coupon.getMinAmount()) {
			double discountAmount = 0;
			if (coupon.getType().equals(DiscountType.RATE)) {
				discountAmount = (getTotalAmount() * coupon.getDiscountAmount() / 100);
			} else if (coupon.getType().equals(DiscountType.AMOUNT)) {
				discountAmount = coupon.getDiscountAmount();
			}
			coupons.put(coupon, discountAmount);
			calculateTotalAmountAfterDiscount();
		}
	}

	/*
	 * Food Almond 1 150.0TL 150.0TL Apple 3 100.0TL 300.0TL Car BMW 1 500.0TL
	 * 500.0TL
	 * 
	 * Total Discount: 395.0TL
	 * 
	 * Biciminde bir cikti verir.
	 */
	protected void print() {
		for (Map.Entry<Category, Hashtable<Product, Integer>> entry : cart.entrySet()) {
			System.out.println(entry.getKey().getTitle());
			for (Map.Entry<Product, Integer> product : entry.getValue().entrySet()) {
				System.out.println("\t" + product.getKey().getTitle() + "\t" + product.getValue() + "\t"
						+ product.getKey().getCost() + "TL\t" + product.getValue() * product.getKey().getCost() + "TL");
			}
		}
		System.out.println("\nTotal Discount: " + (getCampaignDiscount() + getCouponDiscounts()) + "TL");
	}

}
