package com.kafkatester.infrastructure.kafka;

import com.kafkatester.domain.messages.TestMessage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class TopicRouterTest {

    TopicRouter router = new TopicRouter();

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(router, "testTopic", "this_is_fo_test_purpose");
    }

    @Test
    public void givenValidMessageContent_whenGetTopic_thenItReturnsProperTopic() {
        TestMessage someMessage = new TestMessage();
        someMessage.Message = "hello";
        someMessage.TimeStamp = new Date();
        String topic = router.getTopicRouting(someMessage);

        assertThat(topic).isNotEmpty();
    }
}
