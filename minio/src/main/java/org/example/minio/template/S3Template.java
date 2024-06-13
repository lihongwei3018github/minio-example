package org.example.minio.template;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import jakarta.annotation.PostConstruct;
import org.apache.commons.lang3.ObjectUtils;
import org.example.minio.properties.S3Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class S3Template {

    @Autowired
    private AmazonS3 amazonS3;

    @Autowired
    private S3Properties s3Properties;

    @PostConstruct
    public void init() {
        if (amazonS3.doesBucketExistV2(s3Properties.getBucketName())) {
            System.out.println(s3Properties.getBucketName() + " bucket already exists");
            return;
        }
        amazonS3.createBucket(s3Properties.getBucketName());
    }

    public boolean upload(String key, File file) {
        return ObjectUtils.isNotEmpty(amazonS3.putObject(s3Properties.getBucketName(), key, file));
    }

    public boolean download(String key) {
        S3Object s3Object = amazonS3.getObject(s3Properties.getBucketName(), key);
        byte[] readBytes = IoUtil.readBytes(s3Object.getObjectContent());
        return ObjectUtils.isNotEmpty(FileUtil.writeBytes(readBytes, new File(s3Properties.getFilepath()))) ;
    }

    public boolean verifySize(String key){
        ObjectMetadata objectMetadata = amazonS3.getObjectMetadata(s3Properties.getBucketName(), key);
        return objectMetadata.getContentLength() <= Long.parseLong(s3Properties.getFilesize());
    }

}
