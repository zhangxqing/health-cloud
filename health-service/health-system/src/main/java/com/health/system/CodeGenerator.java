package com.health.system;//package com.ylz;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.FileType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 代码生成器
 */
@Slf4j
public class CodeGenerator {

    //需要生成的表
    private static  final String[] INCLUDE_TABLES= {"METHOD"};
    //需要排除的表
    //private static  final String[] EXCLUDE_TABLES= {""};

    //工程路径
   // private static final String PROJECT_PATH = System.getProperty("user.dir");
    private static final String PROJECT_PATH = "/Users/zq/ylzProjects";
    //模块名称
    private static final String MODULE_NAME = "DataCenterSync";
    //包名
    private static final String PACKAGE_NAME = "com.ylz.dataSync";
    //包路径
    private static final String PACKAGE_DIR = "src/main/java/com/ylz/dataSync";
    //输出文件的路径
    private static final String FILE_OUT_PATH = PROJECT_PATH +"/"+MODULE_NAME+ "/src/main/java";
    //xml输出位置
    private static final String MAPPER_XML_PATH = FILE_OUT_PATH+"/"+"conf";
    //代码生成者
    private static final String AUTHOR = "jcq";
    //数据源
    private static final DbType DB_TYPE = DbType.ORACLE;
    private static final String DRIVER = "oracle.jdbc.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@192.168.0.118:1521/orcl";
    private static final String USER_NAME = "grademed";
    private static final String PASSWORD = "grademed2017ylz";

    /**
     * main方法
     */
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(FILE_OUT_PATH);
        gc.setAuthor(AUTHOR);
        gc.setOpen(false);
        //gc.setSwagger2(true); //实体属性 Swagger2 注解
        gc.setFileOverride(true);// 是否覆盖文件
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setXmlName("%sMapper");
        gc.setMapperName("%sMapper");
        gc.setServiceName("I%sService");
        gc.setServiceImplName("%sServiceImpl");
        //不生成controller 生成dto 将controller模板拿来生成dto
        gc.setControllerName("%sDto");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DB_TYPE);
        dsc.setUrl(URL);
        // dsc.setSchemaName("public");
        dsc.setDriverName(DRIVER);
        dsc.setUsername(USER_NAME);
        dsc.setPassword(PASSWORD);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("");
        pc.setParent(PACKAGE_NAME);
        pc.setEntity("entity");
        //不生成controller 生成dto 将controller模板拿来生成dto
        pc.setController("dto");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        // 如果模板引擎是 freemarker
        //String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
         //String templatePath = "/templates/mapper.xml.ftl";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        // 自定义配置会被优先输出
        String file = Objects.requireNonNull(CodeGenerator.class.getClassLoader().getResource("")).getFile();
      /*  final String rootpath = new File(file).getParentFile().getParentFile().getParent()+"/"+MODULE_NAME;
        System.out.println("项目根目录为:");
        System.out.println(rootpath);*/
        final String rootpath = PROJECT_PATH +"/"+MODULE_NAME;

        focList.add(new FileOutConfig("/templates/entity.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String path = rootpath+"/"+PACKAGE_DIR+"/entity/"+ tableInfo.getEntityName()  + StringPool.DOT_JAVA;
                System.out.println("生成文件为："+path);
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return path;
            }
        });
        focList.add(new FileOutConfig("/templates/dto.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String path = rootpath+"/"+PACKAGE_DIR+"/dto/"+ tableInfo.getEntityName()+"Dto"  + StringPool.DOT_JAVA;
                System.out.println("生成文件为："+path);
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return path;
            }
        });
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String path = rootpath+"/"+PACKAGE_DIR+"/mapper/xml/"+ tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                System.out.println("生成XML文件为："+path);
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return path;
            }
        });
        focList.add(new FileOutConfig("/templates/mapper.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String path = rootpath+"/"+PACKAGE_DIR+"/mapper/"+ tableInfo.getEntityName() + "Mapper" + StringPool.DOT_JAVA;
                System.out.println("生成XML文件为："+path);
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return path;
            }
        });
        focList.add(new FileOutConfig("/templates/service.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String path = rootpath+"/"+PACKAGE_DIR+"/service/I"+ tableInfo.getEntityName()+"Service"  + StringPool.DOT_JAVA;
                System.out.println("生成文件为："+path);
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return path;
            }
        });

        focList.add(new FileOutConfig("/templates/serviceImpl.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String path = rootpath+"/"+PACKAGE_DIR+"/service/impl/"+ tableInfo.getEntityName()+"ServiceImpl"  + StringPool.DOT_JAVA;
                System.out.println("生成文件为："+path);
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return path;
            }
        });
        cfg.setFileCreate(new IFileCreate() {
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {

               //这些类型的文件 如果已存在 则不覆盖
                String[] suffixArray = {"Dto.java","Mapper.xml","Mapper.java","Service.java","ServiceImpl.java","ServiceImpl.java","Controller.java"};

                File file = new File(filePath);

                for(String suffix:suffixArray){
                    if(file.exists()&&filePath.endsWith(suffix)){
                        log.info("文件{}不覆盖",filePath);
                        return false;
                    }
                }

                log.info("文件{}已生成",filePath);
                return true;
                }
            });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.ftl, 会根据使用的模板引擎自动识别
        templateConfig.setEntity("templates/entity.java");
        templateConfig.setMapper("templates/mapper.java");
        templateConfig.setXml("templates/mapper.xml");
        templateConfig.setService("templates/service.java");
//        templateConfig.setServiceImpl("templates/serviceImpl.java");
        //不生成controller 生成dto 将controller模板拿来生成dto
        templateConfig.setController("templates/dto.java");
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setCapitalMode(true);
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityColumnConstant(true);
        //strategy.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");
        strategy.setEntityLombokModel(true);
        strategy.setSuperMapperClass("com.baomidou.mybatisplus.core.mapper.BaseMapper");
        strategy.setSuperServiceClass("com.ylz.common.base.BaseService");
        //strategy.setRestControllerStyle(true);
        //strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
        strategy.setInclude(INCLUDE_TABLES);
        //strategy.setExclude(EXCLUDE_TABLES);
        //strategy.setSuperEntityColumns("id");
       // strategy.setControllerMappingHyphenStyle(true);
       // strategy.setTablePrefix(pc.getModuleName() + "_");
        strategy.setEntitySerialVersionUID(false);
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}

