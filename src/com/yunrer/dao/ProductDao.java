package com.yunrer.dao;

import com.yunrer.entity.Product;
import com.yunrer.entity.ProductImage;
import com.yunrer.entity.ProductItems;
import com.yunrer.entity.SysStringMap;
import com.yunrer.util.SkuSqlUtil;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository("ProductDao")
public class ProductDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	/**
	 * 查询pc热卖推荐商品列表
	 * 
	 * @param count
	 *           Integer 数量
	 * @return
	 */
	public List<Product> queryProductRedList(Integer count) {
		String sql = "select a.id,a.name,a.sub_title,a.img_url,a.is_yj,a.is_cx,a.is_ms,"
				+ "case when items.id is not null then concat( substring_index(  concat('(',IF(isnull(items.attrVal1),'',CONCAT(items.attrVal1,',')),IF(isnull(items.attrVal2),'',CONCAT(items.attrVal2,',')),IF(isnull(items.attrVal3),'',CONCAT(items.attrVal3,',')),IF(isnull(items.attrVal4),'',CONCAT(items.attrVal4,',')),IF(isnull(items.attrVal5),'',CONCAT(items.attrVal5,',')),IF(isnull(items.attrVal6),'',CONCAT(items.attrVal6,',')),IF(isnull(items.attrVal7),'',CONCAT(items.attrVal7,',')),IF(isnull(items.attrVal8),'',CONCAT(items.attrVal8,',')),')') , ',)', 1) ,')')else '' end  pz,"
				+ "case when items.id is not null then items.price else a.price_new end price_new, "
				+ "case when items.id is not null then items.stocks else a.count end count, "
				+ "0 as out_count, "
				+ "IFNULL(items.id,0) product_items_id  "
				+ "from tbl_product a  "
				+ "left join tbl_product_skus items on items.product_id = a.id and items.attrVal1 is not null"
				+ " where a.state_tulingbuy = 1 and is_red = 1 and is_pc = 1 order by sortid desc limit 0,"
				+ (count == null || count < 0 ? 4 : count);
		List<Product> list = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<Product>(Product.class));
		return list;
	}

	public List<Product> queryProductRedList1(Integer count) {
		String sql = "select a.id,a.name,a.sub_title,a.img_url,a.is_yj,a.is_cx,a.is_ms,"
				+ SkuSqlUtil.makeSkuSql(8, "items", "pz")+","
				+ "case when items.id is not null then items.price else a.price_new end price_new, "
				+ "case when items.id is not null then items.stocks else a.count end count, "
				+ "0 as out_count, "
				+ "IFNULL(items.id,0) product_items_id  "
				+ "from tbl_product a  "
				+ "left join tbl_product_skus items on items.product_id = a.id and items.attrVal1 is not null"
				+ " where a.state_tulingbuy = 1 and is_red = 1 and is_pc = 1 order by sortid desc limit 0,"
				+ (count == null || count < 0 ? 4 : count);
		List<Product> list = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<Product>(Product.class));
		return list;
	}

	public List<SysStringMap> QueryPublicStringMap() {
		String sql = "SELECT * FROM sys_stringmap where ObjectTypeName='Product_Items'";
		List<SysStringMap> list = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<SysStringMap>(SysStringMap.class)); 
		return list;
	}

	public List<ProductItems> QueryProductItemsList(int product_id) { 

		String sql = "select " + "a.id as product_items_id," + "a.product_id,"
				+ "a.attrName1,a.attrVal1," + "a.attrName2,a.attrVal2,"
				+ "a.attrName3,a.attrVal3," + "a.attrName4,a.attrVal4,"
				+ "a.attrName5,a.attrVal5," + "a.attrName6,a.attrVal6,"
				+ "a.attrName7,a.attrVal7," + "a.attrName8,a.attrVal8,"
				+ "a.stocks," + "a.price as product_costprice,"
				+ "a.originPrice as product_saleprice,"
				+ "a.platformPrice as product_marketprice "
				+ "from tbl_product_skus a where product_id='"
				+ Integer.toString(product_id) + "'";

		List<ProductItems> list = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<ProductItems>(ProductItems.class));
		return list;
	}

	/**
	 * 查询pc商品列表 
	 * 
	 * @param type
	 *            类型ID
	 * @param begin_index
	 * @param page_size
	 * @return
	 */
	public List<Product> queryProductList(String type,int brand,int zxsyh,int begin_index,int page_size,String orderby,String where) {
		List<Object> paramlist = new ArrayList<Object>();
		String sql = "select a.id,a.name,a.sub_title,a.is_yj,a.is_cx,a.is_ms," +
				"(select a.id) as out_count1," +
				SkuSqlUtil.makeSkuSql(8,"tps","pz") + ','+
				"a.img_url as img_url,"+
				"case when tps.id is not null then tps.platformPrice else a.price_new end price_new,"+
				"case when tps.id is not null then tps.stocks else a.count end count,"+
				"0 as out_count,"+ 
				"ifnull(tps.id,0) as product_items_id "+ 
				"from tbl_product a "+ 
				"left join tbl_product_skus tps on tps.product_id=a.id "+ 
				"where a.state_tulingbuy = 1  and a.is_pc = 1 and a.special_code = 0 ";
		if(type!="0"){
			sql += " and a.type_ids like ? ";
			paramlist.add("%,"+type+",%");
		}
		if(brand!=0){
			sql += " and a.brand_id = ? ";
			paramlist.add(brand);
		}
		if(!where.equals("")){
			sql += " and (a.name like ? or a.sub_title like ? ) ";
			paramlist.add("%"+where+"%");
			paramlist.add("%"+where+"%");
		}
		if(zxsyh!=0){
			sql += " and a.count>0 ";
		}
		sql += " order by ";
		if(orderby.equals("")){
			sql += "a.sortid desc,a.id desc ";
		}else{
			sql += orderby;
		}
		sql += " limit ?,?";
		paramlist.add(begin_index);
		paramlist.add(page_size);
		Object[] params = paramlist.toArray();
		List<Product> list = jdbcTemplate.query(sql,params,
				new BeanPropertyRowMapper<Product>(Product.class));
		return list;
	}
	
	/**
	 * 查询pc商品数量
	 * @param type 类型ID
	 * @param brand  品牌ID
	 * @param zxsyh 只显示有货  1
	 * @return
	 */
	public int queryProductcount(String type,int brand,int zxsyh,String where,String size,String color,String vision) {
		List<Object> paramlist = new ArrayList<Object>();
		String sql = "select count(1) from tbl_product a LEFT JOIN tbl_product_skus b on  a.id=b.product_id where a.state_tulingbuy = 1  and a.is_pc = 1 and a.special_code = 0 ";
		if(type!="0"){
			sql += " and a.type_ids like ? ";
			paramlist.add("%,"+type+",%"); 
		}

		if(brand!=0){
			sql += " and a.brand_id = ? ";
			paramlist.add(brand);
		}
		if(!where.equals("")){
			sql += " and (a.name like ? or a.sub_title like ? ) ";
			paramlist.add("%"+where+"%");
			paramlist.add("%"+where+"%");
		}
		if(zxsyh!=0){
			sql += " and a.count>0 ";
		}
		
		Object[] params = paramlist.toArray();
		int total_count = jdbcTemplate.queryForObject(sql, params, Integer.class);
		return total_count;
	}

	/**
	 * 更具商品ID集合查询pc商品信息-ajax
	 * 
	 * @param ids
	 *            商品ID集合
	 * @return
	 */
	public List<Product> validateProductList(String ids, String subids,
			int owning_company) {
		String Where = "";
		if (subids != "") {
			Where += " and items.id in (" + subids + ")";
		}
		String sql = "select a.id,a.special_code,"
				+ " case when s.product_price is not null then s.product_price  when items.id is not null then items.platformPrice else a.price_new end price_new,"
				+ " a.price_new price_old ,"
				+ " case when items.id is not null then items.stocks else a.count end count,"
				+ " 0 as out_count,"
				+ " IFNULL(items.id,0)product_items_id "
				+ " from tbl_product a "
				+ " LEFT JOIN tbl_special_products s on s.product_id = a.id and s.owning_company = ? "
				+ " LEFT JOIN tbl_product_skus items on items.product_id=a.id "
				+ Where + " where a.state_tulingbuy = 1  and a.is_pc = 1 and "
				+ " a.id in (" + ids + ")";

		List<Product> list = jdbcTemplate.query(sql,
				new Object[] { owning_company },
				new BeanPropertyRowMapper<Product>(Product.class));
		return list;
	}

	/**
	 * 更具商品ID集合查询pc商品信息-后台提交验证
	 * 
	 * @param ids
	 *            商品ID集合
	 * @return
	 */
	public List<Product> validateProductList_do(String ids,
			Integer owning_company) {

		String sql = "select a.id,a.type_ids,a.brand_id,a.xh,a.special_code,"
				+ SkuSqlUtil.makeSkuSql(8, "items", "pz")+","
				+ " a.name,a.img_url,"
				+ " case when items.id is not null then items.platformPrice when s.id is not null then s.product_price  else a.price_new end price_new,"
				+ " case when items.id is not null then items.stocks else a.count end count,"
				+ " 0 as out_count,"
				+ " IFNULL(items.id,0)product_items_id "
				+ " from tbl_product a "
				+ " LEFT JOIN tbl_product_skus items on items.product_id=a.id "
				+ " LEFT JOIN tbl_special_products s on s.product_id=a.id and s.owning_company = "
				+ owning_company
				+ " where (a.state_tulingbuy = 1 or a.is_yun=1)  and a.is_pc = 1 and "
				+ " a.id in (" + ids + ") order by instr('" + ids + "',a.id)";

		List<Product> list = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<Product>(Product.class));
		return list;
	}

	/**
	 * 查询商品详情
	 * 
	 * @param id
	 *            商品ID
	 * @return
	 */
	public Product queryProduct(int id) {
		Object[] params = new Object[] { id };
		String sql = "select * from tbl_product where id = ? and state_tulingbuy = 1  and is_pc = 1 ";
		List<Product> list = jdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<Product>(Product.class));
		if (null != list && !list	.isEmpty() && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 根据公司id和产品id 查询特供价格
	 * 
	 * @param owning_company
	 * @param product_id
	 * @return
	 */
	public Double getSpecialPrice(int owning_company, int product_id) {
		StringBuffer sql = new StringBuffer(
				"select product_price from tbl_special_products where owning_company = ? and product_id = ?");
		return jdbcTemplate.queryForObject(sql.toString(), new Object[] {
				owning_company, product_id }, Double.class);

	}

	/**
	 * 更新商品库存-及产品的销售记录
	 * 
	 * @param id
	 *            商品ID
	 * @return
	 */
	public int updateProductCount(int product_id, int count) {
		try {
			Object[] params = new Object[] { product_id, count, new Date(), 0 };
			String sql = "insert into tbl_product_count(product_id,count,create_time,state) values(?,?,?,?)";
			int count_ = jdbcTemplate.update(sql, params);
			return count_;
		} catch (Exception ex) {
			System.err.println("---------------------------------------------");
			System.err.println(ex.getMessage());
			System.err.println("---------------------------------------------");
			throw new RuntimeException("更新库存出现错误！");
		}
	}

	/**
	 * 批量更新产品的销售记录
	 */
	public void updateProductCounts(final List<Map<String, Object>> list) {
		String sql = "insert into tbl_product_count(product_id,product_items_id,count,create_time,state) values(?,?,?,now(),?)";
		try {
			BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {
				public void setValues(PreparedStatement ps, int index)
						throws SQLException {
					Map<String, Object> product_map = list.get(index);
					ps.setInt(1, Integer.parseInt(product_map.get("product_id")
							.toString()));
					ps.setInt(2, Integer.parseInt(product_map.get(
							"product_items-id").toString()));
					ps.setInt(3, Integer.parseInt(product_map.get(
							"product_count").toString()));
					ps.setInt(4, 0);
				}

				public int getBatchSize() {
					return list.size();
				}
			};
			jdbcTemplate.batchUpdate(sql, setter);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateSingleProductStock(String symbol, int productId,
			int productItemId) {
		String sql = "";
		Object[] params = null;
		// 更新主表库存
		if (productId > 0 && productItemId == 0) {
			sql = "update tbl_product set count=count" + symbol
					+ " 1 where id=?";
			params = new Object[] { productId };

		}

		if (productId != 0 && productItemId != 0) {

			sql = "update tbl_product_items set stocks=stocks" + symbol
					+ " 1,modifiedOn=now() where product_items_id=?";
			params = new Object[] { productItemId };
		}
		jdbcTemplate.update(sql, params);
	}

	/**
	 * 批量更新商品库存
	 */
	public void updateProductStocks(final String symbol,
			final List<Map<String, Object>> list) {

		for (int i = 0; i < list.size(); i++) {
			String sql = "";
			Object[] params = null;
			Map<String, Object> product_map = list.get(i);
			if (Integer
					.parseInt(product_map.get("product_items-id").toString()) > 0) {
				sql = "update tbl_product_items set stocks=stocks" + symbol
						+ " ?,modifiedOn=now() where product_items_id=?";
				params = new Object[] {
						product_map.get("product_count").toString(),
						product_map.get("product_items-id").toString() };

			} else {
				sql = "update tbl_product set count=count" + symbol
						+ " ? where id=?";
				params = new Object[] {
						product_map.get("product_count").toString(),
						product_map.get("product_id").toString() };

			}
			jdbcTemplate.update(sql, params);

		}

	}

	/**
	 * 查询产品的销出数量
	 * 
	 * @return
	 */
	public int queryProductCount(int product_id) {
		Object[] params = new Object[] { product_id, 0 };
		String sql = "select sum(count) out_count from tbl_product_count where product_id = ? and state = ?";
		Object obj = jdbcTemplate.queryForObject(sql, Object.class, params);
		if (obj != null) {
			int out_count = Integer.parseInt(obj.toString());
			return out_count;
		}
		return 0;
	}

	/**
	 * 更具商品ID查询商品图片相册
	 * 
	 * @param id
	 *            商品ID集合
	 * @return
	 */
	public List<ProductImage> queryProductImagesList(int product_id,
			int product_item_id) {
		Object[] params = new Object[] { product_id, product_item_id };
		String sql = "select * from tbl_product_image where product_id = ? and product_items_id = ? order by id asc";
		List<ProductImage> list = jdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<ProductImage>(ProductImage.class));
		return list;
	}

	/**
	 * 查询特供商品列表
	 * 
	 * @param type
	 *            类型ID
	 * @param begin_index
	 * @param page_size
	 * @return
	 */
	public List<Product> querySpecialProductList(int owning_company,
			String type, int brand, int zxsyh, int begin_index, int page_size,
			String orderby, String where) {
		List<Object> paramlist = new ArrayList<Object>();
		String sql = " select id,`name`,`sub_title`,`is_yj`,`is_cx`,`is_ms`,`count`,`img_url`, "
				+ " (select product_price from tbl_special_products where product_id = a.id )	price_new, "
				+ " (select ifnull(sum(c.count),0) out_count from tbl_product_count c where c.product_id = a.id and c.state = 0) as out_count1 "
				+ " from tbl_product a  where a.state_tulingbuy = 1  and a.is_pc = 1  and a.id in (select product_id from tbl_special_products where owning_company =  ? ) ";
		paramlist.add(owning_company);
		if (type != "0") {
			sql += " and type_ids like ? ";
			paramlist.add("%," + type + ",%");
		}
		if (brand != 0) {
			sql += " and brand_id = ? ";
			paramlist.add(brand);
		}
		if (!where.equals("")) {
			sql += " and (name like ? or sub_title like ? ) ";
			paramlist.add("%" + where + "%");
			paramlist.add("%" + where + "%");
		}
		if (zxsyh != 0) {
			sql += " and count>0 ";
		}
		sql += " order by ";
		if (orderby.equals("")) {
			sql += "sortid desc,id desc ";
		} else {
			sql += orderby;
		}
		sql += " limit ?,?";
		paramlist.add(begin_index);
		paramlist.add(page_size);
		Object[] params = paramlist.toArray();
		List<Product> list = jdbcTemplate.query(sql, params,
				new BeanPropertyRowMapper<Product>(Product.class));
		return list;
	}

	/**
	 * 查询特供商品数量
	 * 
	 * @param type
	 *            类型ID
	 * @param brand
	 *            品牌ID
	 * @param zxsyh
	 *            只显示有货 1
	 * @return
	 */
	public int querySpecialProductcount(int owning_company, String type,
			int brand, int zxsyh, String where) {
		List<Object> paramlist = new ArrayList<Object>();
		String sql = " select count(1) from tbl_product  where id  in  (SELECT product_id from tbl_special_products where owning_company = ? )  and  state_tulingbuy = 1 ";
		paramlist.add(owning_company);
		if (type != "0") {
			sql += " and type_ids like ? ";
			paramlist.add("%," + type + ",%");
		}
		if (brand != 0) {
			sql += " and brand_id = ? ";
			paramlist.add(brand);
		}
		if (!where.equals("")) {
			sql += " and (`name` like ? or sub_title like ? ) ";
			paramlist.add("%" + where + "%");
			paramlist.add("%" + where + "%");
		}
		if (zxsyh != 0) {
			sql += " and count>0 ";
		}

		Object[] params = paramlist.toArray();
		int total_count = jdbcTemplate.queryForObject(sql, params,
				Integer.class);
		return total_count;
	}

}