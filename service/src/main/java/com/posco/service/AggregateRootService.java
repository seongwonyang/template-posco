forEach: Aggregate
representativeFor: Aggregate
fileName: {{namePascalCase}}RepositoryService.java
path: {{boundedContext.name}}/s20a01-service/{{options.packagePath}}/s20a01/service
---
package {{options.package}}.s20a01.service;

import {{options.package}}.s20a01.domain.{{namePascalCase}};
{{#commands}}
import {{options.package}}.s20a01.service.{{namePascalCase}}Command;
{{/commands}}
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping(value="/{{namePlural}}")
@Service
@Transactional
public class {{namePascalCase}}RepositoryService {

    private final {{namePascalCase}}Repository {{nameCamelCase}}Repository;

    @Autowired
    public {{namePascalCase}}RepositoryService({{namePascalCase}}Repository {{nameCamelCase}}Repository) {
        this.{{nameCamelCase}}Repository = {{nameCamelCase}}Repository;
    }

    {{#commands}}
    {{#if isRestRepository}}
    {{else}}
    @RequestMapping(value = "/{id}/{{nameCamelCase}}", method = RequestMethod.POST)
    public {{../namePascalCase}} {{nameCamelCase}}({{namePascalCase}}Command {{nameCamelCase}}Command) {
        {{../namePascalCase}} {{../nameCamelCase}} = {{../nameCamelCase}}Repository
            .findById({{nameCamelCase}}Command.getId())
            .orElseThrow(() -> new EntityNotFoundException("{{../namePascalCase}} not found"));
        
        // Map command fields to method parameters
        {{../nameCamelCase}}.{{nameCamelCase}}(
            {{#fieldDescriptors}}
            {{../nameCamelCase}}Command.get{{pascalCase nameCamelCase}}(){{^@last}},{{/@last}}
            {{/fieldDescriptors}}
        );
        
        // 레포지토리에 저장
        return {{../nameCamelCase}}Repository.save({{../nameCamelCase}});
    }
    {{/if}}
    {{/commands}}

    {{#aggregateRoot.operations}}
    {{#setOperations ../commands name}}
    {{#isOverride}}
    @Override
    {{/isOverride}}
    {{^isRootMethod}}
    public {{returnType}} {{name}}({{../namePascalCase}} {{../nameCamelCase}}){
        // 비즈니스 로직 호출
        {{returnType}} result = {{../nameCamelCase}}.{{name}}();
        
        // 필요한 경우 레포지토리에 저장
        {{nameCamelCase}}Repository.save({{../nameCamelCase}});
        
        return result;
    }
    {{/isRootMethod}}
    {{/setOperations}}
    {{/aggregateRoot.operations}}
}