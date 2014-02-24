package org.gunisalvo.automaton.realTimeHook;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;


public class Test2 extends AutomatonTest{

	@Override
	protected void runTest() {
		WebDriver driver = new HtmlUnitDriver();
		driver.get("http://www.yahoo.com");
		System.out.println("running test2");
	}

}
