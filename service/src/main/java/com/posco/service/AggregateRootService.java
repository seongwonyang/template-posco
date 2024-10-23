forEach: Aggregate
representativeFor: Aggregate
fileName: {{namePascalCase}}RepositoryService.java
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
    public void {{nameCamelCase}}({{namePascalCase}} {{nameCamelCase}}{{#if (has fieldDescriptors)}}, {{namePascalCase}}Command {{nameCamelCase}}Command{{/if}}) {
        // Implement business logic here
        {{#if (has fieldDescriptors)}}
        // Update {{nameCamelCase}} with {{nameCamelCase}}Command
        {{/if}}
        {{nameCamelCase}}Repository.save({{nameCamelCase}});

        {{#triggerByCommand}}
        {{eventValue.namePascalCase}} {{eventValue.nameCamelCase}} = new {{eventValue.namePascalCase}}({{nameCamelCase}});
        {{#correlationGetSet .. eventValue}}
        {{#if target}}
        {{../eventValue.nameCamelCase}}.set{{target.namePascalCase}}({{nameCamelCase}}Command.get{{source.namePascalCase}}());
        {{/if}}
        {{/correlationGetSet}}
        {{eventValue.nameCamelCase}}.publishAfterCommit();
        {{/triggerByCommand}}
    }
    {{/if}}
    {{/commands}}

    {{#policyList}}
    {{#relationEventInfo}}
    public static void {{../nameCamelCase}}({{eventValue.namePascalCase}} {{eventValue.nameCamelCase}}){
        // Implement business logic here
        {{../../namePascalCase}}Repository {{../../nameCamelCase}}Repository = {{../../boundedContext.namePascalCase}}Application.applicationContext.getBean({{../../namePascalCase}}Repository.class);

        // Example implementation:
        // {{../../namePascalCase}} {{../../nameCamelCase}} = new {{../../namePascalCase}}();
        // {{../../nameCamelCase}}Repository.save({{../../nameCamelCase}});

        // {{#../relationExampleEventInfo}}
        // {{eventValue.namePascalCase}} {{eventValue.nameCamelCase}} = new {{eventValue.namePascalCase}}({{../../../nameCamelCase}});
        // {{eventValue.nameCamelCase}}.publishAfterCommit();
        // {{/../relationExampleEventInfo}}
    }
    {{/relationEventInfo}}
    {{/policyList}}
}