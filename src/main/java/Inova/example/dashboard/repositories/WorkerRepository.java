package Inova.example.dashboard.repositories;

import Inova.example.dashboard.models.WorkerTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerRepository extends JpaRepository<WorkerTable,Integer> {
}
