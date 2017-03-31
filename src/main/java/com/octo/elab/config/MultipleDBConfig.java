package com.octo.elab.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class MultipleDBConfig {

	private static final Logger logger = LoggerFactory.getLogger(MultipleDBConfig.class);

	@Bean(name = "roleManagementDb")
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource primaryDataSource() {
		DataSource ds = DataSourceBuilder.create().build();
		logger.info("DS-RoleManagement  :: " + ds.toString());
		return ds;
	}

	@Bean(name = "roleManagementTemplate")
	public JdbcTemplate roleManagementJdbcTemplate(@Qualifier("roleManagementDb") DataSource dsRoleManagement) {
		return new JdbcTemplate(dsRoleManagement);
	}

	@Bean(name = "roleClaimDb")
	@ConfigurationProperties(prefix = "role.claim.datasource")
	public DataSource secondaryDataSource() {
		String deploy_env = System.getenv("DEPLOY_ENV");
		if (deploy_env == null) {
			DataSource ds = DataSourceBuilder.create().build();
			logger.info("DS-StagingClaim - local :: " + ds.toString());
			return ds;
		} else {
			// DriverManagerDataSource ds = new DriverManagerDataSource();
			org.apache.tomcat.jdbc.pool.DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource();
			ds.setDriverClassName("org.postgresql.Driver");
			String dbURL = System.getenv("Stage_DB");
			String dbUserName = System.getenv("Stage_User");
			String dbPassword = System.getenv("Stage_Password");
			ds.setUrl(dbURL);
			ds.setUsername(dbUserName);
			ds.setPassword(dbPassword);
			ds.setDefaultReadOnly(true);
			ds.setTestOnBorrow(true);
			ds.setRemoveAbandoned(true);
			ds.setValidationQuery("SELECT 1");
			logger.info("DS-StagingClaim - " + deploy_env + " :: " + ds.toString());
			logger.info("DS-StagingClaim - dbURL : " + dbURL);
			logger.info("DS-StagingClaim - dbUserName : " + dbUserName);
			logger.info("DS-StagingClaim - dbPassword : " + dbPassword);
			return ds;
		}
	}

	@Bean(name = "roleClaimTemplate")
	public JdbcTemplate roleClaimJdbcTemplate(@Qualifier("roleClaimDb") DataSource dsRoleClaim) {
		return new JdbcTemplate(dsRoleClaim);
	}

	@Bean(name = "fhDb")
	@ConfigurationProperties(prefix = "fh.datasource")
	public DataSource ternaryDataSource() {
		String deploy_env = System.getenv("DEPLOY_ENV");
		if (deploy_env == null) {
			DataSource ds = DataSourceBuilder.create().build();
			logger.info("DS-FH - local :: " + ds.toString());
			return ds;
		} else {
			// DriverManagerDataSource ds = new DriverManagerDataSource();
			org.apache.tomcat.jdbc.pool.DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource();
			ds.setDriverClassName("org.postgresql.Driver");
			String dbURL = System.getenv("FH_DB");
			String dbUserName = System.getenv("FH_User");
			String dbPassword = System.getenv("FH_Password");
			ds.setUrl(dbURL);
			ds.setUsername(dbUserName);
			ds.setPassword(dbPassword);
			ds.setDefaultReadOnly(true);
			ds.setTestOnBorrow(true);
			ds.setRemoveAbandoned(true);
			ds.setValidationQuery("SELECT 1");
			logger.info("DS-FH - " + deploy_env + " :: " + ds.toString());
			logger.info("DS-FH - dbURL : " + dbURL);
			logger.info("DS-FH - dbUserName : " + dbUserName);
			logger.info("DS-FH - dbPassword : " + dbPassword);
			return ds;
		}
	}

	@Bean(name = "fhTemplate")
	public JdbcTemplate fhJdbcTemplate(@Qualifier("fhDb") DataSource dsFH) {
		return new JdbcTemplate(dsFH);
	}
}