欢迎您：${username}
<#--这是一个注释-->
<#--获取数据 ${根节点下的数据 }-->

<#--if指令 里面也有brake的概念-->
if指令：
<#if flag==1>
    flag等于1
    <#elseif flag==2>
    flag等于2
    <#else>
    传入其它数据
</#if>


<#--list指令 循环迭代
    语法：数据名称 as 别名
            获取索引：别名_index
            判断是否有下一个元素：别名_has_next
            也可以使用<#break>跳出迭代
-->
list指令：
<#list list as day>
    ${day_index}=${day}
    <#if day=="星期三"><#break ></#if>
    <#if day_has_next>,</#if>
</#list>


<#--include指令 模板包含
    写相对路径或绝对路径
-->
include指令：
<#include "template02.ftl">


<#--assign指令 在ftl模板中定义数据存入到root节点下-->
assign指令测试：
<#assign name="张三疯">
${name}

