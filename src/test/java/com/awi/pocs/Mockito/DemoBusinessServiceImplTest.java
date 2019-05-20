package com.awi.pocs.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.awi.pocs.Mockito.business.impl.DemoBusinessServiceImpl;
import com.awi.pocs.Mockito.service.CardsApi;
import com.awi.pocs.Mockito.service.HazelcastApi;
import com.awi.pocs.model.Card;
import com.awi.pocs.model.Session;
import com.awi.pocs.model.api.CardApi;
import com.awi.pocs.model.api.DemoRequest;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import io.reactivex.subscribers.TestSubscriber;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DemoBusinessServiceImplTest {

  @InjectMocks
  private DemoBusinessServiceImpl service;

  @Mock
  private Session session;

  @Mock
  private DemoRequest request;

  @Mock
  private HazelcastApi hazelcast;

  @Mock
  private CardsApi cardsApi;

  @Before
  public void setUp() {

    when(session.getDocumentNumber()).thenReturn("12345678");
    when(session.isBlacklisted()).thenReturn(false);
    when(hazelcast.getFromCache(any())).thenReturn(session);
    when(cardsApi.getCards(any())).thenReturn(buildCardApiData());

  }

  @Test
  public void testTilterActiveCards() {

    TestObserver<List<Card>> testObserver = new TestObserver();
    service.filterActiveCards(request).subscribe(testObserver);
    testObserver.assertSubscribed()
        .assertComplete()
        .assertValue(cards -> cards.size() == 3)
        .assertValue(cards -> cards.stream().noneMatch(card -> !card.getActive()))
        .assertValueCount(1)
        .assertNoErrors();
  }

  @Test
  public void testTilterActiveCardsException() {

    when(session.isBlacklisted()).thenReturn(true);

    service.filterActiveCards(request).test()
        .assertSubscribed()
        .assertNotComplete()
        .assertValueCount(0)
        .assertError(RuntimeException.class);
  }

  @Test
  public void testTilterActiveCardsObs() {

    TestObserver<Card> testObserver = new TestObserver();

    service.filterActiveCardsObs(request).subscribe(testObserver);

    //Observable Async
    testObserver.awaitTerminalEvent();

    testObserver
        .assertSubscribed()
        .assertComplete()
        .assertValueCount(3)
        .assertNever(card -> !card.getActive())
        .assertNoErrors();
  }

  private Single<List<CardApi>> buildCardApiData(){
    return Single.just(Arrays.asList(
        new CardApi(1001, "4557885801554491", true),
        new CardApi(1002, "4557885801554492", true),
        new CardApi(1003, "4557885801554493", true),
        new CardApi(1004, "4557885801554494", false)
    ));
  }
}
