package com.github.icovn.modal;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor(staticName = "of")
@Data
public class Namespace {

  private String name;
  private List<Module> modules = new ArrayList<>();
}
