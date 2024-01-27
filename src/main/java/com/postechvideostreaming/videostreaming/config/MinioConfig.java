package com.postechvideostreaming.videostreaming.config;

import com.postechvideostreaming.videostreaming.exception.FailedDependencyException;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import java.util.concurrent.TimeUnit;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class MinioConfig {

  private static final String ERROR_LOADING_MINIO_CONF = "error loading minio configuration";

  @Value("${minio.endpoint}")
  private String minioEndpoint;

  @Value("${minio.accessKey}")
  private String minioAccessKey;

  @Value("${minio.secretKey}")
  private String minioSecretKey;

  @Getter
  @Value("${minio.bucket}")
  private String bucketName;

  @Bean
  public MinioClient minioClient() {
    try {

      OkHttpClient httpClient = new OkHttpClient.Builder()
          .connectTimeout(10, TimeUnit.MINUTES)
          .writeTimeout(10, TimeUnit.MINUTES)
          .readTimeout(30, TimeUnit.MINUTES)
          .build();

      MinioClient minioClient = MinioClient.builder()
          .endpoint(minioEndpoint)
          .credentials(minioAccessKey, minioSecretKey)
          .httpClient(httpClient)
          .build();

      boolean found =
          minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());

      if (!found) {
        minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
      }

      return minioClient;
    } catch (Exception ex) {
      log.error(ERROR_LOADING_MINIO_CONF, ex);
      throw new FailedDependencyException(ERROR_LOADING_MINIO_CONF, ex);
    }
  }

}
