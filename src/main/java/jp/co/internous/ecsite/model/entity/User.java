// modelパッケージには、DBとのやり取りに使用される情報や、フロントとバック間でのやり取りに使用される情報などを、役割に応じて格納する。
// entityパッケージには、DBテーブルの1レコード(1件のデータ)を表すクラスを格納する。

package jp.co.internous.ecsite.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// Entityを付与すると、Springの機能により当該クラスはEntityとして振る舞うようになる。
@Entity
// Tableは、DBにある「どのテーブルの実態なのか」を指定している
@Table(name="user")
public class User {
	
	// プライマリキー(主キー)であることを指定
	@Id
	// テーブルのどのカラム（列のこと）とマッピングるか指定
	@Column(name="id")
	// IDフィールドの振る舞い方を指定。今回はAuto_incrementとして振る舞う(主キー値を生成する)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	// テーブルのどのカラム（列のこと）とマッピングるか指定
	@Column(name="user_name")
	private String userName;
	
	// テーブルのどのカラム（列のこと）とマッピングるか指定
	@Column(name="password")
	private String password;
	
	// テーブルのどのカラム（列のこと）とマッピングるか指定
	@Column(name="full_name")
	private String fullName;
	
	// テーブルのどのカラム（列のこと）とマッピングるか指定
	@Column(name="is_admin")
	private int isAdmin;
	
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
	
	public int getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}
}
