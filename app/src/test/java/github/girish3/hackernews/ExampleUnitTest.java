package github.girish3.hackernews;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

import github.girish3.hackernews.providers.RetrofitProvider;
import github.girish3.hackernews.retrofit_services.TopicListService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {

    Retrofit mRetrofit;

    @Mock
    Call<List<String>> idsCall;

    @Captor
    private ArgumentCaptor<Callback<List<String>>> callbackArgumentCaptor;

    @Before
    public void runBefore() {
        mRetrofit = RetrofitProvider.getInstance();
    }

    @Test
    public void BasicTesting() throws Exception {

        /*TopicListService service = mRetrofit.create(TopicListService.class);
        idsCall = service.listTopicIds();

        Mockito.verify(idsCall).enqueue(callbackArgumentCaptor.capture());

        callbackArgumentCaptor.getValue();*/

        long currentTime = System.currentTimeMillis();
        long one_minute_back = currentTime - 60000;

        assertEquals(TimeDiff.getTimeAgo(one_minute_back), "a minute ago");
    }
}