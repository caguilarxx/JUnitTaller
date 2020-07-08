package com.awi.pocs.Mockito;

import com.awi.pocs.Mockito.business.impl.CardBusinessServiceImpl;
import com.awi.pocs.Mockito.service.CardApi;
import com.awi.pocs.Mockito.service.HazelcastApi;
import com.awi.pocs.model.Card;
import com.awi.pocs.model.Session;
import com.awi.pocs.model.api.DemoRequest;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CardBusinessServiceImplTest {

  @InjectMocks
  private CardBusinessServiceImpl service;

  @Mock
  private Session session;

  @Mock
  private DemoRequest request;

  @Mock
  private HazelcastApi hazelcast;

  @Mock
  private CardApi cardApi;

  @Test
  public void testTilterActiveCardsAltWay() {

    when(session.getDocumentNumber()).thenReturn("12345678");
    when(session.isBlacklisted()).thenReturn(false);
    when(hazelcast.getFromCache(any())).thenReturn(session);
    when(cardApi.getCards(any())).thenReturn(buildCardApiData());

    List<Card> cardsTmp = new ArrayList<>();

    service.filterActiveCards(request).subscribe(cards -> cardsTmp.addAll(cards));

    assertThat(cardsTmp, hasSize(3));
    assertThat(cardsTmp, notNullValue());
  }


  @Test
  public void testTilterActiveCards() {

    when(session.getDocumentNumber()).thenReturn("12345678");
    when(session.isBlacklisted()).thenReturn(false);
    when(hazelcast.getFromCache(any())).thenReturn(session);
    when(cardApi.getCards(any())).thenReturn(buildCardApiData());

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

//    when(session.getDocumentNumber()).thenReturn("12345678");
//    when(cardsApi.getCards(any())).thenReturn(buildCardApiData());

    when(session.isBlacklisted()).thenReturn(false);
    when(hazelcast.getFromCache(any())).thenReturn(session);

    when(session.isBlacklisted()).thenReturn(true);

    service.filterActiveCards(request).test()
        .assertSubscribed()
        .assertNotComplete()
        .assertValueCount(0)
        .assertError(RuntimeException.class);
  }

  @Test
  public void testTilterActiveCardsObs() {

    when(session.getDocumentNumber()).thenReturn("12345678");
//    when(session.isBlacklisted()).thenReturn(false);
    when(hazelcast.getFromCache(any())).thenReturn(session);
    when(cardApi.getCards(any())).thenReturn(buildCardApiData());

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

  private Single<List<Card>> buildCardApiData() {
    return Single.just(Arrays.asList(
        new Card(1001, "4557885801554491", true),
        new Card(1002, "4557885801554492", true),
        new Card(1003, "4557885801554493", true),
        new Card(1004, "4557885801554494", false)
    ));
  }
}
