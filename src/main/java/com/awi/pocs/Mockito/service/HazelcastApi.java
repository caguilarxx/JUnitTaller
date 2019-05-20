package com.awi.pocs.Mockito.service;

import com.awi.pocs.model.Session;
import org.springframework.stereotype.Service;

public interface HazelcastApi {

  public Session getFromCache(String sessionId);
}
