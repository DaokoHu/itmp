package com.huadi.itmp.util;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @author 胡学良
 * @date 2021-08-26 14:39
 **/
public class CodeGenerator {
    static class Module {
        private String moduleName;
        private String[] include;

        public Module(String moduleName, String[] include) {
            this.moduleName = moduleName;
            this.include = include;
        }
        public static Module of(String moduleName, String... include) {
            return new Module(moduleName, include);
        }
    }

    public static void main(String[] args) {
        Module[] modules = new Module[] {
                // 用户模块
                Module.of("user",
                        "user", "user_auth", "role", "role_menu", "relation"),
                Module.of("request",
                        "course_change_record","leave_request_record","request_reject_record","teacher_change_record"),
                Module.of("project",
                        "project"),
                Module.of("curriculum",
                        "curriculum"),
                Module.of("resource",
                        "resource"),
                Module.of("sign",
                        "sign"),
                Module.of("meeting",
                        "meeting", "meeting_type", "role_meeting", "meeting_clbum", "meeting_user"),
                Module.of("school",
                        "school", "college"),
                Module.of("dorm",
                        "dorm", "dorm_student"),
                Module.of("classroom",
                        "classroom"),
                Module.of("clbum",
                        "clbum", "clbum_student", "clbum_teacher", "group", "group_student"),
                Module.of("task",
                        "task", "item", "item_score", "reply", "score"),
                Module.of("exam",
                        "exam", "clbum_exam"),
                Module.of("report",
                        "day_report", "week_report", "leave_report_record", "report_leave")



        };

        for (Module module :
                modules) {
            execute(module.moduleName, module.include);
        }
    }

    private static void execute(String moduleName, String... include) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setIdType(IdType.ASSIGN_UUID);
        String projectPath = "E:\\java\\itmp";
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("胡学良");
        gc.setOpen(false);
        gc.setSwagger2(true); // 实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/itmp?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("197442");
        mpg.setDataSource(dsc);

        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setInclude(include);
        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.huadi.itmp.modules");
        pc.setModuleName(moduleName);
        mpg.setPackageInfo(pc);
        mpg.execute();
    }
}

