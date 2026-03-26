package jp.co.sss.crud.filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.co.sss.crud.form.LoginForm;
/** ログイン機能を管理するためのフィルタクラス */
public class LoginCheckFilter extends HttpFilter {
	/**
	 * ログインの要らないページとそれ以外を判定
	 * doFilterデフォルトの3つの引数
	 * 戻り値無し
	 */
	@Override 
    public void doFilter(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain) throws IOException, ServletException { 
		//現在のURLを取得
		String requestURL = request.getRequestURI();
		//ログイン前の2つのURLと静的コンテンツをフィルタ処理から除外
		if (requestURL.endsWith("/spring_crud/") || requestURL.endsWith("/login") ||
		  requestURL.indexOf("/html/") != -1 || requestURL.indexOf("/css/") != -1 ||
		    requestURL.indexOf("/img/") != -1 || requestURL.indexOf("/js/") != -1) {
			chain.doFilter(request, response);
		} else {
			//セッションスコープからからログイン情報を取得
			HttpSession session = request.getSession();
			LoginForm loginForm = (LoginForm) session.getAttribute("loginForm");
			//ログイン情報が無い＝未ログインならログイン画面へ
			if (loginForm == null) {
				response.sendRedirect("/spring_crud/");
				return;
			} else {
				//ログインしていればフィルタ処理終了
				chain.doFilter(request, response);
			}
 		}
        
        
    }
}
