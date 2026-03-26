package jp.co.sss.crud.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jp.co.sss.crud.filter.AccountCheckFilter;
import jp.co.sss.crud.filter.LoginCheckFilter;

/**
 * 動作順などフィルタの設定を行うクラス
 */
@Configuration
public class FilterConfig implements WebMvcConfigurer {
	
	/**
	 * LoginCheckFilterの設定をするメソッド
	 * @return 設定対象LoginCheckFilterをジェネリクスにもつFilterRegistrationBeanクラスのオブジェクト
	 */
	@Bean
	public FilterRegistrationBean<LoginCheckFilter> configLoginCheckFilter() {
		FilterRegistrationBean<LoginCheckFilter> bean =
		  new FilterRegistrationBean<LoginCheckFilter>();
		
		bean.setFilter(new LoginCheckFilter());
		bean.setOrder(1);
		return bean;
	}
	/**
	 * AccountCheckFilterの設定をするメソッド
	 * @return 設定対象AccountCheckFilterをジェネリクスにもつFilterRegistrationBeanクラスのオブジェクト
	 */
	@Bean
	public FilterRegistrationBean<AccountCheckFilter> configAccountCheckFilter() {
		FilterRegistrationBean<AccountCheckFilter> bean =
		  new FilterRegistrationBean<AccountCheckFilter>();
				
		bean.setFilter(new AccountCheckFilter());
		bean.setOrder(2);
		return bean;
	}
}
