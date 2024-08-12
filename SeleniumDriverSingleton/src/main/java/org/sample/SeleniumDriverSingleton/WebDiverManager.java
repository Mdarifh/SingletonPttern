package org.sample.SeleniumDriverSingleton;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDiverManager {
	private static volatile  WebDiverManager instance;
	private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
	
	private WebDiverManager() {}
	
	private void initDriver(String browser) {
		switch(browser) {
		case "chrome":
			tlDriver.set(new ChromeDriver());
			break;
			
		case "firefox":
			tlDriver.set(new FirefoxDriver());
			break;
			
		case "MicrosoftEgde":
			tlDriver.set(new EdgeDriver());
			break;
			
			default:
				throw new IllegalArgumentException("Unsupported browser:" + browser);
				
			
		}
	}
	
	public static WebDiverManager getInstance(String browser) {
		if (instance== null) {
			synchronized (WebDiverManager.class) {
				if(instance==null) {
					instance = new WebDiverManager();
					
				}
			}
		}
		
		if (tlDriver.get()==null) {
			instance.initDriver(browser);
		}
		return instance;
		
	}
	
	public WebDriver getDriver() {
		return tlDriver.get();
		
	}
	
	public static void quitBrowser() {
		if (tlDriver.get()!=  null) {
			tlDriver.get().quit();
			tlDriver.remove();
		}
	}
}
