package org.gunisalvo.automaton.realTimeHook;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Test3 extends AutomatonTest{

	@Override
	protected void runTest() {
		WebDriver driver = new HtmlUnitDriver();
		driver.get("http://www.bing.com");
		System.out.println("running test3");
	}

}
