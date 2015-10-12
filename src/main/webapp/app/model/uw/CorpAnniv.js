Ext.define('compass.model.uw.CorpAnniv', {
    extend: 'compass.model.uw.Base',

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
