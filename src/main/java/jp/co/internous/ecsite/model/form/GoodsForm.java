// 新規商品を登録する機能を実装

package jp.co.internous.ecsite.model.form;

import java.io.Serializable;

//implements Serializableと宣言したら「インスタンスをファイルなどに送信、書き込みができる」
public class GoodsForm implements Serializable{
	// シリアライズされたものをデシリアライズする時に同一のバージョンにするための設定
	private static final long serialVersionUID = 1L;
	
	private long id;
	
	private String goodsName;
	
	private long price;
	
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

}
