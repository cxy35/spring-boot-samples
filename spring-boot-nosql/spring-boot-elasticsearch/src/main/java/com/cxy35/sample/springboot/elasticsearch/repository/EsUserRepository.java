package com.cxy35.sample.springboot.elasticsearch.repository;

import com.cxy35.sample.springboot.elasticsearch.pojo.EsUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface EsUserRepository extends ElasticsearchRepository<EsUser, Integer> {
    // 方法定义规范：
    // 1.按照 Spring Data 的规范，查询方法以 find/get/read 开头
    // 2.涉及条件查询时，条件的属性用条件关键字连接，要注意的是：条件属性以首字母大写
    // 3.支持属性的级联查询. 若当前类有符合条件的属性, 则优先使用, 而不使用级联属性. 若需要使用级联属性, 则属性之间使用 _ 进行连接

    EsUser findByIdAndUsername(Integer id, String username);

    Page<EsUser> findByIdGreaterThan(Integer id, Pageable pageable);

    List<EsUser> findByIdLessThanOrUsernameContaining(Integer id, String username);

    // 使用 @Query 注解可以用 Elasticsearch 的 DSL 语句进行查询
    @Query("{\"bool\" : {\"must\" : {\"match\" : {\"address\" : \"?0\"}}}}")
    Page<EsUser> findByAddress(String address, Pageable pageable);
}
