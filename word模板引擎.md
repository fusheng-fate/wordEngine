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

