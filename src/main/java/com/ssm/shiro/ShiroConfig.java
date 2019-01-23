package com.ssm.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

//http://jinnianshilongnian.iteye.com/blog/2018936/
//http://lgbolgger.iteye.com/blog/2163890����ѧϰ
@Configuration
public class ShiroConfig {
	@Bean
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
		System.out.println("ShiroConfiguration.shirFilter()");
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		//������.
		Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
        filterChainDefinitionMap.put("/favicon.ico","anon");
        filterChainDefinitionMap.put("/userAdd","anon");
		// ���ò��ᱻ���ص����� ˳���ж�
		filterChainDefinitionMap.put("/static/**", "anon");
		//�����˳� ������,���еľ�����˳�����Shiro�Ѿ�������ʵ����
		filterChainDefinitionMap.put("/logout", "logout");
		//<!-- ���������壬��������˳��ִ�У�һ�㽫/**������Ϊ�±� -->:����һ�����أ�һ��С�Ĵ���Ͳ���ʹ��;
		//<!-- authc:����url��������֤ͨ���ſ��Է���; anon:����url����������������-->


		filterChainDefinitionMap.put("/**", "authc");
        //filterChainDefinitionMap.put("/**", "user");
		// ���������Ĭ�ϻ��Զ�Ѱ��Web���̸�Ŀ¼�µ�"/login.jsp"ҳ��
		shiroFilterFactoryBean.setLoginUrl("/login");
		// ��¼�ɹ���Ҫ��ת������
		shiroFilterFactoryBean.setSuccessUrl("/index");

        FormAuthenticationFilter formAuthenticationFilter = new FormAuthenticationFilter();
        formAuthenticationFilter.setRememberMeParam("rememberMe");
        formAuthenticationFilter.setUsernameParam("username");
        formAuthenticationFilter.setPasswordParam("password");
        shiroFilterFactoryBean.getFilters().put("authc", formAuthenticationFilter);
		//δ��Ȩ����;
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	/**
	 * ƾ֤ƥ����
	 * ���������ǵ�����У�齻��Shiro��SimpleAuthenticationInfo���д�����
	 * ��
	 * @return
	 */
	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher(){
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName("md5");//ɢ���㷨:����ʹ��MD5�㷨;
		hashedCredentialsMatcher.setHashIterations(2);//ɢ�еĴ���������ɢ�����Σ��൱�� md5(md5(""));
		return hashedCredentialsMatcher;
	}

	@Bean
    public MyShiroRealm myShiroRealm(){
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
	}


	@Bean
	public SecurityManager securityManager(){
		DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
		securityManager.setRealm(myShiroRealm());
		// ��֤�� Authenticator ���realm����һ��ͨ����ͨ������ͨ����ͨ����
        //��Ȩ�� Authrizer��Ȩ�� ���߷��ʿ��������������������Ƿ���Ȩ�޽�����Ӧ�Ĳ��������������û��ܷ���Ӧ���е���Щ���ܣ�
        //SessionManager Session����Ҫ����ȥ���������������ڣ�����������SessionManager��
        //SessionDAO��Ҷ��ù������ݷ��ʶ������ڻỰ��CRUD�������������Session���浽���ݿ⣬
        // ��ô����ʵ���Լ���SessionDAO��ͨ����JDBCд�����ݿ⣻�������Session�ŵ�Memcached�У�
        // ����ʵ���Լ���Memcached SessionDAO������SessionDAO�п���ʹ��Cache���л��棬��������ܣ�
        //CacheManager����������������������û�����ɫ��Ȩ�޵ȵĻ���ģ�
        // ��Ϊ��Щ���ݻ����Ϻ���ȥ�ı䣬�ŵ������к������߷��ʵ�����
        //Cryptography ����ģ�飬Shiro�����һЩ�����ļ�������������������/���ܵġ�

        securityManager.setRememberMeManager(rememberMeManager());
		return securityManager;
	}

	/**
	 *  ����shiro aopע��֧��.
	 *  ʹ�ô���ʽ;������Ҫ��������֧��;
	 * @param securityManager
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

	@Bean(name="simpleMappingExceptionResolver")
	public SimpleMappingExceptionResolver createSimpleMappingExceptionResolver() {
		SimpleMappingExceptionResolver r = new SimpleMappingExceptionResolver();
		Properties mappings = new Properties();
		mappings.setProperty("DatabaseException", "databaseError");//���ݿ��쳣����
		mappings.setProperty("UnauthorizedException","403");
		r.setExceptionMappings(mappings);  // None by default
		r.setDefaultErrorView("error");    // No default
		r.setExceptionAttribute("ex");     // Default is "exception"
		//r.setWarnLogCategory("example.MvcLogger");     // No default
		return r;
	}

	@Bean
    public SimpleCookie rememberMeCookie(){
        SimpleCookie simpleCookie =  new SimpleCookie("rememberMe");
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(2592000);
        return simpleCookie;
    }
    @Bean
    public CookieRememberMeManager rememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCipherKey(org.apache.shiro.codec.Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        cookieRememberMeManager.setCookie(rememberMeCookie());
        return cookieRememberMeManager;
    }


}