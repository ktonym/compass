Ext.define('compass.model.uw.CorpAnniv', {
    extend: 'Ext.data.Model',
    requires: ['compass.util.Util'],

    idProperty: 'idCorpAnniv',
    
    fields: [
        { name: 'idCorpAnniv', type: 'int', useNull: true },
        { name: 'anniv', type: 'int' },
        { name: 'startDate', type: 'date', dateFormat: 'Ymd' },
        { name: 'endDate', type: 'date', dateFormat: 'Ymd' },
        { name: 'renewalDate', type: 'date', dateFormat: 'Ymd' }

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
            create:'compass/uw/anniv/store.json',
            read: 'compass/uw/anniv/find.json',
            update: 'compass/uw/anniv/store.json',
            destroy: 'compass/uw/anniv/remove.json'
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
        anniv: [
            { type: 'presence', message: 'This field is mandatory' }
        ],
        startDate: [
            { type: 'presence', message: 'This field is mandatory' }
        ],
        endDate: [
            { type: 'presence', message: 'This field is mandatory' }
        ],
        renewalDate: [
            { type: 'presence', message: 'This field is mandatory' }
        ]
    }
});
