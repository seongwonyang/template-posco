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
    public {{../namePascalCase}} {{nameCamelCase}}({{../keyFieldDescriptor.className}} id{{#if (has fieldDescriptors)}}, {{namePascalCase}}Command {{nameCamelCase}}Command{{/if}}) {
        {{../namePascalCase}} {{../nameCamelCase}} = {{nameCamelCase}}Repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("{{../namePascalCase}} not found"));
        
        // 비즈니스 로직 호출
        {{../nameCamelCase}}.{{nameCamelCase}}({{#if (has fieldDescriptors)}}{{nameCamelCase}}Command{{/if}});
        
        // 레포지토리에 저장
        return {{nameCamelCase}}Repository.save({{../nameCamelCase}});
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

    {{#policyList}}
    {{#relationEventInfo}}
    public void {{../nameCamelCase}}({{eventValue.namePascalCase}} {{eventValue.nameCamelCase}}){
        // 이벤트 처리 로직
        {{../../namePascalCase}} {{../../nameCamelCase}} = {{../../nameCamelCase}}Repository.findById({{eventValue.nameCamelCase}}.getId())
            .orElseThrow(() -> new EntityNotFoundException("{{../../namePascalCase}} not found"));
        
        {{../../nameCamelCase}}.{{../nameCamelCase}}({{eventValue.nameCamelCase}});
        
        {{../../nameCamelCase}}Repository.save({{../../nameCamelCase}});
    }
    {{/relationEventInfo}}
    {{/policyList}}
}