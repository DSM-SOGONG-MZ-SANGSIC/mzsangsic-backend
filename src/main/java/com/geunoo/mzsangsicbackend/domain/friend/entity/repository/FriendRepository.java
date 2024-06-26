package com.geunoo.mzsangsicbackend.domain.friend.entity.repository;

import com.geunoo.mzsangsicbackend.domain.friend.entity.Friend;
import com.geunoo.mzsangsicbackend.domain.quiz.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friend, Long> {

    List<Friend> findAllByApplyUserIdOrApplieUserIdAndIsAccept(Long userId1, Long userId2, boolean accept);

    List<Friend> findAllByApplyUserIdAndIsAccept(Long applyUserId, boolean accept);

    Optional<Friend> findByApplieUserIdAndApplyUserId(Long appliedUserId, Long applyUserId);

    List<Friend> findAllByApplieUserIdAndIsAccept(Long appliedUserId, boolean accept);
}
