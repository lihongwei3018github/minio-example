package org.example.minio;

import org.example.minio.template.S3Template;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
class MinioApplicationTests {

    @Autowired
    S3Template s3Template;

    @Test
    public void testUpload(){
        boolean test = s3Template.upload("test.txt", new File("D:\\temp\\test.txt"));
        System.out.println(test);
    }

}
