package pl.secrets.secrets.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.secrets.secrets.models.Secret;
import pl.secrets.secrets.repositories.SecretsRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class SecretsController {

    @Autowired
    private SecretsRepository secretsRepository;

    @GetMapping("/secrets")
    public List<Secret> getAllSecrets() {
        Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
        return secretsRepository.findAll(sortByCreatedAtDesc);
    }

    @PostMapping("/secrets")
    public Secret createSecret(@Valid @RequestBody Secret secret) {
        return secretsRepository.save(secret);
    }

    @GetMapping(value = "/secrets/{id}")
    public ResponseEntity<Secret> getSecretById(@PathVariable("id") String id) {
        return secretsRepository.findById(id)
                .map(secret -> ResponseEntity.ok().body(secret))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value = "/secrets/{id}")
    public ResponseEntity<Secret> updateSecret(@PathVariable("id") String id,
                                               @Valid @RequestBody Secret secret) {
        return secretsRepository.findById(id)
                .map(secretData -> {
                    secretData.setTitle(secret.getTitle());
                    Secret updatedSecret = secretsRepository.save(secretData);
                    return ResponseEntity.ok().body(updatedSecret);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/secrets/{id}")
    public ResponseEntity<?> deleteSecret(@PathVariable("id") String id) {
        return secretsRepository.findById(id)
                .map(secret -> {
                    secretsRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}