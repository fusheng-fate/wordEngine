# word模板引擎

市面上常见的开源的word模板引擎主要有以下几种: **Apache POI**、**Freemarker**、**OpenOffice**、**Poi-tl**、**docx4j**、**spire.doc for java**。

| 方案               | 移植性           | 功能性                                                  | 易用性                                                       |
| ------------------ | ---------------- | ------------------------------------------------------- | ------------------------------------------------------------ |
| Apache POI         | Java跨平台       | Apache项目，封装了常见的文档操作，也可以操作底层XML结构 | 文档不全，开发难度大，最终生成效果不好                       |
| Freemarker         | XML跨平台        | 仅支持文本，有局限性                                    | 不推荐，XML结构的代码几乎无法维护                            |
| OpenOffice         | 需部署OpenOffice | 不支持图表                                              | 需要了解OpenOffice的API                                      |
| **Poi-tl**         | **Java跨平台**   | **Word模板引擎，基于Apache POI，提供更友好的API**       | **低代码，文档易读性高，开发简单，社区活跃，更新迭代比较快** |
| docx4j             | 依赖office       | word模板引擎                                            | 生成效果不好,开发难度大                                      |
| spire.doc for java | java跨平台       | word模板引擎，word转pdf                                 | 开源版本有局限性                                             |



<hr><span style = "color:blue">综上对比之下，决定使用Poi-tl</span>


## Poi-tl

Poi-tl 支持 文本、段落、列表、表格、表格行循环、表格列循环、复杂动态表格、图表(单系列图表、多系列图表)、区块对、嵌套文档等等。

poi-tl 的核心是插件，依靠插件可以实现各种word文档元素，支持自定义插件。

### Licence

**Apache License 2.0**

Apache License 2.0协议来自于著名的Apache基金会，其最重要的开源软件就是Apache（HTTPD）网站服务器。

Apache License协议和BSD类似，同样鼓励代码共享和尊重原作者的著作权，同样允许代码修改，再发布（作为开源或商业软件）。需要满足的条件也和BSD类似：

1. 需要给代码的用户一份Apache Licence
2. 如果你修改了代码，需要在被修改的文件中说明
3. 在延伸的代码中（修改和有源代码衍生的代码中）需要带有原来代码中的协议，商标，专利声明和其他原来作者规定需要包含的说明。
4. 如果再发布的产品中包含一个Notice文件，则在Notice文件中需要带有Apache Licence。你可以在Notice中增加自己的许可，但不可以表现为对Apache Licence构成更改。

Apache Licence也是对商业应用友好的许可。使用者也可以在需要的时候修改代码来满足需要并作为开源或商业产品发布/销售。著名的Android系统，Apache基金会的众多开源项目，Swift项目等，都是用Apache Lincese 2.0协议。

使用Apache Licence 2.0协议的好处是:

- 永久权利，一旦被授权，永久拥有。
- 全球范围的权利，在一个国家获得授权，适用于所有国家。假如你在美国，许可是从印度授权的，也没有问题。
- 授权免费无版税， 前期、后期均无任何费用。
- 授权无排他性，任何人都可以获得授权
- 授权不可撤消，一旦获得授权，没有任何人可以取消。比如，你基于该产品代码开发了衍生产品，你不用担心会在某一天被禁止使用该代码。

### 版本对应关系

| Poi-tl | Apache POI | java     |
| ------ | ---------- | -------- |
| 1.12.1 | 5.2.2+     | jdk 1.8+ |

### Maven

```xml
<dependency>
  <groupId>com.deepoove</groupId>
  <artifactId>poi-tl</artifactId>
  <version>1.12.1</version>
</dependency>
```

> NOTE: poi-tl `1.12.x` requires POI version `5.2.2+`.

## 标签
标签由两个花括号组成，` {{title}} `是一个标签，`{{?Title}}` 也是一个标签，Title是标签的名称，'?'标识标签的类型。接下来，让我们看看有哪些标记类型。

### 文本标签
文本标签是Word模板中最基本的标签类型。{{name}} 将被数据模型中键 'name' 的值所替换。如果键不存在，标记将被清除(程序可以配置是保留标记还是抛出异常)。文本标记的样式将应用于被替换的文本，如下面的示例所示。

