package jp.co.internous.ecsite.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import jp.co.internous.ecsite.model.dao.GoodsRepository;
import jp.co.internous.ecsite.model.dao.LoginDto;
import jp.co.internous.ecsite.model.dao.PurchaseRepository;
import jp.co.internous.ecsite.model.dao.UserRepository;
import jp.co.internous.ecsite.model.dto.HistoryDto;
import jp.co.internous.ecsite.model.entity.Goods;
import jp.co.internous.ecsite.model.entity.Purchase;
import jp.co.internous.ecsite.model.entity.User;
import jp.co.internous.ecsite.model.form.CartForm;
import jp.co.internous.ecsite.model.form.HistoryForm;
import jp.co.internous.ecsite.model.form.LoginForm;

//クライアントからのリクエストを受け取り、処理し、レスポンスを返却することができるようになる
@Controller
//URL(localhost:8080/ecsite/)でアクセスできるように設定
@RequestMapping("/ecsite")
public class IndexController {
	
	// Autowiredを付与されたフィールドは、new演算子を使うことなくインスタンス化される
	@Autowired
	// UsersエンティティからuserテーブルにアクセスするDAO
	private UserRepository userRepos;
	// Autowiredを付与されたフィールドは、new演算子を使うことなくインスタンス化される
	@Autowired
	// GoodsエンティティからgoodsテーブルにアクセスするDAO
	private GoodsRepository goodsRepos;
	// Autowiredを付与されたフィールドは、new演算子を使うことなくインスタンス化される
	@Autowired
	// PurchaseエンティティからpurchaseテーブルにアクセスするDAO
	private PurchaseRepository purchaseRepos;
	
	// webサービスAPIとして作詞するためJSON形式を扱えるようGsonをインスタンス化する
	private Gson gson = new Gson();
	
	@RequestMapping("/")
	// HTMLで表示する値を渡したいので、メソッドの引数にModelを追加し、Modelに対して値をセットする
	public String index(Model m) {
		// goodsRepostoryのインスタンスからfindAllメソッドを呼び出し、Goodsエンティティのリストを取得
		List<Goods> goods = goodsRepos.findAll();
		// m.addAttributeメソッドで画面に渡したいデータをModelオブジェクトに追加 m.addAttribute("属性名",渡したいデータ);
		// addAttribute(goods)というキーでリストのgoodsを渡す
		m.addAttribute("goods", goods);
		// index.htmlに遷移する設定
		return "index";
	}
	
	// DBテーブル（user)から、ユーザ名とパスワードを検索し、結果を取得。
	// その後、DTOをゲストの情報で初期化し、検索結果が存在していた場合のみ、実在のユーザ情報をDTOに詰めている。
	// 最終的に、DTOをJSONNオブジェクトとして画面側に返している。
	
	@ResponseBody
	@PostMapping("/api/login")
	// クライアント側で loginApiを使えるようにする空のクラス
	public String loginApi(@RequestBody LoginForm form) {
		// userRepostoryのインスタンスからfindメソッドを呼び出し、userエンティティのリストを取得
		List<User> users = userRepos.findByUserNameAndPassword(form.getUserName(), form.getPassword());
		
		LoginDto dto = new LoginDto(0, null, null, "ゲスト");
		// ログインしたアカウントのIDが０より大きかった場合（存在していた場合）初期化していたdtoに実在のユーザー情報を上書きする
		if(users.size() > 0) {
			dto = new LoginDto(users.get(0));
		}
		// アカウントがなかったらゲストのままアクセス
		return gson.toJson(dto);
	}
	
	// 購入処理
	
	@ResponseBody
	@PostMapping("/api/purchase")
	// クライアント側で purchaseApiを使えるようにする空のクラス
	public String purchaseApi(@RequestBody CartForm f) {
		
		f.getCartList().forEach((c) -> {
			long total = c.getPrice() * c.getCount();
			purchaseRepos.persist(f.getUserId(), c.getId(), c.getGoodsName(), c.getCount(), total);
		});
		// 数値型を文字列型へ変換する、変換したい値があっても、NullPointerExceptionを発生させない
		return String.valueOf(f.getCartList().size());
	}
	
	// 購入履歴
	
	@ResponseBody
	@PostMapping("/api/history")
	public String historyApi(@RequestBody HistoryForm form) {
		String userId = form.getUserId();
		List<Purchase> history = purchaseRepos.findHistory(Long.parseLong(userId));
		List<HistoryDto> historyDtoList = new ArrayList<>();
		history.forEach((v) -> {
			HistoryDto dto = new HistoryDto(v);
			historyDtoList.add(dto);
		});
		
		return gson.toJson(historyDtoList);
	}

}
