package com.github.icovn.controller;

import com.github.icovn.config.RancherAccountConfig;
import com.github.icovn.modal.Account;
import com.github.icovn.modal.Module;
import com.github.icovn.modal.Namespace;
import com.github.icovn.modal.Workload;
import com.github.icovn.modal.Workloads;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class StatusController {

  private final RancherAccountConfig rancherAccountConfig;

  public StatusController(
      RancherAccountConfig rancherAccountConfig
  ){
    this.rancherAccountConfig = rancherAccountConfig;
  }

  @GetMapping("/status")
  public List<Namespace> status(){
    Map<String, List<Module>> map = new HashMap<>();
    for(Account account: rancherAccountConfig.getAccounts()){
      Workloads workloads = getWorkloads(account);
      for(Workload workload: workloads.getData()){
        if(!map.containsKey(workload.getNamespaceId())){
          List<Module> modules = new ArrayList<>();
          modules.add(Module.of(workload));
          map.put(workload.getNamespaceId(), modules);
        }else {
          List<Module> tmp = map.get(workload.getNamespaceId());
          tmp.add(Module.of(workload));
          map.put(workload.getNamespaceId(), tmp);
        }
      }
    }

    List<Namespace> data = new ArrayList<>();
    for (Map.Entry<String, List<Module>> entry : map.entrySet()) {
      data.add(Namespace.of(entry.getKey(), entry.getValue()));
    }
    return data;
  }

  private Workloads getWorkloads(Account account){
    RestTemplate restTemplate = new RestTemplate();
    HttpEntity<Workloads> request = new HttpEntity<>(createHeaders(account.getAccessKey(), account.getSecretKey()));
    ResponseEntity<Workloads> response = restTemplate.exchange(
        account.getUrl(),
        HttpMethod.GET,
        request,
        Workloads.class
    );
    return response.getBody();
  }

  private HttpHeaders createHeaders(String username, String password){
    return new HttpHeaders() {{
      String auth = username + ":" + password;
      byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")) );
      String authHeader = "Basic " + new String( encodedAuth );
      set("Authorization", authHeader);
    }};
  }
}
