Ext.define('compass.model.uw.CorpAnniv', {

    extend: 'compass.model.uw.Base',

    entityName: 'CorpAnniv',

    idProperty: 'idCorpAnniv',
    
    fields: [
        { name: 'idCorpAnniv', type: 'int', useNull: true },
        { name: 'anniv', type: 'int' },
        { name: 'inception', type: 'date', dateFormat: 'Ymd' },
        { name: 'expiry', type: 'date', dateFormat: 'Ymd' },
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

    validators: {
        anniv: [
            { type: 'presence', message: 'This field is mandatory' }
        ],
        inception: [
            { type: 'presence', message: 'This field is mandatory' }
        ],
        expiry: [
            { type: 'presence', message: 'This field is mandatory' }
        ],
        renewalDate: [
            { type: 'presence', message: 'This field is mandatory' }
        ]
    }
});
