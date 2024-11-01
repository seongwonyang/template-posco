forEach: Aggregate
representativeFor: Aggregate
fileName: {{namePascalCase}}RepositoService.java
path: {{boundedContext.name}}/s20a01-service/src/main/java/com/posco/{{boundedContext.name}}/s20a01/service
---
package com.posco.{{boundedContext.name}}.s20a01.service;

import com.posco.{{boundedContext.name}}.s20a01.domain.{{namePascalCase}};
import com.posco.{{boundedContext.name}}.s20a01.domain.{{namePascalCase}}Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
@Transactional
public class {{namePascalCase}}RepositoryService {

    private final {{namePascalCase}}Repository {{nameCamelCase}}Repository;

    @Autowired
    public {{namePascalCase}}RepositoryService({{namePascalCase}}Repository {{nameCamelCase}}Repository) {
        this.{{nameCamelCase}}Repository = {{nameCamelCase}}Repository;
    }

    public {{namePascalCase}} create(Create{{namePascalCase}}Command command) {
        {{namePascalCase}} {{nameCamelCase}} = new {{namePascalCase}}();
        {{#fieldDescriptors}}
        {{nameCamelCase}}.set{{pascalCase nameCamelCase}}(command.get{{pascalCase nameCamelCase}}());
        {{/fieldDescriptors}}
        
        return {{nameCamelCase}}Repository.save({{nameCamelCase}});
    }

    public {{namePascalCase}} findById({{keyFieldDescriptor.className}} id) {
        return {{nameCamelCase}}Repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "{{namePascalCase}} not found"));
    }

    public List<{{namePascalCase}}> findAll() {
        return (List<{{namePascalCase}}>){{nameCamelCase}}Repository.findAll();
    }

    public {{namePascalCase}} update({{keyFieldDescriptor.className}} id, Update{{namePascalCase}}Command command) {
        {{namePascalCase}} existing = findById(id);
        
        {{#fieldDescriptors}}
        if (command.get{{pascalCase nameCamelCase}}() != null) {
            existing.set{{pascalCase nameCamelCase}}(command.get{{pascalCase nameCamelCase}}());
        }
        {{/fieldDescriptors}}
        
        return {{nameCamelCase}}Repository.save(existing);
    }

    public void delete({{keyFieldDescriptor.className}} id) {
        {{namePascalCase}} {{nameCamelCase}} = findById(id);
        {{nameCamelCase}}Repository.delete({{nameCamelCase}});
    }

    {{#commands}}
    {{#if isRestRepository}}
    public {{../namePascalCase}} {{nameCamelCase}}({{../keyFieldDescriptor.className}} {{../keyFieldDescriptor.nameCamelCase}}, {{namePascalCase}}Command command) {
        {{../namePascalCase}} {{../nameCamelCase}} = findById({{../keyFieldDescriptor.nameCamelCase}});
        
        {{../nameCamelCase}}.{{nameCamelCase}}(
            {{^isKey}}
            command.get{{pascalCase nameCamelCase}}(){{^@last}},{{/@last}}
            {{/isKey}}
        );
        
        return {{../nameCamelCase}}Repository.save({{../nameCamelCase}});
    }
    {{/if}}
    {{/commands}}
}