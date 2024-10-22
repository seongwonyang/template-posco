forEach: Aggregate
representativeFor: Aggregate
fileName: {{namePascalCase}}Service.java
path: {{boundedContext.name}}/service/{{options.packagePath}}/service
---
package {{options.package}}.service;

import {{options.package}}.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

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
    public void {{nameCamelCase}}(Long id{{#if (has fieldDescriptors)}}, {{namePascalCase}}Command {{nameCamelCase}}Command{{/if}}) {
        {{namePascalCase}} {{nameCamelCase}} = {{nameCamelCase}}Repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("{{namePascalCase}} not found"));
        {{nameCamelCase}}.{{nameCamelCase}}({{#if (has fieldDescriptors)}}{{nameCamelCase}}Command{{/if}});
        {{nameCamelCase}}Repository.save({{nameCamelCase}});
    }
    {{/if}}
    {{/commands}}

    {{#aggregateRoot.operations}}
    {{#setOperations ../commands name}}
    {{#isOverride}}
    @Override
    {{/isOverride}}
    {{^isRootMethod}}
    public {{returnType}} {{name}}(Long id) {
        {{../namePascalCase}} {{../nameCamelCase}} = {{../nameCamelCase}}Repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("{{../namePascalCase}} not found"));
        return {{../nameCamelCase}}.{{name}}();
    }
    {{/isRootMethod}}
    {{/setOperations}}
    {{/aggregateRoot.operations}}
}