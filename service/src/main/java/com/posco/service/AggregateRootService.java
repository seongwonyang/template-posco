forEach: Aggregate
representativeFor: Aggregate
fileName: {{namePascalCase}}RepositoryService.java
path: {{boundedContext.name}}/s20a01-service/src/main/java/com/posco/{{boundedContext.name}}/s20a01/service
---
package com.posco.{{boundedContext.name}}.s20a01.service;

import com.posco.{{boundedContext.name}}.s20a01.domain.{{namePascalCase}};
import com.posco.{{boundedContext.name}}.s20a01.domain.{{namePascalCase}}Repository;
{{#commands}}
{{^isRestRepository}}
import com.posco.{{boundedContext.name}}.s20a01.service.{{namePascalCase}}Command;
{{/isRestRepository}}
{{/commands}}
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

@RepositoryRestController
@RequestMapping("/{{namePlural}}")
public class {{namePascalCase}}RepositoryService {

    private final {{namePascalCase}}Repository {{nameCamelCase}}Repository;

    @Autowired
    public {{namePascalCase}}RepositoryService({{namePascalCase}}Repository {{nameCamelCase}}Repository) {
        this.{{nameCamelCase}}Repository = {{nameCamelCase}}Repository;
    }

    @GetMapping
    public ResponseEntity<List<{{namePascalCase}}>> getAll{{namePascalCase}}s() {
        return ResponseEntity.ok({{nameCamelCase}}Repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<{{namePascalCase}}> get{{namePascalCase}}(@PathVariable("id") {{keyFieldDescriptor.className}} id) {
        return {{nameCamelCase}}Repository.findById(id)
            .map(ResponseEntity::ok)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "{{namePascalCase}} not found"));
    }

    @PostMapping
    public ResponseEntity<{{namePascalCase}}> create{{namePascalCase}}(@RequestBody {{namePascalCase}} {{nameCamelCase}}) {
        {{namePascalCase}} saved{{namePascalCase}} = {{nameCamelCase}}Repository.save({{nameCamelCase}});
        return ResponseEntity.status(HttpStatus.CREATED).body(saved{{namePascalCase}});
    }

    @PutMapping("/{id}")
    public ResponseEntity<{{namePascalCase}}> update{{namePascalCase}}(
        @PathVariable("id") {{keyFieldDescriptor.className}} id,
        @RequestBody {{namePascalCase}} {{nameCamelCase}}) {
        
        if (!{{nameCamelCase}}Repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "{{namePascalCase}} not found");
        }
        
        {{nameCamelCase}}.set{{keyFieldDescriptor.namePascalCase}}(id);
        return ResponseEntity.ok({{nameCamelCase}}Repository.save({{nameCamelCase}}));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete{{namePascalCase}}(@PathVariable("id") {{keyFieldDescriptor.className}} id) {
        if (!{{nameCamelCase}}Repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "{{namePascalCase}} not found");
        }
        
        {{nameCamelCase}}Repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    {{#commands}}
    {{^isRestRepository}}
    @PostMapping("/{id}/{{nameCamelCase}}")
    public ResponseEntity<{{../namePascalCase}}> {{nameCamelCase}}(
        @PathVariable("id") {{../keyFieldDescriptor.className}} id,
        @RequestBody {{namePascalCase}}Command command) {
        
        {{../namePascalCase}} {{../nameCamelCase}} = {{../nameCamelCase}}Repository
            .findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "{{../namePascalCase}} not found"));
        
        {{../nameCamelCase}}.{{nameCamelCase}}(
            {{#fieldDescriptors}}
            {{^isKey}}
            command.get{{pascalCase nameCamelCase}}(){{^@last}},{{/@last}}
            {{/isKey}}
            {{/fieldDescriptors}}
        );
        
        return ResponseEntity.ok({{../nameCamelCase}}Repository.save({{../nameCamelCase}}));
    }
    {{/isRestRepository}}
    {{/commands}}
}