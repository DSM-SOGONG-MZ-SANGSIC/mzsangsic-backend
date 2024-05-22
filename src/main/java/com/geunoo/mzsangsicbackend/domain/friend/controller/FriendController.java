package com.geunoo.mzsangsicbackend.domain.friend.controller;

import com.geunoo.mzsangsicbackend.domain.friend.controller.dto.response.QueryApplyUsersResponse;
import com.geunoo.mzsangsicbackend.domain.friend.controller.dto.response.QueryFriendsResponse;
import com.geunoo.mzsangsicbackend.domain.friend.service.AcceptFriendService;
import com.geunoo.mzsangsicbackend.domain.friend.service.CreateFriendService;
import com.geunoo.mzsangsicbackend.domain.friend.service.DeleteFriendService;
import com.geunoo.mzsangsicbackend.domain.friend.service.QueryApplyUsersService;
import com.geunoo.mzsangsicbackend.domain.friend.service.QueryFriendsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/friends")
@RestController
public class FriendController {

    private final CreateFriendService createFriendService;
    private final QueryApplyUsersService queryApplyUsersService;
    private final AcceptFriendService acceptFriendService;
    private final DeleteFriendService deleteFriendService;
    private final QueryFriendsService queryFriendsService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{user-id}")
    public void createFriend(@PathVariable("user-id") Long userId) {
        createFriendService.execute(userId);
    }

    @GetMapping("/applied")
    public QueryApplyUsersResponse queryApplyUser() {
        return queryApplyUsersService.execute();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{user-id}")
    public void acceptFriend(@PathVariable("user-id") Long applyUserId) {
        acceptFriendService.execute(applyUserId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{user-id}")
    public void deleteFriend(@PathVariable("user-id") Long applyUserId) {
        deleteFriendService.execute(applyUserId);
    }

    @GetMapping
    public QueryFriendsResponse queryFriends() {
        return queryFriendsService.execute();
    }
}
