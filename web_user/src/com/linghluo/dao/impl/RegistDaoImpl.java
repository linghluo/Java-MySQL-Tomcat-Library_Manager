package com.linghluo.dao.impl;

import com.linghluo.dao.RegistDao;
import com.linghluo.pojo.Info;
import com.linghluo.pojo.Regist;
import com.linghluo.pool.DruidTest;

import java.sql.*;

public class RegistDaoImpl implements RegistDao {

    @Override
    public boolean  insert(Regist regist) {
        String sql = "INSERT INTO regist (registN, registP,nameown) VALUES (?, ?, ?)";

        try (Connection conn = DruidTest.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, regist.getRegistName());
            ps.setString(2, regist.getRegistPassword());
            ps.setString(3, regist.getRegistNameOwn());

            int rowsAffected = ps.executeUpdate();
            ps.close();
            DruidTest.release();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Info selectByDo(Regist login) {
        String sql = "SELECT nameown FROM regist WHERE registN = ? AND registP = ?;";

        Info info = new Info();
        try (Connection conn = DruidTest.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, login.getRegistName());
            ps.setString(2, login.getRegistPassword());
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                // 没有查询到结果，不给登录
                info.setTag(0);
            } else {
                // 如果查询到至少一条记录，检查字段是否为NULL
                String nameown = rs.getString("nameown");
                if (nameown == null) {
                    // 如果查询结果中的email字段为NULL
                    info.setTag(1);
                } else {
                    // 如果查询结果中的email字段有值
                    info.setTag(1);
                }
                rs.close();
                DruidTest.release();
            }
            return info;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

        @Override
    public boolean update(Regist regist) {
        String sql = "UPDATE regist SET registP = ?, nameown = ? WHERE registN = ?";

        try (Connection conn = DruidTest.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, regist.getRegistPassword());
            ps.setString(2, regist.getRegistNameOwn());
            ps.setString(3, regist.getRegistName());

            int rowsAffected = ps.executeUpdate();
            ps.close();
            DruidTest.release();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Regist regist) {
        String sql = "DELETE FROM regist WHERE registN = ? AND registP = ?";

        try (Connection conn = DruidTest.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, regist.getRegistName());
            ps.setString(2, regist.getRegistPassword());

            int rowsAffected = ps.executeUpdate();
            ps.close();
            DruidTest.release();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean stnm(Regist regist) {
        String sql = "UPDATE regist SET nameown = ? WHERE registN = ? AND registP = ?";

        try (Connection conn = DruidTest.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, regist.getRegistNameOwn());
            ps.setString(2, regist.getRegistName());
            ps.setString(3, regist.getRegistPassword());

            int rowsAffected = ps.executeUpdate();
            ps.close();
            DruidTest.release();

            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
