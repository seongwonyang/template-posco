forEach: Aggregate
representativeFor: Aggregate
fileName: {{namePascalCase}}RepositoryService.java
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

@Service
@Transactional
public class {{namePascalCase}}Service {

    private final {{namePascalCase}}Repository {{nameCamelCase}}Repository;

    @Autowired
    public {{namePascalCase}}Service({{namePascalCase}}Repository {{nameCamelCase}}Repository) {
        this.{{nameCamelCase}}Repository = {{nameCamelCase}}Repository;
    }

    {{#commands}}
    {{#if isRestRepository}}
    {{else}}
    public {{../namePascalCase}} {{nameCamelCase}}({{../keyFieldDescriptor.className}} {{../keyFieldDescriptor.nameCamelCase}}, {{namePascalCase}}Command command) {
        {{../namePascalCase}} {{../nameCamelCase}} = {{../nameCamelCase}}Repository
            .findById({{../keyFieldDescriptor.nameCamelCase}})
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "{{../namePascalCase}} not found"));
        
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