forEach: Aggregate
representativeFor: Aggregate
fileName: {{namePascalCase}}Controller.java
path: {{boundedContext.name}}/s20a01-service/src/main/java/com/posco/{{boundedContext.name}}/s20a01/service
---
package com.posco.{{boundedContext.name}}.s20a01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/{{namePlural}}")
public class {{namePascalCase}}Controller {

    private final {{namePascalCase}}RepositoryService {{nameCamelCase}}RepositoryService;

    @Autowired
    public {{namePascalCase}}Controller({{namePascalCase}}RepositoryService {{nameCamelCase}}RepositoryService) {
        this.{{nameCamelCase}}RepositoryService = {{nameCamelCase}}RepositoryService;
    }

    // Basic CRUD Operations
    @PostMapping
    public ResponseEntity<{{namePascalCase}}> create(@RequestBody {{namePascalCase}} {{nameCamelCase}}) {
        return ResponseEntity.ok({{nameCamelCase}}RepositoryService.save({{nameCamelCase}}));
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
        @RequestBody {{namePascalCase}} {{nameCamelCase}}) {
        return ResponseEntity.ok({{nameCamelCase}}RepositoryService.update(id, {{nameCamelCase}}));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable {{keyFieldDescriptor.className}} id) {
        {{nameCamelCase}}RepositoryService.delete(id);
        return ResponseEntity.ok().build();
    }

    // Custom Commands
    {{#commands}}
    {{#if isRestRepository}}
    {{else}}
    @PostMapping("/{id}/{{nameCamelCase}}")
    public ResponseEntity<{{../namePascalCase}}> {{nameCamelCase}}(
        @PathVariable("id") {{../keyFieldDescriptor.className}} id,
        @RequestBody {{namePascalCase}}Command command) {
        return ResponseEntity.ok({{../nameCamelCase}}RepositoryService.{{nameCamelCase}}(id, command));
    }
    {{/if}}
    {{/commands}}
}