package com.bba.client;

import com.bba.client.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<ClientDto> list() {
        return clientService.getClients(1);
    }

    @GetMapping("/{id}")
    public ClientDto get(@PathVariable("id") Integer clientId) {
        return clientService.getClient(1, clientId);
    }

    @PostMapping
    public ClientDto save(@RequestBody ClientDto client) {
        return clientService.saveClient(1, client);
    }

    @PutMapping
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void update(@RequestBody ClientDto client) {
        clientService.updateClient(1, client);
    }
}
