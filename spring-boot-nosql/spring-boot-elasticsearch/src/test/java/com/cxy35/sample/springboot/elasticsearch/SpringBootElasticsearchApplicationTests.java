package com.cxy35.sample.springboot.elasticsearch;

import com.cxy35.sample.springboot.elasticsearch.pojo.EsUser;
import com.cxy35.sample.springboot.elasticsearch.repository.EsUserRepository;
import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
class SpringBootElasticsearchApplicationTests {

    @Autowired
    EsUserRepository esUserRepository;
    // 用于自定义复杂查询
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Test
    public void save() {
        EsUser esUser = new EsUser();
        esUser.setId(1);
        esUser.setUsername("zhangsan");
        esUser.setPassword("123456");
        esUser.setAddress("浙江杭州");
        esUser.setNickName("张三");
        esUser.setCreateTime(new Date());
        esUser.setUpdateTime(new Date());
        EsUser esUserResult = esUserRepository.save(esUser);
        System.out.println(esUserResult);
    }

    @Test
    public void saveAll() {
        List<EsUser> esUserList = new ArrayList<>();
        for (int i = 2; i <= 11; i++) {
            EsUser esUser = new EsUser();
            esUser.setId(i);
            esUser.setUsername("lisi" + i);
            esUser.setPassword("123456");
            esUser.setAddress("浙江宁波");
            esUser.setNickName("李四" + i);
            esUser.setCreateTime(new Date());
            esUser.setUpdateTime(new Date());
            esUserList.add(esUser);
        }
        Iterable<EsUser> list = esUserRepository.saveAll(esUserList);
        System.out.println(list);
    }

    @Test
    public void deleteById() {
        esUserRepository.deleteById(5);
    }

    @Test
    public void delete() {
        EsUser esUser = new EsUser();
        esUser.setId(6);
        esUserRepository.delete(esUser);
    }

    @Test
    public void deleteAll() {
        esUserRepository.deleteAll();
    }

    @Test
    public void findById() {
        Optional<EsUser> esUser = esUserRepository.findById(1);
        System.out.println(esUser.get());
    }

    @Test
    public void findAllById() {
        List<Integer> idList = new ArrayList<>();
        idList.add(1);
        idList.add(2);
        Iterable<EsUser> list = esUserRepository.findAllById(idList);
        System.out.println(list);
    }

    @Test
    public void findAll() {
        Iterable<EsUser> list = esUserRepository.findAll();
        System.out.println(list);
    }

    @Test
    public void findAllSort() {
        Iterable<EsUser> list = esUserRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        System.out.println(list);
    }

    @Test
    public void findAllPage() {
        Pageable pageable = PageRequest.of(0, 2);
        Page<EsUser> page = esUserRepository.findAll(pageable);
        System.out.println("总记录数：" + page.getTotalElements());
        System.out.println("当前页记录数：" + page.getNumberOfElements());
        System.out.println("每页记录数：" + page.getSize());
        System.out.println("总页数：" + page.getTotalPages());
        System.out.println("查询结果：" + page.getContent());
        System.out.println("当前页（从0开始计）：" + page.getNumber());
        System.out.println("是否为首页：" + page.isFirst());
        System.out.println("是否为尾页：" + page.isLast());
    }

    /**
     * more_like_this query
     */
    @Test
    public void searchSimilar() {
        EsUser esUser = new EsUser();
        esUser.setId(2);
        Pageable pageable = PageRequest.of(0, 2);
        Page<EsUser> page = esUserRepository.searchSimilar(esUser, new String[]{"address"}, pageable);
        System.out.println("总记录数：" + page.getTotalElements());
        System.out.println("当前页记录数：" + page.getNumberOfElements());
        System.out.println("每页记录数：" + page.getSize());
        System.out.println("总页数：" + page.getTotalPages());
        System.out.println("查询结果：" + page.getContent());
        System.out.println("当前页（从0开始计）：" + page.getNumber());
        System.out.println("是否为首页：" + page.isFirst());
        System.out.println("是否为尾页：" + page.isLast());
    }

    @Test
    public void existsById() {
        boolean b = esUserRepository.existsById(1);
        System.out.println(b);
    }

    @Test
    public void count() {
        long count = esUserRepository.count();
        System.out.println(count);
    }

    /*=============== 自定义简单查询-开始 ===============*/
    @Test
    public void findByIdAndUsername() {
        EsUser esUser = esUserRepository.findByIdAndUsername(2, "lisi");
        System.out.println(esUser);
    }

    @Test
    public void findByIdGreaterThan() {
        Pageable pageable = PageRequest.of(0, 2);
        Page<EsUser> page = esUserRepository.findByIdGreaterThan(4, pageable);
        System.out.println("总记录数：" + page.getTotalElements());
        System.out.println("当前页记录数：" + page.getNumberOfElements());
        System.out.println("每页记录数：" + page.getSize());
        System.out.println("总页数：" + page.getTotalPages());
        System.out.println("查询结果：" + page.getContent());
        System.out.println("当前页（从0开始计）：" + page.getNumber());
        System.out.println("是否为首页：" + page.isFirst());
        System.out.println("是否为尾页：" + page.isLast());
    }

