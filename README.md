# free_marker
```properties
代码生成器
ui-
   |--   DataBaseUi 是整个程序的进口
         CodeUi 中的main方法可以使用默认的数据库连接，其中的generator()方法是初始化数据的地方
utils-
   |--   DataBaseUtils 自定义的，主要用来获取数据库元数据
         PropertiesUtils用来加载自定义配置文件，指定加载properties目录下所有的.properties配置文件，key不能重复

core-
   |--   Generator代码生成器的核心处理，使用Freemarker完成文件生成，数据模型+模板
         GeneratorFacade接收UI界面传来的参数，调用Generator完成代码生成

entity-
   |--   Table做为表的元数据封装，包含着Column集合
         Column做为列的元数据封装，其中注意columnKey是用来判断该列是否为主键，是的话则==PRI，而不是true（在DataBaseUtils中定义的）
         Settings用来定义包名，项目名，作者等相关信息，UI界面传入数据会自动将数据传送过来
         DataBase与数据库驱动相关，用来连接数据库和获取元数据
```
