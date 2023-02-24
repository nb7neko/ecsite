// ログインするには、HTMLのformからユーザ情報（ユーザ名とパスワード）を渡す必要がある為、LoginForm（画面からJavaプログラムに送るデータを管理するクラス）を作成

package jp.co.internous.ecsite.model.form;

import java.io.Serializable;

// implements Serializableと宣言したら「インスタンスをファイルなどに送信、書き込みができる」
public class LoginForm implements Serializable {
	// シリアライズされたものをデシリアライズする時に同一のバージョンにするための設定
	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String password;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
	this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
