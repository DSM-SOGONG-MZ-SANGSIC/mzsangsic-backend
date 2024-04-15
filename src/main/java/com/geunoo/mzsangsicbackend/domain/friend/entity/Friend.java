package com.geunoo.mzsangsicbackend.domain.friend.entity;

import com.geunoo.mzsangsicbackend.domain.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apply_user_id")
    private User applyUser;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "applied_user_id")
    private User applieUser;

    @NotNull
    @Column(columnDefinition = "TINYINT(1)")
    private boolean isAccept;

    @Builder
    public Friend(User applyUser, User applieUser, boolean isAccept) {
        this.applyUser = applyUser;
        this.applieUser = applieUser;
        this.isAccept = isAccept;
    }
}
