forEach: View
path: {{boundedContext.name}}/{{{options.packagePath}}}/domain
except: {{#checkExtend this}}{{/checkExtend}}

package {{options.package}}.domain;

import java.util.Date;
import lombok.Data;

@Data
public class {{namePascalCase}}Query {

    {{#queryParameters}}
    private {{className}} {{nameCamelCase}};
    {{/queryParameters}}
}
<function>
window.$HandleBars.registerHelper('checkExtend', function (readModel) {
    if(readModel.queryOption.useDefaultUri != true && readModel.queryOption.apiPath !=''){
        return false;
    }
});
</function>