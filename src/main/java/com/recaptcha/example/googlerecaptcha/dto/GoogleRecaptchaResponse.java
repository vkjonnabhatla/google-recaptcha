package com.recaptcha.example.googlerecaptcha.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GoogleRecaptchaResponse {
    @JsonProperty("success")
    private boolean success;
    @JsonProperty("challenge_ts")
    private String challengeTS;
    @JsonProperty("hostname")
    private String hostname;
    @JsonProperty("error-codes")
    private List<String> errorCodes;

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getChallengeTS() {
        return challengeTS;
    }

    public void setChallengeTS(String challengeTS) {
        this.challengeTS = challengeTS;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public List<String> getErrorCodes() {
        return errorCodes;
    }

    public void setErrorCodes(List<String> errorCodes) {
        this.errorCodes = errorCodes;
    }
}