**Code**:

```java
XWPFTemplate template = XWPFTemplate.compile(PathUtils.getWordTemplateTextUrl("hello_poi_tl.docx")).render(
                new HashMap<String, Object>(){{
                    put("title", "Hi, poi-tl Word模板引擎");
                }});
        template.writeAndClose(new FileOutputStream("target/output.docx"));
```

**Template**:

```
{{title}}
```

**OutPut**:

```
Hi, poi-tl Word模板引擎
```

### 段落标签

本质上段落和文本是没有区别的，也是使用`{{}}`表示。使用ParagraphRenderPolicy插件可以设置段落的样式。

**Code**:

```java
ParagraphRenderData para = Paragraphs.of().addText("\t段落标签不是一种默认支持的策略,所以需要")
                .addText(Texts.of("手动").color("0000FF").bold().create())
                .addText(Texts.of("配置 ").color("FF0000").sup().create())        
             .addPicture(Pictures.ofLocal(PathUtils.getWordTemplateImageUrl("earth.png"))
                												.size(40, 40).create())
              .addText(Texts.of(" poi-tl").link("http://deepoove.com/poi-tl").create())
    		  .addText(".\n end!").create();
Configure config = Configure.builder().bind("paragraph", new ParagraphRenderPolicy())
 .build();
HashMap<String,Object> data = new HashMap<>();
data.put("paragrapgh", para);
XWPFTemplate.compile(PathUtils.getWordTemplateTextUrl("render_paragraph.docx"),config)
    .render(data).writeToFile("target/out_render_paragraph.docx");
```

**Template**:

```
{{paragrapgh}}
```

**OutPut**:

```javascript
	段落标签不是一种默认支持的策略,所以需要手动配置

	段落标签不是一种默认支持的策略,所以需要手动配置
```


### 图片标签
图像标签以`@`开头，例如，“{{@logo}}”将在数据模型中查找键为“logo”的值，然后将标签替换为图像。与图像标记相对应的数据可以是简单的URL或Path字符串，也可以是包含图像宽度和高度的结构。

**Code**:

```java
// 设置图片宽高
put("logo", Pictures.ofLocal("logo.png").size(120, 120).create());

// 图片流
put("streamImg", Pictures.ofStream(new FileInputStream("logo.jpeg"), PictureType.JPEG)
  .size(100, 120).create());

// 网络图片(注意网络耗时对系统可能的性能影响)
put("urlImg", Pictures.ofUrl("http://deepoove.com/images/icecream.png")
  .size(100, 100).create());

// java图片
put("buffered", Pictures.ofBufferedImage(bufferImage, PictureType.PNG)
  .size(100, 100).create());
```

**Template**:

```java
{{@logo}}
```

### 表格标签
表格标签是以 `#`开头, 例如 `{{#table}}`, 它将呈现为一个有N行N列的Word表。N的值取决于“table”标签的数据。

**Code**:

```java
put("table", Tables.of(new String[][] {
                new String[] { "Song name", "Artist" }
    			new String[] { "Song name", "Artist" }
            }).border(BorderStyle.DEFAULT).create());
```

**Template**:

```
{{#table}}
```

**Output**:

| Song name | Artist |
| --------- | ------ |
| Song name | Artist |

### 列表标签

列表标签对应于Word的符号列表或编号列表，以`*`开头，例如`{{*number}}`。

**Code**:

```java
put("list", Numberings.create("Plug-in grammar",
                  "Supports word text, pictures, table...",
                  "Template, not just template, but also style template"));
```

**Template**:

```
{{*list}}
```

**Output**:

```
● Plug-in grammar
● Supports word text, pictures, table...
● Templates, not just templates, but also style templates
```

### 区块标签
一个区块对由前后两个标签组成，标签由`?`开头，结束标记用`/`标识，例如`{{?Section}}` 作为sections块的开始标签，`{{/ Section}}`是结束标签，`Section`是这个Section的名称。
区段在处理一系列文档元素时非常有用。section中的文档元素(文本、图片、表格等)可以呈现0次、1次或N次，具体取决于section的值。

