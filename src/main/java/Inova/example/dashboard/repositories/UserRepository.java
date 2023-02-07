package Inova.example.dashboard.repositories;

import Inova.example.dashboard.models.UserTable;
import Inova.example.dashboard.models.WorkerTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserTable,Integer> {
}
