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
@Table(name="purchase")
public class Purchase {

	// プライマリキー(主キー)であることを指定
	@Id
	// テーブルのどのカラム（列のこと）とマッピングるか指定
	@Column(name="id")
	// IDフィールドの振る舞い方を指定。今回はAuto_incrementとして振る舞う(主キー値を生成する)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	// テーブルのどのカラム（列のこと）とマッピングるか指定
	@Column(name="user_id")
	private long userId;
	
	// テーブルのどのカラム（列のこと）とマッピングるか指定
	@Column(name="goods_id")
	private long goodsId;
	
	// テーブルのどのカラム（列のこと）とマッピングるか指定
	@Column(name="goods_name")
	private String goodsName;
	
	// テーブルのどのカラム（列のこと）とマッピングるか指定
	@Column(name="item_count")
	private long itemCount;
	
	// テーブルのどのカラム（列のこと）とマッピングるか指定
	@Column(name="total")
	private long total;
	
	// テーブルのどのカラム（列のこと）とマッピングるか指定
	@Column(name="created_at")
	private Timestamp createdAt;
	
	// getter(フィールドの値を取り出す) setter(フィールドに値を代入する)
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
		
	public long getItemCount() {
		return itemCount;
	}
	public void setItemCount(long itemCount) {
		this.itemCount = itemCount;
	}
	
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
}
