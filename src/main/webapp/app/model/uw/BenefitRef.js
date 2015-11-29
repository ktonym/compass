Ext.define('compass.model.uw.BenefitRef',{
   extend: 'compass.model.uw.Base',

    entityName: 'BenefitRef',

    idProperty: 'benefitCode',

    identifier: 'custom',

    fields: [
        {name: 'benefitCode', type: 'int', useNull: true},
        {name: 'benefitName', type: 'string'},
        {name: 'description', type: 'string'}
    ],

    validators: {
        benefitName: [
            {type: 'presence', message: 'This field is mandatory'},
            {type: 'length', min:3, max: 45}
        ],
        description: [
            {type: 'presence', message: 'This field is mandatory'}
        ]
    }

});
