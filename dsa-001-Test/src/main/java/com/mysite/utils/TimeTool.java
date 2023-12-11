package com.mysite.utils;

import sun.reflect.generics.tree.VoidDescriptor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ClassName: TimeTool
 * Package: com.mysite.utils
 * Description
 *  计算算法执行的时间
 * @Author zhl
 * @Create 2023/10/28 21:26
 * version 1.0
 */
public class TimeTool {
    private static final SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss.SSS");

    public interface Task{
        void execute();
    }

    public static void check(String title,Task task){
        if (task == null) return;
        title = (title == null) ? "" : ("【" + title +"】");
        System.out.println(title);
        System.out.println("开始："+fmt.format(new Date()));
        long begin = System.currentTimeMillis();

        task.execute();

        long end = System.currentTimeMillis();

        System.out.println("结束："+fmt.format(new Date()));
        double delta = (end - begin) / 1000.0;
        System.out.println("耗时："+delta + "秒");
        System.out.println("------------------------------------");
    }
}
