package com.ta.coremolde.shop.service;

import com.ta.coremolde.shop.model.response.DiscussionResponseServiceResponse;

public interface PushNotificationService {

    String subscribeToTopic(String registrationToken);

    String unsubscribeToTopic(String registrationToken);

    String pushDiscussionReplyNotification(DiscussionResponseServiceResponse response);

}
