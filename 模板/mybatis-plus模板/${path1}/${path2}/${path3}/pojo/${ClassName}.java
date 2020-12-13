package ${pPackage}.pojo;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * ${projectComment}实体类
 */
@TableName("${table.name}")
@Data
public class ${ClassName} implements Serializable {
<#--遍历column集合-->
<#list table.columns as column>
    <#--主键判断-->
<#if column.columnKey=="PRI">
    @TableId(type = IdType.INPUT)
    @TableField("${column.columnName}")
    private ${column.columnType} ${column.columnName2};//${column.columnComment}
<#else>
    <#--判断是否为Date类型-->
<#if column.columnType=="Date">
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("${column.columnName}")
    private ${column.columnType} ${column.columnName2};//${column.columnComment}
<#else>
    @TableField("${column.columnName}")
    private ${column.columnType} ${column.columnName2};//${column.columnComment}
</#if>
</#if>
</#list>
}