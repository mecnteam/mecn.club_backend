package club.mecn.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import club.mecn.module.Category;
import club.mecn.module.Thread;
import club.mecn.service.CategoryService;
import club.mecn.service.ThreadService;


/**
 * 测试category和thread
 * @author Administrator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/configtext/mecn-servlet-test.xml"})
public class CategoryTest {

	@Autowired
	private CategoryService cs;
	
	@Autowired
	private ThreadService ts;
	
	
	@Test
	public void addCategory()
	{
		Category c = new Category("news");
		cs.add(c);
		
		
	}
	//增贴
	@Test
	public void addThread()
	{
		Thread t = new Thread("delicious egg");
		ts.add(t, "egg");
		
	}
	
	@Test
	public void addWithNewCategory()
	{
		Thread t = new Thread("playground1");
		ts.add(t, "speedrun");
	}
	
	//删贴
	@Test
	public void remove()
	{
		ts.delete(6);
	}
	
	//该贴
	
	//查--(所有)
	@Test
	public void findall()
	{
		List<Thread> all = ts.getAll();
		System.out.println("帖子数:" + all.size());
		for(Thread t : all){
			
			System.out.print(t.getThreadTitle() + " | ");
			for(Category c : t.getCategories())
			{
				System.out.print(c.getCategoryName() + " " );
			}
			System.out.println();
		}
	}
	
	@Test
	public void findAllCategory()
	{
		List<Category> all = cs.getAll();
		System.out.println(all.size());
		for(Category a : all)
		{
			System.out.println(a.getCategoryName());
		}
	}
	
	//查看某分类的帖子
	@Test
	public void findAllThreadOfC()
	{
		List<Thread> list = cs.getAllThreads(5);
		for(Thread t : list)
		{
			System.out.println(t.getThreadTitle());
		}
	}
	
	//变更标签
	
	@Test
	public void existThreadAddTag()
	{
		
		ts.updateCategories(8, new String[]{"pig","dog"});
		
	}
	
	
}
