package sql;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


/**
 * 
 * @author hhh
 * @Description 读取和输出sql
 * @Title RWSql.java
 * @Package sql
 * @date 2016年11月23日 下午5:43:02
 */
public class RWSql {

    public static String wordCreateTable = "CREATE TABLE";

    public static String table = "table";

    public static String tbaleName = ".name";
    public static String tbaleCandrop = ".candrop";
    public static String tbaleRule = ".rule";
    public static String tbaleSplit = "_";


    public void readMakeProperties(String sqlPath, String propertiesUrl) throws IOException {

        // --配置文件的生成
        File file = new File(propertiesUrl);

        mkdirNext(file, propertiesUrl);

        // －读取sql
        String str = "";

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(sqlPath)));

        // －解析sql
        List<String> tableList = new ArrayList<String>();

        while ((str = br.readLine()) != null) {
            if (str.startsWith("/*")) {
                // --这个是注释
            } else {
                if (str.indexOf(wordCreateTable) > -1) {
                    // --开头
                    String[] split = str.split(wordCreateTable);

                    if (split.length == 1) {
                        str = split[0];
                    } else {
                        str = split[1];
                    }

                    str = str.replaceAll("\\s*", "");
                    str = str.replaceAll("'", "");
                    str = str.replaceAll("\"", "");
                    tableList.add(str);
                }
            }
        }
        br.close();

        // －输出配置
        // Properties prop = new Properties();/／输出乱序
        FileOutputStream oFile = new FileOutputStream(file, false);

        StringBuffer sb = new StringBuffer();

        int size = tableList.size();
        // prop.setProperty(table, size + "");
        sb.append("#tableNumber\n");
        sb.append(table + "=" + size + "\n");

        for (int i = 0; i < size; i++) {
            // prop.setProperty((table + i + tbaleName), tableList.get(i));
            // prop.setProperty((table + i + tbaleCandrop), "true");// true
            // prop.setProperty((table + i + tbaleRule), "0-3");// ""
            sb.append("\n#tableName\n");
            sb.append((table + i + tbaleName) + "=" + tableList.get(i) + "\n");
            sb.append((table + i + tbaleCandrop) + "=" + "true" + "\n");
            sb.append((table + i + tbaleRule) + "=" + "0-3" + "\n");
            sb.append((table + i + tbaleSplit) + "=" + "_" + "\n");
        }

        oFile.write(sb.toString().getBytes("utf-8"));

        oFile.flush();

        oFile.close();
    }


    public void makeSqlByProperties(String propertiesUrl, String newSqlFileUrl, String sqlFile)
            throws IOException {

        File newSqlFile = new File(newSqlFileUrl);

        mkdirNext(newSqlFile, newSqlFileUrl);

        // -解析前奏1 读取 sql-准备解析

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(sqlFile)));

        Map<String, String> sqlMap = new HashMap<String, String>();

        StringBuffer tableSql = new StringBuffer();
        String tableName = "";
        String str = "";
        while ((str = br.readLine()) != null) {
            if (str.startsWith("/*")) {
                // --这个是注释
            } else {

                if (str.indexOf(wordCreateTable) > -1) {

                    if (tableSql.length() != 0 && tableName.length() != 0) {
                        // --存上次的数据
                        sqlMap.put(tableName, tableSql.toString());
                    }
                    tableSql = new StringBuffer();
                    // --开头
                    String[] split = str.split(wordCreateTable);

                    String strX = "";
                    if (split.length == 1) {
                        strX = split[0];
                    } else {
                        strX = split[1];
                    }
                    strX = strX.replaceAll("\\s*", "");
                    strX = strX.replaceAll("'", "");
                    strX = strX.replaceAll("\"", "");
                    tableName = strX;
                    // -获取头
                    // tableSql.append(str + "\n");//--头不要下面增加
                } else {
                    if (str.length() > 0) {
                        tableSql.append(str.trim() + "\n");
                    }

                }

            }
        }

        if (tableSql.length() != 0 && tableName.length() != 0) {
            sqlMap.put(tableName, tableSql.toString());
        }

        br.close();

        // -解析前奏2
        File propertiesFile = new File(propertiesUrl);// 配置

        InputStream inputstream = new FileInputStream(propertiesFile);
        Properties properties = new Properties();
        properties.load(inputstream);

        // －解析
        int tableNumber = Integer.parseInt(properties.get("table").toString());
        StringBuffer tableEndSql = new StringBuffer();

        for (int i = 0; i < tableNumber; i++) {

            String tbaleNameStr = properties.get(table + i + tbaleName).toString();
            String tbaleCandropStr = properties.get(table + i + tbaleCandrop).toString();
            String tbaleRuleStr = properties.get(table + i + tbaleRule).toString();
            String tbaleSplitStr = properties.get(table + i + tbaleSplit).toString();

            tableEndSql.append(fx(tbaleNameStr, tbaleCandropStr, tbaleRuleStr, tbaleSplitStr, sqlMap));

        }

        // 输出
        FileOutputStream oFile = new FileOutputStream(newSqlFile, false);

        oFile.write(tableEndSql.toString().getBytes("utf-8"));

        oFile.flush();

        oFile.close();
    }


    private void mkdirNext(File file0, String newFile) throws IOException {

        if (!file0.exists()) {
            file0.createNewFile();
        } else {
            File file2 = new File(newFile + "." + System.currentTimeMillis());
            file0.renameTo(file2);
        }

    }


    private String fx(String tableName, String tbaleCandrop, String tbaleRule, String tbaleSplit,
            Map<String, String> sqlMap) {

        StringBuffer sb = new StringBuffer();

        sb.append("/******************** Add Table: " + tableName + " S ************************/\n");

        String tableAllSql = sqlMap.get(tableName);

        if (tbaleRule == null || tbaleRule.length() == 0 || tbaleRule.indexOf("-") == -1) {
            sb.append(tableAllSql);
        } else {

            String[] split = tbaleRule.split("-");
            int startIndex = Integer.parseInt(split[0]);
            int endIndex = Integer.parseInt(split[1]);

            boolean tbalecandrop = (tbaleCandrop != null && tbaleCandrop.equals("true"));

            for (int i = startIndex; i <= endIndex; i++) {

                String newTableName = tableName + tbaleSplit + i;
                String replaceTableName = (" " + tableName + " ");

                if (tbalecandrop) {
                    sb.append("DROP TABLE IF EXISTS `" + newTableName + "`;\n");
                }
                sb.append("CREATE TABLE " + newTableName + "\n");

                String replaceAll = tableAllSql.replaceAll(replaceTableName, " " + newTableName + " ");

                sb.append(replaceAll + "\n");

            }
        }

        return sb.toString() + "/******************** Add Table: " + tableName
                + " E ************************/\n\n\n";
    }


    public static void main(String[] args) {
        String x = "ALTER TABLE mymq_data_index_car COMMENT = '数据索引表_car';";
        String replaceAll = x.replaceAll(" mymq_data_index_car ", "mymq_data_index_car_1");
        System.out.println(x);
        System.out.println(replaceAll);
    }
}
