package mainpackage;
import java.util.Scanner;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import mainpackage.dao.NamedParamJdbcDaoImpl;
public class EngineersLoginApp {
	public static void main(String[] args) {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("engineersinfo.xml");
		Engineers engineers = ctx.getBean("engineers", Engineers.class);
		Scanner scanner = new Scanner(System.in);
		boolean loginFail = true;
		boolean ifNotExit = true;
		while (loginFail) {
			System.out.println("Enter 'exit' for both username and password if you want to exit.");
			System.out.println("Enter username: ");
	        String inputname = scanner.nextLine();
	        System.out.println("Enter password: ");
	        String inputpw = scanner.nextLine();
	        ifNotExit = !inputname.equals("exit") || !inputpw.equals("exit");
	        loginFail = engineers.login(inputname, inputpw) && ifNotExit;
		}
		if(loginFail == false && ifNotExit == true) {
			AbstractApplicationContext ctx2 = new ClassPathXmlApplicationContext("namedparamjdbcdaoimpl.xml");
			NamedParamJdbcDaoImpl dao = ctx2.getBean("namedParamJdbcDaoImpl", NamedParamJdbcDaoImpl.class);
			dao.fillTableFromCsv("C:\\Users\\berkehan\\Downloads\\CustomerData.csv");
			ctx2.close();
		}
		scanner.close();
		ctx.close();
	}
}
