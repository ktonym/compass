Ext.define('compass.model.uw.BenefitRef',{
   extend: 'compass.model.uw.Base',

    idProperty: 'benefitCode',

    fields: [
        {name: 'benefitCode', type: 'int', useNull: true},
        {name: 'benefitName', type: 'string'},
        {name: 'description', type: 'string'}
    ],

    validations: {
        benefitName: [
            {type: 'presence', message: 'This field is mandatory'},
            {type: 'length', min:3, max: 45}
        ],
        description: [
            {type: 'presence', message: 'This field is mandatory'}
        ]
    }

});
