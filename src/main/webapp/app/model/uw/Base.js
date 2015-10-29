Ext.define('compass.model.uw.Base',{
    extend: 'compass.model.Base',

    fields: [
        {
            name: 'lastUpdate',
            type: 'date',
            dateFormat: 'Ymj H:i'
        }
    ],

    validators: {
        lastUpdate: 'presence'
    }

});