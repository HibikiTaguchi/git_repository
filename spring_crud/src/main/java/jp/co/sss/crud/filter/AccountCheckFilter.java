package jp.co.sss.crud.filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jp.co.sss.crud.entity.Employee;
/** ユーザー権限を判定するためのフィルタクラス */
public class AccountCheckFilter extends HttpFilter {
	/**
	 * 現在のURLを取得して権限による表示の可否を判定
	 * doFilterデフォルトの3つの引数
	 * 戻り値無し
	 */
	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response,
		      FilterChain chain) throws IOException, ServletException {
		//現在のURLを取得
		String requestURL = request.getRequestURI();
		//セッションスコープからログイン中のユーザー情報を取得
		HttpSession session = request.getSession();
		Employee loginUser = (Employee) session.getAttribute("user");
		//まずURLが管理者権限の必要な部分に該当する場合
		if (requestURL.contains("regist") || requestURL.contains("delete")) {
			//ログインユーザーが一般権限ならログインページへ
			if (loginUser.getAuthority() == 1) {
				response.sendRedirect("/spring_crud/");
				return;
			} else {
				//そうでなければフィルタ処理を終了
				chain.doFilter(request, response);
			}
		//登録、削除以外の画面ならフィルタ終了
		} else {
			chain.doFilter(request, response);
		}
	}
}
