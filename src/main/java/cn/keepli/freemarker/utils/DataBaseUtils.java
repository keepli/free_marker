package cn.keepli.freemarker.utils;

import cn.keepli.freemarker.entity.Column;
import cn.keepli.freemarker.entity.DataBase;
import cn.keepli.freemarker.entity.Table;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 元数据封装到数据模型
 */
public class DataBaseUtils {

    //获取到mysql中所有的名称

    /**
     * 测试用的
     */
    public static void main(String[] args) throws Exception {
        DataBase db = new DataBase ( "MYSQL","127.0.0.1","freemarker" );
        db.setUserName ( "root" );
        db.setPassWord ( "root" );
        List<Table> dataInfo = getDataInfo ( db );
        for (Table table : dataInfo) {
            System.out.println ( table );
        }
    }

    /**
     * 获取数据库连接
     */
    public static Connection getConnection(DataBase db) throws Exception {
        Properties props = new Properties ( );
        props.put ( "remarksReporting", "true" );//获取数据库的备注信息
        props.put ( "user", db.getUserName ( ) );
        props.put ( "password", db.getPassWord ( ) );
        Class.forName ( db.getDriver ( ) );//注册驱动
        String url = db.getUrl();
        return DriverManager.getConnection ( url, props );
    }


    /**
     * 获取数据库列表
     */
    public static List<String> getSchemas(DataBase db) throws Exception {
        //1.获取元数据
        Connection connection = getConnection ( db );
        DatabaseMetaData metaData = connection.getMetaData ( );
        //2.获取所有数据库列表
        ResultSet rs = metaData.getCatalogs ( );
        List<String> list = new ArrayList<> ( );
        while (rs.next ( )) {
            list.add ( rs.getString ( 1 ) );
        }
        close ( connection,rs );
        return list;
    }

    /**
     * 获取数据库中的表和字段构造实体类
     */
    public static List<Table> getDataInfo(DataBase db) throws Exception {
        //1.获取元数据
        Connection connection = getConnection ( db );
        DatabaseMetaData metaData = connection.getMetaData ( );
        //2.获取当前连接数据库的所有表信息
        String tableNamePattern = PropertiesUtils.customMap.get ( "tableNamePattern" );
        if (tableNamePattern != null && !"".equals(tableNamePattern) && "ORACLE".equalsIgnoreCase(db.getDbType())){
            // oracle表名必须大写
            tableNamePattern = tableNamePattern.toUpperCase();
        }
        ResultSet rs;
        if (db.getDbType().equalsIgnoreCase("MYSQL")){
            rs = metaData.getTables ( null, null, tableNamePattern, new String[]{"TABLE"} );
        }else {
            //获取当前用户下的所有表信息 （1.第二个参数是用户名，oracle必须大写，2.第三个参数是表名，oracle必须大写）
            rs = metaData.getTables ( null, db.getUserName().toUpperCase(), tableNamePattern, new String[]{"TABLE"} );
        }
        //封装表数据模型
        List<Table> tableList = new ArrayList<> ( );
        Table tab = null;
        while (rs.next ()) {
            tab = new Table ( );
            String tableName = rs.getString ( "TABLE_NAME" );
            tab.setName ( tableName );//原始表名称
            tab.setName2 ( removeTablePrefixAndFirstLetterUpperCase ( tableName ) );//处理后的表名称
            tab.setColumns ( getColumns ( metaData,tableName ) );//列集合
            tab.setKey ( getPrimaryKeys ( metaData,tableName ) );//主键列
            tab.setComment ( rs.getString ( "REMARKS" ) );//列介绍
            tableList.add ( tab );
        }
        close ( connection,rs );
        return tableList;
    }





    /* --------------------------------- 以下是私有方法 ------------------------------------- */

    /**
     * 移除原始表的前缀，并且将第一个字母变大写后返回
     */
    private static String removeTablePrefixAndFirstLetterUpperCase(String tableName) {
        String prefixes = PropertiesUtils.customMap.get ( "tableRemovePrefixes" );//获取指定要删除的前缀
        if (StringUtils.isNotBlank ( prefixes )) {
            //循环获取分割后的前缀
            for (String pf : prefixes.split ( "," ))
                tableName = StringUtils.removePrefix ( tableName, pf, true );
        }
        return StringUtils.makeAllWordFirstLetterUpperCase ( tableName );
    }

    /**
     *获取列相关信息集合
     */
    private static List<Column> getColumns(DatabaseMetaData metaData, String tableName) throws Exception {
        List<Column> columnList = new ArrayList<> ();
        Column column = null;
        ResultSet rs = metaData.getColumns ( null, null, tableName, null );
        while (rs.next ()){
            column = new Column ();
            String columnName = rs.getString ( "COLUMN_NAME" );
            String columnDbType = rs.getString ( "TYPE_NAME" );
            column.setColumnName ( columnName );//原始的列名
            column.setColumnName2 ( StringUtils.toJavaVariableName ( columnName ) );//转换之后的列名
            column.setColumnDbType ( columnDbType );//原始的列类型
            column.setColumnType ( PropertiesUtils.customMap.get ( columnDbType ) );//转换成java之后的列类型
            column.setColumnComment ( rs.getString ( "REMARKS" ) ); //列名的备注信息
            //判断当前列名是否是注解
            String keys = getPrimaryKeys ( metaData, tableName );
            String pri = null;
            if (StringUtils.contains ( columnName,keys.split ( "," ))){
                pri = "PRI";
            }
            column.setColumnKey ( pri );//列名主键标注

            columnList.add ( column );
        }
        close ( rs );
        return columnList;
    }

    /**
     *获取主键名
     */
    private static String getPrimaryKeys(DatabaseMetaData metaData,String tableName) throws Exception {
        String keys = "";
        ResultSet rs = metaData.getPrimaryKeys ( null, null, tableName);
        while (rs.next ()){
            String columnName = rs.getString ( "COLUMN_NAME" );
            keys += columnName + ",";
            if (!rs.next ()){
                //移除最后一个逗号
                keys = keys.substring ( 0,keys.lastIndexOf ( "," ) );
            }
        }
        close ( rs );
        return keys;
    }

    /**
     * 关闭资源
     */
    private static void close(ResultSet resultSet) throws Exception{
        close ( null,resultSet );
    }

    private static void close(Connection connection,ResultSet resultSet) throws Exception{
        if (resultSet!=null){
            resultSet.close ();
        }
        if (connection!=null){
            connection.close ();
        }
    }
}
