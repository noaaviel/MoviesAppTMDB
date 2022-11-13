package com.example.moviesapptmdb;

public class SessionKey {

  /*
  * { example
  "success": true,
  "expires_at": "2016-08-26 17:04:39 UTC",
  "request_token": "ff5c7eeb5a8870efe3cd7fc5c282cffd26800ecd"
}
  *
  *
  * */
    boolean success;
    String request_token;
    String expires_at;

    public SessionKey(boolean success, String guest_session_id, String expires_at) {
        this.success = success;
        this.request_token = guest_session_id;
        this.expires_at = expires_at;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getRequest_token() {
        return request_token;
    }

    public void setRequest_token(String request_token) {
        this.request_token = request_token;
    }

    public String getExpires_at() {
        return expires_at;
    }

    public void setExpires_at(String expires_at) {
        this.expires_at = expires_at;
    }
}
