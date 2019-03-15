package com.github.icovn.modal;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Workload {

  private String namespaceId;
  private List<Container> containers = new ArrayList<>();
  private List<PublicEndPoint> publicEndpoints = new ArrayList<>();
}
