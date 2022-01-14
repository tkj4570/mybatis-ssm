package com.xiana.mybatis.test;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.xiana.mybatis.bean.Employee;
import com.xiana.mybatis.config.SpringConfig;
import com.xiana.mybatis.dao.EmployeeMapper;
import com.xiana.mybatis.service.EmployeeService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * <p>包名称: com.xiana.mybatis.test </p>
 * <p>项目名称: mybatis-ssm </p>
 * <p>文件名称: null.java </p>
 * <p>描述: [类型描述] </p>
 * <p>创建时间: 2022/1/13 下午6:09 </p>
 * <p>公司信息: 全球贸易通公司</p>
 *
 * @author <a href="mail to: 457066709@qq.com" rel="nofollow">唐科技</a>
 * @version v1.0
 * @update [序号][日期YYYY-MM-DD] [更改人姓名][变更描述]
 */
public class DataSourceTest {
    private AnnotationConfigApplicationContext ctx;

    @Before
    public void init(){
        ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
    }

    @After
    public void destroy(){
        ctx.close();
    }

    /**
     * 测试druid数据池连接
     * @throws SQLException
     */
    @Test
    public void testDruidDataSource() throws SQLException {
        DruidDataSource dataSource = ctx.getBean("dataSource", DruidDataSource.class);
        DruidPooledConnection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from tbl_employee");
        while (resultSet.next()) {
            System.out.println(resultSet.getObject(2));
        }
    }

    @Test
    public void testSqlSessionFactory(){
        SqlSessionFactory factory = ctx.getBean("sqlSessionFactory", SqlSessionFactory.class);
        SqlSession sqlSession = factory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = mapper.getEmployeeById(2);
        System.out.println(employee);
    }

    @Test
    public void testEmployeeService(){
        EmployeeService employeeService = ctx.getBean("employeeService", EmployeeService.class);
        List<Employee> list = employeeService.list();
        for (Employee employee : list) {
            System.out.println(employee);
        }
    }
}
