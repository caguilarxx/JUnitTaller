package com.awi.pocs.Mockito;

import com.awi.pocs.Mockito.business.impl.CardBusinessServiceImpl;
import com.awi.pocs.Mockito.service.CardApi;
import com.awi.pocs.Mockito.service.HazelcastApi;
import com.awi.pocs.model.Card;
import com.awi.pocs.model.Session;
import com.awi.pocs.model.api.DemoRequest;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class CardBusinessServiceImplTest {

  @InjectMocks
  private CardBusinessServiceImpl service;

  @Mock
  private DemoRequest request;

  @Mock
  private HazelcastApi hazelcast;

  @Mock
  private CardApi cardApi;

  @Test
  public void testFilterActiveCardsAltWay() {

    /* Mock */
    when(hazelcast.getFromCache(anyString())).thenReturn(new Session("e156a4fc", "45614477", false));
    when(request.getSessionId()).thenReturn("e156a4fc");
    when(cardApi.getCards(any())).thenReturn(buildCardApiData());

    /* Test */
    List<Card> responseList = service.filterActiveCards(request).blockingGet();

//    List<Card> responseList = new ArrayList<>();
//    service.filterActiveCards(request).subscribe(cards -> responseList.addAll(cards));

    /* Asserts */
    assertThat(responseList, hasSize(3));
    assertThat(responseList, notNullValue());
  }


  @Test
  public void testFilterActiveCardsTestObs() {

    /* Mock */
    when(hazelcast.getFromCache(anyString())).thenReturn(new Session("e156a4fc", "45614477", false));
    when(request.getSessionId()).thenReturn("e156a4fc");
    when(cardApi.getCards(any())).thenReturn(buildCardApiData());

    /* Test */   //TODO merge?
    TestObserver<List<Card>> testObserver = service.filterActiveCards(request).test();

    /* Asserts */
    testObserver
        .assertSubscribed()
        .assertComplete()
        .assertNever(cards -> cards.stream().anyMatch(card -> !card.getActive()))  // F
        .assertValue(cards -> cards.size() == 3)
        .assertValueCount(1)
        .assertNoErrors();
  }

  @Test
  public void testTilterActiveCardsException() {

    /* Mock */
    when(hazelcast.getFromCache(any())).thenReturn(new Session("e156a4fc", "45614477", true));

    /* Test */   //TODO merge?
    TestObserver<List<Card>> testObserver = service.filterActiveCards(request).test();

    /* Asserts */
    testObserver
        .assertSubscribed()
        .assertNotComplete()
        .assertValueCount(0)
        .assertError(Exception.class);
  }

  @Test
  public void testTilterActiveCardsObs() {

    /* Mock */
    when(hazelcast.getFromCache(any())).thenReturn(new Session("e156a4fc", "45614477", true));
    when(cardApi.getCards(any())).thenReturn(buildCardApiData());

    /* Test */
    TestObserver<Card> testObserver = service.filterActiveCardsObs(request).test();

    /* blocking async io !! */
    testObserver.awaitTerminalEvent();

    /* Asserts */
    testObserver
        .assertSubscribed()
        .assertComplete()
        .assertValueCount(3)
        .assertNever(card -> !card.getActive())
        .assertNoErrors();
  }

  /* CardAPI data return */
  private Single<List<Card>> buildCardApiData() {
    return Single.just(asList(
        new Card(1001, "4557885801554491", true),
        new Card(1002, "4557885801554492", true),
        new Card(1003, "4557885801554493", true),
        new Card(1004, "4557885801554494", false)
    ));
  }
}
