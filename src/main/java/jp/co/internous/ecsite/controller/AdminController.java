// Controllerは主に画面遷移を担うため、対応するフロントエンド（HTML）と交互に作成することで動作や表示を確認しながら開発を進められる

package jp.co.internous.ecsite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.internous.ecsite.model.dao.GoodsRepository;
import jp.co.internous.ecsite.model.dao.UserRepository;
import jp.co.internous.ecsite.model.entity.Goods;
import jp.co.internous.ecsite.model.entity.User;
import jp.co.internous.ecsite.model.form.GoodsForm;
import jp.co.internous.ecsite.model.form.LoginForm;

// クライアントからのリクエストを受け取り、処理し、レスポンスを返却することができるようになる
@Controller
// URL(localhost:8080/ecsite/admin/)でアクセスできるように設定
@RequestMapping("/ecsite/admin")
public class AdminController {
	
	// Autowiredを付与されたフィールドは、new演算子を使うことなくインスタンス化される
	@Autowired
	private UserRepository userRepos;
	@Autowired
	private GoodsRepository goodsRepos;
	
	@RequestMapping("/")
	public String index() {
		return "adminindex";
	}
	
	// メソッドとPOST処理を行うURLを紐づける役割を担う
	@PostMapping("/welcome")
	// HTMLで表示する値を渡したいので、メソッドの引数にModelを追加し、Modelに対して値をセットする
	public String welcome(LoginForm form, Model m) {
		// userRepostoryのインスタンスからfindAllメソッドを呼び出し、userエンティティのリストを取得(ユーザ名とパスワードでユーザを詮索)
		List<User> users = userRepos.findByUserNameAndPassword(form.getUserName(), form.getPassword());
		// コンソールに表示
		System.out.println(form.getUserName() + " " + form.getPassword());
		
		// userリストがnullではないかつ、リストに値があればTrue
		if (users != null && users.size() > 0) {
			// isAdminにTrue or Falseを入れる、ログインしているユーザーのIsAdminが0ならTrue
			boolean isAdmin = users.get(0).getIsAdmin() != 0;
			// アドミンでのログインだった場合(True)、アドミンではなかったら何も表示されない(False)
			if (isAdmin) {
				// goodsRepostoryのインスタンスからfindAllメソッドを呼び出し、Goodsエンティティのリストを取得
				List<Goods> goods = goodsRepos.findAll();
				// m.addAttributeメソッドで画面に渡したいデータをModelオブジェクトに追加 m.addAttribute("属性名",渡したいデータ);
				// addAttribute(userName,password,goods)というキーでリストの0番目のusername,passを渡す、goodsを渡す
				m.addAttribute("userName", users.get(0).getUserName());
				m.addAttribute("password", users.get(0).getPassword());
				m.addAttribute("goods", goods);
			}
		}
		// welcome.htmlに遷移する設定
		return "welcome";
	}
	
	// 新商品登録機能
	
	// welcome.htmlの新規追加からアクセス
	@RequestMapping("/goodsMst")
	// クライアント側で addGoodsを使えるようにする空のクラス
	public String goodsMst(LoginForm form, Model m) {
		// ControllerからViewに値を受け渡すためのパラメーター
		m.addAttribute("userName", form.getUserName());
		m.addAttribute("password", form.getPassword());
		
		// goodsmst.htmlに遷移する設定
		return "goodsmst";
	}
	
	// addGoods
	
	@RequestMapping("/addGoods")
	// クライアント側で addGoodsを使えるようにする空のクラス
	public String addGoods(GoodsForm goodsForm, LoginForm loginForm, Model m) {
		// ControllerからViewに値を受け渡すためのパラメーター
		m.addAttribute("userName", loginForm.getUserName());
		m.addAttribute("password", loginForm.getPassword());
		
		// goodsという名前で新しくインスタンスを作成
		Goods goods = new Goods();
		// addGoodsからもらった新規のGoodsNameをGoodsForm.Javaにgetした後setする
		goods.setGoodsName(goodsForm.getGoodsName());
		// addGoodsからもらった新規のPriceをGoodsForm.Javaにgetした後setする
		goods.setPrice(goodsForm.getPrice());
		// goodsReposにアクセスして、新規のgoodsをSAVE（エンティティの保存）andFLUSH（操作を反映させる）
		// 引数のエンティティを保存し、それをデータベースに反映させる
		goodsRepos.saveAndFlush(goods);
		
		// welcome.htmlに遷移する設定
		return "forward:/ecsite/admin/welcome";
	}
	
	// 商品マスタから商品を消去する機能、ページ遷移による処理ではなく、ajaxを使用した方式で処理する（RESTと呼ばれる）
	
	@ResponseBody
	@PostMapping("/api/deleteGoods")
	// クライアント側で deleteApiを使えるようにする空のクラス
	public String deleteApi(@RequestBody GoodsForm f, Model m) {
		try {
			// 指定されたIDのエンティティを消去する
			goodsRepos.deleteById(f.getId());
			// 不正な引数（エンティティの消去）をキャッチしたらエラーを知らせる
		} catch(IllegalArgumentException e) {
			// エラーを得たら-1を返す
			return "-1";
		}
		// エラーを得られなかったら1を送信
		return "1";
	}

}
