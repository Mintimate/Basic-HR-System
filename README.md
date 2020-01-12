# 引言
本文章，主要是作为Java小型项目演示--人事管理系统
<font color="#FD0A7C">（因为现在很少人用Java打界面，所以界面等未完全规划，仅为一次Java入门项目演示）</font>  
**更多内容**以及想**捐赠**我的，请访问:https://www.mintimate.cn

# 需求分析
为了方便用户对Sql表进行可视化增、删、改、查、导入、导出。设计改系统进行可视化操作；


# 项目结构
| 数据库       | 媒介           | 工具类         | 特殊\-Excel导入Mysql | UI界面          |
|-----------|--------------|-------------|------------------|---------------|
| CommonDao | Department   | browser     | InExcel          | MainFrame     |
| EmpDao    | Employee     | ExportExcel | Read\\\_Excel    | Refind        |
| UserDao   | salary       | DBOut       | Read\\\_Mysql    | RegisterFrame |
| User      | Md5          | SQLselect   | Result           |
|           | Print\_Error |             |                  |               |
|           | Check\_Image |             |                  |               |


# 调用包
> java jdk-8.jar
>poi-3.7.jar
>mysql-connector-java-8.0.15.jar
>log4j-1.2.17.jar
>commons-logging-1.2.jar
>jxl-2.6.12.jar

## 数据库实现
> CommonDao
>
>> 数据库连接功能
>
> EmpDao
>
>> 数据库Emp表的增、删、改、查
>
> UserDao
>> 数据库User表的增、删、改、查

## 工具类实现
>browser
>> 打开外链(我用来打开“开发者文档”/“帮助文档”)
>
> DBOut
>> 数据库sql表导出为Excel表格
>
> ExportExcel
>> 数据库部分sql表导出为Excel表格
>
> Md5
>> Md5加密类，传入String类型字符串，返回改加密后内容
>
> Print_Error
>> 日志文件、异常报错输出到日志文件
>
> Check_Image
>> 登入图形验证码核心实例代码

## UI界面实现
> MainFrame
>> 主要窗口(登入)
>
> Refind
>> 找回密码页面
>
> RegisterFrame
>> 注册账户、用户权限建立
>
> SQLselect
>> 管理员模糊查询界面
>
>SQLselect2
>> 普通用户模糊查询界面

# 注册/登陆
## 权限分级
根据用户不同需求分为：
>管理员
>> 可以对数据库表进行增、删、改、查、**导出、导入**
>
>普通用户
>>仅能对数据库进行查、**导出**

## 用户登陆
> 使用工具类-Md5对密码进行加密传输
**为了防止来自网络的“字典爆破”，对密码采取Md5加密**

> 为了防止多用户恶意多次无效请求造成服务器负载，对登陆用户**采取图形验证码**

## 用户注册
>注册时，可以根据用户的需求，注册不同用户权限的账户

>注册时填写的密保钥匙用于找回密码(重置密码)

# 日志功能
针对用户不同操作，如：用户尝试登陆、用户密码错误等操作，进行日志输出功能，日志文件存储在**keyservice.log**文件内
## 额外功能
对可能存在的异常报错，输出至**keyservice.log**文件内，方便后期程序调试。

# 操作功能
## 查询、删除功能
查找，针对用户提供的关键字，对全表进行检录；并提供删除功能

## 修改功能
针对用户选中的数据，用户可在**修改**页面进行选定项内部内容修改

## 添加功能
用户可在**添加**页面添加数据内容

## 导入功能
管理员用户可以选择一个和数据库表头相同的xls、xlsx表文件进行导入操作

## 导出功能
用户可以导出当前查询的结果为xls表格

# 部分功能代码实现

## 用户密码MD5加密
> 调用security.MessageDigest类

```
public static String Md5(String str){
    String Md=null;
    try {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(str.getBytes());
        Md=toHex(messageDigest.digest());
    } catch (Exception e) {
        e.printStackTrace();
    }
    return Md;
}
private static String toHex(byte buffer[]) {
    StringBuffer sb = new StringBuffer(buffer.length * 2);
    for (int i = 0; i < buffer.length; i++) {
        sb.append(Character.forDigit((buffer[i] & 240) >> 4, 16));
        sb.append(Character.forDigit(buffer[i] & 15, 16));
    }

    return sb.toString();
}
```

## 外链跳转
```
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//import static com.Mintimate.tool.Print_Error.getTrace;

public class browser {
    public static Log log = LogFactory.getLog(browser.class);
    public static void show(String url) {
        try {
            java.net.URI uri = java.net.URI.create(url);
            // 获取当前系统桌面扩展
            java.awt.Desktop dp = java.awt.Desktop.getDesktop();
            // 判断系统桌面是否支持要执行的功能
            if (dp.isSupported(java.awt.Desktop.Action.BROWSE)) {
                //File file = new File("D:\\aa.txt");
                //dp.edit(file);// 　编辑文件
                dp.browse(uri);// 获取系统默认浏览器打开链接
                // dp.open(file);// 用默认方式打开文件
                // dp.print(file);// 用打印机打印文件
            }
        } catch (java.lang.NullPointerException e) {
            // 此为uri为空时抛出异常
//            e.printStackTrace();
            log.error(getTrace(e));

        } catch (java.io.IOException e) {
            // 此为无法获取系统默认浏览器
            e.printStackTrace();
        }
    }
}
```
## Mysql表格转Excel(部分表格导出)
> 需要依赖apache.poi.jar

