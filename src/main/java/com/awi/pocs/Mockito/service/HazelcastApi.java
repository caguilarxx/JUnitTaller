package com.awi.pocs.Mockito.service;

import com.awi.pocs.model.Session;

public interface HazelcastApi {

  Session getFromCache(String sessionId);
}
