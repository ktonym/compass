Ext.define('compass.model.uw.ContactInfo', {
    extend: 'Ext.data.Model',
    requires: ['compass.util.Util'],

    idProperty: 'idContactInfo',

    fields: [
        { name: 'idContactInfo', type: 'int' },
        { name: 'firstName', type: 'string' },
        { name: 'surname', type: 'string' },
        { name: 'jobTitle', type: 'string' },
        { name: 'email', type: 'auto' },
        { name: 'tel', type: 'string' }

    ],

    hasOne: [
        {
            model: 'Corporate',
            name: 'corporate',
            foreignKey: 'idCorporate',
            associationKey: 'corporate'
        }
    ],

    proxy: {
        type: 'ajax',
        idParam: 'idContactInfo',
        api: {
            create:'compass/uw/contactinfo/store.json',
            read: 'compass/uw/contactinfo/find.json',
            update: 'compass/uw/contactinfo/store.json',
            destroy: 'compass/uw/contactinfo/remove.json'
        },
        reader: {
            type: 'json',
            rootProperty: 'data'
        },
        writer: {
            type: 'json',
            allowSingle:true,
            encode:true,
            rootProperty:'data',
            writeAllFields: true
        },
        listeners: {
            exception: function(proxy,response,operation){
                compass.util.Util.showErrorMsg(response.responseText);
            }
        }
    },

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
