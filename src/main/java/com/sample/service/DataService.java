package com.sample.service;

import java.util.List;

public interface DataService<T> {

  List<T> getAll();

  List<T> getByRelationId(int relationId) throws Exception;

  T update(int id, T target) throws Exception;

}
