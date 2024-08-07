package com.lspeixotodev.family_activity_control_api.repository;

import com.lspeixotodev.family_activity_control_api.entity.bill.Bill;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BillRepository extends JpaRepository<Bill, UUID> {

    boolean existsByTitle(String title);

    @Query(nativeQuery = true, value = """
                    SELECT * from bill b
                    WHERE b.user_id = :userId
                    AND to_tsvector(title) @@ to_tsquery(:title);
            """)
    List<Bill> findByTitleAndUserId(@Param("title") String title, @Param("userId") UUID userId);

    @Query(nativeQuery = true, value = "SELECT * from bill b WHERE b.user_id = :userId")
    List<Bill> findAllBillsByUser(@Param("userId") UUID userId);

    @NotNull
    @Override
    List<Bill> findAll();
}
