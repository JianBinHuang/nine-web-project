package com.hjb.util;

import com.hjb.util.DBUtils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 提供增删改查的操作
 */

public class CommonUtils {

    private static Connection connection = null;
    private static PreparedStatement pst = null;
    private static ResultSet resultSet = null;

    /**
     * 公共的增删改方法
     *
     * @return
     */
    public static int commonUpdate(String sql, Object... params) {
        try {
            //得到连接对象
            connection = DBUtils.getConnection();

            //得到预编译对象
            pst = connection.prepareStatement(sql);

            //给sql中的占位符赋值
            for (int i = 0; i < params.length; i++) {
                pst.setObject(i + 1, params[i]);
            }

            //执行更新操作
            return pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeAll(pst, connection);
        }
        return 0;
    }


    /**
     *     //查询
     *     //查询集合
     *     //注意：表中的字段名称和类中的属性名称不一致   select * from user where id = ?
     *     //第一个参数：要执行的sql语句
     *     //第二个参数：要查询的数据的类型   User.Class
     *     //第三个参数：给sql的点位符赋的值
     * @param sql
     * @param clazz
     * @param params
     * @param <T>
     * @return
     */
    public static <T> List<T> commonQuery(String sql, Class<T> clazz, Object... params) {
        List<T> list = new ArrayList<>();
        try {
            //得到连接对象
            connection = DBUtils.getConnection();

            //得到预编译对象
            pst = connection.prepareStatement(sql);

            //给sql中的占位符赋值
            for (int i = 0; i < params.length; i++) {
                pst.setObject(i + 1, params[i]);
            }

            //执行查询操作
            resultSet = pst.executeQuery();
            //得到Class类型中的属性
            Field[] fields = clazz.getDeclaredFields();
            //遍历查询到的结果集合
            while (resultSet.next()) {
                Object value = null;
                //每循环一次可以得到一条查询到的数据
                //通过Class创建一个对象
                T instance = clazz.newInstance();
                //遍历属性数组
                for (Field field : fields) {
                    //得到属性的名称//uid
                    String fieldName = field.getName();
                    try {
                        //从结果集中通过属性名称得到对应字段的值//u_id
                        value = resultSet.getObject(fieldName);
                    } catch (Exception e) {
                        //读取文件，配置相应关系
                        Properties properties = new Properties();
                        properties.load(CommonUtils.class.getClassLoader().getResourceAsStream("mapping.properties"));
                        //得到表字段名称
                        String columnName = properties.getProperty(fieldName);
                        //从结果集中取到对应字段的值
                        value = resultSet.getObject(columnName);
                    }

                    //设置访问权限
                    field.setAccessible(true);
                    //给对象的属性赋值
                    field.set(instance, value);
                }
                //把所有的对象放到集合中
                list.add(instance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeAll(pst, connection);
        }
        return list;
    }


    //查询
    //查询集合
    //注意：表中的字段名称和类中的属性名称一致   select * from user where id = ?
    //第一个参数：要执行的sql语句
    //第二个参数：要查询的数据的类型   User.Class
    //第三个参数：给sql的点位符赋的值
    public static <T> List<T> commonQuery2(String sql, Class<T> clazz, Object... params) {
        List<T> list = new ArrayList<>();
        try {
            //得到连接对象
            connection = DBUtils.getConnection();

            //得到预编译对象
            pst = connection.prepareStatement(sql);

            //给sql中的占位符赋值
            for (int i = 0; i < params.length; i++) {
                pst.setObject(i + 1, params[i]);
            }

            //执行查询操作
            resultSet = pst.executeQuery();
            //得到Class类型中的属性
            Field[] fields = clazz.getDeclaredFields();
            //遍历查询到的结果集合
            while (resultSet.next()) {
                Object value = null;
                //每循环一次可以得到一条查询到的数据
                //通过Class创建一个对象
                T instance = clazz.newInstance();
                //遍历属性数组
                for (Field field : fields) {
                    //得到属性的名称
                    String fieldName = field.getName(); //uid
                    //从结果集中通过属性名称得到对应字段的值
                    value = resultSet.getObject(fieldName);//uid
                    //设置访问权限
                    field.setAccessible(true);
                    //给对象的属性赋值
                    field.set(instance, value);
                }
                //把所有的对象放到集合中
                list.add(instance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeAll(pst, connection);
        }
        return list;
    }


    //查询
    //查询单个对象
    public static <T> T querySingleInstance(String sql, Class<T> clazz, Object... params) {
        try {
            //得到连接对象
            connection = DBUtils.getConnection();

            //得到预编译对象
            pst = connection.prepareStatement(sql);

            //给sql中的占位符赋值
            for (int i = 0; i < params.length; i++) {
                pst.setObject(i + 1, params[i]);
            }

            //执行查询操作
            resultSet = pst.executeQuery();
            //得到Class类型中的属性
            Field[] fields = clazz.getDeclaredFields();
            //遍历查询到的结果集合
            if (resultSet.next()) {
                Object value = null;
                //每循环一次可以得到一条查询到的数据
                //通过Class创建一个对象
                T instance = clazz.newInstance();
                //遍历属性数组
                for (Field field : fields) {
                    //得到属性的名称//uid
                    String fieldName = field.getName();
                    try {
                        //从结果集中通过属性名称得到对应字段的值//u_id
                        value = resultSet.getObject(fieldName);
                    } catch (Exception e) {
                        //读取文件，配置相应关系
                        Properties properties = new Properties();
                        properties.load(CommonUtils.class.getClassLoader().getResourceAsStream("mapping.properties"));
                        //得到表字段名称
                        String columnName = properties.getProperty(fieldName);
                        //从结果集中取到对应字段的值
                        value = resultSet.getObject(columnName);
                    }

                    //设置访问权限
                    field.setAccessible(true);
                    //给对象的属性赋值
                    field.set(instance, value);
                }
                //返回实例化的对象
                return instance;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeAll(pst, connection);
        }
        return null;
    }


    //查询总条数
    public static int count(String sql, Object... params) {
        try {
            //得到连接对象
            connection = DBUtils.getConnection();

            //得到预编译对象
            pst = connection.prepareStatement(sql);

            //给sql中的占位符赋值
            for (int i = 0; i < params.length; i++) {
                pst.setObject(i + 1, params[i]);
            }

            //执行查询操作
            resultSet = pst.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeAll(pst, connection);
        }
        return 0;
    }
}
