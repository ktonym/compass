Ext.define('compass.model.uw.Agency',{

    extend: 'compass.model.uw.Intermediary',

    entityName: 'Agency',

    fields: [
        { name: 'name', type: 'string' },
        { name: 'street', type: 'string' },
        { name: 'town', type: 'string'},
        { name: 'postalAddress', type: 'string' }
    ],

    validators: {
        name: [
            { type: 'presence', message: 'This field is mandatory'},
            { type: 'length', min: 5, max: 45}
        ],
        street: [
            { type: 'presence', message: 'This field is mandatory'}
        ],
        town: [
            { type: 'presence', message: 'This field is mandatory'},
            { type: 'length', min: 3, max: 45}
        ],
        postalAddress: [
            { type: 'presence', message: 'This field is mandatory'}
        ]
    }

});