    @Test
    public void findByIdLessThanOrUsernameContaining() {
        List<EsUser> list = esUserRepository.findByIdLessThanOrUsernameContaining(10, "si");
        System.out.println(list);
    }

    @Test
    public void findByAddress() {
        Pageable pageable = PageRequest.of(0, 2);
        Page<EsUser> page = esUserRepository.findByAddress("宁波", pageable);
        System.out.println("总记录数：" + page.getTotalElements());
        System.out.println("当前页记录数：" + page.getNumberOfElements());
        System.out.println("每页记录数：" + page.getSize());
        System.out.println("总页数：" + page.getTotalPages());
        System.out.println("查询结果：" + page.getContent());
        System.out.println("当前页（从0开始计）：" + page.getNumber());
        System.out.println("是否为首页：" + page.isFirst());
        System.out.println("是否为尾页：" + page.isLast());
    }
    /*=============== 自定义简单查询-结束 ===============*/

    /*=============== 自定义复杂查询（ElasticsearchRestTemplate）-开始 ===============*/
    /**
     * 根据关键字搜索用户名或昵称，再增加过滤、聚合、排序、分页
     */
    @Test
    public void search() {
        Boolean enabled = true;
        Boolean locked = false;
        String keyword = "四";
        Integer sort = 1;
        PageImpl<EsUser> page = null;
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();

        // 搜索
        if (StringUtils.isEmpty(keyword)) {
            nativeSearchQueryBuilder.withQuery(QueryBuilders.matchAllQuery());
        } else {
            // nativeSearchQueryBuilder.withQuery(QueryBuilders.multiMatchQuery(keyword, "username", "nickName"));

            List<FunctionScoreQueryBuilder.FilterFunctionBuilder> filterFunctionBuilders = new ArrayList<>();
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("username", keyword),
                    ScoreFunctionBuilders.weightFactorFunction(10)));
            filterFunctionBuilders.add(new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery("nickName", keyword),
                    ScoreFunctionBuilders.weightFactorFunction(2)));
            FunctionScoreQueryBuilder.FilterFunctionBuilder[] builders = new FunctionScoreQueryBuilder.FilterFunctionBuilder[filterFunctionBuilders.size()];
            filterFunctionBuilders.toArray(builders);
            FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(builders)
                    .scoreMode(FunctionScoreQuery.ScoreMode.SUM)
                    .setMinScore(2);
            nativeSearchQueryBuilder.withQuery(functionScoreQueryBuilder);
        }

        // 过滤
        if (enabled != null || locked != null) {
            BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
            if (enabled != null) {
                boolQueryBuilder.must(QueryBuilders.termQuery("enabled", enabled));
            }
            if (locked != null) {
                boolQueryBuilder.must(QueryBuilders.termQuery("locked", locked));
            }
            nativeSearchQueryBuilder.withFilter(boolQueryBuilder);
        }

        // 聚合，对应的字段一般设置为 FieldType.Keyword
        // nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms("passwords").field("password"));
        // nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms("addresses").field("address"));

        // 排序
        if (sort == 1) {
            // 按id降序
            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("id").order(SortOrder.DESC));
        } else if (sort == 2) {
            // 按更新时间降序
            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("updateTime").order(SortOrder.DESC));
        } else if (sort == 3) {
            // 按地址升序
            nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("address").order(SortOrder.ASC));
        } else {
            // 按相关度
            nativeSearchQueryBuilder.withSort(SortBuilders.scoreSort().order(SortOrder.DESC));
        }

        // 分页
        Pageable pageable = PageRequest.of(0, 2);
        nativeSearchQueryBuilder.withPageable(pageable);

        NativeSearchQuery searchQuery = nativeSearchQueryBuilder.build();
        System.out.println("DSL: " + searchQuery.getQuery().toString());
        SearchHits<EsUser> searchHits = elasticsearchRestTemplate.search(searchQuery, EsUser.class);
        if (searchHits.getTotalHits() <= 0) {
            page = new PageImpl<>(Collections.emptyList(), pageable, 0);
        }
        List<EsUser> esUserList = searchHits.stream().map(SearchHit::getContent).collect(Collectors.toList());
        page = new PageImpl<>(esUserList, pageable, searchHits.getTotalHits());
        System.out.println("总记录数：" + page.getTotalElements());
        System.out.println("当前页记录数：" + page.getNumberOfElements());
        System.out.println("每页记录数：" + page.getSize());
        System.out.println("总页数：" + page.getTotalPages());
        System.out.println("查询结果：" + page.getContent());
        System.out.println("当前页（从0开始计）：" + page.getNumber());
        System.out.println("是否为首页：" + page.isFirst());
        System.out.println("是否为尾页：" + page.isLast());
    }
    /*=============== 自定义复杂查询（ElasticsearchRestTemplate）-结束 ===============*/
}
