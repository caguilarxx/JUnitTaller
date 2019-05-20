package com.awi.pocs.Mockito.service.impl;

import com.awi.pocs.Mockito.service.HazelcastApi;
import com.awi.pocs.model.Session;
import org.springframework.stereotype.Service;

@Service
public class HazelcastApiImpl implements HazelcastApi {
  @Override
  public Session getFromCache(String sessionId) {
    return null;
  }
}
