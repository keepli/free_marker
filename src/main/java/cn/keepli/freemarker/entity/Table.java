package cn.keepli.freemarker.entity;

import lombok.Data;

import java.util.List;

/**
 * 表实体
 * @author Administrator
 *
 */
@Data
public class Table {
	
	private String name;//原始表名称
	private String name2;//处理后的表名称
	private String comment;//列介绍
	private String key;// 主键列
	private List<Column> columns;//列集合
}
