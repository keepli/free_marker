package cn.keepli.freemarker.test;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件模板方式 使用
 *  指令测试
 */
public class FreeMarkerTest03 {

    private Template template;

    @Before
    public void init() throws Exception {
        //1.创建FreeMarker的配置类
        Configuration cfg = new Configuration ( Configuration.getVersion() );
        cfg.setDefaultEncoding("utf-8");//指定编码字符集
        cfg.setClassicCompatible ( true );//开启兼容模式，不开启在语法上会出现异常
        //2.指定模板加载器，将模板存入缓存中
        //文件路径加载器
        FileTemplateLoader ftl = new FileTemplateLoader ( new File ( "templates" ) );
        cfg.setTemplateLoader ( ftl );
        //3.获取模板
        template = cfg.getTemplate ( "template01.ftl" );
        Map<String, Object> dataModel = new HashMap<> ( );
        dataModel.put ( "username","李狗蛋" );
    }

    /**
     * if指令测试
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        //4.构造数据模型
        Map<String, Object> dataModel = new HashMap<> ( );
        dataModel.put ( "flag","1" );
        //dataModel.put ( "flag","2" );
        //dataModel.put ( "flag","3" );
        //5.文件输出
        template.process ( dataModel,new PrintWriter ( System.out ) );
    }

    /**
     * list指令测试
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {
        //4.构造数据模型
        List<String> list = new ArrayList<> ( );
        list.add ( "星期一" );
        list.add ( "星期二" );
        list.add ( "星期三" );
        list.add ( "星期四" );
        list.add ( "星期五" );
        Map<String, Object> dataModel = new HashMap<> ( );
        dataModel.put ( "list",list );
        //5.文件输出
        template.process ( dataModel,new PrintWriter ( System.out ) );
    }

    /**
     * include指令测试
     * @throws Exception
     */
    @Test
    public void test3() throws Exception {
        //4.构造数据模型
        Map<String, Object> dataModel = new HashMap<> ( );
        dataModel.put ( "include","模板包含测试" );
        //5.文件输出
        template.process ( dataModel,new PrintWriter ( System.out ) );
    }
}
