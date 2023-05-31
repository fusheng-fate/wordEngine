# 开源组件对比

docx4j、openoffice、poi、spire.doc、iText、opensagres.xdocreport

| 方案                  | 移植性                                 | 功能                                         | 性能     | 易用性     |
| --------------------- | -------------------------------------- | -------------------------------------------- | -------- | ---------- |
| docx4j                | 依赖jar包                              | 格式有失真，页数有变化                       | 差       | -          |
| poi                   | 依赖jar包                              | 格式有失真，效果不好                         | 较好     | 繁琐       |
| spire.doc             | 不依赖其他组件                         | 开源版本仅支持3页                            | -        | -          |
| **openOffice**        | **依赖jar包，需要安装openOffice服务**  | **相对其他开源组件(但不支持图表)、效果较好** | **较好** | **低代码** |
| opensagres.xdocreport | 依赖jar包                              | 格式失真(不支持图表)、效果差                 | -        | -          |
| **LibreOffice**       | **依赖jar包，需要安装LibreOffice服务** | **相对openOffice(支持图表)、效果较好**       | **较好** | **低代码** |





## OpenOffice

使用openOffice + jodConverter库实现word转pdf


### 安装openOffice

- 官网链接： http://www.openoffice.org/

- 本次测试安装openOffice(windows)版本：**4.1.14**

### 功能流程

1. 启动openOffice服务

```shell
soffice.exe -headless -accept="socket,host=127.0.0.1,port=8100;urp;" -nofirststartwizard
```

2. word转pdf简易demo

   ```java
   public static void wordToPdf(String docFile,String pdfFile) throws ConnectException {
           // 源文件目录
           File inputFile = new File(docFile);
           // 输出文件目录
           File outputFile = new File(pdfFile);
           if (!outputFile.getParentFile().exists()) {
               outputFile.getParentFile().mkdirs();
           }
           // 连接openoffice服务
           OpenOfficeConnection connection = new SocketOpenOfficeConnection(
                   "127.0.0.1", 8100);
           connection.connect();
           // 转换word到pdf
           DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
           converter.convert(inputFile, outputFile);
           // 关闭连接
           connection.disconnect();
       }
   ```

   3. 评测：
      - 优势：
        - 性能好，转换效率比较高
        - 代码简单，可以使用命令进行转换。
      - 缺点：
        - 不支持图表，不支持并发
   

## LibreOffice

  ### 安装LibreOffice

- 官网链接：https://www.libreoffice.org/
- 本次测试安装包(LibreOffice)版本：7.5.3

### 功能流程

1. 启动libreOffice服务

```shell
libreoffice7.5 --headless --accept="socket,host=127.0.0.1,port=8100;urp;" --nofirststartwizard
```

2. word转pdf命令行：
```shell
libreoffice7.5 --invisible --convert-to pdf --outdir  "/root" "/root/testPdf.docx"
```

3. word转pdf demo
```java
public static void wordToPdf(String docFile,String pdfFile) throws ConnectException {
        // 源文件目录
        File inputFile = new File(docFile);
        // 输出文件目录
        File outputFile = new File(pdfFile);
        if (!outputFile.getParentFile().exists()) {
            outputFile.getParentFile().mkdirs();
        }
        // 连接openoffice服务
        OpenOfficeConnection connection = new SocketOpenOfficeConnection(
                "127.0.0.1", 8100);
        connection.connect();
        // 转换word到pdf
        DocumentConverter converter = new StreamOpenOfficeDocumentConverter(connection);
        converter.convert(inputFile, outputFile);
        // 关闭连接
        connection.disconnect();
    }
```

4. 评测：
    - 优势：
        - 性能好，转换效率比较高
        - 代码简单，可以使用命令进行转换。
        - 支持图表，效果相对openOffice 来说要好一点
    - 缺点：
        - 不支持复杂图表，会出现转换完后多页的现象，不影响阅读

   













