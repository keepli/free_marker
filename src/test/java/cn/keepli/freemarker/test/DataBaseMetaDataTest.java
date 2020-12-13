package cn.keepli.freemarker.test;

import org.junit.Before;
import org.junit.Test;

import java.sql.*;

/**
 * mysql元数据获取测试
 */
public class DataBaseMetaDataTest {

    private Connection connection;

    @Before
    public void init() throws Exception {
        String url = "jdbc:mysql://keepli.cn:3306/freemarker?characterEncoding=utf-8&serverTimezone=GMT%2B8";
        String username = "root";
        String password = "root";
        //1.注册数据库驱动
        Class.forName ( "com.mysql.jdbc.Driver" );
        //2.获取连接
        connection = DriverManager.getConnection ( url, username, password );
        //3.获取元数据
        DatabaseMetaData metaData = connection.getMetaData ( );
    }

    @Test
    public void test1() throws Exception {
        //4.获取数据库基本信息
        DatabaseMetaData metaData = connection.getMetaData ( );
        String userName = metaData.getUserName ( );//获取数据库用户名
        System.out.println ( userName );
        connection.close ();
    }

    /**
     * 获取数据库列表（名称）
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {
        DatabaseMetaData metaData = connection.getMetaData ( );
        //获取所有数据列表
        ResultSet rs = metaData.getCatalogs ( );
        while (rs.next ()){
            System.out.println ( rs.getString ( 1 ) );//获取到所有数据库的名称
        }
        rs.close ();
        connection.close ();
    }

    /**
     * 获取指定数据库的所有表信息
     * @throws Exception
     */
    @Test
    public void test3() throws Exception {
        DatabaseMetaData metaData = connection.getMetaData ( );
        //获取指定数据库的所有表信息
        /**
         * 参数说明：
         *      catalog：当前连接的数据库
         *      schemaPattern：在mysql中没有什么意义可以为null，在Oracle中代表着用户名（大写）
         *      tableNamePattern：
         *          1.指定查询的表名
         *          2.为null代表查询所有表
         *      types[]：表示类型（TABLE: 表 VIEW：视图）
         */
        ResultSet rs = metaData.getTables ( null, null, null, new String[]{"TABLE"} );
        while (rs.next ()){
            //System.out.println ( rs );
            System.out.println ( rs.getString ( "TABLE_NAME" ));//获取所有表名
        }
        connection.close ();
    }

    /**
     * 获取指定表的所有字段信息
     * @throws Exception
     */
    @Test
    public void test4() throws Exception {
        DatabaseMetaData metaData = connection.getMetaData ( );
        //获取表中的字段信息
        /**
         * 参数说明：
         *      catalog：当前连接的数据库
         *      schemaPattern：在mysql中没有什么意义可以为null，在Oracle中代表着用户名（大写）
         *      tableNamePattern：指定查询的表名
         *      columnNamePattern：指定列名，为null代表所有
         */
        ResultSet rs = metaData.getColumns ( null, null, "table_user", null );
        while (rs.next ( )){
            //System.out.println ( rs );
            System.out.println ( rs.getString ( "COLUMN_NAME" ) );//获取列名
            System.out.println ( rs.getString ( "REMARKS" ) );//获取列备注信息
        }
        connection.close ();
    }

    /**
     * 获取指定表的所有主键信息
     * @throws Exception
     */
    @Test
    public void test5() throws Exception {
        DatabaseMetaData metaData = connection.getMetaData ( );
        //获取表中的字段信息
        /**
         * 参数说明：
         *      catalog：当前连接的数据库
         *      schemaPattern：在mysql中没有什么意义可以为null，在Oracle中代表着用户名（大写）
         *      table：指定查询的表名
         */
        ResultSet rs = metaData.getPrimaryKeys ( null,null,"table_user" );
        while (rs.next ( )){
            //System.out.println ( rs );
            System.out.println ( rs.getString ( "COLUMN_NAME" ) );//获取主键名
        }
        connection.close ();
    }
}
