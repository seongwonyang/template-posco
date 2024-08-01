forEach: Relation
fileName: {{target.aggregate.namePascalCase}}Service.java
path: {{source.boundedContext.name}}/{{options.packagePath}}/external
except: {{contexts.except}}
ifDuplicated: merge
---

package {{options.package}}.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;


{{#if value.fallback}}
@FeignClient(name = "{{target.boundedContext.name}}", url = "{{apiVariable target.boundedContext.name}}", fallback = {{target.aggregate.namePascalCase}}ServiceImpl.class)
{{else}}
@FeignClient(name = "{{target.boundedContext.name}}", url = "{{apiVariable target.boundedContext.name}}")
{{/if}}
 
{{#target}}
public interface {{aggregate.namePascalCase}}Service {
    {{#if queryOption.multipleResult}}
    @GetMapping(path="/{{aggregate.namePlural}}/search/{{#if queryOption.useDefaultUri}}findBy{{queryOption.apiPath}}{{else}}findBy{{namePascalCase}}{{/if}}")
    public List<{{aggregate.namePascalCase}}> {{#if queryOption.useDefaultUri}}{{queryOption.apiPath}}{{else}}{{nameCamelCase}}{{/if}}({{namePascalCase}}Query {{nameCamelCase}}query);
    {{else}}
    @GetMapping(path="/{{aggregate.namePlural}}/search/{{#if queryOption.apiPath}}findBy{{queryOption.apiPath}}{{else}}findBy{{namePascalCase}}{{/if}}")
    public {{aggregate.namePascalCase}} {{#if queryOption.useDefaultUri}}{{queryOption.apiPath}}{{else}}{{nameCamelCase}}{{/if}}(@PathVariable{{#queryParameters}}{{#if isKey}}("{{nameCamelCase}}") {{className}} {{nameCamelCase}}, ({{/if}}){{/queryParameters}}{{namePascalCase}}Query {{nameCamelCase}}query);
    {{/if}}

}

{{/target}}



<function>
 

    let isGetInvocation = ((this.source._type.endsWith("Command") || this.source._type.endsWith("Policy")) && (this.target._type.endsWith("View") && this.target.dataProjection==("query-for-aggregate")))
    let isPostInvcation = ((this.source._type.endsWith("Event") || this.source._type.endsWith("Policy")) && this.target._type.endsWith("Command"))
    let isExternalInvocation = (this.source.boundedContext.name != this.target.boundedContext.name)

    this.contexts.except = !(isExternalInvocation && isGetInvocation)
 
if(!this.contexts.except){
 
}
window.$HandleBars.registerHelper('changeUpper', function (name) {
    return name.charAt(0).toUpperCase() + name.slice(1);
});
 
</function>