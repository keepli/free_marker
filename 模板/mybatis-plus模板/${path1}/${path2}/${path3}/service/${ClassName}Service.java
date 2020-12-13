<#--调用内置函数将首字母变成小写，而且存入root中-->
<#assign ClassNameLower= ClassName ? uncap_first>
<#--将id主键类型存入root中-->
<#list table.columns as column>
    <#if column.columnKey=="PRI"&&column.columnName2=="id">
        <#assign idType="${column.columnType}">
        <#break>
    </#if>
</#list>
package ${pPackage}.service;

import ${pPackage}.pojo.${ClassName};

import java.util.List;

/**
 * ${projectComment}业务层接口
 */
public interface ${ClassName}Service {

    /**
     * 查询所有${projectComment}
     */
    List<${ClassName}> findAll();

    /**
     *根据id查询${projectComment}
     */
    ${ClassName} findById(${idType} id);

    /**
     *新增${projectComment}
     */
    void save(${ClassName} ${ClassNameLower});

    /**
     * 修改${projectComment}
     */
    void update(${ClassName} ${ClassNameLower});

    /**
     * 根据id删除${projectComment}
     */
    void delete(${idType} id);
}
