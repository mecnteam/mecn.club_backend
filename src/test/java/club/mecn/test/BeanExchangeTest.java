package club.mecn.test;

import org.junit.Test;

import club.mecn.module.Comment;
import club.mecn.module.User;
import club.mecn.util.BeanValueExchangeUtil;

public class BeanExchangeTest {

	@Test
	public void exchange()
	{
		User user1 = new User("game","game","where itis h");
		User user2 = new User("game","start","game");
		System.out.println(user1.getPassword() + user1.getEmail());
		
		BeanValueExchangeUtil.exchangeBeanValue(user1, user2);
		
		System.out.println(user1.getPassword() + user1.getEmail());
	}
	
	@Test
	public void exchange2()
	{
		Comment comment1 = new Comment("comment1");
		Comment comment2 = new Comment("comment2");
		
		System.out.println(comment1.getCommentContent());
		
		BeanValueExchangeUtil.exchangeBeanValue(comment1, comment2);
		
		System.out.println(comment1.getCommentContent());
	}
}
