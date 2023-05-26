package org.example.dao.impl;

import cn.hutool.core.date.DateUtil;
import org.example.dao.UserDao;
import org.example.domain.Favourites;
import org.example.domain.Review;
import org.example.domain.User;
import org.example.util.JdbcUtil;
import org.example.domain.buy;
import org.example.util.PstmtUtil;
import org.example.util.showError;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author 19086
 * @version 1.0
 * Create by 2023/5/16 8:46
 */

public class UserDaoImpl implements UserDao {

    @Override
    public int insert(User user) throws SQLException {
        return 0;
    }

    @Override
    //新用户注册时将信息导入数据库
    public boolean insert(int id, String name, String password) throws SQLException {
        String sql = "insert into user(id, balance, identity, username, passworld) values ("+id+", 0, 1, '"+name+"', '"+password+"')";
        PstmtUtil pstmtUtil = new PstmtUtil();
        PreparedStatement pre = pstmtUtil.PstmtUtil(sql);
        pre.executeUpdate(sql);
        pstmtUtil.closeConnection();
        return false;
    }

    @Override
    public boolean insertReview(String text, int userid, int goodid) throws Exception {
        String today = DateUtil.today();
        String sql = "insert into review values ("+userid+","+goodid+",'"+text+"','"+today+"')";
        PstmtUtil pstmtUtil = new PstmtUtil();
        PreparedStatement pre = pstmtUtil.PstmtUtil(sql);
        pre.executeUpdate();
        pstmtUtil.closeConnection();
        return true;
    }

    @Override
    public boolean insertFavourites(int goodid, int number, int userid) throws Exception {
        boolean flag = false;
        boolean flag2 = false;
        String sql1 = "insert into favourites values ("+goodid+","+number+","+userid+")";
        String sql2 = "select * from goods where id = "+goodid+"";
        String sql4 = "select * from favourites where uesrid = "+userid+"";
        PstmtUtil pstmtUtil = new PstmtUtil();
        PreparedStatement pre2 = pstmtUtil.PstmtUtil(sql2);
        ResultSet resultSet2 = pre2.executeQuery();
        if (resultSet2.next()){
            if (resultSet2.getInt(6) >= number){
                int num = resultSet2.getInt(6) - number;
                PreparedStatement pre4 = pstmtUtil.PstmtUtil(sql4);
                ResultSet resultSet4 = pre4.executeQuery();
                if (resultSet4.next()){
                    if (resultSet4.getInt(1) == goodid){
                        flag2 = true;
                        String sql5 = "select * from favourites where goodid = "+goodid+" and uesrid = "+userid+"";
                        PreparedStatement pre5 = pstmtUtil.PstmtUtil(sql5);
                        ResultSet resultSet5 = pre5.executeQuery();
                        if (resultSet5.next()){
                            int oldNum = resultSet5.getInt(2);
                            int newNum = oldNum + number;
                            String sql6 = "update favourites set buynumber = "+newNum+" where uesrid = "+userid+" and goodid = "+goodid+"";
                            PreparedStatement pre6 = pstmtUtil.PstmtUtil(sql6);
                            pre6.executeUpdate();
                        }
                    }
                }
                if (!flag2){
                    PreparedStatement pre1 = pstmtUtil.PstmtUtil(sql1);
                    pre1.executeUpdate();
                }
                String sql3 = "update goods set store = "+num+" where id = "+goodid+"";
                PreparedStatement pre3 = pstmtUtil.PstmtUtil(sql3);
                pre3.executeUpdate();
                flag = true;
            }else{
                System.err.println("商品数目不足");
            }
        }
        pstmtUtil.closeConnection();
        return flag;
    }

    @Override
    //充值余额
    public boolean changeBalance(String username, double money) throws SQLException {
        double balance = checkBalance(username) + money;
        String sql = "update user set balance = "+balance+" where username = '"+username+"'";
        PstmtUtil pstmtUtil = new PstmtUtil();
        PreparedStatement pre = pstmtUtil.PstmtUtil(sql);
        pre.executeUpdate(sql);
        System.out.println("充值成功");
        pstmtUtil.closeConnection();
        return false;
    }

    @Override
    //开通Vip扣除余额
    public boolean changeBalance2(String username) throws SQLException {
        double money = checkBalance(username);
        double balance = money - 648;
        String sql = "update user set balance = "+balance+" where username = '"+username+"'";
        PstmtUtil pstmtUtil = new PstmtUtil();
        PreparedStatement pre = pstmtUtil.PstmtUtil(sql);
        pre.executeUpdate(sql);
        pstmtUtil.closeConnection();
        return false;
    }

