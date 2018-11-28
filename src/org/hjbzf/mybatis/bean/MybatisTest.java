package org.hjbzf.mybatis.bean;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.hjbzf.mybatis.dao.EmployeeMapper;
import org.junit.Test;

public class MybatisTest {

	//定义一个getSqlSessionFactory方法
	public SqlSessionFactory getSqlSessionFactory() throws IOException{
		/**
		 * 1、根据xml配置文件(全局配置文件)，创建一个SqlsessionFactory对象
		 */
		String resource = "config-mybatis.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		return new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	/**
	 * 注意：
	 * 1、接口式编程
	 * 		原生：                      Dao  ===  DaoImpl
	 * 		mybatis：        Mapper   ===   xxMapper.xml
	 * 
	 * 2、SqlSession代表数据库的一次回话；用完必须关闭；
	 * 3、SqlSession和connection一样都是非线程安全，每次使用我们都应该去获取新的对象
	 * 4、mapper接口没有实现类，但是mybatis会为这个接口生成一个代理对象。
	 * 	  （将接口和xml进行绑定）
	 *   EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
	 * 5、两个重要的配置文件：
	 *   mybatis的全局配置文件(mybatis-config.xml)，包含数据库连接池信息，事务管理器信息等...系统运行环境信息
	 *   sql映射文件(EmployeeMapper.xml):保存了每一个sql语句的映射信息，
	 *   			将sql抽取出来。
	 * 
	 * @throws IOException
	 */
	@Test
	public void test() throws IOException{
		//1、获取sqlSessionFactory对象
		SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
		
		//2、获取SqlSession对象(通过openSession获取)
		SqlSession openSession = sqlSessionFactory.openSession();
		
		try{
			//3、获取接口的实现类对象
			//会为接口自动的创建一个代理对象，代理对象去执行增删改查方法
			EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
			
			Employee employee = mapper.getEmpById(1);
			System.out.println(mapper.getClass());
			System.out.println(employee);
		}finally{
			openSession.close();
		}
	}
}
