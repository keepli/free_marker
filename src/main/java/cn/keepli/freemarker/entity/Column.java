package cn.keepli.freemarker.entity;

import lombok.Data;

/**
 * 列对象
 */
@Data
public class Column {
	
	private String columnName;//列名称
	private String columnName2;//列名称(处理后的列名称)
	private String columnType;//列类型
	private String columnDbType;//列数据库类型
	private String columnComment;//列备注
	private String columnKey;//是否是主键

}
