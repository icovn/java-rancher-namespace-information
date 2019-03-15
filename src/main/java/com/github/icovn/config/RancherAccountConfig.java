package com.github.icovn.config;

import com.github.icovn.modal.Account;
import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="application.rancher")
@Data
public class RancherAccountConfig {

  private List<Account> accounts;
}
