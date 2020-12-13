package cn.keepli.freemarker.core;

import cn.keepli.freemarker.utils.FileUtils;
import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * 代码生成器的核心处理
 *      使用Freemarker完成文件生成
 *          数据模型+模板
 * 数据：
 *      数据模型
 *      模板的位置
 *      生成文件的路径
 */
public class Generator {

    private String templatePath;
    private String outPath;
    private Configuration cfg;

    public Generator(String templatePath, String outPath) {
        try {
            this.templatePath = templatePath;
            this.outPath = outPath;
            //实例化Configuration
            cfg = new Configuration ( Configuration.getVersion () );
            cfg.setDefaultEncoding("utf-8");//指定编码字符集
            cfg.setClassicCompatible ( true );//开启兼容模式，不开启在语法上会出现异常
            //指定模板加载器
            FileTemplateLoader ftl = new FileTemplateLoader ( new File ( templatePath ) );
            cfg.setTemplateLoader ( ftl );
        } catch (Exception e) {
            e.printStackTrace ( );
        }

    }

    /**
     * 代码生成
     *      1.扫描模板路径下的所有模板
     *      2.对每个模板进行文件生成（数据模型）
     */
    public void scanAndGenerator(Map<String,Object> dataModel) throws Exception {
        //1.根据模板路径找到此路径下的所有模板文件
        List<File> fileList = FileUtils.searchAllFile ( new File ( templatePath ) );
        //2.对每个模板进行文件生成
        for (File file : fileList) {
            executeGenerator ( dataModel,file );
        }
    }

    /**
     * 对模板进行文件生成
     * @param dataModel ： 数据模型
     * @param file      ： 模板文件
     *            模板文件：c：com.ihrm.system.abc.java
     */
    private void executeGenerator(Map<String,Object> dataModel,File file) throws Exception {
        //1.文件路径处理   (E:\模板\${path1}\${path2}\${path3}\${ClassName}.java)
        //templatePath : E:\模板\
        //去掉路径前缀
        String templateFileName = file.getAbsolutePath().replace(this.templatePath,"");
        String outFileName = processTemplateString(templateFileName,dataModel);
        //2.读取文件模板
        Template template = cfg.getTemplate(templateFileName);
        //template.setOutputEncoding("utf-8");//指定生成文件的字符集编码
        //3.创建文件
        File fileOut = FileUtils.mkdir(outPath, outFileName);
        //4.模板处理（文件生成）
        FileWriter fw = new FileWriter (fileOut);
        template.process(dataModel,fw);
        fw.close();
    }

    /**
     * 文件名生成输出
     * 使用内存模板方式进行
     */
    private String processTemplateString(String templateString,Map dataModel) throws Exception {
        StringWriter out = new StringWriter();
        Template template = new Template("ts",new StringReader (templateString),cfg);
        template.process(dataModel,out);
        String outFileName = out.toString ( );
        out.close ();
        return outFileName;
    }

}
