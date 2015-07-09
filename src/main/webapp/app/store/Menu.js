Ext.define('compass.store.Menu',{
    extend: 'Ext.data.Store',
    requires: [
          'compass.util.Util'
    ],

    model: 'compass.model.menu.Accordion',

    proxy: {
        type: 'ajax',
        url: 'compass/security/menu/findAll.json',

        reader: {
            type: 'json',
            rootProperty: 'data'
        },

        listeners: {
            exception: function(proxy,response,operation){
                compass.util.Util.showErrorMsg(response.responseText);
            }
        }

    }


});