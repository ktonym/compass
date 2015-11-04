Ext.define('compass.model.uw.ContactInfo', {
    extend: 'compass.model.uw.Base',

    entityName: 'ContactInfo',

    idProperty: 'idContactInfo',

    fields: [
        { name: 'idContactInfo', type: 'int', useNull: true },
        { name: 'firstName', type: 'string' },
        { name: 'surname', type: 'string' },
        { name: 'jobTitle', type: 'string' },
        { name: 'email', type: 'auto' },
        { name: 'tel', type: 'string' },
        { name: 'fullName', type: 'string', persist: false,
            convert:function(v, record){
                var data = record.getData();
                return (data.firstName ? data.firstName + ' ': '') + data.surname;
            }
        }

    ],

    hasOne: [
        {
            model: 'Corporate',
            name: 'corporate',
            foreignKey: 'idCorporate',
            associationKey: 'corporate'
        }
    ],

    validators: {
        firstName: [
            { type: 'presence', message: 'This field is mandatory' },
            { type: 'length', min: 3, max: 20 }
        ],
        surname: [
            { type: 'presence', message: 'This field is mandatory' },
            { type: 'length', min: 3, max: 20 }
        ],
        jobTitle: [
            { type: 'presence', message: 'This field is mandatory' },
            { type: 'length', min: 3, max: 20 }
        ],
        email: [
            { type: 'presence', message: 'This field is mandatory' },
            { type: 'length', min:5, max:40 } ,
            { type: 'email' }
        ],
        tel: [
            { type: 'presence', message: 'This field is mandatory' },
            { type: 'length', min:5, max:40 }
        ]
    }
});
