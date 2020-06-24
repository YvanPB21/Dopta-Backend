package com.dopta.repository;

import com.dopta.model.UserSignIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserSignIn,Long> {
    UserSignIn findOneByUsername(String username);

    @Modifying//INSERT, UPDATE , DELETE ==SQL
    @Query(value="INSERT INTO user_roles(user_id,role_id) VALUES(:userId,:roleId)",nativeQuery =true)
    void register(@Param("userId") Long userId, @Param("roleId") Long roleId );
}
