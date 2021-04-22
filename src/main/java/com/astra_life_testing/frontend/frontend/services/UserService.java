package com.astra_life_testing.frontend.frontend.services;

import com.astra_life_testing.frontend.frontend.models.UserModel;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    static RestTemplate restTemplate = new RestTemplate();

    private static final String uri = "http://localhost:8088/api/user/";

    public static List<UserModel> allUser() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>("parameters",headers);

        List<UserModel> result = restTemplate
                .exchange(uri, HttpMethod.GET, entity, new ParameterizedTypeReference<List<UserModel>>() {
                }).getBody();

        return result;
    }

    public UserModel save(UserModel model){
        HttpEntity<UserModel> httpEntity = new HttpEntity<>(model);
        UserModel result = restTemplate.exchange(
                uri+"create",
                HttpMethod.POST,
                httpEntity,
                UserModel.class).getBody();

        return result;
    }

    public UserModel update(UserModel model){
        Map<Object, Object> params = new HashMap<>();
        HttpEntity<UserModel> httpEntity = new HttpEntity<>(model);
        params.put("id", model.getId());
        UserModel result = restTemplate.exchange(
                uri+"update/"+model.getId(),
                HttpMethod.POST,
                httpEntity,
                UserModel.class,
                params).getBody();
        return result;
    }

    public UserModel delete(String id){
        Map<Object, Object> params = new HashMap<>();
        params.put("id", id);
        UserModel result = restTemplate.exchange(
                uri+"delete/"+id,
                HttpMethod.GET,
                null,
                UserModel.class,
                params).getBody();

        return result;
    }


    public UserModel getById(String id){
        Map<String, String> params = new HashMap<>();
        params.put("id", id);
        UserModel result = restTemplate.exchange(
                uri+id,
                HttpMethod.GET,
                null,
                UserModel.class,
                params).getBody();

        return result;
    }

}
