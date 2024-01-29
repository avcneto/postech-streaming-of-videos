package com.postechvideostreaming.videostreaming.repository.video;

import com.postechvideostreaming.videostreaming.domain.video.Category;
import com.postechvideostreaming.videostreaming.domain.video.Video;
import com.postechvideostreaming.videostreaming.domain.video.VideoSearchParams;
import com.postechvideostreaming.videostreaming.dto.video.VideoSearch;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.SampleOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.postechvideostreaming.videostreaming.domain.video.Order.ASC;
import static com.postechvideostreaming.videostreaming.util.Validators.isNullOrEmptyOrBlank;
import static org.springframework.data.domain.Sort.Order.asc;
import static org.springframework.data.domain.Sort.Order.desc;

public class VideoRepositoryImpl implements VideoRepositoryCustom {

  private static final String ID = "id";
  private static final String TITLE = "title";
  private static final String DESCRIPTION = "description";
  private static final String CATEGORY = "category";
  private static final String CREATION_DATE = "creationDate";
  private static final String VIDEO = "video";
  private static final String OPTIONS = "i";
  private static final int TWENTY_THREE = 23;
  private static final int FIFTY_NINE = 59;
  private final ReactiveMongoTemplate mongoTemplate;

  public VideoRepositoryImpl(ReactiveMongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  @Override
  public Mono<VideoSearch> findByCustomParams(VideoSearchParams params) {
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

    query.with(params.getSort().equals(ASC) ? Sort.by(asc(CREATION_DATE)) : Sort.by(desc(CREATION_DATE)));
    query.skip(params.getOffsetMultiplyLimit()).limit(params.getLimit());


    return Mono.just(new VideoSearch(
            mongoTemplate.find(query, Video.class),
            mongoTemplate.count(query, Video.class),
            params.getLimit(), params.getOffset()));
  }


  @Override
  public Flux<Video> findRandomVideosByCategory(List<Category> category, Integer limit) {
    MatchOperation matchOperation = Aggregation.match(Criteria.where(CATEGORY).in(category));
    SampleOperation sampleOperation = Aggregation.sample(limit);

    Aggregation aggregation = Aggregation.newAggregation(matchOperation, sampleOperation);

    return mongoTemplate.aggregate(aggregation, VIDEO, Video.class);
  }
}
