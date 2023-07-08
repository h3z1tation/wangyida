package org.YaoLeGouDB.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.YaoLeGouDB.Dao.BannerDao;
import org.YaoLeGouDB.entity.Banner;
import org.YaoLeGouDB.utils.BaseDao;

public class BannerDaoImpl extends BaseDao implements BannerDao {

	@Override
	public List<Banner> GetAllInfo() {
		String sql = "select * from banner";
		ResultSet rs = super.executeQuery(sql, null);
		List<Banner> list = new ArrayList<Banner>();
		try {
			while(rs.next()){
				Banner b = new Banner();
				b.setBeizhu(rs.getString("beizhu"));
				b.setBgcolor(rs.getString("bgcolor"));
				b.setHref(rs.getString("href"));
				b.setId(rs.getInt("id"));
				b.setImage(rs.getString("image"));
				b.setIstingyong(rs.getInt("istingyong"));
				b.setCreatetime(rs.getString("createtime"));
				list.add(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				CloseAll(getConnection(), rs.getStatement(), rs);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public boolean ByBannerIdUpdateIsTingYong0(int BannerId) {
		String sql = "update Banner set IsTingYong = 0 where id = ?";
		Object[] sz = {BannerId};
		return super.executeUpdate(sql, sz);
	}

	@Override
	public boolean ByBannerIdUpdateIsTingYong1(int BannerId) {
		String sql = "update Banner set IsTingYong = 1 where id = ?";
		Object[] sz = {BannerId};
		return super.executeUpdate(sql, sz);
	}

	@Override
	public boolean AddBannerInfo(Banner banner) {
		String sql = "insert into Banner(Image,BgColor,Href,IsTingYong,BeiZhu,CreateTime) values(?,?,?,'0',?,now())";
		Object[] sz = {banner.getImage(),banner.getBgcolor(),banner.getHref(),banner.getBeizhu()};
		return super.executeUpdate(sql, sz);
	}

	@Override
	public boolean ByBannerIdDelBanner(String BannerId) {
		String sql = "delete from banner where id = ?";
		Object[] sz = {BannerId};
		return super.executeUpdate(sql, sz);
	}

	@Override
	public String ByBannerIdGetImageURL(String BannerId) {
		String sql = "select * from banner where id = ?";
		Object[] sz = {BannerId};
		ResultSet rs = super.executeQuery(sql, sz);
		String ImageURL = null;
		try {
			while(rs.next()){
				ImageURL = rs.getString("image");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				CloseAll(getConnection(), rs.getStatement(), rs);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ImageURL;
	}

}
