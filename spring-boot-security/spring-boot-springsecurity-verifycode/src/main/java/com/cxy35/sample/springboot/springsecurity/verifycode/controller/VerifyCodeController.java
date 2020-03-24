package com.cxy35.sample.springboot.springsecurity.verifycode.controller;

import com.cxy35.sample.springboot.springsecurity.verifycode.config.VerifyCode;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
public class VerifyCodeController {
    @GetMapping("/vercode")
    public void code(HttpServletRequest req, HttpServletResponse resp, Model model) throws IOException {
        VerifyCode vc = new VerifyCode();
        BufferedImage image = vc.getImage();
        String text = vc.getText();
        model.addAttribute("text", text);
        System.out.println("text = " + text);
        HttpSession session = req.getSession();
        session.setAttribute("s_vercode", text);
        VerifyCode.output(image, resp.getOutputStream());
    }
}
