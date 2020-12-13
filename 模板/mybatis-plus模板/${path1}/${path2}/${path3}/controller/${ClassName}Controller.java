<#--调用内置函数将首字母变成小写，而且存入root中-->
<#assign ClassNameLower= ClassName ? uncap_first>
<#--将id主键类型存入root中-->
<#list table.columns as column>
    <#if column.columnKey=="PRI"&&column.columnName2=="id">
        <#assign idType="${column.columnType}">
        <#break>
    </#if>
</#list>
package ${pPackage}.controller;

import ${pPackage}.pojo.${ClassName};
import cn.keepli.user.service.${ClassName}Service;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ${projectComment}控制层
 */
@RestController
@RequestMapping("/${ClassNameLower}")
@CrossOrigin
public class ${ClassName}Controller {

    @Autowired
    private ${ClassName}Service ${ClassNameLower}Service;

    /**
     * 查询所有${projectComment}
     */
    @GetMapping
    public Result findAll(){
        List<${ClassName}> ${ClassNameLower}List = ${ClassNameLower}Service.findAll ( );
        return new Result ( StatusCode.OK,true,"查询成功",${ClassNameLower}List);
    }

    /**
     *根据id查询${projectComment}
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable ${idType} id){
        ${ClassName} ${ClassNameLower} = ${ClassNameLower}Service.findById ( id );
        return new Result ( StatusCode.OK,true,"查询成功",${ClassNameLower});
    }

    /**
     *新增${projectComment}
     */
    @PostMapping
    public Result save(@RequestBody(required = false) ${ClassName} ${ClassNameLower}){
        ${ClassNameLower}Service.save ( ${ClassNameLower} );
        return new Result ( StatusCode.OK,true,"保存成功");
    }

    /**
     * 修改${projectComment}
     */
    @PutMapping
    public Result update(@RequestBody(required = false) ${ClassName} ${ClassNameLower}){
        ${ClassNameLower}Service.update(${ClassNameLower});
        return new Result( StatusCode.OK,true,"修改成功");
    }

    /**
     * 根据id删除${projectComment}
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable ${idType} id){
        ${ClassNameLower}Service.delete(id);
        return new Result( StatusCode.OK,true,"删除成功");
    }

}
