package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CampaignTest.class, CategoryTest.class, CouponTest.class, DeliveryCostCalculatorTest.class,
		ProductTest.class, ShoppingCartTest.class })
public class AllTests {

}
