package com.postechvideostreaming.videostreaming.dto.summary;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ViewDTOTest {

  @Test
  public void shouldReturnViewDTO() {
    Long total = 50L;
    ViewDTO viewDTO = new ViewDTO(total);

    assertEquals(total, viewDTO.total());
  }

  @Test
  public void shouldReturnQuantityView() {
    Long total = 50L;
    ViewDTO viewDTO = new ViewDTO(total);

    assertEquals(total, viewDTO.quantityView());

    ViewDTO viewDTOWithNull = new ViewDTO(null);
    assertEquals(0L, viewDTOWithNull.quantityView());
  }

  @Test
  public void shouldJsonSerialization() throws Exception {
    Long total = 50L;
    ViewDTO viewDTO = new ViewDTO(total);
    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(viewDTO);

    ViewDTO deserializedDTO = objectMapper.readValue(json, ViewDTO.class);

    assertEquals(viewDTO, deserializedDTO);
  }
}
