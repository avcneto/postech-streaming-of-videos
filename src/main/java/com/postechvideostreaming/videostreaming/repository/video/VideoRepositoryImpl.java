package com.postechvideostreaming.videostreaming.repository.video;

import com.postechvideostreaming.videostreaming.domain.video.Video;
import com.postechvideostreaming.videostreaming.domain.video.VideoSearchParams;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;

import static com.postechvideostreaming.videostreaming.util.Validators.isNullOrEmptyOrBlank;

public class VideoRepositoryImpl implements VideoRepositoryCustom {

  private static final String ID = "id";
  private static final String TITLE = "title";
  private static final String DESCRIPTION = "description";
  private static final String CATEGORY = "category";
  private static final String CREATION_DATE = "creationDate";
  private static final String OPTIONS = "i";
  private static final int TWENTY_THREE = 23;
  private static final int FIFTY_NINE = 59;
  private final ReactiveMongoTemplate mongoTemplate;

  public VideoRepositoryImpl(ReactiveMongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  @Override
  public Flux<Video> findByCustomParams(VideoSearchParams params) {
    Query query = new Query();

    if (!isNullOrEmptyOrBlank(params.getVideoId())) {
      query.addCriteria(Criteria.where(ID).is(params.getVideoId()));
    }

    if (!isNullOrEmptyOrBlank(params.getTitle())) {
      query.addCriteria(Criteria.where(TITLE).regex(params.getTitle(), OPTIONS));
    }

    if (!isNullOrEmptyOrBlank(params.getDescription())) {
      query.addCriteria(Criteria.where(DESCRIPTION).regex(params.getDescription(), OPTIONS));
    }

    if (!isNullOrEmptyOrBlank(params.getCategory())) {
      query.addCriteria(Criteria.where(CATEGORY).is(params.getCategory()));
    }

    if (!isNullOrEmptyOrBlank(params.getCreationDate()) && !isNullOrEmptyOrBlank(params.getEndCreationDate())) {
      query.addCriteria(Criteria.where(CREATION_DATE)
              .gte(params.getCreationDate().atStartOfDay())
              .lte(params.getEndCreationDate().atTime(TWENTY_THREE, FIFTY_NINE, FIFTY_NINE)));
    }

    return mongoTemplate.find(query, Video.class);
  }
}
