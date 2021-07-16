package com.cxy35.sample.springboot.mybatis.mybatisplusmulti.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author cxy35
 * @date 2021/1/20 15:59
 */
@Configuration
@MapperScan("com.cxy35.sample.springboot.mybatis.mybatisplus.mapper")
public class MyBatisPlusConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        // 配置分页查询
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        paginationInnerInterceptor.setDbType(DbType.MYSQL);
        // 设置请求的页面大于最大页后操作，true调回到首页，false 继续请求，默认false
        // paginationInnerInterceptor.setOverflow(false);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        // paginationInnerInterceptor.setMaxLimit(500L);
        // 开启 count 的 join 优化，只针对部分 left join
        // paginationInnerInterceptor.setOptimizeJoin(true);
        interceptor.addInnerInterceptor(paginationInnerInterceptor);

        // 配置动态表名
        /*DynamicTableNameInnerInterceptor dynamicTableNameInnerInterceptor = new DynamicTableNameInnerInterceptor();
        HashMap<String, TableNameHandler> map = new HashMap<>();
        // 对于 t_user 表，进行动态表名设置（t_user_1,t_user_2）
        map.put("t_user", (sql, tableName) -> {
            String _ = "_";
            int random = new Random().nextInt(2) + 1;
            return tableName + _ + random; // 若返回null, 则不会进行动态表名替换, 还是会使用 t_user
        });
        dynamicTableNameInnerInterceptor.setTableNameHandlerMap(map);
        interceptor.addInnerInterceptor(dynamicTableNameInnerInterceptor);*/

        return interceptor;
    }
}
