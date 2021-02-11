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

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(TopicRouter.class, "testTopic", "this_is_fo_test_purpose");
        TopicRouter.initialize();
    }

    @Test
    public void givenValidMessageContent_whenGetTopic_thenItReturnsProperTopic() {
        TestMessage someMessage = new TestMessage();
        someMessage.Message = "hello";
        someMessage.TimeStamp = new Date();
        String topic = TopicRouter.getTopicRouting(someMessage);

        assertThat(topic).isNotEmpty();
    }
}
