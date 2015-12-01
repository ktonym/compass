Ext.define('compass.fields.IntermediaryType',{
   extend: 'Ext.data.field.String',
    alias: 'data.field.intermediarytype',
    validators: {
        type: 'inclusion',
        list: [ 'AGENT','AGENCY','BROKER']
    }
});
