package com.geunoo.mzsangsicbackend.domain.user.entity.repository;

import com.geunoo.mzsangsicbackend.domain.user.entity.UserCategory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserCategoryRepository extends CrudRepository<UserCategory, Long> {

    List<UserCategory> findAllByUserId(Long userId);
}
