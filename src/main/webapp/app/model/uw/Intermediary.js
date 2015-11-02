Ext.define('compass.model.uw.Intermediary',{

    extend: 'compass.model.uw.Base',

    idProperty: 'idIntermediary',

    fields: [
        { name: 'idIntermediary', type: 'int', useNull: true },
        { name: 'pin', type: 'string' },
        { name: 'type', type: 'string' },
        { name: 'joinDate', type: 'date', dateFormat: 'Ymd'},
        { name: 'email', type: 'email'},
        { name: 'tel', type: 'string'}
        ],

    validators: {
        pin:      [ { type: 'presence', message: 'This field is mandatory'} ],
        type:     [ { type: 'presence', message: 'This field is mandatory'} ],
        joinDate: 'presence',
        email:    [
                    { type: 'presence', message: 'This field is mandatory' },
                    { type: 'length', min:5, max:40 } ,
                    { type: 'email' }
                  ],
        tel:      [
                    { type: 'presence', message: 'This field is mandatory' },
                    { type: 'length', min:5, max:40 }
                  ]
    }
});