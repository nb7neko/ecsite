// ログイン情報をJava側からControllerにわたすためのクラス

package jp.co.internous.ecsite.model.dao;

import jp.co.internous.ecsite.model.entity.User;

public class LoginDto {

	private long id;
	private String userName;
	private String password;
	private String fullName;
	
	// コンストラクタを3つ持つ。
	// 1,インスタンス化の際に初期設定せず、後から1つずつSetterを使ってデータをセットする
	// 2,Userエンティティをまとめて受け取りデータをセット
	// 3,引数を分けて受け取り、データをセット
	
	public LoginDto() {}
	
	public LoginDto(User user) {
		this.id = user.getId();
		this.userName = user.getUserName();
		this.password = user.getPassword();
		this.fullName = user.getFullName();
	}
	
	public LoginDto(long id, String userName, String password, String fullName) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.fullName = fullName;
	}
	
	// getter(フィールドの値を取り出す) setter(フィールドに値を代入する)
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
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
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
}
