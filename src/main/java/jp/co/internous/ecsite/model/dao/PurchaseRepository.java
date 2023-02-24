// 購入情報をJava側からフロントにわたすためのPurchaseエンティティを介し、purchaseテーブルにアクセスする役割

package jp.co.internous.ecsite.model.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jp.co.internous.ecsite.model.entity.Purchase;

//GoodsテーブルのEntityにアクセスする、
public interface PurchaseRepository extends JpaRepository<Purchase, Long>{
	
	@Query(value="SELECT * FROM purchase p " +
			"WHERE created_at = (" + 
			"SELECT MAX(created_at) FROM purchase p WHERE p.user_id = :userId)",
			// JPQLではなくSQLによる問い合わせができるようになる
			nativeQuery=true)
	
	// Purchaseリストからあるユーザーの履歴を見つける
	List<Purchase> findHistory(@Param("userId") long userId);
	
	// purchaseテーブルの要素にそれぞれの値を追加する
	@Query(value="INSERT INTO purchase (user_id, goods_id, goods_name, item_count, total, created_at) " +
			"VALUES (?1, ?2, ?3, ?4, ?5, now())", nativeQuery=true)
	
	// 最初の処理になる
	@Transactional
	@Modifying
	void persist(@Param("userId") long userId,
				@Param("goodsId") long productId,
				@Param("goodsName") String goodsName,
				@Param("itemCount") long itemCount,
				@Param("total") long total);

}