## Mysql表转Excel(全表格导出)
> 调用poi2.7jar外部包

```
public class DBOut {
    public final static String url = "jdbc:mysql://？？"; // 数据库URL
    public final static String user = "？？"; // 数据库用户名
    public final static String password = "？？"; // 数据库密码
    private static String printfdata;


    public static void PrintExcel(String Excel){ //打印Excel表
        printfdata=Excel;
        try {
            DBOut.printfdata();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    // 把数据库单张表信息导入到Excel表中

    private static void  printfdata() throws Exception {
        Class.forName("？？");        // 数据库驱动

        // 连接数据库
        Connection con = (Connection) DriverManager.getConnection(url, user,
                password);
        // 创建Excel表。
        Workbook book = new HSSFWorkbook();
        String Table_Name = printfdata;//表名
        // 在当前Excel创建一个子表
        Sheet sheet = book.createSheet(Table_Name);

        Statement st = (Statement) con.createStatement();
        // 创建sql语句，对team进行查询所有数据
        String sql = "select * from ？？." + Table_Name;//？？为数据库名字
        ResultSet rs = st.executeQuery(sql);
        // 设置表头信息（写入Excel左上角是从(0,0)开始的）
        Row row1 = sheet.createRow(0);
        ResultSetMetaData rsmd = rs.getMetaData();
        int colnum = rsmd.getColumnCount();
        for (int i = 1; i <= colnum; i++) {
            String name = rsmd.getColumnName(i);
            // 单元格
            Cell cell = row1.createCell(i - 1);
            // 写入数据
            cell.setCellValue(name);
        }
        // 设置表格信息
        int idx = 1;
        while (rs.next()) {
            // 行
            Row row = sheet.createRow(idx++);
            for (int i = 1; i <= colnum; i++) {
                String str = rs.getString(i);
                // 单元格
                Cell cell = row.createCell(i - 1);
                // 写入数据
                cell.setCellValue(str);
            }
        }
        // 保存
        File file = new File(printfdata+".xls");
        book.write(new FileOutputStream(file));

    }
}
```
使用：
比如打印数据库内user表，只需要
```
DBOut.PrintExcel("user");
```

## Excel导入Mysql
>InExcel
>>主要操作类，将Excel生成的二维表导入Mysql
>
>Read_Excel
>>读取选定Excel表，生成二维表
>
>Read_Mysql
>>读取Mysql数据，查询是否可以插入
>
>Result
>>返回结果，是否成功

## 图形验证码实现
> Check_Image
>> 使用随机线段和随机颜色，在String库中，挑选4个字符，生成图片

```
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Check_Image {
    private static final char[] chars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
            'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' }; // 字符数量
    private static final int SIZE = 4; 
    // 干扰线数量
    private static final int LINES = 5; 
    // 宽度
    private static final int WIDTH = 80; 
    // 高度
    private static final int HEIGHT = 40; 
    // 字体大小
    private static final int FONT_SIZE = 35;

    /** * 生成随机验证码及图片 */
    public static Object[] createImage() {
        StringBuffer sb = new StringBuffer(); // 1.创建空白图片
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB); // 2.获取图片画笔
        Graphics graphic = image.getGraphics(); // 3.设置画笔颜色
        graphic.setColor(Color.LIGHT_GRAY); // 4.绘制矩形背景
        graphic.fillRect(0, 0, WIDTH, HEIGHT);
        // 5.画随机字符
        Random ran = new Random();
        for (int i = 0; i < SIZE; i++) {
            // 取随机字符索引
            int n = ran.nextInt(chars.length);
            // 设置随机颜色
            graphic.setColor(getRandomColor());
            // 设置字体大小
            graphic.setFont(new Font(null, Font.BOLD + Font.ITALIC, FONT_SIZE));
            // 画字符
            graphic.drawString(chars[n] + "", i * WIDTH / SIZE, HEIGHT);
            // 记录字符
            sb.append(chars[n]);
        } // 6.画干扰线
        for (int i = 0; i < LINES; i++) { // 设置随机颜色
            graphic.setColor(getRandomColor()); // 随机画线
            graphic.drawLine(ran.nextInt(WIDTH), ran.nextInt(HEIGHT), ran.nextInt(WIDTH), ran.nextInt(HEIGHT));
        }
        // 7.返回验证码和图片
        return new Object[] { sb.toString(), image };
    }

    /** * 随机取色 */
    public static Color getRandomColor() {
        Random ran = new Random();
        Color color = new Color(ran.nextInt(256), ran.nextInt(256), ran.nextInt(256));
        return color;
    }
```

# 安全实现
## 数据库内容备份
每日24点，由服务器自动备份数据库文件

## 用户密码加密
用户密码Md5加密，防止“字典爆破”

# 规划
## B/S化结构
计划实现B/S项目结构，使用SpringBoot+MyBatis，目前没有打算MVC结构

## MongoDB
MongoDB是基于分布式文件存储的数据库，为WEB应用提供可扩展的高性能数据存储解决方案；
为了预防用户使用过程中多次读写，使用MongoDB对数据进行缓冲。
