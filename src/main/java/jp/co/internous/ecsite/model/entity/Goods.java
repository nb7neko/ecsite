// modelパッケージには、DBとのやり取りに使用される情報や、フロントとバック間でのやり取りに使用される情報などを、役割に応じて格納する。
// entityパッケージには、DBテーブルの1レコード(1件のデータ)を表すクラスを格納する。

package jp.co.internous.ecsite.model.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//Entityを付与すると、Springの機能により当該クラスはEntityとして振る舞うようになる。
@Entity
//Tableは、DBにある「どのテーブルの実態なのか」を指定している
@Table(name="goods")
public class Goods {
	
	// プライマリキー(主キー)であることを指定
	@Id
	// テーブルのどのカラム（列のこと）とマッピングるか指定
	@Column(name="id")
	// IDフィールドの振る舞い方を指定。今回はAuto_incrementとして振る舞う(主キー値を生成する)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	// テーブルのどのカラム（列のこと）とマッピングるか指定
	@Column(name="goods_name")
	private String goodsName;
	
	// テーブルのどのカラム（列のこと）とマッピングるか指定
	@Column(name="price")
	private long price;
	
	// テーブルのどのカラム（列のこと）とマッピングるか指定
	@Column(name="updated_at")
	private Timestamp updatedAt;
	
	// getter(フィールドの値を取り出す) setter(フィールドに値を代入する)
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

}
