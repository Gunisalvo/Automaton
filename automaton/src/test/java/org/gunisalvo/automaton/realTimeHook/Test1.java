package org.gunisalvo.automaton.realTimeHook;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;


public class Test1 extends AutomatonTest{

	@Override
	protected void runTest() {
		WebDriver driver = new HtmlUnitDriver();
		driver.get("http://www.google.com");
		System.out.println("running test1");
	}

}