    @Override
    //开通Vip
    public boolean changeVip(String username) throws SQLException {
        double money = checkBalance(username);
        boolean bl = true;
        if (money >= 648) {
        String sql = "update user set identity = 2 where username = '"+username+"'";
        PstmtUtil pstmtUtil = new PstmtUtil();
        PreparedStatement pre = pstmtUtil.PstmtUtil(sql);
        pre.executeUpdate(sql);
        pstmtUtil.closeConnection();
        changeBalance2(username);
        } else {
            bl = false;
        }
        return bl;
    }

    @Override
    public int check(int id, String password) throws Exception {
        return 0;
    }

    @Override
    public boolean changeBuy(double[] buy,int goodid,String name,int num) throws Exception{
        boolean flag = true;
        String now = DateUtil.now();
        String sql1 = "update goods set store = "+(int)buy[0]+" where id = "+goodid+"";
        String sql2 = "update user set balance = "+buy[1]+" where username = '"+name+"'";
        String sql3 = "select * from user where username = '"+name+"'";
        PstmtUtil pstmtUtil = new PstmtUtil();
        PreparedStatement pre1 = pstmtUtil.PstmtUtil(sql1);
        PreparedStatement pre2 = pstmtUtil.PstmtUtil(sql2);
        PreparedStatement pre3 = pstmtUtil.PstmtUtil(sql3);
        pre1.executeUpdate();
        pre2.executeUpdate();
        ResultSet resultSet3 = pre3.executeQuery();
        if (resultSet3.next()){
            int userid = resultSet3.getInt(1);
            String sql4 = "insert into buy values ("+goodid+","+num+",'"+now+"',"+userid+","+buy[2]+")";
            PreparedStatement pre4 = pstmtUtil.PstmtUtil(sql4);
            pre4.executeUpdate();
        }
        pstmtUtil.closeConnection();
        return flag;
    }

    @Override
    public boolean changeName(String oldName,String newName) throws Exception{
        boolean flag = false;
        String sql = "update user set username = ? where username = ?";
        PstmtUtil pstmtUtil = new PstmtUtil();
        PreparedStatement pre = pstmtUtil.PstmtUtil(sql);
        pre.setString(1,newName);
        pre.setString(2,oldName);
        pre.executeUpdate();
        flag = true;
        pstmtUtil.closeConnection();
        return flag;
    }
    @Override
    public double changeFavourites(int userid) throws Exception {
        double sum = 0;
        boolean flag = false;
        boolean flag2 = false;
        String sql1 = "select * from favourites where uesrid = "+userid+"";
        String sql7 = "select * from user where id = "+userid+"";
        PstmtUtil pstmtUtil = new PstmtUtil();
        PreparedStatement pre1 = pstmtUtil.PstmtUtil(sql1);
        PreparedStatement pre7 = pstmtUtil.PstmtUtil(sql7);
        ResultSet resultSet1 = pre1.executeQuery();
        ResultSet resultSet7 = pre7.executeQuery();
        if (resultSet7.next()){
            if (resultSet7.getInt(3) == 2){
                flag2 = true;
            }
        }
        String sql6 = "select count(*) as total from goods";
        PreparedStatement pre6 = pstmtUtil.PstmtUtil(sql6);
        ResultSet resultSet6 = pre6.executeQuery();
        int row = 0;
        if (resultSet6.next()){
            row = resultSet6.getInt("total");
        }
        String[] sql8 = new String[row];
        PreparedStatement[] pre8 = new PreparedStatement[row];
        for (int i = 0; i < row;i++){
            if (resultSet1.next()){
                String now = DateUtil.now();
                double discount = 1;
                int goodId = resultSet1.getInt(1);
                int buyNumber = resultSet1.getInt(2);
                String sql2 = "select * from goods where id = "+goodId+"";
                PreparedStatement pre2 = pstmtUtil.PstmtUtil(sql2);
                ResultSet resultSet2 = pre2.executeQuery();
                if (resultSet2.next()){
                    if (flag2){
                        discount = resultSet2.getDouble(4);
                    }
                    double price = resultSet2.getDouble(3);
                    double buy = buyNumber * price * discount;
                    sum += buy;
                    sql8[i] = "insert into buy values ("+goodId+","+buyNumber+",'"+now+"',"+userid+","+buy+");";
                    pre8[i] = pstmtUtil.PstmtUtil(sql8[i]);
                }
            }
        }
        String sql3 = "select * from user where id = "+userid+"";
        PreparedStatement pre3 = pstmtUtil.PstmtUtil(sql3);
        ResultSet resultSet3 = pre3.executeQuery();
        if (resultSet3.next()){
            double oldBalance = resultSet3.getDouble(2);
            double newBalance = oldBalance - sum;
            if (newBalance < 0){
                showError showError = new showError();
                showError.showError("出错","余额不足，请充值");
                System.err.println("余额不足");
            }else {
                for (int i = 0; i < row;i++){
                    if (sql8[i] == null){
                        break;
                    }
                    pre8[i].executeUpdate();
                }
                flag = true;
                String sql4 = "update user set balance = "+newBalance+" where id = "+userid+"";
                PreparedStatement pre4 = pstmtUtil.PstmtUtil(sql4);
                pre4.executeUpdate();
                String sql5 = "delete from favourites where uesrid = "+userid+"";
                PreparedStatement pre5 = pstmtUtil.PstmtUtil(sql5);
                pre5.executeUpdate();
            }
        }
        pstmtUtil.closeConnection();
        return sum;
    }
    @Override
    public int check(String name, String password) throws Exception {
        int num;
        String sql = "select * from user where username = ? and passworld = ?";
        PstmtUtil pstmtUtil = new PstmtUtil();
        PreparedStatement pre = pstmtUtil.PstmtUtil(sql);
        pre.setString(1,name);
        pre.setString(2,password);
        ResultSet resultSet = pre.executeQuery();
        if (resultSet.next()){
            num = resultSet.getInt(3);
            pstmtUtil.closeConnection();
            return num;
        }else {
            pstmtUtil.closeConnection();
            return 9;
        }
    }


