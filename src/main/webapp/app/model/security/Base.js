Ext.define('compass.model.security.Base', {
    extend: 'Ext.data.Model',

    requires: ['compass.util.Util'],

    idProperty: 'id',
    
    fields: [
        { name: 'id', type: 'int' }

    ],

    schema: {
        namespace: 'compass.model.security',
        //urlPrefix: 'user',
        proxy: {
            type: 'ajax',
            api: {
                create: 'compass/{entityName:lowercase}/store',
                read: 'compass/{entityName:lowercase}/findAll',
                update: 'compass/{entityName:lowercase}/store',
                destroy: 'compass/{entityName:lowercase}/remove'
            },
            reader: {
                type: 'json',
                rootProperty: 'data'
            },
            writer: {
                type: 'json',
                writeAllFields: true,
                encode: true,
                rootProperty: 'data',
                allowSingle: false
            },
            listeners: {
                exception: function(proxy,response,operation){
                    compass.util.Util.showErrorMsg(response.responseText);
                }
            }

        }
    }

});
