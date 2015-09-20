/**
 * Created by akipkoech on 18/09/15.
 */

Ext.define('compass.model.security.Base',{
    extend: 'Ext.data.Model',
    requires: ['compass.util.Util'],

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