package com.szw;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;

import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * @Author Szw 2001
 * @Date 2023/4/29 20:43
 * @Slogn 致未来的你！
 */
public class CodeGenerator {
    public static void main(String[] args) {
        String outPutDir = "E:\\myjava\\x-admin_1\\src\\main\\java";
        String parent = "com.szw";
        String moduleName = "sys";
        String mapperLocation = "E:\\myjava\\x-admin_1\\src\\main\\resources\\mapper\\"+moduleName;
        String tablePrefix = "x_";
        String tables = "x_user,x_role,x_menu,x_user_role,x_role_menu";
        String url = "jdbc:mysql://localhost:3306/xdb?serverTimezone=UTC";
        String userName = "root";
        String password ="szw123";


        FastAutoGenerator.create(url, userName, password)
                .globalConfig(builder -> {
                    builder.author("szw") // 设置作者
//                            .enableSwagger() // 开启 swagger 模式
//                            .fileOverride() // 覆盖已生成文件
                            .outputDir(outPutDir); // 指定输出目录
                })

                .packageConfig(builder -> {
                    builder.parent(parent) // 设置父包名
                            .moduleName(moduleName) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml,mapperLocation)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tables) // 设置需要生成的表名
                            .addTablePrefix(tablePrefix); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }
}
