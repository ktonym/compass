Ext.define('compass.model.Base',{
    extend: 'Ext.data.Model',

    requires: ['compass.util.Util'],

    schema: {
        namespace: 'compass.model',
        urlPrefix: 'compass',
        proxy: {
            type: 'ajax',
            api : {
                create: '{prefix}/{entityName:lowercase}/store.json',
                read: '{prefix}/{entityName:lowercase}/findAll.json',
                update: '{prefix}/{entityName:lowercase}/store.json',
                destroy: '{prefix}/{entityName:lowercase}/remove.json'
            },
            reader: {
                type: 'json',
                rootProperty: 'data'
            },
            writer: {
                type: 'json',
                writeAllFields: true,
                rootProperty: 'data',
                allowSingle: false,
                encode: true
            },
            listeners: {
                exception: function(proxy, response, operation){
                    compass.util.Util.showErrorMsg(response.responseText);
                }
            }
        }
    }

});
