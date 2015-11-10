Ext.define('compass.fields.MemberType',{
    extend: 'Ext.data.field.String',
    alias: 'data.field.membertype',
    validators: {
        type: 'inclusion',
        list: [ 'PRINCIPAL','SPOUSE','CHILD','PARENT','GRANDPARENT','OTHER' ]
    }
});
