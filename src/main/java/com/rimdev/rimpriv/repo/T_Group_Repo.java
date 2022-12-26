package com.rimdev.rimpriv.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

import com.rimdev.rimpriv.entities.T_Group;

@Repository
public interface T_Group_Repo extends JpaRepository<T_Group, Integer> {

    Optional<T_Group> findByGroupId(String groupid);

    Optional<List<T_Group>> findNotDele();

}
