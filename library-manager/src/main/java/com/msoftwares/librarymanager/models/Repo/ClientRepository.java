package com.msoftwares.librarymanager.models.Repo;

import com.msoftwares.librarymanager.models.Entities.Client;
import com.msoftwares.librarymanager.models.Entities.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
