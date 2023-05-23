# word模板引擎

市面上常见的开源的word模板引擎主要有以下几种: **Apache POI**、**Freemarker**、**OpenOffice**、**Poi-tl**、**docx4j**、**spire.doc for java**。

| 方案               | 移植性           | 功能性                                                  | 易用性                                                       |
| ------------------ | ---------------- | ------------------------------------------------------- | ------------------------------------------------------------ |
| Apache POI         | Java跨平台       | Apache项目，封装了常见的文档操作，也可以操作底层XML结构 | 文档不全，开发难度大，最终生成效果不好                       |
| Freemarker         | XML跨平台        | 仅支持文本，有局限性                                    | 不推荐，XML结构的代码几乎无法维护                            |
| OpenOffice         | 需部署OpenOffice |                                                         | 需要了解OpenOffice的API                                      |
| **Poi-tl**         | **Java跨平台**   | **Word模板引擎，基于Apache POI，提供更友好的API**       | **低代码，文档易读性高，开发简单，社区活跃，更新迭代比较快** |
| docx4j             | 依赖office       | word模板引擎                                            | 生成效果不好,开发难度大                                      |
| spire.doc for java | java跨平台       | word模板引擎，word转pdf                                 | 开源版本有局限性                                             |



<hr><span style = "color:blue">综上对比之下，推荐使用Poi-tl</span>

## Poi-tl

### Licence

Apache License 2.0

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
标签由两个花括号组成，' {{title}} '是一个标签，{{?Title}} 也是一个标签，Title是标签的名称，'?'标识标签的类型。接下来，让我们看看有哪些标记类型。

### 文本标签
文本标签是Word模板中最基本的标签类型。{{name}} 将被数据模型中键 'name' 的值所替换。如果键不存在，标记将被清除(程序可以配置是保留标记还是抛出异常)。文本标记的样式将应用于被替换的文本，如下面的示例所示。


### 图片标签
图像标签以`@`开头，例如，“{{@logo}}”将在数据模型中查找键为“logo”的值，然后将标签替换为图像。与图像标记相对应的数据可以是简单的URL或Path字符串，也可以是包含图像宽度和高度的结构。

Code:

```java
// 设置图片宽高
put("image1", Pictures.ofLocal("logo.png").size(120, 120).create());
```

Template:

```java
{{@logo}}
```

### 表格标签
表格标签是以 `#`开头, such as `{{#table}}`, 它将呈现为一个有N行N列的Word表。N的值取决于“table”标签的数据。

Code:

```java
put("table", Tables.of(new String[][] {
                new String[] { "Song name", "Artist" }
            }).border(BorderStyle.DEFAULT).create());
```

Template:

```
{{#table}}
```

### 列表标签
列表标签对应于Word的符号列表或编号列表，以`*`开头，例如`{{*number}}`。

Code:
```java
put("list", Numberings.create("Plug-in grammar",
                  "Supports word text, pictures, table...",
                  "Template, not just template, but also style template"));
```

Template:

```
{{*list}}
```

Output:

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


















