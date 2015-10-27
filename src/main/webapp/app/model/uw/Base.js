Ext.define('compass.model.uw.Base',{
    extend: 'compass.model.Base',

    fields: [
        {
            name: 'lastUpdate',
            type: 'date',
            dateFormat: 'Y-m-j H:i:s'
        }
    ],

    validators: {
        lastUpdate: 'presence'
    }

});