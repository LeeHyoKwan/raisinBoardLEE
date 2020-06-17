package com.raisin.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.ExceptionMappingInterceptor;
import com.raisin.constants.CommonContants;

/**
 * ログインしているユーザーかのチェックインターセプタ
 *
 * @author raisin
 * @since 2020/06/01
 * @version 1.0.0
 */
public class LoginCheckInterceptor extends ExceptionMappingInterceptor {

	public String intercept(ActionInvocation invocation) {
		try {

			/** アクションの前処理 */
			// セッション情報を取得する。
			Map<String, Object> session = invocation.getInvocationContext().getSession();

			// セッション情報の有無をチェックする。
			if (session.get(CommonContants.SESSION_USER) == null) {
				return "login";
			}

			/** アクションの実行 */
			String result = super.intercept(invocation);

			//TODO アクションの後処理（必要であれば記述）

			return result;
		} catch (Throwable t) {
			return "exception";
		}
	}

}
