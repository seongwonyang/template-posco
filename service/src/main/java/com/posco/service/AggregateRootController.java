forEach: Aggregate
representativeFor: Aggregate
fileName: {{namePascalCase}}Controller.java
path: {{boundedContext.name}}/s20a01-service/src/main/java/com/posco/{{boundedContext.name}}/s20a01/service
---
package com.posco.{{boundedContext.name}}.s20a01.service;

import com.posco.{{boundedContext.name}}.s20a01.domain.{{namePascalCase}};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/{{namePlural}}")
public class {{namePascalCase}}Controller {

    private final {{namePascalCase}}RepositoryService {{nameCamelCase}}RepositoryService;

    @Autowired
    public {{namePascalCase}}Controller({{namePascalCase}}RepositoryService {{nameCamelCase}}RepositoryService) {
        this.{{nameCamelCase}}RepositoryService = {{nameCamelCase}}RepositoryService;
    }

    @PostMapping
    public ResponseEntity<{{namePascalCase}}> create(@RequestBody Create{{namePascalCase}}Command command) {
        return ResponseEntity.ok({{nameCamelCase}}RepositoryService.create(command));
    }

    @GetMapping("/{id}")
    public ResponseEntity<{{namePascalCase}}> findById(@PathVariable {{keyFieldDescriptor.className}} id) {
        return ResponseEntity.ok({{nameCamelCase}}RepositoryService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<{{namePascalCase}}>> findAll() {
        return ResponseEntity.ok({{nameCamelCase}}RepositoryService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<{{namePascalCase}}> update(
        @PathVariable {{keyFieldDescriptor.className}} id,
        @RequestBody Update{{namePascalCase}}Command command) {
        return ResponseEntity.ok({{nameCamelCase}}RepositoryService.update(id, command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable {{keyFieldDescriptor.className}} id) {
        {{nameCamelCase}}RepositoryService.delete(id);
        return ResponseEntity.ok().build();
    }

    {{#commands}}
    {{#if isRestRepository}}
    {{else}}
    @PostMapping("/{id}/{{nameCamelCase}}")
    public ResponseEntity<{{../namePascalCase}}> {{nameCamelCase}}(
        @PathVariable("id") {{../keyFieldDescriptor.className}} id,
        @Valid @RequestBody {{namePascalCase}}Command command) {
        {{../namePascalCase}} {{../nameCamelCase}} = {{../nameCamelCase}}RepositoryService.findById(id);
        
        {{../nameCamelCase}}.{{nameCamelCase}}(
            {{#fieldDescriptors}}
            {{^isKey}}
            command.get{{pascalCase nameCamelCase}}(){{^@last}},{{/@last}}
            {{/isKey}}
            {{/fieldDescriptors}}
        );
        
        return ResponseEntity.ok({{../nameCamelCase}}RepositoryService.save({{../nameCamelCase}}));
    }
    {{/if}}
    {{/commands}}
}