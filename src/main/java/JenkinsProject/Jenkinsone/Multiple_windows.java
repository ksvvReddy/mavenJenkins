package JenkinsProject.Jenkinsone;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Multiple_windows {

	@Test(groups = { "regression" })
	public void check() throws Exception {
		// TODO Auto-generated method stub
		WebDriverManager.firefoxdriver().setup();
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/popup.php");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()='Click Here']")).click();
		Thread.sleep(2000);
		String MainWindow = driver.getWindowHandle();
		System.out.println(MainWindow);

		// To handle all new opened window.
		Set<String> s1 = driver.getWindowHandles();
		Iterator<String> i1 = s1.iterator();

		while (i1.hasNext()) {
			String ChildWindow = i1.next();
			System.out.println(ChildWindow);

			if (!MainWindow.equalsIgnoreCase(ChildWindow)) {

				// Switching to Child window
				driver.switchTo().window(ChildWindow);
				driver.findElement(By.name("emailid")).sendKeys("vardhanreddy071094@gmail.com");

				driver.findElement(By.name("btnLogin")).click();

				// Closing the Child Window.
				driver.close();
			}
		}
		// Switching to Parent window i.e Main Window.
		driver.switchTo().window(MainWindow);
		driver.quit();
	}
	
	@Test(groups = { "sanity" })
	public  void check2() throws Exception{
		WebDriverManager.firefoxdriver().setup();
		WebDriver driver = new FirefoxDriver();
		 driver.manage().window().maximize();			
	     driver.get("https://jqueryui.com/");	
	     Thread.sleep(1000);
	     driver.findElement(By.xpath("//a[text()='Draggable']")).click();
	     Thread.sleep(1000);
	     driver.findElement(By.xpath("//a[text()='Droppable']")).click();
	     Thread.sleep(1000);
	     WebElement main_frame=driver.findElement(By.className("demo-frame"));
	     driver.switchTo().frame(main_frame);
	     WebElement draggable=driver.findElement(By.xpath("//p[text()='Drag me to my target']"));
	     WebElement droppable=driver.findElement(By.xpath("//p[text()='Drop here']"));
	     Actions actions=new Actions(driver);
	     actions.dragAndDrop(draggable, droppable).build().perform();
	     Thread.sleep(1000);
	     driver.quit();    
	}

	
}