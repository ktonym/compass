Ext.define('compass.model.uw.Corporate', {

    extend: 'compass.model.uw.Base',

    entityName: 'Corporate',

    idProperty: 'idCorporate',

    fields: [
        { name: 'idCorporate', type: 'int', useNull: true },
        { name: 'corporateName', type: 'string' },
        { name: 'abbreviation', type: 'string' },
        { name: 'email', type: 'auto' },
        { name: 'tel', type: 'string' },
        { name: 'postalAddress', type: 'auto' },
        { name: 'joined', type: 'date', dateFormat: 'Ymd'}

    ],

    validators: {
        corporateName: [
            { type: 'presence', message: 'This field is mandatory' },
            { type: 'length', min: 3, max: 20 }
        ],
        abbreviation: [
            { type: 'presence', message: 'This field is mandatory' },
            { type: 'length', min: 3, max: 3 }
        ],
        email: [
            { type: 'presence', message: 'This field is mandatory' },
            { type: 'length', min:5, max:40 } ,
            { type: 'email' }
        ],
        tel: [
            { type: 'presence', message: 'This field is mandatory' },
            { type: 'length', min:5, max:40 }
        ],
        joined: 'presence'
    }

});
