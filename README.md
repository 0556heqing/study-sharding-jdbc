# sharding-jdbc 参数配置

## 参数配置详解
````
spring:
  shardingsphere:
    datasource:
      names: ds0, ds1 # 数据源名称，多数据源以逗号分隔
      ds0:
        type: # 数据库连接池类名称
        driver-class-name: com.mysql.cj.jdbc.Driver # 数据库驱动类名
        url: jdbc:mysql://localhost:3306/ds0?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&characterEncoding=utf8 # 数据库url连接
        username: root # 数据库用户名
        password: root # 数据库密码
        xxx: #数据库连接池的其它属性
      ds1:
        type:
          driver-class-name: com.mysql.cj.jdbc.Driver
          url: jdbc:mysql://localhost:3306/ds1?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&characterEncoding=utf8
          username: root
          password: root
    config:
      props:
        sql:
          show: true # 是否开启SQL显示，默认值: false
          executor:
            size: # 工作线程数量，默认值: CPU核数
          check:
            table:
              metadata:
                enabled: # 是否在启动时检查分表元数据一致性，默认值: false
      sharding:
        binding-tables: # 绑定表规则列表1
        broadcast-tables: #广播表规则列表1
        default-data-source-name: # 未配置分片规则的表将通过默认数据源定位
        default-database-strategy: # 默认数据库分片策略，同分库策略
          inline:
            sharding-column: user_id
            algorithm-expression: ds$->{user_id % 2}
        default-table-strategy: # 默认表分片策略，同分表策略
        default-key-generator-class-name: # 默认自增列值生成器类名称，缺省使用io.shardingsphere.core.keygen.DefaultKeyGenerator。该类需实现KeyGenerator接口并提供无参数的构造器
        tables: # 数据分片
          t_order:
            # 由数据源名 + 表名组成，以小数点分隔。多个表以逗号分隔，支持inline表达式。缺省表示使用已知数据源与逻辑表名称生成数据节点。用于广播表（即每个库中都需要一个同样的表用于关联查询，多为字典表）或只分库不分表且所有库的表结构完全一致的情况
            actual-data-nodes: ds$->{0..1}.t_order$->{0..1}
            database-strategy: # 分库策略 4选一
              standard: # 用于单分片键的标准分片场景
                sharding-column: # 分片列名称
                precise-algorithm-class-name: # 精确分片算法类名称，用于=和IN。该类需实现PreciseShardingAlgorithm接口并提供无参数的构造器
                range-algorithm-class-name: # 范围分片算法类名称，用于BETWEEN，可选。该类需实现RangeShardingAlgorithm接口并提供无参数的构造器
              complex: # 用于多分片键的复合分片场景
                sharding-columns: # 分片列名称，多个列以逗号分隔
                algorithm-class-name: # 复合分片算法类名称。该类需实现ComplexKeysShardingAlgorithm接口并提供无参数的构造器
              inline: # 行表达式分片策略
                sharding-column: # 分片列名称
                algorithm-expression: # 分片算法行表达式，需符合groovy语法
              hint: # Hint分片策略
                algorithm-class-name: # Hint分片算法类名称。该类需实现HintShardingAlgorithm接口并提供无参数的构造器
            table-strategy: # 分表策略
              standard: # 用于单分片键的标准分片场景
                sharding-column: # 分片列名称
                precise-algorithm-class-name: # 精确分片算法类名称，用于=和IN。该类需实现PreciseShardingAlgorithm接口并提供无参数的构造器
                range-algorithm-class-name: # 范围分片算法类名称，用于BETWEEN，可选。该类需实现RangeShardingAlgorithm接口并提供无参数的构造器
                complex: # 用于多分片键的复合分片场景
                  sharding-columns: # 分片列名称，多个列以逗号分隔
                  algorithm-class-name: # 复合分片算法类名称。该类需实现ComplexKeysShardingAlgorithm接口并提供无参数的构造器
                inline: # 行表达式分片策略
                  sharding-column: # 分片列名称
                  algorithm-expression: # 分片算法行表达式，需符合groovy语法
                hint: # Hint分片策略
                  algorithm-class-name: # Hint分片算法类名称。该类需实现HintShardingAlgorithm接口并提供无参数的构造器
            key-generator-column-name: # 自增列名称，缺省表示不使用自增主键生成器
            key-generator-class-name: # 自增列值生成器类名称，缺省表示使用默认自增列值生成器。该类需提供无参数的构造器
            logic-index: # 逻辑索引名称，对于分表的Oracle/PostgreSQL数据库中DROP INDEX XXX语句，需要通过配置逻辑索引名称定位所执行SQL的真实分表
          t_order_item:
            # 由数据源名 + 表名组成，以小数点分隔。多个表以逗号分隔，支持inline表达式。缺省表示使用已知数据源与逻辑表名称生成数据节点。用于广播表（即每个库中都需要一个同样的表用于关联查询，多为字典表）或只分库不分表且所有库的表结构完全一致的情况
            actual-data-nodes: ds$->{0..1}.t_order$->{0..1}
            database-strategy: # 分库策略
              standard: # 用于单分片键的标准分片场景
                sharding-column: # 分片列名称
                precise-algorithm-class-name: # 精确分片算法类名称，用于=和IN。该类需实现PreciseShardingAlgorithm接口并提供无参数的构造器
                range-algorithm-class-name: # 范围分片算法类名称，用于BETWEEN，可选。该类需实现RangeShardingAlgorithm接口并提供无参数的构造器
              complex: # 用于多分片键的复合分片场景
                sharding-columns: # 分片列名称，多个列以逗号分隔
                algorithm-class-name: # 复合分片算法类名称。该类需实现ComplexKeysShardingAlgorithm接口并提供无参数的构造器
              inline: # 行表达式分片策略
                sharding-column: # 分片列名称
                algorithm-expression: # 分片算法行表达式，需符合groovy语法
              hint: # Hint分片策略
                algorithm-class-name: # Hint分片算法类名称。该类需实现HintShardingAlgorithm接口并提供无参数的构造器
            table-strategy: # 分表策略
              standard: # 用于单分片键的标准分片场景
                sharding-column: # 分片列名称
                precise-algorithm-class-name: # 精确分片算法类名称，用于=和IN。该类需实现PreciseShardingAlgorithm接口并提供无参数的构造器
                range-algorithm-class-name: # 范围分片算法类名称，用于BETWEEN，可选。该类需实现RangeShardingAlgorithm接口并提供无参数的构造器
                complex: # 用于多分片键的复合分片场景
                  sharding-columns: # 分片列名称，多个列以逗号分隔
                  algorithm-class-name: # 复合分片算法类名称。该类需实现ComplexKeysShardingAlgorithm接口并提供无参数的构造器
                inline: # 行表达式分片策略
                  sharding-column: # 分片列名称
                  algorithm-expression: # 分片算法行表达式，需符合groovy语法
                hint: # Hint分片策略
                  algorithm-class-name: # Hint分片算法类名称。该类需实现HintShardingAlgorithm接口并提供无参数的构造器
        master-slave-rules: # 读写分离
          ds0: # 主从数据源的名称
            master-data-source-name: # 主库数据源名称
            slave-data-source-names: # 从库数据源名称列表
            load-balance-algorithm-class-name: # 从库负载均衡算法类名称。该类需实现MasterSlaveLoadBalanceAlgorithm接口且提供无参数构造器
            load-balance-algorithm-type: # 从库负载均衡算法类型，可选值：ROUND_ROBIN，RANDOM。若`load-balance-algorithm-class-name`存在则忽略该配置
          ds1: # 主从数据源的名称
            master-data-source-name: # 主库数据源名称
            slave-data-source-names: # 从库数据源名称列表
            load-balance-algorithm-class-name: # 从库负载均衡算法类名称。该类需实现MasterSlaveLoadBalanceAlgorithm接口且提供无参数构造器
            load-balance-algorithm-type: # 从库负载均衡算法类型，可选值：ROUND_ROBIN，RANDOM。若`load-balance-algorithm-class-name`存在则忽略该配置
          map:
            key1: # 用户自定义配置 Key1为用户填
            key2: # 用户自定义配置
        orchestration: # 数据治理
          name: # 数据治理实例名称
          overwrite: # 本地配置是否覆盖注册中心配置。如果可覆盖，每次启动都以本地配置为准
          registry:
            server-lists: # 连接注册中心服务器的列表。包括IP地址和端口号。多个地址用逗号分隔。如: host1:2181,host2:2181
            namespace: # 注册中心的命名空间
            digest: # 连接注册中心的权限令牌。缺省为不需要权限验证
            operation-timeout-milliseconds: # 操作超时的毫秒数，默认500毫秒
            max-retries: # 连接失败后的最大重试次数，默认3次
            retry-interval-milliseconds: # 重试间隔毫秒数，默认500毫秒
            time-to-live-seconds: # 临时节点存活秒数，默认60秒