    @Override
    //用户注册查询用户名是否存在
    public boolean check(String name) throws Exception {
        String sql = "select username from user where username = ?";
        boolean bl = false;
        PstmtUtil pstmtUtil = new PstmtUtil();
        PreparedStatement pre = pstmtUtil.PstmtUtil(sql);
        pre.setString(1, name);
        ResultSet resultSet = pre.executeQuery();
        if (resultSet.next()) {
            bl = true;
        }
        pstmtUtil.closeConnection();
        return bl;
    }

    @Override
    public double[] checkBuy(int goodid,String name,int num) throws Exception {
        double[] buy = new double[3];
        String sql1 = "select * from user where username = ?";
        double balance = 0;
        double sum = 0;
        String sql2 = "select * from goods where id = ?";
        double price;
        int store;
        double discount;
        PstmtUtil pstmtUtil = new PstmtUtil();
        PreparedStatement pre1 = pstmtUtil.PstmtUtil(sql1);
        PreparedStatement pre2 = pstmtUtil.PstmtUtil(sql2);
        pre1.setString(1,name);
        pre2.setInt(1,goodid);
        ResultSet resultSet1 = pre1.executeQuery();
        ResultSet resultSet2 = pre2.executeQuery();
        if (resultSet1.next() && resultSet2.next()){
            balance = resultSet1.getDouble(2);//获取用户余额
            price = resultSet2.getDouble(3);//获取商品价格
            store = resultSet2.getInt(6);//获取商品数目
            buy[0] = store - num;//存储商品剩余数目
            discount = resultSet2.getFloat(4);//获取商品折扣
            if (resultSet1.getInt(3) == 2){
                sum = price * discount * num;//会员计算总价
                buy[2] = sum;//存储总价
            }else if (resultSet1.getInt(3) == 1){
                sum = price * num;//普通计算总价
                buy[2] = sum;//存储总价
            }
            buy[1] = balance - sum;//存储余额
            pstmtUtil.closeConnection();
        }
        return buy;
    }




    @Override
    //获取余额
    public double checkBalance(String username) throws SQLException {
        double money = 0;
        String sql = "select * from user where username = ?";
        PstmtUtil pstmtUtil = new PstmtUtil();
        PreparedStatement pre = pstmtUtil.PstmtUtil(sql);
        pre.setString(1, username);
        ResultSet resultSet = pre.executeQuery();
        if (resultSet.next()) {
            money = resultSet.getDouble(2);
        }
        pstmtUtil.closeConnection();
        return money;
    }

    @Override
    public int getid(String user) {
        String sql = "select id from user where username = ?";
        PstmtUtil pst = new PstmtUtil();
        int id = 0;
        PreparedStatement pre = pst.PstmtUtil(sql);
        try {
            pre.setString(1,user);
            ResultSet rs = pre.executeQuery();
            rs.next();
            id = rs.getInt("id");
        } catch (SQLException e) {
            System.out.println("用户名不存在");
            throw new RuntimeException(e);
        }
        pst.closeConnection();
        return id;
    }

    @Override
    public Double getprice(int goodid) {
        String sql = "select price from goods where id = ?";
        PstmtUtil pst = new PstmtUtil();
        double price = 0;
        PreparedStatement pre = pst.PstmtUtil(sql);
        try {
            pre.setInt(1,goodid);
            ResultSet rs = pre.executeQuery();
            rs.next();
            price = rs.getDouble("price");
        } catch (SQLException e) {
            System.out.println("用户名不存在");
            throw new RuntimeException(e);
        }
        pst.closeConnection();
        return price;
    }

