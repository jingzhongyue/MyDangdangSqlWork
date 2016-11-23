package sql;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class RuleWork {

    public static String sqlFileName = "careateTable.sql";

    public static String newSqlFileName = "careateTable-new.sql";

    public static String urlPath = "sql/";

    public static String propertiesName = "rule.properties";

    // ////////

    public static String propertiesUrl = urlPath + propertiesName;
    public static String sqlFile = urlPath + sqlFileName;
    public static String newSqlFile = urlPath + newSqlFileName;


    public void start() throws IOException {

        RWSql rWSql = new RWSql();

        // --读取sql 生成 properties
        rWSql.readMakeProperties(sqlFile, propertiesUrl);

    }


    public void next() throws IOException {
        // 手动去修改 properties 配置 0-3 还是 0-7 还是要加 DROP TABLE IF EXISTS

        File file = new File(propertiesUrl);

        if (file.exists()) {

            RWSql rWSql = new RWSql();
            // 根据 配置 和sql 去生成 全新的 sql
            rWSql.makeSqlByProperties(propertiesUrl, newSqlFile, sqlFile);
        } else {
            System.out.println("不存在");
        }

    }


    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);

        RuleWork r = new RuleWork();

        System.out.println("start");
        System.out.println("是否要生成 rule.properties:(0:不是,1:是,other:退出)");
        int nextInt = sc.nextInt();

        if (nextInt == 1) {
            r.start();
            System.out.println("生成完毕，请前往查看和修改");
        } else {
            if (nextInt != 0) {
                System.exit(0);
            }

        }

        System.out.println("是否要生成newsql:(0:不是,1:是,other:退出)");

        int nextIntEnd = sc.nextInt();
        if (nextIntEnd == 1) {
            r.next();// 线 手动去修改 properties 配置 0-3 还是 0-7 还是要加 DROP TABLE IF
            System.out.println("生成完毕");
        }
        if (nextInt != 0) {
            System.exit(0);
        }
        System.out.println("系统over");
    }
}