````

## 同库分表
````
spring:
  shardingsphere:
    datasource:
      # 设置两个数据库
      names: ds0
      # 第一个数据库的配置信息
      ds0:
        type: com.alibaba.druid.pool.DruidDataSource
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://localhost:3306/follow?allowMultiQueries=true&autoReconnect=true&useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai
        username: root
        password: 246512
    sharding:
      # 分表配置
      tables:
        # 表名
        person:
          # 设置为2个库1张表。。。1库n表：ds.t_$->{0..n} ; n库1表：ds$->{0..n}.t ; n库n表：ds$->{0..n}.t_$->{0..n}
          actual-data-nodes: ds0.person_$->{0..2}
          table-strategy:
            inline:
              sharding-column: id
              algorithm-expression: person_$->{id % 3}
          key-generator:
            column: id
            type: SNOWFLAKE
      # 默认使用第一个数据库
      defaultDataSourceName: ds0
    props:
      # 是否打印sql语句
      sql.show: true
````

## 分库不分表
````
spring:
  shardingsphere:
    datasource:
      # 设置两个数据库
      names: ds0,ds1
      # 第一个数据库的配置信息
      ds0:
        type: com.alibaba.druid.pool.DruidDataSource
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://localhost:3306/follow?allowMultiQueries=true&autoReconnect=true&useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai
        username: root
        password: 246512
      # 第二个数据库的配置信息
      ds1:
        type: com.alibaba.druid.pool.DruidDataSource
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://localhost:3306/demo?allowMultiQueries=true&autoReconnect=true&useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai
        username: root
        password: 246512
    sharding:
      # 分库配置
      default-database-strategy:
        sharding-column: id
        algorithm-expression: ds$->{id % 2}
        # 自定义分片策略
        standard:
          sharding-column: id
          precise-algorithm-class-name: com.heqing.sharding.jdbc.algorithm.MyPreciseShardingAlgorithm
      # 分表配置
      tables:
        # 表名
        person:
          # 设置为2个库1张表。。。1库n表：ds.t_$->{0..n} ; n库1表：ds$->{0..n}.t ; n库n表：ds$->{0..n}.t_$->{0..n}
          actual-data-nodes: ds$->{0..1}.person
          table-strategy:
            inline:
              sharding-column: id
              algorithm-expression: person
          key-generator:
            column: id
            type: SNOWFLAKE
      # 默认使用第一个数据库
      defaultDataSourceName: ds0
    props:
      # 是否打印sql语句
      sql.show: true