    @Override
    public String getuser(int id) {
        String sql = "select username from user where id = ?";
        PstmtUtil pst = new PstmtUtil();
        String user;
        PreparedStatement pre = pst.PstmtUtil(sql);
        try {
            pre.setInt(1,id);
            ResultSet rs = pre.executeQuery();
            rs.next();
            user = rs.getString("username");
        } catch (SQLException e) {
            System.out.println("用户名不存在");
            throw new RuntimeException(e);
        }
        pst.closeConnection();
        return user;
    }

    @Override
    public String getgoodName(int goodid) {
        String sql = "select name from goods where id = ?";
        PstmtUtil pst = new PstmtUtil();
        String goodname;
        PreparedStatement pre = pst.PstmtUtil(sql);
        try {
            pre.setInt(1,goodid);
            ResultSet rs = pre.executeQuery();
            rs.next();
            goodname = rs.getString("name");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        pst.closeConnection();
        return goodname;
    }

    @Override
    public int getgoodid(String name) {
        String sql = "select id from goods where name = ?";
        PstmtUtil pst = new PstmtUtil();
        int goodid;
        PreparedStatement pre = pst.PstmtUtil(sql);
        try {
            pre.setString(1,name);
            ResultSet rs = pre.executeQuery();
            rs.next();
            goodid = rs.getInt("id");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        pst.closeConnection();
        return goodid;
    }

    @Override
    public boolean delFavourites(String user, int goodid, int buynumber) throws Exception {
        int userid = getid(user);
        String sql1 = "delete from favourites where uesrid = "+userid+" and goodid = "+goodid+"";
        String sql2 = "select * from goods where id = "+goodid+"";
        PstmtUtil pstmtUtil = new PstmtUtil();
        PreparedStatement pre1 = pstmtUtil.PstmtUtil(sql1);
        PreparedStatement pre2 = pstmtUtil.PstmtUtil(sql2);
        ResultSet resultSet2 = pre2.executeQuery();
        if (resultSet2.next()){
            int oldStore = resultSet2.getInt(6);
            int newStore = oldStore + buynumber;
            String sql3 = "update goods set store = "+newStore+" where id = "+goodid+"";
            PreparedStatement pre3 = pstmtUtil.PstmtUtil(sql3);
            pre3.executeUpdate();
        }
        pre1.executeUpdate();
        pstmtUtil.closeConnection();
        return true;
    }


    @Override
    public ArrayList queryCheckreview() {
        ArrayList<Review> arrayList = new ArrayList<>();
        String sql = "select * from review";
        PstmtUtil pst = new PstmtUtil();
        PreparedStatement pre = pst.PstmtUtil(sql);
        try {
            ResultSet rs = pre.executeQuery();
            while(rs.next()){
                Review re = new Review();
                re.setContent(rs.getString("content"));
                re.setTime(rs.getString("time"));
                re.setUserid(rs.getInt("userid"));
                re.setGoodid(rs.getInt("goodid"));
                arrayList.add(re);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        pst.closeConnection();
        try {
            pre.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return arrayList;
    }

    @Override
    public ArrayList queryBuy() {
        ArrayList<buy> arrayList = new ArrayList<>();
        String sql = "select * from buy";
        PstmtUtil pst = new PstmtUtil();
        PreparedStatement pre = pst.PstmtUtil(sql);
        try {
            ResultSet rs = pre.executeQuery();
            while(rs.next()){
                buy by = new buy();
                by.setTime(rs.getString("time"));
                by.setUserid(rs.getInt("userid"));
                by.setGoodid(rs.getInt("goodid"));
                by.setCount(rs.getInt("count"));
                by.setPay(rs.getDouble("pay"));
                arrayList.add(by);

            }

    }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        pst.closeConnection();
        try {
            pre.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return arrayList;
}

    @Override
    public ArrayList queryFavourites(int userid) {
        ArrayList<Favourites> arrayList = new ArrayList<>();
        String sql = "select * from favourites";
        PstmtUtil pst = new PstmtUtil();
        PreparedStatement pre = pst.PstmtUtil(sql);
        try {
            ResultSet rs = pre.executeQuery();
            while(rs.next()){
                if (rs.getInt("uesrid") == userid){
                    Favourites fa = new Favourites();
                    fa.setBuynumber(rs.getInt("buynumber"));
                    fa.setGoodid(rs.getInt("goodid"));
                    fa.setUesrid(rs.getInt("uesrid"));
                    arrayList.add(fa);}


            }

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        pst.closeConnection();
        try {
            pre.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return arrayList;
    }
}
