package com.footballdle.playerService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.footballdle.playerService.Model.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query(value = "SELECT * FROM :tableName ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Player findRandomPlayerFromTable(@Param("tableName") String tableName);
}
