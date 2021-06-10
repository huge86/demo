package com.xiaoge.test.generator;

import com.aliyun.oss.OSS;
import com.xiaoge.XinGuanApplication;
import com.xiaoge.system.entity.OssEntity;
import com.xiaoge.system.service.AliOssService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.util.UUID;

/**
 * @author NieChangan
 */
@SpringBootTest(classes = XinGuanApplication.class)
public class OssTest {

    @Autowired
    private OssEntity ossEntity;

    @Autowired
    private AliOssService aliOssService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void contextLoads() {
        //aliOssService.deleteFile("2020/10/12/9e36916796804ac98bae363a225230ed.jpg");

        //$2a$10$a6BBlpat/QsdxrQYokF9pueoZn/wmdachMfZ4RV/5EhUiXRDZAaRW
        //$2a$10$juGw32JTAcCzHStkjPanNe.Tu/Avd3TxJp8Avc9..vT/NAgbMsfZ6
        //而且每次运行就算是相同的字符串,生成的密码也是不一样的,因为每次生成的salt不一样
        //String encode = passwordEncoder.encode("123456");
        //boolean matches = passwordEncoder.matches("123456", "$2a$10$a6BBlpat/QsdxrQYokF9pueoZn/wmdachMfZ4RV/5EhUiXRDZAaRW");
        //System.out.println(matches);
    }
}
