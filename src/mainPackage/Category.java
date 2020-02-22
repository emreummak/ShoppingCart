package mainPackage;

public class Category {

	private String title;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	private Category() {

	}

	public Category(String title) {
		try {
			setTitle(title);
		} catch (NullPointerException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
