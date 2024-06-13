package org.example.minio.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DataSizeUnit;
import org.springframework.stereotype.Component;
import org.springframework.util.unit.DataUnit;

@Data
@ConfigurationProperties(prefix = "object.storage")
public class S3Properties {

    private String accessKey;

    private String secretKey;

    private String serviceEndpoint;

    private String bucketName;

    private String filepath;

    @DataSizeUnit(DataUnit.MEGABYTES)
    private String filesize;

}
