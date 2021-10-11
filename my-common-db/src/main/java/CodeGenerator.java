import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HuangXun
 * @date 2021/5/8
 * @description*/



public class CodeGenerator {

    /**
     * 数据库用户名 密码 url 及注释中author的配置
     */
    private static String url="jdbc:mysql://182.254.221.85:3306/t_goods?useUnicode=true&zeroDateTimeBehavior=convertToNull&characterEncoding=utf8";
    private static String username="root";
    private static String password="ROOT_mysql_3306";
    private static String driverName="com.mysql.cj.jdbc.Driver";
    private static String author = "guokui";

    public static List getAllTableColumn() throws SQLException, ClassNotFoundException {

        List<String> tableNameMap = new ArrayList<>();


        //加载驱动
        Class.forName(driverName);

        //获得数据库连接
        Connection connection = DriverManager.getConnection(url, username, password);
        //获得元数据
        DatabaseMetaData metaData = connection.getMetaData();
        //获得表信息
        ResultSet tables = metaData.getTables("t_goods", "t_goods", null, new String[]{"TABLE"});

        while (tables.next()) {
            Map<String, String> columnNameMap = new HashMap<>(); //保存字段名
            //获得表名
            String table_name = tables.getString("TABLE_NAME");

            tableNameMap.add(table_name);

        }

        return tableNameMap;
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String prefixDao = "/my-service-api/my-service-good-api";
        String prefixService = "/my-service/my-service-goods";
        List<String > list=getAllTableColumn();
        for (String table:list) {
            // 代码生成器
            AutoGenerator mpg = new AutoGenerator();

            // 全局配置
            GlobalConfig gc = new GlobalConfig();
            //生成mapper mapper.xml domain的路径
            String projectPathDao = System.getProperty("user.dir") + prefixDao;
            //生成service的路径
            String projectPathService = System.getProperty("user.dir") + prefixService;
            String xml = "/src/main/resources/mapper/goods/";
            String java = "/src/main/java/com/me/goods/";
            gc.setOutputDir(projectPathService + "/src/main/java");
//        // TODO 设置用户名
            gc.setAuthor(author);
//        // service 命名方式
//        // service impl 命名方式
//        gc.setServiceImplName("%sServiceImp");
//        gc.setServiceName("%sService");
//        gc.setControllerName("%sController");
//        // 自定义文件命名，注意 %s 会自动填充表实体属性！
//        gc.setMapperName("%sMapper");
//        gc.setXmlName("%sMapper");
            gc.setFileOverride(true);//是否覆盖文件
            gc.setOpen(false);//打开输出目录
            gc.setActiveRecord(true);
            // XML 二级缓存
            gc.setEnableCache(false);
            // XML ResultMap
            gc.setBaseResultMap(true);
            // XML columList
            gc.setBaseColumnList(false);
            //打开swagger2配置，添加ApiModel、ApiModelProperty注解
            gc.setSwagger2(false);
            mpg.setGlobalConfig(gc);

            // TODO 数据源配置
            DataSourceConfig dsc = new DataSourceConfig();
            dsc.setUrl(url);
            dsc.setDriverName(driverName);
            dsc.setUsername(username);
            dsc.setPassword(password);
            mpg.setDataSource(dsc);

//        // TODO 包配置
            PackageConfig pc = new PackageConfig();
            //pc.setModuleName(scanner("模块名"));
            pc.setParent("com.me.goods");
            pc.setEntity("pojo");
            pc.setMapper("mapper");
            pc.setService("service");
            pc.setServiceImpl("service.impl");
            pc.setController("controller");
            mpg.setPackageInfo(pc);
//            TemplateConfig  tc = new TemplateConfig();

//            tc.disable(TemplateType.ENTITY);
//            tc.setEntity("/templates/entity.java");
//            tc.setService("/templates/service.java");
//            tc.setServiceImpl("/templates/serviceImpl.java");
//            tc.setMapper("/templates/mapper.java");
//            tc.setXml("/templates/mapper.xml");
//            tc.setController("/templates/controller.java");
//            mpg.setTemplate(tc);
//            mpg.setPackageInfo(null);
//            mpg.setTemplate(null);
            // 自定义配置
            InjectionConfig cfg = new InjectionConfig() {
                @Override
                public void initMap() {
                    // to do nothing
                }
            };
            String templatePath = "/templates/mapper.xml.ftl";
            List<FileOutConfig> focList = new ArrayList<>();
            focList.add(new FileOutConfig(templatePath) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输入文件名称
                    return projectPathService + xml + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                }
            });


            //控制层
            templatePath = "/templates/controller.java.ftl";
            // 自定义配置会被优先输出
            focList.add(new FileOutConfig(templatePath) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输出文件名 + pc.getModuleName()
                    String expand = projectPathService + java + "controller";
                    String entityFile = String.format((expand + File.separator + "%s" + ".java"), tableInfo.getControllerName());
                    return entityFile;
                }
            });

            //业务层
            templatePath = "/templates/service.java.ftl";
            // 自定义配置会被优先输出
            focList.add(new FileOutConfig(templatePath) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输出文件名 + pc.getModuleName()
                    String expand = projectPathService + java + "service";
                    String entityFile = String.format((expand + File.separator + "%s" + ".java"), tableInfo.getServiceName());
                    return entityFile;
                }
            });
            templatePath = "/templates/serviceImpl.java.ftl";
            // 自定义配置会被优先输出
            focList.add(new FileOutConfig(templatePath) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输出文件名 + pc.getModuleName()
                    String expand = projectPathService + java + "service/impl";
                    String entityFile = String.format((expand + File.separator + "%s" + ".java"), tableInfo.getServiceImplName());
                    return entityFile;
                }
            });

            //数据层
            templatePath = "/templates/mapper.java.ftl";
            // 自定义配置会被优先输出
            focList.add(new FileOutConfig(templatePath) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输出文件名 + pc.getModuleName()
                    String expand = projectPathService + java + "mapper";
                    String entityFile = String.format((expand + File.separator + "%s" + ".java"), tableInfo.getMapperName());
                    return entityFile;
                }
            });

            //数据层
            templatePath = "/templates/entity.java.ftl";
            // 自定义配置会被优先输出
            focList.add(new FileOutConfig(templatePath) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输出文件名 + pc.getModuleName()
                    String expand = projectPathDao + java + "pojo";
                    String entityFile = String.format((expand + File.separator + "%s" + ".java"), tableInfo.getEntityName());
                    return entityFile;
                }
            });


            cfg.setFileOutConfigList(focList);
            mpg.setCfg(cfg);
            mpg.setTemplate(new TemplateConfig().setXml(null).setController(null).setEntity(null).setEntityKt(null).setService(null).setServiceImpl(null).setMapper(null));

            // 策略配置
            StrategyConfig strategy = new StrategyConfig();
            strategy.setNaming(NamingStrategy.underline_to_camel);
            strategy.setColumnNaming(NamingStrategy.underline_to_camel);
            strategy.setEntityLombokModel(true);
            strategy.setChainModel(true);
            // 驼峰转连字符
            strategy.setControllerMappingHyphenStyle(true);
            //生成实体时,生成字段注解
            strategy.setEntityTableFieldAnnotationEnable(true);
            // 设置逻辑删除键
            strategy.setLogicDeleteFieldName("is_del");
            // TODO 指定生成的bean的数据库表名

            strategy.setInclude(table);
            //strategy.setSuperEntityColumns("id");

            mpg.setStrategy(strategy);
            // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
            mpg.setTemplateEngine(new FreemarkerTemplateEngine());
            mpg.execute();
        }
    }



}
