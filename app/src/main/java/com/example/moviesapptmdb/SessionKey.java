package com.example.moviesapptmdb;

public class SessionKey {

    /*
{
    "success": true,
    "guest_session_id": "4f408212d8504f2a66d293d50035b6d9",
    "expires_at": "2022-11-11 16:04:15 UTC"
}
     */
    boolean success;
    String guest_session_id;
    String expires_at;

    public SessionKey(boolean success, String guest_session_id, String expires_at) {
        this.success = success;
        this.guest_session_id = guest_session_id;
        this.expires_at = expires_at;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getGuest_session_id() {
        return guest_session_id;
    }

    public void setGuest_session_id(String guest_session_id) {
        this.guest_session_id = guest_session_id;
    }

    public String getExpires_at() {
        return expires_at;
    }

    public void setExpires_at(String expires_at) {
        this.expires_at = expires_at;
    }
}
