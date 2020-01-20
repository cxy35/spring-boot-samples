package com.zhengjian.sample.springboot.mail;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SpringBootMailApplicationTests {

    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    MailProperties mailProperties;

    @Test
    void contextLoads() {
    }

    // 发送简单邮件
    @Test
    public void sendSimpleMail() {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setSubject("这是测试邮件主题");
        msg.setFrom(mailProperties.getUsername());
        msg.setTo("454407628@qq.com");
        msg.setCc("454407628@qq.com");
        msg.setBcc("454407628@qq.com");
        msg.setSentDate(new Date());
        msg.setText("这是测试邮件内容");
        javaMailSender.send(msg);
    }

    // 发送带附件的邮件
    @Test
    public void sendAttachFileMail() throws MessagingException {
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setSubject("这是测试邮件主题(带附件)");
        helper.setFrom(mailProperties.getUsername());
        helper.setTo("454407628@qq.com");
//        helper.setCc("454407628@qq.com");
//        helper.setBcc("454407628@qq.com");
        helper.setSentDate(new Date());
        helper.setText("这是测试邮件内容(带附件)");
        helper.addAttachment("1.png", new File("D:\\1.png"));
        javaMailSender.send(msg);
    }

    // 发送带图片资源的邮件（图片资源直接放在邮件正文中）
    @Test
    public void sendImgResMail() throws MessagingException {
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setSubject("这是测试邮件主题(带图片)");
        helper.setFrom(mailProperties.getUsername());
        helper.setTo("454407628@qq.com");
//        helper.setCc("454407628@qq.com");
//        helper.setBcc("454407628@qq.com");
        helper.setSentDate(new Date());
        helper.setText("这是测试邮件内容(带图片)，这是第一张图片：<img src='cid:p01'/>，这是第二张图片：<img src='cid:p02'/>", true);
        helper.addInline("p01", new FileSystemResource(new File("D:\\1.png")));
        helper.addInline("p02", new FileSystemResource(new File("D:\\2.png")));
        javaMailSender.send(msg);
    }

    // 使用 Freemarker 作邮件模板
    @Test
    public void sendFreemarkerMail() throws MessagingException, IOException, TemplateException {
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setSubject("这是测试邮件主题(Freemarker 模板)");
        helper.setFrom(mailProperties.getUsername());
        helper.setTo("454407628@qq.com");
//        helper.setCc("454407628@qq.com");
//        helper.setBcc("454407628@qq.com");
        helper.setSentDate(new Date());

        Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
        configuration.setClassLoaderForTemplateLoading(this.getClass().getClassLoader(), "templates");
        Template template = configuration.getTemplate("mail.ftl");
        Map<String, Object> map = new HashMap<>();
        map.put("username", "zhangsan");
        map.put("position", "Java工程师");
        map.put("dep", "产品研发部");
        map.put("salary", 999999);
        map.put("joblevel", "高级工程师");
        StringWriter out = new StringWriter();
        template.process(map, out);
        helper.setText(out.toString(), true);
        javaMailSender.send(msg);
    }

    // 使用 Thymeleaf 作邮件模板（推荐在 Spring Boot 中使用 Thymeleaf 来构建邮件模板）
    @Autowired
    TemplateEngine templateEngine;

    @Test
    public void sendThymeleafMail() throws MessagingException {
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        try {
            helper.setSubject("这是测试邮件主题(Thymeleaf 模板)");
            helper.setFrom(mailProperties.getUsername());
            helper.setTo("454407628@qq.com");
//        helper.setCc("454407628@qq.com");
//        helper.setBcc("454407628@qq.com");
            helper.setSentDate(new Date());

            Context context = new Context();
            context.setVariable("username", "zhangsan");
            context.setVariable("position", "Java工程师");
            context.setVariable("dep", "产品研发部");
            context.setVariable("salary", 999999);
            context.setVariable("joblevel", "高级工程师");
            String process = templateEngine.process("mail.html", context);
            helper.setText(process, true);
            javaMailSender.send(msg);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
