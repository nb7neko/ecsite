// 購入機能、購入情報をフロントからJAVA側に渡すためのCart　

package jp.co.internous.ecsite.model.form;

import java.io.Serializable;

//implements Serializableと宣言したら「インスタンスをファイルなどに送信、書き込みができる」
public class Cart implements Serializable{
	// シリアライズされたものをデシリアライズする時に同一のバージョンにするための設定
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String goodsName;
	private long price;
	private long count;
	
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
	
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}

}
