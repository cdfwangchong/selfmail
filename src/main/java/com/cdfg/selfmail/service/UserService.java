package com.cdfg.selfmail.service;

import java.util.Map;

public interface UserService {
    public Map<String, String> getCoupon(Map<String, String> param);

    public Map<String, String> login(Map<String, String> param);

    public Map<String, String> changPass(Map<String, String> param);

    public Map<String, String> getVercode(Map<String, String> param);

    public Map<String, String> weChat(Map<String, String> param);
}
