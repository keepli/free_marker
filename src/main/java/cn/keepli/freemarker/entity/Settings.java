package cn.keepli.freemarker.entity;


import cn.keepli.freemarker.utils.StringUtils;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Data
public class Settings {

    private String project="example"; //项目名
    private String pPackage="com.example.demo"; //包名
    private String projectComment; //项目中文名
    private String author;  //作者
    private String path1="com"; //cn
    private String path2="example"; //keepli
    private String path3="demo"; //自定义模块名  如：article、user
    private String pathAll;

    public Settings(String project, String pPackage, String projectComment, String author) {
        if(StringUtils.isNotBlank(project)) {
            this.project = project;
        }
        if(StringUtils.isNotBlank(pPackage)) {
            this.pPackage = pPackage;
        }
        this.projectComment = projectComment;
        this.author = author;
        String[] paths = pPackage.split("\\.");
        path1 = paths[0];
        path2 = paths.length>1?paths[1]:path2;
        path3 = paths.length>2?paths[2]:path3;
        pathAll = pPackage.replaceAll(".","/");
    }

    public Map<String, Object> getSettingMap(){
        Map<String, Object> map = new HashMap<>();
        Field[] declaredFields = Settings.class.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            try{
                map.put(field.getName(), field.get(this));
            }catch (Exception e){}
        }
        return map;
    }
}
