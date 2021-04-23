package com.xdcplus.cabinetel.common.config;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Fish Fei
 */
public class CodeGenerator {
    // 固定
    private static final String projectPath = System.getProperty("user.dir")+"/customerorder-service";
    private static final String outPutDir = projectPath + "/src/main/java";
    // 自定义
    private static final String author = "fish";
    private static final String packageName = "com.xdcplus.cabinetel.basic";
    private static final String moduleName = "door";
//    private static final String[] tableName = ("sys_business,sys_dept,sys_permission,sys_role,sys_role_permission,sys_user,sys_user_business,sys_user_dept,sys_user_role").split(",");
    private static final String[] tableName = ("price_terms,charge_type,customer,payment_terms").split(",");
    private static final String tablePrefix = "sys";

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 设置全局配置
        mpg.setGlobalConfig(getGlobalConfig());
        // 设置数据源配置
        mpg.setDataSource(getDataSourceConfig());
        // 包配置
        PackageConfig pc = getPackageConfig();
        mpg.setPackageInfo(pc);
        // 自定义配置
        mpg.setCfg(getInjectionConfig());
        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);
        // 策略配置
        mpg.setStrategy(getStrategyConfig());
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

    private static GlobalConfig getGlobalConfig() {
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(outPutDir);
        gc.setAuthor(author);
        gc.setOpen(false);
        gc.setEntityName("%s");
        gc.setMapperName("%sMapper");
        gc.setServiceName("%sService");
        // 是否覆盖文件，默认false不覆盖
        gc.setFileOverride(true);
        // XML ResultMap
        gc.setBaseResultMap(true);
        // XML columList
        gc.setBaseColumnList(true);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        return gc;
    }

    private static DataSourceConfig getDataSourceConfig() {
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://10.20.1.130:3306/processplus?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false");
        // dsc.setSchemaName("public");
//        String driverName = "com.mysql.jdbc.Driver";
        String driverName = "com.mysql.cj.jdbc.Driver";
        dsc.setDriverName(driverName);
        dsc.setUsername("root");
        dsc.setPassword("xdcplus.com");
        return dsc;
    }

    private static PackageConfig getPackageConfig() {
        // 包配置
        PackageConfig pc = new PackageConfig();
        // 包名称
        pc.setParent(packageName);
        // 模块名称
//        pc.setModuleName(moduleName);
        return pc;
    }

    private static InjectionConfig getInjectionConfig() {
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/src/main/resources/config/mapper/" + tableInfo.getEntityName()
                        + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        return cfg;
    }

    private static StrategyConfig getStrategyConfig() {
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共父类
//        strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        // 写于父类中的公共字段
//        strategy.setSuperEntityColumns("id");
//        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setInclude(tableName);
        strategy.setControllerMappingHyphenStyle(true);
        // ===>4、设置去除数据库表的前缀
//        strategy.setTablePrefix(tablePrefix + "_");
        return strategy;
    }
}