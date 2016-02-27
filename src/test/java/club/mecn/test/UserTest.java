package club.mecn.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import club.mecn.module.UsedName;
import club.mecn.module.User;
import club.mecn.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/configtext/mecn-servlet-test.xml"})
public class UserTest {
	
	@Autowired
	private UserService s;
	
	@Test
	public void addUser()
	{
		System.out.println("ADD User!");
		User u = new User("gamestart","gametarts","gamestart.passwoerds");
		s.register(u);
	}
	
	@Test
	public void addUsedName()
	{
		User u = s.getByName("gamestart");
		UsedName un1 = new UsedName("game");
		UsedName un2 = new UsedName("start");
		u.addUsedName(un1);
		u.addUsedName(un2);
		
	}
	
	@Test
	public void findUser()
	{
		User u = s.getByName("gamestart");
		System.out.println(u);
	}
	

}
