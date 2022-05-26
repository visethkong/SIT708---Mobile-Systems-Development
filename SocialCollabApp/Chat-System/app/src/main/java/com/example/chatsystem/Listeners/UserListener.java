package com.example.chatsystem.Listeners;

import com.example.chatsystem.Models.User;

public interface UserListener {
    void onUserClicked(User user);

    void initiateVideoMeeting(User user);

    void initiateAudioMeeting(User user);

}
