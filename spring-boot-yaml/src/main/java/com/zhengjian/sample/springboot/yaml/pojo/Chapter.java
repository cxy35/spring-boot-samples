package com.zhengjian.sample.springboot.yaml.pojo;

/**
 * @Author zhengjian
 * @Date 2019/10/30 15:26
 */
public class Chapter {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
