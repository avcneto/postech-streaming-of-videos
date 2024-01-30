package com.postechvideostreaming.videostreaming.domain.favorite;

import com.postechvideostreaming.videostreaming.domain.video.Category;
import com.postechvideostreaming.videostreaming.dto.favorite.FavoriteVideoDTO;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@Document
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class FavoriteVideo {

  @Id
  private UUID id;
  private String videoId;
  private Category category;
  private Long userId;
  private Boolean favorite;
  @Version
  private Long version;

  public FavoriteVideo(FavoriteVideoDTO favoriteVideoDTO) {
    this.id = UUID.randomUUID();
    this.videoId = favoriteVideoDTO.videoId();
    this.category = favoriteVideoDTO.category();
    this.userId = favoriteVideoDTO.userId();
    this.favorite = favoriteVideoDTO.favorite();
  }

  public FavoriteVideo setFavorite(Boolean favorite) {
    this.favorite = favorite;
    return this;
  }
}
