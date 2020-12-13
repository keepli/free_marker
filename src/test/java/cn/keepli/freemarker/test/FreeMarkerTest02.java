package cn.keepli.freemarker.test;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 内存模板方式 使用
 */
public class FreeMarkerTest02 {

    @Test
    public void test() throws Exception {
        //1.创建创建配置对象
        Configuration cfg = new Configuration ( Configuration.getVersion ( ) );
        cfg.setDefaultEncoding("utf-8");//指定编码字符集
        cfg.setClassicCompatible ( true );//开启兼容模式，不开启在语法上会出现异常
        //2.指定加载器
        cfg.setTemplateLoader ( new StringTemplateLoader ( ) );
        //3.创建字符串模板
        //3.1 字符串
        String templateStr = "欢迎您：${username}";
        //3.2通过字符串创建模板
        /**
         * 创建模板对象
         *      参数一：模板名称
         *      参数二：使用字符串输入流读取模板
         *      参数三：配置对象
         */
        Template template = new Template ( "template",new StringReader ( templateStr ),cfg );
        //4.构造数据
        Map<String, Object> dataModel = new HashMap<> ( );
        dataModel.put ( "username", "李狗蛋" );
        //5.处理模板
        /**
         * 处理模型
         *      参数一：数据模型
         *      参数二：输出流对象 writer（FileWriter文件输出，PrintWriter控制台输出）
         */
        //template.process ( dataModel, new FileWriter ( "a.txt" ) );
        template.process ( dataModel, new PrintWriter ( System.out ) );
    }
}
