Ext.define('compass.model.uw.Agent',{

    extend: 'compass.model.uw.Intermediary',

    entityName: 'Agent',

    fields: [
        { name: 'firstName', type: 'string' },
        { name: 'surname', type: 'string'},
        { name: 'otherNames', type: 'string'}
    ],

    validators: {
        firstName: [
            { type: 'presence', message: 'This field is mandatory'},
            { type: 'length', min:3, max:40 }
        ],
        surname: [
            { type: 'presence', message: 'This field is mandatory'},
            { type: 'length', min:3, max:40 }
        ],
        otherNames: [
            { type: 'presence', message: 'This field is mandatory'},
            { type: 'length', min:3, max:40 }
        ]
    }

});