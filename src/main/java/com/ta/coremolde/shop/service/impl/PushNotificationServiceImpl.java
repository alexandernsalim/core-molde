package com.ta.coremolde.shop.service.impl;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.TopicManagementResponse;
import com.ta.coremolde.shop.model.response.DiscussionResponseServiceResponse;
import com.ta.coremolde.shop.service.PushNotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
public class PushNotificationServiceImpl implements PushNotificationService {
    private static final Logger logger = LoggerFactory.getLogger(PushNotificationServiceImpl.class);
    private static final String CREDENTALS = "google/molde-c0a96-firebase-adminsdk-2y7dm-08eb39ee4b.json";

    @PostConstruct
    public void initialize() {
        try {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(
                            new ClassPathResource(CREDENTALS).getInputStream())
                    ).build();
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                logger.info("Firebase application has been initialized");
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public String subscribeToTopic(String registrationToken) {
        List<String> tokens = Collections.singletonList(registrationToken);
        try {
            TopicManagementResponse response = FirebaseMessaging.getInstance().subscribeToTopic(tokens, "discussionReply");

            return "Token subscribed successfully: " + response.getSuccessCount();
        } catch (FirebaseMessagingException e) {
            return "Failed to subscribe token with error: " + e.getMessage();
        }
    }

    @Override
    public String unsubscribeToTopic(String registrationToken) {
        List<String> tokens = Collections.singletonList(registrationToken);
        try {
            TopicManagementResponse response = FirebaseMessaging.getInstance().unsubscribeFromTopic(tokens, "discussionReply");

            return "Unsubscribe topic successfully: " + response.getSuccessCount();
        } catch (FirebaseMessagingException e) {
            return "Failed to unsubscribe token with error: " + e.getMessage();
        }
    }

    @Override
    public String pushDiscussionReplyNotification(DiscussionResponseServiceResponse response) {
        String topic = "/topics/discussionReply";
        Message message = Message.builder()
                .putData("discussionId", String.valueOf(response.getId()))
                .putData("shopUsername", response.getShopReplyUsername())
                .putData("shopUserUsername", response.getShopUserReplyUsername())
                .putData("message", response.getMessage())
                .setTopic(topic)
                .build();

        String messageId = null;
        try {
            messageId = FirebaseMessaging.getInstance().send(message);
            logger.info("Successfully push notification");
        } catch (FirebaseMessagingException e) {
            logger.error(e.getMessage());
        }

        return messageId;
    }
}
