package jp.co.internous.ecsite.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.internous.ecsite.model.entity.Goods;

// GoodsテーブルのEntityにアクセスする、
public interface GoodsRepository extends JpaRepository<Goods, Long>{

}
