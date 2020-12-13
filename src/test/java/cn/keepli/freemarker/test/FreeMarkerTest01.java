package cn.keepli.freemarker.test;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件模板方式 使用
 */
public class FreeMarkerTest01 {

    @Test
    public void test() throws Exception {
        //1.创建FreeMarker的配置类
        Configuration cfg = new Configuration ( Configuration.getVersion() );
        cfg.setDefaultEncoding("utf-8");//指定编码字符集
        cfg.setClassicCompatible ( true );//开启兼容模式，不开启在语法上会出现异常
        //2.指定模板加载器，将模板存入缓存中
        //文件路径加载器
        FileTemplateLoader ftl = new FileTemplateLoader ( new File ( "templates" ) );
        cfg.setTemplateLoader ( ftl );
        //3.获取模板
        Template template = cfg.getTemplate ( "template01.ftl" );
        //4.构造数据模型
        Map<String, Object> dataModel = new HashMap<> ( );
        dataModel.put ( "username","李狗蛋" );
        //5.文件输出
        /**
         * 处理模型
         *      参数一：数据模型
         *      参数二：输出流对象 writer（FileWriter文件输出，PrintWriter控制台输出）
         */
        //template.process ( dataModel,new FileWriter ( "test.txt" ) );
        template.process ( dataModel,new PrintWriter ( System.out ) );
    }
}
