package vn.dev.danghung.dao.jdbc.brand;

import org.springframework.stereotype.Component;
import vn.dev.danghung.dao.jdbc.AbstractDao;
import vn.dev.danghung.entities.Brand;
import vn.dev.danghung.factory.MySQLConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class BrandDaoImpl extends AbstractDao implements BrandDao {

    @Override
    public Brand find(String id) {
        Brand brand = new Brand();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM " +
                "" +tBrand+
                " WHERE id = "+id;
        try {
            conn = MySQLConnectionFactory.getInstance().getMySQLConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
                brand.setName(rs.getString("name"));
            }
            return brand;
        }catch (Exception e){
            eLogger.error("error in brand find dao, {}",e.getMessage());
        }finally {
            releaseConnect(conn,stmt,rs);
        }
        return new Brand();
    }

    @Override
    public List<Brand> findAll() {
        List<Brand> brandList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM "+ tBrand;
        try{
            conn = MySQLConnectionFactory.getInstance().getMySQLConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()){
                Brand brand = new Brand();
                brand.setName(rs.getString("name"));
                brandList.add(brand);
            }
            return brandList;
        }catch (Exception e){
            eLogger.error("error in brand findALl dao, {}",e.getMessage());
        }finally {
            releaseConnect(conn,stmt,rs);
        }
        return new ArrayList<>();
    }

    @Override
    public void create(Brand brand) {

    }

    @Override
    public void update(Brand brand) {

    }
}