````

## 分库分表
````
spring:
  shardingsphere:
    datasource:
      # 设置两个数据库
      names: ds0,ds1
      # 第一个数据库的配置信息
      ds0:
        type: com.alibaba.druid.pool.DruidDataSource
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://localhost:3306/follow?allowMultiQueries=true&autoReconnect=true&useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai
        username: root
        password: 246512
      # 第二个数据库的配置信息
      ds1:
        type: com.alibaba.druid.pool.DruidDataSource
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://localhost:3306/demo?allowMultiQueries=true&autoReconnect=true&useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai
        username: root
        password: 246512
    sharding:
      # 分库配置
      default-database-strategy:
        sharding-column: id
        algorithm-expression: ds$->{id % 2}
        # 自定义分片策略
        standard:
          sharding-column: id
          precise-algorithm-class-name: com.heqing.sharding.jdbc.algorithm.MyPreciseShardingAlgorithm
      # 分表配置
      tables:
        # 表名
        person:
          # 设置为2个库1张表。。。1库n表：ds.t_$->{0..n} ; n库1表：ds$->{0..n}.t ; n库n表：ds$->{0..n}.t_$->{0..n}
          actual-data-nodes: ds$->{0..1}.person_$->{0..2}
          table-strategy:
            inline:
              sharding-column: id
              algorithm-expression: person_$->{id % 3}
          key-generator:
            column: id
            type: SNOWFLAKE
      # 默认使用第一个数据库
      defaultDataSourceName: ds0
    props:
      # 是否打印sql语句
      sql.show: true
````

## 读写分离
````
# 读写分离
spring.shardingsphere.sharding.master-slave-rules.ds0.master-data-source-name=ds0
spring.shardingsphere.sharding.master-slave-rules.ds0.slave-data-source-names=ds0slave
````

# sharding-jdbc 相关文章
[参考代码？](https://github.com/yinjihuan/sharding-jdbc)

[Sharding-JDBC:查询量大如何优化？](https://mp.weixin.qq.com/s/kp2lJHpTMz4bDWkJYjVbOQ)

[Sharding-JDBC:垂直拆分怎么做？](https://mp.weixin.qq.com/s/wl8h6LIQUHztVuVbjfsU3Q)

[Sharding-JDBC:单库分表的实现](http://cxytiandi.com/blog/detail/36400)
