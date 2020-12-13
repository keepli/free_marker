<#--调用内置函数将首字母变成小写，而且存入root中-->
<#assign ClassNameLower= ClassName ? uncap_first>
<#--将id主键类型存入root中-->
<#list table.columns as column>
    <#if column.columnKey=="PRI"&&column.columnName2=="id">
        <#assign idType="${column.columnType}">
        <#break>
    </#if>
</#list>
package ${pPackage}.service.impl;

import ${pPackage}.dao.${ClassName}Dao;
import ${pPackage}.pojo.${ClassName};
import ${pPackage}.service.${ClassName}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * ${projectComment}业务层实现类
 */
@Service
public class ${ClassName}ServiceImpl implements ${ClassName}Service {

    @Autowired
    private ${ClassName}Dao ${ClassNameLower}Dao;

    /**
     * 查询所有${projectComment}
     */
    @Override
    public List<${ClassName}> findAll() {
        return ${ClassNameLower}Dao.selectList ( null );
    }

    /**
     *根据id查询${projectComment}
     */
    @Override
    public ${ClassName} findById(${idType} id) {
        return ${ClassNameLower}Dao.selectById ( id );
    }

    /**
     *新增${projectComment}
     */
    @Override
    public void save(${ClassName} ${ClassNameLower}) {
        ${ClassNameLower}Dao.insert ( ${ClassNameLower} );
    }

    /**
     * 修改${projectComment}
     */
    @Override
    public void update(${ClassName} ${ClassNameLower}) {
        ${ClassNameLower}Dao.updateById ( ${ClassNameLower} );
    }

    /**
     * 根据id删除${projectComment}
     */
    @Override
    public void delete(${idType} id) {
        ${ClassNameLower}Dao.deleteById ( id );
    }
}
