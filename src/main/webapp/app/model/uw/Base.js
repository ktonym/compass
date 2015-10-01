Ext.define('compass.model.uw.Base',{
    extend: 'Ext.data.Model',
    requires: ['compass.util.Util'],

    schema: {
        namespace: 'compass.model.uw',
        urlPrefix: 'uw',
        proxy: {
            type: 'ajax',
            api: {
                create: 'compass/{prefix}/{entityName:lowercase}/store',
                read: 'compass/{prefix}/{entityName:lowercase}/findAll',
                update: 'compass/{prefix}/{entityName:lowercase}/store',
                destroy: 'compass/{prefix}/{entityName:lowercase}/remove'
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
                allowSingle: true
            },
            listeners: {
                exception: function(proxy,response,operation){
                    compass.util.Util.showErrorMsg(response.responseText);
                }
            }

        }
    }

});