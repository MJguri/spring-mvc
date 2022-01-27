package spring.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import spring.dao.MemberDao;
import spring.service.AuthService;
import spring.service.ChangePasswordService;
import spring.service.MemberRegisterService;

@Configuration
@EnableTransactionManagement  // tx:annotation-driven transaction-manager="transactionManager"
public class MemberConfig {
	
	@Autowired
	private DataSource  dataSource;
	
//	@Bean
//	public DataSource dataSource() {
//		ComboPooledDataSource ds = new ComboPooledDataSource();
//		
//		try {
//			ds.setDriverClass("oracle.jdbc.OracleDriver");
//		} catch (PropertyVetoException e) {
//			e.printStackTrace();
//		}
//		ds.setJdbcUrl("jdbc:oracle:thin:@oracle.interstander.com:41521:XE");
//		ds.setUser("green00");
//		ds.setPassword("4321");
//		
//		return ds;
//	}
	
	@Bean
	public DataSourceTransactionManager transactionManager() {
		DataSourceTransactionManager txMgr = new DataSourceTransactionManager();
		txMgr.setDataSource(dataSource);
		return txMgr;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		JdbcTemplate jt = new JdbcTemplate(dataSource);
		return jt;
	}
	
	@Bean
	public MemberDao dao() {
		MemberDao dao = new MemberDao(jdbcTemplate());
		return dao;
	}
	
	@Bean
	public MemberRegisterService regSvc() {
		MemberRegisterService regSvc = new MemberRegisterService(dao());
		return regSvc;
	}
	
	@Bean
	public ChangePasswordService changePwdSvc() {
		ChangePasswordService changePwdSvc = new ChangePasswordService(dao());
		return changePwdSvc;
	}
	
	@Bean
	public AuthService authService() {
		AuthService authSvc = new AuthService();
		authSvc.setMemberDao(dao());
		return authSvc;
	}
	
	
	
	
	
}