#### False或者空集合
如果区块对的值是 null 、false 或者空的集合，位于区块中的所有文档元素将不会显示，这就等同于if语句的条件为 false。


data-model:
```json
{
  "announce": false
}
```

Template:

```
Made it,Ma!{{?announce}}Top of the world!{{/announce}}
Made it,Ma!
{{?announce}}
Top of the world!🎋
{{/announce}}
```

Output:

```
Made it,Ma!
Made it,Ma!
```
#### 非False且不是集合

如果区块对的值不为 null 、 false ，且不是集合，位于区块中的所有文档元素会被渲染一次，这就等同于if语句的条件为 true。

data-model
```java
{
  "person": { "name": "Sayi" }
}
```
Template
```java
template.docx
{{?person}}

Hi {{name}}!

{{/person}}
```

Output:

```java
output.docx
Hi Sayi!
```

#### 非空集合
如果区块对的值是一个非空集合，区块中的文档元素会被迭代渲染一次或者N次，这取决于集合的大小，类似于foreach语法。

data-model

```java
{
  "songs": [
    { "name": "Memories" },
    { "name": "Sugar" },
    { "name": "Last Dance" }
  ]
}
```

Template
```java
template.docx
{{?songs}}

{{name}}

{{/songs}}
```
Output

```java
output.docx
Memories

Sugar

Last Dance
```

### 嵌套标签

嵌套又称为导入、包含或者合并，以+标识：{{+var}}，嵌套标签除了导入的功能外，最大的好处就是当我们开发一个章节偏多的word文档时，可能每个章节对应的业务不一，这时候就可以多人协作，各自负责熟悉的章节，最终再合并为一个文档。

```java
class AddrModel {
  private String addr;
  public AddrModel(String addr) {
    this.addr = addr;
  }
  // Getter/Setter
}

List<AddrModel> subData = new ArrayList<>();
subData.add(new AddrModel("Hangzhou,China"));
subData.add(new AddrModel("Shanghai,China"));
put("nested", Includes.ofLocal("sub.docx").setRenderModel(subData).create());
//主模板包含嵌套标签{{+nested}}
//sub.docx是一个包含了{{addr}}的子模板，使用subData集合渲染后合并到主模板
```



### 引用标签

引用标签是一种特殊位置的特殊标签，提供了直接引用文档中的元素句柄的能力，这个重要的特性在我们只想改变文档中某个元素极小一部分样式和属性的时候特别有用，因为其余样式和属性都可以在模板中预置好，真正的所见即所得。

引用标签是指可以将引用对象 元素替换，但是保留被替换元素的格式。

### 图表

图表，我们使用过aspose word, 所以这里简单对比一下，相较于aspose word来说，使用poi-tl不需要根据画图表，而是根据模板中的预设图表进行替换，不会改变模板中预设图表的结构。
相对而言，代码变的更加简单了。(简易demo参照 test/example/ChartExample)  ChartSingleSeriesRenderData(单系列图表)  ChartMultiSeriesRenderData(多系列图表)

#### 饼图
Charts.ofPie

#### 条形图

Charts.ofArea  Charts.ofBar

#### 柱形图

Charts.ofArea  Charts.ofBar

#### 折线图

Charts.ofArea  Charts.ofBar


### 配置

#### spring el表达式

https://docs.spring.io/spring-framework/docs/5.3.18/reference/html/core.html#expressions

### 常用插件

poi-tl常用的策略插件，用来处理文本、段落、图片、列表、表格、引用多系列图表、引用单系列图表、循环表格行、循环表格列、动态表格、引用图表插件等：

- TextRenderPolicy
- ParagraphRenderPolicy
- PictureRenderPolicy
- NumberingRenderPolicy
- TableRenderPolicy
- MultiSeriesChartTemplateRenderPolicy
- SingleSeriesChartTemplateRenderPolicy
- LoopRowTableRenderPolicy
- LoopColumnTableRenderPolicy
- DynamicTableRenderPolicy
- AbstractChartTemplateRenderPolicy




















