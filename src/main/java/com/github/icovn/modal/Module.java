package com.github.icovn.modal;

import java.util.List;
import lombok.Data;

@Data
public class Module {

  private String domain = "";
  private String name = "";
  private String version = "";

  private Module(List<Container> containers, List<PublicEndPoint> publicEndpoints){
    if(publicEndpoints != null && !publicEndpoints.isEmpty()
        && publicEndpoints.get(0).getHostname() != null
    ){
      this.domain = publicEndpoints.get(0).getHostname();
    }

    if(!containers.isEmpty()){
      this.name = containers.get(0).getName();
      this.version = containers.get(0).getImage();
    }
  }

  public static Module of(Workload workload){
    return new Module(workload.getContainers(), workload.getPublicEndpoints());
  }
}
