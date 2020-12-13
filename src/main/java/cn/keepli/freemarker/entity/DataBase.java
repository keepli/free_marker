package cn.keepli.freemarker.entity;

import lombok.Data;

//数据库实体类
@Data
public class DataBase {
                                     //             127.0.0.1：3306/freemarker
    private static String mysqlUrl = "jdbc:mysql://[ip]:[port]/[db]?characterEncoding=utf-8&serverTimezone=GMT%2B8";
    private static String oracleUrl = "jdbc:oracle:thin:@[ip]:[port]:[db]";

    private String dbType;//数据库类型
    private String userName;
    private String passWord;


    private String driver;
    private String url;

    public DataBase() {}

    public DataBase(String dbType) {
        this(dbType,"127.0.0.1","3306","");
    }

    public DataBase(String dbType,String db) {
        this(dbType,"127.0.0.1","3306",db);
    }

    public DataBase(String dbType,String ip,String db) {
        this(dbType,ip,"3306",db);
        this.userName = "root";
        this.passWord = "root";
    }

    public DataBase(String dbType,String ip,String userName,String passWord,String db) {
        this(dbType,ip,"3306",db);
        this.userName = userName;
        this.passWord = passWord;
    }


    /**
     *
     * @param dbType        数据库类型
     * @param ip            ip
     * @param port          3306
     * @param db            freemarker
     */
    public DataBase(String dbType,String ip,String port,String db) {
        this.dbType = dbType;
        if("MYSQL".endsWith(dbType.toUpperCase())) {
            this.driver="com.mysql.jdbc.Driver";
            this.url=mysqlUrl.replace("[ip]",ip).replace("[port]",port).replace("[db]",db);
        }else{
            this.driver="oracle.jdbc.driver.OracleDriver";
            this.url=oracleUrl.replace("[ip]",ip).replace("[port]",port).replace("[db]",db);
        }
    }

}
