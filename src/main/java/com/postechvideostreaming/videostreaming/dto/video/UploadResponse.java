package com.postechvideostreaming.videostreaming.dto.video;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UploadResponse {
  String id;
  String objectName;

  String bucket;
}
