package com.auth.server.api.repository;

import com.auth.server.api.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity,Long> {

    MemberEntity findByEmail(String email);

    Integer countByEmail(String email);

}
