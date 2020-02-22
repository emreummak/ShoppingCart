package mainPackage;

public class Product {

	private String title;
	private double cost;
	private Category category;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if (!title.isEmpty())
			this.title = title;
		else {
			throw new NullPointerException();
		}
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		if (cost > 0)
			this.cost = cost;
		else {
			throw new IndexOutOfBoundsException();
		}
	}

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

	private Product() {

	}

	public Product(String title, double cost, Category category) {
		try {
			setCategory(category);
			setCost(cost);
			setTitle(title);
		} catch (NullPointerException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
