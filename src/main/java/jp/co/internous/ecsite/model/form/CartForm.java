// 購入機能、購入情報をフロントからJAVA側に渡すためのCartForm
// カート情報は、カートに入れた商品のリストとしてわたってくるため、Cartのリストを保持する役目を持つCartFormが存在する

package jp.co.internous.ecsite.model.form;

import java.io.Serializable;
import java.util.List;

//implements Serializableと宣言したら「インスタンスをファイルなどに送信、書き込みができる」
public class CartForm implements Serializable {
	// シリアライズされたものをデシリアライズする時に同一のバージョンにするための設定
	private static final long serialVersionUID = 1L;
	
	private long userId;
	private List<Cart> cartList;
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public List<Cart> getCartList(){
		return cartList;
	}
	public void setCartList(List<Cart> cartList) {
		this.cartList = cartList;
	}

}
