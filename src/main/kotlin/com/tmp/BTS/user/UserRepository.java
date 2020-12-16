package com.tmp.BTS.user;

import com.tmp.BTS.user.model.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    @NotNull
    User getById(@NotNull Object userId);

    @NotNull
    User findByIdAndPassword(@Nullable String id, @Nullable String password);
}
