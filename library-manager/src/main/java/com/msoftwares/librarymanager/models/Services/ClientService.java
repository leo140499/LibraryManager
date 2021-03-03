package com.msoftwares.librarymanager.models.Services;

import com.msoftwares.librarymanager.models.Entities.Client;
import com.msoftwares.librarymanager.models.Repo.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    //save
    public void saveClient(Client client){ clientRepository.save(client);}

    //getAll
    public List<Client> getClients(){ return clientRepository.findAll();}

    //getById
    public Client findById(int id){return clientRepository.findById(id).get();}

    //delete
    public void deleteClient(Client client){clientRepository.delete(client);}


}
